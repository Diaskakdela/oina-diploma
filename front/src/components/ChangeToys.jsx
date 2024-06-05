import React, { useState, useEffect } from "react";
import { Link, useNavigate } from 'react-router-dom';

function ChangeToys() {
    const [Name, setName] = useState('');
    const [Description, setDescription] = useState('');
    const [AgeRange, setAgeRange] = useState('');
    const [CategoryId, setCategoryId] = useState('');
    const [id, setId] = useState('');
    const [Price, setPrice] = useState('');
    const [categories, setCategories] = useState([]);
    const [toys, setToys] = useState([]);
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

    useEffect(() => {
        async function fetchToys() {
            try {
                const response = await fetch(`${process.env.REACT_APP_TOY_SERVICE_URL}/toys`, {
                    method: 'GET',
                    headers: {
                        'Authorization': 'Bearer ' + localStorage.token
                    },
                });
                const data = await response.json();
                setToys(data);
            } catch (error) {
                console.error('Error fetching toys:', error);
            }
        }
        fetchToys();
    }, []);

    // Функция для обработки выбора категории
    function handleCategoryChange(event) {
        setCategoryId(event.target.value);
    }

    function handleToyChange(event) {
        setId(event.target.value);
    }

    async function ChangeToy(event) {
        event.preventDefault();

        const toyData = {};
        if (Name) toyData.Name = Name;
        if (Description) toyData.Description = Description;
        if (AgeRange) toyData.AgeRange = AgeRange;
        if (CategoryId) toyData.CategoryId = CategoryId;
        if (Price) toyData.Price = Price;

        console.log(JSON.stringify(toyData))

        try {
            const response = await fetch(`${process.env.REACT_APP_TOY_SERVICE_URL}/toys/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.token
                },
                body: JSON.stringify(toyData),
            });
            const data = await response
            console.log(data)

            if (response.ok) {
                alert('Игрушка успешно изменена');
                window.location.reload();
            } else {
                alert('Ошибка при изменении игрушки');
            }
        } catch (error) {
            console.error('Error adding toy:', error);
            alert('Произошла ошибка при отправке запроса');
        }
    }

    return (
        <div className="Signup">
            <h1>Изменение игрушек</h1>
            <form onSubmit={ChangeToy}>
                <div className="form-text">
                    <h4>Название игрушки которое хотите изменить</h4>
                    <select value={id} onChange={handleToyChange}>
                        <option key="" value="" >Выберите игрушку</option>
                        {toys.map(toy => (
                            <option name="id" id="id" key={toy.id} value={toy.id}>{toy.name}</option>
                        ))}
                    </select>
                </div>
                <div className="form-text">
                    <h4>Новое название игрушки</h4>
                    <input type="string" name="Name" id="Name" placeholder="Введите название игрушки" minLength="10" value={Name} onChange={(e) => setName(e.target.value)} />
                </div>
                <div className="form-text">
                    <h4>Описание</h4>
                    <input type="string" name="Description" id="Description" placeholder="Введите описание игрушки" minLength="5" value={Description} onChange={(e) => setDescription(e.target.value)} />
                </div>
                <div className="form-text">
                    <h4>Возрастная категория</h4>
                    <input type="string" name="AgeRange" id="AgeRange" placeholder="Введите возрастную категорию игрушки" minLength="1" value={AgeRange} onChange={(e) => setAgeRange(e.target.value)} />
                </div>
                <div className="form-text">
                    <h4>Категория</h4>
                    <select value={CategoryId} onChange={handleCategoryChange}>
                        <option key="" value="" >Выберите категорию</option>
                        {categories.map(category => (
                            <option name="CategoryName" id="CategoryName" key={category.id} value={category.id}>{category.categoryName}</option>
                        ))}
                    </select>
                </div>
                <div className="form-text">
                    <h4>Цена</h4>
                    <input type="decimal" name="Price" id="Price" placeholder="Введите цену игрушки" value={Price} onChange={(e) => setPrice(e.target.value)} />
                </div>
                <br /><button type="submit" className="button" >Изменить игрушку</button>
                <br /> <Link to="/AddCategories">Добавить категории</Link>
            </form>
        </div>
    );
}

export default ChangeToys;


