package it.ing.unibs.tamazoo;

public class TamaTriste extends Tamagotchi {

	private final static int FELICITA = 0;
	public TamaTriste() {
		super();
	}

	public TamaTriste(String nome, double sazieta, double soddisfazione) {
		super(nome, sazieta, MIN_SODDISFAZIONE);
	}
	/**
	 * Il TamaTriste rimane in vita indipendantemente dal suo livello di soddisfazione
	 * @see it.ing.unibs.tamazoo.Tamagotchi#inVita()
	 */
	public boolean inVita()
	{
		if(sazieta == MIN_SAZIETA || sazieta == MAX_SAZIETA)
			return false;
		else
			return true;
	}
	public String statoFelicita()
	{
		//La felicità è sempre nulla
		int felicita = FELICITA;
		//In base alla felicità calcolata ritorno lo stato della felicità sotto forma di stringa
		return controllaFelicita(felicita);	
	}
	
	/**
	 * Dà un numero variabile di carezze al TamaTriste
	 *
	 * @param numero di carezze da dare al TamaTriste
	 */
	public void daiCarezze(int carezze)
	{	
		//Diminuisco la sazietà in base al numero di carezze ricevute
		sazieta -= carezze/FATTORE_CAREZZE;	
		Math.max(sazieta, MIN_SAZIETA);
	}
}
