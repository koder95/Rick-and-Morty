package pl.koder95.rickandmortyfx;

import java.net.http.HttpClient;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.koder95.rickandmortyfx.api.Resources;
import pl.koder95.rickandmortyfx.api.impl.RestResourceFactory;
import tools.jackson.databind.ObjectMapper;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        Application.launch(App.class, args);
    }

    @Bean
    public Resources createResources() {
        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        return new Resources(new RestResourceFactory(httpClient, objectMapper));
    }
}
