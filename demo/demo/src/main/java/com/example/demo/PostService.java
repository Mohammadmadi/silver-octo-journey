package com.example.demo;

import java.util.List;

public interface PostService {

	Post creatPost(Post post);
	List<Post> getAllPosts();
	Post getPostById(long Id);
	Post updatePost(long id,Post post);
	void deletePost(Long id);

}
