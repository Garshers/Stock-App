import './App.css';
import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import CompanyList from './components/HomePage.js';
import StockChart from './components/StockChart';
import { Header } from './components/header.js';
import Footer from './components/footer.js';

function App() {
  return (
      <BrowserRouter>
          <div className="App">
            <Header />
              <Routes>
                  <Route path="/" element={<CompanyList />} />
                  <Route path="/stockChart/:symbol" element={<StockChart />} />
              </Routes>
            <Footer />
          </div>
      </BrowserRouter>
  );
}

export default App;
