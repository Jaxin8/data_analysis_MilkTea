import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useCounterStore = defineStore('counter', () => {

  //共享的数据(好比是vuex中的state)
  const count = ref(110)

  //类似于computed（好比是vuex中的getter）
  const doubleCount = computed(() => count.value * 2)

  //好比是vuex中的action
  function increment() {
    count.value++
  }
  function decrement() {
    count.value = count.value-1
  }

  return { count, doubleCount, increment, decrement }
})
