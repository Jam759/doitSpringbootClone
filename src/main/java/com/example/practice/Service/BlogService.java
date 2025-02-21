package com.example.practice.Service;

import com.example.practice.Controller.DTO.AddArticleRequest;
import com.example.practice.Controller.DTO.ArticleResponse;
import com.example.practice.Repository.BlogRepository;
import com.example.practice.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request, String userName){
        return blogRepository.save(request.toEntity(userName));
    }

    public List<Article> findAll(){
        return blogRepository.findAll();

    }

    public Article findById(Long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found: " + id));
    }

    public void delete(Long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(Long id, AddArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found: "+id));
        article.update(request.getTitle(), request.getContent());

        return article;
    }

}
