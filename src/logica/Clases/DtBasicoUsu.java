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
public class DtBasicoUsu {
    String nombre;
    String apellido;
    String nickName;

    public DtBasicoUsu(String nombre, String apellido, String nickName) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickName = nickName;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNickName() {
        return nickName;
    }
}
