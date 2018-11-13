/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

/**
 *
 * @author Martin
 */
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtPago {

    String tipo;
    String tarjetaBANCO;
    String numeroCUENTA;
    String fechaV;
    int cvc;
    String titular;

    public DtPago(String tarjetaBANCO, String numeroCUENTA, String fechaV, int cvc, String titular, String tipo) {
        this.tarjetaBANCO = tarjetaBANCO;
        this.numeroCUENTA = numeroCUENTA;
        this.fechaV = fechaV;
        this.cvc = cvc;
        this.titular = titular;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTarjetaBANCO() {
        return tarjetaBANCO;
    }

    public String getNumeroCUENTA() {
        return numeroCUENTA;
    }

    public String getFechaV() {
        return fechaV;
    }

    public int getCvc() {
        return cvc;
    }

    public String getTitular() {
        return titular;
    }

}
