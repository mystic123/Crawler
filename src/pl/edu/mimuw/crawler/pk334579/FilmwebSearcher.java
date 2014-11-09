/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.mimuw.crawler.pk334579;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author super.maja
 */
public class FilmwebSearcher extends Website {
    
	private String film;
	
    private List<Filmweb> candidates = new LinkedList<Filmweb>();
	private Map<Filmweb,Integer> match = new TreeMap<Filmweb,Integer>();
    
    public FilmwebSearcher(String film) throws InvalidAddress {
        super("http://www.filmweb.pl/search/film?q="+film);
		this.film=film;
        setDoc();
        findPage();
    }
    
    public static String parseFilmTitle(String title) {
        return title.replaceAll(" ", "+");
    }
    
    private void findPage() {
        Elements elems = this.doc.getElementsByTag("a");
        for (Element x: elems) {
            if (x.className().equals("hdr hdr-medium")) {
                try {
                    Filmweb f = new Filmweb(x.attr("abs:href"));
                    this.candidates.add(f);
                    System.out.println(f);
                } catch (InvalidAddress ex) {
                    System.err.println("Film not found");
                }
           }
        }
    }
	
	private void findBestMatch() {
		
	}
}
