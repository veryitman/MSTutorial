package com.veryitman.blog.home.model;

import lombok.*;

@Builder
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class MSBlogHomeArticle {

    private int articleId;

    // 文章标题
    private String articleTitle;

    // 文章概要
    private String articleOutline;

    // 文章地址
    private String articleUrl;

    // 文章icon
    private String articleIcon;
}
