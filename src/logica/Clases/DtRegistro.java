/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

/**
 *
 * @author gabri
 */
public class DtRegistro {
    private String ip;
    private String Sitio;
    private String navegador;
    private String SO;
    
    public DtRegistro(String IP,String sitio,String Navegador,String so){
        this.SO=so;
        this.Sitio=sitio;
        this.ip=IP;
        this.navegador=Navegador;
    }

    public String getIp() {
        return ip;
    }

    public String getSitio() {
        return Sitio;
    }

    public String getNavegador() {
        return navegador;
    }

    public String getSO() {
        return SO;
    }
}
