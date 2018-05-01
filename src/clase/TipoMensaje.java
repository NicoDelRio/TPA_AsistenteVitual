package clase;

public interface TipoMensaje {
		

	public abstract String mensajeSinSentido(String usuario);
	
	public abstract boolean esMensajeNoDirigidoAsistente(String mensaje, String asistente);
	public abstract String mensajeNoDirigidoAsistente(String usuario, String asistente);
	
	public abstract boolean esSaludo(String mensaje);
	public abstract String saludar(String usuario);
	
	public abstract boolean esAgradecimiento(String mensaje);
	public abstract String agradecer(String usuario);
	
	public abstract boolean esDiaActual_Hora(String mensaje);
	public abstract String diaActual_Hora(String usuario);
	
	public abstract boolean esDiaActual_Fecha(String mensaje);
	public abstract String diaActual_Fecha(String usuario);
	
	public abstract boolean esDiaActual_DiaSemana(String mensaje);
	public abstract String diaActual_DiaSemana(String usuario);

	public abstract boolean esFecha_Mañana(String mensaje);
	public abstract String fecha_Mañana(String usuario);
		
	public abstract boolean esFecha_DiaDentro_X(String mensaje);
	public abstract String fecha_DiaDentro_X(String mensaje, String usuario);

	public abstract boolean esFecha_Ayer(String mensaje);
	public abstract String fecha_Ayer(String usuario);
		
	public abstract boolean esFecha_DiaHace_X(String mensaje);
	public abstract String fecha_DiaHace_X(String mensaje, String usuario);

	public abstract boolean esTiempoDesde(String mensaje);
	public abstract String tiempoDesde(String mensaje, String usuario);

	public abstract boolean esTiempoHasta(String mensaje);
	public abstract String tiempoHasta(String mensaje, String usuario);
			
//	public abstract boolean esEfemeride(String mensaje);
//	public abstract boolean esCalculoMatematico(String mensaje);
//	public abstract boolean esDefinirFormula(String mensaje);
//	public abstract boolean esDatoFinanciero(String mensaje);
//	public abstract boolean esDatoClima(String mensaje);
//	public abstract boolean esJuego(String mensaje);


}
