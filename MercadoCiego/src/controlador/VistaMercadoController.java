/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Stack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import modelo.Orden;
import modelo.Stat;

/**
 * FXML Controller class
 *
 * 
 */
public class VistaMercadoController implements Initializable {
    
    @FXML
    private TableView<Orden> tblCompra;
    @FXML
    private TableColumn colCompraProd;
    @FXML
    private TableColumn colCompraTime;
    @FXML
    private TableColumn colCompraAcciones;
    @FXML
    private TableColumn colCompraPrecio;
    @FXML
    private TableView<Orden> tblVenta;
    @FXML
    private TableColumn colVentaPrecio;
    @FXML
    private TableColumn colVentaAcciones;
    @FXML
    private TableColumn colVentaTime;
    @FXML
    private TableColumn colVentaProd;

    private ObservableList<Orden> ordenesCompra;
    private ObservableList<Orden> ordenesVenta;
    @FXML
    private Button btnNuevoLogin;
    
    private ObservableList<Orden> historial;

    @FXML
    private Button bntHistorial;
    @FXML
    private Button btnStats;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización de la lista del historial
        this.historial = FXCollections.observableArrayList();
        
        // Inicialización de las listas que representan las órdenes de compra y venta
        this.ordenesCompra = FXCollections.observableArrayList();
        this.ordenesVenta = FXCollections.observableArrayList();
        
        // Asociación de cada columna con el atributo Orden que le corresponde
        this.colCompraProd.setCellValueFactory(new PropertyValueFactory("nombreProd"));
        this.colCompraTime.setCellValueFactory(new PropertyValueFactory("timestamp"));
        this.colCompraAcciones.setCellValueFactory(new PropertyValueFactory("acciones"));
        this.colCompraPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        
        this.colVentaProd.setCellValueFactory(new PropertyValueFactory("nombreProd"));
        this.colVentaTime.setCellValueFactory(new PropertyValueFactory("timestamp"));
        this.colVentaAcciones.setCellValueFactory(new PropertyValueFactory("acciones"));
        this.colVentaPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        
        // Ajuste del tamaño de las columnas para que sean todas iguales
        this.colCompraProd.prefWidthProperty().bind(tblCompra.widthProperty().divide(3));
        this.colCompraTime.prefWidthProperty().bind(tblCompra.widthProperty().divide(3));
        this.colCompraAcciones.prefWidthProperty().bind(tblCompra.widthProperty().divide(6));
        this.colCompraPrecio.prefWidthProperty().bind(tblCompra.widthProperty().divide(6));
        
        this.colVentaProd.prefWidthProperty().bind(tblVenta.widthProperty().divide(3));
        this.colVentaTime.prefWidthProperty().bind(tblVenta.widthProperty().divide(3));
        this.colVentaAcciones.prefWidthProperty().bind(tblVenta.widthProperty().divide(6));
        this.colVentaPrecio.prefWidthProperty().bind(tblVenta.widthProperty().divide(6));
        
        // Datos de relleno para el mercado
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date (System.currentTimeMillis());
   
        this.addToCompras(new Orden(true, "GOOGLE", date, 120, 100.24));
        this.addToVentas(new Orden(false, "AMAZON", date, 100, 400.23));
        this.addToVentas(new Orden(false, "BBVA", date, 1000, 3.27));
        this.addToCompras(new Orden(true, "BBVA", date, 7500, 3.23));
        this.addToCompras(new Orden(true, "AMAZON", date, 2000, 390.86));
        this.addToCompras(new Orden(true, "GOOGLE", date, 1200, 101.05));
        this.addToCompras(new Orden(true, "BBVA", date, 1000, 3.23));
        this.addToCompras(new Orden(true, "BBVA", date, 1000, 3.23));
    }    
    
    /*
        Descripción del método: añade una Orden a la lista que almacen las órdenes
        de venta. Después, ordena la lista y actualiza la tabla para que el mercado
        muestre los nuevos registros
    
    */
    public void addToVentas(Orden o) {
        this.ordenesVenta.add(o);
        this.ordenarListaVenta();
        this.tblVenta.setItems(this.ordenesVenta);
    }
    
    /*
        Descripción del método: misma funcionalidad que el anterior pero con la lista
        órdenes de compra.
    */
    public void addToCompras(Orden o) {
        this.ordenesCompra.add(o);
        this.ordenarListaCompra();
        this.tblCompra.setItems(this.ordenesCompra);        
    }
    
    /*
        Descripción del método: eliminar una orden de la lista de órdenes de venta
        y actualiza la tabla para eliminar el registro seleccionado de ella.
    */
    public void eliminarFromVentas(Orden o) {
        this.ordenesVenta.remove(o);
        this.tblVenta.setItems(this.ordenesVenta);
    }
    
    /*
        Despcripción del método: misma funcionalidad que el método anterior pero para
        las órdenes de compra.
    */
    public void eliminarFromCompras(Orden o) {
        this.ordenesCompra.remove(o);
        this.tblCompra.setItems(this.ordenesCompra);
    }

    /*
        Descripcción del método: abre una nueva pestaña de Login para que pueda iniciar
        sesión un nuevo cliente
    */
    @FXML
    private void nuevoLogin(ActionEvent event) throws IOException {
        FXMLLoader loaderMercado = new FXMLLoader(getClass().getResource("/vista/Login.fxml"));            
        Parent root1 = (Parent) loaderMercado.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    
    /*
        Descripción del método: recibe una orden y decide si tiene que encolarla como
        orden de compra u orden de venta
    */
    public void encolarOrden(Orden o) {
        if (o.isCompra())
            this.encolarOrdenCompra(o);
        else
            this.encolarOrdenVenta(o);
        
        this.tblCompra.refresh();
        this.tblVenta.refresh();
    }
    
    /*
        Descripción del método: recibe una orden de compra e intenta cruzarla con
        alguna orden de venta que tenga un precio igual o menor al del la orden de venta.
        En caso de encontrar una orden para cruzar, realiza la operación invocando a otro método.
        En caso contrario, añade la orden recibida a la lista de órdenes de compra
    */
    private void encolarOrdenCompra(Orden o) { 
        // Para controlar si se ha realizado alguna operación
        boolean actionDone = false;

        for (Orden tmpOrder : this.ordenesVenta){
            // Comprobación de que la órdenes son relativas al mismo producto
            if (o.getNombreProd().equals(tmpOrder.getNombreProd())){
                // Si la orden de compra de llegada tiene un precio menor al de la primera
                // orden de venta del mismo producto que se encuentra esto significa que
                // no hay ninguna orden c on la que se pueda cruzar, al estar la lista
                // de órdenes de venta ordenada por precio ascendente.
                if (o.getPrecio() < tmpOrder.getPrecio()) {
                    this.addToCompras(o);
                } else {
                    this.operacionCompra(o, tmpOrder);
                }
                actionDone = true;
                break;              
            }
        }
        // Si no se ha encontrado ningún producto en el mercado con el mismo nombre,
        // directamente se añade la orden a la lista de órdenes de compra
        if (!actionDone)
            this.addToCompras(o);
    }
    
    /*
        Descripción del método: mismo funcionamiento que el anterior pero para las 
        órdenes de venta de acciones que se reciben
    */
    private void encolarOrdenVenta(Orden o) {
        boolean actionDone = false;
        for (Orden tmpOrder : this.ordenesCompra){
            if (o.getNombreProd().equals(tmpOrder.getNombreProd())){
                if (o.getPrecio() > tmpOrder.getPrecio()) {
                    this.addToVentas(o);
                } else {
                    this.operacionVenta(o, tmpOrder);
                }
                actionDone = true;
                break;              
            }
        }
        // Si no se ha llevado a cabo ninguna operación, se encola la orden en el mercado
        if (!actionDone)
            this.addToVentas(o);
    }
    
    /*
        Descripción del método: ordena la lista de órdenes de compra de forma descendente
        teniendo en cuenta en primer lugar el precio más alto. Como segundo criterio,
        utilizado en el caso de que el precio sea el mismo, ordena de forma ascendente,
        según la hora de llegada de la orden. Esto garantiza que, cuando hay dos órdenes de
        compra con el mismo precio, y llega una orden de venta para ese mismo producto y precio,
        el cruce se lleve a cabo con la orden de compra que llegó antes al mercado
    */
    private void ordenarListaCompra(){
        this.ordenesCompra.sort(Comparator.comparing(Orden::getPrecio).reversed().thenComparing(Orden::getTimestamp));        
    }
    
    /*
        Descripción del método: misma funcionalidad que el anterior, solo que en este caso
        la primera ordenación, la del precio, es de forma ascendente.
    */
    private void ordenarListaVenta() {
        this.ordenesVenta.sort(Comparator.comparing(Orden::getPrecio).thenComparing(Orden::getTimestamp));
    }
    
    /*
        @param ordenLlegada: orden que un usuario acaba de realizar
        @param ordenEncolada: orden que ya se encuentra encolada en el mercado de ventas
        Descripción del método: lleva a cabo la operación de compra entre ordenLlegada
        y ordenEncolada. La operación varía según si la orden de llegada tiene más acciones
        que la orden ya encolada. Comúnmente, las operaciones se añaden al historial con el
        momento exacto en el que se realizan.
    */
    private void operacionCompra(Orden ordenLlegada, Orden ordenEncolada) {
        /*
            En este caso, la orden que llega tiene más acciones que la que ya está
            en el mercado, por lo que quedan acciones en standby. Estas acciones
            se intentan cruzar de nuevo invocando al método encolarOrdenCompra() de nuevo.
        */
        if (ordenLlegada.getAcciones() > ordenEncolada.getAcciones()) {
            // Registramos la orden en el historial
            ordenEncolada.setTimestamp(new Date(System.currentTimeMillis()));
            this.historial.add(ordenEncolada);
            // Eliminamos orden encolada de la cola de ventas
            this.eliminarFromVentas(ordenEncolada);
            // Creamos nueva orden con las acciones restantes y la encolamos
            Orden tmp = ordenLlegada;
            tmp.setAcciones(tmp.getAcciones() - ordenEncolada.getAcciones());
            this.encolarOrdenCompra(tmp);
        } else if (ordenLlegada.getAcciones() == ordenEncolada.getAcciones()) {
            // Registramos la orden en el historial
            ordenEncolada.setTimestamp(new Date(System.currentTimeMillis()));
            this.historial.add(ordenEncolada);
            this.eliminarFromVentas(ordenEncolada);
        }
        /*
            En este caso, el número de acciones de la orden de llegada es menor al
            de la orden ya encolada, por lo que no hay que eliminar la orden encolada
            del mercado, sino registrar la transacción con el número de acciones 
            de la orden de llegada y actualizar el número de acciones de la orden
            encolada.
        */
        else {
            // Registramos la orden en el historial
            ordenLlegada.setTimestamp(new Date(System.currentTimeMillis()));
            ordenLlegada.setPrecio(ordenEncolada.getPrecio());
            this.historial.add(ordenLlegada);
            // Eliminamos la antigua orden
            this.eliminarFromVentas(ordenEncolada);
            // Actualizamos orden encolada con el nuevo número de acciones
            ordenEncolada.setAcciones(ordenEncolada.getAcciones() - ordenLlegada.getAcciones());
            this.addToVentas(ordenEncolada);       
        }
    }
    
    /*
        Descripción del método: mismo funcionamiento que el anterior pero para las 
        operaciones de venta.
    */
    private void operacionVenta(Orden ordenLlegada, Orden ordenEncolada) {
        if (ordenLlegada.getAcciones() > ordenEncolada.getAcciones()) {
            // Registramos la orden en el historial
            ordenEncolada.setTimestamp(new Date(System.currentTimeMillis()));
            this.historial.add(ordenEncolada);
            // Eliminamos orden encolada de la cola de ventas
            this.eliminarFromCompras(ordenEncolada);
            // Creamos nueva orden con las acciones restantes y la encolamos
            Orden tmp = ordenLlegada;
            tmp.setAcciones(tmp.getAcciones() - ordenEncolada.getAcciones());
            this.encolarOrdenVenta(tmp);
        } else if (ordenLlegada.getAcciones() == ordenEncolada.getAcciones()) {
            // Registramos la orden en el historial
            ordenEncolada.setTimestamp(new Date(System.currentTimeMillis()));
            this.historial.add(ordenEncolada);
            this.eliminarFromCompras(ordenEncolada);
        }
        else {
            // Registramos la orden en el historial
            ordenLlegada.setPrecio(ordenEncolada.getPrecio());
            ordenLlegada.setTimestamp(new Date(System.currentTimeMillis()));
            this.historial.add(ordenLlegada);
            // Eliminamos la antigua orden
            this.eliminarFromCompras(ordenEncolada);
            // Actualizamos orden encolada con el nuevo número de acciones
            ordenEncolada.setAcciones(ordenEncolada.getAcciones() - ordenLlegada.getAcciones());
            this.addToCompras(ordenEncolada);       
        }
    }

    
    /*
        Abre la ventana con el historial de transacciones del mercado.
    */
    @FXML
    private void consultarHistorial(ActionEvent event) throws IOException {
        FXMLLoader loaderHistorial = new FXMLLoader(getClass().getResource("/vista/Historial.fxml"));            
        Parent root1 = (Parent) loaderHistorial.load();
        HistorialController hController = (HistorialController) loaderHistorial.getController();
        hController.setHistorial(this.historial);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.setResizable(false);
        stage.show();
    }

    /*
        Abre la pestaña con las estadísticas relativas al estado actual del mercado
    */
    @FXML
    private void generarStats(ActionEvent event) throws IOException {
        ObservableList<Stat> listStats = FXCollections.observableArrayList();
        // Calculo de estadísticas
        Pair<Pair, Pair> minMaxValues = this.calcularStatsMinMax(this.ordenesCompra);
        String titulo = "Menor precio medio de cotización\nen órdenes de compra";
        double valor = Math.round((Double) minMaxValues.getKey().getValue()*1000.0)/1000.0;
        String producto = minMaxValues.getKey().getKey().toString();
        Stat s = new Stat(titulo, valor, producto);
        
        listStats.add(s);
        
        titulo = "Mayor precio medio de cotización\nen órdenes de compra";
        valor = Math.round((Double) minMaxValues.getValue().getValue()*1000.0)/1000.0;
        producto = minMaxValues.getValue().getKey().toString();
        s = new Stat(titulo, valor, producto);
        
        listStats.add(s);
        
        minMaxValues = this.calcularStatsMinMax(this.ordenesVenta);
        titulo = "Menor precio medio de cotización\nen órdenes de venta";
        valor = Math.round((Double) minMaxValues.getKey().getValue()*1000.0)/1000.0;
        producto = minMaxValues.getKey().getKey().toString();
        s = new Stat(titulo, valor, producto);
        
        listStats.add(s);
        
        titulo = "Mayor precio medio de cotización\nen órdenes de venta";
        valor = Math.round((Double) minMaxValues.getValue().getValue()*1000.0)/1000.0;
        producto = minMaxValues.getValue().getKey().toString();
        s = new Stat(titulo, valor, producto);
        
        listStats.add(s);
        
        Pair<String, Integer> mayorVolumen = this.calcularMayorVolumen(this.ordenesCompra);
        titulo = "Mayor volumen de acciones\nen órdenes de compra";
        valor = mayorVolumen.getValue();
        producto = mayorVolumen.getKey().toString();
        s = new Stat(titulo, valor, producto);
        
        listStats.add(s);
        
        mayorVolumen = this.calcularMayorVolumen(this.ordenesVenta);
        titulo = "Mayor volumen de acciones\nen órdenes de venta";
        valor = mayorVolumen.getValue();
        producto = mayorVolumen.getKey();
        s = new Stat(titulo, valor, producto);
        
        listStats.add(s);
        
        Pair<Pair, Pair> minMaxSpread = this.calcularMinMaxSpread();
        titulo = "Menor spread";
        valor = Math.round((Double) minMaxSpread.getKey().getValue()*1000.0)/1000.0;
        producto = minMaxSpread.getKey().getKey().toString();
        s = new Stat(titulo, valor, producto);
        
        listStats.add(s);
        
        titulo = "Mayor spread";
        valor = Math.round((Double) minMaxSpread.getValue().getValue()*1000.0)/1000.0;
        producto = minMaxSpread.getValue().getKey().toString();
        s = new Stat(titulo, valor, producto);
        
        listStats.add(s);
        
        FXMLLoader loaderStats = new FXMLLoader(getClass().getResource("/vista/Stats.fxml"));            
        Parent root1 = (Parent) loaderStats.load();
        StatsController sController = (StatsController) loaderStats.getController();
        sController.setStatsList(listStats);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.setResizable(false);
        stage.show();
    }

    /*
        Descripción del método: calcula qué producto tienen un mayor y qué producto 
        tieneun menor precio medio en el mercado, en la lista que se haya proporcionado 
        el método (puede ser de compra o de venta).
    */
    private Pair<Pair,Pair> calcularStatsMinMax(ObservableList<Orden> lista) {
        Hashtable<String, Double> mapNombreValor = new Hashtable<>();
        Hashtable<String, Integer> mapNombreCuenta = new Hashtable<>();
        
        // Relacionamos cada producto con sus precios
        for (Orden o : lista) {
            if (mapNombreValor.containsKey(o.getNombreProd())) {
                mapNombreValor.put(o.getNombreProd(), mapNombreValor.get(o.getNombreProd())+o.getPrecio());
                mapNombreCuenta.put(o.getNombreProd(), mapNombreCuenta.get(o.getNombreProd())+1);
            }
            else {
                mapNombreValor.put(o.getNombreProd(), o.getPrecio());
                mapNombreCuenta.put(o.getNombreProd(), 1);
            }
        }
        
        // Calculamos el valor mínimo y el máximo
        Set<String> keys = mapNombreValor.keySet();
        
        double min = 0;
        double max = 0;
        String minProd = "";
        String maxProd = "";
        
        for (String k : keys) {
            double valor = mapNombreValor.get(k);
            int count = mapNombreCuenta.get(k);
            double avg = valor/count; // Media del precio
            
            if (avg > max) {
                max = avg;
                maxProd = k;
            }
            if (avg < min || min == 0){
                min = avg;
                minProd = k;
            }                           
            
        }
        
        Pair<String, Double> pairMin = new Pair<>(minProd, min);
        Pair<String, Double> pairMax = new Pair<>(maxProd, max);
        Pair<Pair, Pair> pairPairs = new Pair<>(pairMin, pairMax);
        
        return pairPairs;
    }
    
    /*
        Descrpción del método: calcula qué producto tiene un mayor volumen de acciones
        en la lista proporcionada al método (compra o venta);
    */
    private Pair<String, Integer> calcularMayorVolumen(ObservableList<Orden> lista) {
        Hashtable<String, Integer> mapNombreCuenta = new Hashtable<>();
        
        // Contamos el número de acciones de cada producto
        for (Orden o : lista) {
            if (mapNombreCuenta.containsKey(o.getNombreProd())) {
                mapNombreCuenta.put(o.getNombreProd(), mapNombreCuenta.get(o.getNombreProd())+o.getAcciones());
            }
            else {
                mapNombreCuenta.put(o.getNombreProd(), o.getAcciones());
            }
        }
        
        // Calculamos el valor mínimo y el máximo
        Set<String> keys = mapNombreCuenta.keySet();
        
        Integer max = 0;
        String maxProd = "";
        
        // Calculamos el producto con mayor volumen
        for (String k : keys) {
            int count = mapNombreCuenta.get(k);
            
            if (count > max) {
                max = count;
                maxProd = k;
            }
        }
        Pair<String, Integer> ret = new Pair<>(maxProd, max);
        
        return ret;
    }
    
    /*
        Descripción del método: calcula qué producto tiene un mayor spread en el
        mercado y qué producto tiene un menor sptread.
    */
    private Pair<Pair,Pair> calcularMinMaxSpread() {
        Hashtable<String, Double> mapNombreMin = new Hashtable<>();
        Hashtable<String, Double> mapNombreMax = new Hashtable<>();
        
        // Relacionamos cada precio máximo en órdenes de compra
        for (Orden o : this.ordenesCompra) {
            if (mapNombreMax.containsKey(o.getNombreProd())) {
                if (mapNombreMax.get(o.getNombreProd()) < o.getPrecio()) 
                    mapNombreMax.put(o.getNombreProd(), o.getPrecio());
            }
            else {
                mapNombreMax.put(o.getNombreProd(), o.getPrecio());
            }
        }
        
        // Seleccionamos cada precio mínimo en órdenes de venta
        for (Orden o : this.ordenesVenta) {
            if (mapNombreMin.containsKey(o.getNombreProd())) {
                if (mapNombreMin.get(o.getNombreProd()) > o.getPrecio()) 
                    mapNombreMin.put(o.getNombreProd(), o.getPrecio());
            }
            else {
                mapNombreMin.put(o.getNombreProd(), o.getPrecio());
            }
        }
        // Calculamos el valor mínimo y el máximo
        Set<String> keys = mapNombreMin.keySet();
        
        double min = 0;
        double max = 0;
        String minProd = "";
        String maxProd = "";
        
        for (String k : keys) {
            double spread = mapNombreMin.get(k) - mapNombreMax.get(k);
            
            if (spread > max) {
                max = spread;
                maxProd = k;
            }
            if (spread < min || min == 0){
                min = spread;
                minProd = k;
            }                           
            
        }
        
        Pair<String, Double> pairMin = new Pair<>(minProd, min);
        Pair<String, Double> pairMax = new Pair<>(maxProd, max);
        Pair<Pair, Pair> pairPairs = new Pair<>(pairMin, pairMax);
        
        return pairPairs;
    }
}
