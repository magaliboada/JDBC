/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author Magali
*/



public class MensajesJDBC {
    
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String JDBC_URL = "jdbc:mysql://localhost/mensajeria";
	public static final String JDBC_USER = "root";
	public static final String JDBC_PASS = "";

	private Connection conn = null;
	
	public boolean openConnection() {
		if (conn != null) closeConnection();
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("Driver no encontrado");
			System.exit(-1);
		}
		try {
			conn = (Connection) DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		} catch (SQLException e) {
			System.err.println("ERROR CONNECTING: " + e.getMessage());
			return false;
		}
		return true;
	}
        
        public List<Usuario> getUsuario() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM Usuario";
			if (!st.execute(sql)) return null;
			ResultSet result = st.getResultSet();
			while (result.next()) {
				String usuarioId = result.getString("usuario");
				String nombre = result.getString("nombre");
				String password = result.getString("password");				
				Usuario usuario = new Usuario(usuarioId, nombre, password);
				usuarios.add(usuario);
			}
			return usuarios;
		}
		catch (SQLException e) {
			System.err.println("Error in SQL: " + e.getMessage());
			return null;
		}
	}
        
        public List<Usuario> getRanking()
	{
            List<Usuario> usuarios = getUsuario();
				
		for (Usuario u : usuarios) 
		{
                    List<Mensaje> mensajes = getMensajeporUsuario(u, false);
                    for (Mensaje s : mensajes) {
                        u.setMensajesEnviados(u.getMensajesEnviados()+1);
                    }
		}
                
            Collections.sort(usuarios, new CustomComparator());
            Collections.reverse(usuarios);
            return usuarios;
	}
        
        public List<Mensaje> getMensajes() {
		List<Mensaje> mensajes = new ArrayList<Mensaje>();
		try {
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM Mensajes";
			if (!st.execute(sql)) return null;
			ResultSet result = st.getResultSet();
			while (result.next()) {
				String idMensaje = result.getString("idMensaje");
				String texto = result.getString("texto");
				String emisor = result.getString("emisor");
                                String receptor = result.getString("receptor");
                                String fecha = result.getString("fecha");
				Mensaje mensaje = new Mensaje(Integer.parseInt(idMensaje), texto, emisor, receptor, fecha);
				mensajes.add(mensaje);
			}
			return mensajes;
		}
		catch (SQLException e) {
			System.err.println("Error in SQL: " + e.getMessage());
			return null;
		}
	}
        
        public boolean insertarUsuario(Usuario usuario)
	{
		try {
			PreparedStatement st = conn.prepareStatement(
					"INSERT INTO usuario VALUES (?,?,?)" );

			st.setString(1, usuario.getUsuario());
			st.setString(2, usuario.getNombre());
			st.setString(3, usuario.getPassword());
			return st.execute();
		}
		catch (SQLException e) {
			System.err.println("Error executing SQL: " + e.getMessage());
			return false;
		}
	}
        
        
        public String validarUsuario(String usuarioentra, String contrasenyaentra) {
		Usuario usuariovalidar = new Usuario();
		try {
			PreparedStatement st = conn.prepareStatement(
					"SELECT usuario.* " +
					"FROM usuario " +
					"WHERE usuario.usuario=?" );
			st.setString(1, usuarioentra);
			if (!st.execute()) return null;
			ResultSet result = st.getResultSet();
			while (result.next()) {
				usuarioentra = result.getString(1);
				String contrasenya = result.getString("password");
				String nombre = result.getString("nombre");				
				usuariovalidar = new Usuario(usuarioentra, nombre, contrasenya);
			}
		}
		catch (SQLException e) {
			System.err.println("Error executing SQL: " + e.getMessage());
			return null;
		}
		
                
		if(usuariovalidar.getPassword().isEmpty())
			return "Usuario incorrecto";		
		else if (usuariovalidar.getPassword().equals(contrasenyaentra))
				return "Hola "+usuariovalidar.getNombre();
		else return "Contrasenya incorrecta.";
	
        }
        
        public boolean cambiarPerfil(String usuarioentra, String nuevonombre)
	{
		try {
			PreparedStatement st = conn.prepareStatement(
					"UPDATE usuario " +
					"SET usuario.nombre = ? " +
					"WHERE usuario.usuario=? ;" );
			st.setString(1, nuevonombre);
			st.setString(2, usuarioentra);
                        st.execute();
		}

		catch (SQLException e) {
			System.err.println("Error executing SQL: " + e.getMessage());
			return false;
		}
		return true;

	}
        
        public boolean cambiarContrasenya(String usuarioentra, String nuevacontrasenya)
	{
		try {
			PreparedStatement st = conn.prepareStatement(
					"UPDATE usuario " +
					"SET usuario.password = ? " +
					"WHERE usuario.usuario=?" );
			st.setString(1, nuevacontrasenya);
			st.setString(2, usuarioentra);
                        st.execute();
		}

		catch (SQLException e) {
			System.err.println("Error executing SQL: " + e.getMessage());
			return false;
		}
		return true;

	}
	
	public boolean eliminarUsuario(String usuarioelimina)
	{
		try {
			PreparedStatement st = conn.prepareStatement(
					"DELETE FROM usuario " +
					"WHERE usuario = ?" );
			st.setString(1, usuarioelimina);
			st.execute();
		}
                
		catch (SQLException e) {
			System.err.println("Error executing SQL: " + e.getMessage());
			return false;
		}
		return true;
	}
        
        public Mensaje getMensajeporId(int idMensaje) {
			Mensaje mensaje = new Mensaje();
			try {
				PreparedStatement st = conn.prepareStatement(
						"SELECT mensajes.* " +
						"FROM mensajes " +
						"WHERE idMensaje=?" );
				st.setInt(1, idMensaje);
				if (!st.execute()) return null;
				ResultSet result = st.getResultSet();
				while (result.next()) {
					idMensaje = result.getInt(1);
					String texto = result.getString("texto");
					String emisor = result.getString("emisor");
					String receptor = result.getString("receptor");
                                        String fecha = result.getString("fecha");
					mensaje = new Mensaje(idMensaje, texto, emisor, receptor, fecha);
				}
			}
			catch (SQLException e) {
				System.err.println("Error executing SQL: " + e.getMessage());
				return null;
			}
			return mensaje;
		
	}
        
        public boolean insertarMensaje(Mensaje mensaje) {
		try {
			PreparedStatement st = conn.prepareStatement(
					"INSERT INTO mensajes VALUES (?,?,?,?,?)" );
			if (mensaje.getIdMensaje() == -1) {
				st.setNull(1, Types.INTEGER);
			}
			else {
				st.setInt(1, mensaje.getIdMensaje());
			}
			st.setString(2, mensaje.getTexto());
			st.setString(3, mensaje.getEmisor());
			st.setString(4, mensaje.getReceptor());
                        st.setString(5, mensaje.getFecha());
			return st.execute();
		}
		catch (SQLException e) {
			System.err.println("Error executing SQL: " + e.getMessage());
			return false;
		}
	}
        
        public List<Mensaje> getMensajeporUsuario(Usuario usuario, Boolean esEmisor) {
            List<Mensaje> mensajes = new ArrayList<Mensaje>();
			Mensaje mensaje = new Mensaje();
			try {
                            PreparedStatement st = null;
                            
                            if(esEmisor)
                            {
                                st = conn.prepareStatement(
						"SELECT mensajes.* " +
						"FROM mensajes " +
						"WHERE emisor=?" );
				st.setString(1, usuario.getUsuario());
                            }
                            else
                            {
				st = conn.prepareStatement(
						"SELECT mensajes.* " +
						"FROM mensajes " +
						"WHERE receptor=?" );
				st.setString(1, usuario.getUsuario());
                            }
                                
                                
                                
				if (!st.execute()) return null;
				ResultSet result = st.getResultSet();
				while (result.next()) {
					int idMensaje = result.getInt(1);
					String texto = result.getString("texto");
					String emisor = result.getString("emisor");
					String receptor = result.getString("receptor");
                                        String fecha = result.getString("fecha");
					mensaje = new Mensaje(idMensaje, texto, emisor, receptor, fecha);
                                        mensajes.add(mensaje);
				}
			}
			catch (SQLException e) {
				System.err.println("Error executing SQL: " + e.getMessage());
				return null;
			}
			return mensajes;
		
	}
        
        public String getFechaPorUsuario(Usuario usuario) {
			Historial historial = new Historial();
                        String fechafinal;
			try {
				PreparedStatement st = conn.prepareStatement(
						"SELECT historial.* " +
						"FROM historial " +
						"WHERE usuario=?" +
                                                 "ORDER BY fecha");
				st.setString(1, usuario.getUsuario());
				if (!st.execute()) return null;
				ResultSet result = st.getResultSet();
				while (result.next()) {
					String tipoConsulta = result.getString("tipoConsulta");
					String usuarioNombre = result.getString("usuario");
                                        String fecha = result.getString("fecha");
					historial = new Historial(tipoConsulta, usuarioNombre, fecha);
				}
			}
			catch (SQLException e) {
				System.err.println("Error executing SQL: " + e.getMessage());
				return null;
			}
                        fechafinal = historial.convertirHora(historial.getFecha());                        
			return fechafinal;
		
	}
        
        public int getPosicionRanking(String usuario)
	{
		List<Usuario> usuarios = this.getRanking();
		String usuariocomp;
		
		for (int i=0; i<usuarios.size();++i)
		{			
			usuariocomp = usuarios.get(i).getUsuario();
			if(usuariocomp.equals(usuario))
			{
				return i+1;
			}
		}
		
		return 0;		
	}
        
        
        
	public void closeConnection() {
		if (conn == null) return;
		try { conn.close(); }
		catch (SQLException e) { }
		conn = null;
	}
    
}
