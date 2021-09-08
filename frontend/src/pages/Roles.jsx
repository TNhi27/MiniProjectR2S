import React, { useEffect, useState } from "react";
import { deleteRole, getRole, saveRole, updateRole } from "../service/Role";
import "./Roles.scss";

function Roles(props) {
  const [roles, setRoles] = useState([]);
  const [role, setRole] = useState({});
  const [isLogin, setIsLogin] = useState(false);

  useEffect(() => {
    getRole().then((res) => {
      setRoles(res.data);
      console.log(res.data);
    });
    if (window.localStorage.getItem("token")!==null) {
      setIsLogin(true)
    }
  }, []);

  const onChangeRole = (e) => {
    setRole({ ...role, [e.target.name]: e.target.value });
  };

  const save = () => {
    saveRole(role).then((res) => {
      setRole(res.data);
      getRole().then((res) => {
        setRoles(res.data);
      });
    });
  };

  const updateR = () => {
    updateRole(role, role.id)
      .then((res) => {
        setRole(res.data);
        getRole().then((res) => {
          setRoles(res.data);
        });
      })
      .catch((er) => {
        alert(er);
      });
  };

  const del = () => {
    deleteRole(role.id)
      .then((res) => {
        alert("Xoa thanh cong");
        setRole({
          id: "",
          role: "",
        });
        getRole().then((res) => {
          setRoles(res.data);
        });
      })
      .catch((er) => {
        alert("Khong the xoa vao luc nay");
      });
  };

  if (isLogin==false) {
    return <div>Chua login</div>;
  } else {
    return (
      <div className="users">
        <div className="form">
          <div className="form-gr">
            <span>ID</span>
            <input
              name="id"
              type="text"
              value={role.id}
              onChange={(e) => onChangeRole(e)}
            />
          </div>
          <div className="form-gr">
            <span>ROLE</span>
            <input
              name="role"
              type="text"
              value={role.role}
              onChange={(e) => onChangeRole(e)}
            />
          </div>

          <div className="edit-container">
            <button onClick={() => save()} className="edit-button">
              SAVE
            </button>
            <button onClick={() => updateR()} className="edit-button">
              UPDATE
            </button>
            <button onClick={() => del()} className="edit-button">
              DELETE
            </button>
            <button
              onClick={() =>
                setRole({
                  id: "",
                  role: "",
                })
              }
              className="edit-button"
            >
              RESET
            </button>
          </div>
        </div>
        <div className="table">
          <table id="customers">
            <tr>
              <th>ID</th>
              <th>ROLE</th>
              <th>#</th>
            </tr>
            {roles.map((e, i) => {
              return (
                <tr>
                  <td>{e.id}</td>
                  <td>{e.role}</td>

                  <td>
                    <button onClick={() => setRole(e)} className="btn">
                      EDIT
                    </button>
                  </td>
                </tr>
              );
            })}
          </table>
        </div>
      </div>
    );
  }
}

export default Roles;
