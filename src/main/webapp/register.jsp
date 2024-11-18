<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--STYLES-->
        <link rel="stylesheet" href="assets/css/styles.css"/>
        <!--CDN IMPLEMENTS-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA==" crossorigin="anonymous" referrerpolicy="no-referrer" />        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <div class="container d-flex justify-content-center align-items-center min-vh-100">
            <div class="row p-3 bg-white shadow box-area">
                <div class="col-6 d-flex">
                    <div>
                        <img src="assets/img/cuzco-painting.jpeg" class="w-100">
                    </div>
                </div>
                <div class="col-6">
                    <div class="row align-items-start h-100">
                        <div>
                            <h2>Registro de usuario</h2>
                        </div>
                        <form id="formElem">
                            <div class="row mb-3">
                                <div class="col-6">
                                    <div class="form-floating">
                                        <input type="text" name="name" id="first_name floatingInput" class="form-control form-control-lg bg-light fs-6" placeholder="Nombres completos" tabindex="1" required>
                                        <label for="floatingInput">Nombres completos</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating">
                                        <input type="text" name="lastname" id="last_name floatingInput" class="form-control form-control-lg bg-light fs-6" placeholder="Apellidos" tabindex="2" required>
                                        <label for="floatingInput">Apellidos completos</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-floating mb-3">
                                <input id="floatingInput" type="text" name="username" class="form-control form-control-lg bg-light fs-6" placeholder="Nombre de usuario" tabindex="3" required>
                                <label for="floatingInput">Nombre de usuario</label>
                            </div>
                            <div class="row mb-5">
                                <div class="col-5">
                                    <div class="form-floating">
                                        <input type="password" id="password" name="password" class="form-control form-control-lg bg-light fs-6" placeholder="Contraseña de usuario" tabindex="5" required>
                                        <label for="floatingInput">Contraseña de usuario</label>
                                    </div>
                                </div>
                                <div class="col-5">
                                    <div class="form-floating">
                                        <input type="password" id="password_confirmation" name="password_confirmation" class="form-control form-control-lg bg-light fs-6" placeholder="Confirmar contraseña" tabindex="6" required>
                                        <label for="floatingInput">Confirmar contraseña</label>
                                    </div>
                                </div>
                                <div class="col-2"> 
                                    <a class="btn btn-light w-100 border h-100 d-flex align-items-center justify-content-center" onclick="showPassword(['password', 'password_confirmation'])"><i class="fa-solid fa-eye"></i></a>
                                </div>
                            </div>
                            <div class="input-group">
                                <button type="submit" class="btn btn-dark btn-block btn-lg form-control mb-3 fs-6" tabindex="7" onclick="singup()">Crear cuenta</button>
                            </div>
                            <div class="input-group d-flex gap-2">
                                <p>¿Ya tienes una cuenta?</p>
                                <a href="login">Inicia sesión</a>
                            </div>
                            <div class="input-group d-flex gap-2">
                                <p>¿Quieres regresar?</p>
                                <a href="/peruinolvidable">Click aqui</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="assets/js/notifications.js"></script>
        <script src="assets/js/main.js"></script>
        <script src="assets/js/userMethods.js"></script>
    </body>
</html>
