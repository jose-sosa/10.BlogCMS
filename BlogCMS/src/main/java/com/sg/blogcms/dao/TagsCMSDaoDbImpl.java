/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.dao;

import com.sg.blogcms.dto.Tags;
import com.sg.blogcms.mappers.BlogMapper;
import com.sg.blogcms.mappers.TagMapper;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author svlln
 */

public class TagsCMSDaoDbImpl implements TagsCMSDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    //==========================================================================
    //                                  SQL TAGS
    //==========================================================================
    
    private static final String SQL_INSERT_TAG
            = "insert into Tag (tagName, tagDescription)"
            + "values(?,?)";
    
     private static final String SQL_SELECT_ALL_TAGS
            = "select * from Tag";
    
    //==========================================================================
    //                              METHODS
    //==========================================================================

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Tags createTag(Tags tag) {
        jdbcTemplate.update(SQL_INSERT_TAG,
                tag.getTagName(),
                tag.getDescription());
        int tagID =
                jdbcTemplate.queryForObject("select LAST_INSERTED_ID()",
                                                Integer.class);
        tag.setTagId(tagID);
        return tag;
    }

    @Override
    public void removeTag(int tagID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tags updateTag(Tags tag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tags SelectTag(int tagID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tags> SelectAllTags() {
        return jdbcTemplate.query(SQL_SELECT_ALL_TAGS,
                new TagMapper());
    }
       
    
    //==========================================================================
    //                              HELPER METHODS
    //==========================================================================
    
}
