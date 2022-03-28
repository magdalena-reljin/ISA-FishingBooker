<template>
  <BoatOwnerNav :username=email />
  <Calendar :email=email />

</template>

<script>
 //import axios from "axios";
 import BoatOwnerNav from './BoatOwnerNav.vue'
 import Calendar from '../../components/OwnerCalendar.vue'

   export default{
    
     components: {
         BoatOwnerNav,
         Calendar
  
     },
     data(){
       return{
       email: '',
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
