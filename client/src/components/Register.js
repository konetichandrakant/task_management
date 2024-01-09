import React, { useState } from 'react';
import {
  Box,
  Typography,
  TextField,
  Button,
  InputLabel,
} from '@mui/material';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Register() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [passwordNotMatch, setPasswordNotMatch] = useState(null);
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    if (password !== confirmPassword) {
      return setPasswordNotMatch(true);
    }
    axios.post()
      .then((res) => {
        sessionStorage.setItem('token',res.token);
        navigate('/home');
      }).catch(() => {
        navigate('/login');
      })
  };

  return (
    <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center', height: '100vh' }}>
      <Box
        sx={{
          bgcolor: 'background.paper',
          p: 4,
          borderRadius: 1,
          boxShadow: 2,
          width: 400,
          maxWidth: '100%',
          height: '50vh'
        }}
      >
        <Typography variant="h5" sx={{ mb: 3 }}>Register</Typography>
        <InputLabel htmlFor="email">Email</InputLabel>
        <TextField
          id="email"
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          fullWidth
        />
        <InputLabel htmlFor="password">Password</InputLabel>
        <TextField
          id="password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          fullWidth
        />
        <InputLabel htmlFor="confirm-password">Confirm Password</InputLabel>
        <TextField
          id="confirm-password"
          type="password"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
          fullWidth
        />
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
          <Button onClick={handleSubmit} variant="contained" sx={{ mt: 3 }}>
            Register
          </Button>
        </div>
        {
          passwordNotMatch && (
            <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
              <span style={{ color: 'red' }}>password doesn't match</span>
            </div>
          )
        }
      </Box>
    </Box >
  );
}

export default Register;
