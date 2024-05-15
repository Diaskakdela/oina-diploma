import React, { useState, useEffect } from "react";
import { Link, useNavigate } from 'react-router-dom';

function AddToys() {
    const [Name, setName] = useState('');
    const [Description, setDescription] = useState('');
    const [AgeRange, setAgeRange] = useState('');
    const [CategoryId, setCategoryId] = useState('');
    const [Price, setPrice] = useState('');
    const [ImageUrl, setImageUrl] = useState('');
    const [categories, setCategories] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const isAdmin = localStorage.role === 'ADMIN' || localStorage.role === 'USER and ADMIN';

        if (!isAdmin) {
            alert('У вас нету прав администратора');
            navigate('/Home');
        }
    }, [navigate]);

    // Функция для загрузки категорий из API
    useEffect(() => {
        async function fetchCategories() {
            try {
                const response = await fetch(`${process.env.REACT_APP_TOY_SERVICE_URL}/category`, {
                    method: 'GET',
                    headers: {
                        'Authorization': 'Bearer ' + localStorage.token
                    },
                });
                const data = await response.json();
                setCategories(data);
            } catch (error) {
                console.error('Error fetching categories:', error);
            }
        }
        fetchCategories();
    }, []);

    // Функция для обработки выбора категории
    function handleCategoryChange(event) {
        setCategoryId(event.target.value);
    }

    async function AddToy(event) {
        event.preventDefault();

        const toyData = {
            Name,
            Description,
            AgeRange,
            CategoryId,
            Price,
            ImageUrl,
        };

        if (!Name || !Description || !AgeRange || !CategoryId || !Price || !ImageUrl) {
            alert('Пожалуйста, заполните все поля');
            return;
        } else {
            try {
                const response = await fetch(`${process.env.REACT_APP_TOY_SERVICE_URL}/toys`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + localStorage.token
                    },
                    body: JSON.stringify(toyData),
                });
                const data = await response.json();

                if (data) {
                    alert('Игрушка успешно добавлена')
                } 
            } catch (error) {
                console.error('Error adding toy:', error);
                alert('Ошибка при добавлении игрушки');
            }
        }
    }

    return (
        <div className="Signup">
            <h1>Добавление игрушек</h1>
            <form onSubmit={AddToy}>
                <div className="form-text">
                    <h4>Название игрушки</h4>
                    <input type="text" name="Name" id="Name" placeholder="Введите название игрушки" minLength="3" value={Name} onChange={(e) => setName(e.target.value)} />
                </div>
                <div className="form-text">
                    <h4>Описание</h4>
                    <input type="text" name="Description" id="Description" placeholder="Введите описание игрушки" minLength="10" value={Description} onChange={(e) => setDescription(e.target.value)} />
                </div>
                <div className="form-text">
                    <h4>Возрастная категория</h4>
                    <input type="text" name="AgeRange" id="AgeRange" placeholder="Введите возрастную категорию игрушки" minLength="2" value={AgeRange} onChange={(e) => setAgeRange(e.target.value)} />
                </div>
                <div className="form-text">
                    <h4>Категория</h4>
                    <select  value={CategoryId} onChange={handleCategoryChange}>
                        <option key="" value="" >Выберите категорию</option>
                        {categories.map(category => (
                            <option name="CategoryName" id="CategoryName" key={category.id} value={category.id}>{category.categoryName}</option>
                        ))}
                    </select>
                </div>
                <div className="form-text">
                    <h4>Цена</h4>
                    <input type="text" name="Price" id="Price" placeholder="Введите цену игрушки" value={Price} onChange={(e) => setPrice(e.target.value)} />
                </div>
                <div className="form-text">
                    <h4>URL картинки</h4>
                    <input type="text" name="ImageUrl" id="ImageUrl" placeholder="Введите URL картинки" minLength="10" value={ImageUrl} onChange={(e) => setImageUrl(e.target.value)} />
                </div>
                <br /><button type="submit" className="button" >Добавить игрушку</button>
                <br /> <Link to="/AddCategories">Добавить категории</Link>
            </form>
        </div>
    );
}

export default AddToys;