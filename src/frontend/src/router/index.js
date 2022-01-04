import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Signup from '../views/Signup.vue'
import ConfirmEmail from '../views/ConfirmEmail.vue'
import Activation from '../views/Activation.vue'
import ProfileAdmin from '../views/ProfileAdmin.vue'
import RequestsFromUsers from '../views/RequestsFromUsers'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/confirmEmail/:email',
    name: 'ConfirmEmail',
    component: ConfirmEmail
  },
  {
    path: '/activation/:activationCode/:email',
    name: 'Activation',
    component: Activation
  },
  {
    path: '/profileAdmin/:email',
    name: 'ProfileAdmin',
    component: ProfileAdmin
  },
  {
    path: '/requests/:email',
    name: 'RequestsFromUsers',
    component: RequestsFromUsers
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
