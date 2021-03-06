/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtConsultaPropuesta {

    private String titulo;
    private String descripcion;
    private String categoria;
    private String lugar;
    private String fechaR;
    private String imagen;
    private float montoActual;
    private String EstadoActual;
    private float montoE;
    private float montoTot;
    private String tipoRet;
    private String nickproponente;
    private boolean esExtendible;
    private boolean esCancelable;
    private boolean esComentable;
    private boolean esColaborable;

    public DtConsultaPropuesta(String tit, String cat, String lugar, String fecha, float monto, float montoe, String estado, String desc, String img, float montoTot, String tipoRet, String nickprop, boolean extendible, boolean cancelable, boolean comentable, boolean colaborable) {
        this.titulo = tit;
        this.categoria = cat;
        this.descripcion = desc;
        this.EstadoActual = estado;
        this.montoE = montoe;
        this.montoActual = monto;
        this.imagen = img;
        this.fechaR = fecha;
        this.lugar = lugar;
        this.montoTot = montoTot;
        this.tipoRet = tipoRet;
        this.nickproponente = nickprop;
        this.esExtendible = extendible;
        this.esCancelable = cancelable;
        this.esComentable = comentable;
        this.esColaborable = colaborable;
    }

    public String getNickproponente() {
        return nickproponente;
    }

    public float getMontoTot() {
        return this.montoTot;
    }

    public String getTipoRetorno() {
        return this.tipoRet;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public String getEstadoActual() {
        return this.EstadoActual;
    }

    public String getFechaR() {
        return this.fechaR;
    }

    public float getMontoActual() {
        return this.montoActual;
    }

    public String getLugar() {
        return this.lugar;
    }

    public String getImagen() {
        return this.imagen;
    }

    public float getMontoE() {
        return this.montoE;
    }

    public boolean getCancelable() {
        return this.esCancelable;
    }

    public boolean getExtendible() {
        return this.esExtendible;
    }

    public boolean getComentable() {
        return this.esComentable;
    }

    public boolean getColaborable() {
        return this.esColaborable;
    }
}
