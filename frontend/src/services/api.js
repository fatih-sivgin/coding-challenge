// Minimal API client using fetch. Adjust BASE_URL as needed.
export const BASE_URL = import.meta.env.VITE_API_BASE || ''


async function request(path, opts = {}) {
const res = await fetch(BASE_URL + path, opts)
if (!res.ok) {
const text = await res.text()
const err = new Error(res.status + ' ' + res.statusText + (text ? (': ' + text) : ''))
err.status = res.status
throw err
}
return res.json()
}


export const TrainingsApi = {
async list({ page = 0, size = 10 } = {}) {
const q = new URLSearchParams({ page, size })
const res = await request(`/api/v1/trainings?${q.toString()}`)
// adjust to your response model: { totalElements, trainings }
return res.trainings || []
},
async get(id) {
const res = await request(`/api/v1/trainings/${id}`)
return res || null
}
}