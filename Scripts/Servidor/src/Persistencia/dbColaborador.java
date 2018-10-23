/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import logica.Clases.Colaborador;

/**
 *
 * @author Lucas
 */
public class dbColaborador {

    private Connection conexion = new ConexionDB().getConexion();

    public boolean agregarColaborador(Colaborador p) {
        try {
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO colaborador "
                    + "(NickName, nombre, apellido,correo,fechaN,imagen) values(?,?,?,?,?,?)");
            Calendar a = p.getFechaN();

            statement.setString(1, p.getNickname());
            statement.setString(2, p.getNombre());
            statement.setString(3, p.getApellido());
            statement.setString(4, p.getCorreo());
            statement.setDate(5, (Date) a.getTime());
            statement.setString(6, p.getImagen());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
