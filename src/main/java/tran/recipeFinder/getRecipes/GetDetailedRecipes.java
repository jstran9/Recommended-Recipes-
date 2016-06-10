package tran.recipeFinder.getRecipes;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tran.recipeFinder.model.DetailedRecipes;
import tran.recipeFinder.model.Recipes;

/**
 * This class holds methods to search to get a detailed recipe listing based off the recipe's ID.
 * @author Todd
 *
 */
public class GetDetailedRecipes extends GetRecipesBase {
	
	private String sRecipeURL = "http://food2fork.com/api/get?key=";
	private List<String> lIngredientsList;
	private DetailedRecipes drRecipeDetails;
	
	/*
	 * arraylist because it's rare for recipe ingredient lists to hit a capacity of over 25.
 	 * only 1 resize is needed if it hits capacity 10.
	 */
	public GetDetailedRecipes() {
		// arraylist because it's rare for recipe ingredient lists to hit a capacity of over 25.
		// only 1 resize is needed if it hits capacity 10.
		readAPIKey();
		lIngredientsList = new ArrayList<String>();
		drRecipeDetails = new DetailedRecipes();
	}
	
	/**
     * calls a helper method to add the ingredients of the recipe.
     * @param recipeId The recipe id retrieved from the user after selecting a recipe.
     */
    public void getDetailedRecipes(String sRecipeId) {
        String sRecipeWithIngredientsResult = makeAPIRequest(sRecipeURL + sAPIkey + "&rId=" + sRecipeId);
        if(sRecipeWithIngredientsResult != null)
        	addIngredientsToRecipe(sRecipeWithIngredientsResult);
    }
    
    /**
     * fills up the ingredients list and assigns relevant data members to be used to display the recipe.
     * @param sRecipeWithIngredientsResult The unparsed string from the recipes get query.
     */
    public void addIngredientsToRecipe(String sRecipeWithIngredientsResult) {
    	JSONObject joUnparsedResults = null;
    	JSONObject joUnparsedRecipe = null;
    	JSONArray jaIngredientsList = null;
    	try {
	    	joUnparsedResults = new JSONObject(sRecipeWithIngredientsResult);
	    	joUnparsedRecipe = joUnparsedResults.getJSONObject("recipe");
	    	jaIngredientsList = joUnparsedRecipe.getJSONArray("ingredients");
	    	for(int i = 0; i < jaIngredientsList.length(); i++) {
	    		lIngredientsList.add((String) jaIngredientsList.get(i));
	    	}
	    	
	    	String sRecipeName = joUnparsedRecipe.getString("title");
	    	if(sRecipeName != null)
	    		drRecipeDetails.setsRecipeName(sRecipeName); // title
			String sPublisherName = joUnparsedRecipe.getString("publisher");
			if(sPublisherName != null)
	    		drRecipeDetails.setsPublisherName(sPublisherName); // publisher name
			Double dSocialRank = joUnparsedRecipe.getDouble("social_rank");
			if(dSocialRank != null)
	    		drRecipeDetails.setdSocial_rank(dSocialRank); // rank
			String sFoodToForkURL = joUnparsedRecipe.getString("f2f_url");
			if(sFoodToForkURL != null)
	    		drRecipeDetails.setsFoodToForkURL(sFoodToForkURL); // foodtofork url
			String sPublisherHomeURL = joUnparsedRecipe.getString("publisher_url");
			if(sPublisherHomeURL != null)
	    		drRecipeDetails.setsPublisherHomeURL(sPublisherHomeURL); // publisher home url
			String sPublisherURL = joUnparsedRecipe.getString("source_url");
			if(sPublisherURL != null)
	    		drRecipeDetails.setsPublisherURL(sPublisherURL); // source_url (to get detailed directions)
			String sRecipeImageURL = joUnparsedRecipe.getString("image_url");
			if(sRecipeImageURL != null) {
				drRecipeDetails.setsRecipeImageURL(sRecipeImageURL); // the url for the picture
			}
			
    	}
    	catch(JSONException e) {
    		e.printStackTrace(); // invalid keys being used.
    	}
    }
    
    /**
     * helper method to allow the controller access to the data members of the parent class.
     */
    public DetailedRecipes getdrRecipeDetails() {
    	return this.drRecipeDetails;
    }
    
    /**
     * returns the ingredients list.
     */
    public List<String> getlIngredientsList() {
    	return lIngredientsList;
    }

}
