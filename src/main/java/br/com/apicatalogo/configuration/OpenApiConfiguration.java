package br.com.apicatalogo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/swagger-ui/index.html");
	}

	@Bean
	public OpenAPI customOpenApi (@Value("api-catalogo") String descricao , @Value("0.0.1") String version ) {
		return new OpenAPI()
				.info(new Info()
						.title("Api de Catálogo de produtos")
						.version(version)
						.description(descricao)
						.contact(new Contact().name("João Carlos Belmiro Gonçalves").email("joao_belmiro@hotmail.com").url("https://github.com/joao-belmiro"))
				        .termsOfService("http://swagger.io/terms/")
				        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
