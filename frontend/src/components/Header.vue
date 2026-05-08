<template>
  <header class="header">
    <div class="container header-content">
      <router-link to="/" class="logo-link">
        <h1 class="logo">🛒 SuperMercado</h1>
      </router-link>
      
      <div class="search-bar">
        <input 
          type="text" 
          v-model="cartStore.search" 
          placeholder="Pesquisar produtos..." 
          class="search-input"
        />
        <VoiceAssistant @result="handleVoiceSearch" />
      </div>
      
      <div class="actions">
        <router-link to="/admin" class="admin-link">Gerenciar</router-link>
        
        <router-link to="/checkout" class="cart-btn" aria-label="Abrir carrinho de compras">
          🛒 ({{ cartStore.totalItems }})
        </router-link>
      </div>
    </div>
  </header>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { useCartStore } from '../stores/cart'
import VoiceAssistant from './VoiceAssistant.vue'

const cartStore = useCartStore()
const router = useRouter()

const handleVoiceSearch = (text) => {
  cartStore.search = text
  console.log("Usuário disse:", text)
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
  padding: 0.75rem 1rem;
}
.logo-link {
  text-decoration: none;
  color: inherit;
}
.logo {
  font-size: 1.4rem;
  font-weight: 800;
  margin: 0;
}
.search-bar {
  display: flex;
  flex: 1;
  max-width: 500px;
  margin: 0 2rem;
  gap: 0.5rem;
}
.search-input {
  flex: 1;
  padding: 0.5rem 1rem;
  border: 1px solid var(--color-border);
  border-radius: var(--radius-lg);
  font-size: 1rem;
}
.actions {
  display: flex;
  gap: 1.5rem;
  align-items: center;
}
.admin-link {
  color: var(--color-text);
  text-decoration: none;
  font-weight: 500;
}
.admin-link:hover {
  color: var(--color-primary);
}
.cart-btn {
  background-color: var(--color-accent);
  color: white;
  padding: 0.5rem 1rem;
  border-radius: var(--radius-lg);
  font-weight: bold;
  text-decoration: none;
}
</style>
