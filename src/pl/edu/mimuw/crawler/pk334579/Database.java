package pl.edu.mimuw.crawler.pk334579;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Database used to store collected data. Based on HashMap.
 * Key is SiteElement, Value is Integer.
 * @author Paweł Kapica <pawel.kapica@gmail.com>
 */
public class Database {

	private HashMap<SiteElement, Integer> map;

	public Database() {
		map = new HashMap<SiteElement, Integer>();
	}

	/**
	 * Adds new element to database.
	 * If element already exists, increases value.
	 * @param elem - SiteElement to add
	 */
	public void addElement(SiteElement elem) {
		Integer nnew;
		if (this.map.containsKey(elem)) {
			nnew = this.map.get(elem);
			this.map.remove(elem);
		} else {
			nnew = 0;
		}
		this.map.put(elem, nnew + 1);
	}

	/**
	 * Adds Element with specified Key.
	 * @param elem - SiteElement to add
	 * @param n - Value to save with this Key
	 */
	public void addElement(SiteElement elem, int n) {
		if (!this.map.containsKey(elem)) {
			this.map.put(elem, n);
		}
	}

	/**
	 * Returns Value of elem
	 * @param elem
	 * @return Value of elem
	 */
	public int getValue(SiteElement elem) {
		if (this.map.containsKey(elem)) {
			return this.map.get(elem);
		} else {
			return (-1);
		}
	}

	/**
	 * Clears database
	 */
	public void clean() {
		this.map.clear();
	}

	/**
	 * Returns number of Keys stored in database
	 * @return number of Keys stored in database
	 */
	public int countKeys() {
		return this.map.size();
	}

	/**
	 * Prints all data saved in Database.
	 * @return 
	 */
	@Override
	public String toString() {
		Set info;
		String result = new String();
		info = this.map.entrySet();
		SortedSet<Map.Entry> sorted;
		sorted = new TreeSet<Map.Entry>(new Comparator<Map.Entry>() {

			@Override
			public int compare(Map.Entry x, Map.Entry y) {
				if (x.getValue().equals(y.getValue())) {
					SiteElement xx = (SiteElement) x.getKey();
					SiteElement yy = (SiteElement) y.getKey();
					return xx.compareTo(yy);
				} else {
					Integer xi = (Integer) x.getValue();
					Integer yi = (Integer) y.getValue();
					return yi - xi;
				}
			}
		});
		Iterator<Map.Entry> it = info.iterator();
		while (it.hasNext()) {
			Entry ent = it.next();
			sorted.add(ent);
		}
		Iterator<Map.Entry> sit = sorted.iterator();
		while (sit.hasNext()) {
			Entry sent = sit.next();
			result += sent.getKey() + " " + sent.getValue() + "\n";
		}
		return result;
	}
}