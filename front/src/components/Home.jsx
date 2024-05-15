import React from "react"
import { Routes, Route, Link } from 'react-router-dom';


function Home() {
    return (
        <div className="Home">
            <div className="Hometext">
                <h2>Новый год, больше игр, меньше отходов</h2>
                <h2>Больше 1000 игрушек!</h2>
                <h5>Доступная и экологичная альтернатива покупке игрушек по цене от 10000 тенге.</h5>
                <a class="button" id="button"><Link to="/Subscription">подписаться</Link></a>
            </div>
            <div className="Homelist">
                <h2>Как работает Oina?</h2>

                <ol class="push">
                    <li>ВЫБЕРИТЕ ИГРУШКИ: <br/>Выбирайте из более чем 1000 новых игрушек!</li>
                    <li>ПОЛНАЯ ГИБКОСТЬ: <br/>Храните игрушки дома до тех пор, пока их любят!</li>
                    <li>ПОМЕНЯТЬ И ПОВТОРИТЬ: <br/>Верните любую нелюбимую игрушку и поменяйте ее на что-нибудь другое!</li>
                    <li>ИЛИ СОХРАНИТЕ ИХ НАВСЕГДА: <br/>Не волнуйтесь, если ваш ребенок влюбился в игрушку, вы можете купить ее у нас!</li>
                </ol>

            </div>
        </div>
    )
}

export default Home;