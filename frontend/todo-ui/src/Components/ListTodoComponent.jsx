import React, { useEffect, useState } from "react";
import { completeTodo, deleteTodo, getAllTodos, inCompleteTodo } from "../services/TodoService";
import { useNavigate } from "react-router-dom";

const ListTodoComponent = () => {
  const [todos, setTodos] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    listTodos();
  }, []);

  const listTodos = () => {
    getAllTodos()
      .then((response) => {
        setTodos(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const addNewTodo = () => {
    navigate("/add-todo");
  };

  const updateTodo = (id) => {
    console.log(id);
    navigate(`/update-todo/${id}`);
  };

  const removeTodo = (id) => {
    deleteTodo(id)
      .then((response) => {
        listTodos();
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const markCompleteTodo = (id) => {
    completeTodo(id)
      .then((response) => {
        listTodos();
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const markInCompleteTodo = (id) => {
    inCompleteTodo(id)
      .then((response) => {
        listTodos();
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className='container'>
      <h2 className='text-center'>List of Todos</h2>
      <button className='btn btn-primary mb-2' onClick={addNewTodo}>
        Add Todo
      </button>
      <div>
        <table className='table table-bordered table striped'>
          <thead>
            <tr>
              <th>Todo Title</th>
              <th>Todo Description</th>
              <th>Todo Completed</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {todos.map((todo) => (
              <tr key={todo.id}>
                <td>{todo.title}</td>
                <td>{todo.description}</td>
                <td>{todo.completed ? "YES" : "NO"}</td>
                <td>
                  <button className='btn btn-info' onClick={() => updateTodo(todo.id)}>
                    Update
                  </button>
                  <button className='btn btn-danger' style={{ marginLeft: "15px" }} onClick={() => removeTodo(todo.id)}>
                    Delete
                  </button>
                  <button className='btn btn-success' style={{ marginLeft: "15px" }} onClick={() => markCompleteTodo(todo.id)}>
                    Complete
                  </button>
                  <button className='btn btn-info' style={{ marginLeft: "15px" }} onClick={() => markInCompleteTodo(todo.id)}>
                    In Complete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ListTodoComponent;
