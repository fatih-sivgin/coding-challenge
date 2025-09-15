<template>
  <div class="training-list">
    <h2>Trainings</h2>
    <ul>
      <li v-for="t in trainings" :key="t.id">
        {{ t.description }} - {{ t.speaker }} -
        {{ t.appointments?.length ?? 0 }} Termine
        <ul v-if="t.appointments?.length">
          <li v-for="a in t.appointments" :key="a.id">
            {{ a.beginDate }} bis {{ a.endDate }} ({{ a.numberOfParticipants }} Teilnehmer max.)
          </li>
        </ul>
      </li>
    </ul>

    <button @click="reload" :disabled="loading">Reload</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { TrainingsApi } from '../services/api.js'

const trainings = ref([])
const loading = ref(false)

async function loadTrainings() {
  loading.value = true
  trainings.value = await TrainingsApi.list()
  loading.value = false
}

// Daten direkt beim Mount laden
loadTrainings()

// Reload-Funktion
function reload() {
  loadTrainings()
}
</script>
