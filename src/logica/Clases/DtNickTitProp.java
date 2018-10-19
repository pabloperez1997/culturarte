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
public class DtNickTitProp {

    private String Titulo;
    private String Proponente;

    public DtNickTitProp() {
        this.Titulo = null;
        this.Proponente = null;
    }

    public DtNickTitProp(Propuesta p) {
        this.Titulo = p.getTituloP();
        this.Proponente = p.getAutor().getNickname();

    }

    public DtNickTitProp(String titulo, String descricion) {
        this.Titulo = titulo;
        this.Proponente = descricion;
    }

    public String getTituloP() {
        return Titulo;
    }

    public String getProponente() {
        return Proponente;
    }

}
