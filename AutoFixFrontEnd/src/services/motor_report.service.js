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

export default { getAll, generate, getOrder };