import axios from "axios"

const getRole= async ()=>{
    const result = await axios.get(`http://localhost:8080/api/v1/role`);
    return result;
}

const saveRole =async (role)=>{
    const result = await axios.post(`http://localhost:8080/api/v1/role`,role);
    return result;
}

const updateRole = async (role, id)=>{
    const result = await axios.put(`http://localhost:8080/api/v1/role/${id}`,role);
    return result;
}

const deleteRole =async (id)=>{
    const result = await axios.delete(`http://localhost:8080/api/v1/role/${id}`);
    return result;
}
export {
    getRole,
    saveRole,
    updateRole,
    deleteRole
}