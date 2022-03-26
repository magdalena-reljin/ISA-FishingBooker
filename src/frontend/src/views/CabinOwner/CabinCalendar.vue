<template>
 <CabinOwnerNavBar :username=email />
  <div>
   
    <div class="card bg-light text-light" style=" margin: 5%; margin-top: 2%">
    <div class="content">
      <FullCalendar :options="calendarOptions"    @select="handleSelect" class="calendar"  />
      <div class="info">
        <h2>Timetable</h2>

        <br>
        <br>
        <h5 style=" text-align: left;">SET AVAILABLE PERIOD</h5>
        <br>
        <div class="row">
          <div class="col" style="padding-top: 2%; text-align: left;" >
            <h5>From</h5>
          </div>
          <div class="col-sm-9" style="padding: 1%;" >
             <Datepicker   
           
           v-model="start" 
                
         >
          </Datepicker>
          </div>
        </div>
        <div class="row">
          <div class="col" style="padding-top: 2%; text-align: left;">
            <h5>To</h5>
          </div>
          <div class="col-sm-9" style="padding: 1%;">
             <Datepicker v-model="end" ></Datepicker>
          </div>
        </div>
           &nbsp;
           <div class="col" style="text-align: right; width: 100%; padding-top: 1%;">
           <button type="button" @click="setPeriod()" class="btn btn-light">Save</button>
          </div>
        <br>
        <br>
      
       <div style="padding: 5%;">
       <button style="width: 80%; " type="button" data-bs-toggle="modal" data-bs-target="#makeReservation" class="btn btn-primary btn-lg">Make reservation</button>
       </div>
       
       <div style="padding: 5%;">
       <button style="width: 80%;" type="button" class="btn btn-primary btn-lg">Create quick action</button>
      </div>
      </div>
    </div>


<vue-modality ref="myRef" title="Edit available period" hide-footer centered>

   <br>
        <div class="row">
          <div class="col" style="padding-top: 2%; text-align: left;" >
            <h6>From</h6>
          </div>
          <div class="col-sm-9" style="padding: 1%;" >
             <Datepicker   
           v-model="startEdit" 
                
         >
          </Datepicker>
          </div>
        </div>
        <div class="row">
          <div class="col" style="padding-top: 2%; text-align: left;">
            <h6>To</h6>
          </div>
          <div class="col-sm-9" style="padding: 1%;">
             <Datepicker  v-model="endEdit"></Datepicker>
          </div>
        </div>

  <br>
  <label style="color: red;" v-if="editDataIsNotValid==true">Please enter valid dates</label>
  <hr>
  <div style="text-align: left;">
  <h6>Add unavailable days</h6>
  </div>
         <br>
        <div class="row">
          <div class="col" style="padding-top: 2%; text-align: left;" >
            <h6>From</h6>
          </div>
          <div class="col-sm-9" style="padding: 1%;" >
             <Datepicker   
           
           v-model="unavailableStart" 
                
         >
          </Datepicker>
          </div>
        </div>
        <div class="row">
          <div class="col" style="padding-top: 2%; text-align: left;">
            <h6>To</h6>
          </div>
          <div class="col-sm-9" style="padding: 1%;">
             <Datepicker  v-model="unavailableEnd"></Datepicker>
          </div>
        </div>
        <div class="row">
                  <label v-if="reservationExists==true" style="color: red;">Reservation in this period already exists!</label>
        </div>
        <button type="button" class="btn btn-outline-dark">Add</button>
  <br>
  <hr>
   <div class="row">
        <div class="col">
           <button type="button" @click="clearModalEdit()" class="btn btn-secondary">Close</button>
        </div>
        <div class="col">
           <button type="button" class="btn btn-danger">Delete</button>
        </div>
        <div class="col">
           <button type="button" @click="editAvailablePeiod" class="btn btn-primary" >Save</button>
        </div>
    </div>
</vue-modality>




<div id="makeReservation" class="modal" tabindex="-1" >
  <div class="modal-dialog modal-xl modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" style="color: gray; ">Make reservation</h5>
      </div>
       <form @submit="createReservation" method="post" class="was-validated">
      <div class="modal-body">
        
             <div class="row">
           <div class=col>
             <div class="row">
               <div class="col-sm-3" style="color: gray; text-align: left;">Cabin</div>
               <div class="col-sm-10" style=" width: 100%; text-align: left;" > 
                  <input class="form-control"  v-model="cabinName" readonly>
               </div>
          </div>
            
            <br>
            
              <div class="row">
          <div class="col" style="padding-top: 2%; padding-left: 2.5%; text-align: left;">
            <h6 style="color: gray;">From</h6>
          </div>
          <div class="col-sm-9"  style="padding: 2.5%;">
             <Datepicker  v-model="startReservation" ></Datepicker>
          </div>
        </div>
               <div class="row">
          <div class="col" style="padding-top: 2%; padding-left: 2.5%; text-align: left;">
            <h6 style="color: gray;">To</h6>
          </div>
          <div class="col-sm-9"  style="padding: 2.5%;">
             <Datepicker  v-model="endReservation"></Datepicker>
          </div>
        </div>

 
  
        </div>
        <div class="col">
                
              <div class="row">
               <div class="col-sm-3" style="color: gray; text-align: left;">Client</div>
               <div class="col-sm-10" style=" width: 100%; text-align: left;" > 
                  <input class="form-control" type="text"  v-model="client" required>
               </div>
          </div>
          
               <div class="row">
        <div class="col form-group">
              <label style="color: gray; padding-top: 9%; " id="label">Price per night ($)</label>
              <input v-model="cabinDto.price" class="form-control" disabled>
              <div class="valid-feedback">Valid.</div>
              <div class="invalid-feedback">Please fill out this field.</div>
          </div>

          <div class="col form-group">
                    <label style="color: gray; padding-top: 9%; " id="label">Number of rooms </label>   
                    <input v-model="cabinDto.numOfRooms"  class="form-control" disabled>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                    </div> 

          <div class="col form-group">
                    <label style="color: gray; padding-top: 9%;  " id="label">Beds per room </label>   
                    <input v-model="cabinDto.bedsPerRoom"   class="form-control" disabled>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
          </div> 

          

          </div> 
          
          <div class="col">
                    <label style="color: gray;  padding-top: 1%; " id="label">Additional services </label>  
                     <Multiselect   style="color: gray; padding-bottom: 5%; " 
                           v-model="value"
                          mode="tags"
                          :close-on-select="false"
                          :searchable="true"
                          :create-option="false"
                          :options="options"
                          
                        />
          </div> 

              
     
        </div>
</div>
      <div  style="text-align: left;">
      <label style="color: red;" v-if="startReservation==null || endReservation==null || dateIsNotValid==true">Please enter valid dates.</label>
         </div>
      </div>
      <div class="modal-footer">
        <button @click="clearModal()" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="submit"  class="btn btn-success" data-bs-dismiss="modal">Create</button>
      </div>
       </form>
    </div>
  </div>
</div>



    </div>


  </div>

</template>

<script>

import CabinOwnerNavBar from './CabinOwnerNav.vue'
import { ref} from "vue";
import "@fullcalendar/core/vdom"; // solves problem with Vite
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import Datepicker from 'vue3-date-time-picker';
import 'vue3-date-time-picker/dist/main.css';
import dayjs from 'dayjs';
import '@shapla/vue-modal/dist/style.css';
import VueModality from 'vue-modality-v3'
import Multiselect from '@vueform/multiselect'
import "@vueform/multiselect/themes/default.css"
import axios from "axios";
   export default{
     components: {
           CabinOwnerNavBar,
           FullCalendar,
           Datepicker,
           VueModality,
           Multiselect
   },
   setup() {
          const startDate = ref();
          const endDate = ref();
          const myRef = ref(null)
          const openMyModal = () => { myRef.value.open() }
   
    return {
      myRef,
      openMyModal,
      startDate,
      endDate,
     
    
    };
  },

     data(){
       return{
         startReservation: null,
         endReservation: null,
         startEdit: null,
         endEdit: null,
         value: null,
        options: [
        ],
      unavailableStart: '',
      unavailableEnd: '',
      reservationExists: false,
      closeModal: false,
      disabledPickers: false,
      selectDisabled: false,
      currentEvent: "",
      selectData: [],
      entityType: "",
      calendarOptions: {
        plugins: [interactionPlugin, dayGridPlugin, timeGridPlugin],
        initialDate: new Date(),
        headerToolbar: {
          left: "prev,next today",
          center: "title",
          right: "dayGridMonth,timeGridWeek,timeGridDay"
        },
        selectable: true,
        eventClick: (arg)=>{
         this.$refs.myRef.open()
          this.startEdit=arg.event.start
          this.endEdit=arg.event.end
        },
        selectMirror: true,
        dayMaxEvents: true,
        initialView: "dayGridMonth",
        events: [],
      },
         email: '',
          user:{
           username: ''
         },
         cabinName: '',
          cabinDto: {
              id: null,
              name: '',
              description: '',
              numOfRooms: 1,
              bedsPerRoom: 1,
              rules: '',
              price: 1.0,
              addressDto: {
                  longitude: 0.0,
                  latitude: 0.0,
                  country: '',
                  city: '',    
                  streetAndNum: ''
              },
             additionalServices: [{
                 id: null,
                 name: '',
                 price: 0.0

             }],
             rating: 0.0,
             images: [{
                 id: null,
                 url: '',
                 cabin: ''

             }],
             ownerUsername: ''
         },
       availableCabinPeriod: [{
                 id: null,
                startDate: null,
                endDate: null,
                username: '',
                propertyId: null
            }],
            start: null,
            end: null,
            cabinId: null,
            additionalServicesToSend: [],
            totalPrice: 0,
            client: '',
            dateIsNotValid: false,
            editDataIsNotValid: false,
       }
     },
     mounted() {
       this.email = this.$route.params.email
       this.cabinName= this.$route.params.cabinName
       this.availableCabinPeriod.username=this.email
       this.getCabin()
       
     },
     methods: {
         getCabinsAvailablePeriod: function(){
               axios.post("http://localhost:8081/cabinsPeriod/getAvailablePeriod",this.cabinDto)
               .then(response => {
                  this.availableCabinPeriod=response.data
                     for( let newData of response.data ){
                                var start=newData.startDate
                                var end=newData.endDate
                                newData.startDate=this.setDate(start)
                                newData.endDate=this.setDate(end)
                              this.calendarOptions.events.push({id: newData.id ,title: 'Available', start: newData.startDate , end: newData.endDate })
                            }   
              })

         },
         getCabin: function(){
             this.cabinDto.name=this.cabinName
            
             axios.post("http://localhost:8081/cabins/findByName",this.cabinDto)
               .then(response => {
                        this.cabinDto=response.data
                         this.cabinId=this.cabinDto.id
                         for(let i =0; i< this.cabinDto.additionalServices.length; i++){
                               this.options.push({ 
                                  value: this.cabinDto.additionalServices[i].id,
                                  label: this.cabinDto.additionalServices[i].name+"-"+this.cabinDto.additionalServices[i].price+"$",
                                })
                         }
                         this.getCabinsAvailablePeriod();

             })
          },
          formatDate(formatDate) {
            const date = dayjs(formatDate);
            return date.format('YYYY-MM-DDTHH:mm:ss');
          },
          setPeriod: function(){
            if(!this.dataIsValid(this.start,this.end)){
                 this.$swal.fire({
                 position: 'top-end',
                  icon: 'error',
                 title: 'Please choose valid dates',
                 showConfirmButton: false,
                 timer: 1500
                })
              return;
            }
            this.availableCabinPeriod[0]=({
              startDate:  this.formatDate(this.start),
              endDate:  this.formatDate(this.end),
              username: this.email,
              propertyId: this.cabinId
             })
              this.start='',
              this.end=''
              axios.post("http://localhost:8081/cabinsPeriod/setAvailableCabinsPeriod",this.availableCabinPeriod)
                .then(response => {
                       this.$swal.fire({
                 position: 'top-end',
                  icon: 'success',
                 title: 'Your work has been saved',
               showConfirmButton: false,
               timer: 1500
                })
                this.$router.go()
                 return response;
                      
              })
            },
           setDate: function(newDate){
                    var date= new Date()
                    var splits =newDate.toString().split(",")
                    date.setDate( splits[1],splits[2], splits[0])
                    return new Date( parseInt(splits[0]), parseInt(splits[1])-1, parseInt(splits[2]),parseInt(splits[3]),parseInt(splits[4]))
           },
           createReservation: function(event){
                    event.preventDefault()
          
                    if(!this.dataIsValid(this.startReservation,this.endReservation)){
                      this.dateIsNotValid=true
                      return;
                    }


                    if(this.startReservation != null && this.endReservation!=null){
                                 this.additionalServicesAdded()
                                 this.calculatePrice()
                                axios
                                .post(
                                "http://localhost:8081/reservationCabin/ownerCreates",
                                {
                                id: null,
                                startDate: this.formatDate(this.startReservation),
                                endDate: this.formatDate(this.endReservation),
                                price: this.totalPrice,
                                cabinDto: this.cabinDto,
                                addedAdditionalServices: this.additionalServicesToSend,
                                clientUsername: this.client
                                })
                              .then((response) => {
                                      console.log(response)
                                       this.$swal.fire({
                                          position: 'top-end',
                                          icon: 'success',
                                          title: 'Successfull reservation!',
                                          showConfirmButton: false,
                                           timer: 1500
                                       })
                                      this.clearModal()
                                })
                              .catch((err) =>{
                                     console.log(err)
                                       this.$swal.fire({
                                        icon: 'error',
                                        title: 'Oops...',
                                        text: 'Something went wrong!',
                               })
                                    this.clearModal()
                              })
                                 
                    }
           },
           additionalServicesAdded: function(){
                  
                    this.additionalServicesToSend=[]
                    if(this.value != null){
                    for(let i=0; i < this.value.length ; i++){
                          console.log("selected "+this.value[i])
                          for(let j=0; j < this.cabinDto.additionalServices.length ; j++){
                            if(this.value[i] == this.cabinDto.additionalServices[j].id )
                               this.additionalServicesToSend.push(this.cabinDto.additionalServices[j])
                          }
                    }
                    console.log("len od nove liste  "+  this.cabinDto.additionalServices)
                    }

           },
           calculatePrice: function() {
              let numOfDays = this.getNumberOfDays(this.start, this.end);
              this.totalPrice = numOfDays * this.cabinDto.price;
              for (let i = 0; i < this.additionalServicesToSend.length; i++) {
                this.totalPrice += this.additionalServicesToSend[i].price;
              }
            },
            getNumberOfDays: function() {
               const date1 = new Date(this.startReservation);
               const date2 = new Date(this.endReservation);
               const oneDay = 1000 * 60 * 60 * 24;
               const diffInTime = date2.getTime() - date1.getTime();
               const diffInDays = Math.round(diffInTime / oneDay);
               return diffInDays;
           },
            dataIsValid(start,end){
              const date1 = new Date(start);
              const date2 = new Date(end);
              const currentDate = new Date();
              if((date1.getTime() - date2.getTime()) > 0){
                return false;
              }
              if(currentDate.getTime() - (date1.getTime()) > 0){
                return false;
              }
              return true;
            },
            clearModal: function(){
                 this.startReservation=null
                 this.endReservation=null
                 this.dateIsNotValid=false
                 this.client=''
                 this.value=[]
            },
            editAvailablePeiod: function(){
                 if(!this.dataIsValid(this.startEdit,this.endEdit)){
                    this.editDataIsNotValid=true
                    return
                 }

                 this.clearModalEdit()
            },
            clearModalEdit: function(){
                 this.unavailableStart=null
                 this.unavailableEnd=null
                 this.editDataIsNotValid=false

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
  background-image:  url("../../assets/IMG_3872.jpeg"); 
}
#nav a {
  font-weight: bold;
  color: #2c3e50;
}
#nav a.router-link-exact-active {
  color: #42b983;
}
.element {
  margin-bottom: 2rem;
}
.content {
  display: flex;
  justify-content: space-between;
}
.calendar {
  width: 70%;
  color: #1a252f;
  
  
}
.info {
  background: #1a252f;
  width: 28%;
  border-radius: 5px;
  border: 1px solid white;
  padding: 1rem;
}
</style>