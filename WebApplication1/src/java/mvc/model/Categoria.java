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
public class Categoria {
    private int _rowIndex;
    private int IdCategoria;
    private String Nombre;
    private String Descripcion;
    private String Estado;

    public Categoria(int IdCategoria,int rowIndex, String Nombre, String Descripcion, String Estado) {
        this.IdCategoria = IdCategoria;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Estado = Estado;
        this._rowIndex = rowIndex;
    }
    
    public Categoria( String Nombre, String Descripcion, String Estado) {
        this.IdCategoria = 0;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Estado = Estado;
        this._rowIndex = 0;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public int getRowIndex() {
        return _rowIndex;
    }
    
}
