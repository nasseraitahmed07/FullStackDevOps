package Nasser.AITAHMED.gestionStocke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class GestionStockeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionStockeApplication.class, args);
	}

}
