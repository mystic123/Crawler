/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.mimuw.crawler.pk334579;

import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author mystic
 */
public class PDF_file implements SiteElement {

    private final static String tagName = "a";
    private final static String attribute = "abs:href";
    private String address;
    
    public PDF_file() {
        
    }
    
    public PDF_file(String address) {
        this.address = address;
    }
    
    @Override
    public void parseSite(Website w, Database data) {
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

    @Override
    public void parseSite(LocalSite w, Database data, int depth) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(SiteElement x) {
        if (x instanceof PDF_file) {
			PDF_file link = (PDF_file) x;
			return (-1) * link.toString().compareTo(this.toString());
		} else {
			return 0;
		}
    }
    
}
