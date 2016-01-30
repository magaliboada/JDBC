/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajes;

/**
 *
 * @author Magali
 */
public class Mensaje {
    
    private int idMensaje;
    private String texto;
    private String emisor;
    private String receptor;
    private String fecha;

    public Mensaje() {
        this.idMensaje = 0;
        this.texto = "";
        this.emisor = "";
        this.receptor = "";
        this.fecha = "";        
        
    }

    public Mensaje(int idMensaje, String texto, String emisor, String receptor, String fecha) {
        this.idMensaje = idMensaje;
        this.texto = texto;
        this.emisor = emisor;
        this.receptor = receptor;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "idMensaje=" + idMensaje + ", texto=" + texto + ", emisor=" + emisor + ", receptor=" + receptor + ", fecha=" + fecha + '}';
    }
    
    

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
