<template>
  <div class="storefront">
    <!-- Hero Marketing Section -->
    <section class="hero-marketing">
      <div class="hero-overlay"></div>
      <div class="container hero-content">
        <h2 class="hero-title"><span class="italic">Sua feira</span> <span class="bold highlight">completa</span>, sem sair de casa!</h2>
        <p class="hero-subtitle">Qualidade e frescor <span class="bold">direto do produtor</span> para sua mesa.</p>
      </div>
    </section>

    <!-- Daily Offers Section -->
    <section class="offers-section">
      <div class="container">
        <h3 class="section-title"><span class="highlight">🔥 Ofertas</span> Imperdíveis</h3>
        <div class="offers-grid">
          <ProductCard 
            v-for="product in products.slice(0, 3)" 
            :key="'offer-' + product.id" 
            :product="product"
            class="offer-card"
          />
        </div>
      </div>
    </section>

    <div class="container">
      <h3 class="section-title">Todos os Produtos</h3>
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
import { productService } from '../services/productService'
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
    products.value = await productService.getAll()
  } catch (err) {
    console.error("Failed to fetch products:", err)
    error.value = true
  } finally {
    loading.value = false
  }
})
</script>

<style scoped>
.hero-marketing {
  position: relative;
  background-image: url('/market-bg.png');
  background-size: cover;
  background-position: center;
  color: white;
  padding: 6rem 1rem;
  margin-bottom: 3rem;
  border-radius: 0 0 50px 50px;
  overflow: hidden;
  display: flex;
  align-items: center;
}
.hero-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(135deg, rgba(var(--color-primary-rgb), 0.8), rgba(var(--color-surface-rgb), 0.4));
  backdrop-filter: blur(4px);
  z-index: 1;
}
.hero-content {
  position: relative;
  z-index: 2;
  text-shadow: 0 2px 10px rgba(0,0,0,0.3);
}
.hero-title {
  font-size: 3rem;
  margin-bottom: 1.5rem;
  color: white;
  line-height: 1.2;
}
.hero-subtitle {
  font-size: 1.4rem;
  opacity: 1;
  font-weight: 400;
}
.offers-section {
  padding: 2rem 0;
  margin-bottom: 3rem;
  background: rgba(245, 158, 11, 0.05);
  border-radius: 30px;
}
.offers-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  gap: 2rem;
}
.section-title {
  font-size: 1.5rem;
  margin-bottom: 1.5rem;
  color: var(--color-primary);
  text-align: left;
}
.product-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 2rem;
}
</style>
