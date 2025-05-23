package org.example.torneos.controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.torneos.MainApp;
import org.example.torneos.model.Equipo;
import org.example.torneos.model.Partido;
import org.example.torneos.model.Torneo;
import org.example.torneos.repository.EquipoRepository;
import org.example.torneos.repository.PartidoRepository;
import org.example.torneos.repository.TorneoRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class RegistroPartidoController {
    @FXML private ComboBox<Torneo> torneoCombo;
    @FXML private DatePicker fechaPicker;
    @FXML private TextField horaField;
    @FXML private TextField lugarField;
    @FXML private ComboBox<String> statusCombo;
    @FXML private ComboBox<Equipo> equipoLocalCombo;
    @FXML private ComboBox<Equipo> equipoVisitanteCombo;

    private MainApp mainApp;
    private PartidoRepository partidoRepository = new PartidoRepository();
    private TorneoRepository torneoRepository = new TorneoRepository();
    private EquipoRepository equipoRepository = new EquipoRepository();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        // Cargar torneos
        List<Torneo> torneos = torneoRepository.findAll();
        torneoCombo.getItems().addAll(torneos);

        // Cargar estados
        statusCombo.getItems().addAll(
                "Programado", "En Juego", "Finalizado",
                "Cancelado", "Suspendido"
        );
        statusCombo.setValue("Programado");

        // Cargar equipos
        List<Equipo> equipos = equipoRepository.findAll();
        equipoLocalCombo.getItems().addAll(equipos);
        equipoVisitanteCombo.getItems().addAll(equipos);

        // Establecer valores por defecto
        fechaPicker.setValue(LocalDate.now());
        horaField.setText("18:00");
    }

    @FXML
    private void handleGuardar() {
        if (validarCampos()) {
            Partido partido = new Partido();
            partido.setId_torneo(torneoCombo.getValue().getId_torneo());
            partido.setFecha(fechaPicker.getValue());

            try {
                partido.setHora(LocalTime.parse(horaField.getText()));
            } catch (DateTimeParseException e) {
                mostrarAlerta("Error", "Formato de hora inválido. Use HH:MM", Alert.AlertType.ERROR);
                return;
            }

            partido.setLugar(lugarField.getText());
            partido.setStatus(statusCombo.getValue());
            partido.setId_equipo_local(equipoLocalCombo.getValue().getId_equipo());
            partido.setId_equipo_visitante(equipoVisitanteCombo.getValue().getId_equipo());

            if (partidoRepository.save(partido)) {
                mostrarAlerta("Éxito", "Partido registrado correctamente", Alert.AlertType.INFORMATION);
                limpiarCampos();
            } else {
                mostrarAlerta("Error", "No se pudo registrar el partido", Alert.AlertType.ERROR);
            }
        }
    }

    private boolean validarCampos() {
        // Validación de torneo
        if (torneoCombo.getValue() == null) {
            mostrarAlerta("Validación", "Debe seleccionar un torneo", Alert.AlertType.WARNING);
            return false;
        }

        // Validación de fecha
        if (fechaPicker.getValue() == null) {
            mostrarAlerta("Validación", "La fecha es obligatoria", Alert.AlertType.WARNING);
            return false;
        }

        // Validación de hora
        if (!horaField.getText().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            mostrarAlerta("Validación", "Formato de hora inválido. Use HH:MM", Alert.AlertType.WARNING);
            return false;
        }

        // Validación de equipos
        if (equipoLocalCombo.getValue() == null || equipoVisitanteCombo.getValue() == null) {
            mostrarAlerta("Validación", "Debe seleccionar ambos equipos", Alert.AlertType.WARNING);
            return false;
        }

        // Validar que no sean el mismo equipo
        if (equipoLocalCombo.getValue().getId_equipo() == equipoVisitanteCombo.getValue().getId_equipo()) {
            mostrarAlerta("Validación", "El equipo local y visitante no pueden ser el mismo", Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    private void limpiarCampos() {
        torneoCombo.setValue(null);
        fechaPicker.setValue(LocalDate.now());
        horaField.setText("18:00");
        lugarField.clear();
        statusCombo.setValue("Programado");
        equipoLocalCombo.setValue(null);
        equipoVisitanteCombo.setValue(null);
    }

    @FXML
    private void handleCancelar() throws Exception {
        mainApp.showAdminMainView();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
