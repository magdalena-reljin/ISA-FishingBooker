<template>
  <div>
    <nav  class="navbar navbar-fixed-top navbar-expand" style="background-color: #1d7ac9; list-style: none;">
      <div class="container-fluid" style="background-color: #1d7ac9;">
      <a class="navbar-brand"  href="http://localhost:8080/" >
      <img src="../assets/logoF1.png" alt="" width="194" height="80" >
      </a>
      </div>
    </nav>
    &nbsp;  &nbsp;

   
   <div class="row justify-content-center" style="width: 100%;">
   <div class="col-md-6">
   <div class="card card-outline-secondary">
   <div class="card-header">
               <h3 style="text-align:center;" class="mb-0">Personal info</h3>
   </div>
   <div class="card-body">
    <form  @submit="editData" method='post' class="was-validated">
        <div class="form-group">
            <label>First name:</label>
            <input class="form-control" type="text" v-model="userRequestDto.firstname" disabled>
          
        </div>

        <div class="form-group">
          <label>Last name:</label>
          <input class="form-control" type="text" v-model="userRequestDto.lastname" >
          
        </div>

        <div class="form-group">
          <label>Email:</label>
          <input class="form-control" type="text" v-model="userRequestDto.username" disabled>

        </div>
        <div class="form-group">
          <label>Account type:</label>
          <input class="form-control" type="text" v-model="userRequestDto.role" disabled>

        </div>

        <div class="form-group">
            
          <label>Phone number:</label>
          <input class="form-control" type="text" v-model="userRequestDto.phoneNum" required>
          <div class="valid-feedback">Valid.</div>
          <div class="invalid-feedback">Please fill out this field.</div>
        </div>

        <div class="form-group">
            
          <label>Country:</label>
          <input class="form-control" type="text" v-model="userRequestDto.address.country" required>
          <div class="valid-feedback">Valid.</div>
          <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group">
            
          <label>City:</label>
          <input class="form-control" type="text" v-model="userRequestDto.address.city" required>
          <div class="valid-feedback">Valid.</div>
          <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group">
            
          <label>Street and num:</label>
          <input class="form-control" type="text" v-model="userRequestDto.address.streetAndNum" required>
          <div class="valid-feedback">Valid.</div>
          <div class="invalid-feedback">Please fill out this field.</div>
        </div>

        <div class="form-group">
            
          <label>Longitude:</label>
          <input class="form-control" type="text" v-model="userRequestDto.address.longitude" required>
          <div class="valid-feedback">Valid.</div>
          <div class="invalid-feedback">Please fill out this field.</div>
        </div>
         <div class="form-group">
            
          <label>Latitude:</label>
          <input class="form-control" type="text" v-model="userRequestDto.address.latitude" required>
          <div class="valid-feedback">Valid.</div>
          <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        

        <br>
        <div class="row">
             <div class="col">
             <div class="form-group">
             <button  type="submit" class="btn btn-outline-dark">Save changes</button>
             </div>
             </div>
             <div class="col">
             <div class="form-group">
             <button @click="changePassword()" type="button" class="btn btn-outline-danger">Change password</button>
            </div>
            </div>
            <div v-if="role !='admin'" class="col">
             <div class="form-group">
             <button  @click="deleteAccount()" type="button" class="btn btn-outline-danger">Delete account</button>
            </div>
            </div>
        </div>
    </form>
</div>
</div>
</div>
</div>
</div>
</template>
<script>


import axios from "axios";
   export default{
     data(){
       return{
           email: '',
           role: '',

           userRequestDto: {
            id: 0,
            username:'',
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
            registrationReason: '',
            role: ''
           }
       
       }
     },
     mounted() {
          this.role = this.$route.params.role
          this.email = this.$route.params.email
          this.loadData();
      },
     methods: {
       
       loadData: function(){
           this.userRequestDto.username=this.email
                 axios.post(process.env.VUE_APP_BACKEND_URL+"auth/findByEmail/",this.userRequestDto)
                 .then(response => {
                        this.userRequestDto = response.data
                        this.userRequestDto.role=this.role
                        return response;
                   })

       },
       editData: function(event){
            event.preventDefault();

            axios.post(process.env.VUE_APP_BACKEND_URL+"auth/editUser/",this.userRequestDto)
                 .then(response => {
                        this.$swal.fire({
                           position: 'top-end',
                           icon: 'success',
                           title: 'Your work has been saved',
                           showConfirmButton: false,
                           timer: 1500
                         })
                        if(this.role == 'admin')
                            this.$router.push('/profileAdmin/'+this.email); 
                        else if(this.role == 'cabinOwner')
                            this.$router.push('/cabinOwnerHome/'+this.email); 
                        else if(this.role == 'boatOwner')
                            this.$router.push('/boatOwnerHome/'+this.email); 
                        else if(this.role == 'fishingInstructor')
                            this.$router.push('/fishingInstructorHome/'+this.email); 
                        else if (this.role == 'client')
                            this.$router.push('/clientHome/'+this.email); 
                        return response;
                   })

       },
       changePassword: function(){
            this.$router.push('/changedPassword/'+this.email); 
       },
       deleteAccount: function(){
            this.$router.push('/deleteAccount/'+this.email); 
       }
       
      
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
#logincard{
  width: 47%;
  background-image:  url("../assets/IMG_3872.jpeg"); 
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
