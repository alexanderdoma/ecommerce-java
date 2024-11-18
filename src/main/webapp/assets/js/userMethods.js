function singin(){
    const formEle = document.getElementById('formElem');

    formEle.addEventListener('submit', function(e) {
        e.preventDefault();

        var data = new FormData(formEle);
        fetch('session',{
            method: 'POST',
            body: data
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                showToast(data.type, data.tittle);
                formEle.removeEventListener('submit', arguments.callee);
                setTimeout(() => {
                    window.location.href = "/peruinolvidable";
                }, 1000);
            }else {
                showToast(data.type, data.tittle);
                formEle.removeEventListener('submit', arguments.callee);
            }
        })
        .catch(error => {
            showToast('error', 'Error al iniciar sesión');
            console.error('Error:', error);
        });
    });
}

function singup(){
    const formEle = document.getElementById('formElem');

    formEle.addEventListener('submit', function(e) {
        e.preventDefault();
        var data = new FormData(formEle);
        fetch('insert',{
            method: 'POST',
            body: data
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                showToast(data.type, data.tittle);
                formEle.removeEventListener('submit', arguments.callee);
                setTimeout(() => {
                    window.location.href = "/peruinolvidable/login";
                }, 1000);
            }else {
                showToast(data.status, data.message);
                formEle.removeEventListener('submit', arguments.callee);
            }

        })
        .catch(error => {
            showToast('error', 'Error al crear cuenta');
            console.error('Error:', error);
        });
    });
}

function logout() {
    fetch('logout',{
        method: 'POST'
    })
    .then(response => response.json())

    .then(data => {
        if(data.type === 'success'){
            showToast(data.type, data.tittle);
            setTimeout(() => {
                window.location.href = "/peruinolvidable/";
            }, 1000);
        }else {
            showToast(data.type, data.tittle);
        }
    })
    .catch(error => {
        showToast('error', 'Error al cerrar sesión');
        console.error('Error:', error);
    });
}

function updateProfile(){
    const formEle = document.getElementById('formElem');
    formEle.addEventListener('submit', function(e) {
        e.preventDefault();

        var data = new FormData(formEle);
        fetch('update',{
            method: 'PUT',
            body: data
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                showToast(data.type, data.tittle, data.message);
                formEle.removeEventListener('submit', arguments.callee);
            }else {
                showToast(data.status, data.tittle, data.message);
                formEle.removeEventListener('submit', arguments.callee);
            }

        })
        .catch(error => {
            showToast('error', 'Error al actualizar información de usuario');
            console.error('Error:', error);
        });
    });
}

function deleteAccount(){
    fetch('delete',{
            method: 'DELETE'
        })
        .then(response => response.json())

        .then(data => {
            if(data.type === 'success'){
                showToast(data.type, data.tittle, data.message);
                setTimeout(() => {
                    window.location.href = "/peruinolvidable/";
                }, 1000);
            }else {
                showToast(data.type, data.tittle);
            }
        })
        .catch(error => {
            showToast('error', 'Error al eliminar cuenta');
            console.error('Error:', error);
        });
}
