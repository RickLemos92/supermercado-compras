import { createRouter, createWebHistory } from 'vue-router'
import Storefront from '../views/Storefront.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Storefront
    },
    {
      path: '/checkout',
      name: 'checkout',
      component: () => import('../views/Checkout.vue')
    }
  ]
})

export default router
