package dio.web.api.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

import static org.springframework.web.servlet.mvc.method.RequestMappingInfo.paths;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private Contact contato(){
        return new Contact(
                "Seu nome",
                "http://www.seusite.com.br",
                "voce@seusite.com.br"
        );
    }

    private ApiInfoBuilder informacoesApi(){
        ApiInfoBuilder apiInfoBuider = new ApiInfoBuilder();

        apiInfoBuider.title("Title - Rest API");
        apiInfoBuider.description("API exemplo de uso de Springboot API REST");
        apiInfoBuider.version("1.0");
        apiInfoBuider.termsOfServiceUrl("Termo de uso: Open Source");
        apiInfoBuider.license("Licença - Sua Empresa");
        apiInfoBuider.licenseUrl("http://www.seusite.com.br");
        apiInfoBuider.contact(this.contato());

        return apiInfoBuider;
    }

    @Bean
    public Docket detalheApi(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket
                .select()
                .apis(RequestHandlerSelectors.basePackage("dio.web.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.informacoesApi().build())
                .consumes(new HashSet<String>(Arrays.asList("aplicattion/json")))
                .produces(new HashSet<String>(Arrays.asList("aplicattion/json")));

        return docket;
    }

}
