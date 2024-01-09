import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import TaskManager from './components/TaskManager';
// Import other pages as needed

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<TaskManager />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;