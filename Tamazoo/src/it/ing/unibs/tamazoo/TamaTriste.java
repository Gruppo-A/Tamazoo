package it.ing.unibs.tamazoo;

import mylib.math.MyMath;

public class TamaTriste extends Tamagotchi {

	public final static int FELICITA = 0;
	public TamaTriste() {
		super();
	}

	public TamaTriste(String nome, double sazieta, double soddisfazione) {
		super(nome, sazieta, soddisfazione);
		soddisfazione = MIN_SODDISFAZIONE;
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
		//Controllo il parametro soddisfazione che deve essere sempre al minimo
		soddisfazione = MIN_SODDISFAZIONE;
	}
}
