package juego;

public class Mundo {
	private Superficie superficie;
	
	
	
/**
 * 
 * @param nf valor entero positivo que indica el numero de filas de la superficie
 * @param nc valor entero positivo que indica el numero de columnas de la superficie
 * @param n numero entero positivo de celulas que se generan en la superficie
 */
	public Mundo(int nf, int nc, int n){
		this.superficie = new Superficie(nf, nc);
		for (int i = 0; i < n; i++){
			generarCelulaAleatoria();
		}
	}
	/**
	 * para cada c�lula de la superficie ejecuta un paso de acuerdo a las
		reglas que se describieron en la Secci�n 2. El m�todo evoluciona recorre la superficie
		y dependiendo de las reglas de la vida, va pidi�ndola a �sta que realice los pasos
		pertinentes. Ten en cuenta que este m�todo es el que determina c�mo se mueven las
		c�lulas, las coloca, comprueba si mueren o se reproducen, etc.. Para ello tendr� que
		dar las �rdenes pertienentes a la superficie para poder consultar la informaci�n sobre
		las c�lulas. Por otro lado cuando el mundo evoluciona, hay que controlar que una
		c�lula no se mueva dos veces en el mismo paso de evoluci�n.
	 */
	public void evoluciona(){
		
	}
	
	/**
	 * 
	 * @param f
	 * @param c
	 * @return
	 */	  
	public boolean crearCelulaSuperficie(int f, int c){
		return true;
	}
	
	/**
	 * 
	 * @param f
	 * @param c
	 * @return
	 */	 
	public boolean eliminarCelulaSuperficie(int f, int c){
		return true;
	}
	
	/**
	 * Generar la celulas en la superficie de forma random
	 */
	public void generarCelulaAleatoria(){
		
	}
}
