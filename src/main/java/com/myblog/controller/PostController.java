package com.myblog.controller;

import com.myblog.payload.PostDto;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {


    public PostController(PostService postService) {
        this.postService = postService;
    }

    private PostService postService;


@PostMapping
@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createpost(@RequestBody PostDto postDto){
        PostDto dto =postService.createPost(postDto);
return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getPostById(@RequestParam long id)
            {
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @GetMapping("/all")
    public List<PostDto> getAllPosts(

            @RequestParam (name="PageNo",required=false,defaultValue = "0") int PageNo,
            @RequestParam (name="PageSize",required=false,defaultValue = "3") int PageSize,

            @RequestParam (name="sortBy",required=false,defaultValue = "id") String sortBy,
    @RequestParam (name="sortDir",required=false,defaultValue = "id") String sortDir)
            {
        List<PostDto> PostDtos = postService.getAllPosts(PageNo,PageSize, sortBy, sortDir);

        return PostDtos;
    }
}
