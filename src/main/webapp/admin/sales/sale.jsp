<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--STYLES-->
        <link rel="stylesheet" href="assets/css/styles.css"/>
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
                    <h3 class="card-tittle">Detalles de pedido ${order.getId()}</h3>
                    <h4 class="card-subtittle text-body-secondary mb-3">Seleccione un pedido para ver los detalles</h4>
                    <table class="table table-striped">
                        <tr>
                            <th>Nombre</th>
                            <th>Marca</th>
                            <th>Precio</th>
                            <th>Cantidad</th>
                            <th>Total</th>
                        </tr>
                        <c:forEach items="${orderlines}" var="orderline">
                            <tr>
                                <td>${orderline.product.getName()}</td>
                                <td>${orderline.product.getBrand()}</td>
                                <td>${orderline.product.getPrice()} USD</td>
                                <td>${orderline.getQuantity()} unidades</td>    
                                <td>${orderline.getTotal()} USD</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </main>
    </body>
</html>
