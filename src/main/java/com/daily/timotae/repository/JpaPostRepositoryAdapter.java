package com.daily.timotae.repository;

import com.daily.timotae.constant.SearchType;
import com.daily.timotae.domain.Post;
import com.daily.timotae.exception.post.NoSuchPostExist;
import com.daily.timotae.exception.post.NotSupportSuchTypeException;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import static com.daily.timotae.constant.PostConstant.POST_NOT_EXIST;

@Component
public class JpaPostRepositoryAdapter implements PostRepository {

    private JpaPostRepository jpaPostRepository;

    @Override
    public Post savePost(Post post) {
        return jpaPostRepository.save(post);
    }

    @Override
    public List<Post> findPostAll() {
        return jpaPostRepository.findAll();
    }

    @Override
    public Optional<Post> findPostOne(Long postId) {
        return jpaPostRepository.findById(postId);
    }

    @Override
    public void changePost(Long postId, Post post) {
        Post newPost = findPostOne(postId)
                .orElseThrow( () -> new NoSuchPostExist());
        newPost.update(post.getTitle(), post.getCategory(), post.getUserId(), post.getContent());
    }

    @Override
    public void removePost(Long postId) {
        jpaPostRepository.deleteById(postId);
    }

    @Override
    public List<Post> searchPost(String type, String keyword, Pageable pageable) {
        SearchType searchType = SearchType.valueOf(type);
        try {
            return searchType.getListBySearchType(jpaPostRepository, keyword, pageable);
        }
        catch(IllegalArgumentException e){
            throw new NotSupportSuchTypeException();
        }
    }

    @Override
    public List<Post> findAllPaging(Pageable pageable) {
        return jpaPostRepository.findAll(pageable);
    }
}
