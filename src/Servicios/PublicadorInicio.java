/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import logica.Clases.TipoE;
import logica.Fabrica;
import logica.Clases.DtPropuestaWeb;
import DtServicios.DtListPropuestaWeb;

/**
 *
 * @author Martin
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PublicadorInicio {

    private Endpoint endpoint = null;

    public void publicarInicio() {
        endpoint = Endpoint.publish("http://127.0.0.1:8280/servicioInicio", this);
    }

    @WebMethod
    public DtListPropuestaWeb ListarPropuestasWeb() {
        List<DtPropuestaWeb> pub = Fabrica.getInstance().getControladorPropCat().ListarPropuestasWeb(TipoE.Publicada);
        List<DtPropuestaWeb> enF = Fabrica.getInstance().getControladorPropCat().ListarPropuestasWeb(TipoE.enFinanciacion);
        List<DtPropuestaWeb> noF = Fabrica.getInstance().getControladorPropCat().ListarPropuestasWeb(TipoE.noFinanciada);
        List<DtPropuestaWeb> fin = Fabrica.getInstance().getControladorPropCat().ListarPropuestasWeb(TipoE.Financiada);
        List<DtPropuestaWeb> cancel = Fabrica.getInstance().getControladorPropCat().ListarPropuestasWeb(TipoE.Cancelada);

        return new DtListPropuestaWeb(pub, fin, enF, noF, cancel);
    }
}
