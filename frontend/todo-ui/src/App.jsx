import "./App.css";
import ListTodoComponent from "./Components/ListTodoComponent";
import HeaderComponent from "./Components/HeaderComponent";
import FooterComponent from "./Components/FooterComponent";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import TodoComponent from "./Components/TodoComponent";
import RegisterComponent from "./Components/RegisterComponent";
import LoginComponent from "./Components/LoginComponent";
import { isUserLoggedIn } from "./services/AuthService";

function App() {
  const AuthenticatedRoute = ({ children }) => {
    const isAuth = isUserLoggedIn();

    if (isAuth) {
      return children;
    }

    return <Navigate to={"/"} />;
  };

  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          {/* localhost:8080 */}
          <Route path='/' element={<LoginComponent />}></Route>
          {/* localhost:8080/todos */}
          <Route
            path='/todos'
            element={
              <AuthenticatedRoute>
                <ListTodoComponent />
              </AuthenticatedRoute>
            }
          ></Route>
          {/* localhost:8080/add-todo */}
          <Route
            path='/add-todo'
            element={
              <AuthenticatedRoute>
                <TodoComponent />
              </AuthenticatedRoute>
            }
          ></Route>
          {/* localhost:8080/update-todo/id */}
          <Route
            path='/update-todo/:id'
            element={
              <AuthenticatedRoute>
                <TodoComponent />
              </AuthenticatedRoute>
            }
          ></Route>
          {/* localhost:8080/register*/}
          <Route path='/register' element={<RegisterComponent />}></Route>
          {/* localhost:8080/login*/}
          <Route path='/login' element={<LoginComponent />}></Route>
        </Routes>
        <FooterComponent />
      </BrowserRouter>
    </>
  );
}

export default App;
