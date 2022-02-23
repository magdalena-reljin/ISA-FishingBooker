package rs.ac.uns.ftn.isa.fisherman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FIsherManApplication {

	public static void main(String[] args) {
		SpringApplication.run(FIsherManApplication.class, args);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+     System.getProperty("user.dir"));
	}

}
