<template>
  <div class="card product-card" :aria-label="'Produto: ' + product.name">
    <img :src="product.imageUrl" :alt="product.name" class="product-img" />
    <div class="product-info">
      <h2 class="product-name">{{ product.name }}</h2>
      <p class="product-price">R$ {{ product.price.toFixed(2) }}</p>
      
      <div class="card-actions">
        <button 
          class="btn btn-outline listen-btn" 
          @click="readProductInfo"
          aria-label="Ouvir informações do produto"
        >
          🔊 Ouvir
        </button>
        <button 
          class="btn btn-primary add-btn" 
          @click="cartStore.addToCart(product)"
          :aria-label="'Adicionar ' + product.name + ' ao carrinho'"
        >
          Adicionar
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useCartStore } from '../stores/cart'

const props = defineProps({
  product: {
    type: Object,
    required: true
  }
})

const cartStore = useCartStore()

const readProductInfo = () => {
  if ('speechSynthesis' in window) {
    const utterance = new SpeechSynthesisUtterance(
      `${props.product.name}. Preço: ${props.product.price.toFixed(2)} reais.`
    )
    utterance.lang = 'pt-BR'
    window.speechSynthesis.speak(utterance)
  } else {
    alert("Leitura de tela não suportada neste navegador.")
  }
}
</script>

<style scoped>
.product-card {
  display: flex;
  flex-direction: column;
}
.product-img {
  width: 100%;
  height: 200px;
  object-fit: cover;
  border-bottom: 1px solid var(--color-border);
}
.product-info {
  padding: 1.5rem;
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}
.product-name {
  font-size: 1.4rem;
  margin-bottom: 0.5rem;
}
.product-price {
  font-size: 1.8rem;
  font-weight: bold;
  color: var(--color-accent);
  margin-bottom: 1.5rem;
}
.card-actions {
  margin-top: auto;
  display: flex;
  gap: 0.5rem;
}
.listen-btn {
  flex: 1;
  padding: 1rem;
}
.add-btn {
  flex: 2;
  padding: 1rem;
}
</style>
