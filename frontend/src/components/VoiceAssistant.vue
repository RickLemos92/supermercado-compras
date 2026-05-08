<template>
  <button 
    class="voice-btn" 
    @click="toggleListening"
    :aria-label="isListening ? 'Parar escuta' : 'Buscar por voz'"
    :class="{ listening: isListening }"
  >
    <span v-if="isListening">🔴</span>
    <span v-else>🎤</span>
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
    recognition.interimResults = true

    recognition.onresult = (event) => {
      const transcript = event.results[0][0].transcript
      emit('result', transcript)
      
      if (event.results[0].isFinal) {
        isListening.value = false
      }
    }

    recognition.onerror = (event) => {
      console.error("Speech recognition error:", event.error)
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
  border: 1px solid var(--color-border);
  border-radius: 50%;
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.2s;
  font-size: 1.2rem;
}
.voice-btn:hover {
  border-color: var(--color-primary);
  color: var(--color-primary);
}
.voice-btn.listening {
  background-color: #ff4757;
  border-color: #ff4757;
  color: white;
  animation: pulse 1.5s infinite;
}

@keyframes pulse {
  0% { transform: scale(1); box-shadow: 0 0 0 0 rgba(255, 71, 87, 0.7); }
  70% { transform: scale(1.05); box-shadow: 0 0 0 10px rgba(255, 71, 87, 0); }
  100% { transform: scale(1); box-shadow: 0 0 0 0 rgba(255, 71, 87, 0); }
}
</style>
