package in.my.blog.service.impl;

import in.my.blog.dto.CommentDto;
import in.my.blog.entity.Comment;
import in.my.blog.entity.Post;
import in.my.blog.repository.CommentRepository;
import in.my.blog.repository.PostRepository;
import in.my.blog.service.CommentService;
import in.my.blog.util.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository   postRepository;
    private final MapperUtils mapperUtils;


    @Override
    public CommentDto createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl( postUrl ).get();
        Comment comment = mapperUtils.map(commentDto,Comment.class);
        comment.setPost( post );
        commentRepository.save( comment );
        return null;
    }


    public Set<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return mapperUtils.mapToSet(comments, CommentDto.class );
    }

    @Override
    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById( commentId );
    }
}