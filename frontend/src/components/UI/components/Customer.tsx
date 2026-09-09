import React from 'react';
import {useState} from 'react';
import {useParams, useNavigate} from 'react-router-dom';

import {UserProfile} from "./UserProfiles.tsx";
import api from "./Client.tsx";
export type Address = {
    id: number;
    street: string;
    city: string;
    zipCode: string;
}
export type CustomerProps = {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    createdAt: string;
    address?: Address;
    profile?: UserProfile;
}



    function ConfirmUser(){
    const {orderId} = useParams();
    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const handleConfirm = async () => {
        if (!email || !firstName || !lastName) {
            setError('Fyll i förnamn, efternamn och email');
            return;
        }

        setLoading(true);
        setError(null);

        try {
            await api.post('/orders', {
                orderId,
                email,
                firstName,
                lastName,
            });
            navigate('/my-orders');
        } catch (err) {
            setError('Kunde inte genomföra ordern. Försök igen.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="flex flex-col gap-4 max-w-md p-6">
            <h1 className="text-2xl font-bold">Bekräfta order {orderId}</h1>

            <input
                type="text"
                placeholder="Förnamn"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
                className="border p-2 rounded"
            />

            <input
                type="text"
                placeholder="Efternamn"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
                className="border p-2 rounded"
            />

            <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="border p-2 rounded"
            />

            {error && <p className="text-red-600 text-sm">{error}</p>}

            <button
                onClick={handleConfirm}
                disabled={loading}
                className="bg-blue-600 text-white px-4 py-2 rounded disabled:opacity-50"
            >
                {loading ? 'Bokar...' : 'Bekräfta order'}
            </button>
        </div>
    );
}

export default ConfirmUser;