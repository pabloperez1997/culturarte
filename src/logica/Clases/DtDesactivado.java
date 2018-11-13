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
public class DtDesactivado {
    String nombre;
    String apellido;
    String fechaD;
    String fechaN;
    String correo;
    String nick;

    public DtDesactivado(String nombre, String apellido, String fechaD, String fechaN, String correo, String nick) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaD = fechaD;
        this.fechaN = fechaN;
        this.correo = correo;
        this.nick = nick;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFechaD() {
        return fechaD;
    }

    public String getFechaN() {
        return fechaN;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNick() {
        return nick;
    }
    
    
}
