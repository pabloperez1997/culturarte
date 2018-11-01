/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

/**
 *
 * @author Santiago.S
 */
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtComentarios {
    private String Colaborador;
    private String Propuesta;
    private String Comentario;

    public String getColaborador() {
        return Colaborador;
    }

    public String getPropuesta() {
        return Propuesta;
    }

    public String getComentario() {
        return Comentario;
    }

    public DtComentarios(String Colaborador, String Propuesta, String Comentario) {
        this.Colaborador = Colaborador;
        this.Propuesta = Propuesta;
        this.Comentario = Comentario;
    }
}
