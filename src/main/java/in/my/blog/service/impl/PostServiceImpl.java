package in.my.blog.service.impl;

import in.my.blog.dto.PostDto;
import in.my.blog.entity.Post;
import in.my.blog.mapper.PostMapper;
import in.my.blog.repository.PostRepository;
import in.my.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }
}
