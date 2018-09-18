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
public class DtConsultaPropuesta2 {

    private String nickColab;
    private String nombreColab;
    private String apellidoColab;
    private float montoColab;
    private String fechaR;

    public DtConsultaPropuesta2(String nick, String nombre, String apellido, float monto, String fecha) {
        this.nickColab = nick;
        this.nombreColab = nombre;
        this.apellidoColab = apellido;
        this.montoColab = monto;
        this.fechaR = fecha;
    }

    public String getNickColab() {
        return this.nickColab;
    }

    public String getNombreColab() {
        return this.nombreColab;
    }

    public String getApellidoColab() {
        return this.apellidoColab;
    }

    public float getMontoColab() {
        return this.montoColab;
    }

    public String getFechaR() {
        return this.fechaR;
    }
}
