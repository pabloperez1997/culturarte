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
import logica.Clases.TipoRetorno;
import logica.Fabrica;
import DtServicios.DtCategorias;
/**
 *
 * @author Martin
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PublicadorAltaPropuesta {

    private Endpoint endpoint = null;

    public void publicar() {
        endpoint = Endpoint.publish("http://127.0.0.1:8280/servicioAltaP", this);
    }

    @WebMethod
    public DtCategorias ListarCategorias() {
        return new DtCategorias(Fabrica.getInstance().getControladorPropCat().ListarCategorias());
    }

    @WebMethod
    public boolean CrearPropuesta(@WebParam(name = "tituloP") String tituloP, @WebParam(name = "descripcion") String descripcion, @WebParam(name = "lugar") String lugar, @WebParam(name = "imagen") DataImagen imagen, @WebParam(name = "fecha") Calendar fecha, @WebParam(name = "montoE") float montoE, @WebParam(name = "montoTot") float montoTot, @WebParam(name = "retorno") TipoRetorno retorno) throws Exception {
        return Fabrica.getInstance().getControladorPropCat().crearPropuesta(tituloP, descripcion, lugar, imagen, fecha, montoE, montoTot, retorno);
    }
}
