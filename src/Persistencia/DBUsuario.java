/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.time.LocalDate;
import logica.Clases.Colaborador;
import logica.Clases.Proponente;
import java.util.Date;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Clases.Colaboracion;
import logica.Clases.Propuesta;
import logica.Clases.Usuario;
import logica.Fabrica;

/**
 *
 * @author Usuario
 */
public class DBUsuario {

    private Connection conexion = new ConexionDB().getConexion();

    public boolean agregarColaborador(Colaborador p) {
        try {
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO usuario " + "(nickName, nombre, apellido,correo,fechaN,imagen,direccion,biografia,sitioWeb,esProponente, contrasenia) values(?,?,?,?,?,?,?,?,?,?,?)");
            Calendar calen = p.getFechaN();
            Date fechaN = (Date) calen.getTime();
            java.sql.Date sDate = new java.sql.Date(fechaN.getTime());
            statement.setString(1, p.getNickname());
            statement.setString(2, p.getNombre());
            statement.setString(3, p.getApellido());
            statement.setString(4, p.getCorreo());
            statement.setDate(5, sDate);
            statement.setString(6, p.getImagen());
            statement.setString(7, null);
            statement.setString(8, null);
            statement.setString(9, null);
            statement.setBoolean(10, false);
            statement.setString(11,p.getPassword());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean agregarProponente(Proponente p) {
        try {
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO usuario " + "(nickName, nombre, apellido,correo,fechaN,imagen,direccion,biografia,sitioWeb,esProponente,contrasenia) values(?,?,?,?,?,?,?,?,?,?,?)");
            Calendar calendar = p.getFechaN();
            Calendar calen = p.getFechaN();
            Date fechaN = (Date) calen.getTime();
            java.sql.Date sDate = new java.sql.Date(fechaN.getTime());
            statement.setString(1, p.getNickname());
            statement.setString(2, p.getNombre());
            statement.setString(3, p.getApellido());
            statement.setString(4, p.getCorreo());
            statement.setDate(5, sDate);
            statement.setString(6, p.getImagen());
            statement.setString(7, p.getDireccion());
            statement.setString(8, p.getBiografia());
            statement.setString(9, p.getsitioweb());
            statement.setBoolean(10, true);
            statement.setString(11,p.getPassword());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean seguirUsuario(String nickUsu1, String nickUsu2) {
        try {
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO ususigueusu" + "(nickSeguidor, nickSeguido) values(?,?)");
            statement.setString(1, nickUsu1);
            statement.setString(2, nickUsu2);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean dejarseguirUsuario(String nickUsu1, String nickUsu2) {
        try {
            PreparedStatement statement = conexion.prepareStatement("DELETE FROM ususigueusu WHERE nickSeguidor =? AND nickSeguido=?");
            statement.setString(1, nickUsu1);
            statement.setString(2, nickUsu2);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Map<String, Usuario> cargarUsuarios() {
        try {
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM usuario");
            ResultSet st = statement.executeQuery();
            Map<String, Usuario> usuarios = new HashMap<>();
            while (st.next()) {
                String nick = st.getString(1);
                String nombre = st.getString(2);
                String apellido = st.getString(3);
                String correo = st.getString(4);
                Date fecha = st.getDate(5);
                Calendar c = Calendar.getInstance();
                c.setTime(fecha);
                String img = st.getString(6);
                String dir = st.getString(7);
                String biog = st.getString(8);
                String web = st.getString(9);
                String pass= st.getString(11);
                if (st.getInt(10) == 1) {
                    Proponente p = new Proponente(biog, dir, web, nick, nombre, apellido, correo, c, img,pass);
                    usuarios.put(nick, p);
                } else {
                    Colaborador col = new Colaborador(nick, nombre, apellido, correo, c, img,pass);
                    usuarios.put(nick, col);
                }
            }
            statement.close();
            st.close();
            return usuarios;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public void Cargarseguidos(Usuario usu) {
        try {
            PreparedStatement statement = conexion.prepareStatement("SELECT nickSeguido FROM ususigueusu WHERE nickSeguidor = '" + usu.getNickname() + "'");
            ResultSet st = statement.executeQuery();

            while (st.next()) {

                Usuario seguido = Fabrica.getInstance().getIControladorUsuario().getUsuarios().get(st.getString("nickSeguido"));

                usu.setSeguido(seguido);

            }
            statement.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean limpiarBD() {

        PreparedStatement statement;

        try {
            statement = conexion.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
            statement.executeUpdate();
            statement.close();

            statement = conexion.prepareStatement("TRUNCATE TABLE estadopropuesta");
            statement.executeUpdate();
            statement.close();

            statement = conexion.prepareStatement("TRUNCATE TABLE ususigueusu");
            statement.executeUpdate();
            statement.close();

            statement = conexion.prepareStatement("TRUNCATE TABLE colaboracion");
            statement.executeUpdate();
            statement.close();

            statement = conexion.prepareStatement("TRUNCATE TABLE propuesta");
            statement.executeUpdate();
            statement.close();

            statement = conexion.prepareStatement("TRUNCATE TABLE categoria");
            statement.executeUpdate();
            statement.close();

            statement = conexion.prepareStatement("TRUNCATE TABLE favoritas");
            statement.executeUpdate();
            statement.close();
            
            statement = conexion.prepareStatement("TRUNCATE TABLE comentarios");
            statement.executeUpdate();
            statement.close();
            
            statement = conexion.prepareStatement("TRUNCATE TABLE usuario");
            statement.executeUpdate();
            statement.close();

            statement = conexion.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
            statement.executeUpdate();
            statement.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(DBUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    public List<String> CargarFavoritas(String nick){
        List<String> favoritas=new ArrayList<>();
        try {
           PreparedStatement statement = conexion.prepareStatement("SELECT * FROM favoritas WHERE Usuario='"+nick+"'");
           ResultSet st = statement.executeQuery();

            while (st.next()) {
                favoritas.add(st.getString("Propuesta"));
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return favoritas;
    }
}
