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
public class TransfPay extends Pago {

    String nomBanco;
    String numCuenta;
    String nomTitular;

    public TransfPay(String nomBanco, String numCuenta, String nomTitular) {
        this.nomBanco = nomBanco;
        this.numCuenta = numCuenta;
        this.nomTitular = nomTitular;
    }

    public String getNomBanco() {
        return nomBanco;
    }

    public void setNomBanco(String nomBanco) {
        this.nomBanco = nomBanco;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getNomTitular() {
        return nomTitular;
    }

    public void setNomTitular(String nomTitular) {
        this.nomTitular = nomTitular;
    }

    @Override
    public DtPago getPago() {
        if (nomBanco == null) {
            return new DtPago(null, numCuenta, null, 0, nomTitular, "paypal");
        } else {
            return new DtPago(nomBanco, numCuenta, null, 0, nomTitular, "transferencia");
        }
    }
}
