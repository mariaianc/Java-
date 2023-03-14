package gui;

import domain.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import service.Service;

public class Controller {
    private Service service; //primeste service cu operatiile de sortare

    public Controller(Service service) {
        this.service = service;
    }


    //fiecare chestie din fxml are id pe baza caruia accesezi campul ca sa pui/scoti chestii din el
    @FXML
    private ListView<Route> available_routes_id;

    //pe obslist asta il pui separat pt ca in lista 2 ai nevioie de obiect nu de string ca sa poti lua nr_seats de acolo
    ObservableList<Route> availableRoutesOBSList;

    @FXML
    private Button buy_button_id;

    @FXML
    private ComboBox<String> combobox_arrival_id;

    @FXML
    private ComboBox<String> combobox_departure_id;

    @FXML
    private ListView<String> list_all_routes_id;

    @FXML
    private TextField nr_tickets_id;

    @FXML
    private Text total_price;



    @FXML
    public void initialize() {
        //daca ai nevoie sa setezi cv in fxml setezi in initialize ca in constructor nu o sa fie initializate inca
        var sortedRoutes = service.getSorted(); //var ca sa poata fii orice type si aici pui toate rutele sortate
        ObservableList<String> allRoutes = FXCollections.observableArrayList(); //folosesti observable list ca sa se modifice dupa si il faci de stringuri ca sa afisezi dupa rutele ca string
        this.list_all_routes_id.setItems(allRoutes);//seteaza lista de iteme si orice se schimba aici se schimba si in list view
        sortedRoutes.forEach(route -> allRoutes.add(route.toString()));//pt fiecare rupa pui in items


        //comboboxu pt source city
        var sourceCities = service.getAllSourceCities();
        ObservableList<String> sourceCitiesOBSList = FXCollections.observableArrayList();
        this.combobox_departure_id.setItems(sourceCitiesOBSList);
        sourceCities.forEach(city->sourceCitiesOBSList.add(city.toString()));
    }




    //asa populezi combobox 2 cu destinatii
    @FXML
    void selectDeparture(ActionEvent event) {
        var departure = this.combobox_departure_id.getValue();  //iei valoarea din combobox plecare
        var destinationCities = service.getAllDestinationCitiesForSourceCity(departure); //pui toate destinatiile de la plecarea selectata aici
        ObservableList<String> destinationCitiesOBSList = FXCollections.observableArrayList(); //creezi o lista care sa contina toate destinatiile pt combobox 2
        this.combobox_arrival_id.setItems(destinationCitiesOBSList); //pui lista cu destinatiile in combobox
        destinationCities.forEach(city->destinationCitiesOBSList.add(city.toString())); //pt ca e combobox poti sa transformi lista dupa din cod si sa adaugi obiecte in ea
    }

    //asa populezi lista cu rutele disponibile dupa alegerea oraselor
    @FXML
    void select_arrival(ActionEvent event) {
    var source = this.combobox_departure_id.getValue(); //valoarea de la plecare
    var destination = this.combobox_arrival_id.getValue();//valoarea de la ajuns
    var availableRoutes = service.getAllRoutesWithDEPARTUREandDestination(source,destination); //folosesti functia ca sa vezi care sunt toate rutele disponibile
    availableRoutesOBSList = FXCollections.observableArrayList(); //faci o lista de observable in care pui obiecte (nu stringuri) de la care iei nr de locuri disponibile
    this.available_routes_id.setItems(availableRoutesOBSList); //pui lista cu rutele disponibile in a doua lista
    availableRoutes.forEach(route->availableRoutesOBSList.add(route));//adaugi rutele pe parcurs ca e lista observabila
    }

    //selecteaza nr de bilete dupa ce alegi ruta si face pretul total + erori
    @FXML
    void select_nr_tickets(ActionEvent event) {

        int nr_tickets= Integer.parseInt(nr_tickets_id.getText());//iei stringu dat de la client din textbox si il transformi in int

        var selected = this.available_routes_id.getSelectionModel().getSelectedItem(); //ca sa iei ce ai selectat  din lista de available routes

        if (selected == null) { //daca nu ai selectat nicio ruta iti face fereastra cu eroare (cod de pe net)
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error!");  //mesajul mare
            errorAlert.setContentText("No Route Selected!"); //mesajul mic
            errorAlert.showAndWait();
            return;
        }
        int totalPrice = nr_tickets * selected.getPrice_ticket(); //calc pretul total

        if (nr_tickets > selected.getAvailable_seats()) {   //eroare daca selecteaza mai multe bilete decat cate exista
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Invalid!");
            errorAlert.setContentText("too much tickets!");
            errorAlert.showAndWait();
        }
        else {
            this.total_price.setText(String.valueOf(totalPrice)); //iti seteaza textul pt pret total
        }

    }

    //
    @FXML
    void select_buy_button(ActionEvent event) {
        Route selectedRoute = this.available_routes_id.getSelectionModel().getSelectedItem(); //iei ruta selectata de la care vrei bilete
        if (selectedRoute == null && nr_tickets_id.getText() != "" ) {    //daca nu ai selectat ruta si nu ai scris cate bilete vrei => eroare
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Invalid!");
            errorAlert.setContentText("Cannot buy dude!");
            errorAlert.showAndWait();

            return;
        }

        int nr_tickets = Integer.parseInt(nr_tickets_id.getText()); //transformi in int nr de tikete din text box , clasa Integer are parseint

        if (nr_tickets > selectedRoute.getAvailable_seats()) { //iar fereastra de erori
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Invalid!");
            errorAlert.setContentText("too much tickets!");
            errorAlert.showAndWait();

            return;
        }

        this.service.updateSeats(selectedRoute, nr_tickets);  //faci update la baza de date dupa cumpararea biletelor
        var source = this.combobox_departure_id.getValue();
        var destination = this.combobox_arrival_id.getValue();
        var availableRoutes = service.getAllRoutesWithDEPARTUREandDestination(source,destination);
        availableRoutesOBSList.clear();
        availableRoutes.forEach(route->availableRoutesOBSList.add(route));



    }







}
