import React, { useEffect, useState } from "react";
import { getProfile, saveUser, sendFile, updateUser } from "../service/UserService";
import "./Profile.scss";

function Profile(props) {
  const [isLogin, setIsLogin] = useState(false);
  const [profile, setProfile] = useState({});
  const [img, setImg] = useState("");

  useEffect(() => {
    if (window.localStorage.getItem("token") !== null) {
      setIsLogin(true);
      getPro()
    }
  }, []);

  const getPro = () => {
  
    getProfile("a").then((res) => {
      setProfile(res.data);
    });
  };

  const onChangeProfile = (e) => {
    setProfile({ ...profile, [e.target.name]: e.target.value });
  };

  const send = () => {
    sendFile(img, profile.username)
      .then((res) => {
        setProfile({ ...profile, image: res.data });
      })
      .then(() => {
        alert("Upload thanh cong");
      }).catch((er)=>{
        alert(er)
      });
  };


  const saveProfile= ()=>{
    updateUser({
      username: profile.username,
      password:profile.password,
      fullname:profile.fullname,
      birthday:profile.birthday,
      address:profile.address,
      image:profile.image,
      active:profile.active,
      role:profile.role.id,
      
    }).then((res)=>{
      console.log(res.data);
    })
  }
  if (isLogin == false) {
    return <div>Chua login</div>;
  } else {
    return (
      <div className="profile">
        <div className="form">
          <div className="image-user">
            <img
              src={
                profile.image == null
                  ? "./unnamed.jpg"
                  : `http://localhost:8080/upload/${profile.image}`
              }
            />
            <input
            onChange={(e) => setImg(e.target.files[0])}
            type="file"
            name=""
            id=""
            accept="image/*"
          />
          <button onClick={() =>send()}>Upload</button>
          </div>
          <div className="form-gr">
            <span>USERNAME</span>
            <input value={profile.username} type="text" />
          </div>
          <div className="form-gr">
            <span>FULLNAME</span>
            <input
              value={profile.fullname}
              type="text"
              name="fullname"
              onChange={(e) => onChangeProfile(e)}
            />
          </div>
          <div className="form-gr">
            <span>BRITHDAY</span>
            <input
              onChange={(e) => onChangeProfile(e)}
              name="birthday"
              value={profile.birthday}
              type="date"
            />
          </div>
          <div className="form-gr">
            <span>ADDRESS</span>
            <textarea value={profile.address} name="address" onChange={(e) => onChangeProfile(e)} id="" cols="20" rows="4"></textarea>
          </div>

          <div className="edit-container">
            <button onClick={() => saveProfile()} className="edit-button">
              SAVE
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default Profile;
