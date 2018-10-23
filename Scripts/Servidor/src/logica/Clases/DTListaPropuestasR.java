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
public class DTListaPropuestasR {

    private String Titulo;
    private String Proponente;
    private TipoE Estado;

    public DTListaPropuestasR() {
        this.Titulo = null;
        this.Proponente = null;
        this.Estado = null;
    }

    public DTListaPropuestasR(Propuesta p) {
        this.Titulo = p.getTituloP();
        this.Proponente = p.getAutor().getNickname();
        this.Estado = p.getEstadoActual().getEstado();
    }

    public DTListaPropuestasR(String titulo, String descricion, TipoE Estado) {
        this.Titulo = titulo;
        this.Proponente = descricion;
        this.Estado = Estado;
    }

    public String getTituloP() {
        return Titulo;
    }

    public String getProponente() {
        return Proponente;
    }

    public TipoE getEstadoActual() {
        return Estado;
    }

}
