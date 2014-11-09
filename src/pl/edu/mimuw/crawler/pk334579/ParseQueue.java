package pl.edu.mimuw.crawler.pk334579;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Paweł Kapica <pawel.kapica@gmail.com>
 */
public class ParseQueue {

	private Queue<Hyperlink> q;
	static ParseQueue queue = new ParseQueue();

	private ParseQueue() {
		q = new LinkedList<Hyperlink>();
	}

	void addWebsite(Hyperlink link) {
		if (!BeenHere.visited.isVisited(link)) {
			this.q.add(link);
			BeenHere.visited.add(link);
		}
	}

	Hyperlink getFirst() {
		return this.q.poll();
	}

	boolean isEmpty() {
		return q.peek() == null;
	}

	void clean() {
		this.q.clear();
	}

	int remaining() {
		return this.q.size();
	}
}