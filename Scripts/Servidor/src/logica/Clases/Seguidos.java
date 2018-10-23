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
public class Seguidos {
    private String Seguidor;
    private String Seguido;

    public Seguidos(String seguidor,String seguido){
        this.Seguidor=seguidor;
        this.Seguido=seguido;
    }
    public String getSeguidor() {
        return Seguidor;
    }

    public String getSeguido() {
        return Seguido;
    }
    
    
}
