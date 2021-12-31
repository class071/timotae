package com.daily.timotae.repository;

import com.daily.timotae.domain.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Post findByPostId(Long postId) {
        return store.get(postId);
    }

    @Override
    public Map<Long, Post> findAllWithPostId() {
        return store;
    }

    @Override
    public void changePost(Long postId, Post post) {
        store.replace(postId, post);
    }

    @Override
    public void removePost(Long postId) {
        store.remove(postId);
    }

    @Override
    public List<Post> findAll(){
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearStore() {
        store.clear();
    }

}