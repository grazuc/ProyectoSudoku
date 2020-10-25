package Logica;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;



/** 
 * Implementacion de la logica del sudoku.
 * @author Razuc Gonzalo.
 * */
public class Juego {
	private Celda [][] tablero;
	private int cantFilas,cantColumnas;
	private int [][] matrizSolucion;
	private boolean cumple;
	
	/** 
	 *Inicializa el tablero, chequea si el archivo es correcto y, si lo es, lo guarda en matrizSolucion.
	 * */
	public Juego() {
		cantColumnas=cantFilas=9;
		matrizSolucion = new int[cantFilas][cantColumnas];
		tablero = new Celda[cantFilas][cantColumnas];
			cumple = chequearSolucion();
			if (cumple) {
				inicializarConArchivo(matrizSolucion);
			}
	}
	
	public Celda[][] getTablero() {
		return tablero;
	}
	
	
	/** 
	 * Verifica que la solucion del archivo dado sea correcta.
	 * @return boolean TRUE Si la solucion es correcta, FALSE en caso contrario.
	 * */
	public boolean chequearSolucion() {
		boolean toRet=true;
		Integer c;
		Scanner scn=null;
		InputStream in = Juego.class.getClassLoader().getResourceAsStream("Archivo/solucionvalida.txt");	
		try {
			scn =new Scanner(in);
		}catch(NullPointerException e) {
			System.exit(0);
		}
		c=scn.hasNext() ? scn.nextInt() : null;
		for(int i=0; i<this.cantFilas && toRet ; i++) {
			for(int j=0; j<this.cantFilas && toRet; j++) {
				if(c==null) {
					System.exit(0);
				}
				else {
					if(c>9) {
						toRet=false;
					}else {
						matrizSolucion[i][j]=c;
					}
					c=scn.hasNext() ? scn.nextInt() : null;
				}
			}
		}
		int indiceInicialF;
		int indiceFinalC;
			
			
			for(int i=0; i<this.cantFilas && toRet; i++) {
				for(int j=0; j<this.cantFilas && toRet; j++) {
					if(i < 3) {
						indiceInicialF = 0;
					}
					else {
						if(j < 6) {
							indiceInicialF = 3;
						}
						else {
							indiceInicialF = 6;
						}
					}
					if(j < 3) {
						indiceFinalC = 0;
					}
					else {
						if(j < 6) {
							indiceFinalC = 3;
						}
						else {
							indiceFinalC = 6;
						}
					}
					toRet=verificarPropiedadesArchivo(i,j,indiceInicialF,indiceFinalC,matrizSolucion);
				}
			}
			scn.close();
		return toRet;
	}
	
	/** 
	 * Verifica que cada elemento del archivo verifique las propiedades para ser una solución válida
	 * @param fila fila del entero del archivo a ser chequeado.
	 * @param columna columna del entero del archivo a ser chequeado.
	 * @param indiceInicialF entero que marca el inicio de la matriz correspondiente del entero a chequear.
	 * @param indiceFinalC entero que marca la columna de la matriz correspondiente del entero a chequear .
	 * @param m matriz que contiene la solución del archivo.
	 * @return seVerifica verdadero si se verifican las reglas del archivo, falso en caso contrario.
	 * */
	private  boolean verificarPropiedadesArchivo(int fila, int columna, int indiceInicialF, int indiceFinalC,int[][]m) {
		boolean seVerifica = true;
		int cantVecesEncontrado=0;
		int nroActual=m[fila][columna]; //este nro puede aparecer 1 vez en la fila, columna y matriz 3x3 perteneciente
		for(int i = 0; i< 9 && seVerifica; i++) {//Recorro toda la fila
			if(m[fila][i] == nroActual) {
				cantVecesEncontrado++;
				if(cantVecesEncontrado>1) 
				seVerifica = false;	
			}
				
		}
		/** si llego hasta esta instancia cantVeces encontrado sera 1 ya que contemplo toda la fila*/
		if(seVerifica) {
			cantVecesEncontrado=0;
			for(int j = 0; j<9 && seVerifica; j++) {//Recorro toda la columna
				if(m[j][columna] == nroActual) //Si se repite el valor de la imagen no se verifica la propiedad
					cantVecesEncontrado++;
					if(cantVecesEncontrado>1)
						seVerifica = false;	
			}
		}
		cantVecesEncontrado=0;
		for(int i = indiceInicialF; i<indiceInicialF + 3 && seVerifica; i++){
			for(int j = indiceFinalC; j<indiceFinalC + 3 && seVerifica; j++) {
				if(nroActual==m[i][j]) {
					if(m[i][j] == nroActual)
						cantVecesEncontrado++;
					if(cantVecesEncontrado>1)
						seVerifica = false;	
				}
				
			}
		}	
		return seVerifica;
	}
	
	/** 
	 * Inicializa las celdas del Sudoku.
	 * @param matriz Matriz Solucion del archivo.
	 * */
	public void inicializarConArchivo(int [][] matriz) {
		Random rnd=new Random();
		Celda celda = new Celda();
		int c=0;;
			
			for (int i =0; i<cantFilas; i++) {
				for (int j =0; j<cantFilas; j++) {
					c=matriz[i][j]-1;
					int aux=rnd.nextInt(3);
					celda = new Celda(i,j);
					tablero[i][j] = celda;
					celda.setCumplePropiedad(true);
					if(aux==1 || aux==0) {
						celda.setValor(c);
					}
				}
		    }
	}
	
	/** 
	 * Verifica que si la fila,columna y cuadrante cumplen con las reglas del Sudoku.
	 * @param fila Fila a verificar.
	 * @param columna Columna a verificar
	 * @return boolean TRUE, Si cumple con las reglas, FALSE En caso contrario.
	 * */
	public boolean cumpleCondiciones(int fila, int columna) {
		return cumpleFila(fila, columna)&&cumpleColumna(fila, columna) && cumplePanel(fila, columna);
	}
	
	/** 
	 * Verifica que la fila dada cumpla con las reglas del Sudoku.
	 * @param fila Fila a verificar.
	 * @param columna Columna a verificar.
	 * @return boolean TRUE, Si la fila dada cumple con las reglas del Sudoku, FALSE En caso contrario.
	 * */
	private boolean cumpleFila(int fila, int columna) {
		boolean cumple = true;
		Celda elemento = new Celda();
		elemento = tablero[fila][columna]; 
		
		for(int j = 0; j< cantFilas && cumple; j++) {
			
			if((tablero[fila][j] != null) && !(tablero[fila][j].equals(elemento))) {
				if(tablero [fila][j].getValor() == elemento.getValor())
				cumple = false;		
			}	
		}
		return cumple;		
	}
	
	/** 
	 * Verifica que la columna dada cumpla con las reglas del Sudoku.
	 * @param fila Fila a verificar.
	 * @param columna Columna a verificar.
	 * @return boolean TRUE, Si la columna dada cumple con las reglas del Sudoku, FALSE En caso contrario.
	 * */
	private boolean cumpleColumna(int fila, int columna) {
		boolean cumple = true; 
		Celda elemento = tablero[fila][columna]; 
		for(int i = 0; i< cantFilas && cumple; i++) {
			
			if((tablero[i][columna] != null) && !(tablero[i][columna].equals(elemento))) {
				if(tablero [i][columna].getValor() == elemento.getValor()) 
				cumple = false;		
			}
		}
		return cumple;			
	}
	
	/** 
	 * Identifica el cuadrante que contiene a la fila y columna dadas y verifica que cumpla con las reglas del Sudoku.
	 * @param fila Fila a verificar.
	 * @param columna Columna a verificar.
	 * @return boolean TRUE, Si el cuadrante cumple con las reglas del Sudoku, FALSE En caso contrario.
	 * */
	private boolean cumplePanel(int fila, int columna) {
		
		boolean cumple = false;
		
		Celda elemento = tablero[fila][columna];		
	
		switch(fila) {
			
		case 0, 1, 2: 
			if(0 <= columna && columna <= 2) { //primer cuadrante
				
				cumple = revisarPanel(0, 2, 0, 2, elemento);				
			}
			else if(3 <= columna && columna <=5) {// segundo cuadrante
				
				cumple = revisarPanel(0, 2, 3, 5, elemento);
			}
			else if(6 <= columna && columna <=8) {// tercer cuadrante
				
				cumple = revisarPanel(0, 2, 6, 8, elemento);
			}
			break;
		case 3, 4, 5:
			if(0 <= columna && columna <= 2) { //cuarto cuadrante
				
				cumple = revisarPanel(3, 5, 0, 2, elemento);				
			}
			else if(3 <= columna && columna <=5) {// quinto cuadrante
				
				cumple = revisarPanel(3, 5, 3, 5, elemento);
			}
			else if(6 <= columna && columna <=8) {// sexto cuadrante
				
				cumple = revisarPanel(3, 5, 6, 8, elemento);
			}
			break;
		case 6, 7, 8:	
			if(0 <= columna && columna <= 2) { //séptimo cuadrante
				
				cumple = revisarPanel(6, 8, 0, 2, elemento);				
			}
			else if(3 <= columna && columna <=5) {// octavo cuadrante
				
				cumple = revisarPanel(6, 8, 3, 5, elemento);
			}
			else if(6 <= columna && columna <=8) {// noveno cuadrante
				
				cumple = revisarPanel(6, 8, 6, 8, elemento);
			}
			break; 
		}	
		return cumple;
	}
	
	
	private boolean revisarPanel (int filaInicial, int filaFinal, int columnaInicial, int columnaFinal, Celda e) {
		
		boolean rta = true;
		
		for(int i = filaInicial; i<=filaFinal && rta; i++) {
			for(int j = columnaInicial; j<=columnaFinal; j++) {
				if((tablero[i][j] != null) && !(tablero[i][j].equals(e))) {
					if(tablero[i][j].getValor() == e.getValor())
						rta = false;
				}
			}
		}
		
		return rta;
	}
	
	/** 
	 * Verifica si el usuario termino el juego ingresando una solucion valida.
	 * @return TRUE, Si la solucion dada por el usuario es valida, FALSE En caso contrario.
	 * */
	public boolean victoria() {
		boolean victoria = true;
		for (int i=0;i<cantFilas&&victoria;i++) {
			for (int j=0;j<cantColumnas&&victoria;j++) {
				if(tablero[i][j].getValor()==null) {
					victoria=false;
				} else {
					if ((tablero[i][j].getValor()+1)!=matrizSolucion[i][j]) {
						victoria=false;
					}
				}
			}
		}
		return victoria;
	}
	
	public void accionar(Celda c) {
		c.actualizarImagen();
	}
	
	public void accionarConError(Celda c) {
		c.actualizarImagenConError();
	}
	
	public Celda getCelda(int i, int j) {
		return this.tablero[i][j];
	}
	
	public int getCantColumnas() {
		return cantColumnas;
	}
	
	public int getCantFilas() {
		return this.cantFilas;
	}
	
	public boolean getCumple() {
		return cumple;
	}
}
