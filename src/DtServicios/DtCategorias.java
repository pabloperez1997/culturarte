/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DtServicios;

import java.util.List;

/**
 *
 * @author Martin
 */
public class DtCategorias {

    List<String> listCategoria;

    public DtCategorias(List<String> lista) {
        this.listCategoria = lista;
    }

    public List<String> getListCategoria() {
        return listCategoria;
    }
}
