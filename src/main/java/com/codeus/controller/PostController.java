package com.codeus.controller;

import com.codeus.domain.Post;
import com.codeus.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        Post newPost = postService.createPost(post);
        return ResponseEntity.ok(newPost);
    }

    @GetMapping("") //추가함
    public List<Post> retrieveAllPosts() {
        return postService.findAll();
    }
}
