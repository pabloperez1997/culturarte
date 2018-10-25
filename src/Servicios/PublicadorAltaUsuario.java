/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    public void publicarAltaUsuario(String URL) {
        endpoint = Endpoint.publish(URL, this);
    }

    @WebMethod
    public boolean AgregarUsuarioProponente(@WebParam(name = "nick") String nick, @WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "correo") String correo, @WebParam(name = "fecha") String fecha, @WebParam(name = "direccion") String direccion, @WebParam(name = "biografia") String biografia, @WebParam(name = "sitio") String sitio, @WebParam(name = "hash") String hash) {
        return Fabrica.getInstance().getIControladorUsuario().AgregarUsuarioProponente(nick, nombre, apellido, correo, ParseFecha(fecha), null, direccion, biografia, sitio, hash);
    }

    @WebMethod
    public boolean AgregarUsuarioColaborador(@WebParam(name = "nick") String nick, @WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "correo") String correo, @WebParam(name = "fecha") String fecha, @WebParam(name = "hash") String hash) {
        return Fabrica.getInstance().getIControladorUsuario().AgregarUsuarioColaborador(nick, nombre, apellido, correo, ParseFecha(fecha), null, hash);
    }

    public GregorianCalendar ParseFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = new Date();
        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException ex) {
            System.out.println(ex);
        }

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(fechaDate);
        return cal;
    }
}
