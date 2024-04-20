import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import repairService from "../services/repair.service";
import Button from "@mui/material/Button";
import CarRepair from "@mui/icons-material/CarRepair";


const RepairList = () => {
    const [repairs, setRepair] = useState([]);

    const init = () => {
        repairService
            .getAll()
            .then((response) => {
                console.log(
                    "Mostrando planilla de reparaciones.",
                    response.data
                );
                setRepair(response.data);
            })
            .catch((error) => {
                console.log(
                    "Se ha producido un error al intentar mostrar planilla de reparaciones.",
                    error
                );
            });
    };

    useEffect(() => {
        init();
    }, []);

    return (
        <TableContainer component={Paper}>
            <hr />
            <h3>Planilla de Reparaciones</h3>
            <hr />
            <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
                <TableHead>
                    <TableRow>
                        <TableCell align="right" sx={{ fontWeight: "bold" }}>
                            Patente
                        </TableCell>
                        <TableCell align="right" sx={{ fontWeight: "bold" }}>
                            Fecha Entrada
                        </TableCell>
                        <TableCell align="right" sx={{ fontWeight: "bold" }}>
                            Hora Entrada
                        </TableCell>
                        <TableCell align="right" sx={{ fontWeight: "bold" }}>
                            Reparación
                        </TableCell>
                        <TableCell align="right" sx={{ fontWeight: "bold" }}>
                            Fecha Salida
                        </TableCell>
                        <TableCell align="right" sx={{ fontWeight: "bold" }}>
                            Hora Salida
                        </TableCell>
                        <TableCell align="right" sx={{ fontWeight: "bold" }}>
                            Fecha Colecta
                        </TableCell>
                        <TableCell align="right" sx={{ fontWeight: "bold" }}>
                            Hora Colecta
                        </TableCell>
                        <TableCell align="right" sx={{ fontWeight: "bold" }}>
                            Monto Total
                        </TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {repairs.map((repair) => (
                        <TableRow
                            key={repairs.id}
                            sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                        >
                            <TableCell align="right">{repair.plate}</TableCell>
                            <TableCell align="right">{repair.checkinDate}</TableCell>
                            <TableCell align="right">{repair.checkinHour}</TableCell>
                            <TableCell align="right">{repair.reparationType}</TableCell>
                            <TableCell align="right">{repair.exitDate}</TableCell>
                            <TableCell align="right">{repair.exitHour}</TableCell>
                            <TableCell align="right">{repair.collectDate}</TableCell>
                            <TableCell align="right">{repair.collectHour}</TableCell>

                            <TableCell align="right">
                                {new Intl.NumberFormat("es-CL", { style: "decimal" }).format(
                                    repair.totalAmount
                                )}
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
            <br />
            <Link
                to="/repair/add"
                style={{ textDecoration: "none", marginBottom: "1rem"}}
            >
                <Button
                    variant="contained"
                    color="primary"
                    startIcon={<CarRepair />}
                >
                    Añadir Reparación
                </Button>
            </Link>
            <br /> <br />
        </TableContainer>
    );
};

export default RepairList;