import React, { useState } from 'react';
import { 
  AppBar, 
  Toolbar, 
  Typography, 
  Button, 
  Box, 
  Avatar,
  Menu,
  MenuItem,
  Tooltip,
  IconButton
} from '@mui/material';
import { Link as RouterLink, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function NavBar() {
  const { user, isAuthenticated, logout } = useAuth();
  const navigate = useNavigate();
  
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);

  const handleMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleLogout = async () => {
    handleClose();
    await logout();
    navigate('/');
  };

  return (
    <AppBar 
      position="sticky" 
      elevation={1} // Adds a subtle shadow
      sx={{
        // FIXED: A permanent, solid background style.
        background: '#ffffff',
        color: '#2c2c54', // Dark text for contrast
      }}
    >
      <Toolbar sx={{ 
        justifyContent: 'space-between', 
        maxWidth: '1280px', 
        width: '100%',
        margin: '0 auto',
        padding: { xs: '0 1rem', md: '0 2rem' }
      }}>
        {/* Logo */}
        <Typography 
          variant="h5" 
          component={RouterLink} 
          to="/"
          className="logo-text"
          sx={{ 
            color: '#714b67', // Permanent logo color
            textDecoration: 'none',
          }}
        >
          CoHost
        </Typography>
        
        {/* Navigation Links */}
        <Box sx={{ display: { xs: 'none', md: 'flex' }, alignItems: 'center', gap: 2 }}>
          <Button component={RouterLink} to="/events" color="inherit">Events</Button>
          <Button component={RouterLink} to="/communities" color="inherit">Communities</Button>
          <Button component={RouterLink} to="/users" color="inherit">Users</Button>
        </Box>

        {/* Auth Actions */}
        <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
          {isAuthenticated && user ? (
            <>
              <Tooltip title="Account actions">
                <IconButton onClick={handleMenu} size="small">
                  <Avatar sx={{ width: 36, height: 36, bgcolor: '#714b67' }}>
                    {user.firstName?.charAt(0).toUpperCase()}
                  </Avatar>
                </IconButton>
              </Tooltip>
              <Menu
                anchorEl={anchorEl}
                open={open}
                onClose={handleClose}
                transformOrigin={{ horizontal: 'right', vertical: 'top' }}
                anchorOrigin={{ horizontal: 'right', vertical: 'bottom' }}
              >
                <MenuItem disabled>Hello, {user.firstName}!</MenuItem>
                <MenuItem onClick={handleLogout}>Logout</MenuItem>
              </Menu>
            </>
          ) : (
            <>
              <Button component={RouterLink} to="/login" color="inherit">
                Login
              </Button>
              <Button 
                component={RouterLink} 
                to="/register"
                variant="outlined"
                color="inherit"
                sx={{
                  borderRadius: '20px',
                  borderColor: '#714b67',
                  '&:hover': {
                    backgroundColor: 'rgba(113, 75, 103, 0.1)',
                    borderColor: '#714b67',
                  }
                }}
              >
                Register
              </Button>
            </>
          )}
        </Box>
      </Toolbar>
    </AppBar>
  );
}