import React from "react"
import { Routes, Route, Link, useNavigate } from 'react-router-dom';
import Home from './components/Home';
import Toys from './components/Toys';
import AboutUs from './components/AboutUs';
import Signin from './components/Signin';
import Signup from './components/Signup';
import Subscription from './components/Subscription';
import AddToys from './components/AddToys';
import AddCategories from './components/AddCategories';
import logo from './img/oina.png';

function Header() {
  const navigate = useNavigate()

  function logout(){
      localStorage.clear();
      window.location.reload();
      navigate('/Home')
  }
  let buttonToLogOut;
    if (localStorage.token) {
      buttonToLogOut = <li><Link onClick={logout}>Выйти</Link></li>;
    } else {
      buttonToLogOut = <li><Link to="/Signin">Войти</Link></li>;
    }

    let buttonToAddToys;
    if (localStorage.role === "ADMIN" || localStorage.role === "USER and ADMIN") {
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
        <Route exact path="/" element={<Home />} />
        <Route exact path="/Home" element={<Home />} />
        <Route exact path="/Toys" element={<Toys />} />
        <Route exact path="/AddToys" element={<AddToys />} />
        <Route exact path="/AddCategories" element={<AddCategories />} />
        <Route exact path="/Subscription" element={<Subscription />} />
        <Route exact path="/AboutUs" element={<AboutUs />} />
        <Route exact path="/Signin" element={<Signin />} />
        <Route exact path="/Signup" element={<Signup />} />
      </Routes>
    </>
  );
}

export default Header;