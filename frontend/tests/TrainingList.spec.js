import { describe, it, expect, vi } from 'vitest'
import { mount } from '@vue/test-utils'
import flushPromises from 'flush-promises'
import TrainingList from '../src/components/TrainingList.vue'
import { TrainingsApi } from '../src/services/api.js'

// Mock der API
vi.mock('../src/services/api.js', () => ({
  TrainingsApi: {
    list: vi.fn(() => Promise.resolve([
      { id: '1', description: 'Training 1', speaker: 'Dr. A' },
      { id: '2', description: 'Training 2', speaker: 'Dr. B' }
    ]))
  }
}))

describe('TrainingList', () => {
  it('renders trainings from API', async () => {
    const wrapper = mount(TrainingList)

    // Warten bis die Promise aus setup() aufgelöst ist
    await flushPromises()

    const items = wrapper.findAll('li')
    expect(items).toHaveLength(2)
    expect(items[0].text()).toContain('Training 1')
    expect(items[0].text()).toContain('Dr. A')
    expect(items[1].text()).toContain('Training 2')
    expect(items[1].text()).toContain('Dr. B')
  })
})
