<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.alexanderdoma.peruinolvidable.model.entity.Orderline"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.alexanderdoma.peruinolvidable.model.mysql.CategoryDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.alexanderdoma.peruinolvidable.model.mysql.ProductDAO"%>
<%@page import="com.alexanderdoma.peruinolvidable.model.entity.Product"%>
<%@page import="com.alexanderdoma.peruinolvidable.model.entity.Category"%>
<%@page import="com.sun.tools.javac.resources.compiler"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio | Perú inolvidable</title>
        <!--STYLES-->
        <link rel="stylesheet" href="assets/css/styles.css"/>
        <!--CDN IMPLEMENTS-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA==" crossorigin="anonymous" referrerpolicy="no-referrer" />        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <%@include file="components/header.jsp" %>
        <div>
            <img src="assets/img/banner.jpg" class="image-banner">
        </div>
        <div class="container-sm mt-3">
            <h2 class="font-monospace text-secondary text-center">Los más vendidos</h2>
            <div class="row row-cols-4">
                <% request.setAttribute("products", new ProductDAO().getActiveProducts()); %>
                <c:forEach items="${products}" var="product" end="3">
                    <a class="card col text-decoration-none my-2 hover-zoom" style="border: none" href="product?id=${product.getId()}">
                        <img src="${product.getImage()}" style="width: 250px" alt="Medias Cuy"
                             class="motion-reduce hover-zoom" loading="lazy">
                        <div class="card-body p-0">
                            <p class="card-title" style="font-size: 13px">${product.getName()}</p>
                            <p class="card-text">${product.getPrice()} USD</p>
                        </div>
                    </a>
                </c:forEach>  
            </div>
        </div>
        <div class="container-sm mt-3">
            <div class="collage">
                <div class="collage__item--left">
                    <div class="d-flex flex-column">
                        <img class="img-fluid hover-zoom"
                             src="assets/img/gifts.jpeg"
                             alt="" height="1921" loading="lazy" class="motion-reduce">
                    </div>
                </div>
                <div class="collage__item--right-top">
                    <div class="d-flex flex-column h-100">
                        <img class="img-fluid h-100 hover-zoom"
                             src="assets/img/briefcase.jpeg"
                             sizes="
                             (min-width: 1200px) 550px,
                             (min-width: 750px) calc((100vw - 10rem) / 2),
                             calc(100vw - 3rem)" alt="" height="600" width="800" loading="lazy" class="motion-reduce">
                    </div>
                </div>
                <div class="collage__item--right-bottom">
                    <div class="d-flex flex-column h-100">
                        <img class="img-fluid h-100 hover-zoom"
                             src="assets/img/key-ring.jpeg"
                             sizes="
                             (min-width: 1200px) 550px,
                             (min-width: 750px) calc((100vw - 10rem) / 2),
                             calc(100vw - 3rem)" alt="" height="600" width="800" loading="lazy" class="motion-reduce">
                    </div>
                </div>
            </div>
        </div>
        <%@include file="components/footer.jsp"%>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="assets/js/notifications.js"></script>
    </body>
</html>
