<template>
  <div>
    <div v-if="loading" class="small">Loading…</div>
    <div v-if="error" class="small" style="color:#b91c1c">Error: {{ error }}</div>

    <div v-if="!loading && trainings.length === 0" class="small">No trainings found.</div>

    <div v-for="t in trainings" :key="t.id" class="training" @click="$emit('select', t)">
      <div style="display:flex; justify-content:space-between; align-items:center">
        <div>
          <div><strong>{{ t.description }}</strong></div>
          <div class="small">Speaker: {{ t.speaker }} • Preis: {{ t.price }}</div>
        </div>
        <div class="small">{{ t.appointments?.length ?? 0 }} Termine</div>
      </div>
    </div>

    <div style="margin-top:12px; display:flex; gap:8px;">
      <button class="btn" @click="$emit('loadMore')">Load more</button>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue'
const props = defineProps({
  trainings: { type: Array, default: () => [] },
  loading: { type: Boolean, default: false },
  error: { type: [String, Object], default: null }
})
</script>
