import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/repairs/');
}

const get = id => {
    return httpClient.get(`/repairs/${id}`);
}

const calculate = (plate,mileage,checkinDate,checkinHour,reparationType,exitDate,exitHour,collectDate,collectHour) => {
    return httpClient.get("/repairs/calculate",{params:{plate,mileage,checkinDate,checkinHour,reparationType,exitDate,exitHour,collectDate,collectHour}});
}

export default { getAll, get, calculate };