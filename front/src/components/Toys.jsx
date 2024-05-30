import React, { useState, useEffect } from "react";
import { Link} from 'react-router-dom';
import searchIcon from '../img/search-icon.png';

function Toys() {
    const [toys, setToys] = useState([]);
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('');
    const [searchTerm, setSearchTerm] = useState('');
    const [maxPrice, setMaxPrice] = useState(400);  
    const [maxAge, setMaxAge] = useState(8);  

    useEffect(() => {
        fetchToys();
        fetchCategories();
    }, []);

    const fetchToys = async () => {
        try {
            const response = await fetch(`${process.env.REACT_APP_TOY_SERVICE_URL}/toys`);
            const data = await response.json();
            setToys(data);
        } catch (error) {
            console.error('Error fetching toys:', error);
        }
    };

    const fetchCategories = async () => {
        try {
            const response = await fetch(`${process.env.REACT_APP_TOY_SERVICE_URL}/category`);
            const data = await response.json();
            setCategories(data);
        } catch (error) {
            console.error('Error fetching categories:', error);
        }
    };

    const handleCategoryChange = (event) => {
        setSelectedCategory(event.target.value);
    };

    const handleSearchChange = (event) => {
        setSearchTerm(event.target.value);
    };

    const handleMaxPriceChange = (event) => {
        setMaxPrice(event.target.value);
    };

    const handleMaxAgeChange = (event) => {
        setMaxAge(event.target.value);
    };

    const filteredToys = toys.filter(toy => {
        return (
            (!selectedCategory || toy.categoryId === selectedCategory) &&
            (!searchTerm || toy.name.toLowerCase().includes(searchTerm.toLowerCase())) &&
            (toy.price <= maxPrice) &&
            (toy.ageRange <= maxAge)
        );
    });

    return (
        <div className="toys-catalog">
            <div className="search-category-container">
                <div className="search">
                    <img src={searchIcon} className="searchIcon" alt=""></img>
                    <input
                        type="text"
                        placeholder="Я ищу.."
                        value={searchTerm}
                        onChange={handleSearchChange}
                    />
                </div>
                <h1>Каталог игрушек</h1>
            </div>
            <div className="line"></div>
            <div className="content">
                <div className="filters">
                    <div className="category">
                        <select value={selectedCategory} onChange={handleCategoryChange}>
                            <option key="" value="">Все категории</option>
                            {categories.map(category => (
                                <option key={category.id} value={category.id}>{category.categoryName}</option>
                            ))}
                        </select>
                    </div>
                    <div className="price-filter">
                        <h3>Цена</h3>
                        <input
                            type="range"
                            min="100"
                            max="400"
                            value={maxPrice}
                            onChange={handleMaxPriceChange}
                        />
                        <label>До {maxPrice}</label>
                    </div>
                    <div className="age-filter">
                        <h3>Возраст</h3>
                        <input
                            type="range"
                            min="3"
                            max="8"
                            value={maxAge}
                            onChange={handleMaxAgeChange}
                        />
                        <label>До {maxAge}</label>
                    </div>
                </div>
                <div className="toys-list">
                    {filteredToys.map(toy => (
                        <div key={toy.id} className="toy-item">
                           <Link to={`/toys/${toy.id}`}><img to={`/toys/${toy.id}`} src={`${process.env.REACT_APP_TOY_SERVICE_URL}/Images/${toy.imageUrl}`} alt="" /></Link>
                            <h2><Link to={`/toys/${toy.id}`} className="toy-name">{toy.name}</Link></h2>
                            <p>От {toy.ageRange} лет</p>
                            <p>{toy.price} Токенов</p>
                            <div className="button-item">
                                <Link to={`/toys/${toy.id}`} className="buybutton" id="button">добавить</Link>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default Toys;
