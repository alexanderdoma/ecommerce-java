<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.alexanderdoma.peruinolvidable.model.mysql.ProductDAO"%>
<%@page import="com.sun.tools.javac.resources.compiler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuestros productos</title>
        <!--STYLES-->
        <link rel="stylesheet" href="assets/css/styles.css"/>
        <!--CDN IMPLEMENTS-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA==" crossorigin="anonymous" referrerpolicy="no-referrer" />        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <%@include file="components/header.jsp"%>
        
        <div class="container-sm" style="min-height: 80vh">
            <c:if test="${empty products}">
                <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 80vh">
                    <h1>No se encontraron productos</h1>
                    <i class="fa-regular fa-circle-xmark fs-1 text-danger"></i>
                </div>
            </c:if>
            
            <c:if test="${not empty products}">
                <div class="row row-cols-4">
                    <c:forEach items="${products}" var = "product">
                        <div class="col my-3">
                            <a class="card text-decoration-none my-2 hover-zoom" style="border: none" href="product?id=${product.getId()}">
                                <img src="${product.getImage()}" alt="Medias Cuy"
                                     class="motion-reduce hover-zoom" loading="lazy">
                                <div class="card-body">
                                    <p class="card-title" style="font-size: 13px">${product.getName()}</p>
                                    <p class="card-text">$. ${product.getPrice()} USD</p>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
        
        <%@include file="components/footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="assets/js/toastr.min.js"></script>
        <script src="assets/js/notifications.js"></script>
    </body>
</html>
