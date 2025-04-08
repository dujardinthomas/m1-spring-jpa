package ynov_m1.post.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ynov_m1.post.dto.PostsDTO;
import ynov_m1.post.service.PostsService;
import ynov_m1.post.tools.ErrorMessage;

@RestController
@RequestMapping("/api/posts")
public class PostsController {

    @Autowired
    private PostsService postService;

    @GetMapping
    public ResponseEntity<List<PostsDTO>> getAllPosts() {
        List<PostsDTO> posts = postService.findAll();
        if(posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostsDTO> getPostById(@PathVariable Long id) {
        PostsDTO post = postService.findById(id);
        if(post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<Object> createPost(@Validated @RequestBody PostsDTO post, BindingResult bindingResult) {
        ResponseEntity<Object> errors = ErrorMessage.getMessageErrors(bindingResult);
        if(errors != null) {
            return errors;
        }
        PostsDTO createdPost = postService.save(post);
        if(createdPost == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(createdPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostsDTO> updatePost(@PathVariable Long id, @Validated @RequestBody PostsDTO post) {
        PostsDTO updatedPost = postService.update(id, post);
        if(updatedPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.ok("Post deleted successfully with id : " + id);
    }


}
