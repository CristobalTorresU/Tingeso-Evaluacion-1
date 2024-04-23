import { useState } from "react";
import { useNavigate } from "react-router-dom";
import repairService from "../services/repair.service";
import CalculateIcon from "@mui/icons-material/Calculate";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import DatePicker from "react-datepicker";

const RepairCalculate = () => {
  const [plate, setPlate] = useState("");
  const [checkinDate, setCheckinDate] = useState("");
  const [checkinHour, setCheckinHour] = useState("");
  const [reparationType, setReparationType] = useState("");
  const [exitDate, setExitDate] = useState("");
  const [exitHour, setExitHour] = useState("");
  const [collectDate, setCollectDate] = useState("");
  const [collectHour, setCollectHour] = useState("");

  const navigate = useNavigate();

  const calculateRepair = (r) => {
    r.preventDefault();
    console.log("Solicitar calcular reparacion.");
    repairService
      .calculate(plate, checkinDate, checkinHour, reparationType, exitDate, exitHour, collectDate, collectHour)
      .then((response) => {
        console.log("Reparacion ha sido actualizada.", response.data);
        navigate("/repair/list");
      })
      .catch((error) => {
        console.log(
          "Ha ocurrido un error al intentar calcular la reparacion.",
          error
        );
      });
    console.log("Fin calculo reparacion.");
  };

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      component="form"
    >
      <hr />
      <h3> Calcular Reparación </h3>
      <hr />
      <form>
        <FormControl fullWidth>
          <TextField
            id="plate"
            label="Patente"
            value={plate}
            variant="standard"
            onChange={(r) => setPlate(r.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="checkinDate"
            label="Fecha Entrada"
            value={checkinDate}
            variant="standard"
            onChange={(r) => setCheckinDate(r.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="checkinHour"
            label="Hora Entrada"
            value={checkinHour}
            variant="standard"
            onChange={(r) => setCheckinHour(r.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="reparationType"
            label="Tipo de Reparación"
            value={reparationType}
            select
            variant="standard"
            defaultValue="1"
            onChange={(r) => setReparationType(r.target.value)}
            style={{ width: "25%" }}
          >
            <MenuItem value={1}>Reparaciones del Sistema de Frenos</MenuItem>
            <MenuItem value={2}>Servicio del Sistema de Refrigeración</MenuItem>
            <MenuItem value={3}>Reparaciones del Motor</MenuItem>
            <MenuItem value={4}>Reparaciones de la Transmisión</MenuItem>
            <MenuItem value={5}>Reparación del Sistema Eléctrico</MenuItem>
            <MenuItem value={6}>Reparaciones del Sistema de Escape</MenuItem>
            <MenuItem value={7}>Reparación de Neumáticos y Ruedas</MenuItem>
            <MenuItem value={8}>Reparaciones de la Suspensión y la Dirección</MenuItem>
            <MenuItem value={9}>Reparación del Sistema de Aire Acondicionado y Calefacción</MenuItem>
            <MenuItem value={10}>Reparaciones del Sistema de Combustible</MenuItem>
            <MenuItem value={11}>Reparación y Reemplazo del Parabrisas y Cristales</MenuItem>
          </TextField>
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="exitDate"
            label="Fecha de Salida"
            value={exitDate}
            variant="standard"
            onChange={(r) => setExitDate(r.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="exitHour"
            label="Hora de Salida"
            value={exitHour}
            variant="standard"
            onChange={(r) => setExitHour(r.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="collectDate"
            label="Fecha de Recolección"
            value={collectDate}
            variant="standard"
            onChange={(r) => setCollectDate(r.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="collectHour"
            label="Hora de Recolección"
            value={collectHour}
            variant="standard"
            onChange={(r) => setCollectHour(r.target.value)}
          />
        </FormControl>

        <FormControl>
          <br />
          <Button
            variant="contained"
            color="info"
            onClick={(r) => calculateRepair(r)}
            style={{ marginLeft: "0.5rem" }}
            startIcon={<CalculateIcon />}
          >
            Calcular Reparación
          </Button>
        </FormControl>
      </form>
    </Box>
  );
};

export default RepairCalculate;