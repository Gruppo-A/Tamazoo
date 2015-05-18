package it.ing.unibs.tamazoo;
import mylib.math.MyMath;
import mylib.util.MyMenu;
import mylib.util.MyScanner;

public class ZooMain {
	// Stringhe costanti per il menu principale
	private final static String TITOLO = "TamaZoo";
	private final static String[] vociMenu = { "Inserisci tamagotchi",	"Nutri i tamagotchi", "Coccola i tamagotchi" };
	
	// Stringhe costanti per il menu della selezione della specie
	private final static String TITOLO_SPECIE = "Seleziona la specie";
	private final static String[] vociMenuSpecie = { "Standard",	"TamaGordo", "TamaTriste" };
	
	// Interi costanti per impostare i limiti nella creazione del tamagotchi
	private final static int VAL_MIN = 1;
	private final static int VAL_MAX = 100;
	
	// Interi costanti per impostare i limiti nel nutrimento del tamagotchi
	private final static int BISCOTTI_MIN = 1;
	private final static int BISCOTTI_MAX = 5;
	
	// Interi costanti per impostare i limiti nel coccolamento del tamagotchi
	private final static int CAREZZE_MIN = 1;
	private final static int CAREZZE_MAX = 7;
	
	// Stringhe costanti per eventuali errori
	private final static String ERRORE_NUTRI_ZOO = "Per nutrire i tamagotchi ne deve esistere almeno uno nello zoo!";
	private final static String ERRORE_COCCOLA_ZOO = "Per coccolare i tamagotchi ne deve esistere almeno uno nello zoo!";
	private final static String ERRORE_INSERISCI_TAMA = "Tamagotchi NON inserito! ";
		
	// Stringa costante per annulare l'inserimento di un tamagotchi
	private final static String ANNULLA_INSERIMENTO = "Inserimento annullato!";
	
	// Stringhe costanti di messaggi ripetuti
	private final static String RICHIAMO = "%nATTENZIONE! ";	
	private final static String A_CAPO = "%n";
	private final static String CORNICE = " *** ";
	
	// Stringhe costanti per inserimento dati
	private final static String INSERISCI_NOME_TAMA = "Inserisci il nome del tamagotchi: ";
	
	// Stringa costante per il messaggio di uscita
	private final static String USCITA_TAMAZOO = "Sei davvero sicuro di volere uscire da TamaZoo?";
	
	private static TamaZoo tamaZoo = new TamaZoo();	

	public static void main(String[] args) {
			// Variabile utilizzata per la condizione di uscita dal ciclo principale
			// del programma
			boolean continua = true;
			
			// Creazione del menu principale
			MyMenu menu = new MyMenu(TITOLO, vociMenu);

			do {
				// Il menu principale
				// lo switch è effettuato sulla scelta della voce di menu
				// dell'utente
				switch (menu.seleziona()) {
				case 1:
					// Provo ad aggiungere un tamagotchi
					aggiungiTama();
					break;
				case 2:
					nutriZoo();					
					break;
				case 3:
					coccolaZoo();					
					break;
				case 4:
					continua = !(MyScanner.si_No(USCITA_TAMAZOO));
				}
			} while (continua);

		}

	private static void aggiungiTama() {		
		// Il tamagotchi da inserire nello Zoo
		Tamagotchi tama = null;
		// I valori casuali di sazietà e soddisfazione
		int sazieta = MyMath.randomIntero(VAL_MIN, VAL_MAX);
		int soddisfazione = MyMath.randomIntero(VAL_MIN, VAL_MAX);

		String lettura = MyScanner.leggiStringaNonVuota(String.format(A_CAPO + INSERISCI_NOME_TAMA));
		// Variabile utilizzata per la condizione di uscita dal sottomenu
		boolean continua = true;
		// Creazione del menu di selezione della specie
		MyMenu menuSpecie = new MyMenu(TITOLO_SPECIE, vociMenuSpecie);

		do {
			switch (menuSpecie.seleziona()) {
			case 1:
				tama = new Tamagotchi(lettura, sazieta, soddisfazione);
				continua = false;
				break;
			case 2:
				tama = new TamaGordo(lettura, sazieta, soddisfazione);
				continua = false;
				break;
			case 3:
				tama = new TamaTriste(lettura, sazieta, soddisfazione);
				continua = false;
				break;
			case 4:
				System.out.println(String.format(ANNULLA_INSERIMENTO + A_CAPO));
				// Torno al menu iniziale
				return;			
			}
			
		} while (continua);

		try {
			tamaZoo.aggiungiTama(tama);
		} catch (IllegalArgumentException e) {
			System.out.println(String.format(RICHIAMO + ERRORE_INSERISCI_TAMA + e.getMessage() + A_CAPO));
		}
	}

	private static void nutriZoo() {
		if (tamaZoo.getTamaRimanenti() > 0) {
			// Il valore casuale di biscotti da dare a tutti i tamagotchi
			int biscotti = MyMath.randomIntero(BISCOTTI_MIN, BISCOTTI_MAX);
			tamaZoo.daiBiscotti(biscotti);
			// Visualizzo il nuovo stato dei tamagotchi dello zoo
			visualizzaTamaZoo();
		} else {
			System.out.println(String.format(RICHIAMO + ERRORE_NUTRI_ZOO + A_CAPO));
		}
	}
	
	private static void coccolaZoo() {
		if (tamaZoo.getTamaRimanenti() > 0) {
			// Il valore casuale di carezze da dare a tutti i tamagotchi
			int carezze = MyMath.randomIntero(CAREZZE_MIN, CAREZZE_MAX);
			tamaZoo.daiBiscotti(carezze);
			// Visualizzo il nuovo stato dei tamagotchi dello zoo
			visualizzaTamaZoo();
		} else {
			System.out.println(String.format(RICHIAMO + ERRORE_COCCOLA_ZOO + A_CAPO));
		}
	}
		
	private static void visualizzaTamaZoo() {

		System.out.println(String.format(A_CAPO + CORNICE + TITOLO.toUpperCase() + CORNICE));
		System.out.println(String.format(tamaZoo.toString()));

	}

}

