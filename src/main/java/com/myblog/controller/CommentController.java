package com.myblog.controller;

import com.myblog.payload.CommentDto;
import com.myblog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    public CommentController(CommentService commentservice) {
        this.commentService = commentservice;
    }

    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createcomment(@RequestBody CommentDto commentDto,
                                                    @RequestParam Long postId){
        CommentDto dto = commentService.createcomment(commentDto, postId);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id){
         commentService.deleteComment(id);
        return new ResponseEntity<>("COMMENT IS DELETED", HttpStatus.OK);
    }
    @PutMapping ("/{id}/post/{postId}")
    public ResponseEntity<?> updateComment(@PathVariable long id, @RequestBody CommentDto commentDto, long postId){

        CommentDto dto =  commentService.updateComment(id,commentDto, postId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
