/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo2;

import pl.edu.mimuw.crawler.pk334579.Crawler;
import pl.edu.mimuw.crawler.pk334579.Hyperlink;
import pl.edu.mimuw.crawler.pk334579.LocalCrawler;

/**
 * Collects and counts all external hyperlinks on website.
 * @author Paweł Kapica <pawel.kapica@gmail.com>
 */
public class Demo2 {

	/**
	 * @param args the command line arguments (path to file and depth)
	 */
	public static void main(String[] args) {
		try {
			Crawler crawler = new LocalCrawler(args[0],new Hyperlink(), Integer.parseInt(args[1]));
			crawler.crawl();
		} catch (IndexOutOfBoundsException exc) {
			throw new IndexOutOfBoundsException("Invalid argument.");
		} catch (NumberFormatException exc) {
			throw new NumberFormatException("Invalid depth.");
		}
	}
}
