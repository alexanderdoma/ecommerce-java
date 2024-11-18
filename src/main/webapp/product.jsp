<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.alexanderdoma.peruinolvidable.model.entity.Product"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Descripción del producto</title>
        <!--STYLES-->
        <link rel="stylesheet" href="assets/css/styles.css"/>
        <!--CDN IMPLEMENTS-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA==" crossorigin="anonymous" referrerpolicy="no-referrer" />        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <%@include file="components/header.jsp" %>
        
        <div class="container-sm productos">
            <div class="d-flex gap-4">
                <div class="producto-imagen">
                    <img src="${product.getImage()}" width="500" height="500" alt="">
                </div>
                <div class="producto-contenido d-flex flex-column">
                    <form id="formElem">
                        <c:set var="productOrderline" value="${null}"/>
                            <c:forEach items="${orderlines}" var="orderline">
                                <c:if test="${orderline.product.getId() == product.getId()}">
                                    <c:set var="productOrderline" value="${orderline}"/>
                                </c:if>
                            </c:forEach>
                            <input type="hidden" value="${product.getId()}" name="product_id">
                            <input type="hidden" value="${product.getId()}" name="orderline_id">
                            <p class="fw-light">HANGERTIPS</p>
                            <h1 class="fw-light">${product.getName()}</h1>
                            <span class="mt-2 mb-2 fw-light">${product.getPrice()} USD</span>
                            <p class="fw-light text-secondary">Impuesto incluido. Los gastos de envío se calculan en la pantalla de
                                pago.</p>
                            <p class="fw-light text-secondary">Cantidad</p>
                            <div class="mb-4">
                                <div class="d-flex gap-2">
                                    <c:choose>
                                        <c:when test="${productOrderline != null}">
                                            <input id="quantity" class="form-control w-auto" min="0" max="${product.getStock()}" type="number" value="${productOrderline.getQuantity()}" name="quantity" readonly>
                                            <a class="btn btn-light border" onclick="removeOneProduct(), updateItem()"><i class="fa-solid fa-minus"></i></a>
                                            <a class="btn btn-light border" onclick="addOneProduct(), updateItem()"><i class="fa-solid fa-plus"></i></a>
                                        </c:when>
                                        <c:otherwise>
                                            <input id="quantity" class="form-control w-auto" min="0" max="${product.getStock()}" type="number" value="1" name="quantity" readonly>
                                            <a class="btn btn-light border" onclick="removeOneProduct()"><i class="fa-solid fa-minus"></i></a>
                                            <a class="btn btn-light border" onclick="addOneProduct()"><i class="fa-solid fa-plus"></i></a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <c:choose>
                                <c:when test="${productOrderline != null}">
                                    <a onclick="removeItem(${product.getId()})" class="btn btn-outline-primary mt-2 mb-2">Quitar del carrito</a>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-outline-primary mt-2 mb-2" onclick="addItem()">Agregar al carrito</button>
                                </c:otherwise>
                            </c:choose>
                            <p class="fw-light text-secondary">
                                ${product.getDescription()}
                            </p>
                            <p class="fw-light text-secondary">100% hecho en Perú</p>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="components/footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="assets/js/notifications.js"></script>
        <script src="assets/js/main.js"></script>
        <script src="assets/js/cartMethods.js"></script>
    </body>
</html>
