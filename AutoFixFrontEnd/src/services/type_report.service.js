import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/typereports/');
}

/*
const getAll = () => {
    return httpClient.get('/typereports/');
}
*/

const get = id => {
    return httpClient.get(`/typereports/${id}`);
}

const create = data => {
    return httpClient.post("/typereports/", data);
}

export default { getAll, get, create };