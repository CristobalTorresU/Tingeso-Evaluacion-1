-- R3:
-- Listado con los tiempos promedio de reparación por cada una de las marcas
-- de vehículos (ordenado por tiempos de menor a mayor).

SELECT 
	brand,
	AVG(duracion) AS promedios
FROM (SELECT 
	veh.brand,
	AGE(rep.exitdate::DATE + rep.exithour::TIME, rep.checkindate::DATE + rep.checkintime::TIME) AS duracion
FROM repair AS rep
JOIN vehicle AS veh ON veh.vehicle_id = rep.vehicle_id) AS subquery
GROUP BY brand
ORDER BY promedios ASC;
