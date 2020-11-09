package com.nilton.vocabularyweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nilton.vocabularyweb.entities.Vocabulary;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, Long>{

}