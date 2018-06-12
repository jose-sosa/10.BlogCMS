/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.dao;

import com.sg.blogcms.dto.BlogPost;
import com.sg.blogcms.dto.Category;
import com.sg.blogcms.dto.Tags;
import com.sg.blogcms.dto.User;
import com.sg.blogcms.mappers.BlogMapper;
import com.sg.blogcms.mappers.CategoryMapper;
import com.sg.blogcms.mappers.TagMapper;
import com.sg.blogcms.mappers.UserMapper;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author svlln
 */
public class BlogsCMSDaoDbImpl implements BlogsCMSDao{
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    //==========================================================================
    //                          SQL BLOGPOST
    //==========================================================================

    private static final String SQL_INSERT_BLOG
            = "insert into BlogPost (title, description, content, author, createdDate, " 
            + "publishDate, expirationDate,approved,idUser ,idCategories) values(?,?,?,?,?,?,?,?,?,?)";
    
    private static final String SQL_DELETE_BLOG
            = "delete from BlogPost where idBlogPost = ?";
    
    private static final String SQL_UPDATE_BLOG
            = "update BlogPost set title = ?, content = ?, author = ?, ";
    
    private static final String SQL_SELECT_BLOG
            = "select * from BlogPost where idBlogPost = ? ";
    
    private static final String SQL_SELECT_BLOG_BY_USER
            = "select * from BlogPost where idUser =? ";
    
    private static final String SQL_SELECT_BLOG_BY_CAT
            = "select * from BlogPost where idCategories = ?";
    
    private static final String SQL_SELECT_BLOG_BY_TAG
            = "select * from BlogPost where idTag = ?";
    
    private static final String SQL_SELECT_LAST_TEN_BLOGPOST
           = "select * FROM BlogPost where approved = 1 ORDER BY idBlogPost DESC LIMIT 10";
    
    private static final String SQL_SELECT_ALL_BLOGS = 
            "select * from BlogPost";
    
    private static final String SQL_SELECT_USER_BY_BLOG
            = "select * from User usr Join BlogPost bp on usr.idUser = bp.idUser where idBlogPost =? ";
    
    private static final String SQL_SELECT_ALL_TAGS
            = "select * from Tag";

    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from Categories";
    
    //==========================================================================
    //                                 METHODS
    //==========================================================================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public BlogPost createBlog(BlogPost blog) {
        jdbcTemplate.update(SQL_INSERT_BLOG,
                blog.getTitle(),
                blog.getDescription(),
                blog.getContent(),
                blog.getAuthor(),
                blog.getCreatedDate(),
                blog.getPublishDate(),
                blog.getExpirationDate(),
                blog.getIsApproved(),
                blog.getUserId(),
                blog.getCatId());
        int blogId = 
                jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                             Integer.class);

        blog.setId(blogId);
        return blog; 
    }

    @Override
    public void removeBlogPost(int blogID) {
        jdbcTemplate.update(SQL_DELETE_BLOG, blogID);
    }

    @Override
    public BlogPost updateBlog(BlogPost blog) {
         jdbcTemplate.update(SQL_UPDATE_BLOG,
                blog.getTitle(),
                blog.getDescription(),
                blog.getContent(),
                blog.getAuthor(),
                blog.getCreatedDate(),
                blog.getPublishDate(),
                blog.getExpirationDate(),
                blog.getIsApproved(),
                blog.getUserId(),
                blog.getCatId());
         return blog;
    }

    @Override
    public BlogPost selectBlog(int blogID) {
         try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BLOG, 
                                               new BlogMapper(), 
                                               blogID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    

    @Override
    public List<BlogPost> selectAllBlogs() {
        return jdbcTemplate.query(SQL_SELECT_ALL_BLOGS,
                new BlogMapper());
    }
    
    @Override
    public List<BlogPost> selectLastTenBlogs() {
       return jdbcTemplate.query(SQL_SELECT_LAST_TEN_BLOGPOST,
               new BlogMapper());
    }
    
    @Override
    public List<BlogPost> selectAllBlogsByUser(int userID) {
       List<BlogPost> blogByUserList = 
                jdbcTemplate.query(SQL_SELECT_BLOG_BY_USER, 
                                   new BlogMapper(), 
                                   userID);
        // set the publisher and list of Authors for each book
       //return associateBlogAndUser(blogByUserList);
       return null;
    }

    @Override
    public List<BlogPost> selectAllBlogsByCategory(int catID) {
        return null;
    }

    @Override
    public List<BlogPost> selectAllBlogsByTag(int tagID) {
        return null;
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
    
    
    //==========================================================================
    //                          HELPER METHODS
    //==========================================================================
    @Override
    public BlogPost appointUserToBlog(BlogPost blog) {
         try {
            User user =  jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_BLOG, 
                                               new UserMapper(), 
                                               blog.getId());
            
            blog.setUser(user);
            return blog;
            
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void removeUnapprovedBlog() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
     
     
     
     
     
     
     
}
