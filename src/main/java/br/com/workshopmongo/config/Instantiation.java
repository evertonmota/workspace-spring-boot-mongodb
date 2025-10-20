package br.com.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.workshopmongo.domain.Post;
import br.com.workshopmongo.domain.User;
import br.com.workshopmongo.dto.AuthorDTO;
import br.com.workshopmongo.repository.PostRepository;
import br.com.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User user1 = new User(null, "Maria da Conceição", "mariadaconceicao@gmail.com");
		User user2 = new User(null, "sophia cerino mota", "sophiacerinomota@gmail.com");
		User user3 = new User(null, "Everton Mota", "mcostagt@gmail.com");
		User user4 = new User(null, "Karin Lima", "karinlima.lima@gmail.com");

		userRepository.saveAll(Arrays.asList(user1,user2,user3,user4));
		
		Post post1 = new Post(null, sdf.parse("20/10/2025"), "Alô Brasil", "Estou na França", new AuthorDTO(user1));
		Post post2 = new Post(null, sdf.parse("20/10/2025"), "Alô Brasil", "Estou na França", new AuthorDTO(user1));

		postRepository.saveAll(Arrays.asList(post1,post2));
		
		user1.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(user1);
		
	}

}
