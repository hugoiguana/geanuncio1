package com.hugoiguana.br.geanuncio1.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugoiguana.br.geanuncio1.models.MyEntity;

public abstract class BaseEntityService<T extends MyEntity, ID> {

	JpaRepository<T, ID> baseRepository;
	
	public BaseEntityService (JpaRepository<T, ID> baseRepository) {
		this.baseRepository = baseRepository;
	}
	
	public void save(T t) {
		baseRepository.save(t);
	};
	
	public void delete(T t) {
		baseRepository.delete(t);
	}
	
	public void deleteById(ID id) {
		baseRepository.deleteById(id);
	};
	
	public Optional<T> getById(ID id) {
		return baseRepository.findById(id);
	};
}
