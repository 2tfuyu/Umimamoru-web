package main;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("umimamoru-server")
                .select()
                .paths(paths())
                .build()
                .apiInfo(apiInfo());
    }

    private Predicate<String> paths() {
        return Predicates.or(Predicates.containsPattern("/umimamoru"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Umimamoru"
                , "離岸流情報の送受信"
                , "1.0.0"
                , ""
                , new Contact(
                "OKNCT-ICT"
                , ""
                , "")
                , ""
                , ""
                , new ArrayList<>()
        );
    }
}