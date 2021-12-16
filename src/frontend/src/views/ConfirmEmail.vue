<template>
  <div id="mainc">
    <nav  class="navbar navbar-fixed-top navbar-expand" style="background-color: #1d7ac9; list-style: none;">
      <div class="container-fluid" style="background-color: #1d7ac9;">
      <a class="navbar-brand" href="http://localhost:8080/">
      <img src="../assets/logoF1.png" alt="" width="194" height="80" >
      </a>
      </div>
    </nav>

   <br>
     <h1>aaaaaaaaaaaaaaaaaaaaaaaaaaaaa</h1>


  </div>

</template>

<script>



export default {
  data() {
    return {
       user: {
         id: null,
         email:'',
         password: '',
         firstname: '',
         lastname: '',
         phoneNum: '',
         address: {
              longitude: 0,
             latitude: 0,
             country: '',
             city: '',
           streetAndNum: ''
         },
         registrationReason: ''
        

       }
    };
  },
  mounted() {
    
  },
  methods: {
    
  }
};
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

#label{
  float: left;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
#mainc{

  background-image: linear-gradient(#278ade,black);
}
</style>
