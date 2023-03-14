package gui;

import domain.Document;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import service.Service;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @FXML
    public void initialize() {

        var sortedDocuments = service.getDocumentsSortedAfterName();
        ObservableList<Document> allDocuments = FXCollections.observableArrayList();
        this.list_all_id.setItems(allDocuments);
        sortedDocuments.forEach(document -> allDocuments.add(document)); //nu uita ca parcurgi sorted doc si pui pe rand in obs list

    }

    private void updateList(){
        var sortedDocuments = service.getDocumentsSortedAfterName();
        ObservableList<Document> allDocuments = FXCollections.observableArrayList();
        this.list_all_id.setItems(allDocuments);
        sortedDocuments.forEach(document -> allDocuments.add(document));
    }

    @FXML
    private ListView<Document> list_all_id;

    @FXML
    private TextField search_id;

    @FXML
    private TextArea text_area_content_id;

    @FXML
    private TextField text_field_key_words_id;

    @FXML
    private Button button_search_id;

    @FXML
    private Button button_open_id;

    @FXML
    private Button button_update_id;

    ObservableList<Document> obs_list = FXCollections.observableArrayList();
    @FXML
    void select_button_search(ActionEvent event) {
        String input = search_id.getText();
        var new_list = service.filterOnSearchedWords(input);
        this.list_all_id.setItems(obs_list);
        new_list.forEach(document -> obs_list.add(document));
    }


    @FXML
    void select_button_open(ActionEvent event) {
        Document selected = this.list_all_id.getSelectionModel().getSelectedItem();
        this.text_area_content_id.setText(selected.getContent());
        Document selected2 = this.list_all_id.getSelectionModel().getSelectedItem();
        this.text_field_key_words_id.setText(selected2.getKeyWords());
    }


    @FXML
    void select_button_update(ActionEvent event) {
        String new_keys = this.text_field_key_words_id.getText();
        String new_content = this.text_area_content_id.getText();
        service.updateDB(this.list_all_id.getSelectionModel().getSelectedItem(),new_keys,new_content);

        /*var new_list=service.getDocumentsSortedAfterName();
        obs_list.clear();
        this.list_all_id.setItems(obs_list);
        new_list.forEach(document -> obs_list.add(document));*/
        updateList();
    }


    //nu merge la enter
    @FXML
    void select_content(InputMethodEvent event) {

    }


    @FXML
    void select_key_words(KeyEvent event) {

    }

    @FXML
    void select_search(ActionEvent event) {

    }



}
