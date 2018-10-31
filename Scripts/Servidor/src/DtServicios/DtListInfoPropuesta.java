/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DtServicios;

import java.util.List;
import logica.Clases.DtinfoPropuesta;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtListInfoPropuesta {

    private List<DtinfoPropuesta> lista;

    public DtListInfoPropuesta(List<DtinfoPropuesta> list) {
        this.lista = list;
    }

    public List<DtinfoPropuesta> getLista() {
        return this.lista;
    }
}
