/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.mimuw.crawler.pk334579;

import java.util.HashSet;

/**
 *
 * @author Paweł Kapica <pawel.kapica@gmail.com>
 */
class BeenHere {

	private HashSet<Hyperlink> set;
	static BeenHere visited = new BeenHere();

	private BeenHere() {
		this.set = new HashSet<Hyperlink>();
	}

	boolean isVisited(Hyperlink link) {
		return this.set.contains(link);
	}

	void add(Hyperlink link) {
		this.set.add(link);
	}

	void clean() {
		this.set.clear();
	}
}
