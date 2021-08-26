import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Blob from '../src/views/vendor/Blob'
import Export2Excel from '../src/views/vendor/Export2Excel'
import 'xe-utils'
import VXETable from 'vxe-table'
import 'vxe-table/lib/style.css'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(VXETable)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
