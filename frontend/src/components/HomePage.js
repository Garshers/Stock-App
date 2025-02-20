import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../static/css/homePageStyle.css';

function HomePage() {
    const [symbol, setSymbol] = useState('');
    const navigate = useNavigate();

    const handleChange = (event) => {
        setSymbol(event.target.value);
    };

    const handleSubmit = () => {
        if (symbol) {
            navigate(`/stockChart/${symbol}`);
        } else {
            alert('Please enter a company symbol.');
        }
    };

    return (
        <div className="contentBox">
            <div className='browserBox'>
                <label htmlFor="symbol">Enter company symbol:</label>
                <input type="text" id="symbol" value={symbol} onChange={handleChange} className='inputBox'/>
                <button onClick={handleSubmit} className='button'>Find</button>
            </div>
        </div>
    );
}

export default HomePage;