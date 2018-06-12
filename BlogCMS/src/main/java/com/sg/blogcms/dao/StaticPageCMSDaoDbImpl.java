/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.dao;

import com.sg.blogcms.dto.Category;
import com.sg.blogcms.dto.StaticPage;
import com.sg.blogcms.dto.Tags;
import com.sg.blogcms.dto.User;
import com.sg.blogcms.mappers.CategoryMapper;
import com.sg.blogcms.mappers.StaticPageMapper;
import com.sg.blogcms.mappers.TagMapper;
import com.sg.blogcms.mappers.UserMapper;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author svlln
 */
public class StaticPageCMSDaoDbImpl implements StaticPageCMSDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//    ----------------------------------------------------------------------------
//    **********************PREPARED STATEMENTS **********************************
//    ----------------------------------------------------------------------------

    private static final String SQL_SELECT_STATIC_PAGE
            = "select * from StaticPage where idStaticPage = ? ";
    
    private static final String SQL_SELECT_USER_BY_USERNAME
            = "select * from User where username = ? ";
    
    private static final String SQL_SELECT_ALL_STATIC_PAGES = 
            "select * from StaticPage where isActive = 1";
    
    private static final String SQL_SELECT_ALL_TAGS
            = "select * from Tag";

    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from Categories";

//    ----------------------------------------------------------------------------
//    *******************INTERFACE METHODS****************************************
//    ----------------------------------------------------------------------------
    @Override
    public StaticPage createStaticPage(StaticPage sp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public User selectUserByUsername(String username){
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_USERNAME,
                    new UserMapper(),
                    username);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void removeStaticPage(int spId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StaticPage updateStaticPage(StaticPage sp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StaticPage selectStaticPage(int spId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_STATIC_PAGE,
                    new StaticPageMapper(),
                    spId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<StaticPage> selectAllStaticPages() {
        return jdbcTemplate.query(SQL_SELECT_ALL_STATIC_PAGES,
                new StaticPageMapper());
    }

    @Override
    public List<StaticPage> selectAllStaticPagesByUser(int userID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Tags> selectAllTags(){
        return jdbcTemplate.query(SQL_SELECT_ALL_TAGS,
                new TagMapper());
        
    }
    @Override
    public List<Category> selectAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES,
                new CategoryMapper());
    }
    
    
    
//    ----------------------------------------------------------------------------
//    ********************HELPER METHODS*********************************************
//    ----------------------------------------------------------------------------


}
