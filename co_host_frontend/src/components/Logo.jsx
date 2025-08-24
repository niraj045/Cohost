import React from 'react';

const Logo = ({ className = "", size = "medium" }) => {
  const sizeClasses = {
    small: "h-8 w-auto",
    medium: "h-12 w-auto", 
    large: "h-16 w-auto"
  };

  return (
    <div className={`logo-container ${className}`}>
      <img 
        src="/logo.png"
        alt="CoHost - Event Management Platform" 
        className={`logo ${sizeClasses[size]}`}
        onError={(e) => {
          // Fallback to text logo if image fails to load
          e.target.style.display = 'none';
          e.target.nextSibling.style.display = 'block';
        }}
      />
      <div className="text-logo" style={{display: 'none'}}>
        <span className="logo-text">CoHost</span>
      </div>
    </div>
  );
};

export default Logo;