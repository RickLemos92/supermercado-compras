<template>
  <div class="card product-card" :class="{ 'offer-card': product.price < 10 }" :aria-label="'Produto: ' + product.name">
    <div v-if="product.price < 10" class="offer-badge">OFERTA</div>
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
  background: var(--glass-bg);
  backdrop-filter: blur(8px);
  border: 1px solid var(--glass-border);
  border-radius: 24px;
  overflow: hidden;
  transition: all 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  box-shadow: 0 10px 30px rgba(0,0,0,0.05);
}
.offer-card {
  border: 2px solid var(--accent) !important;
}
.offer-badge {
  position: absolute;
  top: 1rem;
  right: -1.5rem;
  background: var(--accent);
  color: white;
  padding: 0.25rem 2.5rem;
  transform: rotate(45deg);
  font-weight: 900;
  font-size: 0.7rem;
  z-index: 10;
  box-shadow: 0 5px 10px rgba(0,0,0,0.1);
}
.product-img {
  width: 100%;
  height: 200px;
  object-fit: cover;
  transition: transform 0.6s ease;
}
.product-card:hover .product-img {
  transform: scale(1.1);
}
.product-info {
  padding: 1.25rem;
  display: flex;
  flex-direction: column;
  flex-grow: 1;
  text-align: left;
}
.product-name {
  font-size: 1.1rem;
  margin-bottom: 0.3rem;
  font-weight: 600;
}
.product-price {
  font-size: 1.4rem;
  font-weight: 800;
  color: var(--color-accent);
  margin-bottom: 1rem;
}
.card-actions {
  margin-top: auto;
  display: flex;
  gap: 0.5rem;
}
.listen-btn {
  flex: 1;
  padding: 0.5rem;
  font-size: 0.9rem;
}
.add-btn {
  flex: 2;
  padding: 0.5rem;
  font-size: 0.9rem;
}
</style>
