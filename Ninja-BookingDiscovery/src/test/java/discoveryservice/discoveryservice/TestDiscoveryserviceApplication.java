package discoveryservice.discoveryservice;

import org.springframework.boot.SpringApplication;

public class TestDiscoveryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.from(DiscoveryserviceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
