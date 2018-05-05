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

	public abstract boolean esTiempoDesde_Dias(String mensaje);
	public abstract String tiempoDesde_Dias(String mensaje, String usuario, String asistente);

	public abstract boolean esTiempoDesde_Semanas(String mensaje);
	public abstract String tiempoDesde_Semanas(String mensaje, String usuario, String asistente);

	public abstract boolean esTiempoDesde_Meses(String mensaje);
	public abstract String tiempoDesde_Meses(String mensaje, String usuario, String asistente);

	public abstract boolean esTiempoDesde_Años(String mensaje);
	public abstract String tiempoDesde_Años(String mensaje, String usuario, String asistente);

	public abstract boolean esTiempoHasta_Dias(String mensaje);
	public abstract String tiempoHasta_Dias(String mensaje, String usuario, String asistente);

	public abstract boolean esTiempoHasta_Semanas(String mensaje);
	public abstract String tiempoHasta_Semanas(String mensaje, String usuario, String asistente);

	public abstract boolean esTiempoHasta_Meses(String mensaje);
	public abstract String tiempoHasta_Meses(String mensaje, String usuario, String asistente);

	public abstract boolean esTiempoHasta_Años(String mensaje);
	public abstract String tiempoHasta_Años(String mensaje, String usuario, String asistente);
	
	public abstract boolean esTiempoHasta(String mensaje);
	public abstract String tiempoHasta(String mensaje, String usuario);
	
//	public abstract boolean esEfemeride(String mensaje);
//	public abstract String efemeride(String mensaje, String usuario, String asistente);
	
	public abstract boolean esCalculoMatematico(String mensaje);
	public abstract String calculoMatematico(String mensaje, String usuario, String asistente);
	
//	public abstract boolean esDefinirFormula(String mensaje);
//	public abstract String definirFormula(String mensaje, String usuario, String asistente);
//	
//	public abstract boolean esDatoFinanciero(String mensaje);
//	public abstract String datoFinanciero(String mensaje, String usuario, String asistente);
//	
//	public abstract boolean esDatoClima(String mensaje);
//	public abstract String datoClima(String mensaje, String usuario, String asistente);
//	
//	public abstract boolean esJuego(String mensaje);
//	public abstract String juego(String mensaje, String usuario, String asistente);


}
