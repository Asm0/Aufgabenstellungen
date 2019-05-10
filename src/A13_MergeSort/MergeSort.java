package A13_MergeSort;

import java.util.Arrays;


public class MergeSort implements PersonenSort {
	
	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {
		// Start des rekursiven Aufrufs
		sort(personen, 0, personen.length-1);
	}

	/**
	 * Rekursive Funktion zum Sortieren eines Teils des Arrays
	 * @param personen Zu sortierendes Array
	 * @param start    Startpunkt im Array
	 * @param end      Endpunkt im Array
	 */
	public void sort(Person[] personen, int start, int end)
	{
		int mitte = end/2;
		
		// Für Merge: Hälften in eigene Arrays kopieren
		// Hinweis: bei copyOfRange ist Obergrenze exklusiv, deshalb "+ 1"
		if(personen.length <= 1) {
			return;		// Wenn Personen Array nur mehr einen Wert hat, brich Rekursion ab
		}
			Person[] teil1 = Arrays.copyOfRange(personen, start, mitte+1);
			Person[] teil2 = Arrays.copyOfRange(personen, mitte+1, end+1);

			sort(teil1, 0, teil1.length-1);
			sort(teil2, 0, teil2.length-1);

		// Beide Hälften zusammenfügen und in data-Array schreiben
		merge(teil1, teil2, personen, start);
	}

	/**
	 * Merge zweier Arrays in ein Ergebnis-Array
	 * @param pers1 Erstes Array
	 * @param pers2 Zweites Array
	 * @param result Ergebnisarray
	 * @param start  Position für Speicherung in Ergebnisarray
	 */
	public void merge(Person[] pers1, Person[] pers2, Person[] result, int start) {
		int leftInd = 0;
		int rightInd = 0;
		int resultInd = 0;
		while (leftInd < pers1.length && rightInd < pers2.length) {
			if(pers1[leftInd].compareTo(pers2[rightInd]) <= 0){
				result[resultInd] = pers1[leftInd];
				leftInd++;
			} else {
				result[resultInd] = pers2[rightInd];
				rightInd++;
			}
			resultInd++;
		}
		while (leftInd < pers1.length) {
			result[resultInd] = pers1[leftInd];
			leftInd++;
			resultInd++;
		}
		while (rightInd < pers2.length) {
			result[resultInd] = pers2[rightInd];
			rightInd++;
			resultInd++;
		}
	}

}
