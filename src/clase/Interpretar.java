package clase;

import java.text.Normalizer;
import java.text.ParseException;
//import java.util.concurrent.TimeUnit;

public class Interpretar implements TipoMensaje {
	
	public String respuesta(String mensaje_original, String usuario, String asistente) {

//		Se normaliza el mensaje (pasa todo a minúscula, se sacan acentos y caracteres extraños)
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
		
		if(esFecha_Mañana(mensaje))
			return fecha_Mañana(usuario);

		if(esFecha_DiaDentro_X(mensaje))
			return fecha_DiaDentro_X(mensaje, usuario);
		
		if(esFecha_Ayer(mensaje))
			return fecha_Ayer(usuario);
		
		if(esFecha_DiaHace_X(mensaje))
			return fecha_DiaHace_X(mensaje, usuario);
		
		if(esTiempoDesde_Dias(mensaje))
			return tiempoDesde_Dias(mensaje_original, usuario, asistente);
		
		if(esTiempoDesde_Semanas(mensaje))
			return tiempoDesde_Semanas(mensaje_original, usuario, asistente);
		
		if(esTiempoDesde_Meses(mensaje))
			return tiempoDesde_Meses(mensaje_original, usuario, asistente);
		
		if(esTiempoDesde_Años(mensaje))
			return tiempoDesde_Años(mensaje_original, usuario, asistente);
		
		if(esTiempoHasta_Dias(mensaje))
			return tiempoHasta_Dias(mensaje_original, usuario, asistente);
		
		if(esTiempoHasta_Semanas(mensaje))
			return tiempoHasta_Semanas(mensaje_original, usuario, asistente);
		
		if(esTiempoHasta_Meses(mensaje))
			return tiempoHasta_Meses(mensaje_original, usuario, asistente);
		
		if(esTiempoHasta_Años(mensaje))
			return tiempoHasta_Años(mensaje_original, usuario, asistente);
		
		
		
		if(esCalculoMatematico(mensaje))
			return calculoMatematico(mensaje_original, usuario, asistente);
		
		
		
		
		
		return mensajeSinSentido(usuario);
		
	}
	

	private static String normalizado(String texto_original) {	
//		limpiar de tildes
        String texto_normalizado = Normalizer.normalize(texto_original.trim().toLowerCase(), Normalizer.Form.NFD);
//      eliminar char que no son ascii salvo ¿?!¡ñ
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
		return "Disculpa... no entiendo el pedido, " + usuario + " ¿podrías repetirlo?";
	}


	@Override
	public boolean esMensajeNoDirigidoAsistente(String mensaje, String asistente) {
		return !mensaje.contains(normalizado(asistente));
	}


	@Override
	public String mensajeNoDirigidoAsistente(String usuario, String asistente) {
		return "Te estás dirigiendo a mí, " + usuario + "?. Me llamo " + asistente + ".";
	}


	@Override
	public boolean esSaludo(String mensaje) {
		String[] v_Saludo = {"hola", "buen día", "buenos días", "buenas", "que tal", "tardes", "noches", "hey"};
		
		for(String saludo: v_Saludo)
			if(mensaje.contains(normalizado(saludo)))
				return true;
		return false;
	}


	@Override
	public String saludar(String usuario) {
		return "¡Hola, " + usuario + "!";
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
		String[] v_DiaActual_Hora = {"qué hora es", "la hora"};
		
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
		String[] v_DiaActual_Fecha = {"qué día es", "la fecha", "fecha es hoy"};
		
		for(String diaActual_Fecha: v_DiaActual_Fecha)
			if(mensaje.contains(normalizado(diaActual_Fecha)))
				return true;
		return false;
	}


	@Override
	public String diaActual_Fecha(String usuario) {
		Fecha diaActual = new Fecha();
		return usuario + " hoy es " + diaActual.fechaToString(diaActual.formato_fecha[0]);
	}


	@Override
	public boolean esDiaActual_DiaSemana(String mensaje) {
		String[] v_DiaActual_DiaSemana = {"día de la semana es", "día es de la semana"};
		
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
	public boolean esFecha_Mañana(String mensaje) {
		String[] v_Fecha_Mañana = {"mañana que día", "es mañana", "sera mañana"};
		
		for(String fecha_Mañana : v_Fecha_Mañana)
			if(mensaje.contains(normalizado(fecha_Mañana)))
				return true;
		return false;
	}


	@Override
	public String fecha_Mañana(String usuario) {
		Fecha date = new Fecha(0, 0, 1, 0, 0, 0);
		return usuario + " será el " + date.fechaToString(date.formato_fecha[4]);
	}


	@Override
	public boolean esFecha_DiaDentro_X(String mensaje) {
		if(mensaje.contains(normalizado("qué día será dentro de")))
			return true;
		return false;
	}


	@Override
	public String fecha_DiaDentro_X(String mensaje, String usuario) {
		int dia = 0, mes = 0, año = 0;
		if(mensaje.contains(normalizado("días?")) || mensaje.contains(normalizado("día?")))
			dia = soloNumero(mensaje);
		if(mensaje.contains(normalizado("meses?")) || mensaje.contains(normalizado("mes?")))
			mes = soloNumero(mensaje);
		if(mensaje.contains(normalizado("años?")) || mensaje.contains(normalizado("año?")))
			año = soloNumero(mensaje);
		Fecha date = new Fecha(año, mes, dia, 0, 0, 0);
		return usuario + " será el " + date.fechaToString(date.formato_fecha[4]);
	}


	@Override
	public boolean esFecha_Ayer(String mensaje) {
		String[] v_Fecha_Ayer = {"ayer fue", "día fue ayer"};
		
		for(String fecha_Ayer : v_Fecha_Ayer)
			if(mensaje.contains(normalizado(fecha_Ayer)))
				return true;
		return false;
	}


	@Override
	public String fecha_Ayer(String usuario) {
		Fecha date = new Fecha(0, 0, -1, 0, 0, 0, "PASADO");
		return usuario + " fue el " + date.fechaToString(date.formato_fecha[4]);
	}


	@Override
	public boolean esFecha_DiaHace_X(String mensaje) {
		if(mensaje.contains(normalizado("qué día fue hace")))
			return true;
		return false;
	}


	@Override
	public String fecha_DiaHace_X(String mensaje, String usuario) {
		int dia = 0, mes = 0, año = 0;
		if(mensaje.contains(normalizado("días?")) || mensaje.contains(normalizado("día?")))
			dia = soloNumero(mensaje);
		if(mensaje.contains(normalizado("meses?")) || mensaje.contains(normalizado("mes?")))
			mes = soloNumero(mensaje);
		if(mensaje.contains(normalizado("años?")) || mensaje.contains(normalizado("año?")))
			año = soloNumero(mensaje);
		Fecha date = new Fecha(-año, -mes, -dia, 0, 0, 0, "PASADO");
		return usuario + " fue el " + date.fechaToString(date.formato_fecha[4]);
	}


	@Override
	public boolean esTiempoDesde_Dias(String mensaje) {
		if(mensaje.contains(normalizado(" cuántos días pasaron desde el")))
			return true;
		return false;
	}


	@Override
	public String tiempoDesde_Dias(String mensaje, String usuario, String asistente) {
		String preguntaTiempo = "' cuántos días pasaron desde el'", respuesta;
		Fecha fechaActual = new Fecha();
		Fecha fechaPasado = null;
		try {
			fechaPasado = new Fecha(mensaje.replace("" + asistente, ""),preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
			int dias = fechaPasado.restaFechas_Dias(fechaActual);
			if (dias < 0)
				return mensajeSinSentido(usuario);
			respuesta  = usuario + " entre el " + fechaPasado.fechaToString("d 'de' MMMMM 'de' yyyy");
			respuesta += " y el " + fechaActual.fechaToString("d 'de' MMMMM 'de' yyyy") + (dias != 1 ? " pasaron "  : " paso " ) + dias ;
			respuesta += (dias != 1 ? " días" : " día");
		} catch (ParseException e1) {
			try {
				fechaPasado = new Fecha(mensaje.replace("" + asistente, "").replace("?", "")+ " de " + fechaActual.getAño() + "?",preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
				int dias = fechaPasado.restaFechas_Dias(fechaActual);
				if (dias < 0)
					return mensajeSinSentido(usuario);
				respuesta  = usuario + (dias != 1 ? " pasaron "  : " paso " ) + dias ;
				respuesta += (dias != 1 ? " días" : " día");
			} catch (ParseException e2) {
				return mensajeSinSentido(usuario);
			}
		}
		return respuesta;
	}


	@Override
	public boolean esTiempoDesde_Semanas(String mensaje) {
		if(mensaje.contains(normalizado(" cuántas semanas pasaron desde el")))
			return true;
		return false;
	}


	@Override
	public String tiempoDesde_Semanas(String mensaje, String usuario, String asistente) {
		String preguntaTiempo = "' cuántas semanas pasaron desde el'", respuesta;
		Fecha fechaActual = new Fecha();
		Fecha fechaPasado = null;
		try {
			fechaPasado = new Fecha(mensaje.replace("" + asistente, ""),preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
			int semanas = fechaPasado.restaFechas_Semanas(fechaActual);
			if (semanas < 0)
				return mensajeSinSentido(usuario);
			respuesta  = usuario + " entre el " + fechaPasado.fechaToString("d 'de' MMMMM 'de' yyyy");
			respuesta += " y el " + fechaActual.fechaToString("d 'de' MMMMM 'de' yyyy") + (semanas != 1 ? " pasaron "  : " paso " ) + semanas ;
			respuesta +=  (semanas != 1 ? " semanas" : " semana");
		} catch (ParseException e1) {
			try {
				fechaPasado = new Fecha(mensaje.replace("" + asistente, "").replace("?", "")+ " de " + fechaActual.getAño() + "?",preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
				int semanas = fechaPasado.restaFechas_Semanas(fechaActual);
				if (semanas < 0)
					return mensajeSinSentido(usuario);
				respuesta  = usuario + (semanas != 1 ? " pasaron "  : " paso " ) + semanas ;
				respuesta +=  (semanas != 1 ? " semanas" : " semana");
			} catch (ParseException e2) {
				return mensajeSinSentido(usuario);
			}
		}
		return respuesta;
	}


	@Override
	public boolean esTiempoDesde_Meses(String mensaje) {
		if(mensaje.contains(normalizado(" cuántos meses pasaron desde el")))
			return true;
		return false;
	}


	@Override
	public String tiempoDesde_Meses(String mensaje, String usuario, String asistente) {
		String preguntaTiempo = "' cuántos meses pasaron desde el'", respuesta;
		Fecha fechaActual = new Fecha();
		Fecha fechaPasado = null;
		try {
			fechaPasado = new Fecha(mensaje.replace("" + asistente, ""),preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
			int meses = fechaPasado.restaFechas_Meses(fechaActual);
			if (meses < 0)
				return mensajeSinSentido(usuario);
			respuesta  = usuario + " entre el " + fechaPasado.fechaToString("d 'de' MMMMM 'de' yyyy");
			respuesta += " y el " + fechaActual.fechaToString("d 'de' MMMMM 'de' yyyy") + (meses != 1 ? " pasaron "  : " paso " ) + meses ;
			respuesta +=  (meses != 1 ? " meses" : " mes");
		} catch (ParseException e1) {
			try {
				fechaPasado = new Fecha(mensaje.replace("" + asistente, "").replace("?", "")+ " de " + fechaActual.getAño() + "?",preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
				int meses = fechaPasado.restaFechas_Meses(fechaActual);
				if (meses < 0)
					return mensajeSinSentido(usuario);
				respuesta  = usuario + (meses != 1 ? " pasaron "  : " paso " ) + meses ;
				respuesta +=  (meses != 1 ? " meses" : " mes");
			} catch (ParseException e2) {
				return mensajeSinSentido(usuario);
			}
		}
		return respuesta;
	}


	@Override
	public boolean esTiempoDesde_Años(String mensaje) {
		if(mensaje.contains(normalizado(" cuántos años pasaron desde el")))
			return true;
		return false;
	}


	@Override
	public String tiempoDesde_Años(String mensaje, String usuario, String asistente) {
		String preguntaTiempo = "' cuántos años pasaron desde el'", respuesta;
		Fecha fechaActual = new Fecha();
		Fecha fechaPasado = null;
		try {
			fechaPasado = new Fecha(mensaje.replace("" + asistente, ""),preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
			int años = fechaPasado.restaFechas_Años(fechaActual);
			if (años < 0)
				return mensajeSinSentido(usuario);			
			respuesta  = usuario + " entre el " + fechaPasado.fechaToString("d 'de' MMMMM 'de' yyyy");
			respuesta += " y el " + fechaActual.fechaToString("d 'de' MMMMM 'de' yyyy") + (años != 1 ? " pasaron "  : " paso " ) + años ;
			respuesta +=  (años != 1 ? " años" : " año");
		} catch (ParseException e1) {
			try {
				fechaPasado = new Fecha(mensaje.replace("" + asistente, "").replace("?", "")+ " de " + fechaActual.getAño() + "?",preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
				int años = fechaPasado.restaFechas_Años(fechaActual);
				if (años < 0)
					return mensajeSinSentido(usuario);			
				respuesta  = usuario + (años != 1 ? " pasaron "  : " paso " ) + años ;
				respuesta +=  (años != 1 ? " años" : " año");
			} catch (ParseException e2) {
				return mensajeSinSentido(usuario);
			}
		}
		return respuesta;
	}


	@Override
	public boolean esTiempoHasta_Dias(String mensaje) {
		if(mensaje.contains(normalizado(" cuántos días faltan para el")))
			return true;
		return false;
	}


	@Override
	public String tiempoHasta_Dias(String mensaje, String usuario, String asistente) {
		String preguntaTiempo = "' cuántos días faltan para el'", respuesta;
		Fecha fechaActual = new Fecha();
		Fecha fechaFuturo = null;
		try {
			fechaFuturo = new Fecha(mensaje.replace("" + asistente, ""),preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
			int dias = fechaActual.restaFechas_Dias(fechaFuturo);
			if (dias < 0)
				return mensajeSinSentido(usuario);
			respuesta  = usuario + " entre el " + fechaActual.fechaToString("d 'de' MMMMM 'de' yyyy");
			respuesta += " y el " + fechaFuturo.fechaToString("d 'de' MMMMM 'de' yyyy") + (dias != 1 ? " faltan "  : " falta " ) + dias ;
			respuesta += (dias != 1 ? " días" : " día");
		} catch (ParseException e1) {
			try {
				fechaFuturo = new Fecha(mensaje.replace("" + asistente, "").replace("?", "")+ " de " + fechaActual.getAño() + "?",preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
				int dias = fechaActual.restaFechas_Dias(fechaFuturo);
				if (dias < 0)
					return mensajeSinSentido(usuario);
				respuesta  = usuario + (dias != 1 ? " faltan "  : " falta " ) + dias ;
				respuesta +=  (dias != 1 ? " días" : " día");
			} catch (ParseException e2) {
				return mensajeSinSentido(usuario);
			}
		}
		return respuesta;
	}


	@Override
	public boolean esTiempoHasta_Semanas(String mensaje) {
		if(mensaje.contains(normalizado(" cuántas semanas faltan para el")))
			return true;
		return false;
	}


	@Override
	public String tiempoHasta_Semanas(String mensaje, String usuario, String asistente) {
		String preguntaTiempo = "' cuántas semanas faltan para el'", respuesta;
		Fecha fechaActual = new Fecha();
		Fecha fechaFuturo = null;
		try {
			fechaFuturo = new Fecha(mensaje.replace("" + asistente, ""),preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
			int semanas = fechaActual.restaFechas_Semanas(fechaFuturo);
			if (semanas < 0)
				return mensajeSinSentido(usuario);
			respuesta  = usuario + " entre el " + fechaActual.fechaToString("d 'de' MMMMM 'de' yyyy");
			respuesta += " y el " + fechaFuturo.fechaToString("d 'de' MMMMM 'de' yyyy") + (semanas != 1 ? " faltan "  : " falta " ) + semanas ;
			respuesta += (semanas != 1 ? " semanas" : " semana");
		} catch (ParseException e1) {
			try {
				fechaFuturo = new Fecha(mensaje.replace("" + asistente, "").replace("?", "")+ " de " + fechaActual.getAño() + "?",preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
				int semanas = fechaActual.restaFechas_Semanas(fechaFuturo);
				if (semanas < 0)
					return mensajeSinSentido(usuario);
				respuesta  = usuario + (semanas != 1 ? " faltan "  : " falta " ) + semanas ;
				respuesta += (semanas != 1 ? " semanas" : " semana");
			} catch (ParseException e2) {
				return mensajeSinSentido(usuario);
			}
		}
		return respuesta;
	}


	@Override
	public boolean esTiempoHasta_Meses(String mensaje) {
		if(mensaje.contains(normalizado(" cuántos meses faltan para el")))
			return true;
		return false;
	}


	@Override
	public String tiempoHasta_Meses(String mensaje, String usuario, String asistente) {
		String preguntaTiempo = "' cuántos meses faltan para el'", respuesta;
		Fecha fechaActual = new Fecha();
		Fecha fechaFuturo = null;
		try {
			fechaFuturo = new Fecha(mensaje.replace("" + asistente, ""),preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
			int meses = fechaActual.restaFechas_Meses(fechaFuturo);
			if (meses < 0)
				return mensajeSinSentido(usuario);
			respuesta  = usuario + " entre el " + fechaActual.fechaToString("d 'de' MMMMM 'de' yyyy");
			respuesta += " y el " + fechaFuturo.fechaToString("d 'de' MMMMM 'de' yyyy") + (meses != 1 ? " faltan "  : " falta " ) + meses ;
			respuesta += (meses != 1 ? " meses" : " mes");
		} catch (ParseException e1) {
			try {
				fechaFuturo = new Fecha(mensaje.replace("" + asistente, "").replace("?", "")+ " de " + fechaActual.getAño() + "?",preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
				int meses = fechaActual.restaFechas_Meses(fechaFuturo);
				if (meses < 0)
					return mensajeSinSentido(usuario);
				respuesta  = usuario + (meses != 1 ? " faltan "  : " falta " ) + meses ;
				respuesta += (meses != 1 ? " meses" : " mes");
			} catch (ParseException e2) {
				return mensajeSinSentido(usuario);
			}
		}
		return respuesta;
	}


	@Override
	public boolean esTiempoHasta_Años(String mensaje) {
		if(mensaje.contains(normalizado(" cuántos años faltan para el")))
			return true;
		return false;
	}


	@Override
	public String tiempoHasta_Años(String mensaje, String usuario, String asistente) {
		String preguntaTiempo = "' cuántos años faltan para el'", respuesta;
		Fecha fechaActual = new Fecha();
		Fecha fechaFuturo = null;
		try {
			fechaFuturo = new Fecha(mensaje.replace("" + asistente, ""),preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
			int años = fechaActual.restaFechas_Años(fechaFuturo);
			if (años < 0)
				return mensajeSinSentido(usuario);
			respuesta  = usuario + " entre el " + fechaActual.fechaToString("d 'de' MMMMM 'de' yyyy");
			respuesta += " y el " + fechaFuturo.fechaToString("d 'de' MMMMM 'de' yyyy") + (años != 1 ? " faltan "  : " falta " ) + años ;
			respuesta += (años != 1 ? " años" : " año");
		} catch (ParseException e1) {
			try {
				fechaFuturo = new Fecha(mensaje.replace("" + asistente, "").replace("?", "")+ " de " + fechaActual.getAño() + "?",preguntaTiempo + " d 'de' MMMMM 'de' yyyy'?'");
				int años = fechaActual.restaFechas_Años(fechaFuturo);
				if (años < 0)
					return mensajeSinSentido(usuario);
				respuesta  = usuario + (años != 1 ? " faltan "  : " falta " ) + años ;
				respuesta += (años != 1 ? " años" : " año");
			} catch (ParseException e2) {
				return mensajeSinSentido(usuario);
			}
		}
		return respuesta;
	}



	@Override
	public boolean esCalculoMatematico(String mensaje) {
		String[] v_Calculo = {"cuánto es", "cuanto da"};
		
		for(String calculo: v_Calculo)
			if(mensaje.contains(normalizado(calculo)))
				return true;
		return false;
	}


	@Override
	public String calculoMatematico(String mensaje, String usuario, String asistente) {
		String respuesta = null;
		try {
			// TODO Auto-generated method stub
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			return mensajeSinSentido(usuario);
		}
		return respuesta;
	}








	
	
	
	
	
	
}
