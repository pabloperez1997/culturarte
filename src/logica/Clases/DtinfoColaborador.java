/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica.Clases;

import java.util.Calendar;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DtinfoColaborador {
  private String nickname;
    private String nombre;
    private String apellido;
    private String correo;
    private Calendar fechaN;
    private String imagen;

    public DtinfoColaborador(String nickname, String nombre, String apellido, String correo, Calendar fechaN) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaN = fechaN;
    }
    public DtinfoColaborador(Usuario aux) {
        this.nickname=aux.getNickname();
        this.nombre=aux.getNombre();
        this.apellido=aux.getApellido();
        this.correo=aux.getCorreo();
        this.fechaN=aux.getFechaN();
        this.imagen=aux.getImagen();
        
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
