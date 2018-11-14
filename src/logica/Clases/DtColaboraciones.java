/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtColaboraciones {

    private String UColaborador;
    private float montoC;
    private Calendar fechaRealiz;
    private boolean Entradas;
    private String Propuesta;
    private DtPago Pago;
    private String desc;

    public DtColaboraciones(String UColaborador, float montoC, Calendar fechaRealiz, boolean Entradas, String Propuesta) {
        this.UColaborador = UColaborador;
        this.montoC = montoC;
        this.fechaRealiz = fechaRealiz;
        this.Entradas = Entradas;
        this.Propuesta = Propuesta;
        this.Pago = Pago;
    }

    public DtColaboraciones(Colaboracion col) {
        this.UColaborador = col.getColaborador().getNickname();
        this.montoC = col.getMontoC();
        this.fechaRealiz = col.getFechaRealiz();
        this.Entradas = col.getEntradas();
        this.Propuesta = col.getPropuesta().getTituloP();
        if (col.getPago() != null) {
            this.Pago = col.getPago().getPago();
        }
    }

    public DtColaboraciones(Colaboracion col, String desc) {
        this.UColaborador = col.getColaborador().getNickname();
        this.montoC = col.getMontoC();
        this.fechaRealiz = col.getFechaRealiz();
        this.Entradas = col.getEntradas();
        this.Propuesta = col.getPropuesta().getTituloP();
        if (col.getPago() != null) {
            this.Pago = col.getPago().getPago();
        }
        this.desc = desc;
    }

    public String getUColaborador() {
        return UColaborador;
    }

    public float getMontoC() {
        return montoC;
    }

    public Calendar getFechaRealiz() {
        return fechaRealiz;
    }

    public boolean getEntradas() {
        return Entradas;
    }

    public String getPropuesta() {
        return Propuesta;
    }

    public String getDesc() {
        return desc;
    }

}
