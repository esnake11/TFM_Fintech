/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * 
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;

    private static final String userLogin = "broker1";
    private static final String userPassword = "0000";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {
        if (this.txtUsuario.getText().equals(this.userLogin) && this.txtPassword.getText().equals(this.userPassword)) {
            // Se cierra la ventana de Login
            Stage thisWnd = (Stage) this.btnLogin.getScene().getWindow();
            thisWnd.close();
            
            // Se abre el la ventana del dashboard
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Dashboard.fxml"));            
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
            
        } else {
            // Alerta de credenciales incorrectas
            Alert wrongLogin = new Alert(Alert.AlertType.ERROR);
            wrongLogin.setHeaderText("Credenciales incorrectas");
            wrongLogin.show();
        }
    }
    
}
