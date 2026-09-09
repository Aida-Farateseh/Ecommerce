import { useNavigate } from 'react-router-dom';
import { useState, type FormEvent } from "react";
import React from "react";

export function SignIn() {
    const navigate = useNavigate();
    const [bookAfterSignup, setBookAfterSignup] = useState(false);

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();

        // TODO: skapa konto / logga in här (t.ex. anrop till din api.post('/auth/...'))

        if (bookAfterSignup) {
            navigate('/formlayout');
        } else {
            // navigate('/SKRIV_DIN_ROUTE_HÄR');
        }
    };

    return (
        <div className="h-full bg-gray-900">
            <div className="h-full">
                <div className="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
                    <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                        <img
                            alt="Your Company"
                            src="https://tailwindcss.com/plus-assets/img/logos/mark.svg?color=indigo&shade=500"
                            className="mx-auto h-10 w-auto"
                        />
                        <h2 className="mt-10 text-center text-2xl/9 font-bold tracking-tight text-white">
                            Logga in på ditt konto
                        </h2>
                    </div>

                    <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                        <form onSubmit={handleSubmit} className="space-y-6">
                            <div>
                                <label htmlFor="email" className="block text-sm/6 font-medium text-gray-100">
                                    Email address
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="email"
                                        name="email"
                                        type="email"
                                        required
                                        autoComplete="email"
                                        className="block w-full rounded-md bg-white/5 px-3 py-1.5 text-base text-white outline-1 -outline-offset-1 outline-white/10 placeholder:text-gray-500 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-500 sm:text-sm/6"
                                    />
                                </div>
                            </div>

                            <div>
                                <div className="flex items-center justify-between">
                                    <label htmlFor="password" className="block text-sm/6 font-medium text-gray-100">
                                        Password
                                    </label>
                                    <div className="text-sm">
                                        <button
                                            type="button"
                                            onClick={() => navigate('/forgetpassword')}
                                            className="font-semibold text-indigo-400 hover:text-indigo-300"
                                        >
                                            Glömt lösenord?
                                        </button>
                                    </div>
                                </div>
                                <div className="mt-2">
                                    <input
                                        id="password"
                                        name="password"
                                        type="password"
                                        required
                                        autoComplete="current-password"
                                        className="block w-full rounded-md bg-white/5 px-3 py-1.5 text-base text-white outline-1 -outline-offset-1 outline-white/10 placeholder:text-gray-500 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-500 sm:text-sm/6"
                                    />
                                </div>
                            </div>

                            <div className="flex items-center gap-2">
                                <input
                                    id="book-after-signup"
                                    type="checkbox"
                                    checked={bookAfterSignup}
                                    onChange={(e) => setBookAfterSignup(e.target.checked)}
                                    className="h-4 w-4 rounded border-white/10 bg-white/5 text-indigo-500 focus:ring-indigo-500"
                                />
                                <label htmlFor="book-after-signup" className="text-sm/6 text-gray-100">
                                    Fortsätt till kassan direkt efter inloggning
                                </label>
                            </div>

                            <div>
                                <button
                                    type="submit"
                                    className="flex w-full justify-center rounded-md bg-indigo-500 px-3 py-1.5 text-sm/6 font-semibold text-white hover:bg-indigo-400 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-500"
                                >
                                    Logga in
                                </button>
                            </div>
                        </form>

                        <p className="mt-10 text-center text-sm/6 text-gray-400">
                            Har du inte konto?{' '}
                            <button
                                type="button"
                                onClick={() => navigate('/register')}
                                className="font-semibold text-indigo-400 hover:text-indigo-300"
                            >
                                Registrera dig här
                            </button>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    )
}