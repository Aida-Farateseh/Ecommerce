import { ChevronDownIcon } from '@heroicons/react/16/solid'
import React from "react";
import { type FormEvent, useState } from "react";
import { useNavigate } from 'react-router-dom';

type PaymentMethod =
    | "card"
    | "swish"
    | "invoice"
    | "wallet"
    | "bankid"
    | "freja";

const createPaymentSessionUrl = "/api/payments/session";
const confirmPaymentUrl = "/api/payments/confirm";
const startBankIdUrl = "/api/auth/bankid/start";
const startFrejaUrl = "/api/auth/freja/start";

 function CheckoutForm() {
    const navigate = useNavigate();
    const [register, setRegister] = useState(false);
    const [method, setMethod] = useState<PaymentMethod>("card");
    const [loading, setLoading] = useState(false);
    const [status, setStatus] = useState<string | null>(null);
    const [error, setError] = useState<string | null>(null);

    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const passwordsMatch = password === '' || confirmPassword === '' || password === confirmPassword;

    const countries = [
        { name: "Sweden", dialCode: "+46" },
        { name: "United States", dialCode: "+1" },
        { name: "Canada", dialCode: "+1" },
        { name: "Mexico", dialCode: "+52" },
        { name: "Norway", dialCode: "+47" },
        { name: "Denmark", dialCode: "+45" },
        { name: "Finland", dialCode: "+358" },
        { name: "United Kingdom", dialCode: "+44" },
        { name: "Germany", dialCode: "+49" },
    ];
    const [country, setCountry] = useState(countries[0].name);
    const [phone, setPhone] = useState('');
    const dialCode = countries.find((c) => c.name === country)?.dialCode ?? '';

    const handleRegister = (e: FormEvent) => {
        e.preventDefault();
        // TODO: din order/registreringslogik här
        // navigate('/order-confirmation');
    };

    const initPaymentSession = async () => {
        setLoading(true);
        setError(null);
        try {
            const res = await fetch(createPaymentSessionUrl, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ method }),
            });
            const data = await res.json();
            setStatus(`Session skapad: ${data.sessionId || data.clientSecret}`);
        } catch {
            setError("Kunde inte skapa betalningssession.");
        } finally {
            setLoading(false);
        }
    };

    const handleConfirmPayment = async () => {
        setLoading(true);
        setError(null);
        try {
            const res = await fetch(confirmPaymentUrl, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ method }),
            });
            const data = await res.json();
            setStatus(`Betalning klar: status=${data.status}, issuer=${data.issuer}`);
        } catch {
            setError("Kunde inte bekräfta betalning.");
        } finally {
            setLoading(false);
        }
    };

    const startBankIdFlow = async () => {
        setLoading(true);
        setError(null);
        try {
            const res = await fetch(startBankIdUrl, { method: "POST" });
            const data = await res.json();
            setStatus(`BankID startat, orderRef=${data.orderRef}`);
        } catch {
            setError("Kunde inte starta BankID-verifiering.");
        } finally {
            setLoading(false);
        }
    };

    const startFrejaFlow = async () => {
        setLoading(true);
        setError(null);
        try {
            const res = await fetch(startFrejaUrl, { method: "POST" });
            const data = await res.json();
            setStatus(`Freja eID startat, transactionId=${data.transactionId}`);
        } catch {
            setError("Kunde inte starta Freja eID-verifiering.");
        } finally {
            setLoading(false);
        }
    };

    return (
        <form onSubmit={handleRegister}>
            <div className="mx-auto max-w-4xl px-4 py-10">
                <div className="space-y-12">

                    <div className="border-b border-gray-900/10 pb-12">
                        <h2 className="text-base/7 font-semibold text-gray-900">Leveransadress</h2>
                        <p className="mt-1 text-sm/6 text-gray-600">Ange adressen dit din order ska skickas.</p>

                        <div className="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                            <div className="sm:col-span-3">
                                <label htmlFor="first-name" className="block text-sm/6 font-medium text-gray-900">Förnamn</label>
                                <div className="mt-2">
                                    <input id="first-name" name="first-name" type="text" autoComplete="given-name"
                                           className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6" />
                                </div>
                            </div>

                            <div className="sm:col-span-3">
                                <label htmlFor="last-name" className="block text-sm/6 font-medium text-gray-900">Efternamn</label>
                                <div className="mt-2">
                                    <input id="last-name" name="last-name" type="text" autoComplete="family-name"
                                           className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6" />
                                </div>
                            </div>

                            <div className="sm:col-span-4">
                                <label htmlFor="email" className="block text-sm/6 font-medium text-gray-900">E-postadress</label>
                                <div className="mt-2">
                                    <input id="email" name="email" type="email" autoComplete="email"
                                           className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6" />
                                </div>
                            </div>

                            {register && (
                                <>
                                    <div className="sm:col-span-3">
                                        <label htmlFor="password" className="block text-sm/6 font-medium text-gray-900">Lösenord</label>
                                        <div className="mt-2">
                                            <input id="password" name="password" type="password" autoComplete="new-password"
                                                   value={password} onChange={(e) => setPassword(e.target.value)}
                                                   className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6" />
                                        </div>
                                    </div>

                                    <div className="sm:col-span-3">
                                        <label htmlFor="confirm-password" className="block text-sm/6 font-medium text-gray-900">Bekräfta lösenord</label>
                                        <div className="mt-2">
                                            <input id="confirm-password" name="confirm-password" type="password" autoComplete="new-password"
                                                   value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)}
                                                   className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6" />
                                        </div>
                                        {!passwordsMatch && (
                                            <p className="mt-2 text-sm/6 text-red-600">Lösenorden matchar inte.</p>
                                        )}
                                    </div>
                                </>
                            )}

                            <div className="sm:col-span-3">
                                <label htmlFor="country" className="block text-sm/6 font-medium text-gray-900">Land</label>
                                <div className="mt-2 grid grid-cols-1">
                                    <select id="country" name="country" autoComplete="country-name"
                                            value={country} onChange={(e) => setCountry(e.target.value)}
                                            className="col-start-1 row-start-1 w-full appearance-none rounded-md bg-white py-1.5 pr-8 pl-3 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6">
                                        {countries.map((c) => (
                                            <option key={c.name} value={c.name}>{c.name}</option>
                                        ))}
                                    </select>
                                    <ChevronDownIcon aria-hidden="true"
                                                     className="pointer-events-none col-start-1 row-start-1 mr-2 size-5 self-center justify-self-end text-gray-500 sm:size-4" />
                                </div>
                            </div>

                            <div className="sm:col-span-3">
                                <label htmlFor="phone" className="block text-sm/6 font-medium text-gray-900">Telefonnummer</label>
                                <div className="mt-2 flex items-center rounded-md bg-white pl-3 outline-1 -outline-offset-1 outline-gray-300 focus-within:outline-2 focus-within:-outline-offset-2 focus-within:outline-indigo-600">
                                    <div className="shrink-0 text-base text-gray-500 select-none sm:text-sm/6">{dialCode}</div>
                                    <input id="phone" name="phone" type="tel" autoComplete="tel-national" placeholder="70 123 45 67"
                                           value={phone} onChange={(e) => setPhone(e.target.value)}
                                           className="block min-w-0 grow bg-white py-1.5 pr-3 pl-1 text-base text-gray-900 placeholder:text-gray-400 focus:outline-none sm:text-sm/6" />
                                </div>
                                <p className="mt-2 text-sm/6 text-gray-600">Används för leveransaviseringar via SMS.</p>
                            </div>

                            <div className="col-span-full">
                                <label htmlFor="street-address" className="block text-sm/6 font-medium text-gray-900">Gatuadress</label>
                                <div className="mt-2">
                                    <input id="street-address" name="street-address" type="text" autoComplete="street-address"
                                           className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6" />
                                </div>
                            </div>

                            <div className="sm:col-span-2 sm:col-start-1">
                                <label htmlFor="city" className="block text-sm/6 font-medium text-gray-900">Stad</label>
                                <div className="mt-2">
                                    <input id="city" name="city" type="text" autoComplete="address-level2"
                                           className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6" />
                                </div>
                            </div>

                            <div className="sm:col-span-2">
                                <label htmlFor="region" className="block text-sm/6 font-medium text-gray-900">Region / Län</label>
                                <div className="mt-2">
                                    <input id="region" name="region" type="text" autoComplete="address-level1"
                                           className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6" />
                                </div>
                            </div>

                            <div className="sm:col-span-2">
                                <label htmlFor="postal-code" className="block text-sm/6 font-medium text-gray-900">Postnummer</label>
                                <div className="mt-2">
                                    <input id="postal-code" name="postal-code" type="text" autoComplete="postal-code"
                                           className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6" />
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="border-b border-gray-900/10 pb-12">
                        <h2 className="text-base/7 font-semibold text-gray-900">Betalning</h2>
                        <p className="mt-1 text-sm/6 text-gray-600">Välj hur du vill betala eller verifiera dig.</p>

                        <div className="mt-10 space-y-6">
                            <fieldset>
                                <legend className="text-sm/6 font-semibold text-gray-900">Betalmetod</legend>
                                <div className="mt-4 space-y-4">
                                    <label className="flex items-center gap-3 text-sm/6 text-gray-900">
                                        <input type="radio" value="card" checked={method === "card"} onChange={() => setMethod("card")}
                                               className="size-4 border-gray-300 text-indigo-600 focus:ring-indigo-600" />
                                        Kort (PCI-säker via PSP)
                                    </label>
                                    <label className="flex items-center gap-3 text-sm/6 text-gray-900">
                                        <input type="radio" value="swish" checked={method === "swish"} onChange={() => setMethod("swish")}
                                               className="size-4 border-gray-300 text-indigo-600 focus:ring-indigo-600" />
                                        Swish
                                    </label>
                                    <label className="flex items-center gap-3 text-sm/6 text-gray-900">
                                        <input type="radio" value="invoice" checked={method === "invoice"} onChange={() => setMethod("invoice")}
                                               className="size-4 border-gray-300 text-indigo-600 focus:ring-indigo-600" />
                                        Faktura
                                    </label>
                                    <label className="flex items-center gap-3 text-sm/6 text-gray-900">
                                        <input type="radio" value="wallet" checked={method === "wallet"} onChange={() => setMethod("wallet")}
                                               className="size-4 border-gray-300 text-indigo-600 focus:ring-indigo-600" />
                                        Wallet / NFC (Apple Pay / Google Pay)
                                    </label>
                                    <label className="flex items-center gap-3 text-sm/6 text-gray-900">
                                        <input type="radio" value="bankid" checked={method === "bankid"} onChange={() => setMethod("bankid")}
                                               className="size-4 border-gray-300 text-indigo-600 focus:ring-indigo-600" />
                                        Verifiering med BankID
                                    </label>
                                    <label className="flex items-center gap-3 text-sm/6 text-gray-900">
                                        <input type="radio" value="freja" checked={method === "freja"} onChange={() => setMethod("freja")}
                                               className="size-4 border-gray-300 text-indigo-600 focus:ring-indigo-600" />
                                        Verifiering med Freja eID
                                    </label>
                                </div>
                            </fieldset>

                            <div className="flex flex-wrap gap-3">
                                {(method === "card" || method === "swish" || method === "invoice" || method === "wallet") && (
                                    <>
                                        <button type="button" disabled={loading} onClick={initPaymentSession}
                                                className="rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-xs hover:bg-indigo-500 disabled:opacity-50">
                                            Initiera betalningssession
                                        </button>
                                        <button type="button" disabled={loading} onClick={handleConfirmPayment}
                                                className="rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-xs inset-ring inset-ring-gray-300 hover:bg-gray-50 disabled:opacity-50">
                                            Bekräfta betalning
                                        </button>
                                    </>
                                )}
                                {method === "bankid" && (
                                    <button type="button" disabled={loading} onClick={startBankIdFlow}
                                            className="rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-xs hover:bg-indigo-500 disabled:opacity-50">
                                        Starta BankID-verifiering
                                    </button>
                                )}
                                {method === "freja" && (
                                    <button type="button" disabled={loading} onClick={startFrejaFlow}
                                            className="rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-xs hover:bg-indigo-500 disabled:opacity-50">
                                        Starta Freja eID-verifiering
                                    </button>
                                )}
                            </div>

                            {loading && <p className="text-sm/6 text-gray-600">Jobbar...</p>}
                            {status && <p className="text-sm/6 text-gray-600">Status: {status}</p>}
                            {error && <p className="text-sm/6 text-red-600">Fel: {error}</p>}
                        </div>
                    </div>

                    <div className="border-b border-gray-900/10 pb-12">
                        <h2 className="text-base/7 font-semibold text-gray-900">Aviseringar</h2>
                        <p className="mt-1 text-sm/6 text-gray-600">Vi meddelar dig alltid om viktiga ändringar i din order.</p>

                        <div className="mt-10 space-y-10">
                            <fieldset>
                                <legend className="text-sm/6 font-semibold text-gray-900">Via e-post</legend>
                                <div className="mt-6 space-y-6">
                                    <div className="flex gap-3">
                                        <input defaultChecked id="order-updates" name="order-updates" type="checkbox"
                                               className="size-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-600" />
                                        <div className="text-sm/6">
                                            <label htmlFor="order-updates" className="font-medium text-gray-900">Orderuppdateringar</label>
                                            <p className="text-gray-500">Få besked när din order skickas eller levereras.</p>
                                        </div>
                                    </div>
                                    <div className="flex gap-3">
                                        <input id="promotions" name="promotions" type="checkbox"
                                               className="size-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-600" />
                                        <div className="text-sm/6">
                                            <label htmlFor="promotions" className="font-medium text-gray-900">Erbjudanden</label>
                                            <p className="text-gray-500">Få besked om rabatter och kampanjer.</p>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>

                            <fieldset>
                                <legend className="text-sm/6 font-semibold text-gray-900">Push-notiser</legend>
                                <p className="mt-1 text-sm/6 text-gray-600">
                                    Skickas via SMS till din mobil{dialCode && phone ? ` (${dialCode} ${phone})` : ''}.
                                </p>
                                <div className="mt-6 space-y-6">
                                    <div className="flex items-center gap-x-3">
                                        <input defaultChecked id="push-everything" name="push-notifications" type="radio"
                                               className="size-4 border-gray-300 text-indigo-600 focus:ring-indigo-600" />
                                        <label htmlFor="push-everything" className="block text-sm/6 font-medium text-gray-900">Allt</label>
                                    </div>
                                    <div className="flex items-center gap-x-3">
                                        <input id="push-order-only" name="push-notifications" type="radio"
                                               className="size-4 border-gray-300 text-indigo-600 focus:ring-indigo-600" />
                                        <label htmlFor="push-order-only" className="block text-sm/6 font-medium text-gray-900">Endast orderstatus</label>
                                    </div>
                                    <div className="flex items-center gap-x-3">
                                        <input id="push-nothing" name="push-notifications" type="radio"
                                               className="size-4 border-gray-300 text-indigo-600 focus:ring-indigo-600" />
                                        <label htmlFor="push-nothing" className="block text-sm/6 font-medium text-gray-900">Inga push-notiser</label>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </div>

                    <div className="flex items-center gap-3">
                        <input id="register-checkbox" type="checkbox" checked={register}
                               onChange={(e) => setRegister(e.target.checked)}
                               className="size-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-600" />
                        <label htmlFor="register-checkbox" className="text-sm/6 text-gray-900">Skapa konto samtidigt</label>
                    </div>

                </div>

                <div className="mt-6 flex items-center justify-end gap-x-6">
                    <button type="button" className="text-sm/6 font-semibold text-gray-900">Avbryt</button>
                    <button type="submit"
                            className="rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-xs hover:bg-indigo-500 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">
                        Slutför köp
                    </button>
                </div>
            </div>
        </form>
    )
}