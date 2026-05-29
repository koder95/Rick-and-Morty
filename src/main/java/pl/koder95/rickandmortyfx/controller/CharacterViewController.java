package pl.koder95.rickandmortyfx.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.koder95.rickandmortyfx.dto.CharacterViewDto;
import pl.koder95.rickandmortyfx.dto.EpisodeLinkDto;
import pl.koder95.rickandmortyfx.service.ImportService;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class CharacterViewController {

    private static final Image NULL_AVATAR = new Image("unknown-avatar.png");
    private static final CharacterViewDto PLACEHOLDER = new CharacterViewDto(Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.of(NULL_AVATAR),
            Optional.empty(),
            Optional.empty()
    );
    @FXML
    private ImageView avatar;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label status;
    @FXML
    private Label species;
    @FXML
    private Label type;
    @FXML
    private Label gender;
    @FXML
    private Label created;
    @FXML
    private ListView<EpisodeLinkDto> episodes;
    @FXML
    private Label origin;
    @FXML
    private Label otherLocation;
    @FXML
    private Button prevIdButton;
    @FXML
    private Button nextIdButton;

    private final ObjectProperty<CharacterViewDto> showingCharacter = new SimpleObjectProperty<>();
    private ImportService importService;

    public void initialize() {
        System.out.println("Character view initialized");
        showingCharacter.subscribe(dto -> {
            if (dto == null) {
                dto = PLACEHOLDER;
            }
            id.setText(dto.id().map(Object::toString).orElse("unknown"));
            name.setText(dto.name().orElse("unknown"));
            status.setText(dto.status().orElse("unknown"));
            species.setText(dto.species().orElse("unknown"));
            type.setText(dto.type().orElse("unknown"));
            gender.setText(dto.gender().orElse("unknown"));
            created.setText(dto.created().orElse("unknown"));
            episodes.setItems(FXCollections.observableList(dto.episodes().orElse(List.of())));
            origin.setText(dto.origin().map(Object::toString).orElse("unknown"));
            otherLocation.setText(dto.location().map(Object::toString).orElse("unknown"));
            avatar.setImage(dto.avatar().orElse(NULL_AVATAR));
            setAbilitiesForButtons();
        });
    }

    private void setAbilitiesForButtons() {
        boolean hasCharacter = showingCharacter.get() != null && showingCharacter.get().id().isPresent();
        boolean hasPrevious = hasCharacter && showingCharacter.get().id().get() > 1;
        boolean hasNext = hasCharacter && showingCharacter.get().id().get() < getCharactersCount();
        prevIdButton.setDisable(!hasPrevious);
        nextIdButton.setDisable(!hasNext);
    }

    public void showCharacter(CharacterViewDto dto) {
        showingCharacter.setValue(dto);
    }

    public void showId(Long id) {
        if (id == null) {
            showCharacter(null);
            return;
        }
        try {
            Optional<CharacterViewDto> characterViewDto = importService.importCharacter(id);
            characterViewDto.ifPresent(this::showCharacter);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load character");
            alert.setContentText("Unable to load character with id " + id + ". Please try again later.");
            alert.showAndWait();
        }
    }

    @FXML
    private void prevId(ActionEvent event) {
        showingCharacter.get().id().ifPresent(id -> {
            if (id > 1) {
                showId(id - 1);
            }
        });
    }

    @FXML
    private void nextId(ActionEvent event) {
        showingCharacter.get().id().ifPresent(id -> {
            if (id < getCharactersCount()) {
                showId(id + 1);
            }
        });
    }

    private long getCharactersCount() {
        try {
            return importService.getCharactersCount();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load characters count");
            alert.setContentText("Unable to determine characters count. Defaulting to 0.");
            alert.showAndWait();
            return 0;
        }
    }

    @FXML
    private void drawNextId(ActionEvent event) {
        drawCharacter();
    }

    public void setImportService(ImportService importService) {
        this.importService = importService;
        if (importService == null) {
            showCharacter(null);
        } else {
            drawCharacter();
        }
    }

    private void drawCharacter() {
        Random random = new Random();
        long draw = random.nextLong(1, getCharactersCount() + 1);
        showId(draw);
    }
}
