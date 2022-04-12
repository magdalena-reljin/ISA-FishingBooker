<template>
  

 <div class="page-content page-container" id="page-content">
    <div class="padding">
        <div class="row container d-flex justify-content-center">
            <div class="col">
                <div class="card user-card-full">
                    <div class="row m-l-0 m-r-0">
                        <div class="col-sm-4 bg-c-lite-green user-profile">
                            <div class="card-block text-center text-white">
                                <div class="m-b-25">
                                     <svg viewBox="0 0 36 36" fill="none" role="img" xmlns="http://www.w3.org/2000/svg" width="80" height="80"><title>Betty Ford</title><mask id="mask__beam" maskUnits="userSpaceOnUse" x="0" y="0" width="36" height="36"><rect width="36" height="36" rx="72" fill="#FFFFFF"></rect></mask><g mask="url(#mask__beam)"><rect width="36" height="36" fill="#708585"></rect><rect x="0" y="0" width="36" height="36" transform="translate(-1 -1) rotate(205 18 18) scale(1.1)" fill="#dedde8" rx="36"></rect><g transform="translate(-5 -3) rotate(5 18 18)"><path d="M15 20c2 1 4 1 6 0" stroke="#000000" fill="none" stroke-linecap="round"></path><rect x="14" y="14" width="1.5" height="2" rx="1" stroke="none" fill="#000000"></rect><rect x="20" y="14" width="1.5" height="2" rx="1" stroke="none" fill="#000000"></rect></g></g></svg>
                                     </div>
                                <h6 class="f-w-600">{{fullName}}</h6>
                                <p>{{MyUser.username}}</p> <i class=" mdi mdi-square-edit-outline feather icon-edit m-t-10 f-16"></i>
                                <p style="color: orange;">
                                    <svg xmlns="http://www.w3.org/2000/svg" style="color: orange;" width="32" height="32" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
  <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
</svg>
                                    5.0
                                </p> <i class=" mdi mdi-square-edit-outline feather icon-edit m-t-10 f-16"></i>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <div class="card-block">
                                <h6 class="m-b-20 p-b-5 b-b-default f-w-600">Information</h6>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <p class="m-b-10 f-w-600">Location</p>
                                        <h6 class="text-muted f-w-400">{{MyUser.address.city}},{{MyUser.address.country}}</h6>
                                    </div>
                                    <div class="col-sm-6">
                                        <p class="m-b-10 f-w-600">Account type</p>
                                        <h6 class="text-muted f-w-400">{{MyUser.role}}</h6>
                                    </div>
                                </div>
                                <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600">Account rank</h6>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <p class="m-b-10 f-w-600">Status</p>
                                        <h6 class="text-muted f-w-400">GOLD</h6>
                                    </div>
                                    <div class="col-sm-6">
                                        <p class="m-b-10 f-w-600">Points</p>
                                        <h6 class="text-muted f-w-400">56</h6>
                                    </div>
                                </div>

                                <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600">Comments about user</h6>
                                <div class="row">
                                    <div class="col" style="text-align: left;">
                                        <p >@co.gmail.com </p>
                                        <p ><svg xmlns="http://www.w3.org/2000/svg" style="color: orange;" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
                                            <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
                                        </svg> 5.0</p>
                                        <p class="m-10 f-w-600">Najbolji korisnik. Sve preporuke</p>
                                        <hr>
                                    </div>
                                </div>
                               

                            </div>
                        </div>
                    </div>
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
      
    email: '',
        user: {
         username: '',
         firstname: '',
         lastname: '',
         phoneNum: '',
         address: '',
         rating: '',

        },
        fullName: ''
    };
  },
  props: {
    emailProp: String,
    
   
  },
  mounted() {
    this.getUser()
  },
  methods: {
     
        getUser: function(){
 
            this.email=this.$props.emailProp
            if(this.$props.emailProp!=''){
            axios.get("http://localhost:8081/userc/getUserByUsername/"+this.$props.emailProp +"/")
               .then(response => {
                       
                        this.user=response.data
                        this.fullName=this.user.firstname + " "+ this.user.lastname
                        console.log("JA SAM   "+response.data.username)
                       
              })
             
             
            }
             return this.user
      },
       
 },
 
   computed: {
      MyUser: function () {
          if(this.user.username=='' && this.$props.emailProp!=undefined)
            this.getUser()
      return this.user;
    },


   
      
   }

  
  
}
</script>


<style scoped>
body {
    background-color: #7070a4
}

.padding {
    padding: 5% !important;
    padding-left: 10% !important
}

.user-card-full {
    overflow: hidden
}

.card {
    border-radius: 5px;
    -webkit-box-shadow: 0 1px 20px 0 rgba(69, 90, 100, 0.08);
    box-shadow: 0 1px 20px 0 rgba(69, 90, 100, 0.08);
    border: none;
    margin-bottom: 30px
}

.m-r-0 {
    margin-right: 0px
}

.m-l-0 {
    margin-left: 0px
}

.user-card-full .user-profile {
    border-radius: 5px 0 0 5px
}

.bg-c-lite-green {
    background: linear-gradient(to right, #133d70, #030b2e)
}

.user-profile {
    padding: 10px 0
}

.card-block {
    padding: 1.25rem
}

.m-b-25 {
    margin-bottom: 25px
}

.img-radius {
    border-radius: 5px
}

h6 {
    font-size: 14px
}

.card .card-block p {
    line-height: 25px
}

@media only screen and (min-width: 1400px) {
    p {
        font-size: 14px
    }
}

.card-block {
    padding: 1.25rem
}

.b-b-default {
    border-bottom: 1px solid #e0e0e0
}

.m-b-20 {
    margin-bottom: 20px
}

.p-b-5 {
    padding-bottom: 5px !important
}

.card .card-block p {
    line-height: 25px
}

.m-b-10 {
    margin-bottom: 10px
}

.text-muted {
    color: #919aa3 !important
}

.b-b-default {
    border-bottom: 1px solid #e0e0e0
}

.f-w-600 {
    font-weight: 600
}

.m-b-20 {
    margin-bottom: 20px
}

.m-t-40 {
    margin-top: 20px
}

.p-b-5 {
    padding-bottom: 5px !important
}

.m-b-10 {
    margin-bottom: 10px
}

.m-t-40 {
    margin-top: 20px
}

.user-card-full .social-link li {
    display: inline-block
}

.user-card-full .social-link li a {
    font-size: 20px;
    margin: 0 10px 0 0;
    -webkit-transition: all 0.3s ease-in-out;
    transition: all 0.3s ease-in-out
}
</style>

