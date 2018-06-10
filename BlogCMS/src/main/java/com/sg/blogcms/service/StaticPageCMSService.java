/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.service;

import com.sg.blogcms.dao.StaticPageCMSDao;
import com.sg.blogcms.dto.Category;
import com.sg.blogcms.dto.StaticPage;
import com.sg.blogcms.dto.Tags;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author josesosa
 */
public class StaticPageCMSService {
    StaticPageCMSDao spDao;
    
    
    @Inject
    public StaticPageCMSService(StaticPageCMSDao spDao) {
        this.spDao = spDao;
    }

    public List<StaticPage> selectAllStaticPages() {
        return spDao.selectAllStaticPages();
    }
    
    public StaticPage selectStaticPage(int spId) {
     return spDao.selectStaticPage(spId);
    }
    
    public List<Tags> selectAllTags() {
     return spDao.selectAllTags();
    }
    
    public List<Category> selectAllCategories() {
       return spDao.selectAllCategories();
    }
    
}
