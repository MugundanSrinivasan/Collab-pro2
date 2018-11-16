package com.niit.dao;

import java.util.List;

import com.niit.models.BlogPost;

public interface BlogPostDAO {
	void addBlogPost(BlogPost blogPost);
	List<BlogPost> getApprovedBlogs();
	BlogPost getBlogPost(int id);
	List<BlogPost> getBlogWaitingForApproval();
	void updateBlogPost(BlogPost blogPost);
	void deleteBlogPost(BlogPost blogPost);
	void updateLike(BlogPost blogPost);
}
