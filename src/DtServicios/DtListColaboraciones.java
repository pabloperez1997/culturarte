/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DtServicios;

import java.util.List;
import logica.Clases.DtColaboraciones;

/**
 *
 * @author Martin
 */
public class DtListColaboraciones {

    List<DtColaboraciones> lista;

    public DtListColaboraciones(List<DtColaboraciones> list) {
        this.lista = list;
    }

    public List<DtColaboraciones> getLista() {
        return this.lista;
    }
}
