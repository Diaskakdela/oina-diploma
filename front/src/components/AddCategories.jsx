import React, { useState, useEffect } from "react";
import { Link, useNavigate } from 'react-router-dom';

function AddCategories() {
    const [CategoryName, setCategoryName] = useState('')
    const navigate = useNavigate()

    async function AddCategory(event) {
        event.preventDefault()

        const toyData = {
            CategoryName
        };

        if (!CategoryName) {
            alert('Пожалуйста заполните все поля');
            return;
        } else {
            const response = await fetch(`${process.env.REACT_APP_TOY_SERVICE_URL}/category`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.token
                },
                body: JSON.stringify(toyData),
            });
            
            const data = await response.json()

            if (data.id) {
                alert('Категория успешно добавлена')
            } else {
                alert('Ошибка при добавлении категорий')
            }
        }
    }

    useEffect(() => {
        const isAdmin = localStorage.role === 'ADMIN' || localStorage.role === 'USER and ADMIN';

        if (!isAdmin) {
            alert('У вас нету прав администратора');
            navigate('/Home');
        }
    }, [navigate]);


    
        return (
            <div className="AddCat" >
                <h1>Добавление категорий</h1>
                <form onSubmit={AddCategory}>
                    <div className="form-text">
                        <h4>Название категорий</h4>
                        <input type="text" name="CategoryName" id="CategoryName" placeholder="Введите название категорий игрушки" minLength="3" value={CategoryName} onChange={(e) => setCategoryName(e.target.value)} />
                    </div>
                    <br /><button type="submit" className="button" >добавить категорию</button>
                    <br /> <Link to="/AddToys">Добавить игрушку</Link>
                </form>
            </div>
        )
}

export default AddCategories;