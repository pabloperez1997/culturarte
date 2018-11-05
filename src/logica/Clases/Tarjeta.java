/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Martin
 */
public class Tarjeta extends Pago {

    String tarjeta;
    String numero;
    Calendar fechaV;
    int cvc;
    String titular;

    public Tarjeta(String tarjeta, String numero, Calendar fechaV, int cvc, String titular) {
        this.tarjeta = tarjeta;
        this.numero = numero;
        this.fechaV = fechaV;
        this.cvc = cvc;
        this.titular = titular;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Calendar getFechaV() {
        return fechaV;
    }

    public void setFechaV(Calendar fechaV) {
        this.fechaV = fechaV;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    @Override
    public DtPago getPago() {
        Date fecha = (Date) fechaV.getTime();
        String fechaR = new SimpleDateFormat("dd/MMM/yyyy").format(fecha);

        return new DtPago(tarjeta, numero, fechaR, cvc, titular, "tarjeta");
    }

}
