import React from "react"
import { Routes, Route, Link } from 'react-router-dom';
import Home from './components/Home';
import Toys from './components/Toys';
import AboutUs from './components/AboutUs';
import Singin from './components/Singin';
import Singup from './components/Singup';
import Subscription from './components/Subscription';
import logo from './img/oina.png';

function Header() {
  const isAuth = false
  let button;
    if (isAuth) {
      button = <li><Link to="/Singin">Выйти</Link></li>;
    } else {
      button = <li><Link to="/Singin">Войти</Link></li>;
    }
  return (
    <>
      <section id="container">
        <div className='navbar'>
          <img src={logo} className="logo"></img>
          <ul>
            <li><Link to="/Home">Главная</Link></li>
            <li><Link to="/Toys">Игрушки</Link></li>
            <li><Link to="/Subscription">Подписка</Link></li>
            <li><Link to="/AboutUs">О нас</Link></li>
          </ul>
          <ul>
          {button}
          </ul>
        </div>
      </section >
      <Routes>
        <Route path="/Home" element={<Home />} />
        <Route path="/Toys" element={<Toys />} />
        <Route path="/Subscription" element={<Subscription />} />
        <Route path="/AboutUs" element={<AboutUs />} />
        <Route path="/Singin" element={<Singin />} />
        <Route path="/Singup" element={<Singup />} />
      </Routes>
    </>
  );
}

export default Header;