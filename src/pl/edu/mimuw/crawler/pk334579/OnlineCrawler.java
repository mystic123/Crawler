package pl.edu.mimuw.crawler.pk334579;

/**
 * Online Crawler collects data from Internet. 
 * @author Paweł Kapica <pawel.kapica@gmail.com>
 */
public class OnlineCrawler extends Crawler {

	private String startLink;
	private Database data;

	/**
	 * 
	 * @param link - page address
	 * @param elem - type of data collected by Crawler (use constructor without
	 * parameters)
	 */
	public OnlineCrawler(String link, SiteElement elem) {
		this.startLink = link;
		this.elem = elem;
		this.data = new Database();
	}

	@Override
	public void crawl() {
		Hyperlink start = new Hyperlink(startLink);
		ParseQueue.queue.addWebsite(start);
		while (!ParseQueue.queue.isEmpty()) {
			Hyperlink link = ParseQueue.queue.getFirst();
			try {
				Website w = new Website(link);
				if (w != null) {
					w.parseSite(elem, data);
				}
			} catch (InvalidAddress exc) {
				System.err.println("Cannot connect to: " + link);
			}
		}
		System.out.println(data);
	}

	@Override
	public void reset() {
		this.data.clean();
		BeenHere.visited.clean();
	}
}
