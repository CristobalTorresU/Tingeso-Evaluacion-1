import './App.css'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Home from './components/Home';
import RegisterVehicle from './components/RegisterVehicle';
import NotFound from './components/NotFound';

function App() {
  return (
    <Router>
      <div className="container">
        <Routes>
          <Route path="/home" element={<Home/>} />
          <Route path="/vehicle/register" element={<RegisterVehicle/>} />
          <Route path="*" element={<NotFound/>} />
        </Routes>
      </div>
    </Router>
  );
}

export default App
