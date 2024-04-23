import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import bonusService from "../services/bonus.service";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import SaveIcon from "@mui/icons-material/Save";
import { MenuItem } from "@mui/material";

const AddRepair = () => {
    const [brand, setBrand] = useState("");
    const [amount, setAmount] = useState("");
    const [used, setUsed] = useState("");
    const { id } = useParams();
    const [titleBonusForm, setTitleBonusForm] = useState("");
    const navigate = useNavigate();

    const saveBonus = (b) => {
        b.preventDefault();

        const bonus = { brand, amount, used, id };
        if (id) {
            bonusService
                .update(bonus)
                .then((response) => {
                    console.log("Vehículo ha sido actualizado.", response.data);
                    navigate("/bonus/list");
                })
                .catch((error) => {
                    console.log("Ocurrió un error al actualizar el vehículo.", error);
                });
        } else {
            bonusService
                .create(bonus)
                .then((response) => {
                    console.log("Vehículo ha sido registrado.", response.data);
                    navigate("/bonus/list");
                })
                .catch((error) => {
                    console.log("Ocurrió un error al crear el vehículo.", error);
                });
        }
    };

    useEffect(() => {
        if (id) {
            setTitleBonusForm("Editar Vehículo");
            bonusService
                .get(id)
                .then((bonus) => {
                    setBrand(bonus.data.brand);
                    setAmount(bonus.data.amount);
                    setUsed(bonus.data.used);
                })
                .catch((error) => {
                    console.log("Se produjo un error.", error);
                });
        } else {
            setTitleBonusForm("Registrar Nuevo Vehículo");
        }
    }, []);
    return (
        <Box
            display="flex"
            flexDirection="column"
            alignItems="center"
            justifyContent="center"
            component="form"
        >
            <hr />
            <h3> {titleBonusForm} </h3>
            <hr />
            <form>
                <FormControl fullWidth>
                    <TextField
                        id="brand"
                        label="Marca"
                        value={brand}
                        select
                        variant="standard"
                        defaultValue="Toyota"
                        onChange={(b) => setBrand(b.target.value)}
                        style={{ width: "25%" }}
                    >
                        <MenuItem value={"Toyota"}>Toyota</MenuItem>
                        <MenuItem value={"Ford"}>Ford</MenuItem>
                        <MenuItem value={"Hyundai"}>Hyundai</MenuItem>
                        <MenuItem value={"Honda"}>Honda</MenuItem>
                    </TextField>
                </FormControl>

                <FormControl fullWidth>
                    <TextField
                        id="amount"
                        label="Monto"
                        type="number"
                        value={amount}
                        variant="standard"
                        onChange={(b) => setAmount(b.target.value)}
                    />
                </FormControl>

                <FormControl fullWidth>
                    <TextField
                        id="Cantidad"
                        label="Monto"
                        type="number"
                        value={amount}
                        variant="standard"
                        onChange={(b) => setAmount(b.target.value)}
                    />
                </FormControl>

                <FormControl>
                    <br />
                    <Button
                        variant="contained"
                        color="info"
                        onClick={(b) => saveBonus(b)}
                        style={{ marginLeft: "0.5rem" }}
                        startIcon={<SaveIcon />}
                    >
                        Guardar Vehículo
                    </Button>
                </FormControl>
            </form>
            <hr />
            <Link to="/bonus/list">Mostrar Bonos Disponibles</Link>
        </Box>
    );
};

export default AddBonus;