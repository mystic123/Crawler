/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.mimuw.crawler.pk334579;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;

/**
 * Class for websites stored on hard disk.
 * @author Paweł Kapica <pawel.kapica@gmail.com>
 */
public class LocalSite extends Site {

	private File file;

	/**
	 * 
	 * @param address - path
	 * @throws InvalidAddress when path is invali
	 */
	public LocalSite(String address) throws InvalidAddress {
		super(address);
		try {
			setFile();
		} catch (IOException exc) {
			throw new InvalidAddress();
		}
	}

	/**
	 * Creates local website from Hyperlink.
	 * @param link
	 * @throws InvalidAddress when address stored in Hyperlink is invalid
	 */
	public LocalSite(Hyperlink link) throws InvalidAddress {
		super(link.getAddress());
		try {
			setFile();
		} catch (IOException exc) {
			throw new InvalidAddress();
		}
	}

	private void setFile() throws IOException {
		this.file = new File(this.address);
		this.doc = Jsoup.parse(file, null, file.toURI().toString());
		this.address = file.getAbsolutePath();
	}

	/**
	 * Parses site.
	 * @param elem - type of data to collect
	 * @param data - database
	 * @param depth - maximum depth of catalog tree to parse
	 */
	public void parseSite(SiteElement elem, Database data, int depth) {
		elem.parseSite(this, data, depth);
	}
}
