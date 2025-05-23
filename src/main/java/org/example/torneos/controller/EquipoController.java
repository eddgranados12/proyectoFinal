package org.example.torneos.controller;



import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.torneos.MainApp;
import org.example.torneos.model.Equipo;
import org.example.torneos.service.EquipoService;

public class EquipoController {
    @FXML private TextField nombreField;
    @FXML private TextField categoriaField;
    @FXML private TableView<Equipo> equiposTable;

    private MainApp mainApp;
    private EquipoService equipoService = new EquipoService();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        cargarEquipos();
    }

    private void cargarEquipos() {
        //equiposTable.getItems().setAll(equipoService.findAll());
    }

    @FXML
    private void handleRegistrarEquipo() {
        /*
        try {
            Equipo equipo = new Equipo();
            equipo.setNombre(nombreField.getText());
            equipo.setCategoria(categoriaField.getText());

            equipoService.save(equipo);
            cargarEquipos();

            // Limpiar campos
            nombreField.clear();
            categoriaField.clear();
        } catch (Exception e) {
            // Mostrar error
        }

         */
    }

    @FXML
    private void handleVolver() throws Exception {
        mainApp.showAdminMainView();
    }

    // En EquipoController.java
    @FXML
    private void handleAgregarJugador() {
        Equipo equipoSeleccionado = equiposTable.getSelectionModel().getSelectedItem();
        if (equipoSeleccionado != null) {
            //mainApp.showRegistroJugadorView(equipoSeleccionado);
            mainApp.showRegistroJugadorView();
        }
    }


    @FXML
    private void handleRegistrarPartido() {
        try {
            mainApp.showRegistroPartidoView();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("No se pudo abrir el registro de partidos");
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}
