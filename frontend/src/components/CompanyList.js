import React, { useState, useEffect } from 'react';
import axios from 'axios';

function CompanyList() {
    const [companies, setCompanies] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchCompanies = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/companies');
                setCompanies(response.data);
            } catch (err) {
                setError(err);
                console.error("Error fetching companies:", err);
            } finally {
                setLoading(false);
            }
        };

        fetchCompanies();
    }, []);

    if (loading) {
        return <div>Loading companies...</div>;
    }

    if (error) {
        return <div>Error: {error.message}</div>;
    }

    return (
        <ul>
            {companies.map(company => (
                <li key={company.symbol}>
                    {company.name} ({company.symbol})
                </li>
            ))}
        </ul>
    );
}

export default CompanyList;