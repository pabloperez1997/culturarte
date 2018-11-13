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
public class DtRecomendacionProp {

    String titulo;
    int puntaje;

    public DtRecomendacionProp(String titulo, int puntaje) {
        this.titulo = titulo;
        this.puntaje = puntaje;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPuntaje() {
        return puntaje;
    }

}
