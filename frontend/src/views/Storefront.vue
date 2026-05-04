<template>
  <div class="storefront">
    <div class="container">
      <h2 class="section-title">Produtos Disponíveis</h2>
      <div v-if="loading" class="loading" aria-live="polite">
        Carregando produtos...
      </div>
      <div v-else-if="error" class="error" aria-live="assertive">
        Erro ao carregar produtos. Tente novamente.
      </div>
      <div v-else class="product-grid">
        <ProductCard 
          v-for="product in filteredProducts" 
          :key="product.id" 
          :product="product" 
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import axios from 'axios'
import ProductCard from '../components/ProductCard.vue'
import { useCartStore } from '../stores/cart'

const cartStore = useCartStore()
const products = ref([])
const loading = ref(true)
const error = ref(false)

const filteredProducts = computed(() => {
  if (!cartStore.search) return products.value
  return products.value.filter(p => 
    p.name.toLowerCase().includes(cartStore.search.toLowerCase()) ||
    p.category.toLowerCase().includes(cartStore.search.toLowerCase())
  )
})

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/products')
    products.value = response.data
  } catch (err) {
    console.error("Failed to fetch products:", err)
    error.value = true
  } finally {
    loading.value = false
  }
})
</script>

<style scoped>
.section-title {
  font-size: 2rem;
  margin-bottom: 2rem;
  color: var(--color-primary);
}
.product-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 2rem;
}
.loading, .error {
  font-size: 1.5rem;
  text-align: center;
  padding: 4rem;
}
.error {
  color: #dc3545;
}
</style>
