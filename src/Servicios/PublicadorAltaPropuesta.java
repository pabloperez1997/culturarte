/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import DtServicios.DtCategorias;
import java.util.Calendar;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import logica.Clases.DataImagen;
import logica.Fabrica;

/**
 *
 * @author Martin
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PublicadorAltaPropuesta {

    private Endpoint endpoint = null;

    public void publicar(String URL) {
        endpoint = Endpoint.publish(URL, this);
    }

    @WebMethod
    public DtCategorias ListarCategorias() {
        return new DtCategorias(Fabrica.getInstance().getControladorPropCat().ListarCategorias());
    }

    @WebMethod
    public boolean CrearPropuesta(@WebParam(name = "tituloP") String tituloP, @WebParam(name = "descripcion") String descripcion, @WebParam(name = "lugar") String lugar, @WebParam(name = "fecha") String fecha, @WebParam(name = "montoE") float montoE, @WebParam(name = "montoTot") float montoTot, @WebParam(name = "retorno") String retorno) throws Exception {
        return Fabrica.getInstance().getControladorPropCat().crearPropuesta(tituloP, descripcion, lugar, new DataImagen(null, null, null), fecha, montoE, montoTot, retorno);
    }

    @WebMethod
    public boolean SeleccionarUC(@WebParam(name = "nick") String nick, @WebParam(name = "categoria") String categoria) throws Exception {
        return Fabrica.getInstance().getControladorPropCat().seleccionarUC(nick, categoria);
    }
}
