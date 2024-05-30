import React, { useEffect, useState, useCallback } from 'react';
import { useParams, Link } from 'react-router-dom';

function ToyDetails() {
    const { id } = useParams();
    const [toy, setToy] = useState(null);
    const [InStock, setInStock] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [count, setCount] = useState();
    const [location, setLocation] = useState();

    const fetchToyDetails = useCallback(async () => {
        try {
            const response = await fetch(`${process.env.REACT_APP_TOY_SERVICE_URL}/toys/${id}`, {
                method: 'GET',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.token
                },
            });
            const data = await response.json();
            setToy(data);
        } catch (error) {
            console.error('Error fetching toy details:', error);
        }
    }, [id]);

    const fetchToyInfo = useCallback(async () => {
        try {
            const response = await fetch(`${process.env.REACT_APP_INVENTORY_SERVICE_URL}/inventory/info/${id}`, {
                method: 'GET'
            });
            const infdata = await response.json();
            setInStock(infdata.data.count);
        } catch (error) {
            console.error('Error fetching toy info:', error);
        }
    }, [id]);

    async function AddToyInStock(event) {
        event.preventDefault();

        const toyData = {
            "toyId": id,
            location,
            count
        };
        if (!count || !location) {
            alert('Пожалуйста заполните все поля');
            return;
        } else {
            try {
                const response = await fetch(`${process.env.REACT_APP_INVENTORY_SERVICE_URL}/inventory/admin/add`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + localStorage.token
                    },
                    body: JSON.stringify(toyData),
                });

                if (response.ok) {
                    alert('Игрушка успешно добавлена на склад');
                    window.location.reload();
                } else {
                    alert('Ошибка при добавлений на склад');
                }
            } catch (error) {
                console.error('Error adding toy:', error);
                alert('Произошла ошибка при отправке запроса');
            }
        }
    }

    useEffect(() => {
        fetchToyDetails();
        fetchToyInfo();
    }, [fetchToyDetails, fetchToyInfo]);


    async function addToOrder(event) {
        event.preventDefault()

        const cart = JSON.parse(localStorage.getItem('cart')) || [];
        localStorage.setItem('cart', JSON.stringify(cart));

        if (localStorage.order === 'false') {
            try {
                const response = await fetch(`${process.env.REACT_APP_ORDER_SERVICE_URL}/order`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + localStorage.token
                    },
                    body: JSON.stringify({
                        "toyId": id,
                        "renterId": localStorage.renterId,
                        "count": 1
                    }),
                });
                const data = await response.json()
                console.log(data)
                if (data.success === true) {
                    localStorage.order = true
                    localStorage.setItem('orderId', data.data.order.id)
                    const newItem = { orderItemId: data.data.orderItems[0].id, id: toy.id, name: toy.name, price: toy.price, imageUrl: toy.imageUrl, count: 1 };
                    const existingItemIndex = cart.findIndex(item => item.id === toy.id);
                    if (existingItemIndex !== -1) {
                        // Если есть, обновить количество
                        cart[existingItemIndex].count += 1;
                    } else {
                        // Если нет, добавить новый товар
                        cart.push(newItem);
                    }
                    localStorage.setItem('cart', JSON.stringify(cart));
                    window.location.reload();
                }
                else {
                    alert("Произошла ошибка при добавлений в корзину")
                }
            } catch (error) {
                console.error('Error adding toy:', error);
                alert('Произошла ошибка при отправке запроса');
            }
        }
        else if (localStorage.order === 'true') {
            try {
                const response = await fetch(`${process.env.REACT_APP_ORDER_SERVICE_URL}/order-item`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + localStorage.token
                    },
                    body: JSON.stringify({
                        "toyId": id,
                        "renterId": localStorage.renterId,
                        "orderId": localStorage.orderId,
                        "count": 1
                    }),
                });
                const data = await response.json()
                console.log(data)
                if (data.success === true) {
                    const newItem = { orderItemId: data.data[0].id, id: toy.id, name: toy.name, price: toy.price, imageUrl: toy.imageUrl, count: 1 };
                    const existingItemIndex = cart.findIndex(item => item.id === toy.id);
                    if (existingItemIndex !== -1) {
                        // Если есть, обновить количество
                        cart[existingItemIndex].count += 1;
                    } else {
                        // Если нет, добавить новый товар
                        cart.push(newItem);
                    }
                    localStorage.setItem('cart', JSON.stringify(cart));
                    window.location.reload();
                }
                else {
                    alert("Произошла ошибка при добавлений в корзину")
                }
            } catch (error) {
                console.error('Error adding toy:', error);
                alert('Произошла ошибка при отправке запроса');
            }
        }

    }

    const handleAddButtonClick = () => {
        if (localStorage.role === "ADMIN" || localStorage.role === "USER and ADMIN") {
            setShowForm(!showForm);
        }

    };

    let button;
    if (localStorage.role === "ADMIN" || localStorage.role === "USER and ADMIN") {
        button = <Link className="buybutton-description" id="button" onClick={handleAddButtonClick}>Добавить на склад</Link>
    } 
    else if(localStorage.role === "USER") {
        button = <Link className="buybutton-description" id="button" onClick={addToOrder}>Добавить</Link>
    }
    else {
        button = <Link to="/Signin" className="buybutton-description" id="button">Добавить</Link>
    }

    if (!toy) {
        return <div>Loading...</div>;
    }

    return (
        <div className='toy-details'>
            <div className='toy-details-content'>
                <div className='toy-details-img'>
                    <img src={`${process.env.REACT_APP_TOY_SERVICE_URL}/Images/${toy.imageUrl}`} alt={toy.name} />
                </div>
                <div className='toy-details-description'>
                    <Link to={`/Toys`} className='back-link' id="button">🠈НАЗАД</Link>
                    <h1>{toy.name}</h1>
                    <p>От {toy.ageRange} лет</p>
                    <p className='price'>Цена: {toy.price} ТОКЕНОВ</p>
                    <p>● Если вы арендуете эту игрушку по плану Toy Sack, вы потеряете
                        ₸20000/2000 жетонов, которые можно потратить на другие игрушки</p>
                    <p>● Арендуйте игрушки на сумму до 23000 тенге за один обмен.</p>
                    <p>● Неограниченные свапы</p>
                    <p>● Упаковка без пластика, пригодная для вторичной переработки и многоразового использования.</p>
                    <p>● Доставка к вашей двери</p>
                    <h3>Количество на складе: {InStock}</h3>
                </div>
            </div>
            <div className='toy-details-button'>
                {button}
            </div>

            {showForm && (
                <div className="Signup">
                    <h1>Добавление игрушки на склад</h1>
                    <form onSubmit={AddToyInStock}>
                        <div className="form-text">
                            <h4>Количество</h4>
                            <input type="string" name='count' id="Description" placeholder="Введите количество игрушек" minLength="1" value={count} onChange={(e) => setCount(e.target.value)} />
                        </div>
                        <div className="form-text">
                            <h4>Место хранения</h4>
                            <input type="string" name='location' id="AgeRange" placeholder="Введите место хранения" minLength="2" value={location} onChange={(e) => setLocation(e.target.value)} />
                        </div>
                        <br /><button type="submit" className="button" >Добавить игрушку на склад</button>
                    </form>
                </div>
            )}

        </div>
    );
}

export default ToyDetails;
