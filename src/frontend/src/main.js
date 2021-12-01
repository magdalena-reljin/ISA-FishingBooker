import Vue from 'vue';
import App from './App.vue';
import VueRouter from 'vue-router';
import "bootstrap/dist/css/bootstrap.min.css";
import Home from "@/components/Home";
import Login from "@/components/Login";



Vue.config.productionTip = false
Vue.use(VueRouter);

const routes=[
  {path: '/', component: Home},
  {path: '/login', component: Login},
]

const router = new VueRouter({
  routes,
  mode: 'history'
});

new Vue({
  el: '#app',
  router,
  render: h => h(App),
}).$mount('#app')