/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import logica.Clases.DtUsuario;
import logica.Fabrica;

/**
 *
 * @author Martin
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PublicadorLogin {

    private Endpoint endpoint = null;

    public void publicarLogin() {
        endpoint = Endpoint.publish("http://127.0.0.1:8280/servicioLogin", this);
    }

    //login
    @WebMethod
    public DtUsuario ObtenerDtUsuario(@WebParam(name = "login") String login) {
        return Fabrica.getInstance().getIControladorUsuario().ObtenerDTUsuario(login);
    }

    @WebMethod
    public DtUsuario ObtenerDtUsuario_Correo(@WebParam(name = "login") String login) {
        return Fabrica.getInstance().getIControladorUsuario().ObtenerDTUsuario_Correo(login);
    }

}
