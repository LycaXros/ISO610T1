/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

/**
 *
 * @author Jesus Dicent
 */
public class Producto {

    private int _rowIndex;
    private int _idProducto;
    private int _idCategoria;
    private String _nombre;
    private double _precioVenta;
    private int _stock;

    public Producto(int idProducto,int rowIndex, int idCategoria, String nombre, double precioVenta, int stock) {
        this._idProducto = idProducto;
        this._idCategoria = idCategoria;
        this._nombre = nombre;
        this._precioVenta = precioVenta;
        this._stock = stock;
        this._rowIndex = rowIndex;
    }
    
    public Producto( int idCategoria, String nombre, double precioVenta, int stock) {
        this._idProducto = 0;
        this._rowIndex = 0;
        this._idCategoria = idCategoria;
        this._nombre = nombre;
        this._precioVenta = precioVenta;
        this._stock = stock;
    }

    public int getIdProducto() {
        return _idProducto;
    }

    public void setIdProducto(int _idProducto) {
        this._idProducto = _idProducto;
    }

    public int getIdCategoria() {
        return _idCategoria;
    }

    public void setIdCategoria(int _idCategoria) {
        this._idCategoria = _idCategoria;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public double getPrecioVenta() {
        return _precioVenta;
    }

    public void setPrecioVenta(double _precioVenta) {
        this._precioVenta = _precioVenta;
    }

    public int getStock() {
        return _stock;
    }

    public void setStock(int _stock) {
        this._stock = _stock;
    }

    public int getRowIndex() {
        return _rowIndex;
    }
    
    
}
