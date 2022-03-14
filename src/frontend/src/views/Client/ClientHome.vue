<template>
  <ClientNavbar/>
  <ClientCabins/>
</template>

<script>
import ClientNavbar from './ClientNavbar'
import ClientCabins from './ClientCabins'

   export default{
     components:{
       ClientNavbar,
       ClientCabins
     },
     data(){
       return{
         email: '',     
       }
     },
     mounted() {
       this.email = this.$route.params.email

     },
     methods: {
     
    },
    computed: {

  },
  }

</script> 

<style>

</style>
