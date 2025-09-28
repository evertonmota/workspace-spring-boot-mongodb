package br.com.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping(value = "/users/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User user = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@PostMapping("/users")
	public ResponseEntity<Void> insert(@RequestBody UserDTO dto){
		User user = userService.fromDTO(dto);
		user = userService.insert(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build(); // retorna o codigo 201 Created
	}
	
	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		userService.delete(id);
		return ResponseEntity.noContent().build();//quando se faz uma operação no precisa retornar nada,vai ser uma resposta 204 NoContent.
	}

	@PutMapping(value="/users/{id}")
	public ResponseEntity<User> update(@RequestBody UserDTO dto, @PathVariable String id){
		User user = userService.fromDTO(dto);
		user.setId(id);
		user = userService.update(user);
		return ResponseEntity.noContent().build();
	}
	
}
