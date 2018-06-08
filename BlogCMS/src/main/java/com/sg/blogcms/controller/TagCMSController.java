/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.controller;

import com.sg.blogcms.dto.Tags;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sg.blogcms.service.TagsCMSService;

/**
 *
 * @author josesosa
 */
@Controller
public class TagCMSController {
    TagsCMSService tagService;
    Tags tag;
    
    
    @Inject
    public TagCMSController(TagsCMSService tagService) {
        this.tagService = (TagsCMSService) tagService;
        
    }
    
    //==========================================================================
    //                              CONTROLLER FOR TAGS
    //==========================================================================
    
    @RequestMapping(value = "/createTag", method = RequestMethod.POST)
    public String createTag(HttpServletRequest request, Model model){
        Tags newTag = new Tags();
        newTag.setTagName(request.getParameter("tagName"));
        newTag.setDescription(request.getParameter("tagDescription"));
        tagService.createTag(newTag);
        return "redirect:about";
    }
    
    
    @RequestMapping(value= {"/about"}, method = RequestMethod.GET)
    public String displayAllTags(HttpServletRequest request, Model model) {
        List<Tags> allTags;
        allTags = tagService.SelectAllTags();
        model.addAttribute("allTags", allTags);
        model.addAttribute("tag", tag);
        return "about";
    }
    
    @RequestMapping(value = "/chooseTag", method = RequestMethod.GET)
    public String chooseTagToUpdate(HttpServletRequest request, Model model) {
        int tagID = Integer.parseInt(request.getParameter("idTag"));
        String viewType = request.getParameter("viewType");
        model.addAttribute("viewType", viewType);
        Tags tags = tagService.SelectTag(tagID);
        model.addAttribute("tags",tags);
        model.addAttribute("tagID", tagID);
        List<Tags> allTags;
        allTags = tagService.SelectAllTags();
        model.addAttribute("allTags", allTags);
        return "tags";
    }
    
    
    @RequestMapping(value = "/updateTag", method = RequestMethod.GET)
    public String updateTag(HttpServletRequest request, Model model){
        int tagID = Integer.parseInt(request.getParameter("idTag"));
        Tags tag = tagService.SelectTag(tagID);
        tag.setTagName(request.getParameter("tagName"));        
        tag.setDescription(request.getParameter("tagDescription"));
        tagService.updateTag(tag);

        return "redirect:about";
    }
   
    @RequestMapping(value= {"/deleteTag"}, method = RequestMethod.GET)
    public String removeTag(HttpServletRequest request, Model model) {
        int tagID = Integer.parseInt(request.getParameter("tagId"));
        tagService.removeTag(tagID);
        return "redirect:about";
    }
    
}
