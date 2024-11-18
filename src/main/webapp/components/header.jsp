<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.alexanderdoma.peruinolvidable.model.mysql.CategoryDAO"%>
<%@page import="com.alexanderdoma.peruinolvidable.model.entity.Category"%>
<%@page import="java.util.List"%>

<div id="header" class="sticky-top bg-white pt-3" style="min-height: 20vh">
    <div>
        <header class="d-grid container-sm">
            <div class="row">
                <div class="col d-flex justify-content-start align-items-center">
                    <a href="#"><i class="fa-solid fa-magnifying-glass" onclick="showToast('info', 'Pronto se habilitará esta función', 'Función inhabilitida')"></i></a>
                </div>
                <div class="col-6 d-flex justify-content-center">
                    <a class="d-flex gap-2 text-decoration-none text-dark" href="<%= request.getContextPath()%>">PERÚ INOLVIDABLE</a>
                </div>
                <div class="col d-flex justify-content-end align-items-center gap-4">
                    <a href="<%=request.getContextPath() + "/cart"%>" class="text-secondary">
                        <i class="fa-solid fa-cart-shopping"></i>
                    </a>
                    <a href="<%=request.getContextPath() + "/orders"%>" class="text-secondary">
                        <i class="fa-solid fa-file-circle-check"></i>
                    </a>
                    <a href="<%=request.getContextPath() + "/profile"%>" class="text-secondary">
                        <i class="fa-solid fa-user"></i>
                    </a>
                </div>
            </div>
            <!-- Barra de navegación - Bottom -->
            <div class="row mt-4 mb-4">
                <!-- Menu - links -->
                <div class="col-12">
                    <ul class=" d-flex justify-content-center list-group list-group-flush list-group-horizontal list-unstyled">
                        <li class="list-group-item link text-secondary link-underline-opacity-0">
                            <a class="text-secondary link-underline link-underline-opacity-0 current" href="<%= request.getContextPath()%>">Inicio</a>
                        </li>
                        <% request.setAttribute("categories", new CategoryDAO().getAll()); %>
                        <c:forEach  items="${categories}" var="category" end="4">
                            <li class="list-group-item link text-secondary link-underline-opacity-0">
                                <a class="text-secondary link-underline link-underline-opacity-0 current"
                                   href="category?category_id=${category.getId()}">${category.getName()}</a>
                            </li>
                        </c:forEach>
                        <li class="list-group-item link text-secondary link-underline-opacity-0">
                            <a class="text-secondary link-underline link-underline-opacity-0 current" href="products">Ver todo</a>
                        </li>
                    </ul>
                </div>
            </div>
        </header>
    </div>
</div>