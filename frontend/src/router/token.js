import axios from "axios";

const AuthService = {
    getToken() {
        return localStorage.getItem("token");
    },
    getUsertype() {
        return localStorage.getItem("usertype");
    },
    async validateToken() {
        let response = await axios.get(process.env.VUE_APP_BASELINK+"/api/login/validateToken",{headers: {'token': this.getToken()}});
        if(response.status == 200) {
            return true;
        } else {
            localStorage.clear();
            return false;
        }
    }
}

export { AuthService }