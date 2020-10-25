package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.*;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

/** 
 * Interfaz Grafica que modela el juego Sudoku.
 * @author Razuc Gonzalo.
 * */
public class GUISudoku extends JFrame {
	
	private JPanel contentPane,panelCeldas;
	private JLabel[][] celdas;
	private Juego juego;
	private boolean sePuedeJugar=false;
	private JLabel lblHoras,lblHoras2, labelMinutos,labelMinutos2, labelSegundos, labelSegundos2, lblFondo;
	private String [] fondo=new String[] {"/Img/fondo.png"};
	private String [] reloj=new String[] {"/ImgReloj/0.png","/ImgReloj/1.png","/ImgReloj/2.png","/ImgReloj/3.png",
			"/ImgReloj/4.png","/ImgReloj/5.png","/ImgReloj/6.png","/ImgReloj/7.png","/ImgReloj/8.png",
			"/ImgReloj/9.png",};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUISudoku frame = new GUISudoku();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public GUISudoku() {
		setTitle("Sudoku");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		/** Crea el objeto de la clase Juego y verifica que el archivo dado contenga una solucion valida.
		 * */
		juego=new Juego();
		if(!juego.getCumple()) {
			JOptionPane.showMessageDialog(null, "Error: no se pudo inicializar el juego, el archivo no contenía una solución válida.");
			System.exit(0);	
		}
		
		
		/** PANEL DE LAS CELDAS*/
		panelCeldas = new JPanel();
		panelCeldas.setBounds(0, 0,600, 600);
		contentPane.add(panelCeldas);
		panelCeldas.setLayout(new GridLayout(9, 9, 2, 2));
		panelCeldas.setBackground(UIManager.getColor("Separator.shadow"));
		this.inicializarLabels();
		/** */
		
		/** Labels para el reloj*/
		ImageIcon grafico0 = new ImageIcon(this.getClass().getResource(this.reloj[0]));
		/** */
		
		
		/** Botones*/
		lblFondo=new JLabel();
		lblFondo.setBounds(595, 0, 200, 611);
		ImageIcon grafico = new ImageIcon(this.getClass().getResource(this.fondo[0]));
		lblFondo.setIcon(grafico);
		reDimensionar(lblFondo, grafico);
		contentPane.add(lblFondo);
		lblFondo.setBackground(SystemColor.control);
		lblHoras = new JLabel();
		lblHoras.setBounds(10, 30, 25, 25);
		lblHoras.setIcon(grafico0);
		reDimensionar(lblHoras,grafico0);
		lblFondo.add(lblHoras);
		
		lblHoras2 = new JLabel();
		lblHoras2.setBounds(36, 30, 25, 25);
		lblHoras2.setIcon(grafico0);
		reDimensionar(lblHoras2,grafico0);
		lblFondo.add(lblHoras2);
		
		labelMinutos = new JLabel();
		labelMinutos.setBounds(74, 30, 25, 25);
		labelMinutos.setIcon(grafico0);
		reDimensionar(labelMinutos,grafico0);
		lblFondo.add(labelMinutos);
		
		labelMinutos2 = new JLabel();
		labelMinutos2.setBounds(100, 30, 25, 25);
		labelMinutos2.setIcon(grafico0);
		reDimensionar(labelMinutos2,grafico0);
		lblFondo.add(labelMinutos2);
		
		
		labelSegundos = new JLabel();
		labelSegundos.setBounds(137, 30, 25, 25);
		labelSegundos.setIcon(grafico0);
		reDimensionar(labelSegundos, grafico0);
		lblFondo.add(labelSegundos);
		
		labelSegundos2 = new JLabel();
		labelSegundos2.setBounds(163, 30, 25, 25);
		labelSegundos2.setIcon(grafico0);
		reDimensionar(labelSegundos2, grafico0);
		lblFondo.add(labelSegundos2);
		JButton btnjugar = new JButton("Jugar");
		contentPane.add(btnjugar);
		btnjugar.setBounds(605, 505, 82, 25);
		JButton btnFinalizar = new JButton("Finalizar");
		contentPane.add(btnFinalizar);
		btnFinalizar.setBounds(702, 505, 82, 25);
		btnFinalizar.setEnabled(false);
		
		
		
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (juego.victoria()) {
					sePuedeJugar=false;
					JOptionPane.showMessageDialog(null, "¡Felicitaciones, ha terminado el juego! ", "Estado del juego", JOptionPane.INFORMATION_MESSAGE);
					btnFinalizar.setEnabled(false);
				}
				else
					JOptionPane.showMessageDialog(null, "Solución incompleta o invalida, ¡siga intentando!", "Estado del juego", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnjugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<juego.getCantFilas(); i++) {
					for(int j=0; j<juego.getCantFilas(); j++) {
							celdas[i][j].setEnabled(true);
					}
				}
				sePuedeJugar=true; //a partir de ahora pueden editarse las celdas!
				btnjugar.setEnabled(false);
				btnFinalizar.setEnabled(true);
				iniciarReloj();
				
			}
			
		});
		/** */
		
	} /** Fin del constructor*/
	
	/** 
	 * 	Crea un objeto de la clase reloj y lo inicia, este correra hasta que el usuario termine el Sudoku ingresando una solucion valida.
	 * */
	private void iniciarReloj() {
		Reloj r=new Reloj();
		r.iniciarReloj();
		Timer timer = new Timer();
		TimerTask tarea=new TimerTask() {

			@Override
			public void run() {
				if(sePuedeJugar) {
					/** el metodo getSegundos devuelve 00,01..60 */
					ImageIcon graficoSegundosIzq, graficoSegundosDer;
					int derecha=Integer.parseInt(r.getSegundos().charAt(1)+"");
					int izquierda=Integer.parseInt(r.getSegundos().charAt(0)+"");
					graficoSegundosIzq = new ImageIcon(this.getClass().getResource(reloj[izquierda]));
					graficoSegundosDer = new ImageIcon(this.getClass().getResource(reloj[derecha]));
					labelSegundos.setIcon(graficoSegundosIzq);
					reDimensionar(labelSegundos,graficoSegundosIzq);
					labelSegundos2.setIcon(graficoSegundosDer);
					reDimensionar(labelSegundos2,graficoSegundosDer);
					/** */
					ImageIcon graficoMinutosIzq, graficoMinutosDer;
					derecha=Integer.parseInt(r.getMinutos().charAt(1)+"");
					izquierda=Integer.parseInt(r.getMinutos().charAt(0)+"");
					graficoMinutosIzq = new ImageIcon(this.getClass().getResource(reloj[izquierda]));
					graficoMinutosDer = new ImageIcon(this.getClass().getResource(reloj[derecha]));
					labelMinutos.setIcon(graficoMinutosIzq);
					reDimensionar(labelMinutos,graficoMinutosIzq);
					labelMinutos2.setIcon(graficoMinutosDer);
					reDimensionar(labelMinutos2,graficoMinutosDer);
					/** */
					if(r.getHorasInt()>0) { //ineficiente repintar 2 labeles cada 1 segundo durante 1 hora por eso el if
						ImageIcon graficoHorasIzq, graficoHorasDer;
						derecha=Integer.parseInt(r.getHoras().charAt(1)+"");
						izquierda=Integer.parseInt(r.getHoras().charAt(0)+"");
						graficoHorasIzq = new ImageIcon(this.getClass().getResource(reloj[izquierda]));
						graficoHorasDer = new ImageIcon(this.getClass().getResource(reloj[derecha]));
						lblHoras.setIcon(graficoHorasIzq);
						reDimensionar(lblHoras,graficoHorasIzq);
						lblHoras2.setIcon(graficoHorasDer);
						reDimensionar(lblHoras2,graficoHorasDer);
					}
					
				}
			}};
			timer.schedule(tarea, 0, 300);
	}
	
	/**
	 * Se le asocia un label y un listener a cada celda.
	 */
	public void inicializarLabels() {
		celdas=new JLabel[juego.getCantFilas()][juego.getCantFilas()];
		for (int i = 0; i <juego.getCantFilas(); i++) {
			for(int j =0; j<juego.getCantFilas(); j++) {
				
				
				Celda c = juego.getCelda(i,j);
				
				ImageIcon grafico = c.getEntidadGrafica().getGrafico();
				JLabel label = new JLabel();
				
				label.setEnabled(false);
				panelCeldas.add(label);
				celdas[i][j]=label;
				label.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						reDimensionar(label, grafico);
						label.setIcon(grafico);
					}
				});
				
				panelCeldas.add(label);
				
				if(c.getValor() == null) {//Solo modifico a las celdas con ?
					
					label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(sePuedeJugar)
							c.actualizarValor();
						juego.accionar(c);
						actualizarJuego();
						reDimensionar(label,c.getEntidadGrafica().getGrafico());
					}
					});	
				}
				
			}
		}
	}
	
	
	/** 
	 * Actualiza las celdas.
	 * */
	private void actualizarJuego() {
		Celda recorro;
		for (int i=0;i<juego.getCantFilas();i++) {
			for (int j=0;j<juego.getCantColumnas();j++) {
				recorro = juego.getCelda(i, j);
				if (recorro.getValor()!=null) {
					if (!juego.cumpleCondiciones(i, j)) {
						juego.accionarConError(recorro);
					} else {
						juego.accionar(recorro);
					}
					reDimensionar(celdas[i][j],recorro.getEntidadGrafica().getGrafico());
				}
			}
		}
	}
	
	/** 
	 * Redimensiona el tamaño del grafico en la label
	 * @param label label a dimensionar.
	 * @param grafico grafico a insertar en el label.
	 */
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}
}
