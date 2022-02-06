package com.daily.timotae.constant;

import com.daily.timotae.domain.Post;
import com.daily.timotae.repository.JpaPostRepository;

import java.util.List;

public enum SearchType {

    TITLE{
        @Override
        public List<Post> getListBySearchType(JpaPostRepository jpaPostRepository, String keyword){
            return jpaPostRepository.findByTitleContaining(keyword);
        }
    },
    CONTENT{
        @Override
        public List<Post> getListBySearchType(JpaPostRepository jpaPostRepository, String keyword){
            return jpaPostRepository.findByContentContaining(keyword);
        }
    },
    USERID{
        @Override
        public List<Post> getListBySearchType(JpaPostRepository jpaPostRepository, String keyword){
            return jpaPostRepository.findByUserIdEquals(keyword);
        }
    };

    public abstract List<Post> getListBySearchType(JpaPostRepository jpaPostRepository, String keyword);
}
