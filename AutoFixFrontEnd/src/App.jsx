import './App.css'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Home from './components/Home';
import Navbar from './components/Navbar';
import RegisterVehicle from './components/RegisterVehicle';
import VehicleLista from './components/VehicleLista';
import NotFound from './components/NotFound';
import Testing from './components/Testing';

function App() {
  return (
    <Router>
      <div className="container">
        <Navbar></Navbar>
        <Routes>
          <Route path="/home" element={<Home/>} />
          <Route path="/testing" element={<Testing/>} />
          <Route path="/vehicle/list" element={<VehicleLista/>} />
          <Route path="/vehicle/add" element={<RegisterVehicle/>} />
          <Route path="*" element={<NotFound/>} />
        </Routes>
      </div>
    </Router>
  );
}

export default App
