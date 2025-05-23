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

    public static void main(String[] args) {
        launch();
    }
}