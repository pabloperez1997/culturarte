/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Clases;

/**
 *
 * @author PabloDesk
 */
public class Comentario {
    private String TituloP;
    private String nickColab;
    private String Texto;
    
    public Comentario(String TituloP, String nickColab, String Texto) {
        this.TituloP = TituloP;
        this.nickColab = nickColab;
        this.Texto = Texto;
    }
    
    public String getTituloP() {
        return TituloP;
    }

    public void setTituloP(String TituloP) {
        this.TituloP = TituloP;
    }

    public String getNickColab() {
        return nickColab;
    }

    public void setNickColab(String nickColab) {
        this.nickColab = nickColab;
    }

    public String getTexto() {
        return Texto;
    }

    public void setTexto(String Texto) {
        this.Texto = Texto;
    }

    
    
}
