package controller;

import common.ResponseResult;
import controller.reqbody.NoteInsertReqBody;
import model.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.NoteService;
import service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class NoteController {
    public static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    /**
     * 保存Note
     */
    @RequestMapping(value = "/{username}/notes/insert", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult insertNote(@PathVariable String username,
                                     @RequestBody NoteInsertReqBody body) {
        Integer userId = userService.getUserByUsername(username).getUserId();
        Note note = new Note(userId, body.getParentId(), body.getTitle(), body.getContent());
        noteService.insertNote(note);
        Map<String, Object> data = new HashMap<>();
        data.put("id", note.getId());
        return new ResponseResult(data,201,"笔记创建成功");
    }

    @RequestMapping(value = "/note/{id}")
    public String goNote(@PathVariable Integer id) {
        return "/detail";
    }

    /**
     * 获取Note
     */
    @RequestMapping(value = "/{username}/notes/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getNote(@PathVariable("id") Integer id) {
        Note note = noteService.getNote(id);
        Map<String, Object> data = note.getNote();
        return new ResponseResult(data,201,"获取成功");
    }

    /**
     * 编辑Note
     */
    @RequestMapping(value = "/{username}/notes/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult edit(@PathVariable("id") Integer id,
                        @RequestParam("title") String title, @RequestParam("content") String content) {
        Note note = noteService.getNote(id);
        note.setTitle(title);
        note.setContent(content);
        noteService.updateNote(note);
        Map<String, Object> data = new HashMap<>();
        data.put("id",id);
        return  new ResponseResult(data,201,"修改成功");
    }

    /**
     * 获取用户的所有Note
     */
    @RequestMapping(value = "/{username}/notes",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<List<Note>> getNoteList(@PathVariable String username) {
        Integer userId = userService.getUserByUsername(username).getUserId();
        List<Note> notes = noteService.listNote(userId);
        return new ResponseResult<>(notes,201,"获取成功");
}
}