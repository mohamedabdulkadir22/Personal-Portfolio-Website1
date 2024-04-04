import React from 'react';

function Navbar() {
  return (
    <nav>
      <div className="navbar-brand"><span>D</span>esign <span>with M</span>ohamed</div>
      <div className="navbar-nav">
        <ul>
          <li><a href="#">projects</a></li>
          <li><a href="#">About us</a></li>
          <li><a href="#">FAQ</a></li>
          <li><a href="#">Contact me</a></li>
        </ul>
      </div>
    </nav>
  );
}

export default Navbar;