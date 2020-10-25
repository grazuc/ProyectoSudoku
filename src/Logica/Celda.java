package Logica;

/** 
 * Clase que simula una celda de Sudoku.
 * @author Razuc Gonzalo
 * */
public class Celda {
	private Integer valor;
	private EntidadGrafica entidadGrafica;
	private int cuadrante,fila,columna;
	private boolean cumple;
	
	/** 
	 * Inicializa la celda.
	 * */
	public Celda() {
		fila=columna=0;
		valor =null;
		entidadGrafica = new EntidadGrafica();
		cumple=false;
		cuadrante=0;
	}
	/** 
	 * Inicializa la celda utilizando una fila y una columna.
	 * @param f fila de la celda.
	 * @param c columna de la celda.
	 * */
	public Celda(int f, int c) {
		fila=f;
		columna=c;
		valor=null;
		cuadrante=0;
		cumple=false;
		entidadGrafica = new EntidadGrafica();
	}
	/** 
	 * Actualiza el valor de la celda.
	 * */
	public void actualizarValor() {
		if (valor!=null && valor < getCantElementos()-1){
			valor++;
		}
		else {
			valor = 0;
		}		
	}
	
	/** 
	 * Actualiza la imagen de la celda.
	 * */
	public void actualizarImagen() {
		entidadGrafica.actualizarImagen(this.valor);
	}
	
	/** 
	 * Actualiza la imagen de la celda con un error.
	 * */
	public void actualizarImagenConError() {
		entidadGrafica.actualizarImagenConError(this.valor);
	}
	
	
	public int getColumna() {
		return columna;
	}
	
	public int getFila() {
		return fila;
	}
	
	public int getCantElementos() {
		return this.entidadGrafica.getImagenes().length;
	}
	
	
	public Integer getValor() {
		return this.valor;
	}
	
	public int getCuadrante() {
		return cuadrante;
	}
	
	public void setCuadrante(int c) {
		cuadrante=c;
	}
	
	/** 
	 * Setea un valor a la celda.
	 * @param valor valor a ser seteado en la celda.
	 * */
	public void setValor(Integer valor) {
		if (valor!=null && valor < this.getCantElementos()) {
			this.valor = valor;
			this.entidadGrafica.actualizarImagen(this.valor);
		}else {
			this.valor = null;
		}
	}
	
	public EntidadGrafica getEntidadGrafica() {
		return this.entidadGrafica;
	}
	
	public void setGrafica(EntidadGrafica g) {
		this.entidadGrafica = g;
	}
	
	public void setCumplePropiedad(boolean b) {
		this.cumple=b;
	}
	
	public boolean getCumplePropiedad() {
		return cumple;
	}
}
