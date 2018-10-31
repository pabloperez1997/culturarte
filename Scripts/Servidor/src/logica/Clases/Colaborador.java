/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Santiago.S
 */
public class Colaborador extends Usuario {

    private List<Colaboracion> Colaboraciones;

    public Colaborador(String nickname, String nombre, String apellido, String correo, Calendar fechaN, String imagen,String password) {
        super(nickname, nombre, apellido, correo, fechaN, imagen,password);
        this.Colaboraciones = new ArrayList();
    }

    public List<Colaboracion> getColaboraciones() {
        return Colaboraciones;
    }

    public void setColaboraciones(Colaboracion Colaboraciones) {
        this.Colaboraciones.add(Colaboraciones);
    }

    public boolean borrarColaboracion(Colaboracion col) {
        boolean ok = false;
        for (Colaboracion c : this.getColaboraciones()) {
            if (c.getColaborador().getNickname().equals(col.getColaborador().getNickname())) {
                this.Colaboraciones.remove(c);
                ok = true;
                break;
            }
        }
        return ok;
    }
}
