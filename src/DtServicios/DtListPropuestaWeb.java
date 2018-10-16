/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DtServicios;

import java.util.List;
import logica.Clases.DtPropuestaWeb;

/**
 *
 * @author Martin
 */
public class DtListPropuestaWeb {

    List<DtPropuestaWeb> publicadas;
    List<DtPropuestaWeb> financiadas;
    List<DtPropuestaWeb> enFinanciacion;
    List<DtPropuestaWeb> noFinanciadas;
    List<DtPropuestaWeb> canceladas;

    public DtListPropuestaWeb(List<DtPropuestaWeb> publicada, List<DtPropuestaWeb> financiada, List<DtPropuestaWeb> enfinanciacion, List<DtPropuestaWeb> nofinanciada, List<DtPropuestaWeb> cancelada) {
        this.publicadas = publicada;
        this.financiadas = financiada;
        this.enFinanciacion = enfinanciacion;
        this.noFinanciadas = nofinanciada;
        this.canceladas = cancelada;
    }
}
