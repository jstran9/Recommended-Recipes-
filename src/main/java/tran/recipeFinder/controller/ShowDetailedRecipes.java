package tran.recipeFinder.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tran.recipeFinder.getRecipes.GetDetailedRecipes;
import tran.recipeFinder.model.DetailedRecipes;

@Controller
public class ShowDetailedRecipes {

	@RequestMapping(value="/getDetailedRecipe", method=RequestMethod.GET)
	/**
	 * @param mmReturnPage An object holding the data for the returned page.
	 * @param sRecipeId A recipe ID to be run on a query to get
	 * @return Returns a jsp page with a detailed recipe object.
	 */
	public String displayAddForm(ModelMap mmReturnPage, @RequestParam(value = "recipe", required = false) String sRecipeId) {
		
		GetDetailedRecipes gdrDetailedRecipe = new GetDetailedRecipes();
		gdrDetailedRecipe.getDetailedRecipes(sRecipeId);
		
		DetailedRecipes drRecipeDetails = gdrDetailedRecipe.getdrRecipeDetails();
		
		String sRecipeName = drRecipeDetails.getsRecipeName();
		String sPublisherName = drRecipeDetails.getsPublisherName();
		String sPublisherURL = drRecipeDetails.getsPublisherURL();
		String sSocialRank = drRecipeDetails.getsSocial_rank();
		String sFoodToForkURL = drRecipeDetails.getsFoodToForkURL();
		String sPublisherHomeURL = drRecipeDetails.getsPublisherHomeURL();
		String sRecipeImageURL = drRecipeDetails.getsRecipeImageURL();
		List<String> lIngredientsList = gdrDetailedRecipe.getlIngredientsList();
		
		if(sRecipeName != null) {
			mmReturnPage.addAttribute("recipeName", sRecipeName);
		}
		if(sPublisherName != null) {
			mmReturnPage.addAttribute("publisherName", sPublisherName);		
		}
		if(sPublisherURL != null) {
			mmReturnPage.addAttribute("publisherURL", sPublisherURL);		
		}
		if(sSocialRank != null) {
			mmReturnPage.addAttribute("socialRank", sSocialRank);
		}
		if(sFoodToForkURL != null) {
			mmReturnPage.addAttribute("foodToForkURL", sFoodToForkURL);
		}
		if(sPublisherHomeURL != null) {
			mmReturnPage.addAttribute("publisherHomeURL", sPublisherHomeURL);
		}
		if(sRecipeImageURL != null) {
			mmReturnPage.addAttribute("imageURL", sRecipeImageURL);
		}
		if(lIngredientsList != null) {
			mmReturnPage.addAttribute("ingredientsList", lIngredientsList);
		}
		return "showDetailedRecipes";
	}
}
