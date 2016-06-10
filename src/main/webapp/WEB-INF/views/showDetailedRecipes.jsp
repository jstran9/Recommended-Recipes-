<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="tran.recipeFinder.model.Recipes"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel = "stylesheet" type="text/css" href="<c:url value="/resources/css/detailedRecipe.css"/>">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Detailed Recipe Listing</title>
</head>
<body>
	<%@ include file="headerTemplate.jsp"%>
	
	<div class = "verticalSeparator">
		<div class="col-5 divBorders">
		<c:choose>
			<c:when test="${recipeName != null}">
				<h1>${recipeName}</h1>
			</c:when>
			<c:otherwise>
				<h1>Could not get a recipe name.</h1>
			</c:otherwise>
		</c:choose>
		<hr>
		
		<c:choose>
			<c:when test="${imageURL != null && recipeName != null}">
				<img class = "imgResize" src="${imageURL}" alt="${recipeName}" class="recipe-image">
			</c:when>
			<c:otherwise>
				<h3>Could not get an image of this recipe.</h3>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${ingredientsList != null}">
				<h5>Ingredients:</h5>
				<ul>
				<c:forEach var="ingredients" items="${ingredientsList}">
					<li>${ingredients}</li>	
				</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
				<h3>Could not get an ingredient list for this recipe.</h3>
			</c:otherwise>
		</c:choose>
		
		<h5>Directions:</h5>
		<c:choose>
			<c:when test="${publisherURL != null && publisherName != null}">
				<a href = "${publisherURL}">View on ${publisherName}</a>
				<br/>
				<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo">Why don't we show directions?</button>
				<div id="demo" class="collapse">
			    	We appreciate the creative talent of our publishers and try to give back by requiring that you visit their site to see the recipe directions.
			  	</div>
			</c:when>
			<c:otherwise>
				<h6>Could not directions for this recipe.</h6>
			</c:otherwise>
		</c:choose>

		</div>
		
		<div class="col-6">
		
			<div class = "divBorders verticalSeparator">
				<c:choose>
					<c:when test="${recipeName != null}">
						<h5>${recipeName}</h5>
					</c:when>
					<c:otherwise>
						<h5>recipe name not available.</h5>
					</c:otherwise>
				</c:choose>
			
				<c:choose>
					<c:when test="${publisherHomeURL != null}">
						<a href ="${publisherHomeURL}">Visit Publisher Site</a> <br/>
						Recipe and photo copyright <br/>
						${publisherHomeURL}
					</c:when>
					<c:otherwise>
						unable to retrieve the publisher's site.
					</c:otherwise>
				</c:choose>
			</div>
		
			<div class = "divBorders verticalSeparator">
				<h5>Rank</h5>
				<c:choose>
					<c:when test="${socialRank != null}">
						${socialRank} <br/>
						<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#rankInfo">What is this?</button>
						<div id="rankInfo" class="collapse">
					    	This is a proprietary ranking from 0-100 based on this recipe's social stats. Higher means this recipe is more popular
					  	</div>
					</c:when>
					<c:otherwise>
						unable to retrieve this recipe's ranking.
					</c:otherwise>
				</c:choose>
			</div>
		
			<div class = "divBorders verticalSeparator">
				<h5>Share</h5>
				<c:choose>
					<c:when test="${foodToForkURL != null}">
						${foodToForkURL}
					</c:when>
					<c:otherwise>
						Unable to retrieve the current URL.
					</c:otherwise>
				</c:choose>
				<hr>
			</div>
		</div>
	</div>

</body>
</html>