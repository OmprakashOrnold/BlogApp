package in.my.blog.mapper;

import in.my.blog.dto.PostDto;
import in.my.blog.entity.Post;

public class PostMapper {

    public static PostDto mapToPostDto(Post post) {
        PostDto postDto = PostDto.builder()
                .id( post.getId() )
                .title( post.getTitle() )
                .content( post.getContent() )
                .url( post.getUrl() )
                .shortDescription( post.getShortDescription() )
                .createdOn( post.getCreatedOn() )
                .updatedOn( post.getUpdatedOn() )
                .build();
        return postDto;
    }


    public static Post mapToPost(PostDto postDto) {
        Post post = Post.builder()
                .id( postDto.getId() )
                .title( postDto.getTitle() )
                .content( postDto.getContent() )
                .url( postDto.getUrl() )
                .shortDescription( postDto.getShortDescription() )
                .createdOn( postDto.getCreatedOn() )
                .updatedOn( postDto.getUpdatedOn() )
                .build();
        return post;
    }
}
