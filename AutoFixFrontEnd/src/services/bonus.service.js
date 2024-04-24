import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/bonuses/');
}

const get = id => {
    return httpClient.get(`/bonuses/${id}`);
}

const create = data => {
    return httpClient.post("/bonuses/", data);
}

const update = data => {
    return httpClient.put('/bonuses/', data);
}

const remove = id => {
    return httpClient.delete(`/bonuses/${id}`);
}
export default { getAll, get, create, update, remove };