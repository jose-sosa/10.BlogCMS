/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.controller;

import com.sg.blogcms.dto.BlogPost;
import com.sg.blogcms.dto.Category;
import com.sg.blogcms.dto.Tags;
import com.sg.blogcms.service.BlogsCMSService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    BlogsCMSService blogsService;
    
    
    
    @Inject
    public BlogsCMSController(BlogsCMSService blogsService) {
        this.blogsService = blogsService;
        
    }
    
    @RequestMapping(value = {"/", "index", ""}, method = RequestMethod.GET)
    public String landingPage(HttpServletRequest request, Model model) {
        List<BlogPost> lastTenBlogs;
        lastTenBlogs = blogsService.selectLastTenBlogs();
        blogsService.updateListOfBlogs(lastTenBlogs);
        model.addAttribute("lastTenBlogs", lastTenBlogs);
        return "index";
    }

//======================= FOR ALL BLOGS ==================================================

    @RequestMapping(value= {"/blogs"}, method = RequestMethod.GET)
    public String blogsPage(HttpServletRequest request, Model model) {
        List<BlogPost> allBlogs;
        allBlogs = blogsService.selectAllBlogs();
        model.addAttribute("allBlogs", allBlogs);
        return "blogs";
    }
    
    @RequestMapping(value= {"/displayCreateBlogPostPage"}, method = RequestMethod.GET)
    public String displayCreateBlogPostPage(HttpServletRequest request,Model model) {
            List<Category> allCategories = blogsService.selectAllCategories();
            List<Tags> allTags = blogsService.selectAllTags();
            model.addAttribute("allCategories",allCategories);
            model.addAttribute("allTags",allTags);
        return "createBlogPost";
    }
    
    @RequestMapping(value = "/createBlogPost", method = RequestMethod.POST)
    public String createBlogPost(HttpServletRequest request ,Model model) {
        BlogPost bp = new BlogPost();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String username = request.getParameter("username");
        
	Date date = new Date();
        bp.setTitle(request.getParameter("title"));
        bp.setDescription(request.getParameter("description"));
        bp.setContent(request.getParameter("content"));
        bp.setAuthor(username);
        bp.setCreatedDate(date);
        bp.setPublishDate(date);
        bp.setExpirationDate(date);
        bp.setIsApproved(true);
        bp.setUserId(9);
        bp.setCatId(1);
        
        blogsService.createBlog(bp);
        
        List<BlogPost> allBlogs;
        allBlogs = blogsService.selectAllBlogs();
        model.addAttribute("allBlogs", allBlogs);
        
        return "blogs";
    }
    
    @RequestMapping(value="/displayBlog/{blogId}", method = RequestMethod.GET)
    public String displayBlog(HttpServletRequest request, Model model,@PathVariable int blogId){
        BlogPost bp = blogsService.selectBlog(blogId);
        model.addAttribute("bp",bp);
        
        return "blogpost";
    }
    
    @RequestMapping(value = {"/createBlogPost/{viewType2}"}, method = RequestMethod.GET)
    public String blogPostAddCategory(HttpServletRequest request, Model model,@PathVariable String viewType2) {
        
        List<Category> allCategories = blogsService.selectAllCategories();

        model.addAttribute("viewType2",viewType2);
        model.addAttribute("allCategories",allCategories);
        return "createBlogPost";
    }
    
    @RequestMapping(value = {"/createBlogPost/{viewType}"}, method = RequestMethod.GET)
    public String blogPostAddTag(HttpServletRequest request, Model model,@PathVariable String viewType) {
        
        List<Tags> allTags = blogsService.selectAllTags();
        
        
        
        
        model.addAttribute("viewType",viewType);
        model.addAttribute("allTags",allTags);
        return "createBlogPost";
    }
    
}
