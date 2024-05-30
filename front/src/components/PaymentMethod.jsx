import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';

function PaymentMethod() {
    const [account, setAccount] = useState('')
    const [fullName, setFullName] = useState('')
    const [expirationDate, setExpirationDate] = useState('')
    const [cvvCode, setCvvCode] = useState('')
    const navigate = useNavigate()

    const handleChange = (e) => {
        let input = e.target.value.replace(/\D/g, ""); // Удаляем все нечисловые символы
        let formattedInput = "";

        for (let i = 0; i < input.length; i++) {
            if (i > 0 && i % 2 === 0) {
                formattedInput += "/";
            }
            formattedInput += input[i];
        }

        setExpirationDate(formattedInput);
    };

    async function AddPaymentMethod(event) {
        event.preventDefault()

        const userData = {
            "renterId": localStorage.renterId,
            account,
            fullName,
            expirationDate,
            cvvCode
        };

        if (!account || !fullName || !expirationDate || !cvvCode) {
            alert('Пожалуйста заполните все поля');
            return;
        } else {
            try {
                const response = await fetch(`http://localhost:8083/user-account`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(userData),
                });
                const data = await response.json()

                if (data) {
                    alert('Операция прошла успешно')
                    navigate('/Home')
                }
            } catch (error) {
                console.error('Error fetching payment details:', error);
            }

        }
    }

    return (
        <div className="Payment-content">
            <div className="Payment-content-head">
                <h1>Детали оплаты</h1>
                <h3>Осталось совсем немного. Всего лишь уточнить платежные реквизиты, и вы сможете сделать свой первый заказ уже сегодня. (Первая доставка бесплатно!)</h3>
            </div>
            <div className="line"></div>
            <form onSubmit={AddPaymentMethod}>
                <div className="Payment-details">
                    <div className="form-details">
                        <h4>ФИО*</h4>
                        <input type="text" name="fullName" id="fullName" placeholder="Введите ФИО" minLength="13" value={fullName} onChange={(e) => setFullName(e.target.value)} />
                    </div>
                    <div className="form-details">
                        <h4>Номер карты*</h4>
                        <input type="text" name="account" id="account" placeholder="Введите номер карты" minLength="18" maxLength="18" value={account} onChange={(e) => setAccount(e.target.value)} />
                    </div>
                    <div className="Payment-form">
                        <div className="form-details">
                            <h4>Срок карты*</h4>
                            <input type="text" name="expirationDate" id="expirationDate" placeholder="ММ/ГГ" minLength="5" maxLength="5" value={expirationDate} onChange={handleChange} />
                        </div>
                        <div className="form-details">
                            <h4>CVV код*</h4>
                            <input type="text" name="cvvCode" id="cvvCode" placeholder="Введите CVV код" minLength="3" maxLength="3" value={cvvCode} onChange={(e) => setCvvCode(e.target.value)} />
                        </div>
                    </div>
                    <button type="submit" className="button">отправить</button>
                </div>
                
            </form>
        </div>
    )
}

export default PaymentMethod;