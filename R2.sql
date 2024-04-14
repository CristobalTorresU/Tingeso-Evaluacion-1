-- R2:
-- Listado de los 11 tipos de reparaciones vs el número de tipos de vehículos
-- que se repararon y el monto total que representa dichas reparaciones. (Ordenar
-- de mayor a menor por monto).

SELECT 
	rep.reparationtype, 
	SUM(rep.totaamount) AS sumita, 
	COUNT(rep.reparationtype),
	veh.type
	FROM repair AS rep
JOIN vehicle AS veh ON rep.vehicle_id = veh.vehicle_id
GROUP BY reparationtype, type
ORDER BY sumita DESC;
