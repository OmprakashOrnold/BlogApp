package in.my.blog.controller;

import in.my.blog.dto.PostDto;
import in.my.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final PostService postService;

    @GetMapping("/")
    public String viewBlogPosts(Model model) {
        List<PostDto> postResponse = postService.findAllPosts();
        model.addAttribute( "postResponse", postResponse );
        return "/blog/view_posts";
    }


    @GetMapping("/post/{postUrl}")
    public String showBlogPosts(@RequestParam("postUrl") String postUrl, Model model) {
        PostDto byPostUrl = postService.findByPostUrl( postUrl );
        model.addAttribute( "post", byPostUrl );
        return "/blog/blog_post";
    }

    @GetMapping("/page/search")
    public String blogSearch(@RequestParam(value = "query") String query, Model mode) {
        List<PostDto> postDtoList = postService.searchPosts( query );
        mode.addAttribute( "postResponse", postDtoList );
        return "/blog/view_posts";
    }

}