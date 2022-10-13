import { createApp } from 'vue'
import App from './App.vue'

import router from './router/index.js'

import ElementPlus from 'element-plus'

// 导入所有的el-icon图标
import * as ElIconModules from '@element-plus/icons-vue'

import '../node_modules/element-plus/dist/index.css'

const app = createApp(App)


app.use(router)
app.use(ElementPlus)
//  统一注册el-icon图标
for(let iconName in ElIconModules){
    app.component(iconName,ElIconModules[iconName])
}
app.mount('#app')

