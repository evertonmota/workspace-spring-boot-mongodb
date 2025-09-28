package br.com.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.workshopmongo.domain.User;
import br.com.workshopmongo.dto.UserDTO;
import br.com.workshopmongo.services.UserService;

@RestController
public class UserResource {
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "/users")
	public ResponseEntity<List<UserDTO>> findAll(){

		List<User> users = userService.findAll();
		List<UserDTO> dtos = users.stream().map( x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}
}
