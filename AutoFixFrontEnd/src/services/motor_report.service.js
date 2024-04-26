import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/motorreports/');
}

/*
const getAll = () => {
    return httpClient.get('/motorreports/generate');
}
*/

const get = id => {
    return httpClient.get(`/motorreports/${id}`);
}

const create = data => {
    return httpClient.post("/motorreports/", data);
}

export default { getAll, get, create };