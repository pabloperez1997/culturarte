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
public class DtSeguidor {

    private String nickName;
    private String nombre;
    private String apellido;
    private String tipo;

    public DtSeguidor(String nick, String nom, String apellido, String tipo) {
        this.nickName = nick;
        this.nombre = nom;
        this.apellido = apellido;
        this.tipo = tipo;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getTipo() {
        return this.tipo;
    }
}
