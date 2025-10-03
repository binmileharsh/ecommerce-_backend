package com.example.storeelectronic.demo;

import com.example.storeelectronic.demo.Repository.Rolerepo;
import com.example.storeelectronic.demo.Repository.UserRepository;
import com.example.storeelectronic.demo.entities.Role;
import com.example.storeelectronic.demo.entities.User;
import com.example.storeelectronic.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);



	}
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private Rolerepo rolerepo;

@Autowired
private UserRepository userrepo;
	@Override
	public void run(String... args) throws Exception {
		Role rolenormal=this.rolerepo.findByName("ROLE_NORMAL").orElse(null);
		if(rolenormal==null){
		Role role2=new Role();
		role2.setRoleId(UUID.randomUUID().toString());
		role2.setName("ROLE_NORMAL");
		this.rolerepo.save(role2);}

		Role roleAdmin = this.rolerepo.findByName("ROLE_ADMIN")
				.orElseGet(() -> {
					Role r = new Role();
					r.setRoleId(UUID.randomUUID().toString());
					r.setName("ROLE_ADMIN");
					return this.rolerepo.save(r);
				});

		User users =this.userrepo.findByEmail("harsh108@gmail.com").orElse(null);
	if(users==null){
		User user =new User();
		user.setName("harsh");
		user.setUserId(UUID.randomUUID().toString());
		user.setGender("Male");
		user.setPassword(passwordEncoder.encode("Hello@1234"));
		user.setEmail("harsh108@gmail.com");
		user.getRoles().add(roleAdmin);
		user = this.userrepo.save(user);
	}


	}


}

