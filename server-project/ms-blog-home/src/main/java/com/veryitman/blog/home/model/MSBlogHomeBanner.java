package com.veryitman.blog.home.model;

import lombok.*;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class MSBlogHomeBanner {

    private int bannerId;
    private String bannerUrl;
    private String bannerTitle;
    private String bannerDesc;
    private String bannerLinker;
}
