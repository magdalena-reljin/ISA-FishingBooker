import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { initializeApp } from "firebase/app";
import { getDatabase } from "firebase/database";
import {getAuth} from "firebase/auth"
import axios from "axios"
import config from "./configuration/config"

import "bootstrap/dist/css/bootstrap.min.css";

const firebaseConfig = {
    apiKey: "AIzaSyAwNdI28zKlIT2GG3pRLpH9aMmcbUftl04",
    authDomain: "isa15-d7c5c.firebaseapp.com",
    projectId: "isa15-d7c5c",
    storageBucket: "isa15-d7c5c.appspot.com",
    messagingSenderId: "16155551868",
    appId: "1:16155551868:web:4b30cbf90676b3a8a681f9",
    measurementId: "G-EN45NNPT4M"
  };
  
  // Initialize Firebase
const app = initializeApp(firebaseConfig);
axios.defaults.headers['Authorization']=config.requestHeader.headers.Authorization
getDatabase(app);
getAuth();
createApp(App).use(router).mount('#app')



