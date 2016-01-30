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
public class Historial {
    private String tipoConsulta;
    private String usuario;
    private String fecha;

    public Historial(String tipoConsulta, String usuario, String fecha) {
        this.tipoConsulta = tipoConsulta;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public Historial() {
    }
    
    public String convertirHora(String hora1)
    {
        String hora;
        hora = ""+hora1.charAt(6)+hora1.charAt(7)+"/"+hora1.charAt(4)+hora1.charAt(5)+"/"+hora1.charAt(0)+hora1.charAt(1)+hora1.charAt(2)+hora1.charAt(3)+" "+hora1.charAt(8)+hora1.charAt(9)+":"+hora1.charAt(10)+hora1.charAt(11);

        return hora;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
    
    
}
