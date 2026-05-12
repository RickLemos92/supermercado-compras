import api from '../api/client'

export const orderService = {
  async create(orderData) {
    const response = await api.post('/orders', orderData)
    return response.data
  }
}
