import axios from "axios";

const BASE_REST_API_URL = "http://localhost:8080/api/todos";

export const getAllTodos = () => axios.get(BASE_REST_API_URL);

export const saveTodo = (todo) => axios.post(BASE_REST_API_URL + "/create", todo);

export const getTodo = (id) => axios.get(BASE_REST_API_URL + "/" + id);

export const updateTodo = (todo) =>
  axios
    .put(BASE_REST_API_URL + "/update", todo)
    .then((response) => {
      const message = response.data;
      console.log(message);
      return response;
    })
    .catch((error) => {
      console.log(error);
      throw error;
    });

export const deleteTodo = (id) =>
  axios
    .delete(BASE_REST_API_URL + "/delete/" + id)
    .then((response) => {
      const message = response.data;
      console.log(message);
      return response;
    })
    .catch((error) => {
      console.log(error);
      throw error;
    });

export const completeTodo = (id) => axios.patch(BASE_REST_API_URL + "/complete/" + id);

export const inCompleteTodo = (id) => axios.patch(BASE_REST_API_URL + "/in-complete/" + id);
