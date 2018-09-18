/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

import java.util.Calendar;

public class EstadoPropuesta {

    private TipoE Estado;
    private Calendar fechaInicio;
    private boolean Actual;

    public EstadoPropuesta(TipoE Estado, Calendar Fechacambio, boolean estActual) {
        this.Estado = Estado;
        this.fechaInicio = Fechacambio;
        this.Actual = estActual;
    }

    public TipoE getEstado() {
        return this.Estado;
    }

    public Calendar getfechaInicio() {
        return this.fechaInicio;
    }

    public boolean getActual() {
        return this.Actual;
    }

    public void setEstado(TipoE Estado) {
        this.Estado = Estado;
    }

    public void setfechaInicio(Calendar inicio) {
        this.fechaInicio = inicio;
    }

    public void setEsActual(boolean actual) {
        this.Actual = actual;
    }

}
