package tran.recipeFinder.getRecipes;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tran.recipeFinder.model.Recipes;

/**
 * This class holds methods in order to run the food2fork search API request.
 * @author Todd
 */
public class GetRecipes extends GetRecipesBase {
	
	private String sSearchURL = "http://food2fork.com/api/search?key=";
	private Map<String, Recipes> mDetailedRecipes;
	
	public GetRecipes() {
		readAPIKey();
		mDetailedRecipes = new HashMap<String, Recipes>();
	}
	
	/**
	 * @return The detailed recipes for the user to view.
	 */
	public Map<String, Recipes> getMDetailedRecipes() {
		return this.mDetailedRecipes;
	}
		
	/**
	 * Places proper delimiters into the user entered search string.
	 * @param sQueryToConvert The user entered query.
	 */
	public String convertSearchQueryString(String sQueryToConvert) {
		StringBuilder sbConvertedString = new StringBuilder();
		for(int i = 0; i < sQueryToConvert.length(); i++) {
			if(sQueryToConvert.charAt(i) == ',') {
				sbConvertedString.append("%2C");
			}
			else if(sQueryToConvert.charAt(i) == ' ') {
				sbConvertedString.append("+");
			}
			else {
				sbConvertedString.append(sQueryToConvert.charAt(i));
			}
		}
		return sbConvertedString.toString();
	}
    
    /**
     * uses a search query to get a list of recipes.
     * @param sSearchParameters The user entered search parameters.
     * @param iSearchType A value representing the search type, (1 for rating sort, and 2 for trending sort).
     * @param sPageNumber The page number from the user, defaults to 1.
     */
    public void getRecipeChoices(String sSearchParameters, int iSearchType, String sPageNumber) {
    	String sResults = null;
    	if(sPageNumber.equals("") || sPageNumber.trim().length() == 0)
    		sPageNumber = "1";
    	if(iSearchType == 1) {
    		sResults = makeAPIRequest(sSearchURL + sAPIkey + "&q=" + convertSearchQueryString(sSearchParameters) + "&sort=r" + "&page=" + sPageNumber); 
    	}
    	else if(iSearchType == 2) {
    		sResults = makeAPIRequest(sSearchURL + sAPIkey + "&sort=t" + "&page=" + sPageNumber); 
    	}
    	if(sResults != null && sResults.length() > 0) // must be able to make API call to continue.
    		getRecipes(sResults);
    }
        
    /**
     * This method will traverse through the JSON array of results and store into a map.
     * @param results The unparsed JSON result.
     */
    public void getRecipes(String results) {
    	JSONObject joUnparsedResults = null;
    	JSONArray jaResults = null;
    	Recipes rDetailedRecipe = null;
    	String sRecipeName = null;
    	try {
	    	joUnparsedResults = new JSONObject(results);
	    	if(joUnparsedResults != null)
	    		jaResults = joUnparsedResults.getJSONArray("recipes");
    	}
    	catch(JSONException e) {
    		e.printStackTrace(); // invalid keys being used
    	}
    	// now go through the json array of results and add to the list.
    	if(jaResults != null) {
	    	for(int i = 0; i < jaResults.length(); i++) {
	    		try {
		    		JSONObject joObjectAtIndex = jaResults.getJSONObject(i);
		    		rDetailedRecipe = new Recipes();
					rDetailedRecipe.setsPublisherName((String) joObjectAtIndex.get("publisher"));
					rDetailedRecipe.setsFoodToForkURL((String) joObjectAtIndex.get("f2f_url"));
					sRecipeName = (String) joObjectAtIndex.get("title");
					rDetailedRecipe.setsRecipeName(sRecipeName);
					rDetailedRecipe.setsPublisherURL((String) joObjectAtIndex.get("source_url"));
					rDetailedRecipe.setsRecipeID((String) joObjectAtIndex.get("recipe_id"));
					rDetailedRecipe.setsRecipeImageURL((String) joObjectAtIndex.get("image_url"));
					rDetailedRecipe.setdSocial_rank((Double)joObjectAtIndex.get("social_rank"));
					rDetailedRecipe.setsPublisherHomeURL((String) joObjectAtIndex.get("publisher_url"));
	    		}
	    		catch(JSONException e) {
	    			e.printStackTrace(); // invalid key(s) being used
	    		}
	    		mDetailedRecipes.put(sRecipeName, rDetailedRecipe);
	    		rDetailedRecipe = null;
	    	}
    	}
    }
}
