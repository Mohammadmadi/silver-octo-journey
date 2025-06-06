package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping
	public ResponseEntity<CommentDto> addComment(@PathVariable Long postId, @Valid @RequestBody CommentDto commentDto) {

		// Convert DTO to Entity
		Comment comment = new Comment();
		comment.setContent(commentDto.getContent());

		// Add and convert back to DTO
		Comment saved = commentService.addCommentToPost(postId, comment);

		CommentDto responseDto = new CommentDto();
		responseDto.setId(saved.getId());
		responseDto.setContent(saved.getContent());

		return ResponseEntity.ok(responseDto);
	}

	@GetMapping
	public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable Long postId) {
		List<Comment> comments = commentService.getCommentsBypostId(postId);

		List<CommentDto> commentDtos = comments.stream().map(comment -> {
			CommentDto dto = new CommentDto();
			dto.setId(comment.getId());
			dto.setContent(comment.getContent());
			return dto;
		}).collect(Collectors.toList());

		return ResponseEntity.ok(commentDtos);
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
		commentService.deleteComment(commentId);
		return ResponseEntity.noContent().build();
	}
}
