/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.mimuw.crawler.pk334579;

/**
 * Abstract Crawler class.
 * @author Paweł Kapica <pawel.kapica@gmail.com>
 */
public abstract class Crawler {

	/**
	 * Type of data collected by Crawler.
	 */
	protected SiteElement elem;

	/**
	 * Initializes Crawler.
	 */
	public abstract void crawl();

	/**
	 * Resets all the data that Crawler has collected (visited sites and parsed
	 * informations).
	 */
	public abstract void reset();
}
