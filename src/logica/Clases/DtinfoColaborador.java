/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtinfoColaborador {

    private String nickname;
    private String nombre;
    private String apellido;
    private String correo;
    private Calendar fechaN;
    private String imagen;
    private List<DtColaboraciones> colaboraciones;

    public DtinfoColaborador(String nickname, String nombre, String apellido, String correo, Calendar fechaN) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaN = fechaN;
    }

    public DtinfoColaborador(Usuario aux) {
        this.nickname = aux.getNickname();
        this.nombre = aux.getNombre();
        this.apellido = aux.getApellido();
        this.correo = aux.getCorreo();
        this.fechaN = aux.getFechaN();
        this.imagen = aux.getImagen();

    }

    public DtinfoColaborador(Colaborador aux) {
        this.nickname = aux.getNickname();
        this.nombre = aux.getNombre();
        this.apellido = aux.getApellido();
        this.correo = aux.getCorreo();
        this.fechaN = aux.getFechaN();
        this.imagen = aux.getImagen();
        this.colaboraciones = aux.getDtColaboraciones();
    }

    public List<DtColaboraciones> getColaboraciones() {
        return colaboraciones;
    }

    public String getImagen() {
        return imagen;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public Calendar getFechaN() {
        return fechaN;
    }
}
