package org.example.torneos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.torneos.controller.*;
import org.example.torneos.model.Usuario;

public class MainApp extends Application {

    private Stage primaryStage;

    /*
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginApp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sistema de Gestión de Torneos Deportivos");

        showLoginView();
    }

    public void showLoginView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/torneos/view/loginApp.fxml"));
        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.setMainApp(this);
        controller.setDialogStage(primaryStage);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void showAdminMainView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/torneos/view/admin-main.fxml"));
        Parent root = loader.load();

        AdminMainController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.setScene(new Scene(root));
    }

    public void showUserMainView(Usuario usuario) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/torneos/view/user-main.fxml"));
        Parent root = loader.load();

        UserMainController controller = loader.getController();
        controller.setMainApp(this);
        controller.setUsuario(usuario);

        primaryStage.setScene(new Scene(root));
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