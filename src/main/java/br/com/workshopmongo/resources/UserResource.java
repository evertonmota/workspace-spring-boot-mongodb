package br.com.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.workshopmongo.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		User maria = new User("1", "Maria da Conceição","mariadaconceicao@gmail.com");
		User sophia= new User("2", "Sophia Mota","sophiacerinomota@gmail.com");
		
		List listaDeUsuarios = new ArrayList<>();
		listaDeUsuarios.addAll(Arrays.asList(maria, sophia));
		return ResponseEntity.ok().body(listaDeUsuarios);
	}
}
