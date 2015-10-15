package juego;

public class Mundo {
	private Superficie superficie;
	
	
	
	/**
	 * La clase constructor de Mundo, genera una superficie con una longitud
	 * especifica de filas y columnas, y genera NUMEROCELULAS en posiciones
	 * aleatorias para esa superficie.
	 */
	public Mundo(int f, int c, int n){
		this.superficie = new Superficie(f, c);
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
	 */
	public boolean crearCelulaSuperficie(){
		return true;
	}
	
	/**
	 * 
	 */
	public boolean eliminarCelulaSuperficie(){
		return true;
	}
	
	/**
	 * Generar la celulas en la superficie de forma random
	 */
	public void generarCelulaAleatoria(){
		
	}
}
