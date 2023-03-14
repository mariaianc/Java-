package gui;

import domain.Weather;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import service.Service;

import java.util.WeakHashMap;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }


    @FXML
    private ListView<Weather> list_all_id;

    ObservableList<Weather> listWeatherOBS = FXCollections.observableArrayList();

    @FXML
    public void initialize()
    {
      this.list_all_id.setItems(listWeatherOBS);
      service.sortByStart().forEach(weather -> listWeatherOBS.add(weather));
    }

    @FXML
    private ComboBox<String> combobox_description_id;

    @FXML
    void select_combobox_description(ActionEvent event) {
        var all = service.getAllDescription();
        ObservableList<String> words = FXCollections.observableArrayList();
        this.combobox_description_id.setItems(words);
        all.forEach(weather->words.add(weather));
    }


}
