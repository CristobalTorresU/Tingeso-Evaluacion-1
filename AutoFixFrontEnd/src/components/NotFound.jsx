import surprised from '../../images/surprised.png';
import '../App.css';

const NotFound = () => {
    return (
        <div>
            <img src={surprised} alt="Sorprendida" className="rotating-image" />
            <h1>La dirección URL a la que ingresó no existe.</h1>
        </div>
    );
}

export default NotFound;