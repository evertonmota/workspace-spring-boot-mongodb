package br.com.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workshopmongo.domain.User;
import br.com.workshopmongo.dto.UserDTO;
import br.com.workshopmongo.exceptions.ObjectNotFoundException;
import br.com.workshopmongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User user) {
		return userRepository.insert(user);
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(User user) {
		User newObj = userRepository.findById(user.getId()).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		updateUser(newObj, user);
		return userRepository.save(newObj);
	}
	/*forma tradicional (mais explícita) */
	/*public User findById(String id) {
	    Optional<User> user = userRepository.findById(id);
	    if (user.isEmpty()) {
	        throw new ObjectNotFoundException("Objeto não encontrado");
	    }
	    return user.get();
	}*/

	private void updateUser(User u, User user) {
		u.setName(user.getName());
		u.setEmail(user.getEmail());
	}

}
