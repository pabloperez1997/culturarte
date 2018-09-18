/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

/**
 *
 * @author Usuario
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;

public class Usuario {

    private String nickname;
    private String nombre;
    private String apellido;
    private String correo;
    private Calendar fechaN;
    private String imagen;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private Map<String, Usuario> seguidos;
    

    public Usuario(String nickname, String nombre, String apellido, String correo, Calendar fechaN, String imagen,String password) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaN = fechaN;
        this.imagen = imagen;
        this.password=password;
        this.seguidos = new HashMap<>();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Calendar getFechaN() {
        return fechaN;
    }

    public void setFechaN(Calendar fechaN) {
        this.fechaN = fechaN;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Map<String, Usuario> getSeguidos() {
        return seguidos;
    }

    public void setSeguido(Usuario u){
        this.seguidos.put(u.getNickname(), u);
    }
}
