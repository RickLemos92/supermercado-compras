<template>
  <header class="header">
    <div class="container header-content">
      <h1 class="logo">🛒 SuperMercado</h1>
      
      <div class="actions">
        <VoiceAssistant @result="handleVoiceSearch" />
        
        <router-link to="/checkout" class="cart-btn" aria-label="Abrir carrinho de compras">
          🛒 Carrinho ({{ cartStore.totalItems }})
        </router-link>
      </div>
    </div>
  </header>
</template>

<script setup>
import { useCartStore } from '../stores/cart'
import VoiceAssistant from './VoiceAssistant.vue'

const cartStore = useCartStore()

const handleVoiceSearch = (text) => {
  cartStore.search = text
  console.log("Usuário disse:", text)
  // Se não estiver na home, vai para a home
  router.push('/')
}
</script>

<style scoped>
.header {
  background-color: var(--color-surface);
  border-bottom: 2px solid var(--color-border);
  position: sticky;
  top: 0;
  z-index: 100;
}
.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.5rem 1rem;
}
.logo {
  font-size: 1.8rem;
  font-weight: 800;
}
.actions {
  display: flex;
  gap: 1rem;
  align-items: center;
}
.cart-btn {
  background-color: var(--color-accent);
  color: white;
  padding: 0.75rem 1.5rem;
  border-radius: var(--radius-lg);
  font-weight: bold;
  text-decoration: none;
}
</style>
