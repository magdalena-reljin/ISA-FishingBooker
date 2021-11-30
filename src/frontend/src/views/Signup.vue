<template>
  <div id="mainc">
    <nav  class="navbar navbar-fixed-top navbar-expand" style="background-color: #1d7ac9; list-style: none;">
      <div class="container-fluid" style="background-color: #1d7ac9;">
      <a class="navbar-brand" href="http://localhost:8081/">
      <img src="../assets/logoF1.png" alt="" width="194" height="80" >
      </a>
      </div>
    </nav>

   <br>
      <div class="container"  >
      
      <div class="row justify-content-center"  >
      <div class="col-md-6" >
      <div class="card" >
      <header id="header" class="card-header" >
          <h4 class="card-title mt-2">Sign up</h4>
      </header>
      <article class="card-body"  >
      <form method='post' class="was-validated">
          <div class="form-row">
              <div class="col form-group">
                  <label id="label">Name </label>   
                    <input  type="text" class="form-control" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
              </div> 
              <div class="col form-group">
                  <label id="label">Surname</label>
                    <input  type="text" class="form-control" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
              </div> 
          </div>
          <div class="form-group">
              <label id="label">Email</label>
              <input type="email" class="form-control" placeholder="someone@gmail.com"   required>
              <div class="valid-feedback">Valid.</div>
              <div class="invalid-feedback">Please fill out this field.</div>
          </div>
          <div class="col form-group">
          <label id="label">Password </label>   
            <input type="password" class="form-control"   required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
      </div> 
          <div class="col form-group">
          <label id="label">Confirm password </label>   
            <input type="password" class="form-control"   required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
      </div> 

       <div class="col form-group">
                  <label id="label">Street and number </label>   
                    <input  type="text" class="form-control" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
        </div> 

        <div class="col form-group">
                  <label id="label">City </label>   
                    <input  type="text" class="form-control" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
         </div> 

        <div class="col form-group">
                  <label id="label">Country </label>   
                    <input  type="text" class="form-control" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
        </div> 

        <div class="col form-group">
                  <label id="label">Phone number </label>   
                    <input  type="text" class="form-control" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
        </div> 

        <div>

        <label id="label">Choose account type</label>   
        <select v-model="selectedClient" class="form-select form-select-sm" aria-label=".form-select-sm example" required>
                <option >CLIENT</option>
                <option >CABIN OWNER</option>
                <option >BOAT OWNER</option>
                <option >FISHING INSTRUCTOR</option>
        </select>
             </div>

   <div v-if="selectedClient != 'CLIENT'"  class="form-group">
    <label for="exampleFormControlTextarea1">Example textarea</label>
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" required></textarea>
  </div>
   
          <br>
          <div class="form-row">
          <div class="row">
          <div class="col-12">
 
  
  </div>
      </div>
          </div> 
         
          <div style="color:red;"></div>
          <br>
          <div class="form-group">
              <button type="submit" class="btn btn-primary"> Sign up  </button>
          </div>                                                
      </form>
      </article> 
      <div class="border-top card-body text-center" >Have an account? <a href="http://localhost:8081/login">Log In</a></div>
      </div> 
      </div> 
      
      </div> 
      
      
      </div> 
     




  </div>

</template>

<script>

import axios from "axios";

export default {
  data() {
    return {
       selectedClient: 'CLIENT',
       user: {
         id: null,
         role: '',

       }
    };
  },
  mounted() {
    
  },
  methods() {
    register: {
    
        
        axios
        .post("api/registration",)
        .then((response) => {
        return response; 
         });
    }
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
