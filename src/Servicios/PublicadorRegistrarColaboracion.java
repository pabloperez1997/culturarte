/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import DtServicios.DtListPropuestasRWEB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import logica.Clases.DtinfoColaborador;
import logica.Clases.DtinfoPropuesta;
import logica.Fabrica;

/**
 *
 * @author Martin
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PublicadorRegistrarColaboracion {

    private Endpoint endpoint = null;

    public void publicarRegistrarColaboracion(String URL) {
        endpoint = Endpoint.publish(URL, this);
    }

    @WebMethod
    public DtListPropuestasRWEB listarPropuestasRWEB() {
        return new DtListPropuestasRWEB(Fabrica.getInstance().getControladorPropCat().listarPropuestasRWEB());
    }

    @WebMethod
    public DtinfoPropuesta SeleccionarPropuestaR(@WebParam(name = "opcion") String opcion) {
        return Fabrica.getInstance().getControladorPropCat().SeleccionarPropuestaR(opcion);
    }

    @WebMethod
    public DtinfoColaborador SeleccionarColaborador(@WebParam(name = "nickName") String nickName) {
        return Fabrica.getInstance().getIControladorUsuario().SeleccionarColaborador(nickName);
    }

    @WebMethod
    public boolean AgregarColaboracion(@WebParam(name = "retorno") boolean retorno, @WebParam(name = "monto_final") float monto_final) throws Exception {
        return Fabrica.getInstance().getControladorPropCat().agregarColaboracion(retorno, monto_final);
    }
    
    @WebMethod
    public void CargarPagosTarjeta(@WebParam(name = "nick") String nick, @WebParam(name = "Titulo") String Titulo, @WebParam(name = "tarjeta") String tarjeta,@WebParam(name = "numero") String numero, @WebParam(name = "fecha") String fecha,@WebParam(name = "cvc") int cvc, @WebParam(name = "titular") String titular)  throws Exception {
        Fabrica.getInstance().getControladorPropCat().CargarPagosTarjeta(nick, Titulo, tarjeta, numero, fecha, cvc, titular);
    }
    
         @WebMethod
    public void CargarPagosTransfPayDP(@WebParam(name = "nick") String nick, @WebParam(name = "Titulo") String Titulo, @WebParam(name = "nomBanco") String banco,@WebParam(name = "numCuenta") String numCuenta, @WebParam(name = "nomTitular") String Titular)  throws Exception {
        Fabrica.getInstance().getControladorPropCat().CargarPagosTransfPayDP(nick,Titulo, banco, numCuenta,Titular);
    }
}
