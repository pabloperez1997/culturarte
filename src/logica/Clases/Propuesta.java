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
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import logica.Clases.Colaboracion;

public class Propuesta {

    private String TituloP;
    private String descripcionP;
    private String imagen;
    private String lugar;
    private Calendar fecha;
    private float montoE;
    private float montoTot;
    private Proponente Autor;
    private TipoRetorno retorno;
    private EstadoPropuesta estadoActual;
    private List<EstadoPropuesta> historialEstados;
    private List<Colaboracion> Colaboraciones;
    private Categoria categoria;

    public Propuesta(String tituloP, String descripcion, String imagen, String lugar, Calendar fecha, float montoE, float montoTot, EstadoPropuesta estado, Categoria cate, TipoRetorno retorno, Proponente autor) {
        this.TituloP = tituloP;
        this.descripcionP = descripcion;
        this.imagen = imagen;
        this.lugar = lugar;
        this.fecha = fecha;
        this.montoE = montoE;
        this.montoTot = montoTot;
        this.categoria = cate;
        this.retorno = retorno;
        this.estadoActual = estado;
        this.Autor = autor;
        this.Colaboraciones = new ArrayList<>();
        this.historialEstados = new ArrayList<>();
    }

    public String getTituloP() {
        return TituloP;
    }

    public void setTituloP(String TituloP) {
        this.TituloP = TituloP;
    }

    public List<EstadoPropuesta> getHistorialEst() {
        return this.historialEstados;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public float getMontoE() {
        return montoE;
    }

    public void setMontoE(float montoE) {
        this.montoE = montoE;
    }

    public float getMontoTot() {
        return montoTot;
    }

    public void setMontoTot(float montoTot) {
        this.montoTot = montoTot;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public TipoRetorno getRetorno() {
        return this.retorno;
    }

    public Proponente getAutor() {
        return this.Autor;
    }

    public DtinfoPropuesta getDtPropuesta() {
        DtinfoPropuesta dp = new DtinfoPropuesta(this.TituloP, this.descripcionP, this.imagen, this.categoria.getNombreC(), this.lugar, this.fecha, this.montoE, this.montoTot, this.retorno);
        return dp;
    }

    public void setAutor(Proponente Autor) {
        this.Autor = Autor;
    }

    public EstadoPropuesta getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoPropuesta estadoActual) {
        this.estadoActual = estadoActual;
    }

    public void setEstados(EstadoPropuesta Estado) {
        this.historialEstados.add(Estado);
    }

    public List<Colaboracion> getColaboraciones() {
        return Colaboraciones;
    }

    public void setColaboraciones(Colaboracion Colaboracion) {
        this.Colaboraciones.add(Colaboracion);
    }

    public boolean eliminarColaboracion(Colaboracion c) {
        boolean ok = false;
        for (Colaboracion col : this.Colaboraciones) {
            if (col.getColaborador().getNickname().equals(c.getColaborador().getNickname())) {
                this.Colaboraciones.remove(col);
                ok = true;
                break;
            }
        }
        return ok;
    }

}
