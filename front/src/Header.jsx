import React from "react"
import { Routes, Route, Link } from 'react-router-dom';
import Home from './components/Home';
import Toys from './components/Toys';
import AboutUs from './components/AboutUs';
import Singin from './components/Singin';
import Singup from './components/Singup';
import Subscription from './components/Subscription';
import AddToys from './components/AddToys';
import logo from './img/oina.png';

function Header() {

  function logout(){
      localStorage.clear();
      window.location.reload();
      window.location.href = '/Home'
  }
  let buttonToLogOut;
    if (localStorage.token) {
      buttonToLogOut = <li><Link onClick={logout}>Выйти</Link></li>;
    } else {
      buttonToLogOut = <li><Link to="/Singin">Войти</Link></li>;
    }

    let buttonToAddToys;
    if (localStorage.roles === "ADMIN" || localStorage.roles === "USER and ADMIN") {
      buttonToAddToys = <li><Link to="/AddToys">Add toys</Link></li>;
    } else {
      buttonToAddToys = null;
    }
  return (
    <>
      <section id="container">
        <div className='navbar'>
          <img src={logo} className="logo" alt=""></img>
          <ul>
            <li><Link to="/Home">Главная</Link></li>
            <li><Link to="/Toys">Игрушки</Link></li>
            <li><Link to="/Subscription">Подписка</Link></li>
            <li><Link to="/AboutUs">О нас</Link></li>
            {buttonToAddToys}
          </ul>
          <ul>
          {buttonToLogOut}
          </ul>
        </div>
      </section >
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/Home" element={<Home />} />
        <Route path="/Toys" element={<Toys />} />
        <Route path="/AddToys" element={<AddToys />} />
        <Route path="/Subscription" element={<Subscription />} />
        <Route path="/AboutUs" element={<AboutUs />} />
        <Route path="/Singin" element={<Singin />} />
        <Route path="/Singup" element={<Singup />} />
      </Routes>
    </>
  );
}

export default Header;