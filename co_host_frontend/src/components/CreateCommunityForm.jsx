import React, { useState } from 'react';
import { communitiesAPI } from '../api/communities';

export default function CreateCommunityForm({ onCommunityCreated }) {
  const [formData, setFormData] = useState({ name: '', description: '' });
  const [error, setError] = useState('');

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      await communitiesAPI.createCommunity(formData);
      alert('Community created successfully!');
      onCommunityCreated();
    } catch (err) {
      setError('Failed to create community. Name might already be taken.');
    }
  };

  return (
    <div className="auth-card" style={{ margin: '2rem 0' }}>
      <h2>Create a New Community</h2>
      <form onSubmit={handleSubmit} className="auth-form">
        {error && <div className="error-message">{error}</div>}
        <div className="form-group">
          <label>Community Name</label>
          <input type="text" name="name" value={formData.name} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label>Description</label>
          <textarea name="description" value={formData.description} onChange={handleChange} required />
        </div>
        <button type="submit" className="auth-button">Create Community</button>
      </form>
    </div>
  );
}