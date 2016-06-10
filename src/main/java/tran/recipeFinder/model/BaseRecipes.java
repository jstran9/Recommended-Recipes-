package tran.recipeFinder.model;

/**
 * The parent class for how a recipe object is represented.
 * Fields that appear for both the general recipes page and detailed recipes will be members of this class.
 * @author Todd
 *
 */
public class BaseRecipes {
	
	protected String sPublisherName; // the "publisher" key.
	
	protected String sFoodToForkURL; // the "f2f_url" key.
	
	protected String sRecipeName; // the "title" key.

	protected String sPublisherURL; // the "source_url" key.
	
	protected String sRecipeID; // the "recipe_id" key.s
	
	protected String sRecipeImageURL; // the "image_url" key. 
	
	protected double dSocial_rank; // the "social_rank" key. 
	
	protected String sPublisherHomeURL; // the "publisher_url" key. 
	
	public String getsPublisherName() {
		return sPublisherName;
	}

	public void setsPublisherName(String sPublisherName) {
		this.sPublisherName = sPublisherName;
	}

	public String getsFoodToForkURL() {
		return sFoodToForkURL;
	}

	public void setsFoodToForkURL(String sFoodToForkURL) {
		this.sFoodToForkURL = sFoodToForkURL;
	}

	public String getsRecipeName() {
		return sRecipeName;
	}

	public void setsRecipeName(String sRecipeName) {
		this.sRecipeName = sRecipeName;
	}

	public String getsPublisherURL() {
		return sPublisherURL;
	}

	public void setsPublisherURL(String sPublisherURL) {
		this.sPublisherURL = sPublisherURL;
	}

	public String getsRecipeID() {
		return sRecipeID;
	}

	public void setsRecipeID(String sRecipeID) {
		this.sRecipeID = sRecipeID;
	}

	public String getsRecipeImageURL() {
		return sRecipeImageURL;
	}

	public void setsRecipeImageURL(String sRecipeImageURL) {
		this.sRecipeImageURL = sRecipeImageURL;
	}

	public double getDSocial_rank() {
		return dSocial_rank;
	}
	
	public String getsSocial_rank() {
		return String.valueOf(Math.round(dSocial_rank));
	}

	public void setdSocial_rank(double dSocial_rank) {
		this.dSocial_rank = dSocial_rank;
	}

	public String getsPublisherHomeURL() {
		return sPublisherHomeURL;
	}

	public void setsPublisherHomeURL(String sPublisherHomeURL) {
		this.sPublisherHomeURL = sPublisherHomeURL;
	}

}
