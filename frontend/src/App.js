import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import CompanyList from './components/CompanyList';
import StockChart from './components/StockChart';

function App() {
  return (
      <BrowserRouter>
          <div className="App">
              <h1>Stock App</h1>
              <Routes>
                  <Route path="/" element={<CompanyList />} />
                  <Route path="/stockChart/:symbol" element={<StockChart />} />
              </Routes>
          </div>
      </BrowserRouter>
  );
}

export default App;
