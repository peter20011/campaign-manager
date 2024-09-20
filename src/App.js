import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Home from './components/Home';
import AddFunds from './components/AddFunds';
import Campaigns from './components/Campaigns';

function App() {
  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/funds">Add Funds</Link></li>
            <li><Link to="/campaigns">Manage Campaigns</Link></li>
          </ul>
        </nav>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/funds" element={<AddFunds />} />
          <Route path="/campaigns" element={<Campaigns />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
