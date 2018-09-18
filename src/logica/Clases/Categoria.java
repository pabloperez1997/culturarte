/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

/**
 *
 * @author Usuario
 */
import java.util.HashMap;
import java.util.Map;

public class Categoria {

    private String nombreC;
    private Map<String, Categoria> Categorias;
    private Map<String, Propuesta> Propuestas;

    public Categoria(String nombreC) {
        this.nombreC = nombreC;
        this.Categorias = new HashMap<>();
        this.Propuestas = new HashMap<>();
    }
    
    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public void setCategoriaH(Categoria hija) {
        this.Categorias.put(hija.getNombreC(), hija);
    }

    public Map<String, Categoria> getCategoriasH() {
        return this.Categorias;
    }

    public void setPropuesta(Propuesta prop) {
        this.Propuestas.put(prop.getTituloP(), prop);
    }

    public Map<String, Propuesta> getPropuestas() {
        return this.Propuestas;
    }
}
