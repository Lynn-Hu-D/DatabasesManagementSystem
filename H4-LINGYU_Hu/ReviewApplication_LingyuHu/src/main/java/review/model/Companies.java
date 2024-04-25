package review.model;

public class Companies {
	protected String companyName;
	protected String about;
	
	/**
	 * @param companyName
	 * @param about
	 */
	public Companies(String companyName, String about) {
		this.companyName = companyName;
		this.about = about;
	}
	
	/**
	 * @param companyName
	 */
	public Companies(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the about
	 */
	public String getAbout() {
		return about;
	}

	/**
	 * @param about the about to set
	 */
	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public String toString() {
		return "Companies [companyName=" + companyName + ", about=" + about + "]";
	}

	
	
}
