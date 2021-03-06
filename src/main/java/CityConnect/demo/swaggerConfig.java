package CityConnect.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Documentation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
@Configuration
@EnableSwagger2
public class swaggerConfig {


	@Bean
	public Docket productApi()
	{
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().paths(regex("/connected.*")).build();
	}
	
	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder().title("TEST").description("TEst").license("TEST").build();
	}
}
