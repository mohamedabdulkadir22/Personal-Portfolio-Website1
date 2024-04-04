import React from 'react';

import './App.css';
import Navbar from './Navbar';
import InfoSection from './InfoSection';
import ProfilePicture from './ProfilePicture';
import ActionsSection from './ActionsSection';

function App() {
  return (
    <div className="portfolio">
      <Navbar />
      <div className="main">
        <ProfilePicture />
       <InfoSection />
       <ActionsSection />

      </div>
    </div>
  );
}

export default App;