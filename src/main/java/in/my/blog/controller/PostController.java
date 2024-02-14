package in.my.blog.controller;


import in.my.blog.dto.PostDto;
import in.my.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/admin/posts")
    public String posts(Model model) {
        List<PostDto> postDtoList = postService.findAllPosts();
        model.addAttribute( "posts", postDtoList );
        return "/admin/posts";
    }

}
