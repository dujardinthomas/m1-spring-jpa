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

import ynov_m1.post.dto.CommentsDTO;
import ynov_m1.post.service.CommentsService;
import ynov_m1.post.tools.ErrorMessage;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {
    
    @Autowired
    private CommentsService commentsService;

    @GetMapping
    public ResponseEntity<List<CommentsDTO>> getAllComments() {
        List<CommentsDTO> comments = commentsService.findAll();
        if(comments.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentsDTO>> getCommentsByPostId(@PathVariable Long postId) {
        List<CommentsDTO> comments = commentsService.findAllByPostId(postId);
        if(comments.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentsDTO> getCommentById(@PathVariable Long id) {
        CommentsDTO comment = commentsService.findById(id);
        if(comment == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(comment);
    }

    @PostMapping()
    public ResponseEntity<Object> createComment(@Validated @RequestBody CommentsDTO comment, BindingResult bindingResult) {
        ResponseEntity<Object> errors = ErrorMessage.getMessageErrors(bindingResult);
        if(errors != null) return errors;
        CommentsDTO createdComment = commentsService.save(comment);
        if(createdComment == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.status(201).body(createdComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentsDTO> updateComment(@PathVariable Long id, @Validated @RequestBody CommentsDTO comment) {
        CommentsDTO updatedComment = commentsService.update(id, comment);
        if(updatedComment == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentsService.delete(id);
        return ResponseEntity.ok("Comments deleted successfully with id : " + id);
    }



    
}
