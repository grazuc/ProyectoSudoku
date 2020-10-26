package Logica;

import java.util.Timer;
import java.util.TimerTask;

/** 
 * Implementa un Reloj.
 * @author Razuc Gonzalo.
 * */
public class Reloj{
	private int horas, minutos, segundos;
	private String minutosString, segundosString, horasString;
	public Reloj() {
		horas=minutos=segundos=0;
		horasString=minutosString=segundosString="00";
	}
	
	public void iniciarReloj() {
		Timer timer = new Timer();
		TimerTask tarea=new TimerTask() {
			@Override
			public void run() {
				if(segundos<10) {
					segundosString="0"+segundos;
					setSegundos(segundosString);
				}	
				else {
					segundosString=""+segundos;
					setSegundos(segundosString);
				}
				if(minutos<10) {
					minutosString="0"+minutos;
					setMinutos(minutosString);
				}
				else {
					minutosString=""+minutos;
					setMinutos(minutosString);
				}
				if(horas<10) {
					horasString="0"+horas;
					setHoras(horasString);
				}
				else {
					horasString=""+horas;
					setHoras(horasString);
				}
				segundos++;
				if(segundos==60) {
					minutos++;
					segundos=0;
				}
				if(minutos==60) {
					horas++;
					minutos=0;
				}
			}
		};
		timer.schedule(tarea, 0, 1000); //
	}
	
	public void setSegundos(String s) {
		this.segundosString=s;
	}
	
	public void setMinutos(String s) {
		this.minutosString=s;
	}
	
	public void setHoras(String s) {
		this.horasString=s;
	}
	
	public String getHoras() {
		return horasString;
	}
	
	public String getMinutos() {
		return minutosString;
	}
	
	public String getSegundos() {
		return segundosString;
	}
	
	public int getHorasInt() {
		return horas;
	}
}
