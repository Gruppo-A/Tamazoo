package it.ing.unibs.tamazoo;

import java.util.LinkedList;

/**
 * Classe TamaZoo nella quale vengono definite le azioni che si possono compiere
 * con un archivio di tamagotchi di più specie.
 * 
 * @author Marco Cadei, Luca Festoni, Antonello Zanini
 *
 */

public class TamaZoo {
	LinkedList<Tamagotchi> tamaZoo;

	// Stringhe costanti per le eccezioni
	private final static String ERRORE_TAMA_NULL = "Il tamagotchi che stai cercando di aggiungere deve esistere!";
	private final static String ERRORE_TAMA_DOPPIO = "Non puoi aggiungere due tamagotchi con lo stesso nome";
	
	// Stringa costante per il toString()
	private final static String DESCRIZIONE_ZOO = "%s%n";
	private final static String STATO_TAMA = "%nSTATO TAMAGOTCHI %d:%n";
	private final static String TAMA_RIMANENTI = "%nNumero tamagotchi deceduti: %d. Numero tamagotchi rimanenti: %d";
	private final static String NO_TAMA_RIMANENTI = "%nATTENZIONE! Tutti i tamagotchi sono deceduti...";
	
	private final static int ZOO_VUOTO = 0;
	
	/**
	 * Costruttore di default per la creazione di uno zoo di tamagotchi
	 */
	public TamaZoo() {
		tamaZoo = new LinkedList<>();
	}

	/**
	 * Permette di aggiungere una gara allo zoo
	 * 
	 * @param tama
	 *        il tamagotchi da aggiungere allo zoo
	 * 
	 * @throws IllegalArgumentException
	 *         - se il tamagotchi è null 
	 *         - se esiste già un tamagotchi con
	 *           lo stesso nome nello zoo
	 */
	public void aggiungiTama(Tamagotchi tama) throws IllegalArgumentException {
		if (tama == null)
			throw new IllegalArgumentException(ERRORE_TAMA_NULL);

		for (Tamagotchi t : tamaZoo) {
			if (t.getNome().equalsIgnoreCase(tama.getNome()))
				throw new IllegalArgumentException(ERRORE_TAMA_DOPPIO);
		}

		tamaZoo.add(tama);
	}

	/**
	 * Permette di nutrire tutti i tamgotchi dello zoo con un numero uguale di
	 * biscotti
	 * 
	 * @param numBiscotti
	 *        il numero di biscotti dato ad ogni singolo tamagotchi
	 */
	public void daiBiscotti(int numBiscotti) {
		for (Tamagotchi tama : tamaZoo)
			tama.daiBiscotti(numBiscotti);
	}

	/**
	 * Permette di coccolare tutti i tamgotchi con un numero uguale di
	 * carezze
	 * 
	 * @param numCarezze
	 *        il numero di carezze da dare ad ogni singolo tamagotchi
	 */
	public void daiCarezze(int numCarezze) {
		for (Tamagotchi tama : tamaZoo)
			tama.daiCarezze(numCarezze);
	}
	
	/**
	 * Aggiorna lo stato dello zoo eliminando i tamagotchi deceduti
	 * e ritorna il numero di tamagotchi in vita rimanenti
	 * 
	 * @return il numero di tamagotchi in vita nello zoo
	 */	
	public int getTamaRimanenti() {
		// Se lo zoo non contiente tamagotchi ritorno
		// il valore 0
		if(tamaZoo.isEmpty())
			return ZOO_VUOTO;
		
		for (Tamagotchi tama : tamaZoo)
			if(!tama.inVita())
				tamaZoo.remove(tama);
		
		return tamaZoo.size();
	}
	
	@Override
	public String toString() {
		// Differenza tra il numero di tamagotchi originario e quello attuale
		int tamaMorti = tamaZoo.size() - getTamaRimanenti();

		if (getTamaRimanenti() == 0)
			return String.format(NO_TAMA_RIMANENTI);
		else {
			StringBuilder descrizione = new StringBuilder();
			for (int i = 1; i <= tamaZoo.size(); i++) {
				descrizione.append(String.format(STATO_TAMA, i));
				descrizione.append(String.format(DESCRIZIONE_ZOO, tamaZoo.get(i - 1).toString()));
			}
			descrizione.append(String.format(TAMA_RIMANENTI, tamaMorti,	getTamaRimanenti()));

			return descrizione.toString();
		}
	}	
}
