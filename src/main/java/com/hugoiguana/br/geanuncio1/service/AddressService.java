package com.hugoiguana.br.geanuncio1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugoiguana.br.geanuncio1.models.Address;
import com.hugoiguana.br.geanuncio1.repository.AddressRepository;

@Service
public class AddressService extends BaseEntityService<Address, Integer> {

	AddressRepository addressRepository;
	
	public AddressService(@Autowired AddressRepository addressRepository) {
		super(addressRepository);
		this.addressRepository = addressRepository;
	}
	
	public Optional<Address> findFirstByOrderByIdAsc() {
		return addressRepository.findFirstByOrderByIdAsc();
	}
	
	public Optional<Address> findByUserId(Integer userId) {
		return addressRepository.findByUserId(userId);
	}
	
}
