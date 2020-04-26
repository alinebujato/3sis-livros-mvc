<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>

	<meta charset="ISO-8859-1">
	<title>Livros</title>
	
 	<spring:url value="/resources/css" var="css" />
 	<spring:url value="/resource/js" var="js" />
 	
 	<c:set value="${pageContext.request.contextPath}" var="contextPath" />
 	<link href="${css}/bootstrap.css" rel="stylesheet">
	<link href="${css}/small-business.css" rel="stylesheet">
   	
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
       <div class="container">
           <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
               <ul class="nav navbar-nav">
                   <li>
                       <a href="${contextPath}/livro">Livros</a>
                   </li>
               </ul>
           </div>
       </div>
   </nav>
   
   <div class="container">
   		<div class="row">
            <div class="col-md-12">
				
				<h1>Livros</h1>
				
				<p class="toolbar">
				
					<a class="create btn btn-default" href="${contextPath}/livro/form?page=novo-livro">Novo Livro</a>
					
					<c:if test="${not empty messages}">
						<h3 class="alert alert-warning">${messages}</h3>
					</c:if>
	
					<span class="alert"></span>
				</p>
				
				<table class="table table-striped" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<th data-field="name">T�tulo</th>
							<th data-field="name">Editora</th>
							<th data-field="forks_count">Pre�o</th>
							<th class="actions" width="220">A��es</th>
						</tr>
					</thead>
					<tbody>
					
						<c:forEach items="${livros}" var="livro">
		
							<tr>
								<td>${livro.titulo}</td>
								<td>${livro.editora}</td>
								<td>${livro.preco}</td>
								
								<td class="actions">
									
									<form:form action="${contextPath}/livro/${livro.id}" method="delete">
									
										<a class="btn btn-success btn-xs" href="${contextPath}/livro/${livro.id}">Detalhes</a>
										<a class="btn btn-warning btn-xs" href="${contextPath}/livro/form?page=editar-livro&id=${livro.id}">Editar</a>
										<input type="submit" value="Excluir" class="btn btn-danger btn-xs">
									</form:form>
									
								</td>
							</tr>
						
						</c:forEach>
                    </tbody>
				</table>
            </div>
        </div>
   </div>

</body>

</html>