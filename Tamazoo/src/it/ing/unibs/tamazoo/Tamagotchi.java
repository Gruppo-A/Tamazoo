package it.ing.unibs.tamazoo;

import mylib.math.*;

/**Classe Tamagotchi nella quale vengono definite le azioni che è in grado di compiere il tamagotchi e le sue proprietà.
 * 
 * @author Marco Cadei, Luca Festoni, Antonello Zanini
 *
 */
public class Tamagotchi {
	
	//attributi del tamagotchi
	protected double sazieta;
	protected double soddisfazione;
	private String nome;

	//Queste costanti sono utilizzate per fare in modo che la sazieta e la soddisfazione aumentino di una percentuale 
	//minore man mano che si danno biscotti al tamagotchi.
	private final static double DECREMENTO_SAZIETA = 0.0075;
	private final static double DECREMENTO_SODDISFAZIONE =  0.009;
	private final static double FATTORE_ESPRESSIONE = 1;
	
	//Queste 4 costanti sono pubbliche perché vengono utilizzate nel main (in fase di lettura dei valori iniziali) 
	//per evitare che l'utente crei un tamagotchi con dei valori iniziali incosistenti
	public final static int MAX_SAZIETA = 100;
	public final static int MAX_SODDISFAZIONE  = 100;
	public final static int MIN_SAZIETA = 0;
	public final static int MIN_SODDISFAZIONE  = 0;
	
	//Valori utilizzati per diminuire i valori di sazietà e soddisfazione rispettivamente quando vengono date
	//carezze e biscotti
	protected final static double FATTORE_CAREZZE = 2;
	private final static double FATTORE_BISCOTTI = 4;
	
	//Valori di defualt del tamagotchi nel caso in cui venga chiamato il costruttore senza parametri
	private final static int SAZIETA_DEFAULT = 50;
	private final static int SODDISFAZIONE_DEFAULT = 50;
	private final static String NOME_DEFAULT = "default";
	
	//Descrizione generale del tamagotchi
	private final static String DESCRIZIONE = "Ciao, mi chiamo %s.%n->La mia sazietà è: %-3.2f.%n->La mia soddisfazione è: %-3.2f%n";	
	//Messaggio di decesso del tamagotchi
	private final static String OUTPUT_MORTE = "->Il tamagotchi %s è deceduto";
	
	//Stringhe utilizzate per comporre lo stato di felicità del tamagotchi
    private final static String INTRO_FELICITA = "In questo momento sono ";
	private final static String FELICITA_BASSA = "triste...";
	private final static String FELICITA_MEDIA = "abbastanza felice.";
	private final static String FELICITA_BUONA = "felice.";
	private final static String FELICITA_ALTA = "molto felice!";
		

	/**
	 * Costruttore principale della classe Tamagotchi.
	 *
	 * @param nome
	 *        nome del tamagotchi
	 * @param sazieta 
	 *        sazietà iniziale del tamagotchi
	 * @param soddisfazione
	 *        soddisfazione iniziale del tamagotchi
	 */
	public Tamagotchi(String nome, double sazieta, double soddisfazione)
	{
		this.nome = nome;
		this.sazieta = sazieta;
		this.soddisfazione = soddisfazione;
	}
	
	/**
	 * Costruttore senza parametri della classe Tamagotchi
	 */
	public Tamagotchi()
	{
		this(NOME_DEFAULT,SAZIETA_DEFAULT, SODDISFAZIONE_DEFAULT);
	}
	
	/**
	 * Getter dell'attributo nome
	 *
	 * @return il nome del tamagotchi
	 */
	public String getNome()
	{
		return nome;
	}
	
	/**
	 * Setter dell'attributo nome
	 *
	 * @param nome del tamagotchi
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	/**
	 * Getter dell'attributo sazieta
	 *
	 * @return la sazieta del tamagotchi
	 */
	public double getSazieta() {
		return sazieta;
	}

	/**
	 * Getter dell'attributo soddisfazione
	 *
	 * @return la soddisfazione del tamagotchi
	 */
	public double getSoddisfazione() {
		return soddisfazione;
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
			
		}
		//Diminuisco la soddisfazione in base al numero di biscotti ricevuti
		soddisfazione -= biscotti/FATTORE_BISCOTTI;
		controllaValori();
	}
	
	/**
	 * Dà un numero variabile di carezze al tamagotchi
	 *
	 * @param numero di carezze da dare al tamagotchi
	 */
	public void daiCarezze(int carezze)
	{	
		double fattore = 0;
		
		for(int i = 1; i <= carezze; i++)
		{
			//Questa espressione è utilizzata per ricavare il fattore con il quale aumenta la soddisfazione.
			//E' stata ricavata sperimentalmente, facendo attenzione a due cose:
			//-il fattore non può essere negativo perché dando delle carezze è insensato ridurre la soddisfazione
			//-più la soddisfazione attuale è grande più il fattore è piccolo (in questo modo teniamo conto della situazione del tamagotchi
			//proprio come si farebbe nella realtà)
			fattore = (FATTORE_ESPRESSIONE - ((getSoddisfazione() - FATTORE_ESPRESSIONE) *DECREMENTO_SODDISFAZIONE))/getSoddisfazione();
			soddisfazione += soddisfazione*(i*fattore);		
		}
		//Diminuisco la sazietà in base al numero di carezze ricevute
		sazieta -= carezze/FATTORE_CAREZZE;	
		controllaValori();
	}
	
	/**
	 * Controlla se il tamagotchi è vivo
	 *
	 * @return true se il tamagotchi è vivo, false altrimenti
	 */
	public boolean inVita()
	{
		if((sazieta == MIN_SAZIETA || sazieta == MAX_SAZIETA) || soddisfazione == MIN_SODDISFAZIONE)
			return false;
		else
			return true;
	}
	
	/**
	 * Controlla se il tamagotchi è felice
	 *
	 * @return true se il tamagotchi è felice, false altrimenti
	 */
	public String statoFelicita()
	{
		//Calcolo la felicità come media di soddisfazione e sazietà
		int felicita = (int)Math.round(MyMath.mediaAlgebrica(soddisfazione, sazieta));
		//In base alla felicità calcolata ritorno lo stato della felicità sotto forma di stringa
		return controllaFelicita(felicita);	
	}
	
	@Override
	public String toString()
	{
		StringBuffer descrizione = new StringBuffer();
		
		//Se il tamagotchi non è più in vita viene stampato il messaggio di morte
		if(!inVita())
		{
			descrizione.append(String.format(OUTPUT_MORTE, getNome()));
		}
		else
		{	
			//Se è ancora vivo, vengono stampate informazione riguardanti la sua sazieta, la sua soddisfazione
			// e il suo stato di felicità	
			descrizione.append(String.format(DESCRIZIONE, nome, sazieta, soddisfazione));
		    descrizione.append(statoFelicita());			
		}
		//Se il tamagotchi dovesse essere morto allora non ci sarebbero informazioni
		//riguardanti la sua sazieta, la sua soddisfazione e il suo stato di felicità
		return descrizione.toString();
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
		//Controllo il parametro soddisfazione
		if(soddisfazione < MIN_SODDISFAZIONE)
			soddisfazione = MIN_SODDISFAZIONE;
		else if(soddisfazione > MAX_SODDISFAZIONE)
			soddisfazione = MAX_SODDISFAZIONE;
	}
	
	/**
	 * Controlla i valori della felicita media e ritorna una stringa che descrive lo stato attuale di 
	 * felicità del tamagotchi
	 */
	protected String controllaFelicita(int felicita)
	{
		StringBuffer statoFelicita = new StringBuffer(INTRO_FELICITA);
		//Controllo del valore della felicita media per la scelta del suo stato di felicita
		if(felicita >= 0 && felicita <= 25)
			statoFelicita.append(FELICITA_BASSA);
		else if(felicita > 25 && felicita <= 50)
			statoFelicita.append(FELICITA_MEDIA);
		else if(felicita > 50 && felicita <= 75)
			statoFelicita.append(FELICITA_BUONA);
		else if(felicita > 75 && felicita <= 100)
			statoFelicita.append(FELICITA_ALTA);
		
		return statoFelicita.toString();
	}
}