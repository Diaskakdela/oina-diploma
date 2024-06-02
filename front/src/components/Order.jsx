import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import X from '../img/X.png'


function Order({ toggleCart }) {
    const [cart, setCart] = useState([]);
    const navigate = useNavigate()

    useEffect(() => {
        if (localStorage.cart) {
            const cartItems = JSON.parse(localStorage.getItem('cart')) || [];
            setCart(cartItems);
        }

    }, []);

    const calculateTotal = () => {
        return cart.reduce((total, item) => total + (item.price * item.count), 0);
    };

    React.useEffect(() => {
        // Запрещаем прокрутку основной страницы при открытии корзины
        document.body.style.overflow = 'hidden';
        // Убираем стили, когда компонент Order размонтируется
        return () => {
            document.body.style.overflow = 'auto';
        };
    }, []); // Вызываем эффект только при монтировании компонента

    async function PayOrder(event) {
        event.preventDefault();

        if (localStorage.orderId) {
            try {
                const response = await fetch(`${process.env.REACT_APP_ORDER_SERVICE_URL}/order/pay`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + localStorage.token
                    },
                    body: JSON.stringify({
                        "orderId": localStorage.orderId
                    }),
                });
                const data = await response.json();
                console.log(data)

                if (data.success === true) {
                    localStorage.removeItem('orderId');
                    localStorage.removeItem('cart')
                    localStorage.order = false
                    alert("Спасибо за покупку!")
                    window.location.reload();
                }
                else if (data.message === "NOT_ENOUGH_TOKENS") {
                    alert("У вас недостаточно токенов")
                }
                else if (data.message === "NO_SUBSCRIPTION") {
                    alert("У вас нету подписки")
                    navigate('/Subscription')
                }
            } catch (error) {
                console.error('Error puy order:', error);
                alert('Произошла ошибка при оплате корзины');
            }
        }
    }


    async function CancelOrder(event) {
        event.preventDefault();

        if (localStorage.orderId) {
            try {
                const response = await fetch(`${process.env.REACT_APP_ORDER_SERVICE_URL}/order/cancel`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + localStorage.token
                    },
                    body: JSON.stringify({
                        "orderId": localStorage.orderId
                    }),
                });
                const data = await response.json();
                console.log(data)

                if (data.success === true || data.message === "Cannot cancel order because status is not PENDING") {
                    localStorage.removeItem('orderId');
                    localStorage.removeItem('cart')
                    localStorage.order = false
                    window.location.reload();
                }
            } catch (error) {
                console.error('Error cancelling order:', error);
                alert('Произошла ошибка при очистке корзины');
            }
        }
    }

    async function CancelOrderItem(orderItemId) {
        console.log(orderItemId)

        if (localStorage.orderId) {
            try {
                const response = await fetch(`${process.env.REACT_APP_ORDER_SERVICE_URL}/order-item/cancel`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + localStorage.token
                    },
                    body: JSON.stringify({
                        "orderItemId": orderItemId
                    }),
                });
                const data = await response.json();
                console.log(data)

                if (data.success === true) {
                    const updatedCart = cart.filter(item => item.orderItemId !== orderItemId);
                    localStorage.setItem('cart', JSON.stringify(updatedCart));
                    setCart(updatedCart);
                    if (updatedCart.length === 0) {
                        CancelOrder()
                    }
                    window.location.reload();
                }
            } catch (error) {
                console.error('Error cancelling order:', error);
                alert('Произошла ошибка при удалений товара из корзины');
            }
        }
    }

    return (
        <div className="order-overlay" onClick={toggleCart}>
            <div className="order" onClick={e => e.stopPropagation()}>
                <Link className='back-link' id="button" onClick={toggleCart}>🠈НАЗАД</Link>
                <div className='Order-clear'>
                    <h1>Корзина</h1>
                    <img src={X} className="crossIcon" alt=""></img>
                    <h2 onClick={CancelOrder}>Очистить корзину</h2>
                </div>
                {cart.length > 0 ? (
                    <div className="cart-items">
                        {cart.map(item => (
                            <div key={item.id} className="cart-item">
                                <img src={`${process.env.REACT_APP_TOY_SERVICE_URL}/Images/${item.imageUrl}`} alt={item.name} />
                                <div className='cart-text'>
                                    <div className="cart-item-details">
                                        <h2>{item.name}</h2>
                                        <p>{item.price} Токенов</p>
                                    </div>
                                    <div className='cart-item-count'></div>
                                    <p>Количество: {item.count}</p>
                                </div>
                            </div>
                        ))}
                    </div>
                ) : (
                    <p>Корзина пуста</p>
                )}
                {cart.length > 0 && (
                    <div className="total-sum">
                        <h2>Итого: {calculateTotal()} Токенов</h2>
                    </div>
                )}
                <div className="payment-button-container">
                    <button className="payment-button" onClick={PayOrder} >Оплатить</button>
                </div>
            </div>
        </div>
    );
}

export default Order;