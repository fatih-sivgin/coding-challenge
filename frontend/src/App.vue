<template>
  <div class="container">
    <div class="header">
      <h1>Trainings</h1>
      <div>
        <button class="btn" @click="reload" :disabled="loading">Reload</button>
      </div>
    </div>

    <div class="card">
      <TrainingList
        :trainings="trainings"
        :loading="loading"
        :error="error"
        @loadMore="onLoadMore"
        @select="onSelect"
      />

      <div v-if="selected" style="margin-top:16px">
        <h3>Details</h3>
        <div class="card">
          <p><strong>{{ selected.description }}</strong></p>
          <p class="small">Preis: {{ selected.price }}</p>
          <p class="small">Speaker: {{ selected.speaker }}</p>
          <div v-if="selected.appointments?.length">
            <h4>Termine</h4>
            <ul>
              <li v-for="a in selected.appointments" :key="a.id">{{ a.beginDate }} – {{ a.endDate }} ({{ a.numberOfParticipants }})</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import TrainingList from './components/TrainingList.vue'
import { TrainingsApi } from './services/api'

const trainings = ref([])
const loading = ref(false)
const error = ref(null)
const page = ref(0)
const size = ref(10)
const selected = ref(null)

async function load(reset = false) {
  loading.value = true
  error.value = null
  try {
    if (reset) { page.value = 0; trainings.value = [] }
    const res = await TrainingsApi.list({ page: page.value, size: size.value })

    // support both Page response and plain list
    if (Array.isArray(res)) {
      trainings.value = reset ? res : trainings.value.concat(res)
    } else if (res?.content) {
      trainings.value = reset ? res.content : trainings.value.concat(res.content)
    } else {
      // unknown response
      trainings.value = reset ? [] : trainings.value
    }
  } catch (e) {
    error.value = e.message || String(e)
  } finally {
    loading.value = false
  }
}

function reload() { load(true) }
function onLoadMore() { page.value += 1; load(false) }
function onSelect(t) { selected.value = t }

onMounted(() => load(true))
</script>