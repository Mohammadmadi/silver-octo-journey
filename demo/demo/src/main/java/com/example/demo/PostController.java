package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping
	public ResponseEntity<List<Post>> getAllPosts() {
		List<Post> posts = postService.getAllPosts();
		return ResponseEntity.ok(posts);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable Long id) {
		Post post = postService.getPostById(id);
		return ResponseEntity.ok(post);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
		Post updatedPost = postService.updatePost(id, postDetails);
		return ResponseEntity.ok(updatedPost);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable Long id) {
		postService.deletePost(id);
		return ResponseEntity.noContent().build();

	}

	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		Post saved = postService.creatPost(post);

		PostDto responseDto = new PostDto();
		responseDto.setId(saved.getId());
		responseDto.setTitle(saved.getTitle());
		responseDto.setContent(saved.getContent());

		return ResponseEntity.ok(responseDto);
	}

}
