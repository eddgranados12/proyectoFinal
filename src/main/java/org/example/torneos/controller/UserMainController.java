package org.example.torneos.controller;



import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.torneos.MainApp;
import org.example.torneos.model.Usuario;
import org.example.torneos.model.Equipo;
import org.example.torneos.service.EquipoService;
import java.util.List;

public class UserMainController {
    @FXML private TextField searchField;
    @FXML private TableView<Equipo> equiposTable;

    private MainApp mainApp;
    private Usuario usuario;
    private EquipoService equipoService = new EquipoService();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        cargarEquipos();
    }


    private void cargarEquipos() {
        //List<Equipo> equipos = equipoService.findAll();
        //equiposTable.getItems().setAll(equipos);
    }


    @FXML
    private void handleBuscarPorNombre() {
        /*
        String nombre = searchField.getText();
        List<Equipo> equipos = equipoService.findByNombre(nombre);
        equiposTable.getItems().setAll(equipos);

         */
    }




    @FXML
    private void handleBuscarPorId() {
        /*
        try {
            int id = Integer.parseInt(searchField.getText());
            Equipo equipo = equipoService.findById(id);
            if (equipo != null) {
                equiposTable.getItems().setAll(equipo);
            }
        } catch (NumberFormatException e) {
            // Mostrar error
        }

         */
    }



    @FXML
    private void handleVerResultados() {
        Equipo equipoSeleccionado = equiposTable.getSelectionModel().getSelectedItem();
        if (equipoSeleccionado != null) {
            // Mostrar resultados del equipo seleccionado
        }
    }

    @FXML
    private void handleLogout() throws Exception {
        mainApp.showLoginView();
    }
}