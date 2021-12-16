<template>
  <div>
    <h1>OVO JE STRANICA ZA AKTIVACIJU</h1>

    
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: "ConfirmEmail",
  data() {
    return {
       activationDTO:{
         activationCode: '',
         email: '',
       }
    };
  },
  mounted() {
    this.activationDTO.activationCode = this.$route.params.activationCode
    this.activationDTO.email = this.$route.params.email
    console.log("ovo je aktivacioni kod: "+ this.activationDTO.activationCode)
    console.log("ovo je email: "+ this.activationDTO.email)
     axios
     .post("http://localhost:8081/auth/activate",this.activationDTO)
     .then((response) => {
      return response; 
     });


     
  }
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

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>