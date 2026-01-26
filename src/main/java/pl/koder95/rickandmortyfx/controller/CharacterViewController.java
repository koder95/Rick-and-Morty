package pl.koder95.rickandmortyfx.controller;

import java.util.List;
import java.util.Optional;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Controller;
import pl.koder95.rickandmortyfx.dto.CharacterViewDto;
import pl.koder95.rickandmortyfx.dto.EpisodeLinkDto;

@Controller
public class CharacterViewController {

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

    private final ObjectProperty<CharacterViewDto> showingCharacter = new SimpleObjectProperty<>();

    public void initialize() {
        Image nullAvatar = new Image("unknown-avatar.png");
        CharacterViewDto placeholder = new CharacterViewDto(Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of(nullAvatar),
                Optional.empty(),
                Optional.empty()
        );
        showingCharacter.subscribe(dto -> {
            if (dto == null) {
                dto = placeholder;
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
            avatar.setImage(dto.avatar().orElse(nullAvatar));
        });
    }

    public void showCharacter(CharacterViewDto dto) {
        showingCharacter.setValue(dto);
    }
}
