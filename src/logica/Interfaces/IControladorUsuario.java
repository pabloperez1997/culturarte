/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Interfaces;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import logica.Clases.Colaboracion;
import logica.Clases.Colaborador;
import logica.Clases.DtColaboraciones;
import logica.Clases.DtNickTitProp;
import logica.Clases.DtSeguidor;
import logica.Clases.DtUsuario;
import logica.Clases.DtinfoColaborador;
import logica.Clases.DtinfoPropuesta;
import logica.Clases.Proponente;
import logica.Clases.Usuario;
import logica.Clases.DataImagen;
import logica.Clases.DtBasicoUsu;
import logica.Clases.DtDesactivado;
import logica.Clases.DtRegistro;
import logica.Clases.Propuesta;

/**
 *
 * @author Usuario
 */
public interface IControladorUsuario {

    public abstract boolean seguirUsuario(String nickUsu1, String nickUsu2) throws Exception;

    public abstract void copiarFoto(String foto, String nick);

    public abstract boolean dejarseguirUsuario(String nickUsu1, String nickUsu2) throws Exception;

    public abstract boolean AgregarUsuarioColaborador(String nickName, String nombre, String apellido, String correo, Calendar fechaN, DataImagen imagen, String password);

    public abstract boolean AgregarUsuarioProponente(String nickName, String nombre, String apellido, String correo, Calendar fechaN, DataImagen imagen, String direccion, String biografia, String sitioWeb, String password);

    public abstract Proponente ObtenerProponente(String nombreP);

    public abstract ArrayList<DtUsuario> ListarProponentes();

    public abstract ArrayList<DtinfoColaborador> ListarColaboradores();

    public abstract ArrayList<DtinfoColaborador> BuscarColaborador(String nick);

    public abstract List<DtinfoPropuesta> verPropuestas(String nick);

    public abstract DtinfoColaborador verPerfil(String nombre);

    public abstract Map<String, Usuario> getUsuarios();

    public abstract java.util.List<DtUsuario> ListarProponentes2();

    public abstract Map<String, Colaboracion> getColaboraciones();

    public abstract Colaborador getColaborador();

    public abstract DtinfoColaborador getDtColaborador(String nick);

    public abstract DtinfoColaborador SeleccionarColaborador(String nickName);

    public abstract void CargarUsuarios();

    public abstract List<DtColaboraciones> ListarColaboraciones(String nickname);

    public abstract void LimpiarUsuarios();

    public abstract ArrayList<DtUsuario> ListarUsuarios();

    public abstract boolean limpiarBaseDeDatos();

    public abstract void borrarProponente(String nickProp);

    public abstract void borrarColaborador(String nickColab);

    public abstract Colaborador ObtenerColaborador(String nombreC);

    public abstract void eliminarCategorias();

    public abstract boolean AgregarUsuarioColaboradorDatosdePrueba(String nickName, String nombre, String apellido, String correo, Calendar fechaN, String imagen, String password);

    public abstract boolean AgregarUsuarioProponenteDatosdePrueba(String nickName, String nombre, String apellido, String correo, Calendar fechaN, String imagen, String direccion, String biografia, String sitioWeb, String password);

    public abstract void resetearColaborador();

    public abstract DtinfoColaborador getDtColaborador();

    public abstract List<DtSeguidor> MostrarUsuarios();

    public abstract List<DtSeguidor> MostrarMisSeguidos(String nick);

    public abstract DtNickTitProp obtenerNombreApellido(String nick) throws Exception;

    public abstract DtUsuario ObtenerDTUsuario(String nombreU);

    public abstract List<DtUsuario> ObtenerSeguidos(String nick);

    public abstract List<DtUsuario> ObtenerSeguidores(String nick);

    public abstract void CargarFavoritas();

    public abstract List<DtinfoPropuesta> obtenerfavoritas(String nick);

    public abstract DtUsuario ObtenerDTUsuario_Correo(String correoU);

    public abstract void agregarfavorita(String nick, Propuesta p);

    public abstract List<DtColaboraciones> getMontoColaboracion(String Colaborador);

    public abstract boolean DesactivarProponente(String nickProp) throws Exception;

    public abstract ArrayList<DtUsuario> ListarUsuariosRanking();

    public abstract boolean agregarRegistro(String ip, String navegador, String sitio, String so);

    public abstract List<DtRegistro> getListaRegistro();

    public byte[] retornarImagen(String nick) throws IOException;

    public abstract List<DtBasicoUsu> ListarProponentesDesactivados();

    public abstract String leerPropiedades(String caso);
    
    public abstract boolean existeNombreUser(String nick);
    public abstract boolean existeCorreoUser(String correo);
    
}
