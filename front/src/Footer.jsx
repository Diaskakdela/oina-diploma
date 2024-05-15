import React from "react";
import { Link } from 'react-router-dom';
import fb from './img/facebook.png';
import twitter from './img/twitter.png';
import insta from './img/insta.png';
import youtube from './img/youtube.png';


function Footer() {
    return (
        <>
        <div className="footer">
            <div className="sb__footer section__padding">
                <div className="sb__footer-links">
                    <div className="sb__footer-links_div">
                        <h4>Время работы</h4>
                        <h4>Пн-Пт <br /> с 9:00 до 18:00. Сб, Вс <br /> выходной</h4>
                    </div>
                    <div className="sb__footer-links_div">
                        <h4>Телефон</h4>
                        <h4>+7 777 585 52 58</h4>
                    </div>
                    <div className="sb__footer-links_div">
                        <h4>Пункт выдачи</h4>
                        <h4>ул. Сатпаева 22, Алматы 050000</h4>
                    </div>
                    <div className="sb__footer-links_div">
                        <h4>Каталог</h4>
                        <Link to="/Toys"><p>Игрушки</p></Link>
                        <Link to="/AboutUs"><p>О нас</p></Link>
                    </div>
                    <div className="sb__footer-links_div">
                        <h4>Соцсети</h4>
                        <div className="socialmedia">
                            <p><img src={fb} alt="" width="40px" height="40px"/></p>
                            <p><img src={twitter} alt="" width="49px" height="41px"/></p>
                            <p><img src={youtube} alt="" width="40px" height="40px"/></p>
                            <p><img src={insta} alt="" width="50px" height="50px"/></p>
                        </div>
                    </div>
                </div>
                <hr></hr>
                <div className="sb__footer-below">
                    <div className="sb__footer-copyright">
                        <p>
                            @{new Date().getFullYear()} Oina, Все права защищены.
                        </p>
                    </div>
                    {/* <div className="sb__footer-below-links">
                        <a href="#"><p>Tearms & Conditions</p></a>
                        <a href="#"><p>Privacy</p></a>
                        <a href="#"><p>Security</p></a>
                        <a href="#"><p>Cookie Declaration</p></a>
                    </div>  */}
                </div>
            </div>
        </div>
      </>
    )
}

export default Footer;