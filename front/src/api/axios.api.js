import axios from "axios"
import { getTokenFromLocalStorage } from "../helpers/localstorage.helper"

export const instance = axios.create({
    baseURL: 'http://localhost:8085/login', 
    headers: {
        Authorization: 'Bearer ' + getTokenFromLocalStorage() || '', 
     },
    })

export const instanceR = axios.create({
    baseURL: 'http://localhost:8085/login', 
    headers: {
        Authorization: 'Bearer ' , 
     },
    })


