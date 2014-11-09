/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.mimuw.crawler.pk334579;

/**
 *
 * @author super.maja
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws InvalidAddress  
     */
    public static void main(String[] args) throws InvalidAddress {
        FilmwebSearcher f = new FilmwebSearcher(FilmwebSearcher.parseFilmTitle("kiler"));
        //System.out.println(f.toString());
    }
}
