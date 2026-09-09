const BASE_URL = '/api';

async function request(method: string, url: string, body?: unknown) {
    const res = await fetch(`${BASE_URL}${url}`, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: body ? JSON.stringify(body) : undefined,
    });

    if (!res.ok) {
        throw new Error(`API-fel: ${res.status} ${res.statusText}`);
    }

    return res.json();
}

const api = {
    get: (url: string) => request('GET', url),
    post: (url: string, body?: unknown) => request('POST', url, body),
    put: (url: string, body?: unknown) => request('PUT', url, body),
    delete: (url: string) => request('DELETE', url),
};

export default api;