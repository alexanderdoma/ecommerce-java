<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--CDN IMPLEMENTS-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA==" crossorigin="anonymous" referrerpolicy="no-referrer" />        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <%@include file="../../components/admin-header.jsp" %>
        
        <main class="container d-flex justify-content-center mt-3"s style="min-height: 80vh">
            <div class="card" style="width: 1000px">
                <div class="card-body">
                    <h3 class="card-tittle">Registro de pedidos</h3>
                    <h4 class="card-subtittle text-body-secondary mb-3">Seleccione un pedido para ver los detalles</h4>
                    <table class="table table-striped">
                        <tr>
                            <th>N° Orden</th>
                            <th>Fecha</th>
                            <th>Subtotal</th>
                            <th>Total</th>
                            <th>Comprador</th>
                            <th>Estado del pedido</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <td>${order.getId()}</td>
                                <td>${order.getDate()}</td>
                                <td>${order.getSubtotal()} USD</td>
                                <td>${order.getTotal()} USD</td>    
                                <td>${order.user.getName()}</td>
                                <td>
                                    <p class="p-2 rounded-4 
                                       <c:if test="${order.getStatus() == 'COMPLETED'}">bg-success</c:if>
                                       <c:if test="${order.getStatus() == 'PENDING'}">bg-warning</c:if>
                                       <c:if test="${order.getStatus() == 'CANCELLED'}">bg-danger</c:if>
                                       ">
                                    ${order.getStatus()}
                                    <p>
                                </td>
                                <td>
                                    <div class="d-flex gap-1">
                                        <a onclick="updateOrder('CANCELLED', ${order.getId()})" class="btn btn-danger border"><i class="fa-solid fa-xmark"></i></a>
                                        <a onclick="updateOrder('COMPLETED', ${order.getId()})" class="btn btn-success border"><i class="fa-solid fa-check"></i></a>
                                        <a href="sale?order_id=${order.getId()}" class="btn btn-light border"><i class="fa-solid fa-eye"></i></a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </main>
        <footer class="py-5 bg-dark mt-3">
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
