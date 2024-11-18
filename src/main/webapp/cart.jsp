<%@page import="com.alexanderdoma.peruinolvidable.model.mysql.ProductDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.alexanderdoma.peruinolvidable.model.entity.Product"%>
<%@page import="java.util.List"%>
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
        <%@include file="components/header.jsp" %>
        <!-- Productos -->
        <div class="container-sm d-flex justify-content-center flex-column" style="min-height: 80vh">
            <c:if test="${(orderlines.size() == 0 || orderlines == null)}">
                <div class="d-flex flex-column align-items-center justify-content-center" style="height: 50vh">
                    <h1 class="mt-3 font-monospace text-secondary text-center mb-3">Tu carrito esta vacio</h1>
                    <a class="btn btn-dark btn-block " style="border-radius: 0" href="<%=request.getContextPath()%>/products">Seguir comprando</a>
                </div>
            </c:if>
            <c:if test="${(orderlines.size() > 0)}">
                <div class="d-flex justify-content-between align-items-center">
                    <div><h2 class="mt-3 font-monospace text-secondary">Tu carrito</h2></div>
                    <div><a class="text-dark" href="<%=request.getContextPath()%>/products">Seguir comprando</a></div>
                </div>
                <!-- Contenedor de Productos -->
                    <div class="mt-4 mb-4">
                        <table class="table">
                            <thead>
                                <tr class="text-secondary">
                                    <th>Producto</th>
                                    <th>Cantidad</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="total" value="0" scope="session"/>
                                <c:forEach items="${orderlines}" var = "orderline">
                                    <tr id="productNumber${orderline.getProduct().getId()}">
                                        <td class="product-name">
                                            <div class="d-flex align-items-center">
                                                <a href="<%=request.getContextPath()%>/product?id=${orderline.getProduct().getId()}">
                                                    <img src="${orderline.getProduct().getImage()}"class="img-fluid" loading="lazy" width="150" height="150">
                                                </a>
                                                <div class="ms-3">
                                                    ${orderline.getProduct().getName()}
                                                    <div class="product-details">
                                                        <p>${orderline.getProduct().getBrand()}</p>
                                                        <p>${orderline.getProduct().getPrice()} USD</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="align-middle">
                                            <form class="d-flex gap-1 align-items-center" id="formElem-${orderline.product.getId()}">
                                                <input id="quantity-product-${orderline.getProduct().getId()}" class="form-control w-auto" min="0" max="${orderline.getProduct().getStock()}" type="number" value="${orderline.getQuantity()}" name="quantity" readonly>
                                                <input type="hidden" value="${orderline.product.getId()}" name="orderline_id"/>
                                                <a class="btn btn-light border" onclick="removeOneProductByOrderline('${orderline.product.getId()}'), updateItemByOrderline ('${orderline.product.getId()}')"><i class="fa-solid fa-minus"></i></a>
                                                <a class="btn btn-light border" onclick="addOneProductByOrderline('${orderline.product.getId()}'), updateItemByOrderline('${orderline.product.getId()}')"><i class="fa-solid fa-plus"></i></a>
                                                <a class="btn btn-light border px-3" onclick="removeItem('${orderline.getProduct().getId()}')" style="cursor: pointer"><i class="fa-solid fa-trash"></i></a>
                                            </form>
                                        </td>
                                        ${orderline.setTotal(orderline.getQuantity() * orderline.getProduct().getPrice())}
                                        <td class="align-middle">${orderline.getTotal()} USD</td>
                                    </tr>
                                <c:set scope = "session" var="total" value="${total + (orderline.getQuantity() * orderline.getProduct().getPrice())}"/>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="d-flex justify-content-end">
                        <div>
                            <div class="text-end mb-4">
                                <p class="fw-bold d-inline text-success-emphasis px-3 fs-5">Subtotal: </p>
                                    <p class="fw-bold d-inline text-secondary fs-5">${total} USD</p>
                            </div>
                            <p class="text-center text-muted small mb-4">
                                Impuesto incluido. Los gastos de envío se calculan en la pantalla de pago.
                            </p>
                            <div class="text-center">
                                <a type="submit" class="btn btn-dark btn-lg w-100" onclick="proccessOrder()">Pagar pedido</a> 
                            </div>
                        </div>
                    </div>
            </c:if>
        </div>

        <div class="container-sm mb-5">
            <h2>Productos destacados</h2>
            <div class="row row-cols-4">
                <% request.setAttribute("products", new ProductDAO().getAll());%>
                <c:forEach items="${products}" var="product" end="3">
                    <div class="col">
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
        </div>
        <%@include file="components/footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="assets/js/notifications.js"></script>
        <script src="assets/js/cartMethods.js"></script>
        <script src="assets/js/paymentMethods.js"></script>
    </body>
</html>
