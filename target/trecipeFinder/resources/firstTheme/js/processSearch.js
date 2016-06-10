// helper function to handle when the user selects to sort by rating or trending without entering in a search term.
$(document).ready(function(){
	$("#sortBy").click(function()
	{
		var sortType = $(sortBy).val();
		if(sortType != null) { 
			document.location.href = "./homePage?sortBy=" + sortType;
		}
	});
});