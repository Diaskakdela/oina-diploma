import React, { useState } from "react";
import { Link, useNavigate } from 'react-router-dom';
import logo from '../img/oina.png';

function Signin() {
    const [Email, setEmail] = useState('')
    const [Password, setPassword] = useState('')
    const navigate = useNavigate()

    function setClearTime() {
        const clearTime = new Date().getTime() + 30 * 60000; // Текущее время + 30 минут в миллисекундах
        localStorage.setItem('clearTime', clearTime);
    }

    const fetchToyDetails = async (toyId) => {
        try {
            const response = await fetch(`${process.env.REACT_APP_TOY_SERVICE_URL}/toys/${toyId}`, {
                method: 'GET',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.token
                },
            });
            const data = await response.json();
            return data;
        } catch (error) {
            console.error('Error fetching toy details:', error);
            return null;
        }
    };

    const fetchTokens = async () => {
        try {
            const response = await fetch(`http://localhost:8083/oina-tokens/%7B${localStorage.renterId}%7D`, {
                method: 'GET',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.token
                },
            });
            const data = await response.json();
            if (data.success === true) {
                localStorage.setItem('tokens', data.data.tokens)
            }
        } catch (error) {
            console.error('Error fetching tokens:', error);
            return null;
        }
    };

    const fetchOrder = async () => {
        try {
            const response = await fetch(`${process.env.REACT_APP_ORDER_SERVICE_URL}/order?renterId=${localStorage.renterId}`, {
                method: 'GET',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.token
                },
            });
            const data = await response.json();
            console.log(data)
            if (data.success === true) {
                const cart = [];

                for (const item of data.data.orderItems) {
                    const toyDetails = await fetchToyDetails(item.toyId);
                    if (toyDetails) {
                        // Проверяем, есть ли уже элемент с таким toyId в корзине
                        const existingIndex = cart.findIndex(cartItem => cartItem.id === item.toyId);
                        if (existingIndex !== -1) {
                            // Если элемент уже есть, увеличиваем его count
                            cart[existingIndex].count += 1;
                        } else {
                            // Если элемента нет, добавляем новый элемент в корзину
                            cart.push({ ...toyDetails, count: 1, orderItemId: item.id });
                        }
                    }
                }

                // Сохраняем корзину в localStorage
                localStorage.setItem('cart', JSON.stringify(cart));
                console.log('Cart saved to localStorage:', cart);
                localStorage.setItem('order', data.success)
                localStorage.setItem('orderId', data.data.order.id)
            }
            else {
                localStorage.setItem('order', data.success)
            }
        } catch (error) {
            console.error('Error fetching order:', error);
        }
    };

    async function loginUser(event) {
        event.preventDefault()

        const userData = {
            Email,
            Password
        };

        if (!Email || !Password) {
            alert('Пожалуйста заполните все поля');
            return;
        } else {
            const response = await fetch(`${process.env.REACT_APP_USER_SERVICE_URL}/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(userData),
            });
            const data = await response.json()

            if (data.token) {
                localStorage.setItem('token', data.token)
                localStorage.setItem('role', data.roles)
                localStorage.setItem('renterId', data.renterId)
                fetchOrder();
                setClearTime();
                alert('Авторизация успешна')
                navigate('/Home')
            }
            else {
                alert('Please check your username and password')
            }
        }
    }
    return (
        <div className="Signup">
            <img src={logo} alt="" />
            <form onSubmit={loginUser}>
                <div className="form-text">
                    <h4>Электронная почта</h4>
                    <input type="text" name="Email" id="Email" placeholder="Введите электронную почту" minLength="13" value={Email} onChange={(e) => setEmail(e.target.value)} />
                </div>
                <div className="form-text">
                    <h4>Пароль</h4>
                    <input type="password" name="Password" id="Password" placeholder="Введите пароль" minLength="4" value={Password} onChange={(e) => setPassword(e.target.value)} />
                </div>
                <br /><button type="submit" className="button" >Войти</button>
                <br />Впервые у нас? <Link to="/Signup">Зарегистрируйтесь!</Link>
            </form>
        </div>
    )
}

export default Signin;
