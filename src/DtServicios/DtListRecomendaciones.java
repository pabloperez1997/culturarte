/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DtServicios;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import logica.Clases.DtRecomendacionProp;

/**
 *
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtListRecomendaciones {

    List<DtRecomendacionProp> lista;

    public DtListRecomendaciones(List<DtRecomendacionProp> lista) {
        this.lista = lista;
    }

    public List<DtRecomendacionProp> getLista() {
        return lista;
    }

}
