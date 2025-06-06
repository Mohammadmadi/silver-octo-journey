package com.example.demo;

import jakarta.validation.constraints.NotBlank;

public class PostDto {

	private Long id;

	@NotBlank(message = "Title is required")
	private String title;

	@NotBlank(message = "Content is required")
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	// Getters and setters

}
