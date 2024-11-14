
import "@/assets/reset.css"

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPLUS from 'element-plus'
import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(ElementPLUS)
app.mount('#app')
