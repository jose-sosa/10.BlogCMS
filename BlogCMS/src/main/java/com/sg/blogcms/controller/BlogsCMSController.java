/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.controller;

import com.sg.blogcms.dto.BlogPost;
import com.sg.blogcms.service.BlogsCMSService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author josesosa
 */

@Controller
public class BlogsCMSController {
    //BlogCMS service;
    BlogsCMSService blogsService;
    
    
    
    @Inject
    public BlogsCMSController(BlogsCMSService blogsService) {
        this.blogsService = blogsService;
        
    }
    
    @RequestMapping(value = {"/", "index", ""}, method = RequestMethod.GET)
    public String landingPage(HttpServletRequest request, Model model) {
        List<BlogPost> lastTenBlogs;
        lastTenBlogs = blogsService.selectLastTenBlogs();
        model.addAttribute("lastTenBlogs", lastTenBlogs);
        return "index";
    }

    
    @RequestMapping(value = "/displayUserProfilePage/{blogID}", method = RequestMethod.GET)
    public String getItemSelected(Model model, @PathVariable int blogID) {
//        userService.selectUserProfile(blogID);
        return "redirect:/";
    }


//======================= FOR ALL BLOGS ==================================================

    @RequestMapping(value= {"/blogs"}, method = RequestMethod.GET)
    public String blogsPage(HttpServletRequest request, Model model) {
        List<BlogPost> blogPosts;
        blogPosts = blogsService.selectAllBlogs();
        model.addAttribute("allBlogs", blogPosts);
        return "blogs";

    }
    
}
