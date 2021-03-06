/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

import java.util.Calendar;
import logica.Clases.Pago;

/**
 *
 * @author Santiago.S
 */
public class Colaboracion {

    private Colaborador UColaborador;
    private float montoC;
    private Calendar fechaRealiz;
    private boolean Entradas;
    private Propuesta Propuesta;
    private Pago pago;

    public Colaboracion(Colaborador UColaborador, float montoC, Calendar fechaRealiz, boolean Entradas, Propuesta propuesta) {
        this.UColaborador = UColaborador;
        this.montoC = montoC;
        this.fechaRealiz = fechaRealiz;
        this.Entradas = Entradas;
        this.Propuesta = propuesta;
        this.pago = null;
    }

    public float getMontoC() {
        return this.montoC;
    }

    public boolean getEntradas() {
        return this.Entradas;
    }

    public Calendar getFechaRealiz() {
        return this.fechaRealiz;
    }

    public Colaborador getColaborador() {
        return this.UColaborador;
    }

    public Propuesta getPropuesta() {
        return this.Propuesta;
    }

    public void setUColaborador(Colaborador UColaborador) {
        this.UColaborador = UColaborador;
    }

    public void setMontoC(float montoC) {
        this.montoC = montoC;
    }

    public void setFechaRealiz(Calendar fechaRealiz) {
        this.fechaRealiz = fechaRealiz;
    }

    public void setEntradas(boolean Entradas) {
        this.Entradas = Entradas;
    }

    public void setPropuesta(Propuesta Propuesta) {
        this.Propuesta = Propuesta;
    }

    public String getNickName() {
        return UColaborador.getNickname();
    }

    public String getTituloP() {
        return Propuesta.getTituloP();
    }

    public boolean borrarme() {
        boolean ok;
        ok = this.UColaborador.borrarColaboracion(this);
        if (ok) {
            ok = this.Propuesta.eliminarColaboracion(this);
        }
        this.UColaborador = null;
        this.Propuesta = null;
        return ok;
    }

    public DtColaboraciones getDtColaboracion() {
        return new DtColaboraciones(this);
    }

    public Pago getPago() {
        return this.pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
}
