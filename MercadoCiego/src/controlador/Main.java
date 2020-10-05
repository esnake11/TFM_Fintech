
package controlador;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            // Inicio de la pantalla del mercado
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vista/VistaMercado.fxml"));
            Pane ventana = (Pane) loader.load();

            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
            // Abrimos la pantalla del login

            FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("/vista/Login.fxml"));            
            Parent root1 = (Parent) loaderLogin.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
            
            /* Asociación del controlador del mercado con la clase DashboardController
               Esto permitirá actualizar el mercado en tiempo real sin necesidad de 
               regargarlo            
            */
            FXMLLoader loaderDash = new FXMLLoader(getClass().getResource("/vista/Dashboard.fxml"));
            loaderDash.load();
            DashboardController d = (DashboardController) loaderDash.getController();
            d.mercadoController = (VistaMercadoController) loader.getController();
                       
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
