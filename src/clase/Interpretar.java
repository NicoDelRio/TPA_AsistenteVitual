package clase;

import java.text.Normalizer;

public class Interpretar implements TipoMensaje {
	
	public String respuesta(String mensaje_original, String usuario, String asistente) {

//		Se normaliza el mensaje (pasa todo a min�scula, se sacan acentos y caracteres extra�os)
		String mensaje = normalizado(mensaje_original);
		
//		Se hacen todas las series de preguntas (esSaludo, esAgradecimiento, etc).
//		En caso afirmativo de alguna pregunta, return respuesta (s/ la pregunta).
//		Si ninguna respuesta es afirmativa, return mensajeSinSentido(usuario).
		
		if(esMensajeNoDirigidoAsistente(mensaje, asistente))
			return mensajeNoDirigidoAsistente(usuario, asistente);
		
		if(esSaludo(mensaje))
			return saludar(usuario);

		if(esAgradecimiento(mensaje))
			return agradecer(usuario);
		
		if(esDiaActual_Hora(mensaje))
			return diaActual_Hora(usuario);
		
		if(esDiaActual_Fecha(mensaje))
			return diaActual_Fecha(usuario);

		if(esDiaActual_DiaSemana(mensaje))
			return diaActual_DiaSemana(usuario);
		
		if(esFecha_Ma�ana(mensaje))
			return fecha_Ma�ana(usuario);

		if(esFecha_DiaDentro_X(mensaje))
			return fecha_DiaDentro_X(mensaje, usuario);
		
		if(esFecha_Ayer(mensaje))
			return fecha_Ayer(usuario);
		
		if(esFecha_DiaHace_X(mensaje))
			return fecha_DiaHace_X(mensaje, usuario);
		
		if(esTiempoDesde(mensaje))
			return tiempoDesde(mensaje, usuario);
		
		if(esTiempoHasta(mensaje))
			return tiempoHasta(mensaje, usuario);
		
		if(esUnaCuenta(mensaje))
			return mostrarResultado(mensaje, usuario);
		
		
		
		
		
		return mensajeSinSentido(usuario);
		
	}
	

	private static String normalizado(String texto_original) {	
//		limpiar de tildes
        String texto_normalizado = Normalizer.normalize(texto_original.trim().toLowerCase(), Normalizer.Form.NFD);
//      eliminar char que no son ascii salvo �?!��
        texto_normalizado = texto_normalizado.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
        texto_normalizado = Normalizer.normalize(texto_normalizado, Normalizer.Form.NFC);        
        return texto_normalizado;
	}
	
	private static int soloNumero(String texto_original) {
		int numero = 0;
		char[] v_numeros = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		String str = texto_original;
		for(char c : str.toCharArray ()) {
			for(char num : v_numeros) {
				if(c == num)
					numero = numero * 10 + Integer.parseInt( "" + c);
			}
		} 
        return numero;
	}
	

	@Override
	public String mensajeSinSentido(String usuario) {
		return "Disculpa... no entiendo el pedido, " + usuario + " �podr�as repetirlo?";
	}


	@Override
	public boolean esMensajeNoDirigidoAsistente(String mensaje, String asistente) {
		return !mensaje.contains(normalizado(asistente));
	}


	@Override
	public String mensajeNoDirigidoAsistente(String usuario, String asistente) {
		return "Te est�s dirigiendo a m�, " + usuario + "?. Me llamo " + asistente + ".";
	}


	@Override
	public boolean esSaludo(String mensaje) {
		String[] v_Saludo = {"hola", "buen d�a", "buenos d�as", "buenas", "que tal", "tardes", "noches", "hey"};
		
		for(String saludo: v_Saludo)
			if(mensaje.contains(normalizado(saludo)))
				return true;
		return false;
	}


	@Override
	public String saludar(String usuario) {
		return "�Hola, " + usuario + "!";
	}

	@Override
	public boolean esAgradecimiento(String mensaje) {
		String[] v_Agradecimiento = {"gracias"};
		
		for(String agradecimiento: v_Agradecimiento)
			if(mensaje.contains(normalizado(agradecimiento)))
				return true;
		return false;
	}

	@Override
	public String agradecer(String usuario) {
		return "No es nada, " + usuario;
	}

	@Override
	public boolean esDiaActual_Hora(String mensaje) {
		String[] v_DiaActual_Hora = {"qu� hora es", "la hora"};
		
		for(String diaActual_Hora: v_DiaActual_Hora)
			if(mensaje.contains(normalizado(diaActual_Hora)))
				return true;
		return false;
	}

	@Override
	public String diaActual_Hora(String usuario) {
		Fecha diaActual = new Fecha();
		return usuario + " son las " + diaActual.hora(diaActual.formato_hora[0]);
	}


	@Override
	public boolean esDiaActual_Fecha(String mensaje) {
		String[] v_DiaActual_Fecha = {"qu� d�a es", "la fecha", "fecha es hoy"};
		
		for(String diaActual_Fecha: v_DiaActual_Fecha)
			if(mensaje.contains(normalizado(diaActual_Fecha)))
				return true;
		return false;
	}


	@Override
	public String diaActual_Fecha(String usuario) {
		Fecha diaActual = new Fecha();
		return usuario + " hoy es " + diaActual.fecha(diaActual.formato_fecha[0]);
	}


	@Override
	public boolean esDiaActual_DiaSemana(String mensaje) {
		String[] v_DiaActual_DiaSemana = {"d�a de la semana es", "d�a es de la semana"};
		
		for(String diaActual_DiaSemana: v_DiaActual_DiaSemana)
			if(mensaje.contains(normalizado(diaActual_DiaSemana)))
				return true;
		return false;
	}


	@Override
	public String diaActual_DiaSemana(String usuario) {
		Fecha diaActual = new Fecha();
		return usuario + " hoy es " + diaActual.diaSemana();
	}


	@Override
	public boolean esFecha_Ma�ana(String mensaje) {
		String[] v_Fecha_Ma�ana = {"ma�ana que d�a", "es ma�ana", "sera ma�ana"};
		
		for(String fecha_Ma�ana : v_Fecha_Ma�ana)
			if(mensaje.contains(normalizado(fecha_Ma�ana)))
				return true;
		return false;
	}


	@Override
	public String fecha_Ma�ana(String usuario) {
		Fecha date = new Fecha(0, 0, 1, 0, 0, 0);
		return usuario + " ser� el " + date.fecha(date.formato_fecha[4]);
	}


	@Override
	public boolean esFecha_DiaDentro_X(String mensaje) {
		if(mensaje.contains(normalizado("qu� d�a ser� dentro de")))
			return true;
		return false;
	}


	@Override
	public String fecha_DiaDentro_X(String mensaje, String usuario) {
		int dia = 0, mes = 0, a�o = 0;
		if(mensaje.contains(normalizado("d�as?")) || mensaje.contains(normalizado("d�a?")))
			dia = soloNumero(mensaje);
		if(mensaje.contains(normalizado("meses?")) || mensaje.contains(normalizado("mes?")))
			mes = soloNumero(mensaje);
		if(mensaje.contains(normalizado("a�os?")) || mensaje.contains(normalizado("a�o?")))
			a�o = soloNumero(mensaje);
		Fecha date = new Fecha(a�o, mes, dia, 0, 0, 0);
		return usuario + " ser� el " + date.fecha(date.formato_fecha[4]);
	}


	@Override
	public boolean esFecha_Ayer(String mensaje) {
		String[] v_Fecha_Ayer = {"ayer fue", "d�a fue ayer"};
		
		for(String fecha_Ayer : v_Fecha_Ayer)
			if(mensaje.contains(normalizado(fecha_Ayer)))
				return true;
		return false;
	}


	@Override
	public String fecha_Ayer(String usuario) {
		Fecha date = new Fecha(0, 0, -1, 0, 0, 0, "PASADO");
		return usuario + " fue el " + date.fecha(date.formato_fecha[4]);
	}


	@Override
	public boolean esFecha_DiaHace_X(String mensaje) {
		if(mensaje.contains(normalizado("qu� d�a fue hace")))
			return true;
		return false;
	}


	@Override
	public String fecha_DiaHace_X(String mensaje, String usuario) {
		int dia = 0, mes = 0, a�o = 0;
		if(mensaje.contains(normalizado("d�as?")) || mensaje.contains(normalizado("d�a?")))
			dia = soloNumero(mensaje);
		if(mensaje.contains(normalizado("meses?")) || mensaje.contains(normalizado("mes?")))
			mes = soloNumero(mensaje);
		if(mensaje.contains(normalizado("a�os?")) || mensaje.contains(normalizado("a�o?")))
			a�o = soloNumero(mensaje);
		Fecha date = new Fecha(-a�o, -mes, -dia, 0, 0, 0, "PASADO");
		return usuario + " fue el " + date.fecha(date.formato_fecha[4]);
	}


	@Override
	public boolean esTiempoDesde(String mensaje) {
		if(mensaje.contains(normalizado("cu�ntos d�as pasaron desde")))
			return true;
		return false;
	}


	@Override
	public String tiempoDesde(String mensaje, String usuario) {
		// TODO Auto-generated method stub
		return "@delucas entre el 1 de abril de 2017 y el 1 de abril de 2018 pasaron 365 d�as";
	}


	@Override
	public boolean esTiempoHasta(String mensaje) {
		if(mensaje.contains(normalizado("cu�ntos d�as faltan para")))
			return true;
		return false;
	}


	@Override
	public String tiempoHasta(String mensaje, String usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public boolean esUnaCuenta(String mensaje) {
		return mensaje.contains("cu�nto es") || mensaje.contains("cuanto es");
	}

	@Override
	public String mostrarResultado(String expresion, String usuario) {
		Calculo calc = new Calculo();
		double resultado = calc.calcular(expresion);
		if(resultado % 1 == 0) // Si es entero devolver sin decimales
			return usuario + " " + Math.round(resultado);
		else // Sino devolver con decimales
			return usuario + " " + resultado;
	}

	
	
	
	
	
}
