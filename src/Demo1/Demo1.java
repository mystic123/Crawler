/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo1;

import pl.edu.mimuw.crawler.pk334579.Crawler;
import pl.edu.mimuw.crawler.pk334579.Hyperlink;
import pl.edu.mimuw.crawler.pk334579.OnlineCrawler;

/**
 * Collects and counts all external hyperlinks on website.
 * @author Paweł Kapica <pawel.kapica@gmail.com>
 */
public class Demo1 {

	/**
	 * @param args the command line arguments (online page address)
	 */
	public static void main(String[] args) {
		try {
			Crawler crawler = new OnlineCrawler(args[0], new Hyperlink());
			crawler.crawl();
		} catch (IndexOutOfBoundsException exc) {
			throw new IndexOutOfBoundsException("Invalid argument.");
		}
	}
}
