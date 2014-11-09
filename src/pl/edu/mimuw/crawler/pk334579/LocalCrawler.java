/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.mimuw.crawler.pk334579;

/**
 * Local Crawler collects data from websites saved on harddisk.
 * @author Paweł Kapica <pawel.kapica@gmail.com>
 */
public class LocalCrawler extends Crawler {

	private String startFile;
	private int depth;
	private Database data;

	/**
	 * 
	 * @param file - path to file
	 * @param elem - type of data collected by Crawler (use constructor without
	 * parameters)
	 * @param depth - maximum depth of catalog tree visited by crawler
	 */
	public LocalCrawler(String file, SiteElement elem, int depth) {
		this.startFile = file;
		this.elem = elem;
		this.depth = depth;
		this.data = new Database();
	}

	@Override
	public void crawl() {
		Hyperlink start = new Hyperlink(startFile);
		try {
			LocalSite l = new LocalSite(start);
			start = new Hyperlink(l.getAddress());
			ParseQueue.queue.addWebsite(start);
			this.data.addElement(start, 0);
		} catch (InvalidAddress exc) {
			System.err.println("Invalid path: " + start);
		}
		while (!ParseQueue.queue.isEmpty()) {
			Hyperlink link = ParseQueue.queue.getFirst();
			try {
				LocalSite w = new LocalSite(link);
				if (w != null) {
					w.parseSite(this.elem, this.data, this.depth);
				}
			} catch (InvalidAddress exc) {
				System.err.println("Invalid path: " + link);
			}
		}
		System.out.println(data.countKeys() - 1);
	}

	@Override
	public void reset() {
		this.data.clean();
		BeenHere.visited.clean();
	}
}
