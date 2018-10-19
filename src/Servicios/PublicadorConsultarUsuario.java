/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import DtServicios.DtListUsuario;
import DtServicios.DtListInfoPropuesta;
import DtServicios.DtListColaboraciones;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import logica.Fabrica;
import logica.Clases.DtinfoColaborador;

/**
 *
 * @author Martin
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PublicadorConsultarUsuario {

    private Endpoint endpoint = null;

    public void publicarConsultarUsuario(String URL) {
        endpoint = Endpoint.publish(URL, this);
    }

    @WebMethod
    public DtListInfoPropuesta ListarPropuestasNoIngresadas(@WebParam(name = "nickname") String nickname) {
        return new DtListInfoPropuesta(Fabrica.getInstance().getControladorPropCat().ListarPropuestasNoIngresadas(nickname));
    }

    @WebMethod
    public DtListInfoPropuesta ListarPropuestasDeProponenteX(@WebParam(name = "nickname") String nickname) throws Exception {
        return new DtListInfoPropuesta(Fabrica.getInstance().getControladorPropCat().ListarPropuestasDeProponenteX(nickname));
    }

    @WebMethod
    public DtListInfoPropuesta VerPropuestas(@WebParam(name = "nickname") String nickname) {
        return new DtListInfoPropuesta(Fabrica.getInstance().getIControladorUsuario().verPropuestas(nickname));
    }

    @WebMethod
    public DtinfoColaborador getDtColaborador(@WebParam(name = "nickname") String nickname) {
        return Fabrica.getInstance().getIControladorUsuario().getDtColaborador(nickname);
    }

    @WebMethod
    public DtListColaboraciones getMontoColaboracion(@WebParam(name = "colaborador") String colaborador) {
        return new DtListColaboraciones(Fabrica.getInstance().getIControladorUsuario().getMontoColaboracion(colaborador));
    }

    @WebMethod
    public DtListUsuario ObtenerSeguidos(@WebParam(name = "nickname") String nickname) {
        return new DtListUsuario(Fabrica.getInstance().getIControladorUsuario().ObtenerSeguidos(nickname));
    }

    @WebMethod
    public DtListUsuario ObtenerSeguidores(@WebParam(name = "nickname") String nickname) {
        return new DtListUsuario(Fabrica.getInstance().getIControladorUsuario().ObtenerSeguidores(nickname));
    }

    @WebMethod
    public DtListInfoPropuesta ObtenerFavoritas(@WebParam(name = "nickname") String nickname) {
        return new DtListInfoPropuesta(Fabrica.getInstance().getIControladorUsuario().obtenerfavoritas(nickname));
    }

    @WebMethod
    public boolean DesactivarProponente(@WebParam(name = "nick") String nick) throws Exception {
        return Fabrica.getInstance().getIControladorUsuario().DesactivarProponente(nick);
    }
}
