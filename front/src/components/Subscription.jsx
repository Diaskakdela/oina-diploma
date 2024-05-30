import React, { useState, useEffect } from "react";
import { Link, useNavigate } from 'react-router-dom';

function Subscription() {
    const [subscription, setSubscription] = useState([]);
    const navigate = useNavigate()

    useEffect(() => {
        const fetchSubscriptionDetails = async () => {
            try {
                const response = await fetch('http://localhost:8083/subscription-types');
                const data = await response.json();
                setSubscription(data.data);
                console.log(data); // Используем данные напрямую
            } catch (error) {
                console.error('Error fetching subscription details:', error);
            }
        };

        fetchSubscriptionDetails();
    }, []);

    const BuySubscription = async (subscriptionTypeId) => {
        try {
            const response = await fetch('http://localhost:8083/subscription', {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.token
                },
                body: JSON.stringify({
                    "renterId": localStorage.renterId,
                    "subscriptionTypeId": subscriptionTypeId
                })
            });
            const data = await response.json();
            console.log(data);
            
            if (data.success === true) {
                alert("Спасибо за покупку!")
                navigate('/Home')
            }
            else if (data.data.status === "NO_USER_ACCOUNT") {
                navigate('/PaymentMethod')
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
                {subscription.map(plan => (
                    <div key={plan.id} className="Sub-plan">
                        <h2>{plan.name}</h2>
                        <h3>План на 1 месяц</h3>
                        <h3>{plan.tokensProvided} ТОКЕНОВ</h3>
                        {localStorage.token ? (
                            <Link 
                                className="button" 
                                id="button"
                                onClick={() => BuySubscription(plan.id)}
                            >
                                {plan.price} тенге
                            </Link>
                        ) : (
                            <Link className="button" id="button" to="/Signin">{plan.price} тенге</Link>
                        )}
                    </div>
                ))}
            </div>
        </div>
    )
}

export default Subscription;