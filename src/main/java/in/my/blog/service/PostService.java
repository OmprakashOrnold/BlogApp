package in.my.blog.service;

import in.my.blog.dto.PostDto;
import in.my.blog.entity.Post;

import java.util.List;

public interface PostService {

    List<PostDto> findAllPosts();

    void createPost(PostDto postDto);

    PostDto findPostById(Long id);

    void updatePost(PostDto postDto);

    void deletePost(Long id);

    PostDto findByPostUrl(String postUrl);

    List<PostDto> searchPosts(String searchQuery);


}
