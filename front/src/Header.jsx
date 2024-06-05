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
    if (localStorage.token && localStorage.role !== "ADMIN") fetchTokens();
  },);

  const fetchTokens = async () => {
    try {
      const response = await fetch(`http://localhost:8083/oina-tokens/${localStorage.renterId}`, {
        method: 'GET',
        headers: {
          'Authorization': 'Bearer ' + localStorage.token
        },
      });
      const data = await response.json();
      console.log(data)
      if (data.success === true) {
        localStorage.removeItem(tokens);
        localStorage.setItem('tokens', data.data.tokens)
      }
    } catch (error) {
      console.error('Error fetching tokens:', error);
      return null;
    }
  };

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
      <Link to="/AddToys">Добавить игрушку</Link>
      <div className="dropdown">
        <Link className="dropdown-item" to="/ChangeToys">Изменить игрушкуs</Link>
      </div>
    </li>
  } else {
    buttonToAddToys = null;
  }

  let tokens
  if (localStorage.tokens) {
    tokens = <li className="token-display">
      <span className="blue-circle">&nbsp;т&nbsp;</span>
      <span className="token-count">{localStorage.tokens}</span>
    </li>
  }
  else {
    tokens = null
  }

  let CartIcon
  if (localStorage.role === "ADMIN") {
    CartIcon = null
  }
  else {
    CartIcon = <Link onClick={toggleCart}><img src={Cart} className="CartIcon" alt=""></img></Link>
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
            {CartIcon}
            {tokens}
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