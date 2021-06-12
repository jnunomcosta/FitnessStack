const AuthService = {
    getToken() {
        return localStorage.getItem("token");
    },
    getUsertype() {
        return localStorage.getItem("usertype");
    },
}

export { AuthService }