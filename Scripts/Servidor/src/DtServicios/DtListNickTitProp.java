/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DtServicios;

import java.util.List;
import logica.Clases.DtNickTitProp;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtListNickTitProp {

    List<DtNickTitProp> ListPropuestas;

    public DtListNickTitProp(List<DtNickTitProp> lista) {
        this.ListPropuestas = lista;
    }

}
