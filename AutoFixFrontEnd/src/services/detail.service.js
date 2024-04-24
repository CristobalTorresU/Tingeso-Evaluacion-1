import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/details/');
}

const get = id => {
    return httpClient.get(`/details/${id}`);
}

const create = data => {
    return httpClient.post("/details/", data);
}

export default { getAll, get, create };