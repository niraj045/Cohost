import React from 'react';
import { Link } from 'react-router-dom';

export default function Home() {
  return (
    <div>
      {/* The <header> section has been removed from here */}

      {/* Hero Section */}
      <div className="odoo-hero-section">
        <div className="odoo-hero-content-with-image">
          <div className="odoo-hero-text">
            <h1 className="odoo-hero-title">
              Your <span className="odoo-professional">professional</span><br />
              <span className="odoo-highlight">event management</span> platform
            </h1>
            <p className="odoo-hero-subtitle">
              CoHost is changing how people think about event organization. Thanks to its 
              user-friendly and intuitive interface, you can create, manage, and customize your 
              events effortlessly.
            </p>
            <div className="odoo-hero-buttons">
              <Link to="/events" className="odoo-primary-btn">
                Start now - It's free
              </Link>
              <button className="odoo-secondary-btn">
                Meet an advisor <span className="odoo-dropdown">▾</span>
              </button>
            </div>
          </div>
          
          <div className="odoo-hero-image">
            <div className="modern-venue-container">
              <img 
                src="https://images.unsplash.com/photo-1511578314322-379afb476865?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" 
                alt="Modern conference room with professional setup" 
                className="venue-image"
              />
              <div className="image-overlay">
                <div className="overlay-badge">
                  <span className="badge-icon">🏢</span>
                  <span className="badge-text">Professional Venues</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Features Section */}
      <div className="odoo-features-section">
        <div className="odoo-container">
          <h2 className="odoo-section-title">Why Choose CoHost?</h2>
          <div className="odoo-features-grid">
            <div className="odoo-feature-card">
              <div className="odoo-feature-icon">📅</div>
              <h3 className="odoo-feature-title">Smart Event Planning</h3>
              <p className="odoo-feature-description">
                Create and manage events with our intelligent planning tools. Set dates, manage venues, and track attendance seamlessly.
              </p>
            </div>

            <div className="odoo-feature-card">
              <div className="odoo-feature-icon">👥</div>
              <h3 className="odoo-feature-title">Community Building</h3>
              <p className="odoo-feature-description">
                Build lasting connections through communities. Engage with like-minded people and grow your network organically.
              </p>
            </div>

            <div className="odoo-feature-card">
              <div className="odoo-feature-icon">✅</div>
              <h3 className="odoo-feature-title">Effortless Experience</h3>
              <p className="odoo-feature-description">
                Enjoy a smooth, professional interface designed for both organizers and attendees. Everything you need in one place.
              </p>
            </div>
          </div>
        </div>
      </div>

      {/* Stats Section */}
      <div className="odoo-stats-section">
        <div className="odoo-container">
          <div className="odoo-stats-grid">
            <div className="odoo-stat-item">
              <div className="odoo-stat-number">10K+</div>
              <div className="odoo-stat-label">Events Created</div>
            </div>
            <div className="odoo-stat-item">
              <div className="odoo-stat-number">50K+</div>
              <div className="odoo-stat-label">Active Users</div>
            </div>
            <div className="odoo-stat-item">
              <div className="odoo-stat-number">500+</div>
              <div className="odoo-stat-label">Communities</div>
            </div>
            <div className="odoo-stat-item">
              <div className="odoo-stat-number">99.9%</div>
              <div className="odoo-stat-label">Uptime</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}