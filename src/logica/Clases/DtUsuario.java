/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

import java.util.ArrayList;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtUsuario {

    private String nickname;
    private String nombre;
    private String apellido;
    private String correo;
    private Calendar fechaN;
    private String imagen;
    private String password;
    private boolean esproponente;
    private String biografia;
    private String direccion;
    private String sitioweb;
    private ArrayList<String> seguidores;
    private ArrayList<String> seguidos;

    public DtUsuario(String nickname, String nombre, String apellido, String correo, Calendar fechaN, String imagen, String password, boolean EsProponente) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaN = fechaN;
        this.imagen = imagen;
        this.password = password;
        this.esproponente = EsProponente;
        this.seguidores = new ArrayList<String>();
	this.seguidos = new ArrayList<String>();
    }

    public DtUsuario(String nickname, String nombre, String apellido, String correo, Calendar fechaN, String imagen, String password, boolean EsProponente, String bio, String sit, String dir) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaN = fechaN;
        this.imagen = imagen;
        this.password = password;
        this.esproponente = EsProponente;
        this.biografia = bio;
        this.sitioweb = sit;
        this.direccion = dir;
        this.seguidores = new ArrayList<String>();
	this.seguidos = new ArrayList<String>();
    }

    public boolean isEsproponente() {
        return esproponente;
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

    public String getSitioWeb() {
        return sitioweb;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getBiografia() {
        return biografia;
    }
    
    public ArrayList<String> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(ArrayList<String> seguidores) {
        this.seguidores = seguidores;
    }

    public ArrayList<String> getSeguidos() {
        return seguidos;
    }

    public void setSeguidos(ArrayList<String> seguidos) {
        this.seguidos = seguidos;
    }

}
