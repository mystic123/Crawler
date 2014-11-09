package pl.edu.mimuw.crawler.pk334579;

/**
 * Everything that can be collected from website such as hyperlinks, images, movies,
 * texts, descriptions etc.
 * @author Paweł Kapica <pawel.kapica@gmail.com>
 */
public interface SiteElement {

	/**
	 * Parses online Website.
	 * @param w - Website
	 * @param data - database
	 */
	public void parseSite(Website w, Database data);

	/**
	 * Parses local Website.
	 * @param w - LocalSite
	 * @param data - database
	 * @param depth - maximum depth of catalog tree
	 */
	public void parseSite(LocalSite w, Database data, int depth);

	public int compareTo(SiteElement x);

	@Override
	public boolean equals(Object x);

	@Override
	public String toString();

	@Override
	public int hashCode();
}