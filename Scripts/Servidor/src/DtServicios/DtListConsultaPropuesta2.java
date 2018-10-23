/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DtServicios;

import java.util.List;
import logica.Clases.DtConsultaPropuesta2;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtListConsultaPropuesta2 {

    List<DtConsultaPropuesta2> lista;

    public DtListConsultaPropuesta2(List<DtConsultaPropuesta2> list) {
        this.lista = list;
    }

    public List<DtConsultaPropuesta2> getLista() {
        return this.lista;
    }
}
