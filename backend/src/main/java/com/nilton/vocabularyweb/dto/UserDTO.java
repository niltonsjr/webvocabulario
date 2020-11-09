package com.nilton.vocabularyweb.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.nilton.vocabularyweb.entities.User;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Campo obligatorio")
	@Size(min = 5, max = 15, message = "El nombre de usuario debe tener entre 5 y 15 caracteres")
	private String userName;

	Set<RoleDTO> roles = new HashSet<>();

	public UserDTO() {

	}

	public UserDTO(Long id,
			@NotBlank(message = "Campo obligatorio") @Size(min = 5, max = 15, message = "El nombre de usuario debe tener entre 5 y 15 caracteres") String userName,
			Set<RoleDTO> roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.roles = roles;
	}

	public UserDTO(User entity) {
		this.id = entity.getId();
		this.userName = entity.getUserName();
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

}
