package com.nilton.vocabularyweb.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nilton.vocabularyweb.dto.RoleDTO;
import com.nilton.vocabularyweb.dto.UserDTO;
import com.nilton.vocabularyweb.dto.UserInsertDTO;
import com.nilton.vocabularyweb.dto.UserUpdateDTO;
import com.nilton.vocabularyweb.entities.Role;
import com.nilton.vocabularyweb.entities.User;
import com.nilton.vocabularyweb.repositories.RoleRepository;
import com.nilton.vocabularyweb.repositories.UserRepository;
import com.nilton.vocabularyweb.services.exceptions.DatabaseException;
import com.nilton.vocabularyweb.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPaged(PageRequest pageRequest) {
		Page<User> list = repository.findAll(pageRequest);
		return list.map(x -> new UserDTO(x));
	}

	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("No se la localizado la entidad"));
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO insert(UserInsertDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		entity = repository.save(entity);
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO update(Long id, UserUpdateDTO dto) {
		try {
			User entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UserDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id no encontrada: " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id no encontrado: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("No se pudo eliminar la entidad por una violación de integridad.");
		}
	}

	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setUserName(dto.getUserName());
		entity.getRoles().clear();
		for (RoleDTO roleDTO : dto.getRoles()) {
			Role role = roleRepository.getOne(roleDTO.getId());
			entity.getRoles().add(role);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Nombre de usuario no encontrado: " + username);
		}
		return user;
	}

}
