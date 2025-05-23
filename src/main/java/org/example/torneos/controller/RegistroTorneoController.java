package org.example.torneos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.torneos.MainApp;
import org.example.torneos.model.Torneo;
import org.example.torneos.repository.TorneoRepository;

import java.time.LocalDate;

public class RegistroTorneoController {
    @FXML private TextField nombreField;
    @FXML private DatePicker fechaInicioPicker;
    @FXML private DatePicker fechaFinPicker;
    @FXML private ComboBox<String> categoriaCombo;

    private MainApp mainApp;
    private TorneoRepository torneoRepository = new TorneoRepository();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        // Cargar categorías disponibles
        categoriaCombo.getItems().addAll(
                "Infantil", "Juvenil", "Adulto", "Veterano",
                "Femenino", "Mixto", "Profesional"
        );

        // Establecer fechas por defecto
        fechaInicioPicker.setValue(LocalDate.now());
        fechaFinPicker.setValue(LocalDate.now().plusMonths(1));
    }

    @FXML
    private void handleGuardar() {
        if (validarCampos()) {
            Torneo torneo = new Torneo();
            torneo.setNombre(nombreField.getText());
            torneo.setFecha_inicio(fechaInicioPicker.getValue());
            torneo.setFecha_fin(fechaFinPicker.getValue());
            torneo.setCategoria(categoriaCombo.getValue());

            if (torneoRepository.save(torneo)) {
                mostrarAlerta("Éxito", "Torneo registrado correctamente", Alert.AlertType.INFORMATION);
                limpiarCampos();
            } else {
                mostrarAlerta("Error", "No se pudo registrar el torneo", Alert.AlertType.ERROR);
            }
        }
    }

    private boolean validarCampos() {
        if (nombreField.getText().isEmpty()) {
            mostrarAlerta("Validación", "El nombre es obligatorio", Alert.AlertType.WARNING);
            return false;
        }

        if (fechaInicioPicker.getValue() == null || fechaFinPicker.getValue() == null) {
            mostrarAlerta("Validación", "Ambas fechas son obligatorias", Alert.AlertType.WARNING);
            return false;
        }

        if (fechaInicioPicker.getValue().isAfter(fechaFinPicker.getValue())) {
            mostrarAlerta("Validación", "La fecha de inicio no puede ser después de la fecha fin", Alert.AlertType.WARNING);
            return false;
        }

        if (categoriaCombo.getValue() == null) {
            mostrarAlerta("Validación", "Debe seleccionar una categoría", Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    private void limpiarCampos() {
        nombreField.clear();
        fechaInicioPicker.setValue(LocalDate.now());
        fechaFinPicker.setValue(LocalDate.now().plusMonths(1));
        categoriaCombo.setValue(null);
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
