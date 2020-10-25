package com.veryitman.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MSCategoryController {

    @GetMapping(value = "blog/category")
    public String category() {
        return "mz-blog-category-haha-hehe";
    }
}
