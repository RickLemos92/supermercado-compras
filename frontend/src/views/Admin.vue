<template>
  <div class="admin">
    <div class="container">
      <h2 class="section-title">Adicionar Novo Produto</h2>
      
      <form @submit.prevent="addProduct" class="admin-form card">
        <div class="form-group">
          <label for="name">Nome do Produto</label>
          <input type="text" id="name" v-model="newProduct.name" required placeholder="Ex: Maçã Fuji">
        </div>
        
        <div class="form-group">
          <label for="description">Descrição</label>
          <textarea id="description" v-model="newProduct.description" required placeholder="Ex: Maçã fresca tipo A"></textarea>
        </div>
        
        <div class="form-group">
          <label for="price">Preço (R$)</label>
          <input type="number" id="price" v-model="newProduct.price" step="0.01" required>
        </div>
        
        <div class="form-group">
          <label for="imageUrl">URL da Imagem</label>
          <input type="url" id="imageUrl" v-model="newProduct.imageUrl" required placeholder="Ex: https://images.unsplash.com...">
        </div>
        
        <div class="form-group">
          <label for="category">Categoria</label>
          <select id="category" v-model="newProduct.category" required>
            <option value="Mercearia">Mercearia</option>
            <option value="Hortifruti">Hortifruti</option>
            <option value="Laticínios">Laticínios</option>
            <option value="Padaria">Padaria</option>
            <option value="Limpeza">Limpeza</option>
          </select>
        </div>
        
        <button type="submit" class="btn btn-primary submit-btn" :disabled="loading">
          {{ loading ? 'Adicionando...' : 'Cadastrar Produto' }}
        </button>
      </form>
      
      <p v-if="message" :class="['message', isError ? 'error' : 'success']">{{ message }}</p>
    </div>
  </div>
</template>

<script setup>
import { productService } from '../services/productService'

const loading = ref(false)
const message = ref('')
const isError = ref(false)

const newProduct = ref({
  name: '',
  description: '',
  price: 0,
  imageUrl: '',
  category: 'Mercearia'
})

const addProduct = async () => {
  loading.value = true
  message.value = ''
  try {
    await productService.create(newProduct.value)
    message.value = 'Produto adicionado com sucesso!'
    isError.value = false
    // Reset form
    newProduct.value = {
      name: '',
      description: '',
      price: 0,
      imageUrl: '',
      category: 'Mercearia'
    }
  } catch (err) {
    console.error("Erro ao adicionar produto:", err)
    message.value = 'Erro ao adicionar produto. Verifique os campos.'
    isError.value = true
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.admin {
  padding: 2rem 0;
}
.admin-form {
  max-width: 600px;
  margin: 0 auto;
  padding: 2rem;
  text-align: left;
}
.form-group {
  margin-bottom: 1.5rem;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}
label {
  font-weight: bold;
  font-size: 0.9rem;
}
input, textarea, select {
  padding: 0.75rem;
  border: 1px solid var(--color-border);
  border-radius: var(--radius-md);
  font-size: 1rem;
}
.submit-btn {
  width: 100%;
  padding: 1rem;
  font-weight: bold;
}
.message {
  margin-top: 1.5rem;
  text-align: center;
  font-weight: bold;
}
.success { color: #28a745; }
.error { color: #dc3545; }
</style>
