package clase;

import java.util.Calendar;

public class Fecha {
	
	private Calendar ahora;
	private int año;
	private int mes;
	private int dia;
//	private int dia_semana_numero;
	private int hr_12;
	private int hr_24;
	private int min;
	private int seg;
	private String am_pm;
	private String mes_nombre;
	private String dia_nombre;
	private String[] v_am_pm = {"AM", "PM"};
	private String[] v_mes_nombre = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
	private String[] v_dia_nombre = {"DOMINGO", "LUNES", "MARTES", "MIÉRCOLES", "JUEVES", "VIERNES", "SÁBADO"};


	public Fecha () {
		this.ahora = Calendar.getInstance();
		ahora.set(2018, 3, 1, 15, 15, 0);
		año = ahora.get(Calendar.YEAR);
		mes = ahora.get(Calendar.MONTH) + 1; // 1 (ENERO) y 12 (DICIEMBRE)
		dia = ahora.get(Calendar.DAY_OF_MONTH);
//		dia_semana_numero = ahora.get(Calendar.DAY_OF_WEEK); // DAY_OF_WEEK: Día de la semana entre 1 (DOMINGO) y 7 (SÁBADO).
		hr_12 = ahora.get(Calendar.HOUR);
		hr_24 = ahora.get(Calendar.HOUR_OF_DAY);
		min = ahora.get(Calendar.MINUTE);
		seg = ahora.get(Calendar.SECOND);
		am_pm = v_am_pm[ahora.get(Calendar.AM_PM)]; //ahora.get(Calendar.AM_PM)=> 0 (AM) y 1 (PM)
		mes_nombre = v_mes_nombre[ahora.get(Calendar.MONTH)];
		dia_nombre = v_dia_nombre[ahora.get(Calendar.DAY_OF_WEEK) - 1];

	}
	
//	Constructor para Fecha Futuro. Ordesn de ".add" es de segundos hasta años.
	public Fecha (int año_p, int mes_p, int dia_p, int hr_p, int min_p, int seg_p) {
		this.ahora = Calendar.getInstance();
		ahora.set(2018, 3, 1, 15, 15, 0);
		if(seg_p != 0)
			ahora.add(Calendar.SECOND, seg_p);
		if(min_p != 0)
			ahora.add(Calendar.MINUTE, min_p);
		if(hr_p != 0)
			ahora.add(Calendar.HOUR, hr_p);
		if(dia_p != 0)
			ahora.add(Calendar.DATE, dia_p);
		if(mes_p != 0)
			ahora.add(Calendar.MONTH, mes_p);
		if(año_p != 0)
			ahora.add(Calendar.YEAR, año_p);
		año = ahora.get(Calendar.YEAR);
		mes = ahora.get(Calendar.MONTH) + 1; // 1 (ENERO) y 12 (DICIEMBRE)
		dia = ahora.get(Calendar.DAY_OF_MONTH);
//		dia_semana_numero = ahora.get(Calendar.DAY_OF_WEEK); // DAY_OF_WEEK: Día de la semana entre 1 (DOMINGO) y 7 (SÁBADO).
		hr_12 = ahora.get(Calendar.HOUR);
		hr_24 = ahora.get(Calendar.HOUR_OF_DAY);
		min = ahora.get(Calendar.MINUTE);
		seg = ahora.get(Calendar.SECOND);
		am_pm = v_am_pm[ahora.get(Calendar.AM_PM)]; //ahora.get(Calendar.AM_PM)=> 0 (AM) y 1 (PM)
		mes_nombre = v_mes_nombre[ahora.get(Calendar.MONTH)];
		dia_nombre = v_dia_nombre[ahora.get(Calendar.DAY_OF_WEEK) - 1];

	}
	
	
//	Constructor para Fecha Pasado. Ordesn de ".add" es de años a segundos. 
//	Ultimo parametro un String para no distingir del Constructor para Fecha Futuro
	public Fecha (int año_p, int mes_p, int dia_p, int hr_p, int min_p, int seg_p, String pasado) {
		this.ahora = Calendar.getInstance();
		ahora.set(2018, 3, 1, 15, 15, 0);
		if(año_p != 0)
			ahora.add(Calendar.YEAR, año_p);
		if(mes_p != 0)
			ahora.add(Calendar.MONTH, mes_p);
		if(dia_p != 0)
			ahora.add(Calendar.DATE, dia_p);
		if(hr_p != 0)
			ahora.add(Calendar.HOUR, hr_p);
		if(min_p != 0)
			ahora.add(Calendar.MINUTE, min_p);
		if(seg_p != 0)
			ahora.add(Calendar.SECOND, seg_p);
		año = ahora.get(Calendar.YEAR);
		mes = ahora.get(Calendar.MONTH) + 1; // 1 (ENERO) y 12 (DICIEMBRE)
		dia = ahora.get(Calendar.DAY_OF_MONTH);
//		dia_semana_numero = ahora.get(Calendar.DAY_OF_WEEK); // DAY_OF_WEEK: Día de la semana entre 1 (DOMINGO) y 7 (SÁBADO).
		hr_12 = ahora.get(Calendar.HOUR);
		hr_24 = ahora.get(Calendar.HOUR_OF_DAY);
		min = ahora.get(Calendar.MINUTE);
		seg = ahora.get(Calendar.SECOND);
		am_pm = v_am_pm[ahora.get(Calendar.AM_PM)]; //ahora.get(Calendar.AM_PM)=> 0 (AM) y 1 (PM)
		mes_nombre = v_mes_nombre[ahora.get(Calendar.MONTH)];
		dia_nombre = v_dia_nombre[ahora.get(Calendar.DAY_OF_WEEK) - 1];

	}
	
//	Calendar ahoraCal = Calendar.getInstance();
//	ahoraCal.set(2018,10,25,14,30,15); // 2018-11-25 14:30:15
//	System.out.println(ahoraCal.getTime());
//	System.out.println(ahoraCal.get(Calendar.MONTH));
	
	public String[] formato_hora =	{"H:MM_AM", "HH:MM_AM", "H:MM:SS_AM", "HH:MM:SS_AM",
									 "H:MM", "HH:MM", "H:MM:SS", "HH:MM:SS"};
	
	
	public String hora(String format_time) {
		if(format_time.equals(formato_hora[0]))
			return "" + hr_12 + ":" + (min<10?"0":"") + min + " " + am_pm;

		if(format_time.equals(formato_hora[1]))
			return "" + (hr_12<10?"0":"") + hr_12 + ":" + (min<10?"0":"") + min + " " + am_pm;

		if(format_time.equals(formato_hora[2]))
			return "" + hr_12 + ":" + (min<10?"0":"") + min + ":" + (seg<10?"0":"") + seg + " " + am_pm;

		if(format_time.equals(formato_hora[3]))
			return "" + (hr_12<10?"0":"") + hr_12 + ":" + (min<10?"0":"") + min + ":" + (seg<10?"0":"") + seg + " " + am_pm;

		if(format_time.equals(formato_hora[4]))
			return "" + hr_24 + ":" + (min<10?"0":"") + min ;
		
		if(format_time.equals(formato_hora[5]))
			return "" + (hr_24<10?"0":"") + hr_24 + ":" + (min<10?"0":"") + min ;

		if(format_time.equals(formato_hora[6]))
			return "" + hr_24 + ":" + (min<10?"0":"") + min + ":" + (seg<10?"0":"") + seg;

		if(format_time.equals(formato_hora[7]))
			return "" + (hr_24<10?"0":"") + hr_24 + ":" + (min<10?"0":"") + min + ":" + (seg<10?"0":"") + seg;
		
		
		return "" + ahora.getTime();
	}

	
	public String[] formato_fecha =	{"D de mmmm de AAAA", "DD de mmmm de AAAA", "DD/MM/AAAA", "DD/MM/AA",
									 "nameDay D de mmmm de AAAA"};

	
	public String fecha(String format_date) {
		if(format_date.equals(formato_fecha[0]))
			return "" + dia + " de " + mes_nombre.toLowerCase() + " de " + año;

		if(format_date.equals(formato_fecha[1]))
			return "" + (dia<10?"0":"")  + dia + " de " + mes_nombre.toLowerCase() + " de " + año;

		if(format_date.equals(formato_fecha[2]))
			return "" + (dia<10?"0":"")  + dia + "/"  + (mes<10?"0":"")  + mes + "/" + año;

		if(format_date.equals(formato_fecha[3]))
			return "" + (dia<10?"0":"")  + dia + "/"  + (mes<10?"0":"")  + mes + "/" + año%100;

		if(format_date.equals(formato_fecha[4]))	// lunes 2 de abril de 2018"
			return dia_nombre.toLowerCase() + " " + dia + " de " + mes_nombre.toLowerCase() + " de " + año;
		
		
		return "" + ahora.get(Calendar.DATE);
	}
	
	
	public String diaSemana() {
		return dia_nombre.toLowerCase();
	}
	
	
	
}
