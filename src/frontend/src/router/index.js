import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Signup from '../views/Signup.vue'
import AccountAlert from '../views/AccountAlert.vue'
import Activation from '../views/Activation.vue'
import ProfileAdmin from '../views/ProfileAdmin.vue'
import RequestsFromUsers from '../views/RequestsFromUsers'
import ReasonForDenying from '../views/ReasonForDenying'
import CabinOwnerHome from '../views/CabinOwner/CabinOwnerHome'
import BoatOwnerHome from '../views/BoatOwnerHome'
import FishingInstructorHome from '../views/FishingInstructorHome'
import EditProfile from '../views/EditProfile'
import AllUsers from '../views/AllUsers'
import AddAdmin from '../views/AddAdmin'
import ChangedPasswordInfo from '../views/ChangedPasswordInfo'
import ChangedPassword from '../views/ChangedPassword'
import DeleteAccount from '../views/DeleteAccount'
import AddNewCabin from '../views/CabinOwner/AddNewCabin'
import CabinProfile from '../views/CabinOwner/CabinProfile'


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
    path: '/accountAlert/:email',
    name: 'AccountAlert',
    component: AccountAlert
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
  {
    path: '/reasonForDenying/:email/:person',
    name: 'ReasonForDenying',
    component: ReasonForDenying
  },
  {
    path: '/cabinOwnerHome/:email',
    name: 'CabinOwnerHome',
    component: CabinOwnerHome
  },
  {
    path: '/boatOwnerHome/:email',
    name: 'BoatOwnerHome',
    component: BoatOwnerHome
  },
  {
    path: '/fishingInstructorHome/:email',
    name: 'FishingInstructorHome',
    component: FishingInstructorHome
  },
  {
    path: '/editProfile/:role/:email',
    name: 'EditProfile',
    component: EditProfile
  },
  {
    path: '/allUsers/:email',
    name: 'AllUsers',
    component: AllUsers
  },
  {
    path: '/addAdmin/:email',
    name: 'AddAdmin',
    component: AddAdmin
  },
  {
    path: '/changedPasswordInfo/:email',
    name: 'ChangedPasswordInfo',
    component: ChangedPasswordInfo
  },
  {
    path: '/changedPassword/:email',
    name: 'ChangedPassword',
    component: ChangedPassword
  },
  {
    path: '/deleteAccount/:email',
    name: 'DeleteAccount',
    component: DeleteAccount
  },
  {
    path: '/addNewCabin/:email',
    name: 'AddNewCabin',
    component: AddNewCabin
  },
  {
    path: '/cabinProfile/:email/:cabinName',
    name: 'CabinProfile',
    component: CabinProfile
  },
  ]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
