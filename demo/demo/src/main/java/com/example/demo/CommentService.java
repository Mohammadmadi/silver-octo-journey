package com.example.demo;

import java.util.List;

public interface CommentService {

	Comment addCommentToPost(Long postId,Comment comment);
	List<Comment> getCommentsBypostId(Long postId);
	void deleteComment(Long commentId);
	
}
