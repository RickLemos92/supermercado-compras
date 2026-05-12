<template>
  <div class="checkout container">
    <h2>Finalizar Compra</h2>
    
    <div v-if="cartStore.items.length === 0" class="empty-cart">
      <p>Seu carrinho está vazio.</p>
      <router-link to="/" class="btn btn-primary">Voltar para a Loja</router-link>
    </div>
    
    <div v-else class="checkout-grid">
      <div class="form-section card">
        <h3>Endereço de Entrega</h3>
        <form @submit.prevent="submitOrder">
          <div class="form-group">
            <label for="rua">Rua e Número</label>
            <input type="text" id="rua" required class="large-input">
          </div>
          
          <h3>Forma de Pagamento</h3>
          <div class="payment-methods">
            <label class="radio-label">
              <input type="radio" name="payment" value="PIX" required> PIX
            </label>
            <label class="radio-label">
              <input type="radio" name="payment" value="CREDIT_CARD"> Cartão de Crédito
            </label>
            <label class="radio-label">
              <input type="radio" name="payment" value="CASH"> Dinheiro na Entrega
            </label>
          </div>
          
          <button type="submit" class="btn btn-primary w-100 mt-2">Confirmar Pedido</button>
        </form>
      </div>
      
      <div class="summary-section card">
        <h3>Resumo do Pedido</h3>
        <ul class="summary-list">
          <li v-for="item in cartStore.items" :key="item.id" class="summary-item">
            <span>{{ item.quantity }}x {{ item.name }}</span>
            <span>R$ {{ (item.price * item.quantity).toFixed(2) }}</span>
          </li>
        </ul>
        <div class="summary-total">
          <strong>Total a Pagar:</strong>
          <strong>R$ {{ cartStore.totalPrice.toFixed(2) }}</strong>
        </div>
        <button class="btn btn-outline w-100 mt-1" @click="readTotal" aria-label="Ouvir total do pedido">
          🔊 Ouvir Total
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useCartStore } from '../stores/cart'
import { useRouter } from 'vue-router'
import { orderService } from '../services/orderService'

const cartStore = useCartStore()
const router = useRouter()

const submitOrder = async () => {
  try {
    const orderData = {
      address: document.getElementById('rua').value,
      paymentMethod: document.querySelector('input[name="payment"]:checked').value,
      totalAmount: cartStore.totalPrice,
      items: cartStore.items.map(item => ({
        productName: item.name,
        quantity: item.quantity,
        price: item.price
      }))
    }
    
    await orderService.create(orderData)
    
    alert('Pedido realizado com sucesso!')
    cartStore.clearCart()
    router.push('/')
  } catch (err) {
    console.error("Erro ao finalizar pedido:", err)
    alert("Houve um erro ao processar seu pedido. Tente novamente.")
  }
}

const readTotal = () => {
  if ('speechSynthesis' in window) {
    const utterance = new SpeechSynthesisUtterance(
      `O total do seu pedido é ${cartStore.totalPrice.toFixed(2)} reais.`
    )
    utterance.lang = 'pt-BR'
    window.speechSynthesis.speak(utterance)
  }
}
</script>

<style scoped>
.checkout-grid {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 2rem;
  margin-top: 2rem;
}
.card {
  padding: 2rem;
}
.form-group {
  margin-bottom: 1.5rem;
}
label {
  display: block;
  font-weight: bold;
  margin-bottom: 0.5rem;
}
.large-input {
  width: 100%;
  padding: 1rem;
  font-size: 1.25rem;
  border: 2px solid var(--color-border);
  border-radius: var(--radius-sm);
}
.payment-methods {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  margin-bottom: 2rem;
}
.radio-label {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 1rem;
  border: 2px solid var(--color-border);
  border-radius: var(--radius-sm);
  cursor: pointer;
}
.radio-label:has(input:checked) {
  border-color: var(--color-accent);
  background-color: #f0f7ff;
}
.w-100 { width: 100%; }
.mt-1 { margin-top: 1rem; }
.mt-2 { margin-top: 2rem; }
.summary-list {
  list-style: none;
  padding: 0;
  margin-bottom: 1.5rem;
}
.summary-item {
  display: flex;
  justify-content: space-between;
  margin-bottom: 0.5rem;
  padding-bottom: 0.5rem;
  border-bottom: 1px solid var(--color-border);
}
.summary-total {
  display: flex;
  justify-content: space-between;
  font-size: 1.5rem;
  color: var(--color-accent);
}
</style>
