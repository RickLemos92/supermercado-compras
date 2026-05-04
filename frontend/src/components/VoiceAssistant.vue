<template>
  <button 
    class="voice-btn" 
    @click="toggleListening"
    :aria-label="isListening ? 'Parar escuta' : 'Buscar por voz'"
    :class="{ listening: isListening }"
  >
    <span v-if="isListening">🎤 Escutando...</span>
    <span v-else>🎙️ Falar</span>
  </button>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const emit = defineEmits(['result'])
const isListening = ref(false)
let recognition = null

onMounted(() => {
  const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition
  if (SpeechRecognition) {
    recognition = new SpeechRecognition()
    recognition.lang = 'pt-BR'
    recognition.continuous = false
    recognition.interimResults = false

    recognition.onresult = (event) => {
      const transcript = event.results[0][0].transcript
      emit('result', transcript)
      isListening.value = false
    }

    recognition.onerror = () => {
      isListening.value = false
    }
    
    recognition.onend = () => {
      isListening.value = false
    }
  }
})

const toggleListening = () => {
  if (!recognition) {
    alert("Seu navegador não suporta reconhecimento de voz.")
    return
  }
  
  if (isListening.value) {
    recognition.stop()
  } else {
    recognition.start()
    isListening.value = true
  }
}
</script>

<style scoped>
.voice-btn {
  background-color: var(--color-surface);
  border: 2px solid var(--color-primary);
  border-radius: var(--radius-lg);
  padding: 0.75rem 1.5rem;
  font-weight: bold;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  color: var(--color-primary);
}
.voice-btn.listening {
  background-color: #ffeaea;
  border-color: #dc3545;
  color: #dc3545;
  animation: pulse 1.5s infinite;
}

@keyframes pulse {
  0% { transform: scale(1); }
  50% { transform: scale(1.05); }
  100% { transform: scale(1); }
}
</style>
