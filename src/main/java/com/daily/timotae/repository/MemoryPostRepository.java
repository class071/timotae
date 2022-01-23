package com.daily.timotae.repository;

import com.daily.timotae.domain.Post;

import java.util.*;

public class MemoryPostRepository implements PostRepository {

    private static Map<Long, Post> store = new HashMap<>();
    private static long seq = 0L;

    @Override
    public Post savePost(Post post){
        seq++;
        store.put(seq, post);
        return post;
    }

    @Override
    public List<Post> findPostAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Post> findPostOne(Long postId) {
        return Optional.ofNullable(store.get(postId));
    }

    @Override
    public void changePost(Long postId, Post post) {
        store.replace(postId, post);
    }

    @Override
    public void removePost(Long postId) {
        store.remove(postId);
    }

}