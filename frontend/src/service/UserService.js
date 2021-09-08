
import axios from "axios"





const getListUsers = async (page, size) => {
    const result = await axios.get(`http://localhost:8080/api/v1/users?pageNumber=${page}&size=${size}`);
    return result;
}
const getUserByUsername = async (username) => {
    const result = await axios.get(`http://localhost:8080/api/v1/users/${username}`);
    return result;
}

const sendFile = async (file, username) => {
    let data = new FormData();
    data.append("file", file);

    const result = await axios.post(`http://localhost:8080/api/v1/users/${username}/upload`, data);
    return result;
}

const saveUser = async (user) => {
    const result = await axios.post(`http://localhost:8080/api/v1/users`, user);
    return result;
}

const updateUser = async (user) => {
    const result = await axios.put(`http://localhost:8080/api/v1/users/${user.username}`, user);
    return result;
}

const deleteUser = async (username) => {
    const result = await axios.delete(`http://localhost:8080/api/v1/users/${username}`);
    return result;
}

const login = async (u, p) => {
    const result = await axios.post(`http://localhost:8080/login`, null, {
        params: {
            username: u,
            password: p
        }
    });
    return result;
}
const saveToStore = (data) => {
    window.localStorage.setItem("token", data)
}

const delToken = () => {
    window.localStorage.removeItem("token")
}

const getProfile = async (token) => {
    const result = await axios.get('http://localhost:8080/profile', {
        headers: {
            Authorization: "Bearer "+"eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJhZG1pbiIsImV4cCI6MTYzMTA4NzQ0MX0.hGwNB90mKhY3VoMMdAy2XeH-yeo7Z5gdpCjfB-1T43OQJuPLY-Z8b1T9HMOUFUGzO9IT19Q2DY1CZxqypMZZFQ"
        }
    })
    return result;
}

export {
    getListUsers,
    getUserByUsername,
    sendFile,
    saveUser,
    updateUser,
    deleteUser,
    login,
    saveToStore,
    delToken, getProfile
}