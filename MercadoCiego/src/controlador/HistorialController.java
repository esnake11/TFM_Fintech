/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Orden;

/**
 * FXML Controller class
 *
 * 
 */
public class HistorialController implements Initializable {

    @FXML
    private TableView<Orden> tblHistorial;
    @FXML
    private TableColumn colProd;
    @FXML
    private TableColumn colAcciones;
    @FXML
    private TableColumn colPrecio;
    @FXML
    private TableColumn colTimestamp;
    private ObservableList<Orden> historial;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setHistorial(FXCollections.observableArrayList());
        
        this.colProd.setCellValueFactory(new PropertyValueFactory("nombreProd"));
        this.colTimestamp.setCellValueFactory(new PropertyValueFactory("timestamp"));
        this.colAcciones.setCellValueFactory(new PropertyValueFactory("acciones"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        
        // Ajuste del tamaño de las columnas para que sean todas iguales
        this.colProd.prefWidthProperty().bind(tblHistorial.widthProperty().divide(3));
        this.colTimestamp.prefWidthProperty().bind(tblHistorial.widthProperty().divide(3));
        this.colAcciones.prefWidthProperty().bind(tblHistorial.widthProperty().divide(6));
        this.colPrecio.prefWidthProperty().bind(tblHistorial.widthProperty().divide(6));
        
        // Ordenamos y actualizamos tabla
        this.ordenarHistorial();
        this.tblHistorial.setItems(this.historial);
    }    

    /**
     * @return the historial
     */
    public ObservableList<Orden> getHistorial() {
        return historial;
    }

    /**
     * @param historial the historial to set
     */
    public void setHistorial(ObservableList<Orden> historial) {
        this.historial = historial;
        this.ordenarHistorial();
        this.tblHistorial.setItems(this.historial);
        
    }
    
    private void ordenarHistorial() {
        this.historial.sort(Comparator.comparing(Orden::getTimestamp).reversed());
    }

    
}
