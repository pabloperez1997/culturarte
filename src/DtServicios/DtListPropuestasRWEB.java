/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DtServicios;

import java.util.List;
import logica.Clases.DTListaPropuestasR;

/**
 *
 * @author Martin
 */
public class DtListPropuestasRWEB {

    private List<DTListaPropuestasR> lista;

    public DtListPropuestasRWEB(List<DTListaPropuestasR> list) {
        this.lista = list;
    }

    public List<DTListaPropuestasR> getLista() {
        return this.lista;
    }
}
