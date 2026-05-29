package pl.koder95.rickandmortyfx;

import java.io.IOException;
import java.net.http.HttpClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import pl.koder95.rickandmortyfx.api.ResourceFactory;
import pl.koder95.rickandmortyfx.api.Resources;
import pl.koder95.rickandmortyfx.api.impl.RestResourceFactory;
import pl.koder95.rickandmortyfx.controller.CharacterViewController;
import pl.koder95.rickandmortyfx.mapper.ImportMapper;
import pl.koder95.rickandmortyfx.mapper.impl.ImportMapperImpl;
import pl.koder95.rickandmortyfx.service.ImportService;
import pl.koder95.rickandmortyfx.service.impl.ImportServiceImpl;
import tools.jackson.databind.ObjectMapper;


/**
 * JavaFX App
 */
public class App extends Application {

    private ImportService importService;

    @Override
    public void init() throws Exception {
        super.init();
        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        ResourceFactory resourceFactory = new RestResourceFactory(httpClient, objectMapper);
        Resources resources = new Resources(resourceFactory);
        ImportMapper importMapper = new ImportMapperImpl(resources);
        importService = new ImportServiceImpl(resources, importMapper);
    }

    @Override
    public void start(Stage stage) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("character.fxml"));
            root = loader.load();
            CharacterViewController characterViewController = loader.getController();
            characterViewController.setImportService(importService);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load UI");
            alert.setContentText("Unable to load the user interface. Please try again later.");
            alert.showAndWait();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Rick and Morty");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
