<template>
<AdminNav :username=email />

<div style="width: 90%; padding-left: 3%; ">
<h1>STATISTICS</h1>
<div class="row" style="padding-top: 3%; ">
        <div class="col-sm-6" style="padding-left: 2%;  padding-top: 1%; ">
           
           <div style="margin: 8%; background-color: #a8afbd; " class="card" >
            <div  class="card-body">
               <h5 class="card-title">General information</h5>
               <br>
               <div  class="row">
                 <div class="col-sm-6" style="padding-top: 2%; text-align: left;" >
                 <h6>OWNER RATING</h6>
                 </div>
                 <div class="col" style="padding: 1%;" >

                      <!--{{stats.ownerRating}}-->
                  <svg xmlns="http://www.w3.org/2000/svg" style="color: yellow;" width="18" height="18" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
  <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
</svg>
                </div>
                </div>

                <div  class="row">
                 <div class="col-sm-6" style="padding-top: 2%; text-align: left;" >
                 <h6>AVERAGE BOAT RATING</h6>
                 </div>
                 <div class="col" style="padding: 1%;" >
        
                <!--   {{stats.avgRating}}-->
                  <svg xmlns="http://www.w3.org/2000/svg" style="color: yellow;" width="18" height="18" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
  <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
</svg>
                </div>
                </div>

                <div  class="row">
                 <div class="col-sm-6" style="padding-top: 2%; text-align: left;" >
                 <h6>APP FEE </h6>
                 </div>
                
                 <div class="col" style="padding: 1%;" >
                       <!--{{stats.reservationsPointsDto.appProfitPercentage}}-->
                     <svg xmlns="http://www.w3.org/2000/svg" style="color: blue;" width="18" height="18" fill="currentColor" class="bi bi-percent" viewBox="0 0 16 16">
  <path d="M13.442 2.558a.625.625 0 0 1 0 .884l-10 10a.625.625 0 1 1-.884-.884l10-10a.625.625 0 0 1 .884 0zM4.5 6a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm0 1a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5zm7 6a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm0 1a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z"/>
</svg>
                 
                </div>
                </div>

                <div  class="row">
                 <div class="col-sm-6" style="padding-top: 2%; text-align: left;" >
                 <h6>CANCELATION FEE </h6>
                 </div>
                 <div class="col" style="padding: 1%;" >
                                    <!--     {{stats.reservationsPointsDto.cancelationFeePercentage}}-->
                      <svg xmlns="http://www.w3.org/2000/svg" style="color: blue;" width="18" height="18" fill="currentColor" class="bi bi-percent" viewBox="0 0 16 16">
  <path d="M13.442 2.558a.625.625 0 0 1 0 .884l-10 10a.625.625 0 1 1-.884-.884l10-10a.625.625 0 0 1 .884 0zM4.5 6a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm0 1a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5zm7 6a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm0 1a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z"/>
</svg>
                </div>
                </div>

                <div  class="row">
                 <div class="col-sm-6" style="padding-top: 2%; text-align: left;" >
                 <h6>POINTS FOR RESERVATION </h6>
                 </div>
                 <div class="col" style="padding: 1%;" >
                                                  <!--         {{stats.reservationsPointsDto.clientPoints}}-->
                  P
                     
                </div>
                </div>
                
               <br>
                <br>
        </div>
        </div>
  </div> 

        <div class="col">
        <h3>SUCCESSFULL RESERVATIONS</h3>
        <BarChart :chart-data="data" css-classes="chart-container" />
        </div>
</div>
    
     

     

</div>
</template>
<script>
import AdminNav from './AdminNav.vue'

   export default{
     components:{
       AdminNav,
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
