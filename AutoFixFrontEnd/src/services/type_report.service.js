import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/typereports/');
}

const generate = () => {
    return httpClient.get('/typereports/generate');
}

const getOrder = () => {
    return httpClient.get('/typereports/ordered');
}

const get = id => {
    return httpClient.get(`/typereports/${id}`);
}

const create = data => {
    return httpClient.post("/typereports/", data);
}

export default { getAll, generate, getOrder, get, create };