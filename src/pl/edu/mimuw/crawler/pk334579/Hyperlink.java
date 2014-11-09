package pl.edu.mimuw.crawler.pk334579;

import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hyperlink class.
 * @author Paweł Kapica <pawel.kapica@gmail.com>
 */
public class Hyperlink implements SiteElement {

	private final static String tagName = "a";
	private final static String attribute = "abs:href";
	private String address;

	/**
	 * Empty construtor for initializing Crawler.
	 */
	public Hyperlink() {
	}

	/**
	 * Creates Hyperlink from String.
	 * @param str 
	 */
	public Hyperlink(String str) {
		this.address = parseLink(str);
	}

	/**
	 * Creates Hyperlink from Site.
	 * @param w 
	 */
	public Hyperlink(Site w) {
		String adr = w.getAddress();
		this.address = parseLink(adr);
	}

	/**
	 * Return Hyperlink's address.
	 * @return Hyperlink's address
	 */
	public String getAddress() {
		return this.address;
	}

	private String parseLink(String str) {
		if (str.startsWith("http://")) {
			return parseLink(str.substring(7));
		}
		if (str.startsWith("https://")) {
			return parseLink(str.substring(8));
		}
		if (str.endsWith("/")) {
			return parseLink(str.substring(0, str.length() - 1));
		}
		return str;
	}

	/**
	 * Method used to parse online Websites.
	 * @param w - Website to parse
	 * @param database - Database to store data
	 */
	@Override
	public void parseSite(Website w, Database database) {
		URL home;
		try {
			home = new URL(w.getAddress());
		} catch (MalformedURLException ex) {
			home = null;
		}
		Elements elems = w.getDoc().getElementsByTag(tagName);
		for (Element x : elems) {
			String url = x.attr(attribute);
			if (!url.equals("") && url != null) {
				if (url.contains("#")) {
					url = url.substring(0, url.indexOf("#"));
				}
				try {
					URL link = new URL(url);
					String host = link.getHost();
					if (!host.equals(home.getHost())) {
						database.addElement(new Hyperlink(host));
					} else {
						ParseQueue.queue.addWebsite(new Hyperlink(host + link.getPath()));
					}
				} catch (MalformedURLException ex) {
					System.err.println("Cannot parse: " + url + " (invalid hyperlink?)");
				}
			}
		}
	}

	/**
	 * 
	 * Method used to parse local Websites.
	 * @param w - LocalSite to parse
	 * @param database - Database to store data
	 * @param depth - maximum depth of catalog tree
	 */
	@Override
	public void parseSite(LocalSite w, Database database, int depth) {
		int homeDepth = 0;
		homeDepth = database.getValue(new Hyperlink(w));
		if (homeDepth < depth) {
			Elements elems = w.getDoc().getElementsByTag(tagName);
			for (Element x : elems) {
				String url = x.attr(attribute);
				if (!url.equals("") && url != null) {
					if (url.contains("#")) {
						url = url.substring(0, url.indexOf("#"));
					}
					if (url.startsWith("file:")) {
						url = url.substring(5);
						Hyperlink link = new Hyperlink(url);
						database.addElement(link, homeDepth + 1);
						ParseQueue.queue.addWebsite(link);
					}
				}
			}
		}
	}

	private String parseUrl(String url, String home) throws IndexOutOfBoundsException {
		if (home.contains(".")) {
			return parseUrl(url, home.substring(0, home.lastIndexOf("/")));
		}
		if (home.endsWith("/")) {
			return parseUrl(url, home.substring(0, home.length() - 1));
		}
		if (url.startsWith("..")) {
			return parseUrl(url.substring(2), home.substring(0, home.lastIndexOf("/")));
		}
		if (url.startsWith(".")) {
			return parseUrl(url.substring(1), home);
		}
		if (url.startsWith("/")) {
			return parseUrl(url.substring(1), home);
		}
		return home + "/" + url;
	}

	/**
	 * Compares two Hyperlink objects by address.
	 * @param x - SiteElement to compare
	 * @return 0 if Hyperlinks are equal or when x is not a Hyperlink
	 */
	@Override
	public int compareTo(SiteElement x) {
		if (x instanceof Hyperlink) {
			Hyperlink link = (Hyperlink) x;
			return (-1) * link.toString().compareTo(this.toString());
		} else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object x) {
		if (x == null) {
			return false;
		}
		if (x == this) {
			return true;
		}
		if (!(x instanceof Hyperlink)) {
			return false;
		}
		Hyperlink y = (Hyperlink) x;
		return this.toString().equals(y.getAddress());
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 59 * hash + (this.address != null ? this.address.hashCode() : 0);
		return hash;
	}

	@Override
	public String toString() {
		return this.getAddress();
	}
}