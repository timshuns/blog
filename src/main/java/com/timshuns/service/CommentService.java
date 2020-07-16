package com.timshuns.service;

import java.util.List;
import com.timshuns.pojo.Comment;

public interface CommentService {
  boolean saveComment(Comment comment);
  
  List<Comment> getCommentsByBlogId(long blogId);
  
  Comment getCommentById(long id);
}
