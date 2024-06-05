import React, { useState, useEffect } from "react";
import { Link, useNavigate } from 'react-router-dom';

function AddToys() {
    const [Name, setName] = useState('');
    const [Description, setDescription] = useState('');
    const [AgeRange, setAgeRange] = useState('');
    const [CategoryId, setCategoryId] = useState('');
    const [Price, setPrice] = useState('');
    const [categories, setCategories] = useState([]);
    const navigate = useNavigate();
    const [formData, setFormData] = useState(new FormData());


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

    const handleChange = (event) => {
        formData.set("ImageFile", event.target.files[0]);
        setFormData(formData);
    };

    // Функция для обработки выбора категории
    function handleCategoryChange(event) {
        setCategoryId(event.target.value);
    }

    async function AddToy(event) {
        event.preventDefault();
    
        formData.append('Name', Name);
        formData.append('Description', Description);
        formData.append('AgeRange', AgeRange);
        formData.append('CategoryId', CategoryId);
        formData.append('Price', Price);
    
        try {
            const response = await fetch(`${process.env.REACT_APP_TOY_SERVICE_URL}/toys`, {
                method: 'POST',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.token
                },
                body: formData
            });
            const data = await response

            console.log(data)
    
            if (response.ok) {
                alert('Игрушка успешно добавлена');
                setFormData(new FormData());
                window.location.reload();
            } else {
                alert('Ошибка при добавлении игрушки');
            }
        } catch (error) {
            console.error('Error adding toy:', error);
            alert('Произошла ошибка при отправке запроса');
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
                    <input type="text" name="Description" id="Description" placeholder="Введите описание игрушки" minLength="5" value={Description} onChange={(e) => setDescription(e.target.value)} />
                </div>
                <div className="form-text">
                    <h4>Возрастная категория</h4>
                    <input type="text" name="AgeRange" id="AgeRange" placeholder="Введите возрастную категорию игрушки" minLength="1" value={AgeRange} onChange={(e) => setAgeRange(e.target.value)} />
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
                    <h4>Изображение</h4>
                    <input type="file" name="ImageFile" id="ImageFile" accept="image/*" onChange={handleChange} />
                </div>
                <br /><button type="submit" className="button" >Добавить игрушку</button>
                <br /> <Link to="/AddCategories">Добавить категории</Link>
            </form>
        </div>
    );
}

export default AddToys;


