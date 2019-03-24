package com.hugoiguana.br.geanuncio1.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.hugoiguana.br.geanuncio1.models.ECity;
import com.hugoiguana.br.geanuncio1.models.User;
import com.hugoiguana.br.geanuncio1.repository.UserRepository;

@Service
public class UserService extends BaseEntityService<User, Integer> {

	private final UserRepository userRepository;
	
	public static final Sort ORDER_BY_FULLNAME_ASC = Sort.by("fullName").ascending();

	public UserService(@Autowired UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;
	}

	public Optional<User> findFirstOrderByIdAsc() {
		return userRepository.findFirstByOrderByIdAsc();
	}
	
	public Optional<User> getOneUser() {

		Optional<User> user = getById(1);

		if (!user.isPresent()) {
			user = findFirstOrderByIdAsc();
		}

		return user;
	}
	
	public List<User> findFirst10ByFullNameContainingIgnoreCase(String fullName){
		return userRepository.findFirst10ByFullNameContainingIgnoreCase(fullName, Sort.by("fullName").ascending());
	}
	
	public List<User> findByIdIn(Integer... ids) {
		return userRepository.findByIdIn(Arrays.asList(ids));
	}
	
	public List<User> findByIdNotIn(Integer... ids) {
		return userRepository.findByIdNotIn(Arrays.asList(ids));
	}
	
	public List<User> findByIdNot(Integer id) {
		return userRepository.findByIdNot(id);
	}
	
	public List<User> findByPhoneNumberIsNull() {
		return userRepository.findByPhoneNumberIsNull();
	}
	
	public List<User> findByPhoneNumberIsNotNull() {
		return userRepository.findByPhoneNumberIsNotNull();
	}
	
	public List<User> findByDtAgeBetween(LocalDate firstDate, LocalDate lastDate) {
		return userRepository.findByDtAgeBetween(firstDate, lastDate);
	}
	
	public List<User> findByDtAgeLessThan(LocalDate date) {
		return userRepository.findByDtAgeLessThan(date);
	}
	
	public List<User> findByDtAgeLessThanEqual(LocalDate date) {
		return userRepository.findByDtAgeLessThanEqual(date);
	}
	
	public List<User> findByDtAgeGreaterThan(LocalDate date) {
		return userRepository.findByDtAgeGreaterThan(date);
	}
	
	public List<User> findByDtAgeGreaterThanEqual(LocalDate date) {
		return userRepository.findByDtAgeGreaterThanEqual(date);
	}
	
	public List<User> findByDtAgeBefore(LocalDate date) {
		return userRepository.findByDtAgeBefore(date);
	}
	
	public List<User> findByDtAgeAfter(LocalDate date) {
		return userRepository.findByDtAgeAfter(date);
	}
	
	public List<User> findByDtAgeAndFullNameContainingIgnoreCase(LocalDate dtAge, String fullName) {
		return userRepository.findByDtAgeAndFullNameContainingIgnoreCase(dtAge, fullName);
	}

	public List<User> findByDtAgeAndFullNameContainingIgnoreCase(LocalDate dtAge, String fullName, Sort sort) {
		return userRepository.findByDtAgeAndFullNameContainingIgnoreCase(dtAge, fullName, sort);
	}
	
	public List<User> findByDtAgeAndFullNameContainingIgnoreCaseByOrderByFullNameAsc(LocalDate dtAge, String fullName) {
		return userRepository.findByDtAgeAndFullNameContainingIgnoreCaseOrderByFullNameAsc(dtAge, fullName);
	}
	
	public List<User> findByFullNameContainingOrNickNameContaining(String fullName, String nickName, Sort sort) {
		return userRepository.findByFullNameContainingOrNickNameContaining(fullName, nickName, sort);
	}
	
	public List<User> findByFullNameStartingWithIgnoreCase(String fullName) {
		return userRepository.findByFullNameStartingWithIgnoreCase(fullName, ORDER_BY_FULLNAME_ASC);
	}
	
	public List<User> findByFullNameEndingWithIgnoreCase(String fullName) {
		return userRepository.findByFullNameEndingWithIgnoreCase(fullName, ORDER_BY_FULLNAME_ASC);
	}
	
	public List<User> findByAddressCity(ECity addressCity, Sort sort) {
		return userRepository.findByAddressCity(addressCity, sort);
	}
	
	public List<User> findByAddressCityIn(ECity... addressCity) {
		return userRepository.findByAddressCityIn(Arrays.asList(addressCity), ORDER_BY_FULLNAME_ASC);
	}
	
	public List<User> findByFullNameContainingIgnoreCase(String fullName) {
		return userRepository.findByFullNameContaining(fullName.toUpperCase());
	}
	
	public List<User> findByFullNameContainingIgnoreCase2(String fullName) {
		return userRepository.findByFullNameContaining2(fullName.toUpperCase());
	}
	
}
