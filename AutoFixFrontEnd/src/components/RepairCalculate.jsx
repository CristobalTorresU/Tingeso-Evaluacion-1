import { useState } from "react";
import { useNavigate } from "react-router-dom";
import repairService from "../services/repair.service";
import CalculateIcon from "@mui/icons-material/Calculate";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";

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

  const calculate = (r) => {
    r.preventDefault();
    console.log("Solicitar calcular planilla.", year, "-", month);
    paycheckService
      .calculate(year, month)
      .then((response) => {
        console.log("Planilla ha sido actualizada.", response.data);
        navigate("/repair/list");
      })
      .catch((error) => {
        console.log(
          "Ha ocurrido un error al intentar calcular liquidaciones de sueldos.",
          error
        );
      });
    console.log("Fin calculo planilla.");
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
            variant="standard"
            onChange={(r) => setReparationType(r.target.value)}
          />
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
            onClick={(r) => calculate(r)}
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