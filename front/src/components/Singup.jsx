import React, { useEffect, useState } from "react";
import { Routes, Route, Link, useNavigate } from 'react-router-dom';
import logo from '../img/oina.png';




function Singup() {
    const [Email, setEmail] = useState('')
    const [Password, setPassword] = useState('')
    const [FirstName, setFirstName] = useState('')
    const [LastName, setLastName] = useState('')
    const [Phone, setPhone] = useState('')

    async function registerUser(event) {
        event.preventDefault()
        
        const response = await fetch(`${process.env.REACT_APP_USER_SERVICE_URL}/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                Email,
                Phone,
                Password,
                FirstName,
                LastName,
            }),
        });
    }

    return (
        <div className="Singup">
            <img src={logo} alt="" />
            <form onSubmit={registerUser}>
                <div className="form-text">
                    <h4>Имя</h4>
                    <input type="text" name="FirstName" id="FirstName" placeholder="Введите имя" accept="*/*" value={FirstName} onChange={(e) => setFirstName(e.target.value)}/>
                </div>
                <div className="form-text">
                    <h4>Фамилия</h4>
                    <input type="text" name="LastName" id="LastName" placeholder="Введите фамилию" accept="*/*" value={LastName} onChange={(e) => setLastName(e.target.value)}/>
                </div>
                <div className="form-text">
                    <h4>Номер телефона</h4>
                    <input type="text" name="Phone" id="Phone" placeholder="Введите номер телефона" accept="*/*" value={Phone} onChange={(e) => setPhone(e.target.value)}/>
                </div>
                <div className="form-text">
                    <h4>Электронная почта</h4>
                    <input type="text" name="Email" id="Email" placeholder="Введите электронную почту" accept="*/*"  value={Email} onChange={(e) => setEmail(e.target.value)}/>
                </div>
                <div className="form-text">
                    <h4>Пароль</h4>
                    <input type="password" name="Password" id="Password" placeholder="Введите пароль" accept="*/*" value={Password} onChange={(e) => setPassword(e.target.value)}/>
                </div>
                <br /><button type="submit" className="button" >Зарегистрироваться</button>
                <br />Уже создан аккаунт? <Link to="/Singin">Войдите!</Link>
            </form>
        </div>
    ) 


}

export default Singup;
