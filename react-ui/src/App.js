import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import DisplayProducts from './components/DisplayProducts';
import DisplayOrders from './components/DisplayOrders';
import DisplayInventories from  './components/DisplayInventories'
import Home from './components/Home';

import 'bootstrap/dist/css/bootstrap.min.css';

const App = () => {
    return (
        <Router>
            <div className="container">
                <Routes>
                    <Route path="/fetchProducts" element={<DisplayProducts />} />
                    <Route path="/fetchOrders" element={<DisplayOrders />} />
                    <Route path="/fetchInventories" element={<DisplayInventories />} />

                    <Route path="/" element={<Home />} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;
