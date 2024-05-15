import React, { useState, useEffect } from "react";

function Toys() {
    const [toys, setToys] = useState([]);
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('');

    useEffect(() => {
        fetchToys();
        fetchCategories();
    }, []);

    const fetchToys = async () => {
        try {
            const response = await fetch('http://localhost:8084/toys');
            const data = await response.json();
            setToys(data);
            console.log(data)
        } catch (error) {
            console.error('Error fetching toys:', error);
        }
    };

    const fetchCategories = async () => {
        try {
            const response = await fetch('http://localhost:8084/category');
            const data = await response.json();
            setCategories(data);
        } catch (error) {
            console.error('Error fetching categories:', error);
        }
    };

    const handleCategoryChange = (event) => {
        setSelectedCategory(event.target.value);
    };

    const filteredToys = selectedCategory
        ? toys.filter(toy => toy.categoryId === selectedCategory)
        : toys;

    return (
        <div className="toys-catalog">
            <h1>Каталог игрушек</h1>
            <div className="content">
            <div className="category">
                <select value={selectedCategory} onChange={handleCategoryChange}>
                    <option key="" value="" >Все категории</option>
                    {categories.map(category => (
                        <option name="CategoryName" id="CategoryName" key={category.id} value={category.id}>{category.categoryName}</option>
                    ))}
                </select>
            </div>
            <div className="toys-list">
                {filteredToys.map(toy => (
                    <div key={toy.id} className="toy-item">
                        <h2>{toy.name}</h2>
                        <p>{toy.description}</p>
                        <p>{toy.ageRange}</p>
                        <p>{toy.price}</p>
                    </div>
                ))}
            </div>
            </div>
        </div>
    );
}

export default Toys;