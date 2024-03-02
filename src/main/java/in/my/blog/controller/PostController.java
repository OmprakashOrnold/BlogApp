package in.my.blog.controller;


import in.my.blog.dto.PostDto;
import in.my.blog.entity.Post;
import in.my.blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

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

    @GetMapping("/admin/newpost")
    public String handleNewPostForm(Model model) {
        PostDto postDto = new PostDto();
        model.addAttribute( "post", postDto );
        return "/admin/newpost";
    }

    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute( "post", postDto );
            return "/admin/newpost";
        }
        postDto.setUrl( getUrl( postDto.getTitle() ) );
        postService.createPost( postDto );
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postId}/edit")
    public String updatePost(@PathVariable("postId") Long postId, Model model) {
        PostDto postDto = postService.findPostById( postId );
        model.addAttribute( "post", postDto );
        return "/admin/edit_post";
    }


    @PostMapping("/admin/posts/{postId}/edit")
    public String updatePost(@PathVariable("postId") Long postId, @Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute( "post", postDto );
            return "/admin/edit_post";
        }
        postDto.setId( postId );
        postService.updatePost( postDto );
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost( postId );
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl, Model model) {
        PostDto postDto = postService.findByPostUrl( postUrl );
        model.addAttribute( "post", postDto );
        return "/admin/view_post";
    }


    @GetMapping("/admin/posts/search")
    public String searchPost(@RequestParam("query") String query ,Model model) {
       List<PostDto> postDto= postService.searchPosts( query );
        model.addAttribute( "posts", postDto );
        return "/admin/posts";
    }


    private static String getUrl(String postTitle) {
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll( "\\s+", "-" );
        url = url.replaceAll( "[^A-Za-z0-9]", "-" );
        return url;
    }


}
