<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Peru inolvidable | Administrador</title>
    <!--STYLES-->
    <link rel="stylesheet" href="assets/css/styles.css"/>
    <!--CDN IMPLEMENTS-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA==" crossorigin="anonymous" referrerpolicy="no-referrer" />        
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    
    <div class="container-fluid d-flex align-items-center justify-content-center min-vh-100">
        <div class="card" style="width: 500px">
            <div class="card-header">
                <h2>Acceso al sistema</h1>
            </div>
            <div class="card-body">
                <form id="formElem">
                    <div class="mb-3">
                        <label class="form-label">Nombre de usuario</label>
                        <input type="text" class="form-control form-control-lg bg-light fs-6" required="required" name = "username">
                        <div id="emailHelp" class="form-text">No compartas tu usuario con nadie</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Contraseña</label>
                        <div class="d-flex gap-2">
                            <input id = "password" type="password" class="form-control form-control-lg bg-light fs-6" required="required" name = "password" placeholder="Contraseña">
                            <a class="btn btn-light btn-lg" onclick="showPassword(['password'])"><i class="fa-solid fa-eye"></i></a>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-light btn-lg w-100 border" onclick="singin()">Iniciar sesión</button>
                </form>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="assets/js/notifications.js"></script>
    <script src="assets/js/main.js"></script>
    <script src="assets/js/adminMethods.js"></script>
</body>
</html>
