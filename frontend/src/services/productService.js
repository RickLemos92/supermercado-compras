import api from '../api/client'

export const productService = {
  async getAll() {
    const response = await api.get('/products')
    return response.data
  },

  async getById(id) {
    const response = await api.get(`/products/${id}`)
    return response.data
  },

  async create(productData) {
    const response = await api.post('/products', productData)
    return response.data
  },

  async delete(id) {
    await api.delete(`/products/${id}`)
  }
}
