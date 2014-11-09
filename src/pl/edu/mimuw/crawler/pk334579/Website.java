package pl.edu.mimuw.crawler.pk334579;

/**
 * Class for online websites.
 * @author Paweł Kapica <pawel.kapica@gmail.com>
 */
public class Website extends Site {
    
	/**
	 * Creates Website from String.
	 * @param address page address
	 * @throws InvalidAddress when it was unable to connect
	 */
	public Website(String address) throws InvalidAddress {
		super(address);
		parseAddress();
		setDoc();
	}

	/**
	 * Creates Website from Hyperlink.
	 * @param link
	 * @throws InvalidAddress when it was unable to connect
	 */
	public Website(Hyperlink link) throws InvalidAddress {
		super(link.getAddress());
		parseAddress();
		setDoc();
	}

	private void parseAddress() {
		if (!address.startsWith("http://") && !address.startsWith("https://")) {
			address = "http://" + address;
			parseAddress();
		}
		if (address.endsWith("/")) {
			address = address.substring(0, address.length() - 1);
			parseAddress();
		}
		if (address.contains("index.") && !address.contains("?")) {
			address = address.substring(0, address.indexOf("index."));
			parseAddress();
		}
	}

	/**
	 * Parses site.
	 * @param elem - type of data to collect
	 * @param data - database
	 */
	public void parseSite(SiteElement elem, Database data) {
		elem.parseSite(this, data);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + (this.address != null ? this.address.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object x) {
		if (x == null && this != null) {
			return false;
		}
		if (x == this) {
			return true;
		}
		if (!(x instanceof Website)) {
			return false;
		}
		Website y = (Website) x;
		if (this.getAddress().equals(y.getAddress())) {
			return true;
		} else {
			return false;
		}
	}
}