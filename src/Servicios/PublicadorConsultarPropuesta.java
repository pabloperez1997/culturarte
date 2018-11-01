/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import DtServicios.DtListComentarios;
import DtServicios.DtListConsultaPropuesta;
import DtServicios.DtListNickTitProp;
import DtServicios.DtListConsultaPropuesta2;
import DtServicios.DtListInfoPropuesta;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
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

    public void publicarConsultaPropuesta(String URL) {
        endpoint = Endpoint.publish(URL, this);
    }

    @WebMethod
    public DtListNickTitProp ListarPropuestas() {
        return new DtListNickTitProp(Fabrica.getInstance().getControladorPropCat().listarPropuestas());
    }

    @WebMethod
    public DtConsultaPropuesta SeleccionarPropuesta(@WebParam(name = "titulo") String titulo, @WebParam(name = "nickProp") String nickProp) {
        return Fabrica.getInstance().getControladorPropCat().SeleccionarPropuesta(titulo, nickProp);
    }

    @WebMethod
    public DtConsultaPropuesta SeleccionarPropuesta2(@WebParam(name = "titulo") String titulo) {
        return Fabrica.getInstance().getControladorPropCat().SeleccionarPropuesta(titulo, null);
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

    @WebMethod
    public DtListConsultaPropuesta getDtPropuestas() throws Exception {
        return new DtListConsultaPropuesta(Fabrica.getInstance().getControladorPropCat().getDtPropuestas());
    }

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

    @WebMethod
    public DtListInfoPropuesta ListarPropuestasNoI() {
        return new DtListInfoPropuesta(Fabrica.getInstance().getControladorPropCat().ListarPropuestaNOI());
    }

    @WebMethod
    public byte[] retornarImagen(@WebParam(name = "titulo") String titulo) throws Exception {
        return Fabrica.getInstance().getControladorPropCat().retornarImagen(titulo);
    }
    
      @WebMethod
    public DtListComentarios listarComentarios(@WebParam(name = "TituloP") String titulo) throws Exception {
        return new DtListComentarios(Fabrica.getInstance().getControladorPropCat().retornarComantarios(titulo));
    }
}
