package com.timshuns.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.timshuns.mapper.CommentMapper;
import com.timshuns.mapper.TagMapper;
import com.timshuns.pojo.Comment;
import com.timshuns.service.CommentService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

  @Autowired private CommentMapper commentMapper;

  @Transactional
  @Override
  public boolean saveComment(Comment comment) {
    // 新增失敗，返回空值
    boolean result = false;
    try {
      result = (commentMapper.insert(comment) > 0);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return result;
  }

  @Override
  public List<Comment> getCommentsByBlogId(long blogId) {
    Map<String, Object> queryMap = new HashMap<String, Object>();
    queryMap.put("blog_id", blogId);
    List<Comment> comments = commentMapper.selectByMap(queryMap);
    return comments;
  }

  @Override
  public Comment getCommentById(long id) {
    return commentMapper.selectById(id);
  }
}
