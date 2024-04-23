import Box from "@mui/material/Box";
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterMoment } from '@mui/x-date-pickers/AdapterMoment';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { TimePicker } from '@mui/x-date-pickers/TimePicker';

const DateTimeTest = () => {
    return (
        <Box>
            <hr />
            <hr />
            <hr />
            <LocalizationProvider dateAdapter={AdapterMoment}>
                <DatePicker label="Fecha Entrada" name="owo" />
                <TimePicker label="Hora Entrada" />
            </LocalizationProvider>
        </Box>
    );
};

export default DateTimeTest;