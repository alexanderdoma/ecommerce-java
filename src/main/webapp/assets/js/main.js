function showPassword() {
    var passwordField = document.getElementById("password");
    if (passwordField.type === 'text') {passwordField.type = "password"; return ;}
    passwordField.type = "text";
}

function showPassword(idList) {
    for (let i = 0; i < idList.length; i++){
        var passwordField = document.getElementById(idList[i]);
        if(passwordField.type === "text"){
            passwordField.type = "password";
        }else{
            passwordField.type = "text";
        }
    }
}

