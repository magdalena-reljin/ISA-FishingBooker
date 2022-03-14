<template>
  <ClientNavbar/>

<ul class="nav justify-content-center" style="background-color: #0f5591;">

      <li class="nav-item">
      <a style="color: white;" class="nav-link" @click="changeDisplay('cabins')"> CABINS</a>
      </li>

      <li class="nav-item">
      <a style="color: white;" class="nav-link" @click="changeDisplay('boats')">  BOATS</a>
      </li>
       
       <li class="nav-item">
      <a style="color: white;"  class="nav-link" @click="changeDisplay('instructors')"> INSTRUCTORS</a>
      </li>

     </ul>

  <template v-if="cabinsShown"> 
    <ClientCabins/>
  </template>
  <template v-if="boatsShown"> 
     <ClientBoats/>
  </template>
</template>

<script>
import ClientNavbar from './ClientNavbar'
import ClientCabins from './ClientCabins'
import ClientBoats from './ClientBoats'

   export default{
     components:{
       ClientNavbar,
       ClientCabins,
       ClientBoats
     },
     data(){
       return{
         email: '', 
         cabinsShown: true,
         boatsShown: false, 
         instructorsShown: false,   
       }
     },
     mounted() {
       this.email = this.$route.params.email

     },
     methods: {
     changeDisplay: function(toDisplay){
       if(toDisplay === 'cabins'){
         this.cabinsShown = true;
         this.boatsShown = false;
         this.instructorsShown = false;
       }else if(toDisplay === 'boats'){
         this.cabinsShown = false;
         this.boatsShown = true;
         this.instructorsShown = false;
       }else{
         this.cabinsShown = false;
         this.boatsShown = false;
         this.instructorsShown = true;
       }
     }
    },
    computed: {

  },
  }

</script> 

<style>

</style>
