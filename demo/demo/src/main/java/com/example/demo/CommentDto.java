package com.example.demo;

import jakarta.validation.constraints.NotBlank;

public class CommentDto {

	private Long id;

	@NotBlank(message = "Content is required")
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	// Getters and setters

}
