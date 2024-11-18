<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Review</title>
        <!--STYLES-->
        <link rel="stylesheet" href="assets/css/styles.css"/>
        <!--CDN IMPLEMENTS-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA==" crossorigin="anonymous" referrerpolicy="no-referrer" />        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <%@include file="components/header.jsp" %>
        <div class="container d-flex flex-column" style="min-height: 80vh"> 
            <h1>Porfavor, revisar antes de pagar</h1>
            <hr class="my-3">
            <div class="row g-5">
                <div class="col-md-6 order-md-last">
                    <h4 class="d-flex justify-content-between align-items-center mb-3">
                        <span class="text-primary">Información de la compra</span>
                    </h4>
                    <ul class="list-group mb-3">
                        <li class="list-group-item d-flex justify-content-between lh-sm">
                            <div>
                                <h6 class="my-0">Descripción</h6>
                                    <small class="text-muted">${transaction.description}</small>
                            </div>
                        </li>
                        <li class="list-group-item d-flex justify-content-between lh-sm">
                            <div>
                                <h6 class="my-0">Subtotal</h6>
                                <small class="text-muted">${transaction.amount.details.subtotal} USD</small>
                            </div>
                        </li>
                        <li class="list-group-item d-flex justify-content-between">
                            <span>Total (USD)</span>
                            <strong>${transaction.amount.total} USD</strong>
                        </li>
                    </ul>
                </div>
                <div class="col-md-6">
                    <h4 class="mb-2">Información del propietario</h4>
                    <form action="execute_payment" class="needs-validation" novalidate method="post">
                        <input type="hidden" name="paymentId" value="${param.paymentId}" />
                        <input type="hidden" name="PayerID" value="${param.PayerID}" />
                        <div class="row g-3">
                            <div class="col-sm-6">
                                <label for="firstName" class="form-label">Nombre</label>
                                <input value="${payer.firstName}" type="text" class="form-control" id="firstName"disabled>
                            </div>
                            <div class="col-sm-6">
                                <label for="lastName" class="form-label">Apellido</label>
                                <input value="${payer.lastName}" type="text" class="form-control" id="lastName" placeholder value required disabled>
                                <div class="invalid-feedback">
                                    Se requiere apellido válido.
                                </div>
                            </div>
                            <div class="col-12">
                                <label for="email" class="form-label">Email</label>
                                <input value="${payer.email}" type="email" class="form-control" id="email" placeholder="tu@example.com" disabled>
                            </div>
                        </div>
                        <hr class="my-3">
                        <button class="w-100 btn btn-primary btn-lg" type="submit">Confirmar pago</button>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="components/footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="assets/js/toastr.min.js"></script>
        <script src="assets/js/notifications.js"></script>
    </body>
</html>