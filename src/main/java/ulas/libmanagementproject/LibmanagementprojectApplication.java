package ulas.libmanagementproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2()
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LibmanagementprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibmanagementprojectApplication.class, args);



	}
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("ulas.libmanagementproject"))
				.build()
				.apiInfo(apiInfo());
	}
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Library Managament System",
				"This demo app created for self test on spring application .",
				"API TOS",
				"Terms of service",
				new Contact("Ulaş Müezzinoğlu", "https://instagram.com/he.justulas", "martulas5252@gmail.com"),
				"License of API", "API license URL", Collections.emptyList());
	}

}
