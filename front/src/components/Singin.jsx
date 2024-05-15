import React, { useEffect, useState } from "react";
import { Routes, Route, Link } from 'react-router-dom';
import logo from '../img/oina.png';

function Singin() {
    const [Email, setEmail] = useState('')
    const [Password, setPassword] = useState('')

    async function loginUser(event) {
        event.preventDefault()

        const response = await fetch(`${process.env.REACT_APP_USER_SERVICE_URL}/login`, {
            method: 'POST',
            headers: {
                'Content Type' : 'application/json',
            },
            body: JSON.stringify({
                Email,
                Password,
            }), 
        })

        const data = await response.json()

        if (data.user) {
            localStorage.setItem('token', data.user)
            alert('Login successful')
            window.location.href = '/Home'
            console.log({
                Email: data.get('Email'),
                Password: data.get('password'),
            });
        } else {
            alert('Please check your username and password')
        }
    }
    return (
        <div className="Singup">
            <img src={logo} alt="" />
            <form onSubmit={loginUser}>
                <div className="form-text">
                    <h4>Электронная почта</h4>
                    <input type="text" name="Email" id="Email" placeholder="Введите электронную почту" value={Email} onChange={(e) => setEmail(e.target.value)}/>
                </div>
                <div className="form-text">
                    <h4>Пароль</h4>
                    <input type="password" name="Password" id="Password" placeholder="Введите пароль" value={Password} onChange={(e) => setPassword(e.target.value)}/>
                </div>
                <br /><button type="submit" className="button" >Войти</button>
                <br />Впервые у нас? <Link to="/Singup">Зарегистрируйтесь!</Link>
            </form>
        </div>
    )
}

export default Singin;
