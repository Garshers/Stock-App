import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/stockDashboard';

export const fetchData = async (endpoint, params = {}) => {
    try {
        const url = `${API_BASE_URL}/${endpoint}`;
        const response = await axios.get(url, { params });
        return response.data;
    } catch (error) {
        console.error(`Error fetching data from ${endpoint}:`, error);
        throw error;
    }
};