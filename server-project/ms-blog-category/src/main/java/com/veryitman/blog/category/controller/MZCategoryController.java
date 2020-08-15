package com.veryitman.blog.category.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MZCategoryController {

    @GetMapping(value = "blog/category")
    public String category() {
        return "mz-blog-category-haha";
    }
}
