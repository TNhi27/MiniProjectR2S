import React, { useEffect, useState } from "react";
import {
  deleteUser,
  getListUsers,
  getUserByUsername,
  saveUser,
  sendFile,
  updateUser,
} from "../service/UserService";
import "./Users.scss";

function Users(props) {
  const [isLogin,setIsLogin] = useState(false);
  let [users, setUsers] = useState([]);
  let [user, setUser] = useState({
    username: "",
    password: "",
    fullname: "",
    birthday: "",
    address: "",
    active: true,
    role: "",
  });
  let [img, setImg] = useState("");

  useEffect(() => {
    let list = getListUsers(0, 5).then((res) => {
      setUsers(res.data.content);
    });
    if (window.localStorage.getItem("token")!== null) {
      setIsLogin(true)
    }
  }, []);

  const setUserToForm = (username) => {
    let rs = getUserByUsername(username).then((res) => {
      setUser(res.data);
    });
  };

  const onChangeUser = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };
  const onChangeStatus = (e) => {
    setUser({ ...user, active: e.target.value === "true" ? true : false });
    console.log(user);
  };

  const send = () => {
    sendFile(img, user.username)
      .then((res) => {
        setUser({ ...user, image: res.data });
      })
      .then(() => {
        alert("tc");
      }).catch((er)=>{
        alert(er)
      });
  };

  const save = () => {
    saveUser(user)
      .then((res) => {
        getListUsers(0, 20).then((res) => {
          setUsers(res.data.content);
        });
        alert("Save thanh cong");
      })
      .catch((er) => {
        alert(er.response.data);
      });
  };

  const update = () => {
    updateUser(user)
      .then((res) => {
        getListUsers(0, 20).then((res) => {
          setUsers(res.data.content);
        });
        alert("Save thanh cong");
      })
      .catch((er) => {
        alert(er.response.data);
      });
  };

  const deleteU = (username) => {
    deleteUser(user.username)
      .then((res) => {
        getListUsers(0, 20).then((res) => {
          setUsers(res.data.content);
        });
        alert("Save thanh cong");
      })
      .catch((er) => {
        alert(er.response.data);
      });
  };

  const reset =()=>{
    setUser({
      username: "",
      password: "",
      fullname: "",
      birthday: "",
      address: "",
      active: true,
      role: ""
    })
  }

  if (isLogin==false) {
    return(<div>Chua login</div>)
  }else{

 
  return (
   
    <div className="users">
     
      <div className="form">
        <div className="image-user">
          <img
            src={
              user.image == null
                ? "./unnamed.jpg"
                : `http://localhost:8080/upload/${user.image}`
            }
            alt=""
          />

          <input
            onChange={(e) => setImg(e.target.files[0])}
            type="file"
            name=""
            id=""
            accept="image/*"
          />
          <button style={{width:"160px"}} className="btn" onClick={() => send()}>Upload</button>
        </div>
        <div className="form-gr">
          <span>USERNAME</span>
          <input
            onChange={(e) => onChangeUser(e)}
            name="username"
            value={user.username}
            type="text"
          />
        </div>

        <div className="form-gr">
          <span>FULLNAME</span>
          <input
            onChange={(e) => onChangeUser(e)}
            name="fullname"
            value={user.fullname}
            type="text"
          />
        </div>
        <div className="form-gr">
          <span>PASSWORD</span>
          <input
            onChange={(e) => onChangeUser(e)}
            name="password"
            value={user.password}
            type="password"
          />
        </div>
        <div className="form-gr">
          <span>BIRTHDAY</span>
          <input
            onChange={(e) => onChangeUser(e)}
            name="birthday"
            value={user.birthday}
            type="date"
          />
        </div>
        <div className="form-gr">
          <span>ADDRESS</span>
          <textarea
            value={user.address}
            onChange={(e) => onChangeUser(e)}
            name="address"
            id=""
            cols="20"
            rows="4"
          ></textarea>
        </div>

        <div className="form-gr">
          <span>STATUS</span>
          <input
            onChange={(e) => onChangeStatus(e)}
            type="radio"
            name="active"
            value={true}
            checked={user.active === true}
          />
          Active
          <input
            onChange={(e) => onChangeStatus(e)}
            type="radio"
            name="active"
            value={false}
            checked={user.active === false}
          />
          Inactive
        </div>
        <div className="form-gr">
          <span>ROLE</span>
          <input
            onChange={(e) => onChangeUser(e)}
            type="radio"
            name="role"
            id=""
            value="ADMIN"
            checked={user.role === "ADMIN"}
          />
          Admin
          <input
            onChange={(e) => onChangeUser(e)}
            type="radio"
            name="role"
            id=""
            value="PM"
            checked={user.role === "PM"}
          />
          PM
          <input
            onChange={(e) => onChangeUser(e)}
            type="radio"
            name="role"
            id=""
            value="CUSTOMER"
            checked={user.role === "CUSTOMER"}
          />
          Customer
        </div>
        <div className="edit-container">
          <button onClick={() => save()} className="edit-button">
            SAVE
          </button>
          <button onClick={() => update()} className="edit-button">
            UPDATE
          </button>
          <button onClick={() => deleteU()} className="edit-button">DELETE</button>
          <button onClick={() => reset()} className="edit-button">RESET</button>
        </div>
      </div>
      <div className="table">
        <table id="customers">
          <tr>
            <th>Username</th>
            <th>Fullname</th>
            <th>Brithday</th>
            <th>Status</th>
            <th>Role</th>
            <th>#</th>
          </tr>
          {users.map((user, index) => {
            return (
              <tr>
                <td>{user.username}</td>
                <td>{user.fullname}</td>
                <td>{user.birthday}</td>
                <td>{user.active == true ? "Active" : "Inactive"}</td>
                <td>{user.role.id}</td>
                <td>
                  <button
                    onClick={() => setUserToForm(user.username)}
                    className="btn"
                  >
                    EDIT
                  </button>
                </td>
              </tr>
            );
          })}
        </table>
      </div>
    </div>
  
  );}
}

export default Users;
