/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import DtServicios.DtListConsultaPropuesta2;
import DtServicios.DtListInfoPropuesta;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import DtServicios.DtListNickTitProp;
import logica.Clases.DtConsultaPropuesta;
import logica.Fabrica;

/**
 *
 * @author Martin
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PublicadorConsultarPropuesta {
    
    private Endpoint endpoint = null;
    
    public void publicarConsultaPropuesta() {
        endpoint = Endpoint.publish("http://127.0.0.1:8280/servicioConsultaP", this);
    }
    
    @WebMethod
    public DtListNickTitProp ListarPropuestas() {
        return new DtListNickTitProp(Fabrica.getInstance().getControladorPropCat().listarPropuestas());
    }
    
    @WebMethod
    public DtConsultaPropuesta SeleccionarPropuesta(@WebParam(name = "titulo") String titulo, @WebParam(name = "nickProp") String nickProp) throws Exception {
        return Fabrica.getInstance().getControladorPropCat().SeleccionarPropuesta(titulo, nickProp);
    }
    
    @WebMethod
    public DtListConsultaPropuesta2 ListarColaboradoresProp(@WebParam(name = "titulo") String titulo) throws Exception {
        return new DtListConsultaPropuesta2(Fabrica.getInstance().getControladorPropCat().ListaColaboradoresProp(titulo));
    }

    //listarPropuestas por categoria
    @WebMethod
    public DtListInfoPropuesta ListarPropuestasCategoria(@WebParam(name = "nombre") String nombre) {
        return new DtListInfoPropuesta(Fabrica.getInstance().getControladorPropCat().ListarPropuestasCategoria(nombre));
    }
    
}
