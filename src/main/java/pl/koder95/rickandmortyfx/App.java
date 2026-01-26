package pl.koder95.rickandmortyfx;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;
import pl.koder95.rickandmortyfx.controller.CharacterViewController;
import pl.koder95.rickandmortyfx.dto.CharacterViewDto;
import pl.koder95.rickandmortyfx.service.ImportService;

/**
 * JavaFX App
 */
@Component
public class App extends Application {
    private ConfigurableApplicationContext applicationContext;
    private ImportService importService;

    @Override
    public void init() throws Exception {
        super.init();
        applicationContext = new SpringApplicationBuilder(Main.class).run();
        importService = applicationContext.getBean(ImportService.class);
    }

    @Override
    public void start(Stage stage) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("character.fxml"));
            root = loader.load();
            CharacterViewController characterViewController = loader.getController();
            Random random = new Random();
            Optional<CharacterViewDto> characterViewDto = importService.importCharacter(random.nextLong(1, 826));
            characterViewDto.ifPresent(characterViewController::showCharacter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        stage.setTitle(environment.getProperty("javafx.application.title"));
        stage.show();
    }

    @Override
    public void stop() {
        applicationContext.stop();
    }
}
