package tran.recipeFinder.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tran.recipeFinder.getRecipes.GetRecipes;
import tran.recipeFinder.model.Recipes;

/**
 * This class will show a variety of recipes and the user can choose (click) on one to get detailed information regarding the recipe.
 * @author Todd
 */
@Controller
public class ShowRecipes {
	
	private final String sDefaultPageNumber = "1";
	
	@RequestMapping(value="/homePage", method=RequestMethod.GET)
	/**
	 * @param mmReturnPage Object holding data for the return jsp page.
	 * @param sUserSearchParameters The user entered string from the user
	 * @param sSortType Sort results by trending recipes or highest rated recipes.
	 * @param sPageNumber The page number passed in by the user to keep track of the currently viewed page of recipes.
	 * @return Returns a jsp page with data.
	 */
	public String displayAddForm(ModelMap mmReturnPage, @RequestParam(value = "userSearchTerms", required = false) String sUserSearchParameters,
			@RequestParam(value = "sortBy", required = false) String sSortType, @RequestParam(value = "page", required = false) String sPageNumber) {
		GetRecipes grResults = new GetRecipes();
		if(sUserSearchParameters != null) {
			checkUserInput(sUserSearchParameters, sPageNumber, grResults); // use entered input to find recipes.
		}
		else { 
			checkSortOption(sSortType, sPageNumber, grResults); // check how the user wants to sort the results.
		}
		Map<String, Recipes> resultsMap = grResults.getMDetailedRecipes();
		if(resultsMap != null && resultsMap.size() > 0) 
			mmReturnPage.addAttribute("results", grResults.getMDetailedRecipes());
		setExtraSearchParameters(mmReturnPage, sPageNumber, sSortType); // set attributes to keep track of the sort type and the page number.
		return "showRecipes";
	}
	
	/**
	 * helps with keeping track of the sort type and the page number. 
	 * @param mmReturnPage Object holding data for the return jsp page.
	 * @param sPageNumber The page number passed in by the user to keep track of the currently viewed page of recipes.
	 * @param sSortType Sort results by trending recipes or highest rated recipes.
	 */
	private void setExtraSearchParameters(ModelMap mmReturnPage, String sPageNumber, String sSortType) {
		if(sPageNumber != null && !sPageNumber.equals("") && !(sPageNumber.trim().length() == 0)) {
			mmReturnPage.addAttribute("pageNumber", Integer.parseInt(sPageNumber));
		}
		else {
			mmReturnPage.addAttribute("pageNumber", 1);
		}
		if(sSortType != null) { // keep track of what type of sorting method is being used.
			mmReturnPage.addAttribute("sortType", sSortType);
		}
		else {
			mmReturnPage.addAttribute("sortType", "rating");
		}
	}
		
	/**
	 * helper method to check the user input
	 * @param sUserSearchParameters The user entered search parameters
	 * @param sPageNumber The current page number
	 * @param grResults An object holding the requested recipes.
	 */
	private void checkUserInput(String sUserSearchParameters, String sPageNumber, GetRecipes grResults) {
		// user enters some kind of input into search field or hardcodes some kind of search term into the url.
		// when entering search terms the food2fork api does not seem to return results if sorted by trending (2).
		if(sUserSearchParameters.length() == 0 || sUserSearchParameters.trim().length() == 0) {
			if(sPageNumber != null) {
				grResults.getRecipeChoices("", 1 /* for now sort by ratings*/, sPageNumber); 
			}
			else {
				grResults.getRecipeChoices("", 1 /* for now sort by ratings*/, sDefaultPageNumber); 
			}
		}
		else {
			if(sPageNumber != null) {
				grResults.getRecipeChoices(sUserSearchParameters, 1 /* sort by ratings*/, sPageNumber); 
			}
			else {
				grResults.getRecipeChoices(sUserSearchParameters, 1 /* sort by ratings*/, sDefaultPageNumber); 
			}
		}
	}
	
	/**
	 * helper method to check if the results are sorted by highest rating or most trending.
	 * @param sSortType The method that the recipes will be sorted by
	 * @param sPageNumber The current page number
	 * @param grResults An object holding the requested recipes.
	 */
	private void checkSortOption(String sSortType, String sPageNumber, GetRecipes grResults) {
		if(sSortType != null) {
			if(sSortType.equals("rating")) {
				if(sPageNumber != null) {
					grResults.getRecipeChoices("", 1 /* sort by ratings */, sPageNumber);
				}
				else {
					grResults.getRecipeChoices("", 1 /* sort by ratings */, sDefaultPageNumber);
				}
			}
			else if(sSortType.equals("trending"))  {
				if(sPageNumber != null) {
					grResults.getRecipeChoices("", 2 /* sort by trending */, sPageNumber); 
				}
				else {
					grResults.getRecipeChoices("", 2 /* sort by ratings */, sDefaultPageNumber);
				}
			}
		}
		else { // default when the app starts, default page number of 1.
			grResults.getRecipeChoices("", 1 /* sort by ratings */, sDefaultPageNumber); 
		}
	}
}
