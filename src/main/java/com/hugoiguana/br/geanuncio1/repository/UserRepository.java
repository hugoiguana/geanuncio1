package com.hugoiguana.br.geanuncio1.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hugoiguana.br.geanuncio1.models.ECity;
import com.hugoiguana.br.geanuncio1.models.User;

//Referencia: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

public interface UserRepository extends JpaRepository<User, Integer> {
	
	//Query creation from method names
	
	Optional<User> findFirstByOrderByIdAsc();
	
	List<User> findFirst10ByFullNameContainingIgnoreCase(String fullName, Sort sort);
	
	Optional<User> findTopByOrderByIdAsc();

	Optional<User> findTopByOrderByDtAgeDesc();

	Page<User> queryFirst10ByFullName(String lastname, Pageable pageable);

	Slice<User> findTop3ByFullName(String lastname, Pageable pageable);

	List<User> findFirst10ByFullName(String lastname, Sort sort);

	List<User> findTop10ByFullName(String lastname, Pageable pageable);

	List<User> findByIdIn(Collection<Integer> ids);
	
	List<User> findByIdNotIn(Collection<Integer> ids);

	List<User> findByIdNot(Integer id);
	
	List<User> findByPhoneNumberIsNull();
	
	List<User> findByPhoneNumberIsNotNull();
	
	
	//Dates
	List<User> findByDtAgeBetween(LocalDate firstDate, LocalDate lastDate);
	
	List<User> findByDtAgeLessThan(LocalDate date);
	
	List<User> findByDtAgeLessThanEqual(LocalDate date);
	
	List<User> findByDtAgeGreaterThan(LocalDate date);
	
	List<User> findByDtAgeGreaterThanEqual(LocalDate date);
	
	List<User> findByDtAgeBefore(LocalDate date);
	
	List<User> findByDtAgeAfter(LocalDate date);
	
	
	List<User> findByDtAgeAndFullNameContainingIgnoreCase(LocalDate dtAge, String fullName);
	
	List<User> findByDtAgeAndFullNameContainingIgnoreCase(LocalDate dtAge, String fullName, Sort sort);
	
	List<User> findByDtAgeAndFullNameContainingIgnoreCaseOrderByFullNameAsc(LocalDate dtAge, String fullName);
	
	List<User> findByFullNameContainingOrNickNameContaining(String fullName, String nickName, Sort sort);
	
	List<User> findByFullNameStartingWithIgnoreCase(String fullName, Sort sort);
	
	List<User> findByFullNameEndingWithIgnoreCase(String fullName, Sort sort);

	
	List<User> findByAddressCity(ECity addressCity, Sort sort);
	
	List<User> findByAddressCityIn(Collection<ECity> addressCity, Sort sort);
	
	//@Query("select u from User u where upper(u.fullName) like upper('%?1%')")
	@Query("select u from User u where upper(u.fullName) like %?1%")
	List<User> findByFullNameContaining(String fullName);
	
	@Query("select u from User u where upper(u.fullName) like %:fullName%")
	List<User> findByFullNameContaining2(@Param("fullName") String fullName);
	
}

