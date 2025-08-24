import React, { useState, useEffect } from 'react';
import { usersAPI } from '../api/users';

export default function Users() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      setLoading(true);
      const data = await usersAPI.getAllUsers();
      setUsers(data);
    } catch (err) {
      setError('Failed to fetch users');
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="content-section">
        <div className="loading-spinner">Loading users...</div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="content-section">
        <div className="error-message">{error}</div>
      </div>
    );
  }

  return (
    <div className="content-section">
      <h1>Users</h1>
      <p>Connect with other members of the CoHost community</p>
      
      <div className="users-grid">
        {users.length === 0 ? (
          <div className="no-users">
            <p>No users found.</p>
          </div>
        ) : (
          users.map((user) => (
            <div key={user.id} className="user-card">
              <div className="user-avatar">
                {user.firstName ? user.firstName.charAt(0).toUpperCase() : 'U'}
              </div>
              
              <div className="user-info">
                <h3 className="user-name">
                  {user.firstName} {user.lastName}
                </h3>
                <p className="user-email">{user.email}</p>
                
                <div className="user-details">
                  <span>📅 Joined: {new Date(user.createdAt).toLocaleDateString()}</span>
                </div>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
}
