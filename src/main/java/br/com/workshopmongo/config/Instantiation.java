package br.com.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.workshopmongo.domain.User;
import br.com.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		
		User user1 = new User(null, "Maria da Conceição", "mariadaconceicao@gmail.com");
		User user2 = new User(null, "sophia cerino mota", "sophiacerinomota@gmail.com");
		User user3 = new User(null, "Everton Mota", "mcostagt@gmail.com");
		User user4 = new User(null, "Karin Lima", "karinlima.lima@gmail.com");
		
		userRepository.saveAll(Arrays.asList(user1,user2,user3,user4));
	}

}
