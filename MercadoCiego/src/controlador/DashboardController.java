/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Orden;

/**
 * FXML Controller class
 *
 * 
 */
public class DashboardController implements Initializable {

    static VistaMercadoController mercadoController;
    @FXML
    private TextField txtCompraProd;
    @FXML
    private TextField txtCompraAcciones;
    @FXML
    private TextField txtCompraPrecio;
    @FXML
    private Button btnCompra;
    @FXML
    private TextField txtVentaPrecio;
    @FXML
    private TextField txtVentaAcciones;
    @FXML
    private TextField txtVentaProd;
    @FXML
    private Button btnVenta;

    /**
     * Initializes the controller class.
     */

    //Initialize es como el constructor de nuestra vista
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ordenarCompraPressed(ActionEvent event) {
        try {
            String nombreProd = txtCompraProd.getText();
            int acciones = Integer.parseInt(txtCompraAcciones.getText());
            double precio = Double.parseDouble(txtCompraPrecio.getText());
            Date date = new Date(System.currentTimeMillis());
            Orden o = new Orden(true, nombreProd, date, acciones, precio);

            mercadoController.encolarOrden(o);
            
 
        } catch (Exception e) {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Parámetros incorrectos");
            error.show();
        }
    }

    @FXML
    private void ordenarVentaPressed(ActionEvent event) {
        try {
            String nombreProd = txtVentaProd.getText();
            int acciones = Integer.parseInt(txtVentaAcciones.getText());
            double precio = Double.parseDouble(txtVentaPrecio.getText());
            Date date = new Date(System.currentTimeMillis());
            Orden o = new Orden(false, nombreProd, date, acciones, precio);

            mercadoController.encolarOrden(o);
            
 
        } catch (Exception e) {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Parámetros incorrectos");
            error.show();
        }
    }
    
}
