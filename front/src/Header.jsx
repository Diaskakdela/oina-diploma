import React, { useState, useEffect } from "react";
import { Routes, Route, Link, useNavigate } from 'react-router-dom';
import Home from './components/Home';
import Toys from './components/Toys';
import AboutUs from './components/AboutUs';
import Signin from './components/Signin';
import Signup from './components/Signup';
import Subscription from './components/Subscription';
import AddToys from './components/AddToys';
import ChangeToys from './components/ChangeToys';
import AddCategories from './components/AddCategories';
import ToyDetails from './components/ToyDetails';
import Order from './components/Order';
import PaymentMethod from './components/PaymentMethod';
import logo from './img/oina.png';
import Cart from './img/Cart.png'

function Header() {
  const [isCartOpen, setIsCartOpen] = useState(false);
  const navigate = useNavigate()



  const checkAndClearLocalStorage = () => { 
    const clearTime = localStorage.getItem('clearTime');
    if (clearTime && new Date().getTime() > clearTime) {
      localStorage.clear();
      console.log('Local storage cleared after 30 minutes');
    }
  }

  useEffect(() => {
    checkAndClearLocalStorage();
  }, []);

  function toggleCart() {
    setIsCartOpen(!isCartOpen);
  }

  function logout() {
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
    buttonToAddToys = <li className="menu-item">
      <Link to="/AddToys">Add toys</Link>
      <div className="dropdown">
        <Link className="dropdown-item" to="/ChangeToys">Change toys</Link>
      </div>
    </li>
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
            <Link onClick={toggleCart}><img src={Cart} className="CartIcon" alt=""></img></Link>
            {buttonToLogOut}
          </ul>
        </div>
      </section >
      {isCartOpen && <Order toggleCart={toggleCart} />}
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route exact path="/Home" element={<Home />} />
        <Route exact path="/Toys" element={<Toys />} />
        <Route exact path="/toys/:id" element={<ToyDetails />} />
        <Route exact path="/Order" element={<Order />} />
        <Route exact path="/AddToys" element={<AddToys />} />
        <Route exact path="/ChangeToys" element={<ChangeToys />} />
        <Route exact path="/AddCategories" element={<AddCategories />} />
        <Route exact path="/Subscription" element={<Subscription />} />
        <Route exact path="/PaymentMethod" element={<PaymentMethod />} />
        <Route exact path="/AboutUs" element={<AboutUs />} />
        <Route exact path="/Signin" element={<Signin />} />
        <Route exact path="/Signup" element={<Signup />} />
      </Routes>
    </>
  );
}

export default Header;