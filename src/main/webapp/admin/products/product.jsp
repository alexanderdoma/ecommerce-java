<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        
        <div class="container d-flex justify-content-center mt-3" style="min-height: 90vh">
            <form id="formElem" style="width: 500px" accept-charset="UTF-8">
                <input type="hidden" name="id" value="${product.getId()}">
                <input type="hidden" name="action" value="update">
                <h2 class="text-center">Información del producto</h2>
                <div class="form-floating mb-2">
                    <input id="inpuFloatingValue" type="text" name="name" class="form-control input-lg" placeholder="Nombre del producto" tabindex="1" value="${product.getName()}">
                    <label form="inputFloatingValue">Nombre del producto</label>
                </div>
                <div class="form-floating mb-2">
                    <input id="inputFloatingValue" type="text" name="description" class="form-control input-lg" placeholder="Descripción del producto" tabindex="1" value="${product.getDescription()}">
                    <label for="inputFloatingValue">Descripción del producto</label>
                </div>
                <div class="form-floating mb-2">
                    <input id="inputFloatingValue" type="number" name="price" step="0.01" class="form-control input-lg" placeholder="Precio del producto" tabindex="1" value="${product.getPrice()}">
                    <label for="inputFloatingValue">Precio del producto (USD)</label>
                </div>
                <div class="form-floating mb-2">
                    <input id="inputFloatingValue" type="text" name="brand" class="form-control input-lg" placeholder="Marca del producto" tabindex="1" value="${product.getBrand()}">
                    <label for="inputFloatingValue">Marca del producto</label>
                </div>
                <div class="form-floating mb-2">
                    <input id="inputFloatingValue" type="number" name="stock" class="form-control input-lg" placeholder="Stock del producto" tabindex="1" value="${product.getStock()}">
                    <label for="inputFloatingValue">Stock del producto</label>
                </div>
                <div class="form-floating mb-2">
                    <select id="inputFloatingValue" name="state" class="form-control select-lg">
                        <option value="ACTIVE" <c:if test="${product.getState() == 'ACTIVE'}">selected</c:if> > ACTIVO</option>
                        <option value="NO ACTIVE" <c:if test="${product.getState() == 'NO ACTIVE'}">selected</c:if> >DESACTIVADO</option>
                    </select>
                    <label for="inputFloatingValue">Estado del producto</label>
                </div>
                <div class="form-floating mb-2">
                    <input id="inputFloatingValue" type="text" name="image" class="form-control input-lg" placeholder="Url de la imagen del producto" tabindex="1" value="${product.getImage()}">
                    <label for="inputFloatingValue">URL de la imágen del producto</label>
                </div>
                <div class="form-floating mb-2">
                    <select id="inputFloatingValue" name="category_id" class="form-control select-lg">
                        <c:forEach items="${categories}" var="category">
                            <option <c:if test="${product.getCategory().getId() == category.getId()}">selected</c:if> value="${category.getId()}">${category.getName()}</option>
                        </c:forEach>
                    </select>
                    <label for="inputFloatingValue">Categoría</label>
                </div>
                <hr class="colorgraph">
                <button type="submit" onclick="updateProduct()" class="btn btn-outline-dark form-control mb-2">Actualizar producto</button>
                <a href="delete" class="btn btn-outline-danger form-control">Eliminar producto</a>
                <hr class="colorgraph">
                <a href="products" class="btn btn-outline-dark form-control">Volver</a>
            </form>
        </div>
        <footer class="mt-4 py-5 bg-dark">
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
