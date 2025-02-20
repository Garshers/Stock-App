import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

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
            <label htmlFor="symbol">Enter company symbol:</label>
            <input type="text" id="symbol" value={symbol} onChange={handleChange} />
            <button onClick={handleSubmit}>Find</button>
        </div>
    );
}

export default HomePage;