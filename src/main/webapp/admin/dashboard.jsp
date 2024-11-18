<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA==" crossorigin="anonymous" referrerpolicy="no-referrer" />        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <%@include file="../components/admin-header.jsp" %>
        
        <div class="container mt-3" style="min-height: 90vh">
            <h2>Productos más vendidos</h2>
            <div class="row my-3 row-cols-4 text-center">
                <c:forEach items="${products}" var="product" end="3">
                <div class="col mb-4">
                    <a class="card h-100" href="product?id=${product.getId()}">
                        <img class="card-img-top" src="${product.getImage()}" alt="" loading="lazy">
                        <div class="card-body">
                            <p class="card-text">${product.getName()}</p>
                        </div>
                    </a>
                </div>
                </c:forEach>
            </div>
            <h2>Productos menos vendidos</h2>
            <div class="row my-3 row-cols-4 text-center">
                <c:forEach items="${products}" var="product" end="3">
                <div class="col mb-4">
                    <a class="card h-100" href="product?id=${product.getId()}">
                        <img class="card-img-top" src="${product.getImage()}" alt="" loading="lazy">
                        <div class="card-body">
                            <p class="card-text">${product.getName()}</p>
                        </div>
                    </a>
                </div>
                </c:forEach>
            </div>
        </div>
        
        <footer class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">Copyright &copy; Perú Inolvidable 2024</p>
            </div>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="../assets/js/notifications.js"></script>
        <script src="../assets/js/main.js"></script>
        <script src="../assets/js/adminMethods.js"></script>
        </body>
</html>
