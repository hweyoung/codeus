package com.codeus.service;

import com.codeus.domain.Post;
import com.codeus.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post createPost(Post post){

        Post newPost = postRepository.save(post);
        return newPost;
    }

    @Transactional
    public List<Post> findAll(){//추가함
        List<Post> posts  = postRepository.findAll();
        return posts;
    }
}
