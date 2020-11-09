package com.nilton.vocabularyweb.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nilton.vocabularyweb.dto.VocabularyDTO;
import com.nilton.vocabularyweb.entities.Vocabulary;
import com.nilton.vocabularyweb.repositories.VocabularyRepository;
import com.nilton.vocabularyweb.services.exceptions.DatabaseException;
import com.nilton.vocabularyweb.services.exceptions.ResourceNotFoundException;

@Service
public class VocabularyService {

	@Autowired
	private VocabularyRepository repository;
	
	@Transactional(readOnly = true)
	public Page<VocabularyDTO> findAllPaged(PageRequest pageRequest) {
		Page<Vocabulary> list = repository.findAll(pageRequest);
		return list.map(x -> new VocabularyDTO(x));
	}
	
	@Transactional(readOnly = true)
	public VocabularyDTO findById(Long id) {
		Optional<Vocabulary> obj = repository.findById(id);
		Vocabulary entity = obj.orElseThrow(() -> new ResourceNotFoundException("No se ha localizado la entidad"));
		return new VocabularyDTO(entity);
	}
	
	@Transactional
	public VocabularyDTO insert (VocabularyDTO dto) {
		Vocabulary entity = new Vocabulary();
		entity.setWord(dto.getWord());
		entity.setMeaning(dto.getMeaning());
		entity = repository.save(entity);
		return new VocabularyDTO(entity);
	}
	
	public VocabularyDTO update(Long id, VocabularyDTO dto) {
		try {
			Vocabulary entity = repository.getOne(id);
			entity.setWord(dto.getWord());
			entity.setMeaning(dto.getMeaning());
			entity = repository.save(entity);
			return new VocabularyDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id no encontrado: " + id);
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
}
