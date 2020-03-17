package controller;

import common.ResponseResult;
import model.User;
import model.UserFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.DiskService;
import service.UserService;
import service.dto.UserDTO;
import util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DiskService diskService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult register(@org.jetbrains.annotations.NotNull @RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        //String salt = UUID.randomUUID().toString();
        //// 密码加盐
        //user.setSalt(salt);
        //user.setPassword(Md5Util.md5(user.getPassword() + salt));

        ResponseResult result = new ResponseResult();
        Map<String, Object> data = new HashMap<>();

        if (username == null || password == null) {
            result.setStatus(400);
            result.setMessage("请输入用户名和密码");
            return result;
        }

        int rowCount;
        try {
            userService.insertUser(user);
            rowCount = userService.selectRowCount();
        } catch (DuplicateKeyException e) {
            result.setStatus(400);
            result.setMessage("用户名已存在");
            return result;
        }
        if (rowCount != 0) {
            diskService.newFolder(new UserFolder(userService.getUserByUsername(username).getUserId(),0,"我的文件"));
            data.put("username",username);
            result.setData(data);
            result.setStatus(200);
            result.setMessage("注册成功");
            return result;
        }
        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        //password = Md5Util.md5(user.getPassword() + user.getSalt());

        ResponseResult result = new ResponseResult();
        Map<String, Object> data = new HashMap<>();

        if (username == null || password == null) {
            result.setData(data);
            result.setStatus(400);
            result.setMessage("未输入用户名或密码");
            return result;
        }

        boolean exists = userService.checkUsername(username);
        if (!exists) {
            result.setData(data);
            result.setStatus(400);
            result.setMessage("不存在此用户");
            return result;
        }

        boolean success = userService.checkPassword(username, password);
        if (!success) {
            result.setData(data);
            result.setStatus(400);
            result.setMessage("密码错误");
            return result;
        } else {
            //String token = JwtUtil.generToken("Shawyer", null, null);
            //data.put("token", token);

            String token = userService.login(username, password);
            if (token.equals("登陆失败")) {
                result.setStatus(401);
                result.setMessage("登录失败");
            }
            else result.setStatus(200);
            data.put("token", token);
            data.put("id", userService.getUserByUsername(username).getUserId());
            data.put("username",username);
            result.setData(data);
            result.setMessage("登录成功");
            return result;
        }
    }

    // http://localhost:8080/user/check?username=admin
    @RequestMapping("/checkname")
    @ResponseBody
    public ResponseResult isExistsUserName(@RequestBody String username) {
        ResponseResult result = new ResponseResult();
        Map<String, Object> data = new HashMap<>();
        System.out.println("______=======+++++++++=====______"+username);
        username=username.replace("\"", "");
        System.out.println("______=======+++++++++=====______"+username);
        boolean exists = userService.checkUsername(username);
        if (exists) {
            data.put("isExists", true);
            result.setData(data);
            result.setStatus(201);
            result.setMessage("用户名已存在");
            return result;
        } else {
            data.put("isExists", false);
            result.setData(data);
            result.setStatus(200);
            result.setMessage("查询成功，不存在此用户");
            return result;
        }
    }

/*
    @RequestMapping("/check/{token}")
    @ResponseBody
    public ResponseResult check(@PathVariable String token,@RequestBody String username) {
        ResponseResult result = new ResponseResult();
        Map<String, Object> data = new HashMap<>();
        System.out.println("______=======+++++++++=====______"+username);
        username=username.replace("\"", "");
        System.out.println("______=======+++++++++=====______"+username);
        if(!username.equals(JwtUtil.parseToken(token))){
            result.setStatus(400);
            result.setMessage("验证失败");
            return result;
        }
        data.put("id",userService.getUserByUsername(username).getUserId().toString());
        data.put("username", username);
        result.setStatus(200);
        result.setData(data);
        result.setMessage("验证通过");
        return result;
    }
*/

    @RequestMapping(value = "/{username}/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@PathVariable String username, @RequestBody User user) {
        ResponseResult result = new ResponseResult();
        Map<String, Object> data = new HashMap<>();

        int success = userService.deleteUser(username, user.getPassword());
        if (success == 1) {
            result.setData(data);
            result.setStatus(204);
            result.setMessage("删除成功");
            return result;
        } else {
            result.setData(data);
            result.setStatus(400);
            result.setMessage("删除失败");
            return result;
        }

    }

    @RequestMapping(value = "/{username}/changePass", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult changePass(@PathVariable String username,@RequestBody User user) {
        ResponseResult result = new ResponseResult();
        Map<String, Object> data = new HashMap<>();

        int success = userService.changePass(username, user.getPassword());

        if (success == 1) {
            result.setData(data);
            result.setStatus(201);
            result.setMessage("密码修改成功");
            return result;
        } else {
            result.setData(data);
            result.setStatus(400);
            result.setMessage("密码修改失败");
            return result;
        }
    }

    @RequestMapping(value = "/{username}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult edit(@PathVariable String username,@RequestBody User user) {
        ResponseResult result = new ResponseResult();
        Map<String, Object> data = new HashMap<>();

        user.setUsername(username);//防恶意篡改

        int success = userService.updateUser(user);
        if (success == 1) {
            result.setData(data);
            result.setStatus(201);
            result.setMessage("修改成功");
            return result;
        } else {
            result.setData(data);
            result.setStatus(400);
            result.setMessage("修改失败");
            return result;
        }
    }

    // http://localhost:8080/user/infoByUsername?username=admin
    @RequestMapping("/{username}/getUserInfo")
    @ResponseBody
    public ResponseResult info(@PathVariable String username) {
        ResponseResult result = new ResponseResult();
        Map<String, Object> data = new HashMap<>();

        UserDTO getUser = userService.getUserByUsername(username);

        if (getUser != null) {
            data.put("user", getUser);
            result.setData(data);
            result.setStatus(201);
            result.setMessage("用户信息获取成功");
            return result;
        } else {
            result.setData(data);
            result.setStatus(400);
            result.setMessage("请重新登录");
            return result;
        }
    }

    // http://localhost:8080/user/infoByUserId?userId=admin
    @RequestMapping("/infoByUserId")
    @ResponseBody
    public ResponseResult infoById(@RequestBody User user) {
        ResponseResult result = new ResponseResult();
        Map<String, Object> data = new HashMap<>();

        Integer userId = user.getUserId();
        UserDTO getUser = userService.getUserByUserId(userId);

        if (getUser != null) {
            data.put("user", getUser.toString());
            result.setData(data);
            result.setStatus(201);
            result.setMessage("用户信息获取成功");
            return result;
        } else {
            result.setData(data);
            result.setStatus(400);
            result.setMessage("用户信息获取失败");
            return result;
        }
    }
}