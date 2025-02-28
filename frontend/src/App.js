import './App.css';
import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import HomePage from './components/HomePage/HomePage.js';
import StockChart from './components/StockDashboard/StockDashboard.js';
import Footer from './components/HeaderAndFooter/footer.js';
import { Header } from './components/HeaderAndFooter/header.js';
import './components/HeaderAndFooter/headerFooter.css';

function App() {
  return (
      <BrowserRouter>
          <div className="App">
            <Header />
              <Routes>
                  <Route path="/" element={<HomePage />} />
                  <Route path="/stockDashboard/:symbol" element={<StockChart />} />
              </Routes>
            <Footer />
          </div>
      </BrowserRouter>
  );
}

export default App;
