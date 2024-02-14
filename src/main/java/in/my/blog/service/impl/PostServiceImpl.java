package in.my.blog.service.impl;

import in.my.blog.dto.PostDto;
import in.my.blog.entity.Post;
import in.my.blog.util.MapperUtils;
import in.my.blog.repository.PostRepository;
import in.my.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final MapperUtils mapperUtils;

    private final PostRepository postRepository;
    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return mapperUtils.map( posts,PostDto.class );
    }
}
