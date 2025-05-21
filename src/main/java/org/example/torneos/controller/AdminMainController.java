package org.example.torneos.controller;

import javafx.fxml.FXML;
import org.example.torneos.MainApp;

public class AdminMainController {
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleRegistrarEquipo() throws Exception {
        mainApp.showEquipoView();
    }

    @FXML
    private void handleGestionarPartidos() throws Exception {
        mainApp.showPartidoView();
    }

    @FXML
    private void handleLogout() throws Exception {
        mainApp.showLoginView();
    }
}
