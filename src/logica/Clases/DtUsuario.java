/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

import java.util.Calendar;

/**
 *
 * @author Lucas
 */
public class DtUsuario {

    private String nickname;
    private String nombre;
    private String apellido;
    private String correo;
    private Calendar fechaN;
    private String imagen;
    private String password;
    private boolean esproponente;

    public DtUsuario(String nickname, String nombre, String apellido, String correo, Calendar fechaN, String imagen, String password,boolean EsProponente) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaN = fechaN;
        this.imagen = imagen;
        this.password= password;
        this.esproponente=EsProponente;
    }

    public boolean Esproponente() {
        return esproponente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
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

    public String getImagen() {
        return imagen;
    }

}
