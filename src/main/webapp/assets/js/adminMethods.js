function singin(){
    const formEle = document.getElementById('formElem');

    formEle.addEventListener('submit', function(e) {
        e.preventDefault();

        var formData = new FormData(formEle);
        fetch('admin/session',{
            method: 'POST',
            body: formData
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                showToast(data.type, data.tittle, data.message);
                setTimeout(() => {
                    window.location.href = "/peruinolvidable/admin/dashboard";
                }, 1000);
            }else {
                showToast(data.type, data.message);
                formEle.removeEventListener('submit', arguments.callee);
            }

        })
        .catch(error => {
            showToast('error', 'Error al iniciar sesión');
            console.error('Error:', error);
        });
    });
}

function createProduct(){
    const formEle = document.getElementById('formElem');

    formEle.addEventListener('submit', function(e) {
        e.preventDefault();

        var formData = new FormData(formEle);
        fetch('/peruinolvidable/admin/product',{
            method: 'POST',
            body: formData
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                showToast(data.type, data.tittle, data.message);
                setTimeout(() => {
                    window.location.href = "/peruinolvidable/admin/products";
                }, 1000);
            }else {
                showToast(data.type, data.tittle, data.message);
                formEle.removeEventListener('submit', arguments.callee);
            }

        })
        .catch(error => {
            showToast('error', 'Error al procesar solicitud');
            console.error('Error:', error);
        });
    });
}

function updateProduct(){
    const formEle = document.getElementById('formElem');

    formEle.addEventListener('submit', function(e) {
        e.preventDefault();
        var formData = new FormData(formEle);
        console.log(formData.get('name'));
        fetch('product',{
            method: 'PUT',
            body: formData
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                showToast(data.type, data.tittle, data.message);
                setTimeout(() => {
                    window.location.href = "/peruinolvidable/admin/products";
                }, 1000);
            }else {
                showToast(data.type, data.tittle, data.message);
                formEle.removeEventListener('submit', arguments.callee);
            }

        })
        .catch(error => {
            showToast('error', 'Error al actualizar producto');
            console.error('Error:', error);
        });
    });
}

function deletedProduct(product_id){
    fetch(`product?product_id=${product_id}`,{
            method: 'DELETE'
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                showToast(data.type, data.tittle, data.message);
                setTimeout(() => {
                    window.location.href = "/peruinolvidable/admin/products";
                }, 1000);
            }else {
                showToast(data.type, data.tittle, data.message);
            }
        })
        .catch(error => {
            showToast('error', 'Error al eliminar cuenta');
            console.error('Error:', error);
        });
}


function createCategory(){
    const formEle = document.getElementById('formElem');

    formEle.addEventListener('submit', function(e) {
        e.preventDefault();

        var formData = new FormData(formEle);
        fetch('/peruinolvidable/admin/category',{
            method: 'POST',
            body: formData
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                formEle.removeEventListener('submit', arguments.callee);
                showToast(data.type, data.tittle, data.message);
                setTimeout(() => {
                    window.location.href = "/peruinolvidable/admin/categories";
                }, 1000);
            }else {
                showToast(data.type, data.tittle, data.message);
                formEle.removeEventListener('submit', arguments.callee);
            }

        })
        .catch(error => {
            showToast('error', 'Error al procesar solicitud');
            console.error('Error:', error);
        });
    });
}

function updateCategory(){
    const formEle = document.getElementById('formElem');

    formEle.addEventListener('submit', function(e) {
        e.preventDefault();
        var formData = new FormData(formEle);
        fetch('category',{
            method: 'PUT',
            body: formData
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                showToast(data.type, data.tittle, data.message);
                formEle.removeEventListener('submit', arguments.callee);
            }else {
                showToast(data.type, data.tittle, data.message);
                formEle.removeEventListener('submit', arguments.callee);
            }

        })
        .catch(error => {
            showToast('error', 'Error al procesar operación');
            console.error('Error:', error);
        });
    });
}

function deleteCategory(category_id){
    fetch(`category?category_id=${category_id}`,{
            method: 'DELETE'
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                showToast(data.type, data.tittle, data.message);
                setTimeout(() => {
                    window.location.href = "/peruinolvidable/admin/categories";
                }, 1000);
            }else {
                showToast(data.type, data.tittle, data.message);
            }
        })
        .catch(error => {
            showToast('error', 'Error al procesar método');
            console.error('Error:', error);
        });
}

function updateOrder(order_state, order_id){
    fetch(`/peruinolvidable/admin/sale?sale_state=${order_state}&sale_id=${order_id}`,{
            method: 'PUT'
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                showToast(data.type, data.tittle, data.message);
                setTimeout(() => {
                    window.location.href = "/peruinolvidable/admin/sales";
                }, 1000);
            }else {
                showToast(data.type, data.tittle, data.message);
            }
        })
        .catch(error => {
            showToast('error', 'Error al procesar método');
            console.error('Error:', error);
        });
}