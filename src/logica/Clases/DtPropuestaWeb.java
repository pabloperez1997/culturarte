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
public class DtPropuestaWeb {

    private final String tituloP;
    private final String descrip;
    private final int tiempoR;
    private final float recaudacion;
    private final int porcentaje;
    private final int colaboradores;

    public DtPropuestaWeb(String tituloP, String descrip, int tiempoR, float recaudacion, int porcentaje, int colaboradores) {
        this.tituloP = tituloP;
        this.descrip = descrip;
        this.tiempoR = tiempoR;
        this.recaudacion = recaudacion;
        this.porcentaje = porcentaje;
        this.colaboradores = colaboradores;
    }

    public String getTituloP() {
        return tituloP;
    }

    public String getDescrip() {
        return descrip;
    }

    public int getTiempoR() {
        return tiempoR;
    }

    public float getRecaudacion() {
        return recaudacion;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public int getColaboradores() {
        return colaboradores;
    }

   
}
