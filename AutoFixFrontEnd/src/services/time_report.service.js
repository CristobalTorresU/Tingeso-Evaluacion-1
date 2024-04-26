import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/timereports/');
}

const generate = () => {
    return httpClient.get('/timereports/generate');
}

const getOrder = () => {
    return httpClient.get('/timereports/ordered');
}

const get = id => {
    return httpClient.get(`/timereports/${id}`);
}

const create = data => {
    return httpClient.post("/timereports/", data);
}

export default { getAll, generate, getOrder, get, create };