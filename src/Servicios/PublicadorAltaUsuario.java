/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

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
public class PublicadorAltaUsuario {

    private Endpoint endpoint = null;

    public void publicarConsultaPropuesta() {
        endpoint = Endpoint.publish("http://127.0.0.1:8280/servicioAltaUsuario", this);
    }

    @WebMethod
    public boolean AgregarUsuarioProponente(@WebParam(name = "nick") String nick, @WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "correo") String correo, @WebParam(name = "cal") Calendar cal, @WebParam(name = "imagen") DataImagen imagen, @WebParam(name = "direccion") String direccion, @WebParam(name = "biografia") String biografia, @WebParam(name = "sitio") String sitio, @WebParam(name = "hash") String hash) {
        return Fabrica.getInstance().getIControladorUsuario().AgregarUsuarioProponente(nick, nombre, apellido, correo, cal, imagen, direccion, biografia, sitio, hash);
    }

    @WebMethod
    public boolean AgregarUsuarioColaborador(@WebParam(name = "nick") String nick, @WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "correo") String correo, @WebParam(name = "cal") Calendar cal, @WebParam(name = "imagen") DataImagen imagen, @WebParam(name = "direccion") String direccion) {
        return Fabrica.getInstance().getIControladorUsuario().AgregarUsuarioColaborador(nick, nombre, apellido, correo, cal, imagen, direccion);
    }
}
