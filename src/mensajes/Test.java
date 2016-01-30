/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajes;

import java.util.List;

/**
 *
 * @author Magali
 */
public class Test {
    public static void main(String[] args) {
		MensajesJDBC db = new MensajesJDBC();
		
		// Conectar con la base de datos
		if (!db.openConnection()) {
			System.err.println("ERROR CONEXION");
			System.exit(-1);
		}
		System.out.println("Conectado");

		// Obtener un listado de los usuarios
                /*
		List<Usuario> usuarios = db.getUsuario();
		System.out.println("Usuarios: ");
		for (Usuario s : usuarios) {
			System.out.println(s.getUsuario() + " : " + s.getNombre() + " : " + s.getPassword());
		}
		System.out.println();
                */
		
                //Obtener un lsitado de los mensajes
                /*
                List<Mensaje> mensajes = db.getMensajes();
		System.out.println("Usuarios: ");
		for (Mensaje s : mensajes) {
			System.out.println(s.toString());
		}
		System.out.println();
                */
		
		//insertar usuario
		//Usuario usuario = new Usuario("usuariog", "Usuario generico", "pswd");
		//db.insertarUsuario(usuario);
			
		//validar usuario
		//System.out.println(db.validarUsuario("Mag", "pass"));
			
		//cambiar perfil usuario
		//System.out.println(db.cambiarPerfil("usuariog", "Usuario generico 1"));
			
		//cambiar contrasenya usuario
		//System.out.println(db.cambiarContrasenya("usuariog", "newpswd"));
			
		//eliminar usuario
		//System.out.println(db.eliminarUsuario("usuariog"));
                
                //Obtener mensaje por ID
		//Mensaje s = db.getMensajeporId(1);
		//System.out.println(s.toString());
		
                //insertar mensaje nuevo
                //Mensaje s = new Mensaje(3, "Todo bien", "Mag", "Pepe", "121220121214");
                //db.insertarMensaje(s);
                
                //obtener mensajes en relacion a un usuario
                //Usuario usuario = new Usuario("Pepe", "Pepe Gilberto", "pass2");
                
                //la segunda variable determina si es emisor(true) o receptor(false)
                /*
                List<Mensaje> mensajes = db.getMensajeporUsuario(usuario, false);
		System.out.println("Usuarios: ");
		for (Mensaje s : mensajes) {
			System.out.println(s.toString());
		}
		System.out.println();*/
                
                //ahora ponemos la variable a true para tener los mensajer del usuario como emisor
                /*
                List<Mensaje> mensajes2 = db.getMensajeporUsuario(usuario, true);
		System.out.println("Usuarios: ");
		for (Mensaje s : mensajes2) {
			System.out.println(s.toString());
		}
		System.out.println();
                */
                
                
                //obtener la hora del ultimo inicio de sesion de un usuario
                //Usuario usuario = new Usuario("Mag", "Magali Boada", "pass");
                //System.out.println(db.getFechaPorUsuario(usuario));
		
		
		//ranking usuarios
                
		List<Usuario> ranking = db.getRanking();
                
		for (int i=0; i< ranking.size();++i) 
		{
			System.out.println( (i+1) + ". " + ranking.get(i).getNombre() + " |Mensajes: " + ranking.get(i).getMensajesEnviados());
		}
                
		//ranking usuario
		System.out.println(db.getPosicionRanking("Pepe"));
		
		
		
		
		
		
		// Cerrar la conexion
		db.closeConnection();
		System.out.println("Desconectado ");

	}
}
