/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DtServicios;

import java.util.List;
import logica.Clases.DtComentarios;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Santiago.S
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtListComentarios {
     List<DtComentarios> lista2;
     
     public DtListComentarios(List<DtComentarios> lista){
         this.lista2 = lista;
     }
     
     public List<DtComentarios> getLista() {
        return this.lista2;
    }
}
