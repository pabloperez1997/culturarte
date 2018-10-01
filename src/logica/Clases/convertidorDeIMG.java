/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Lucas
 */
public class convertidorDeIMG {

    public DataImagen convertirStringAImg(String url, String nick) throws FileNotFoundException, IOException {
        File img = new File(url);
        FileInputStream ficheroStream = new FileInputStream(img);
        byte[] contenido = IOUtils.toByteArray(ficheroStream);
        DataImagen imagen = new DataImagen(contenido,nick,FilenameUtils.getExtension(url));
        imagen.setStream(contenido);
        imagen.setExtensionArchivo(FilenameUtils.getExtension(url));
        imagen.setNombreArchivo(nick);
        return imagen;
    }
}
