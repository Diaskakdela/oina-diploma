import React, { useState } from "react";
import { Link, useNavigate } from 'react-router-dom';
import logo from '../img/oina.png';


function Signup() {
    const [Email, setEmail] = useState('')
    const [Password, setPassword] = useState('')
    const [FirstName, setFirstName] = useState('')
    const [LastName, setLastName] = useState('')
    const [Phone, setPhone] = useState('')
    const navigate = useNavigate()

    async function registerUser(event) {
        event.preventDefault()

        const userData = {
            Email,
            Phone,
            Password,
            FirstName,
            LastName
        };

        if (!Email || !Password || !Phone || !FirstName || !LastName) {
            alert('Пожалуйста заполните все поля');
            return;
        } else {
            const response = await fetch(`${process.env.REACT_APP_USER_SERVICE_URL}/register`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(userData),
            });

            const data = await response.json()

            if (data.token) {
                localStorage.setItem('token', data.token)
                alert('Регистрация успешна')
                navigate('/Home')
            } else {
                alert('Something wrong')
            }
        }

    }


    return (
        <div className="Signup">
            <img src={logo} alt="" />
            <form onSubmit={registerUser}>
                <div className="form-text">
                    <h4>Имя</h4>
                    <input type="text" name="FirstName" id="FirstName" placeholder="Введите имя" minLength="3" value={FirstName} onChange={(e) => setFirstName(e.target.value)} />
                </div>
                <div className="form-text">
                    <h4>Фамилия</h4>
                    <input type="text" name="LastName" id="LastName" placeholder="Введите фамилию" minLength="3" value={LastName} onChange={(e) => setLastName(e.target.value)} />
                </div>
                <div className="form-text">
                    <h4>Номер телефона</h4>
                    <input type="text" name="Phone" id="Phone" placeholder="Введите номер телефона" minLength="12" value={Phone} onChange={(e) => setPhone(e.target.value)} />
                </div>
                <div className="form-text">
                    <h4>Электронная почта</h4>
                    <input type="text" name="Email" id="Email" placeholder="Введите электронную почту" minLength="13" value={Email} onChange={(e) => setEmail(e.target.value)} />
                </div>
                <div className="form-text">
                    <h4>Пароль</h4>
                    <input type="password" name="Password" id="Password" placeholder="Введите пароль" minLength="4" value={Password} onChange={(e) => setPassword(e.target.value)} />
                </div>
                <br /><button type="submit" className="button" >Зарегистрироваться</button>
                <br />Уже создан аккаунт? <Link to="/Signin">Войдите!</Link>
            </form>
        </div>
    )


}

export default Signup;
