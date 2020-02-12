import java.io.*;
import java.util.Arrays;
public class Grafos {

	public  static int numNodos;
	public static  int [] Nodo;
	public static int [][] Matriz;
	static Boolean  [] conectado, ConColor;

	public Grafos(int numNodos) {
		this.numNodos = numNodos;
		Nodo = new int [numNodos]
	}

	public  void MostrarDatos() {
		System.out.println("Total de nodos: " + (Nodo.length));
	}

	public int [][] MatrizAdy(){
		Matriz = new int[numNodos][numNodos];
		for(int i = 0; i < numNodos; i++) {
			for(int j = 0; j < numNodos; j++) {
				if(i == j) {
					Matriz[i][j] = 0;
				}else {
					Matriz[i][j] = (int)(Math.random()*2);
				}
			}
			for(int j = 0; j< numNodos; j++) {
				if(Matriz[i][j] == 1) {
					Matriz[j][i] = 1;
				}
				if(Matriz[i][j] == 0) {
					Matriz[j][i] = 0;
				}
			}
		}
		return Matriz;
	}

	public void MostrasLista(int n[][]) {
		for(int i = 0; i< numNodos; i++) {
			System.out.print(i+" ---> (");
			for(int j = 0; j< numNodos; j++) {
				//System.out.print(j);
				if(n[i][j]==1) {
					System.out.print(" "+j );
				}
			}
			System.out.println(")");
		}
	}

	public int Grado(int n[][]) {
		int grado = 0, bandera = 0;//busca con ayuda de el grado mayor.
		for(int i = 0; i< numNodos; i++) {
			for(int j = 0; j< numNodos; j++) {

				if(n[i][j]==1) {
					bandera++;
				}
			}
			if(bandera>grado) {
				grado = bandera;
			}
			bandera =0;
		}
		return grado;
	}
	public static int [] LlenarNodosCeros(){
		for(int i = 0; i < numNodos; i++){
			Nodo[i] = 0; // como dice su nombre llenara los nodos de puro color 0
			}
		return Nodo;
		}
	public static Boolean [] llenarFalse(){
		ConColor = new Boolean [numNodos];// como dice llenara el arreglo de booleanos a false.
		for(int i = 0; i < numNodos; i++){

			ConColor[i] = false;
			}
		return ConColor;
		}
	public static boolean ComprobadorCon(int x, int y){
		if(Matriz[x][y] == 1)//basicamente este metodo nos dice si esta o no conectado nuestros nodos.
			// y nos pide como parametro  x,y
			return true;
			else
				return false;
		}
	public static  int [] ColoresAsignados(int [][] n, int [] m,  Boolean [] x ){
		
		m[0] = 0;//se le asigna el color 0
		for(int i = 0; i < numNodos; i++){
			for(int j = 0; j < numNodos; j++){

				if(n[i][j] == 1 && x[j]==false){
					m[j] = m[j] + 1; // si esta conectado aumentara
					// esto puede traer problemas ya que no dara el color minimo xd
					// por eso es necesario el otro metodo magico llamado Verificacion.
					x[i] = true;//se le asigna true para que ya no tome en cuenta el nodo :v.
					}
				
				}
			}
			return m;
		}
	public static int [] Verificacion(int [] nodo){
		//este metodo hace magia negra NO TOCAR.
		int cont = 0;// se inicia con esta variable la cual nos servira para que se le asigne tamaño a obo.
		int [] obo; // obo nos servira para recuperar los datos de los nodos vecinos los cuales despues con ayuda
					//de un sort los acomodaremos.
		 for(int i = 0; i < numNodos; i++){
		for(int j = 0; j < numNodos; j++){
		if(i > 1 ){
			if(ComprobadorCon(j, i-1)==true && nodo[j] == nodo[i-1] && nodo[i] != 0){
					nodo[j] +=1; 	//se inicia buscando que si estan conectados y son iguales se le aumentara
					//el cual todavia nos seguira dando nodos iguales xd.
					}
			}
		}
		}
		 //Aca es donde viene la magia negra :,,,v
		 for(int i = 0; i < numNodos; i++){
		for(int j = 0; j < numNodos; j++){
			if(ComprobadorCon(i,j) == true && nodo[i]==nodo[j]){//se inicia viendo si estan conectados 
				   												//y tienen el mismo color
				for(int k = 0; k<i; k++){
					if(ComprobadorCon(i,k) == true){ //se creo otro for para ver si tambien tienen el mismo color :v
						cont ++; //entonces si estan conectados cont aumentara 
						}
					}
					obo = new int [cont]; //esto es lo que dije obo tendra el tamaño de todos los que estan conectados
										//en la posicion  i
						int aux = 0; //se crea este auxiliar :,v
					for(int z = 0; z<i;z++){

						if(ComprobadorCon(i,z)==true){ //volvemos a comprobar xdd
							obo[aux] = nodo[z];//y los que estan conectados se le agrega a obo
												//lo cual no faltara ninguno ya que obo esta del tamaño de cont 
												//el cual conto a todos los vecinos
							aux++;
							}
						}
						Arrays.sort(obo); //aca con la ayuda del sort ordenamos 
											//los colores de los vecinos de menor a mayor
						
						//ya casi acabamos :v
						for(int w = 0; w< cont; w++){
							if(w<(cont-1)){ //sirve para que no se pase :,v
								if((obo[w+1] - obo[w]) >1){//Aca como ya estan ordenados si la posicion siguiente 
														//se la restamos a la posicion actual y es mayor a 1
														//se le actualiza el color para que sea el menor :v
									nodo[i] = obo[w] +1; //por lo tanto el nodo se le pone el tamaño de obo +1
									}
								}
							}
							
				}
			}
			cont = 0; //finalizamos reiniciando cont para evitar problemas.
		}
		return nodo;
	}

		public static void v(int n[]) {
			int cont = 0;
			int donde = 0;
			int conquien = 0;
			for(int i = 0; i < numNodos; i++){
				for(int j = 0; j < numNodos; j++){
					
									
						if(ComprobadorCon(i, j)==true && n[j] == n[i]){//si estan conectados y tienen el mismo color
						cont ++; // el contador aumentara por lo tanto algo esta mal :v.
						donde = j; //donde y conquien diran la posicion donde estuvo mal.
						conquien = i;
								}
							
					}
				}
			if(cont >0) 
				System.out.println("hay." + donde+ "  "+conquien);
			else
				System.out.println("No hay.");
		}

		public void GenerarArchivo() {
			try {
			FileWriter Fw = new FileWriter("Grafo.txt"); 
			PrintWriter PW = new PrintWriter(Fw);
			PW.println("Nodo-color");
			for(int i =0; i<numNodos; i++) {
			PW.println(i +"-"+Nodo[i]);
			}
			PW.close();
			Fw.close();
			}catch (Exception e) {}
		}
	
	public static void main(String [] args) {
		long  begin = System.currentTimeMillis();
		int x = (int)(Math.random()*100+1);
	//	System.out.println(x);
		Grafos grafo = new Grafos(x);
		System.out.println("Grafo generado.");
		grafo.MostrarDatos();
		grafo.MatrizAdy();
		grafo.MostrasLista(grafo.Matriz);
		System.out.println("El grado del grafo es: "+ grafo.Grado(grafo.Matriz));

		v(Verificacion(ColoresAsignados(grafo.Matriz, LlenarNodosCeros(), llenarFalse())));
		long end = System.currentTimeMillis() - begin;
		System.out.print(end+" milisegundos.");
		grafo.GenerarArchivo();
	}//2106  2478
}
