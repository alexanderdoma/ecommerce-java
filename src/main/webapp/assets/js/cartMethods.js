function addItem() {
    const formEle = document.getElementById('formElem');
    formEle.addEventListener('submit', function(e) {
        e.preventDefault();
        var data = new FormData(formEle);
        fetch('cart/add',{
            method: 'POST',
            body: data
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                showToast(data.type, data.tittle);
                formEle.removeEventListener('submit', arguments.callee);
                setTimeout(() => {
                    window.location.reload();
                }, 1000);
            }else {
                showToast(data.type, data.tittle);
                formEle.removeEventListener('submit', arguments.callee);
            }
        })
        .catch(error => {
            showToast('error', 'Error al agregar item');
            console.error('Error:', error);
        });
    });
}

function removeItem(id_product){
    fetch(`cart/remove?orderline_id=${id_product}`,{
            method: 'DELETE'
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                showToast(data.type, data.tittle);
                setTimeout(() => {
                    window.location.reload();
                }, 1000);
            }else {
                showToast(data.type, data.tittle);
            }

        })
        .catch(error => {
            showToast('error', 'Error al eliminar pedido');
            console.error('Error:', error);
        });
}

function addOneProduct() {
    var quantityInput = document.getElementById(`quantity`);
    let max = parseInt(quantityInput.max);
    let value = quantityInput.value;
    if(quantityInput.value < max) {
        value ++;
    }
    quantityInput.value = value;
}

function removeOneProduct() {
    var quantityInput = document.getElementById(`quantity`);
    let value = quantityInput.value;
    if(quantityInput.value > 1){
        value--;
    }
    quantityInput.value = value;
}

function addOneProductByOrderline(orderline_id) {
    var quantityInput = document.getElementById(`quantity-product-${orderline_id}`);
    let max = parseInt(quantityInput.max);
    let value = quantityInput.value;
    if(quantityInput.value < max) {
        value ++;
    }
    quantityInput.value = value;
}

function removeOneProductByOrderline(orderline_id) {
    var quantityInput = document.getElementById(`quantity-product-${orderline_id}`);
    let value = quantityInput.value;
    if(quantityInput.value > 1){
        value--;
    }
    quantityInput.value = value;
}

function updateItem() {
    const formEle = document.getElementById('formElem');
    var data = new FormData(formEle);
    
    fetch('cart/update',{
            method: 'PUT',
            body: data
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                showToast(data.type, data.tittle);
            }else {
                showToast(data.type, data.tittle);
            }
        })
        .catch(error => {
            showToast('error', 'Error al actualizar item');
            console.error('Error:', error);
        });
}

function updateItemByOrderline(orderline_id) {
    const formEle = document.getElementById(`formElem-${orderline_id}`);
    var data = new FormData(formEle);
    
    fetch('cart/update',{
            method: 'PUT',
            body: data
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                showToast(data.type, data.tittle);
            setTimeout(() => {
                window.location.reload();
            }, 1000);
            
            }else {
                showToast(data.type, data.tittle);
            }
        })
        .catch(error => {
            showToast('error', 'Error al actualizar item');
            console.error('Error:', error);
        });
}
