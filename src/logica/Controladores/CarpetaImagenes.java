/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Controladores;

/**
 *
 * @author Martin
 */
public class CarpetaImagenes {

    public static CarpetaImagenes instance;

    public String carpetafPropuestas;
    public String carpetafPerfiles;
    public String carpetafDatosDP;

    private CarpetaImagenes() {

        this.carpetafPropuestas = (String) System.getProperty("user.dir") + "\\fPropuestas\\";
        this.carpetafDatosDP = (String) System.getProperty("user.dir") + "\\fotosdp\\";
        this.carpetafPerfiles = (String) System.getProperty("user.dir") + "\\fPerfiles\\";

    }

    public static CarpetaImagenes getInstance() {
        if (instance != null) {
            return instance;
        } else {
            return new CarpetaImagenes();
        }
    }
}
