import React, { useState, useEffect } from 'react';
import { communitiesAPI } from '../api/communities';
import { useAuth } from '../context/AuthContext';
import CreateCommunityForm from '../components/CreateCommunityForm.jsx';

export default function Communities() {
  const [communities, setCommunities] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [showCreateForm, setShowCreateForm] = useState(false);
  const { user, isAuthenticated } = useAuth();

  useEffect(() => {
    fetchCommunities();
  }, []);

  const fetchCommunities = async () => {
    try {
      setLoading(true);
      const data = await communitiesAPI.getAllCommunities();
      setCommunities(data);
    } catch (err) {
      setError('Failed to fetch communities');
    } finally {
      setLoading(false);
    }
  };

  const handleCommunityCreated = () => {
    setShowCreateForm(false);
    fetchCommunities();
  };

  const handleJoinCommunity = async (communityId) => {
    if (!user) {
      alert('Please log in to join a community.');
      return;
    }

    try {
      await communitiesAPI.joinCommunity(communityId);
      alert('Successfully joined community!');
      fetchCommunities();
    } catch (err) {
      alert('Failed to join community.');
    }
  };

  if (loading) {
    return (
      <div className="content-section">
        <div className="loading-spinner">Loading communities...</div>
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
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1rem' }}>
        <h1>Communities</h1>
        {isAuthenticated && (
          <button className="odoo-primary-btn" onClick={() => setShowCreateForm(!showCreateForm)}>
            {showCreateForm ? 'Cancel' : 'Create Community'}
          </button>
        )}
      </div>
      <p>Join communities and connect with like-minded people</p>
      
      {showCreateForm && <CreateCommunityForm onCommunityCreated={handleCommunityCreated} />}
      
      <div className="communities-grid">
        {communities.length === 0 ? (
          <div className="no-communities">
            <p>No communities available at the moment.</p>
          </div>
        ) : (
          communities.map((community) => (
            <div key={community.id} className="community-card">
              <div className="community-header">
                <h3 className="community-name">{community.name}</h3>
              </div>
              
              <div className="community-body">
                <p className="community-description">{community.description}</p>
                
                <div className="community-stats">
                  <span>📅 Created: {new Date(community.createdAt).toLocaleDateString()}</span>
                </div>
              </div>
              
              <div className="community-footer">
                <button 
                  className="join-button"
                  onClick={() => handleJoinCommunity(community.id)}
                  disabled={!user}
                >
                  {user ? 'Join Community' : 'Login to Join'}
                </button>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
}