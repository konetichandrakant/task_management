import React, { useState } from 'react';
import {
  Box,
  Typography,
  TextField,
  Button,
  InputLabel,
} from '@mui/material';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    // Handle form submission (e.g., send data to backend for authentication)
    console.log('Email:', email, 'Password:', password);
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
          height: '40vh'
        }}
      >
        <Typography variant="h5" sx={{ mb: 3 }}>Login</Typography>
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
        <Button type="submit" variant="contained" sx={{ mt: 3 }}>
          Login
        </Button>
      </Box>
    </Box >
  );
}

export default Login;
