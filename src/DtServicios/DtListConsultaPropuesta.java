/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DtServicios;

import java.util.List;
import logica.Clases.DtConsultaPropuesta;

/**
 *
 * @author Martin
 */
public class DtListConsultaPropuesta {

    List<DtConsultaPropuesta> lista;

    public DtListConsultaPropuesta(List<DtConsultaPropuesta> list) {
        this.lista = list;
    }

    public List<DtConsultaPropuesta> getLista() {
        return this.lista;
    }
}
