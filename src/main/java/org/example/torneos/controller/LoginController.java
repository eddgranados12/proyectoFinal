package org.example.torneos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.torneos.MainApp;
import org.example.torneos.model.Usuario;
import org.example.torneos.service.AuthService;


public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private AuthService authService = new AuthService();
    private MainApp mainApp;
    private Stage dialogStage;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleLogin() {
        try {
            String username = usernameField.getText();
            String password = passwordField.getText();

            Usuario usuario = authService.authenticate(username, password);

            dialogStage.close();

            if (usuario.isAdmin()) {
                mainApp.showAdminMainView(); // Puede lanzar Exception
            } else {
                mainApp.showUserMainView(usuario); // Puede lanzar Exception
            }

        } catch (IllegalArgumentException e) {
            showAlert("Error de Autenticación", e.getMessage());
        } catch (Exception e) {
            // Aquí capturas cualquier otra excepción que venga de showAdminMainView o showUserMainView
            showAlert("Error al mostrar la vista", "No se pudo cargar la interfaz de usuario.");
            e.printStackTrace();
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}