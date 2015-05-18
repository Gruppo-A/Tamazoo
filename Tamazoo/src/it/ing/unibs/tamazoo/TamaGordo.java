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
	
	/*
	 * Controlla i valori di sazieta e soddisfazione ogni qual volta che vengano modificati. 
	 * In questo modo essi non potranno andare né oltre il massimo né sotto il minimo prestabilito.
	 */
	private void controllaValori()
	{
		//Controllo il parametro sazieta
		if(sazieta < MIN_SAZIETA)
			sazieta = MIN_SAZIETA;
		else if(sazieta > MAX_SAZIETA)
			sazieta = MAX_SAZIETA;
		//Controllo il parametro soddisfazione che deve essere sempre al massimo
			soddisfazione = MAX_SODDISFAZIONE;
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
		controllaValori();
	}
}
