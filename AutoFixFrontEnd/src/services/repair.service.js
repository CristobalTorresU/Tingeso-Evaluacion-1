import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/repairs/');
}

const get = id => {
    return httpClient.get(`/repairs/${id}`);
}

// TODO: Arreglar "calculate".
const calculate = () => {
    return httpClient.get("/repairs/calculate",);
}

export default { getAll, get, calculate };