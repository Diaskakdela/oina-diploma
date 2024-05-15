import React from "react"
import { Routes, Route, Link } from 'react-router-dom';

function Subscription() {
    return (
        <div>

            
            <div className="Sub-plans">
            <h1>Выберите план!</h1>
                <div className="Sub-plan">
                    <div className="Sub-text">
                    <h2>План на 1 месяц</h2>
                    <h2>Цена: 10000 тенге.</h2>
                    </div>
                    <div className="Sub-button">
                    <a class="button" id="button">10000 тенге</a>
                    </div>
                </div>
                <div className="Sub-plan">
                <div className="Sub-text">
                    <h2>План на 3 месяца</h2>
                    <h2>Цена: 24000 тенге.</h2>
                    <h2>ВЫГОДНО!</h2>
                    </div>
                    <div className="Sub-button">
                    <a class="button" id="button">8000 тенге/месяц</a>
                    <h4>24000 тенге каждые 3 месяца</h4>
                    </div>
                    
                </div>
                <div className="Sub-plan">
                <div className="Sub-text">
                    <h2>План на 12 месяцев</h2>
                    <h2>Цена: 90000 тенге.</h2>
                    <h2>ВЫГОДНО!!!</h2>
                    </div>
                    <div className="Sub-button">
                    <a class="button" id="button">7500 тенге/месяц</a>
                    <h4>90000 тенге каждые 12 месяцев</h4>
                    </div>

                </div>
            </div>
        </div>
    )
}

export default Subscription;