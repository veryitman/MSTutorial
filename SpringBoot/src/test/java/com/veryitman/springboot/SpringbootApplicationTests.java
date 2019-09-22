package com.veryitman.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class SpringbootApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void signinTest() throws Exception {
        //直接写接口的映射地址就可以了，不需要写host和port
        String url = "/signin/name?username=itman&userpwd=123";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)) //断言返回结果是json
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        //HTTP响应的返回码
        int status = response.getStatus();
        //HTTP响应的内容
        String contentAsString = response.getContentAsString();

        System.err.println(status);
        System.err.println(contentAsString);
    }

    @Test
    public void signupTest() throws Exception {
        String url = "/signup/name";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON)
                .param("username", "itman")
                .param("userpwd", "123567"))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        String contentAsString = response.getContentAsString();
        System.err.println(status);
        System.err.println(contentAsString);
    }
}
