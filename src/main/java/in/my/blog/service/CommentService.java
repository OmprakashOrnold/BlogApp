package in.my.blog.service;

import in.my.blog.dto.CommentDto;
import in.my.blog.entity.Comment;

import java.util.Set;

public interface CommentService {

    CommentDto createComment(String postUrl,CommentDto commentDto);

    Set<CommentDto> findAllComments();
}
