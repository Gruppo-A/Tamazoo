package it.ing.unibs.tamazoo;


public class TamaGordo extends Tamagotchi{

	private final static int FATTORE_AUMENTO_FAME = 2;
	public TamaGordo() {
		super();
		
	}

	public TamaGordo(String nome, double sazieta, double soddisfazione) {
		super(nome, sazieta, MAX_SODDISFAZIONE);
	}
	/**
	 *  Il TamaGordo non è in vita solo se la sua sazieta è al livello minimo 
	 * @see it.ing.unibs.tamazoo.Tamagotchi#inVita()
	 */
	public boolean inVita()
	{
		if(sazieta == MIN_SAZIETA )
			return false;
		else
			return true;
	}
	
	/**
	 * @see it.ing.unibs.tamazoo.Tamagotchi#statofelicità()
	 */
	public String statoFelicita()
	{
		//Calcolo la felicità approssimando al valore intero della sazieta
		int felicita =  (int)Math.round(sazieta);
		//In base alla felicità calcolata ritorno lo stato della felicità sotto forma di stringa
		return controllaFelicita(felicita);	
	}
	
	
	
	/**
	 * Dà un numero variabile di carezze al TamaGordo che ne aumenta solamente la fame 
	 *
	 * @param numero di carezze da dare al TamaGordo
	 */
	public void daiCarezze(int carezze)
	{	
		//Diminuisco la sazietà in base al numero di carezze ricevute
		sazieta = FATTORE_AUMENTO_FAME * (sazieta - carezze/FATTORE_CAREZZE);
		Math.max(sazieta, MIN_SAZIETA);
	}
	/**
	 * Dà un numero variabile di biscotti al tamagotchi
	 *
	 * @param il numero di biscotti da dare al tamagotchi
	 */
	public void daiBiscotti(int biscotti)
	{
		double fattore = 0;
		
		for(int i  = 1; i <= biscotti; i++)
		{
			//Questa espressione è utilizzata per ricavare il fattore con il quale aumenta la sazietà.
			//E' stata ricavata sperimentalmente, facendo attenzione a due cose:
			//-il fattore non può essere negativo perché dando dei biscotti è insensato ridurre la sazietà
			//-più la sazietà attuale è grande più il fattore è piccolo (in questo modo teniamo conto della situazione del tamagotchi
			//proprio come si farebbe nella realtà)
			fattore = (FATTORE_ESPRESSIONE - ((getSazieta() - FATTORE_ESPRESSIONE) * DECREMENTO_SAZIETA))/getSazieta();
			sazieta += sazieta*(i*fattore);
			Math.min(sazieta, MAX_SAZIETA);
			
		}
		
	}
	
}
