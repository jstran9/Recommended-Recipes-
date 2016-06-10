<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="tran.recipeFinder.model.Recipes"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="templateInclude.jsp"%>
<link rel = "stylesheet" type="text/css" href="<c:url value="/resources/css/home.css"/>">
<script src="<c:url value="/resources/js/processSearch.js"/>"></script>
<title>Recommended Recipes</title>
</head>
<body>
	<%@ include file="headerTemplate.jsp"%>
	
	<div id = "formContainer">
		<form action ="<c:url value="/homePage"/>">
			<input name = "userSearchTerms" type = "text" size = "27" maxlength = "100" placeholder = "Search by Ingredients or Name"/> 
			<select id = "sortBy">
				<option value="rating">rating</option>
				<option value="trending">trending</option>
			</select>
			<input id = "formSubmit" type="hidden" value="Submit">
		</form>
		
		<c:choose>
			<c:when test="${pageNumber != null && pageNumber gt 1}">
				<c:if test="${sortType == null}">
					<c:set var = "sortType" value="rating"></c:set>
				</c:if>
				<c:set var="newPageNum" value="${pageNumber - 1}"></c:set>
				<a href ="<c:url value="/homePage?page=${newPageNum}&sortBy=${sortType}"/>">previous</a>
			
				<c:set var="newPageNumNext" value="${pageNumber + 1}"></c:set>
				<a href ="<c:url value="/homePage?page=${newPageNumNext}&sortBy=${sortType}"/>">next</a>
			</c:when>
			
			<%--
			<c:when test="${pageNumber != null && pageNumber eq 1}">
				<c:set var="newPageNumNext" value="${pageNumber + 1}"></c:set>
				<c:if test="${sortType == null}">
					<c:set var = "sortType" value="rating"></c:set>
				</c:if>
				<a href ="<c:url value="/homePage?page=${newPageNumNext}&sortBy=${sortType}"/>">next</a>
			</c:when>
			--%>
			<c:otherwise>
				<c:if test="${sortType == null}">
					<c:set var = "sortType" value="rating"></c:set>
				</c:if>
				<a href ="<c:url value="/homePage?page=2&sortBy=${sortType}"/>">next</a>
			</c:otherwise>
			
			
		</c:choose>

		
		
	</div>
		
	<div id="container" class="masonry">
		<c:choose>
			<c:when test = "${results != null && results.size() gt 0}">
				<c:forEach var = "recipe" items = "${results}">
					<c:set var = "recipeObject" value = "${recipe.value}"></c:set>
						<div class="masonryImage masonry-brick">
							<div class = "searchItems">
								<img class = "searchItemsMargin imgResize" src = "${recipeObject.getsRecipeImageURL()}"
								alt = "${recipeObject.getsRecipeName()}">
								
								<p class = "searchItemsMargin"> <a href = "<c:url value="/getDetailedRecipe?recipe=${recipeObject.getsRecipeID()}"/>">${recipeObject.getsRecipeName()}</a></p>
								<p class = "searchItemsMargin">${recipeObject.getsPublisherName()}</p>
							</div>
						
						<c:choose>
							<c:when test="${recipeObject.getsPublisherName().length() le 16}">
									<div class = "ratingContainerTwo">
										Rating <b class="rating-style rating-styleTwo">${recipeObject.getsSocial_rank()}</b>
									</div>
							</c:when>
							<c:otherwise>
									<div class = "ratingContainer">
										Rating <b class="rating-style rating-styleTwo">${recipeObject.getsSocial_rank()}</b>
									</div>
							</c:otherwise>
						</c:choose>
						</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class = "summaryContainer">
					<p>Could not get results from food2fork. Please try again.</p>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>