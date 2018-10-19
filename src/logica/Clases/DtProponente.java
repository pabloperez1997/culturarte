/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtProponente extends DtUsuario {

    private String biografia;
    private String direccion;
    private String sitioweb;

    public DtProponente(String biografia, String direccion, String sitioweb, String nickname, String nombre, String apellido, String correo, Calendar fechaN, String imagen, String password, boolean esproponente) {
        super(nickname, nombre, apellido, correo, fechaN, imagen, password, esproponente);
        this.biografia = biografia;
        this.direccion = direccion;
        this.sitioweb = sitioweb;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getSitioweb() {
        return sitioweb;
    }

}
