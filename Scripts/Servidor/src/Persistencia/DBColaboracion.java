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
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import logica.Clases.Colaboracion;
import logica.Clases.Colaborador;
import logica.Clases.Propuesta;
import logica.Clases.TipoRetorno;
import logica.Clases.Usuario;
import logica.Controladores.ControladorPropCat;
import logica.Controladores.ControladorUsuario;
import logica.Fabrica;
import logica.Interfaces.IControladorUsuario;
import logica.Interfaces.IPropCat;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DBColaboracion {

    private Connection conexion = new ConexionDB().getConexion();

    public boolean agregarColaboracion(boolean Entrada, float monto) {
        Fabrica fabrica = Fabrica.getInstance();
        IControladorUsuario CU = (ControladorUsuario) fabrica.getIControladorUsuario();
        IPropCat CPU = (ControladorPropCat) fabrica.getControladorPropCat();
        Calendar calendario = new GregorianCalendar();
        java.util.Date utilDate = new java.util.Date();
        utilDate = calendario.getTime();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
        List<Colaboracion> colaboraciones = CPU.getPropuesta().getColaboraciones();
        float TotalColaboracion = 0;
        for (int indice = 0; indice < colaboraciones.size(); indice++) {
            TotalColaboracion = TotalColaboracion + colaboraciones.get(indice).getMontoC();
        }
        try {
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO colaboracion " + "(TituloP, nickName, montoC, fechaRealiz, entradas) values(?,?,?,?,?)");
            statement.setString(1, CPU.getPropuesta().getTituloP());
            statement.setString(2, CU.getColaborador().getNickname());
            statement.setFloat(3, monto);
            statement.setTimestamp(4, sqlDate);
            if (Entrada == true) {
                statement.setInt(5, 1);
            } else {
                statement.setInt(5, 0);
            }
            statement.executeUpdate();
            statement.close();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean agregarestadocolaboracion() {
        Fabrica fabrica = Fabrica.getInstance();
        IControladorUsuario CU = (ControladorUsuario) fabrica.getIControladorUsuario();
        IPropCat CPU = (ControladorPropCat) fabrica.getControladorPropCat();
        Calendar calendario = new GregorianCalendar();
        java.util.Date utilDate = new java.util.Date();
        utilDate = calendario.getTime();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
        List<Colaboracion> colaboraciones = CPU.getPropuesta().getColaboraciones();
        float TotalColaboracion = 0;
        for (int indice = 0; indice < colaboraciones.size(); indice++) {
            TotalColaboracion = TotalColaboracion + colaboraciones.get(indice).getMontoC();
        }
        try {
            PreparedStatement statement = conexion.prepareStatement("UPDATE estadopropuesta set estActual = ? where estActual = 1 AND TituloP = '" + CPU.getPropuesta().getTituloP() + "'");
            statement.setInt(1, 0);
            statement.executeUpdate();
            statement.close();

            statement = conexion.prepareStatement("INSERT INTO estadopropuesta" + " (TituloP, FechaInicio, estActual, Estado) values (?,?,?,?)");
            statement.setString(1, CPU.getPropuesta().getTituloP());
            statement.setTimestamp(2, sqlDate);
            statement.setBoolean(3, true);
            if (TotalColaboracion < CPU.getPropuesta().getMontoTot()) {
                statement.setInt(4, 2);
            } else if (TotalColaboracion == CPU.getPropuesta().getMontoTot()) {
                statement.setInt(4, 3);
            }
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean agregarColaboracionDatosdePrueba(String TituloP, String nickName, float monto, Calendar fechaRealiz, boolean Entrada) {
        Fabrica fabrica = Fabrica.getInstance();
        IControladorUsuario CU = (ControladorUsuario) fabrica.getIControladorUsuario();
        IPropCat CPU = (ControladorPropCat) fabrica.getControladorPropCat();
        IControladorUsuario ICU = fabrica.getIControladorUsuario();

        java.util.Date dateR = (java.util.Date) fechaRealiz.getTime();
        java.sql.Timestamp dateRR = new java.sql.Timestamp(dateR.getTime());

//        List<Colaboracion> colaboraciones = CPU.getPropuesta().getColaboraciones();
//        float TotalColaboracion = 0;
//        for (int indice = 0; indice < colaboraciones.size(); indice++) {
//            TotalColaboracion = TotalColaboracion + colaboraciones.get(indice).getMontoC();
//        }
        try {
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO colaboracion " + "(TituloP, nickName, montoC, fechaRealiz, entradas) values(?,?,?,?,?)");
            statement.setString(1, TituloP);
            statement.setString(2, nickName);
            statement.setFloat(3, monto);
            statement.setTimestamp(4, dateRR);

            if (Entrada) {
                statement.setInt(5, 1);
            } else {
                statement.setInt(5, 0);
            }
            statement.executeUpdate();
            statement.close();

//                statement = conexion.prepareStatement("INSERT INTO estadopropuesta " + "(TituloP, FechaInicio, FechaFinal, Estado) values(?,?,?,?)");
//                statement.setString(1, CPU.getPropuesta().getTituloP());
//                statement.setDate(2, sqlDate);
//                if(TotalColaboracion < CPU.getPropuesta().getMontoTot()){
//                   statement.setInt(4, 4);
//                }
//                else if(TotalColaboracion == CPU.getPropuesta().getMontoTot()){
//                    statement.setInt(4, 6);
//                }
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void CargarColaboraciones() {
        try {

            PreparedStatement stat = conexion.prepareStatement("SELECT * FROM colaboracion");
            ResultSet rs = stat.executeQuery();
            Fabrica fabrica = Fabrica.getInstance();
            IPropCat IPC = fabrica.getControladorPropCat();
            IControladorUsuario ICU = fabrica.getIControladorUsuario();
            while (rs.next()) {
                java.util.Date fecha = rs.getTimestamp("fechaRealiz");
                Calendar fechaRC = Calendar.getInstance();
                fechaRC.setTime(fecha);
                Colaboracion colaboracion = new Colaboracion(null, rs.getFloat("montoC"), fechaRC, rs.getBoolean("entradas"), null);
                Map<String, Usuario> usuarios = ICU.getUsuarios();
                Set set = usuarios.entrySet();
                Iterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    Map.Entry mentry = (Map.Entry) iterator.next();
                    if (mentry.getValue() instanceof Colaborador) {
                        if (((Colaborador) mentry.getValue()).getNickname().compareTo(rs.getString("nickName")) == 0) {
                            colaboracion.setUColaborador(((Colaborador) mentry.getValue()));
                            ((Colaborador) mentry.getValue()).setColaboraciones(colaboracion);
                        }
                    }
                }
                Map<String, Propuesta> propuestas = IPC.getPropuestas();
                set = propuestas.entrySet();
                iterator = set.iterator();
                while (iterator.hasNext()) {
                    Map.Entry mentry = (Map.Entry) iterator.next();
                    if (((Propuesta) mentry.getValue()).getTituloP().compareTo(rs.getString("TituloP")) == 0) {
                        colaboracion.setPropuesta((Propuesta) mentry.getValue());
                        ((Propuesta) mentry.getValue()).setColaboraciones(colaboracion);
                    }
                }
            }
            stat.close();
            rs.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean eliminarColaboracion(String TituloP, String nickName) throws SQLException {
        PreparedStatement sql = conexion.prepareStatement("DELETE FROM colaboracion WHERE colaboracion.TituloP = '" + TituloP + "' AND colaboracion.nickName='" + nickName + "';");
        sql.executeUpdate();
        sql.close();
        return true;
    }
}
