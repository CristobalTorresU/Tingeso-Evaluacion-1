import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/timereports/');
}

/*
const getAll = () => {
    return httpClient.get('/timereports/generate');
}
*/

const get = id => {
    return httpClient.get(`/timereports/${id}`);
}

const create = data => {
    return httpClient.post("/timereports/", data);
}

export default { getAll, get, create };