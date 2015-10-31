package juego;


public class Mundo{
	
	private Superficie superficie;

		
	/**
	 * La clase constructor de Mundo, genera una superficie con una longitud
	 * especifica de filas y columnas, y genera NUMEROCELULAS en posiciones
	 * aleatorias para esa superficie.
	 */
	public Mundo(){
		this.superficie = new Superficie(Constantes.NUMEROFILAS, Constantes.NUMEROCOLUMNAS);
		this.generarCelulas();
	}
	/**
	 * 
	 */
	public void generarCelulas(){
	 int contCelulas = 0;
	 while (contCelulas < Constantes.NUMEROCELULAS){
		 int f = (int) (Math.random()* Constantes.NUMEROFILAS);
		 int c = (int) (Math.random()* Constantes.NUMEROCOLUMNAS);
		 if (this.superficie.casillaVacia(f, c)) {
			 this.superficie.llenarCasilla(f, c);
			 contCelulas++;
		 }
	 }
	}
	
	public String toString(){
		return superficie.toString();
	}
	
	/**
	 * Metodo que evoluciona segun las reglas de la vida.
	 * Si la celula se puede mover a otra casilla aleatoria colindante a ella, entonces deja una nueva celula
	 * Si no puede moverse, tiene un maximo de paso para poder hacerlo, si no muere
	 */
	public void evoluciona(){		
		boolean [][] movido = new boolean [this.getFilas()][this.getColumnas()];
		for(int i = 0; i < this.getFilas(); i++){
    		for(int j = 0; j < this.getColumnas(); j++){
    			movido[i][j] = false;
		    }
		}
		for(int i = 0; i < this.getFilas(); i++){
    		for(int j = 0; j < this.getColumnas(); j++){
    			if (!superficie.casillaVacia(i, j) && !morir(i, j) && !movido[i][j]){
    				//Obtener la nueva posicion (f,c)
    				Casilla casilla = this.posicionesVacias(i, j);
    				int f = casilla.getFila();
    				int c = casilla.getColumna();		
					if (f != -1 && c != -1){ //Se puede mover (TIENE SITIO LIBRE)
    					superficie.decrementarRep(i, j);
    					if (!reproducirse(i,j,f,c)){// reproducirse, si no ejecuta solo el movimiento de la celula
    						System.out.println("Movimiento de (" + i + "," + j + ") a (" + f + "," + c + ")");
	    					superficie.moverCelula (f, c, i, j);
    					}
    					//Nueva posicion a la que se mueve la celula
    					movido[f][c]= true;
    				}
    				else {
	    				//Si no se puede mover y esta por reproducirse, la celula muere
	    				if (superficie.getReproducir(i,j) < 0){
	    					superficie.vaciarCasilla(i, j);
	    					System.out.println("Muere la celula de la casilla (" + i + "," + j + ") por no poder reproducirse");
	    				}
    					else {
    						superficie.decrementarSinMover(i, j);
    					}
    				}			
    			}
			}
		}
	}
	/**
	 * Se pasan la posicion de la celula a comprobar si se reproduce, moviendo la celula y creando la nueva celula en la 
	 * posicion antigua si lo hace, tambien reinicia el contador de pasosReproduccion de la celula
	 * @param i Entero que representa la fila de la celula a reproducirse
	 * @param j Entero que representa la columna de la celula a reproducirse
	 * @param f Entero que representa la fila de la nueva celula creada por la reproduccion
	 * @param c Entero que representa la columna de la nueva celula creada por la reproduccion
	 * @return TRUE Si se ha reproducido la nueva celula, FALSE si no
	 */
	private boolean reproducirse(int i, int j, int f, int c) {
		boolean hecho = false;
		if (superficie.getReproducir(i,j) < 0){
			System.out.println("Movimiento de (" + i + "," + j + ") a (" + f + "," + c + ")");
			superficie.reiniciarReproducir(i, j);
			superficie.moverCelula (f, c, i, j);
			this.crearCelulaSuperficie(i,j);
			System.out.println("Nace nueva celula en (" + i + "," + j + ")" + " cuyo padre ha sido (" + f + "," + c + ")");
			hecho = true;
		}
		return hecho;
	}
	/**
	 * Elimina la celula si cumple la condicion de que SinMovimientos sea menor que 0, dejando su casilla libre
	 * @param f Entero que contiene la fila de la celula
	 * @param c Entero que contiene la columna de la celula
	 * @return TRUE Si se han cumplido las condiciones y se elimina la celula, FALSE si no se cumplen
	 */
	private boolean morir(int f, int c){
		boolean hecho = false;
		if (superficie.getSinMover(f, c) == 0){
			superficie.vaciarCasilla(f, c);
			System.out.println("Muere la celula de la casilla (" + f + "," + c + ") por inactividad");
			hecho = true;
		}
		return hecho;
	}
	/**
	 * Busco las posiciones vacias que hay alrededor de la celula, las guarda en un array de tipo casilla y elijo aleatoriamente
	 * una de las posiciones de todas las disponibles para devolver
	 * @param f Entero que representa la fila de la celula
	 * @param c Entero que representa la columna de la celula
	 * @return La casilla seleccionada aleatoriamente
	 */
	public Casilla posicionesVacias(int f, int c){ 
		int cont=0;
		int [] fila = {-1, 0, 1};
		Casilla[] casilla = new Casilla[8];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				int nf = f + fila[i];
				int nc = c + fila[j];
				if (nf < 0 || nf >= superficie.getFilas()){
					nf = f;
				}
				if (nc < 0 || nc >= superficie.getColumnas()){
					nc = c;
				}
				if(superficie.casillaVacia(nf,nc)){
					casilla[cont] = new Casilla(nf, nc);
					cont++;
				}	
		 	}
		}
		//Indica que no hay casillas vacias
		if (cont == 0){
			casilla[cont] = new Casilla(-1, -1);
		}
		return casilla[(int) (Math.random() * cont)]; 
	}
	
	

	/**
	 * Metodo que vacia el tablero de celulas
	 */
	public void vaciar(){
		superficie.reset();
	}
	/**
	 * Crea una celula en la posicion (f,c) de la superficie
	 * @param f Valor entero positivo fila de la matriz
	 * @param c Valor entero positivo columna de la matriz
	 * @return TRUE se ha hecho el proceso de crear la celula
	 */
	public boolean crearCelulaSuperficie(int f, int c){ 
		return superficie.llenarCasilla(f, c);  
	}
	
	/**
	 * Elimina la celula en la posicion (f,c) de la superficie
	 * @param f Valor entero positivo fila de la matriz
	 * @param c Valor entero positivo columna de la matriz
	 * @return TRUE si se ha hecho el proceso de eliminar la celula
	 */
	public boolean eliminarCelulaSuperficie(int f, int c){
		return superficie.vaciarCasilla(f,c);
	}
	
	/**
	 * Metodo que devuelve el valor entero positivo de las filas de la superficie
	 * @return valor entero positivo de las filas de la Superficie en el Mundo
	 */
	public int getFilas(){
		return this.superficie.getFilas();
	}
	/**
	 * Metodo que devuelve el valor entero positivo de las columnas de la superficie
	 * @return valor entero positivo de las columnas de la Superficie en el Mundo
	 */
	public int getColumnas(){
		return this.superficie.getColumnas();
	}
	
}

