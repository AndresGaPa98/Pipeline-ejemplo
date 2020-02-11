package Automata;

import java.awt.BorderLayout;                   //Librerias utilizadas
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Automata.TextPrompt;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaCompilador extends JFrame {
	
	private JPanel contentPane;
	private JScrollPane scroll;
	private JScrollPane scroll2;
	private JScrollPane scroll3;
	private String [] arreglo;
	String palabras [];
	String [][] lexemas;
	DefaultTableModel modelo;
	 JTable table;
	ExpresionRegular regular  = new ExpresionRegular();
	private boolean [] goodOrBad;
	JTextArea textArea_1;
	TextPrompt promptArea;
	JTextArea textArea_2;
	String lexemaId [][];
	String lexemaIdE [][];
	FileOutputStream salida;
	File archivo;
	JFileChooser select = new JFileChooser();
	JButton btnGuardarToken;
	String path,path2;

	public String cabezera [] = {"Lexema", "Token"};
	public DefaultTableModel modelo2;
	public String cabezera2 [] = {"Lexema", "Token Error","Fila"};
	
	String [][] datos3 = new String [0][4];
	String [][] datos2 = new String [0][3];
	private JTable table_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					VistaCompilador frame = new VistaCompilador();
					frame.setVisible(true);
					frame.setLocation(250, 150);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaCompilador() {
		
		setUndecorated(true);
		setBackground(new Color(0, 0, 128));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(148, 0, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Compilador");
		JTextArea textArea = new JTextArea();
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Arial", Font.BOLD, 12));
		textArea.setBackground(Color.BLACK);
		//textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		promptArea = new TextPrompt("Escriba codigo...", textArea);
		promptArea.setBackground(Color.WHITE);
		promptArea.changeStyle(Font.BOLD);
		promptArea.setForeground(Color.WHITE);
		scroll = new JScrollPane(textArea);
		scroll.setBounds(10, 115, 369, 313);
		scroll.setVisible(true);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);
		textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Arial", Font.BOLD, 12));
		textArea_1.setForeground(Color.BLACK);
		textArea_1.setBackground(Color.WHITE);
		textArea_1.setBounds(550, 55, 222, 305);
		scroll3 = new JScrollPane(textArea_1);
		scroll3.setBounds(400, 115, 352, 313);
		scroll3.setVisible(true);
		scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll3);
		
		textArea_2 = new JTextArea();
		textArea_2.setForeground(Color.RED);
		textArea_2.setFont(new Font("Arial", Font.BOLD, 12));
		textArea_2.setBackground(new Color(0, 255, 255));
		textArea_2.setLineWrap(true);
		textArea_2.setWrapStyleWord(true);
		
		modelo = new DefaultTableModel(datos2,cabezera);
		table = new JTable(modelo);
		table.setModel(modelo);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.getColumnModel().getColumn(0).setMaxWidth(110);
		table.getColumnModel().getColumn(1).setMaxWidth(110);
		scroll3 = new JScrollPane(table);
		
		scroll3.setBounds(762, 115, 212, 147);
		contentPane.add(scroll3);
		
		modelo2 = new DefaultTableModel(datos3,cabezera2);
		table_1 = new JTable(modelo2);
		table_1.setFont(new Font("Arial", Font.PLAIN, 12));
		
		table_1.getColumnModel().getColumn(0).setMaxWidth(110);
		table_1.getColumnModel().getColumn(1).setMaxWidth(110);
		JScrollPane scroll4 = new JScrollPane(table_1);
		
		scroll4.setBounds(762, 289, 212, 139);
		contentPane.add(scroll4);
		textArea_1.setEnabled(false);
		JLabel lbldeseaGuardar = new JLabel("\u00BFDesea guardar?");
		lbldeseaGuardar.setFont(new Font("Arial", Font.BOLD, 15));
		lbldeseaGuardar.setForeground(Color.WHITE);
		lbldeseaGuardar.setBounds(470, 439, 131, 30);
		lbldeseaGuardar.setVisible(false);
		contentPane.add(lbldeseaGuardar);
		
		JButton btnCompilar = new JButton("");
		ImageIcon icon;
		try {
			path = "automatas\\run.png";
			path2 = new File(getClass().getResource("")+path).getPath();
			String arreglo [] = path2.split("compilador.jar!");
			icon = new ImageIcon(ImageIO.read(new File(arreglo[0].substring(10)+path)).getScaledInstance(50, 34, Image.SCALE_DEFAULT));
			btnCompilar.setIcon(icon);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		btnCompilar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EliminarTabla();
				borrarText();
				arreglo = textArea.getText().split("\\r?\\n");                         //de todo el texto hacemos un split de los espacios.
				goodOrBad = new boolean[arreglo.length];                              //la verdad no termine usando esta variable xd
				//System.out.println(arreglo.length);
				for(int i = 0; i<arreglo.length;i++) {
					if(!arreglo[i].equals("")) {
					arreglo[i] = arreglo[i].replaceAll("\\s+", " ");                  //basicamente dejamos con un solo espacio la oracion
											}
					
										
				}
				//textArea.append("\n");
				lexemas = new String [arreglo.length][3];                            // este es el importante ya que aca guardaremos los lexemas
				
				for (int j = 0; j < arreglo.length; j++) {
					palabras = arreglo[j].split(" ");                               //por cada fila obtenemos 3 palabras esperadas xd
					try {
					for (int i = 0; i < palabras.length; i++) {
						
						lexemas [j][i] =  palabras[i];                              //lo guardamos en lexemas
						//textArea_1.append(lexemas[j][i]+" ");
					}
					//textArea_1.append("\n");
					}catch (Exception e) {
					}

				}
				lexemaId = new String [arreglo.length*3][2];                       //aca se guardara el lexema con su ID
				// contadores para los tokens
				int cont = 0;
				int cont2=0;
				int cont3=0;
				try {
				if(!lexemas[0][0].equals("")) {    //basicamente la primera fila se hace manual
				lexemaId[0][0] = lexemas [0][0];
				lexemaId[0][1] = "id"+cont;
				if(!lexemaId[0][0].equals(lexemas[0][1])) {
					cont++;
					lexemaId[cont][0] = lexemas[0][1];
					lexemaId[cont][1] = "Op"+cont2;
					
					cont2++;
				}
				if(!lexemaId[0][0].equals(lexemas[0][2]) && !lexemaId[1][0].equals(lexemas[0][2])) {
					cont++;
					cont3++;
					lexemaId[cont][0] = lexemas[0][2];
					lexemaId[cont][1] = "id"+cont3;
					
					
				}
				}
				}catch (Exception e) {
					// TODO: handle exception
				}
				for (int i = 1; i < arreglo.length; i++) {
					for (int j = 0; j < 3; j++) {
						
						if(j==0 || j ==2) {
											//aca es para la asignacion de lexemas y tokens para la tabla de simbolos
								
								
									try {
										if(!lexemas[i][j].equals("")) {//System.out.println(Repetido(lexemas[i][j],i,j));
												if(Repetido(lexemas[i][j],i,j)) {
												//System.out.println(cont);
													cont+=1;
													cont3++;
												//System.out.println("asdasd");
												lexemaId[cont][0] = lexemas[i][j];
												lexemaId[cont][1]  = "id"+(cont3);
												
												}
											
										}
								
							}catch (Exception e) {
								// TODO: handle exception
							}
								
							
							
						}
						else {
							try {
							if(!lexemas[i][j].equals("")) {
									if(Repetido(lexemas[i][j], i,j)) {
										
										cont+=1;
									lexemaId[cont][0] = lexemas[i][j];
									lexemaId[cont][1] = "Op"+cont2;
									
									cont2++;}
								
								
							
						 }
						}catch (Exception e) {
							// TODO: handle exception
						}
						}
					
				}
				
				
				
				//PARTE PARA IMPRIMIR EN EL TEXTAREA
					
				}
				textArea.append("\n");
				
				
				for (int j = 0; j < arreglo.length; j++) {
					palabras = arreglo[j].split(" ");
					try {
					for (int i = 0; i < palabras.length; i++) {
						
						                 
						textArea_1.append(llenarTexto(lexemas[j][i])+" ");
					}
					textArea_1.append("\n");
					}catch (Exception e) {
					}

				}
				for (int i = 0; i < lexemaId.length*2; i++) {	
					//	PARA VER QUE NO SE REPITA XD
					
					//EliminarTabla();
						try {
							
						if(!lexemaId[i][0].equals(null)) {
							modelo.addRow(lexemaId[i]);
							
						}
						}catch (Exception e) {
							// TODO: handle exception
						
					}
				}
				
				// PARTE PARA TABLA DE ERROR
				int opE = 0;
				int idE = 0;
				lexemaIdE = new String [arreglo.length*3][3];
				for (int i = 0; i < lexemaId.length*2; i++) {
					try {
						
						if(!lexemaId[i][0].equals(null)) {
							
							if(!expresion(lexemaId[i][0]) && !expresionOperadores(lexemaId[i][0])) {
								lexemaIdE[i][0] = lexemaId[i][0];
								if (lexemaId[i][1].substring(0, 2).equals("id")) {
									System.out.println(idE);
									lexemaIdE[i][1] = lexemaId[i][1].substring(0, 2)+idE+"E";
									idE++;
								}else {
									lexemaIdE[i][1] = lexemaId[i][1].substring(0, 2)+opE+"E";
									opE++;
								}
								lexemaIdE[i][2] = filas(lexemaId[i][0]);
								//System.out.println(lexemaIdE[i][0] + "   " +lexemaIdE[i][1]);
							}
							
						}
						}catch (Exception e) {
							// TODO: handle exception
						
					}
				}
				for (int i = 0; i < lexemaIdE.length*3; i++) {	
					//	llenar la tabla de errores
						try {
							
						if(!lexemaIdE[i][0].equals(null)) {
							modelo2.addRow(lexemaIdE[i]);
							
						}
						}catch (Exception e) {
							// TODO: handle exception
						
					}
				}

				btnGuardarToken.setVisible(true);
				lbldeseaGuardar.setVisible(true);
			}
				
				
				
					
		});
		btnCompilar.setBackground(new Color(0, 0, 128));
		btnCompilar.setBounds(10, 437, 49, 32);
		contentPane.add(btnCompilar);
		
		JLabel lblEntrada = new JLabel("ENTRADA:");
		lblEntrada.setForeground(Color.WHITE);
		lblEntrada.setFont(new Font("Arial", Font.BOLD, 32));
		lblEntrada.setBounds(10, 70, 186, 34);
		contentPane.add(lblEntrada);
		
		JLabel lblSalida = new JLabel("SALIDA:");
		lblSalida.setForeground(Color.WHITE);
		lblSalida.setFont(new Font("Arial", Font.BOLD, 32));
		lblSalida.setBounds(400, 67, 186, 34);
		contentPane.add(lblSalida);
		
		JLabel lblTablas = new JLabel("TABLAS:");
		lblTablas.setForeground(Color.WHITE);
		lblTablas.setFont(new Font("Arial", Font.BOLD, 32));
		lblTablas.setBounds(762, 67, 186, 34);
		contentPane.add(lblTablas);
		
		btnGuardarToken = new JButton("");
		btnGuardarToken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(select.showDialog(null, "Guardar")==JFileChooser.APPROVE_OPTION) {
					archivo = select.getSelectedFile();
					if(archivo.getName().endsWith("txt")) {
						String doc = textArea_1.getText();
						String mensaje = GuardarTexto(archivo, doc);
						if(mensaje!=null) {
							JOptionPane.showMessageDialog(null, "Guardado");
						}
					}
				}
					
				} 
				
						
					

		
			
		});
		ImageIcon icon2;
		try {
			path = "automatas\\guardar.png";
			path2 = new File(getClass().getResource("")+path).getPath();
			String arreglo [] = path2.split("compilador.jar!");
			icon2 = new ImageIcon(ImageIO.read(new File(arreglo[0].substring(10)+path)).getScaledInstance(45, 30, Image.SCALE_DEFAULT));
			btnGuardarToken.setIcon(icon2);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnGuardarToken.setBackground(new Color(0, 0, 128));
		btnGuardarToken.setBounds(400, 437, 49, 32);
		btnGuardarToken.setVisible(false);
		contentPane.add(btnGuardarToken);
		
		JLabel lblOmk = new JLabel("");
		ImageIcon icon3;
		try {
			path = "automatas\\cerrar.png";
			path2 = new File(getClass().getResource("")+path).getPath();
			String arreglo [] = path2.split("compilador.jar!");
			icon3 = new ImageIcon(ImageIO.read(new File(arreglo[0].substring(10)+path)).getScaledInstance(45, 45, Image.SCALE_DEFAULT));
			lblOmk.setIcon(icon3);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lblOmk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblOmk.setForeground(Color.WHITE);
		lblOmk.setBackground(Color.RED);
		lblOmk.setBounds(954, 0, 46, 45);
		contentPane.add(lblOmk);
		
		JLabel lblOm = new JLabel("");
		ImageIcon icon4;
		try {
			path = "automatas\\minimizar.png";
			path2 = new File(getClass().getResource("")+path).getPath();
			String arreglo [] = path2.split("compilador.jar!");
			icon4 = new ImageIcon(ImageIO.read(new File(arreglo[0].substring(10)+path)).getScaledInstance(45, 45, Image.SCALE_DEFAULT));
			lblOm.setIcon(icon4);
		} catch (IOException e1) {}
		lblOm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			setExtendedState(ICONIFIED);
			}
		});
		lblOm.setBounds(873, 0, 46, 45);
		contentPane.add(lblOm);
		
		JLabel lblLenguajesYAutomatas = new JLabel("Lenguajes y Automatas I");
		lblLenguajesYAutomatas.setFont(new Font("Arial", Font.BOLD, 33));
		lblLenguajesYAutomatas.setBounds(287, 0, 422, 52);
		contentPane.add(lblLenguajesYAutomatas);
		
		
		
		
		
		
		
	}
	//limpia los textArea
	public void borrarText() {
		textArea_1.setText("");
		textArea_2.setText("");
	}
	// nos retorna true o false en dado caso de que cumpla con la expresion regular
	public boolean expresion(String expresion) {
		return expresion.matches("^([A-Z|$]{1}[A-Z|$|0-9]*)$");
	}
	// nos retorna true o false en dado caso de que cumpla con la expresion regular
	public boolean expresionOperadores(String Operador) {
		return Operador.matches("^([-|+|/|*])$");
	}
	//nos retorna false si esta repetido, sino true para agregar el lexema y el token a lexemaId
	public boolean Repetido(String name, int numero,int m) {
		
				for (int i = 0; i <= numero; i++) {
					for (int j = 0; j < 3; j++) {
						if( i == numero && j ==m) {
							return true;
						}
						else {
						//	System.out.println(name+"      =    " + lexemas[i][j]);
							if(name.equals(lexemas[i][j])) {
								//System.out.println(name + "   " + lexemas[i][j] );
								return false;
							}
							}
						}
					}
			
		return true;

	}
	//resetea las tablas xd
public void EliminarTabla() {
		
		for(int i = modelo.getRowCount();i>=0;i--) {
			try {
			modelo.removeRow(i);}catch (Exception e) {
				// TODO: handle exception
			}
		}
		for(int i = modelo2.getRowCount();i>=0;i--) {
			try {
			modelo2.removeRow(i);}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
//nos retorna el token que le pertenece al lexema, sino un "" (lo cual es imposible xd)
public String llenarTexto(String  texto) {
	for (int i = 0; i < lexemaId.length; i++) {
		for (int j = 0; j < 2; j++) {
			if(texto.equals(lexemaId[i][j])) {
				return lexemaId[i][j+1];
			}
		}
	}
	return "";
}
//retorna la fila donde esta el error
public String filas(String name) {
	String fila ="";
	for (int i = 0; i < lexemas.length; i++) {
		for (int j = 0; j < 3; j++) {
			if(name.equals(lexemas[i][j])) {
				fila +=(i+1)+" ";
			}
		}
	}
	return fila;
}
//nos sirve para guardar el texto
public String GuardarTexto(File archivo, String contenido) {
	String respuesta = null;
		try {
			salida = new  FileOutputStream(archivo);
			byte[] bytxt = contenido.getBytes();
			salida.write(bytxt);
			respuesta = "Se guardo correctamente";
		}catch (Exception e) {
			// TODO: handle exception
		}
	return respuesta;
}
}
