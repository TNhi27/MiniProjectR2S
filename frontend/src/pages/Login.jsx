import React, { useState } from "react";
import { login, saveToStore } from "../service/UserService";
import "./Login.scss";

function Login(props) {
  const [user, setUser] = useState({
    username: "",
    password: "",
  });

  const onChangeUser = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const doLogin=()=>{
      login(user.username,user.password).then((res) => {
          saveToStore(res.data)
          alert("Login thanh cong")
      }).catch((er) => {
          alert("Login that bai")
      })
  }
  return (
    <div className="login">
      <div className="form" >
        <span>Username</span>
        <input
          name="username"
          type="text"
          value={user.username}
          onChange={(e) => onChangeUser(e)}
        />
        <span>Password</span>
        <input
          name="password"
          type="password"
          value={user.password}
          onChange={(e) => onChangeUser(e)}
        />
        <button onClick={() =>doLogin()}>LOGIN</button>
      </div>
    </div>
  );
}

export default Login;
