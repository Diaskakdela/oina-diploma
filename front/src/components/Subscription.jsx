import React, { useState, useEffect } from "react";
import { Link } from 'react-router-dom';


function Subscription() {
    const [subscription, setSubscription] = useState([]);


    let button
    if (localStorage.token) {
        button = <Link className="button" id="button" to="/PaymentMethod">9990 тенге</Link>
    }
    else {
        button = <Link className="button" id="button" to="/Signin">9990 тенге</Link>
    }

    useEffect(() => {
        const fetchSubscriptionDetails = async () => {
            try {
                const response = await fetch('http://localhost:8083/subscription-types');
                const data = await response.json();
                setSubscription(data);
                console.log(data); // Используем данные напрямую
            } catch (error) {
                console.error('Error fetching subscription details:', error);
            }
        };

        fetchSubscriptionDetails();
    }, []);

    const BuySubscription = async () => {
        try {
            const response = await fetch('http://localhost:8083/subscription', {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.token
                },
                body: JSON.stringify({
                    "renterId": localStorage.renterId,
                    "subscriptionTypeId": ""
                })
            });
            const data = await response.json();
            console.log(data);
            
            if (data.isSuccess === true) {
                alert("Спасибо за покупку!")
                
            }
        } catch (error) {
            console.error('Error fetching subscription details:', error);
        }
    };



    return (
        <div className="Subscription">
            <h1>Выберите План!</h1>
            <h3>Готовы начать аренду? Выберите план и сделайте свой первый заказ сегодня. (Первая доставка бесплатно!)</h3>
            <div className="line"></div>
            <div className="Sub-plans">
                <div className="Sub-plan">
                    <h2>GOLD</h2>
                    <h3>План на 1 месяц</h3>
                    <h3>500 ТОКЕНОВ</h3>
                    {button}
                </div>
                <div className="Sub-plan">
                    <h2>PLATINUM</h2>
                    <h3>План на 1 месяц</h3>
                    <h3>800 ТОКЕНОВ</h3>
                    {button}
                </div>
                <div className="Sub-plan">
                    <h2>DIAMOND</h2>
                    <h3>План на 1 месяц</h3>
                    <h3>1200 ТОКЕНОВ</h3>
                    {button}
                </div>
            </div>
        </div>
    )
}

export default Subscription;