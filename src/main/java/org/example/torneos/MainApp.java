package org.example.torneos;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.torneos.controller.*;
import org.example.torneos.model.Usuario;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private Stage loginStage;


    @Override

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setOnCloseRequest(e -> Platform.exit());

        showLoginView();
    }

    public void showLoginView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/torneos/view/loginApp.fxml"));
        Parent root = loader.load();

        // Creamos un nuevo Stage para el login
        loginStage = new Stage();
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.initStyle(StageStyle.UTILITY);
        loginStage.setTitle("Inicio de Sesión");
        loginStage.setScene(new Scene(root));

        LoginController controller = loader.getController();
        controller.setMainApp(this);
        controller.setDialogStage(loginStage);

        loginStage.show();
        primaryStage.hide();
    }

    public void showAdminMainView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/torneos/view/admin-main.fxml"));
        Parent root = loader.load();

        AdminMainController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Panel de Administración");
        primaryStage.show();
        loginStage.close();


    }

    public void showUserMainView(Usuario usuario) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/torneos/view/user-main.fxml"));
        Parent root = loader.load();

        UserMainController controller = loader.getController();
        controller.setUsuario(usuario);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Panel de Usuario");
        primaryStage.show();
        loginStage.close();
    }

    public void showEquipoView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/torneos/view/equipo-view.fxml"));
        Parent root = loader.load();

        EquipoController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.setScene(new Scene(root));
    }

    public void showPartidoView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/torneos/view/partido-view.fxml"));
        Parent root = loader.load();

        PartidoController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.setScene(new Scene(root));
    }

    // En MainApp.java
    public void showRegistroJugadorView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/torneos/view/registro-jugador.fxml"));
            Parent root = loader.load();

            RegistroJugadorController controller = loader.getController();
            controller.setMainApp(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registro de Jugador");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // En MainApp.java
    public void showRegistroPartidoView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/torneos/view/registro-partido.fxml"));
            Parent root = loader.load();

            RegistroPartidoController  controller = loader.getController();
            controller.setMainApp(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registro de Partido");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // En MainApp.java
    public void showRegistroTorneoView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/torneos/view/registro-torneo.fxml"));
            Parent root = loader.load();

            RegistroTorneoController controller = loader.getController();
            controller.setMainApp(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registro de Torneo");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRegistroEquipoView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/torneos/view/registro-equipo.fxml"));
            Parent root = loader.load();

            RegistroEquipoController controller = loader.getController();
            controller.setMainApp(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registro de Equipo");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}