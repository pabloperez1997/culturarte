/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Interfaces;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import logica.Clases.Categoria;
import logica.Clases.Colaborador;
import logica.Clases.DTListaPropuestasR;
import logica.Clases.DtColaboraciones;
import logica.Clases.DtConsultaPropuesta;
import logica.Clases.DtConsultaPropuesta2;
import logica.Clases.DtinfoColaborador;
import logica.Clases.DtinfoPropuesta;
import logica.Clases.DtNickTitProp;
import logica.Clases.DtPropuestaWeb;
import logica.Clases.EstadoPropuesta;
import logica.Clases.Proponente;
import logica.Clases.Propuesta;
import logica.Clases.TipoE;
import logica.Clases.TipoRetorno;
import logica.Clases.Usuario;
import logica.Clases.DataImagen;
import logica.Clases.DtComentarios;
import logica.Clases.DtPago;
import logica.Clases.DtRecomendacionProp;

/**
 *
 * @author Santiago.S
 */
public interface IPropCat {

    public abstract List<DtNickTitProp> listarPropuestaC();

    public abstract DtConsultaPropuesta SeleccionarPropuesta(String titulo, String proponente);

    public abstract Map<String, Propuesta> getPropuestas();

    public abstract List<DtinfoPropuesta> DarPropuestasCol(Colaborador c);

    public abstract boolean seleccionarUC(String nombreP, String tipoEsp) throws Exception;

    public abstract boolean crearPropuesta(String tituloP, String descripcion, String lugar, DataImagen imagen, String fecha, float montoE, float montoTot, String retorno) throws Exception;

    public abstract List<String> ListarCategorias();

    public abstract List<DtNickTitProp> listarPropuestas();

    public abstract List<DtNickTitProp> listarPropuestasR();

    public abstract DtinfoPropuesta SeleccionarPropuestaR(String titulo);

    public abstract List<DtinfoColaborador> ListarColaboradores(String titulo);

    public abstract boolean agregarColaboracion(boolean Entrada, Float monto) throws Exception;

    public abstract Propuesta getPropuesta();

    public abstract void CargarPropuestas();

    public abstract boolean crearPropuestaDatosdePrueba(String tituloP, String descripcion, Categoria cat, Calendar fecha, String lugar, float montoE, float montoTot, TipoRetorno retorno, Proponente p, String imagen);

    public abstract boolean crearCategoriaDatosdePrueba(String nomCat, String nomPadre);

    public abstract Categoria ObtenerCategoria(String nomCat);

    public abstract boolean agregarColaboracionDatosdePrueba(String TituloP, String nickName, float montoC, Calendar fechaRealiz, boolean Entrada);

    public abstract boolean nuevoEstadoPropuestaDatosdePrueba(String TituloP, TipoE estado, Calendar fecha);

    public abstract void altaCategoria(String nombre, String padre) throws Exception;

    public abstract Map<String, Categoria> getCategorias();

    public abstract List<DtinfoPropuesta> ListarPropuestasDeProponenteX(String nick) throws Exception;

    public abstract void CargarColaboraciones();

    public abstract void LimpiarPropCat();

    public abstract void comprobarBaseCat();

    public abstract void copiarFoto(String foto, String tituloP);

    public abstract void setEstadoActualDatosDePrueba(String titulo, EstadoPropuesta estadoActual);

    public abstract List<DtColaboraciones> listarColaboraciones(String titulo) throws Exception;

    public abstract boolean eliminarColaboracion(String titulo, String nick) throws Exception;

    public abstract List<DtConsultaPropuesta2> ListaColaboradoresProp(String titulo) throws Exception;

    public abstract boolean ActualizarDatosPropuesta(DtinfoPropuesta dtp);

    public abstract List<DtNickTitProp> ListaEvaluarPropuesta();

    public abstract boolean EvaluarPropuesta(String titulo, TipoE tipo) throws Exception;

    public abstract Usuario CargarFavoritas(Usuario usu, List<String> usufav);

    public abstract List<DtinfoPropuesta> ListarPropuestasNoIngresadas(String nick);

    public abstract List<DtinfoPropuesta> ListarPropuestaNOI();

    public abstract List<DtinfoPropuesta> ListarPropuesta();

    public abstract boolean AgregarFavorita(String titulo, String nick);

    public abstract void EvaluarEstadosPropuestas();

    public abstract List<DtPropuestaWeb> ListarPropuestasWeb(TipoE estado);

    public abstract List<DtinfoPropuesta> ListarPropuestasCategoria(String nombrecat);

    public abstract List<DtNickTitProp> listarPropuestasComentar();

    public abstract void ComentarPropuesta(String TituloP, String nickColab, String texto) throws Exception;

    public abstract boolean ExtenderFinanciacion(String Titulo);

    public abstract List<DtNickTitProp> ListarPropuestasX_DeProponenteX(String nick);

    public abstract void CargarComentarios();

    public abstract List<DtNickTitProp> ListarPropuestasCancelar(String proponente);

    public abstract boolean CancelarPropuesta(String titulo, String nick) throws Exception;

    public abstract List<DTListaPropuestasR> listarPropuestasRWEB();

    public abstract void DesactivarPropuesta(Propuesta prop);

    public abstract List<DtConsultaPropuesta> getDtPropuestas() throws Exception;

    public byte[] retornarImagen(String titulo) throws IOException;

    public abstract List<DtComentarios> retornarComantarios(String TituloP);

    public abstract void CargarPagosTarjetaDP(String nick, String titulo, String tarjeta, String numero, Calendar fecha, int cvc, String titular);

    public abstract void CargarPagosTransfPayDP(String nick, String titulo, String nomBanco, String numCuenta, String nomTitular);

    public abstract DtPago ObtenerPago(String nick, String titulo);

    public abstract void CargarPagosTarjeta(String nick, String titulo, String tarjeta, String numero, String fecha, int cvc, String titular);

    public abstract List<String> ListarPropuestasDe(String Autor);

    public abstract DtColaboraciones ObtenerColaboracion(String nick, String titulo);

    public abstract List<DtRecomendacionProp> recomendacionDePropuestas(String nick);
}
