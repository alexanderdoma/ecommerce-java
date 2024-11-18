<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.alexanderdoma.peruinolvidable.model.mysql.UserDAO"%>
<%@page import="com.alexanderdoma.peruinolvidable.model.entity.User"%>
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
        <% User objUser = new UserDAO().getById((int) session.getAttribute("user_id"));%>
        <%@include file="components/header.jsp"%>
        <div class="container-fluid d-flex justify-content-center align-items-center"  style="min-height: 80vh">
            <div class="row" style="width: 500px">
                <div class="col-12">
                    <h1>Mi información</h1>
                    <form id="formElem">
                        <div class="form-floating mb-2">
                            <input type="text" name="name" id="first_name floatingInputValue" class="form-control input-lg" placeholder="Nombres completos" tabindex="1" value="<%=objUser.getName()%>">
                            <label for="floatingInput">Nombres completos</label>
                        </div>
                        <div class="form-floating mb-2">
                            <input type="text" name="lastname" id="last_name floatingInputValue" class="form-control input-lg" placeholder="Apellidos completos" tabindex="2" value="<%=objUser.getLastname()%>">
                            <label for="floatingInput">Apellidos completos</label>
                        </div>
                        <div class="form-floating mb-2">
                            <input type="text" name="username" id="display_name floatingInputValue" class="form-control input-lg" placeholder="Nombre de usuario" tabindex="3" value="<%=objUser.getUsername()%>">
                            <label for="floatingInput">Nombre de usuario</label>
                        </div>
                        <div class="form-floating mb-2">
                            <input type="email" name="email" id="email floatingInputValue" class="form-control input-lg" placeholder="Correo electrónico" tabindex="4" value="<%=objUser.getEmail()%>">
                            <label for="floating-input">Correo electrónico</label>
                        </div>
                        <div class="form-group mb-2 d-flex gap-2">
                            <div class="form-floating w-100">
                                <input type="password" name="password" id="password floatingInputValue" class="form-control input-lg" placeholder="Contraseña" tabindex="5" value="<%=objUser.getPassword()%>">
                                <label for="floatingInput">Contraseña de usuario</label>
                            </div>
                            <a class="btn btn-light border d-flex align-items-center" onclick="showPassword(['password'])"><i class="fa-solid fa-eye"></i></a>
                        </div>
                        <hr class="colorgraph">
                        <button type="submit" class="btn btn-outline-dark form-control mb-2" onclick="updateProfile()">Actualizar datos</button>
                        <a class="btn btn-outline-dark form-control mb-2" onclick="logout()">Cerrar sesión</a>
                        <hr class="colorgraph">
                        <a class="btn btn-outline-danger form-control" onclick="deleteAccount()">Eliminar cuenta</a>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="components/footer.jsp"%>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="assets/js/notifications.js"></script>
        <script src="assets/js/main.js"></script>
        <script src="assets/js/userMethods.js"></script>
    </body>
</html>
