/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import DtServicios.DtListNickTitProp;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import logica.Fabrica;

/**
 *
 * @author Martin
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PublicadorExtenderCancelarComentarPropuesta {

    private Endpoint endpoint = null;

    public void publicarExtenderCancelarComentarPropuesta(String URL) {
        endpoint = Endpoint.publish(URL, this);
    }

    // Cancelar Propuesta
    @WebMethod
    public DtListNickTitProp ListarPropuestasCancelar(@WebParam(name = "nick") String nick) {
        return new DtListNickTitProp(Fabrica.getInstance().getControladorPropCat().ListarPropuestasCancelar(nick));
    }

    @WebMethod
    public boolean CancelarPropuesta(@WebParam(name = "titulo") String titulo, @WebParam(name = "nick") String nick) throws Exception {
        return Fabrica.getInstance().getControladorPropCat().CancelarPropuesta(titulo, nick);
    }

    //ExtenderFinanciacion
    @WebMethod
    public DtListNickTitProp ListarPropuestasX_deProponenteX(@WebParam(name = "nick") String nick) {
        return new DtListNickTitProp(Fabrica.getInstance().getControladorPropCat().ListarPropuestasX_DeProponenteX(nick));
    }

    @WebMethod
    public boolean ExtenderFinanciacion(@WebParam(name = "titulo") String titulo) {
        return Fabrica.getInstance().getControladorPropCat().ExtenderFinanciacion(titulo);
    }

    //ComentarPropuesta
    @WebMethod
    public DtListNickTitProp ListarPropuestasComentar() {
        return new DtListNickTitProp(Fabrica.getInstance().getControladorPropCat().listarPropuestasComentar());
    }

    @WebMethod
    public void ComentarPropuesta(@WebParam(name = "titulo") String titulo, @WebParam(name = "nick") String nick, @WebParam(name = "texto") String texto) throws Exception {
        Fabrica.getInstance().getControladorPropCat().ComentarPropuesta(titulo, nick, texto);
    }
}
