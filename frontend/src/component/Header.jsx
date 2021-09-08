import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./Home.scss";

function Header(props) {

  const logout=()=>{
    window.localStorage.removeItem("token");
    alert("Da dang xuat")
  }
 
  return (
    <div>
      <div className="home">
        <div className="hero">
          <img
            className="logo"
            src="https://r2s.com.vn/wp-content/uploads/2020/04/r2s.com_.vn_.png"
            alt=""
          />
          <div className="text">
            <h3>MINI PROJECT R2S</h3>
          </div>
          <nav className="menu">
            <ul>
           
              <li>
                <Link to="/users">CRUD USERS</Link>
              </li>
              <li>
                <Link to="/role" href="#">CURD ROLE</Link>
              </li>
              <li>
                <Link to="/profile" href="#">PROFILE</Link>
              </li>
              <li>
                <Link to="/login">LOGIN</Link>
              </li>
              <li>
                <a onClick={()=>logout()} href="#">LOGOUT</a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </div>
  );
}

export default Header;
