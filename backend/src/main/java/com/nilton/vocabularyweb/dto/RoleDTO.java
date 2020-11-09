package com.nilton.vocabularyweb.dto;

import java.io.Serializable;

import com.nilton.vocabularyweb.entities.Role;

public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	public RoleDTO() {

	}

	public RoleDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public RoleDTO(Role entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
