package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commRepository;
	@Autowired
	private PostRepository postRepository;

	@Override
	public Comment addCommentToPost(Long postId, Comment comment) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new RuntimeException("post not found with id" + postId));
		comment.setPost(post);
		return commRepository.save(comment);
	}

	@Override
	public List<Comment> getCommentsBypostId(Long postId) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new RuntimeException("post not found with id" + postId));

		return post.getComments();
	}

	@Override
	public void deleteComment(Long commentId) {
		// TODO Auto-generated method stub
		commRepository.deleteById(commentId);
	}

}
