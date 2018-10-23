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

    private Colaborador colaborador;
    private String Texto;

    public Comentario(Colaborador nickColab, String Texto) {
        this.colaborador = nickColab;
        this.Texto = Texto;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador Colab) {
        this.colaborador = Colab;
    }

    public String getTexto() {
        return Texto;
    }

    public void setTexto(String Texto) {
        this.Texto = Texto;
    }
}
