package com.nilton.vocabularyweb.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.nilton.vocabularyweb.entities.Vocabulary;

public class VocabularyDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Campo requerido")
	private String word;

	@NotBlank(message = "Campo requerido")
	private String meaning;

	public VocabularyDTO() {

	}

	public VocabularyDTO(Long id, @NotBlank(message = "Campo requerido") String word,
			@NotBlank(message = "Campo requerido") String meaning) {
		super();
		this.id = id;
		this.word = word;
		this.meaning = meaning;
	}

	public VocabularyDTO(Vocabulary entity) {
		this.id = entity.getId();
		this.word = entity.getWord();
		this.meaning = entity.getMeaning();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

}
