/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Interfaces;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import logica.Clases.Colaboracion;
import logica.Clases.Colaborador;
import logica.Clases.DtColaboraciones;
import logica.Clases.DtNickTitProp;
import logica.Clases.DtProponente;
import logica.Clases.DtSeguidor;
import logica.Clases.DtUsuario;
import logica.Clases.DtinfoColaborador;
import logica.Clases.DtinfoPropuesta;
import logica.Clases.Proponente;
import logica.Clases.Usuario;

/**
 *
 * @author Usuario
 */
public interface IControladorUsuario {

    public abstract boolean seguirUsuario(String nickUsu1, String nickUsu2) throws Exception;

    public abstract void copiarFoto(String foto, String nick);

    public abstract boolean dejarseguirUsuario(String nickUsu1, String nickUsu2) throws Exception;

    public abstract boolean AgregarUsuarioColaborador(String nickName, String nombre, String apellido, String correo, Calendar fechaN, String imagen, String password);

    public abstract boolean AgregarUsuarioProponente(String nickName, String nombre, String apellido, String correo, Calendar fechaN, String imagen, String direccion, String biografia, String sitioWeb, String password);

    public abstract Proponente ObtenerProponente(String nombreP);

    public abstract ArrayList<DtProponente> ListarProponentes();

    public abstract ArrayList<DtinfoColaborador> ListarColaboradores();

    public abstract ArrayList<DtinfoColaborador> BuscarColaborador(String nick);

    public abstract List<DtinfoPropuesta> verPropuestas();

    public abstract DtinfoColaborador verPerfil(String nombre);

    public abstract Map<String, Usuario> getUsuarios();

    public abstract java.util.List<DtUsuario> ListarProponentes2();

    public abstract Map<String, Colaboracion> getColaboraciones();

    public abstract Colaborador getColaborador();

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

    public abstract boolean AgregarUsuarioColaboradorDatosdePrueba(String nickName, String nombre, String apellido, String correo, Calendar fechaN, String imagen,String password);

    public abstract boolean AgregarUsuarioProponenteDatosdePrueba(String nickName, String nombre, String apellido, String correo, Calendar fechaN, String imagen, String direccion, String biografia, String sitioWeb,String password);

    public abstract void resetearColaborador();

    public abstract DtinfoColaborador getDtColaborador();

    public abstract List<DtSeguidor> MostrarUsuarios();

    public abstract List<DtSeguidor> MostrarMisSeguidos(String nick);

    public abstract DtNickTitProp obtenerNombreApellido(String nick) throws Exception;
    
    public abstract Usuario ObtenerUsuario(String nombreU);
}
