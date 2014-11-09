/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.mimuw.crawler.pk334579;

/**
 *
 * @author super.maja
 */
public class Filmweb extends Website {
    
    private String filmTitle;
    
    public Filmweb(String film) throws InvalidAddress {
        super(film);
        setDoc();
        this.filmTitle = this.getPageTitle().substring(0, this.getPageTitle().indexOf(" - Filmweb"));
    }
    
    @Override
    public String toString() {
        return this.filmTitle;
    }
    
    /**
     * Replaces spaces with + for filmweb seatch query
     * @param title - film title
     * @return title with "+" instead of spaces
     */
    
}
