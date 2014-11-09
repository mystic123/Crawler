/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.mimuw.crawler.pk334579;

/**
 *
 * @author Paweł Kapica <pawel.kapica@gmail.com>
 */
/**
 * 
 * Exception thrown when app cannot connect to given address. Possible reasons:<br>
 * - address is invalid<br>
 * - connection error<br>
 * - who knows
 * 
 */
public class InvalidAddress extends Exception {

	/**
	 * Creates a new instance of <code>InvalidAddress</code> without detail message.
	 */
	public InvalidAddress() {
	}

	/**
	 * Constructs an instance of <code>InvalidAddress</code> with the specified detail message.
	 * @param msg the detail message.
	 */
	public InvalidAddress(String msg) {
		super(msg);
	}
}
