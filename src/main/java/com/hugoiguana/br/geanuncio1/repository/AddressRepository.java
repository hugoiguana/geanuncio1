package com.hugoiguana.br.geanuncio1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugoiguana.br.geanuncio1.models.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	Optional<Address> findFirstByOrderByIdAsc();
	
	Optional<Address> findByUserId(Integer id);
	
}
