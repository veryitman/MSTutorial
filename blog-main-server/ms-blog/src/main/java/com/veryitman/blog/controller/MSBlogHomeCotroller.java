package com.veryitman.blog.controller;

import com.veryitman.blog.model.MSBlogHomeArticle;
import com.veryitman.blog.model.MSBlogHomeBanner;
import com.veryitman.core.model.MSResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blog/home")
public class MSBlogHomeCotroller {

    @GetMapping(value = "banner")
    public MSResponse banner() {
        MSResponse response = new MSResponse();
        MSBlogHomeBanner banner = MSBlogHomeBanner.builder()
                .bannerId(1)
                .bannerUrl("www.baidu.com")
                .bannerLinker("page://one")
                .bannerTitle("banner-title")
                .bannerDesc("description").build();
        response.setMsg("success");
        response.setCode(0);
        response.setResults(banner);

        return response;
    }

    @GetMapping(value = "popular")
    public MSResponse popular() {
        MSResponse response = new MSResponse();
        response.setMsg("success");
        response.setCode(0);

        MSBlogHomeArticle article = MSBlogHomeArticle.builder()
                .articleId(2)
                .articleUrl("www.baidu.com")
                .articleIcon("http://cdn-icon/")
                .articleOutline("outline content")
                .articleTitle("article title").build();
        response.setResults(article);

        return response;
    }
}
