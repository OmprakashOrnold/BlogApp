package in.my.blog.controller;

import in.my.blog.dto.CommentDto;
import in.my.blog.dto.PostDto;
import in.my.blog.repository.CommentRepository;
import in.my.blog.service.CommentService;
import in.my.blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentsController {

    private final CommentService commentService;

    private final PostService postService;

    @PostMapping("/{postUrl}/comments")
    public String createComment(@PathVariable("postUrl") String postUrl, @Valid  @ModelAttribute("comment") CommentDto commentDto
            , BindingResult result , Model model) {
      PostDto postDto= postService.findByPostUrl( postUrl);
        if(result.hasErrors()){
            model.addAttribute( "comment",commentDto );
            model.addAttribute( "post",postDto );
            return "blog/blog_post";
        }
        commentService.createComment( postUrl, commentDto );
        return "redirect:/post/postUrl?postUrl=" + postUrl;

    }

    @GetMapping("/admin/posts/comments/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId){
        commentService.deleteCommentById(commentId);
        return "redirect:/admin/comments";
    }

}
