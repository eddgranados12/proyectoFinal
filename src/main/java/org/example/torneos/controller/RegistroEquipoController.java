package org.example.torneos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.torneos.MainApp;
import org.example.torneos.model.Equipo;
import org.example.torneos.repository.EquipoRepository;

import java.time.LocalDate;

public class RegistroEquipoController {
    @FXML private TextField nombreField;
    @FXML private ComboBox<String> categoriaCombo;
    @FXML private DatePicker fechaCreacionPicker;
    @FXML private ComboBox<String> statusCombo;

    private MainApp mainApp;
    private EquipoRepository equipoRepository = new EquipoRepository();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        // Cargar categorías
        categoriaCombo.getItems().addAll(
                "Infantil", "Juvenil", "Adulto", "Veterano",
                "Femenino", "Mixto", "Profesional"
        );

        // Cargar estados
        statusCombo.getItems().addAll(
                "Activo", "Inactivo", "Suspendido", "Eliminado"
        );
        statusCombo.setValue("Activo");

        // Establecer fecha actual
        fechaCreacionPicker.setValue(LocalDate.now());
    }

    @FXML
    private void handleGuardar() {
        if (validarCampos()) {
            Equipo equipo = new Equipo();
            equipo.setNombre(nombreField.getText());
            equipo.setCategoria(categoriaCombo.getValue());
            equipo.setFecha_creacion(fechaCreacionPicker.getValue());
            equipo.setStatus(statusCombo.getValue());

            if (equipoRepository.save(equipo)) {
                mostrarAlerta("Éxito", "Equipo registrado correctamente", Alert.AlertType.INFORMATION);
                limpiarCampos();
            } else {
                mostrarAlerta("Error", "No se pudo registrar el equipo", Alert.AlertType.ERROR);
            }
        }
    }

    private boolean validarCampos() {
        if (nombreField.getText().isEmpty()) {
            mostrarAlerta("Validación", "El nombre es obligatorio", Alert.AlertType.WARNING);
            return false;
        }

        if (categoriaCombo.getValue() == null) {
            mostrarAlerta("Validación", "Debe seleccionar una categoría", Alert.AlertType.WARNING);
            return false;
        }

        if (fechaCreacionPicker.getValue() == null) {
            mostrarAlerta("Validación", "La fecha de creación es obligatoria", Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    private void limpiarCampos() {
        nombreField.clear();
        categoriaCombo.setValue(null);
        fechaCreacionPicker.setValue(LocalDate.now());
        statusCombo.setValue("Activo");
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