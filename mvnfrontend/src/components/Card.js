import React from 'react';
import { Link } from 'react-router-dom';

function Card({ image, title, description, link }) {
  return (
    <Link to={link || '#'} className="card-link" style={{ textDecoration: 'none', color: 'inherit' }}>
      <div className="card">
        <img src={image} alt={title} className="card-image" />
        <div className="card-content">
          <h3>{title}</h3>
          <p>{description}</p>
        </div>
      </div>
    </Link>
  );
}

export default Card;

