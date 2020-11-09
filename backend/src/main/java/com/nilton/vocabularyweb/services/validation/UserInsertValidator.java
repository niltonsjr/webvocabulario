package com.nilton.vocabularyweb.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nilton.vocabularyweb.dto.UserInsertDTO;
import com.nilton.vocabularyweb.entities.User;
import com.nilton.vocabularyweb.repositories.UserRepository;
import com.nilton.vocabularyweb.resources.exceptions.FieldMessage;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

	@Autowired
	private UserRepository repository;

	@Override
	public void initialize(UserInsertValid constraintAnnotation) {
	}

	@Override
	public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		User user = repository.findByUserName(dto.getUserName());
		
		if (user != null) {
			list.add(new FieldMessage("userName", "El nombre de usuario ya existe en la base de datos."));			
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
			.addConstraintViolation();
		}
		
		return list.isEmpty();
	}

}
