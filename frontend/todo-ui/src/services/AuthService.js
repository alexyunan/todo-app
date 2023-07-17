import axios from "axios";

const AUTH_REST_API_URL = "http://localhost:8080/api/auth";

export const registerCall = (registerObj) => axios.post(AUTH_REST_API_URL + "/register", registerObj);

export const loginCall = (username, password) => axios.post(AUTH_REST_API_URL + "/login", { username, password });

//store and get token
export const storeToken = (token) => localStorage.setItem("token", token);

export const getToken = () => localStorage.getItem("token");

export const saveLoggedInUser = (username) => sessionStorage.setItem("authenticatedUser", username);

export const isUserLoggedIn = () => {
  const username = sessionStorage.getItem("authenticatedUser");
  if (username == null) {
    return false;
  } else {
    return true;
  }
};

export const getLoggedInUser = () => {
  const username = sessionStorage.getItem("authenticatedUser");
  return username;
};
