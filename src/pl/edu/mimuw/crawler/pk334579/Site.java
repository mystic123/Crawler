/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.mimuw.crawler.pk334579;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Abstract Site class.
 * @author Paweł Kapica <pawel.kapica@gmail.com>
 */
public abstract class Site {

	protected String address;
	protected static String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Ubuntu Chromium/25.0.1364.160 Chrome/25.0.1364.160 Safari/537.22";
	protected Document doc;

	public Site(String str) {
		this.address=str;
	}

	protected void setDoc() throws InvalidAddress {
		try {
			Connection con = Jsoup.connect(address);
			con.userAgent(userAgent);
			con.ignoreHttpErrors(true);
			this.doc = con.get();
		} catch (Exception exc) {
			throw new InvalidAddress();
		}
	}
        
	/**
	 * Returns page address.
	 * @return page address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Returns page Document
	 * @return page Document.
	 */
	public Document getDoc() {
		return this.doc;
	}

	/**
	 * Return user agent.
	 * @return user agent.
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * Sets user agent to specified by user.
	 * @param userAgent new user agent to be used
	 */
	public void setUserAgent(String userAgent) {
            this.userAgent = userAgent;
	}
        
        protected String getPageTitle() {
            return this.doc.title();
        }
        
        /**
	 * Returns page title.
	 * @return page title
	 */
	@Override
	public String toString() {
            return getPageTitle();
	}
}
