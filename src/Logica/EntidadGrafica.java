package Logica;

import javax.swing.ImageIcon;
/** 
 * Modela la Entidad Grafica asociada a cada celda del Sudoku.
 * @author Razuc Gonzalo
 * */
public class EntidadGrafica {
	private ImageIcon grafico;
	private String[] imagenes; 
	private String[] imagenesMal;
	private String[] imagenDefault;
	/** 
	 * Inicializa los arreglos de imagenes y el grafico.
	 * */
	public EntidadGrafica() {
		imagenes = new String[]{"/Img/1.png", "/Img/2.png", "/Img/3.png", "/Img/4.png", "/Img/5.png", "/Img/6.png", "/Img/7.png", "/Img/8.png", "/Img/9.png"};
		imagenesMal = new String[] {"/Img/1error.png", "/Img/2error.png", "/Img/3error.png", "/Img/4error.png", "/Img/5error.png", "/Img/6error.png", "/Img/7error.png", "/Img/8error.png", "/Img/9error.png"};
		imagenDefault = new String[] {"/Img/default.png"};
		grafico = new ImageIcon();
		ImageIcon imageIcon = new ImageIcon(getClass().getResource(this.imagenDefault[0]));
        this.grafico.setImage(imageIcon.getImage());  
	}
	
	/** 
	 * Actualiza la imagen utilizando un indice.
	 * @param indice Indice del arreglo de imagenes.
	 * */
	public void actualizarImagen(int indice) {
		if (indice < this.imagenes.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
			this.grafico.setImage(imageIcon.getImage());
		}
	}
	
	/** 
	 * Actualiza la imagen con error utilizando un indice.
	 * @param indice Indice del arreglo imagenesMal.
	 * */
	public void actualizarImagenConError(int indice) {
		if (indice < this.imagenesMal.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenesMal[indice]));
			this.grafico.setImage(imageIcon.getImage());
		}
	}
	
	public ImageIcon getGrafico() {
		return this.grafico;
	}
	
	public void setGrafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	
	public String[] getImagenes() {
		return this.imagenes;
	}
	
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
	
}
