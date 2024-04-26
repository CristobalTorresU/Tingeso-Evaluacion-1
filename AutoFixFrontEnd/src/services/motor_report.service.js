import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/motorreports/');
}

const generate = () => {
    return httpClient.get('/motorreports/generate');
}

const getOrder = () => {
    return httpClient.get('/motorreports/ordered');
}

const get = id => {
    return httpClient.get(`/motorreports/${id}`);
}

const create = data => {
    return httpClient.post("/motorreports/", data);
}

export default { getAll, generate, getOrder, get, create };