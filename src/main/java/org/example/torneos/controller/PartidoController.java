package org.example.torneos.controller;



import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.torneos.MainApp;
import org.example.torneos.model.Equipo;
import org.example.torneos.model.Partido;
import org.example.torneos.service.EquipoService;
import org.example.torneos.service.PartidoService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class PartidoController {
    @FXML private ComboBox<Equipo> equipoLocalCombo;
    @FXML private ComboBox<Equipo> equipoVisitanteCombo;
    @FXML private DatePicker fechaPicker;
    @FXML private TextField horaField;
    @FXML private TableView<Partido> partidosTable;

    private MainApp mainApp;
    private EquipoService equipoService = new EquipoService();
    private PartidoService partidoService = new PartidoService();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        cargarEquipos();
        cargarPartidos();
    }

    private void cargarEquipos() {
        /*
        List<Equipo> equipos = equipoService.findAll();
        equipoLocalCombo.getItems().setAll(equipos);
        equipoVisitanteCombo.getItems().setAll(equipos);

         */
    }

    private void cargarPartidos() {
        //partidosTable.getItems().setAll(partidoService.findAll());
    }

    @FXML
    private void handleProgramarPartido() {
        /*
        try {
            Equipo local = equipoLocalCombo.getValue();
            Equipo visitante = equipoVisitanteCombo.getValue();
            LocalDate fecha = fechaPicker.getValue();
            LocalTime hora = LocalTime.parse(horaField.getText());

            Partido partido = new Partido();
            partido.setEquipoLocal(local);
            partido.setEquipoVisitante(visitante);
            partido.setFechaHora(LocalDateTime.of(fecha, hora));

            partidoService.programarPartido(partido);
            cargarPartidos();
        } catch (Exception e) {
            // Mostrar error
        }

         */
    }

    @FXML
    private void handleVolver() throws Exception {
        mainApp.showAdminMainView();
    }
}
