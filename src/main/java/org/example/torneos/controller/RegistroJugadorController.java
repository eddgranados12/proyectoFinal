package org.example.torneos.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.torneos.MainApp;
import org.example.torneos.model.Equipo;
import org.example.torneos.model.Jugador;
import org.example.torneos.repository.EquipoRepository;
import org.example.torneos.repository.JugadorRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RegistroJugadorController {
    @FXML private TextField nombreField;
    @FXML private TextField primerApellidoField;
    @FXML private TextField segundoApellidoField;
    @FXML private DatePicker nacimientoPicker;
    @FXML private ComboBox<String> posicionCombo;
    @FXML private TextField numeroCamisetaField;
    @FXML private ComboBox<Equipo> equipoCombo;

    private MainApp mainApp;
    private JugadorRepository jugadorRepository = new JugadorRepository();
    private EquipoRepository equipoRepository = new EquipoRepository();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        //cargarDatosIniciales();
        Platform.runLater(this::cargarDatosIniciales);
    }

    private void cargarDatosIniciales() {
        // Cargar posiciones
        posicionCombo.getItems().addAll(
                "Portero", "Defensa", "Lateral", "Mediocentro",
                "Delantero", "Extremo", "Capitán"
        );

        // Cargar equipos
        List<Equipo> equipos = equipoRepository.findAll();
        equipoCombo.getItems().clear();
        equipoCombo.getItems().addAll(equipos);

        // Configurar cómo mostrar los equipos
        equipoCombo.setCellFactory(param -> new ListCell<Equipo>() {
            @Override
            protected void updateItem(Equipo equipo, boolean empty) {
                super.updateItem(equipo, empty);
                if (empty || equipo == null) {
                    setText(null);
                } else {
                    setText(equipo.getNombre() + " (" + equipo.getCategoria() + ")");
                }
            }
        });

        equipoCombo.setButtonCell(new ListCell<Equipo>() {
            @Override
            protected void updateItem(Equipo equipo, boolean empty) {
                super.updateItem(equipo, empty);
                if (empty || equipo == null) {
                    setText("Seleccione un equipo");
                } else {
                    setText(equipo.getNombre() + " (" + equipo.getCategoria() + ")");
                }
            }
        });
    }


    @FXML
    private void handleGuardar() {
        Equipo equipoSeleccionado = equipoCombo.getValue();
        if (equipoSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un equipo", Alert.AlertType.WARNING);
            return;
        }

        if (validarCampos()) {
            Jugador jugador = new Jugador();
            jugador.setNombre(nombreField.getText());
            jugador.setPrimer_apellido(primerApellidoField.getText());
            jugador.setSegundo_apellido(segundoApellidoField.getText());

            LocalDate fecha = nacimientoPicker.getValue();
            jugador.setNacimiento(fecha.format(DateTimeFormatter.ISO_DATE));

            jugador.setPosicion(posicionCombo.getValue());
            jugador.setNumero_camiseta(numeroCamisetaField.getText());
            jugador.setId_equipo(equipoCombo.getValue().getId_equipo());

            if (jugadorRepository.save(jugador)) {
                mostrarAlerta("Éxito", "Jugador registrado correctamente", Alert.AlertType.INFORMATION);
                limpiarCampos();
            } else {
                mostrarAlerta("Error", "No se pudo registrar el jugador", Alert.AlertType.ERROR);
            }
        }
    }

    private boolean validarCampos() {
        if (nombreField.getText().isEmpty() || primerApellidoField.getText().isEmpty()) {
            mostrarAlerta("Validación", "Nombre y primer apellido son obligatorios", Alert.AlertType.WARNING);
            return false;
        }

        if (nacimientoPicker.getValue() == null) {
            mostrarAlerta("Validación", "Fecha de nacimiento es obligatoria", Alert.AlertType.WARNING);
            return false;
        }

        if (posicionCombo.getValue() == null) {
            mostrarAlerta("Validación", "Debe seleccionar una posición", Alert.AlertType.WARNING);
            return false;
        }

        if (equipoCombo.getValue() == null) {
            mostrarAlerta("Validación", "Debe seleccionar un equipo", Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    private void limpiarCampos() {
        nombreField.clear();
        primerApellidoField.clear();
        segundoApellidoField.clear();
        nacimientoPicker.setValue(null);
        posicionCombo.setValue(null);
        numeroCamisetaField.clear();
        equipoCombo.setValue(null);
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