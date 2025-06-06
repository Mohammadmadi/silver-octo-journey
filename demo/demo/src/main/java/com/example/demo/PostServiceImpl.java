package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public Post creatPost(Post post) {
		// TODO Auto-generated method stub
		return postRepository.save(post);
	}

	@Override
	public List<Post> getAllPosts() {
		// TODO Auto-generated method stub
		return postRepository.findAll();
	}

	@Override
	public Post getPostById(long id) {
		// TODO Auto-generated method stub
		Post post= postRepository.findById(id).orElseThrow(() -> new RuntimeException("post not found with id:"+id));
	return post;
	}

	@Override
	public Post updatePost(long id, Post post) {
		// TODO Auto-generated method stub
		Post p = getPostById(id);
		p.setTitle(post.getTitle());
		p.setContent(post.getContent());
		return postRepository.save(p);
	}

	@Override
	public void deletePost(Long id) {
		// TODO Auto-generated method stub
		Post post = getPostById(id);
		postRepository.delete(post);
	}

}
