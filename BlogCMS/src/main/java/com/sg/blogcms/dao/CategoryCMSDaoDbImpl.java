/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.dao;

import com.sg.blogcms.dto.Category;
import com.sg.blogcms.mappers.BlogMapper;
import com.sg.blogcms.mappers.CategoryMapper;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author svlln
 */
public class CategoryCMSDaoDbImpl implements CategoryCMSDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //==========================================================================
    //                          SQL CATEGORIES
    //==========================================================================
    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from Categories";
    
    private static final String SQL_SELECT_CATEGORIES_BY_ID
            = "select * from Categories where idCategories = ?";
    
    private static final String SQL_DELETE_CATEGORY
            = "delete from Categories where idCategories = ?";
    
    private static final String SQL_UPDATE_CATEGORY
            = "update Categories set catName = ?, description = ? where idCategories = ?";

    //==========================================================================
    //                                 METHODS
    //==========================================================================
    
    @Override
    public List<Category> selectAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES,
                new CategoryMapper());
    }

    @Override
    public void removeCategory(int catID) {
         jdbcTemplate.update(SQL_DELETE_CATEGORY, catID);
    }
    
    @Override
    public Category updateCategory(Category cat) {
         jdbcTemplate.update(SQL_UPDATE_CATEGORY,
                 cat.getCatId(),
                 cat.getDescription());
         return cat;
    }
    
    public Category selectCategory(int catID){
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CATEGORIES_BY_ID, 
                                               new CategoryMapper(), 
                                               catID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
}
