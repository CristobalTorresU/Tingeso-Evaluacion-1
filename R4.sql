-- R4
-- Listado de los 11 tipos de reparaciones vs el número de vehículos según
-- tipo de motor (gasolina, diesel, hibrido, eléctrico) que se repararon y el monto
-- total que representa dichas reparaciones. (Ordenar de mayor a menor por
-- monto).

SELECT 
	rep.reparationtype, 
	SUM(rep.totaamount) AS sumita, 
	COUNT(rep.reparationtype),
	veh.motor
	FROM repair AS rep
JOIN vehicle AS veh ON rep.vehicle_id = veh.vehicle_id
GROUP BY reparationtype, veh.motor
ORDER BY sumita DESC;
