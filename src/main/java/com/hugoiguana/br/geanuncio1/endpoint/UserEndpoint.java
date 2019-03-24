package com.hugoiguana.br.geanuncio1.endpoint;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hugoiguana.br.geanuncio1.models.Address;
import com.hugoiguana.br.geanuncio1.models.ECity;
import com.hugoiguana.br.geanuncio1.models.ECountry;
import com.hugoiguana.br.geanuncio1.models.EGender;
import com.hugoiguana.br.geanuncio1.models.EState;
import com.hugoiguana.br.geanuncio1.models.Product;
import com.hugoiguana.br.geanuncio1.models.User;
import com.hugoiguana.br.geanuncio1.service.AddressService;
import com.hugoiguana.br.geanuncio1.service.UserService;

@RestController
@RequestMapping("user")
public class UserEndpoint {

	@Autowired
	UserService userService;

	@Autowired
	AddressService addressService;

	@RequestMapping(path = "add_test", method = RequestMethod.GET)
	public String testAddUser() {

		User user = User.builder().fullName("André Luiz da Mota").gender(EGender.MALE).nickName("André")
				.email("andre.mota@gmail.com").dtAge(LocalDate.of(1987, 12, 13))
				.address(Address.builder().country(ECountry.BRAZIL).state(EState.PERNAMBUCO).city(ECity.CAMARAGIBE)
						.street("Rua francisco Silveira de Andrade").Number("13").zip_code("54753150").build())
				.build();

		userService.save(user);

		return "Usuário adcionado : " + user.toString();
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(path = "get_test", method = RequestMethod.GET)
	public String testGetUser() {

		Optional<User> user = userService.getOneUser();

		if (!user.isPresent()) {
			return "Nenhum Usuário encontrado";
		}

		Optional<Address> address1 = addressService.findFirstByOrderByIdAsc();
		Optional<Address> address2 = addressService.findByUserId(user.get().getId());
		System.out.println(address2.get().getUser().toString());

		List<User> users1 = userService.findFirst10ByFullNameContainingIgnoreCase("hugo");
		List<User> users2 = userService.findByIdIn(1, 2);
		List<User> users3 = userService.findByIdNotIn(1, 3);
		List<User> users4 = userService.findByIdNot(1);
		List<User> users5 = userService.findByPhoneNumberIsNull();
		List<User> users6 = userService.findByPhoneNumberIsNotNull();

		List<User> users7 = userService.findByDtAgeBetween(LocalDate.of(1990, 01, 01), LocalDate.of(2000, 12, 31));
		List<User> users8 = userService.findByDtAgeLessThan(LocalDate.of(1989, 04, 20));
		List<User> users9 = userService.findByDtAgeLessThanEqual(LocalDate.of(1989, 04, 20));
		List<User> users10 = userService.findByDtAgeGreaterThan(LocalDate.of(1989, 04, 20));
		List<User> users11 = userService.findByDtAgeGreaterThanEqual(LocalDate.of(1989, 04, 20));
		List<User> users12 = userService.findByDtAgeBefore(LocalDate.of(1989, 04, 20));
		List<User> users13 = userService.findByDtAgeAfter(LocalDate.of(1989, 04, 20));

		List<User> users14 = userService.findByDtAgeAndFullNameContainingIgnoreCase(LocalDate.of(1989, 04, 20), "hugo");
		List<User> users15 = userService.findByDtAgeAndFullNameContainingIgnoreCase(LocalDate.of(1989, 04, 20), "hugo",
				Sort.by("fullName").ascending());
		List<User> users16 = userService
				.findByDtAgeAndFullNameContainingIgnoreCaseByOrderByFullNameAsc(LocalDate.of(1989, 04, 20), "hugo");

		List<User> users17 = userService.findByFullNameContainingOrNickNameContaining("hugo", "hugo",
				UserService.ORDER_BY_FULLNAME_ASC);

		List<User> users18 = userService.findByFullNameStartingWithIgnoreCase("hugo");
		List<User> users19 = userService.findByFullNameEndingWithIgnoreCase("mota");

		List<User> users20 = userService.findByAddressCity(ECity.CAMARAGIBE, UserService.ORDER_BY_FULLNAME_ASC);
		List<User> users21 = userService.findByAddressCityIn(ECity.CAMARAGIBE, ECity.OLINDA);
		
		List<User> users22 = userService.findByFullNameContainingIgnoreCase("hugo");
		List<User> users23 = userService.findByFullNameContainingIgnoreCase2("hugo");
		
		return user.toString();
	}

	@RequestMapping(path = "remove_test", method = RequestMethod.GET)
	public String testRemoveUser() {

		Optional<User> user = userService.getOneUser();

		if (user.isPresent()) {
			userService.delete(user.get());
		} else {
			return "Nenhum Usuário encontrado";
		}

		return "Usuário deletado com sucesso - " + user.get();
	}

}


