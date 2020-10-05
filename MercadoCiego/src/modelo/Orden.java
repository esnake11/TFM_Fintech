/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * 
 */
public class Orden {

    /**
     * @return the idOrder
     */
    public int getIdOrder() {
        return idOrder;
    }

    /**
     * @param idOrder the idOrder to set
     */
    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }
    private boolean isCompra;
    private String nombreProd;
    private Date timestamp;
    private int acciones;
    private double precio;
    private static int id = 0;
    private int idOrder;
    public Orden (boolean isCompra, String nombreProd, Date timestamp, int acciones, double precio) {
        this.idOrder = ++this.id;
        this.isCompra = isCompra;
        this.nombreProd = nombreProd;
        this.timestamp = timestamp;
        this.acciones = acciones;
        this.precio = precio;
    }
    /**
     * @return the isCompra
     */
    public boolean isCompra() {
        return isCompra;
    }

    /**
     * @param isCompra the isCompra to set
     */
    public void setIsCompra(boolean isCompra) {
        this.isCompra = isCompra;
    }

    /**
     * @return the nombreProd
     */
    public String getNombreProd() {
        return nombreProd;
    }

    /**
     * @param nombreProd the nombreProd to set
     */
    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the acciones
     */
    public int getAcciones() {
        return acciones;
    }

    /**
     * @param acciones the acciones to set
     */
    public void setAcciones(int acciones) {
        this.acciones = acciones;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
   
}
