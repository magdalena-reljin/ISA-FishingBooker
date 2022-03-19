<template>
<AdminNavbar :username=email />
  <div>
  
  </div>
 

</template>
 
<script>
//import axios from "axios";

import AdminNavbar from './AdminNav.vue'

   export default{
    components: {
    AdminNavbar 
    },
     data(){
       return{
         email: ''
       
       }
     },
     mounted() {
       this.email = this.$route.params.email
     },
      
    
  }

</script> 

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}
#logincard{
  width: 47%;
  background-image:  url("../../assets/IMG_3872.jpeg"); 
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
