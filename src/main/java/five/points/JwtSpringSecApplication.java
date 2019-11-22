package five.points;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import five.points.dao.TaskRepository;
import five.points.entities.AppRole;
import five.points.entities.AppUser;
import five.points.entities.Tasks;
import five.points.service.AccountService;

@SpringBootApplication
public class JwtSpringSecApplication implements CommandLineRunner {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringSecApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder getBCrypt() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {

		accountService.saveUser(new AppUser(null, "admin", "1234", null));
		accountService.saveUser(new AppUser(null, "user", "4321", null));

		accountService.saveRole(new AppRole(null, "ADMIN"));
		accountService.saveRole(new AppRole(null, "USER"));
		
		accountService.addRoleToUser("admin", "ADMIN");
		accountService.addRoleToUser("user", "USER");
		
		Stream.of("T1", "T2", "T3", "T5", "T4","TH").forEach(t -> {
			taskRepository.save(new Tasks(null, t));
		});
		taskRepository.findAll().forEach(t -> {
			System.out.println(t.getTaskName());
		});
	}

}
