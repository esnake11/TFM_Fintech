/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Orden;
import modelo.Stat;

/**
 * FXML Controller class
 *
 * 
 */
public class StatsController implements Initializable {

    @FXML
    private TableView<Stat> tblStats;
    @FXML
    private TableColumn colStat;
    @FXML
    private TableColumn colValor;
    @FXML
    private TableColumn colProd;

    private ObservableList<Stat> statsList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setStatsList(FXCollections.observableArrayList());
        
        // Asociacion de cada fila con el atributo de Stat correspondiente
        this.colProd.setCellValueFactory(new PropertyValueFactory("producto"));
        this.colValor.setCellValueFactory(new PropertyValueFactory("valor"));
        this.colStat.setCellValueFactory(new PropertyValueFactory("titulo"));
        
        // Ajuste del tamaño de las columnas para que sean todas iguales
        this.colProd.prefWidthProperty().bind(tblStats.widthProperty().divide(4));
        this.colValor.prefWidthProperty().bind(tblStats.widthProperty().divide(4));
        this.colStat.prefWidthProperty().bind(tblStats.widthProperty().divide(2));
    }    
    
    /**
     * @return the statsList
     */
    public ObservableList<Stat> getStatsList() {
        return statsList;
    }

    /**
     * @param statsList the statsList to set
     */
    public void setStatsList(ObservableList<Stat> statsList) {
        this.statsList = statsList;
        this.tblStats.setItems(this.statsList);
    }
}
