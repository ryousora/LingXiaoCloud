/*
package controller;

import common.ResponseResult;
import controller.reqbody.LoginReqBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    */
/**
     * POST login/authentication <br/>
     * 检查用户名与密码，若用户名与密码匹配，返回一个该用户的Token，
     * 该Token可用于访问该用户的资源
     *//*

    @RequestMapping(value = "/user/login/authentication", method = RequestMethod.POST)
    public ResponseResult authentication(@RequestBody @Valid LoginReqBody reqBody) {
        String token = userService.login(reqBody.getUsername(), reqBody.getPassword());
        ResponseResult result = new ResponseResult();
        Map<String, Object> data = new HashMap<>();
        if (token.equals("登陆失败"))
            result.setStatus(401);
        else result.setStatus(200);
        data.put("token", token);
        result.setData(data);
        return result;
    }
}*/
