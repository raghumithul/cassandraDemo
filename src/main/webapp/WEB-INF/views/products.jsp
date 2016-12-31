<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Product Deatils</title>
</head>
<body>

<h2>Product Information</h2>
<c:if test="${not empty productList}">
<table id="productTable" >
    <thead>
        <tr>
            <th>Product Id</th>
            <th>Product Name</th>
            <th>Product Description</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="product" items="${productList}">
            <tr>
                <td>${product.productId}</td>
                <td>${product.productName}</td>
                <td>${product.productDescription}</td>
            </tr>       
        </c:forEach>
    </tbody>
</table>
</c:if>



<h2>New Product</h2>
<form:form method="POST" action="addProduct" commandName="product">
   <table>
    <tr>
        <td><form:label path="productName">Product Name</form:label></td>
        <td><form:input path="productName" /></td>
    </tr>
    <tr>
        <td><form:label path="productDescription">Product Description</form:label></td>
        <td><form:input path="productDescription" /></td>
    </tr>
    <tr>
        <td><form:label path="productId">Product ID</form:label></td>
        <td><form:input path="productId" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>


<h2>Delete Product</h2>
<form:form method="POST" action="deleteProduct" commandName="product">
   <table>
    <tr>
        <td><form:label path="productId">Product ID</form:label></td>
        <td><form:input path="productId" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>

</body>
</html>