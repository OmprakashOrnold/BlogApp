package in.my.blog.service.impl;

import in.my.blog.dto.PostDto;
import in.my.blog.entity.Post;
import in.my.blog.util.MapperUtils;
import in.my.blog.repository.PostRepository;
import in.my.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final MapperUtils mapperUtils;

    private final PostRepository postRepository;

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return mapperUtils.map( posts, PostDto.class );
    }

    @Override
    public void createPost(PostDto postDto) {
        Post post = mapperUtils.map( postDto, Post.class );
        postRepository.save( post );
    }

    @Override
    public PostDto findPostById(Long id) {
        Optional<Post> post = postRepository.findById( id );
        return post.map( value -> mapperUtils.map( value, PostDto.class ) ).orElse( null );
    }

    @Override
    public void updatePost(PostDto postDto) {
        Post post = mapperUtils.map( postDto, Post.class );
        postRepository.save( post );
    }
}
