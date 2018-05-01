package clase;

public class Asistente extends Interpretar{
	
	private static final String USUARIO = "delucas";
	
	private String nameAsistente;
	private String nameUsuario = "@"+USUARIO;
	
	public Asistente(String nameAsistente) {
		this.nameAsistente = "@"+nameAsistente;
	}
	
	public String escuchar(String mensaje) {
		return respuesta(mensaje, nameUsuario, nameAsistente);
	}

}
