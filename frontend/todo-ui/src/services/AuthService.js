import axios from "axios";

const AUTH_REST_API_URL = "http://localhost:8080/api/auth";

export const registerCall = (registerObj) => axios.post(AUTH_REST_API_URL + "/register", registerObj);

export const loginCall = (username, password) => axios.post(AUTH_REST_API_URL + "/login", { username, password });
