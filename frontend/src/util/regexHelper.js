export function nameValidation(name){
    const regex = new RegExp('^(?=.*[A-Z][a-z])[A-Z][a-z]{1,30}$', 'gm');
    return regex.test(name);
}

export function usernameValidation(username){
    const regex = new RegExp('^(?=.*[A-Za-z]).{1,30}$', 'gm');
    return regex.test(username);
}

export function emailValidation(email){
    const regex = new RegExp('^(.+)@(.+)\\.+(.+)$','gm');
    return regex.test(email);
}

export function passwordValidation(password){
    const regex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,16}$', 'gm');
    return regex.test(password);
}