package com.daily.timotae.constant;

import com.daily.timotae.domain.Post;
import com.daily.timotae.repository.JpaPostRepository;

import java.awt.print.Pageable;
import java.util.List;

public enum SearchType {

    TITLE{
        @Override
        public List<Post> getListBySearchType(JpaPostRepository jpaPostRepository, String keyword, Pageable pageable){
            return jpaPostRepository.findByTitleContaining(keyword, pageable);
        }
    },
    CONTENT{
        @Override
        public List<Post> getListBySearchType(JpaPostRepository jpaPostRepository, String keyword, Pageable pageable){
            return jpaPostRepository.findByContentContaining(keyword, pageable);
        }
    },
    USERID{
        @Override
        public List<Post> getListBySearchType(JpaPostRepository jpaPostRepository, String keyword, Pageable pageable){
            return jpaPostRepository.findByUserIdEquals(keyword, pageable);
        }
    };

    public abstract List<Post> getListBySearchType(JpaPostRepository jpaPostRepository, String keyword, Pageable pageable);
}
