import httpClient from "../http-common";
//import axios from "axios";

const getAll = () => {
    return httpClient.get('/bonuses/');
}

const get = id => {
    return httpClient.get(`/bonuses/${id}`);
}

const create = data => {
    return httpClient.create("/bonuses/", data);
}
export default { getAll, get, create };