import React from 'react';
import Header from './Header';
import Footer from './Footer';
import { ToastContainer } from 'react-toastify';

function App() {
  return (
      <div>
        <Header />
        {}
        <Footer />
        <ToastContainer position='bottom-left' autoClose={2000} />
      </div>
  );
}

export default App;