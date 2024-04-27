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

export default { getAll, generate, getOrder };