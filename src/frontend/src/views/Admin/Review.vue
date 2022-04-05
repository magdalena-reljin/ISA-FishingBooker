<template>
<AdminNavbar :username=email />
    
  <div>
     
<h1>ALL REPORTS </h1>
&nbsp;  
<h2 v-if="reports.lenght==0" > No reports.</h2>
<table v-else class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Owner</th>
      <th scope="col">Comment</th>
      <th scope="col">Client</th>
      <th scope="col">Suggest client gets 1 penal</th>
       <th>&nbsp;</th>
      

    </tr>
  </thead>
   
  <tbody>
    <tr  v-for="(rep,index) in reports" :key="index">
       <th scope="row">{{index +1}}</th>
      <td>{{rep.ownerUsername}}</td>
      <td>{{rep.comment}}</td>
      <td>{{rep.clientUsername}}</td>
      <td v-if="rep.badComment == true">YES</td>
      <td>
        <tr>
         <div class="row">
            <div class="col "><button @click="answer(rep)" data-bs-toggle="modal" data-bs-target="#staticBackdrop"
               type="button" class="btn btn-outline-success" >ANSWER</button></div>
     
        </div>
       
        </tr>          
                  
      </td>
      
      
    </tr>
  </tbody>
</table>


  </div>
 

</template>
 
<script>
//import axios from "axios";

import AdminNavbar from './AdminNav.vue'
import axios from 'axios'

   export default{
    components: {
    AdminNavbar 
    },
     data(){
       return{
         email: '',
         reports: []
       
       }
     },
     mounted() {
       this.email = this.$route.params.email
              axios.get("http://localhost:8081/reports/getAllReports")
            .then(response => {this.reports = response.data
              
              })
             .catch(error => {
                 this.errorMessage = error.message;
                 console.error("There was an error!", error);
           });
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
