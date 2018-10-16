/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DtServicios;

import java.util.List;
import logica.Clases.DtUsuario;

/**
 *
 * @author Martin
 */
public class DtListUsuario {

    List<DtUsuario> lista;

    public DtListUsuario(List<DtUsuario> list) {
        this.lista = list;
    }

    public List<DtUsuario> getLista() {
        return this.lista;
    }
}
