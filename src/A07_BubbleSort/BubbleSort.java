package A07_BubbleSort;


public class BubbleSort implements PersonenSort {

	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {
		Boolean sorted = false;
		int sorted_numbers = 0;
		while (!sorted) {
			sorted = true;
			for (int i = 0; i < personen.length-1-sorted_numbers; i++) {
				Person p = personen[i];
				Person p2 = personen[i+1];
				if(p.compareTo(p2) > 0){
					sorted = false;
					personen[i] = p2;
					personen[i+1] = p;
				}
			}
			sorted_numbers++;
		}
	}
	
}
