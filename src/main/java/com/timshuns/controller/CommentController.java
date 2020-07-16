package com.timshuns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.timshuns.pojo.Comment;
import com.timshuns.service.CommentService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CommentController {
  
  @Autowired
  private CommentService commentService;
  

  @PostMapping({"/blog/comment/{blogId}"})
  public String comment(Model model, @PathVariable long blogId, Comment comment) {
    log.info("comment");
    
    comment.setBlogId(blogId);
    commentService.saveComment(comment);

    return "redirect:/blog/"+blogId;
  }
}
