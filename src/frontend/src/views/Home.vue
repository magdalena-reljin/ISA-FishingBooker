<template>
  <div>
     <nav  class="navbar navbar-fixed-top navbar-expand" style="background-color: #1d7ac9; list-style: none;">
  <div class="container-fluid" style="background-color: #1d7ac9;">
  <a class="navbar-brand">
  <img src="../assets/logoF1.png" alt="" width="194" height="80" >
</a>

<ul class="navbar-nav me-auto mb-2 mb-lg-0">

<li class="nav-item">
  <a style="color: white;" class="nav-link active" aria-current="page" href="http://localhost:8080/login">LOGIN</a>
</li>
<li class="nav-item">
  <a style="color: white;" class="nav-link active" href="http://localhost:8080/signup">SIGN UP</a>
</li>
</ul>


  


  




  </div>

</nav>
  
<input @change="uploadImage" type="file">
   
      
   <div align="center" vertical-align="center" style="border-style:solid; width: 50%; height: 408px">
    <OpenLayersMap  :coordinates=[21.0059,44.0165]  />


   </div>
    

  <h1>ovo je pick location</h1>
  

   <div align="center" vertical-align="center" style="border-style:solid; width: 50%; height: 408px">
    <PickLocationMap :coordinates=[21.0059,44.0165] />
    

    
   </div>


   <div class="mb-3">
             <label id="label" for="formFileMultiple" class="form-label">Import pictures</label>
             <input  @change="onFileSelected" class="form-control" type="file"  multiple>
    </div>
    <button @click="preuzmi()">PREUZMI SLIKE</button>

   
  </div>

</template>



<script>
    import axios from "axios";
      import OpenLayersMap from '../components/OpenLayersMap.vue'
      import PickLocationMap from '../components/PickLocationMap'
      import { getStorage,ref1, uploadBytesResumable } from "firebase/storage";
      import { getDatabase,ref, set } from "firebase/database";
      import {getAuth} from "firebase/auth";
export default {
  components: {
    OpenLayersMap,
    PickLocationMap
  },

  data() {
    return {
      photo:{
          id: 0,
          url: '',
          title: '',
      },
      selectedFile: null,
      cela: '',
      mojEvent: null
    
       
    };
  },
  mounted() {
    
  },
  methods: {
    uploadImage(e){
     
      var file=e.target.files[0]
       console.log('usaoooooooooooo'+file.name)
      const storage = getStorage();
      const mountainImagesRef = ref1(storage, 'images/'+file.name);
      uploadBytesResumable(mountainImagesRef, file);

 
       this.saveUrlToRealTimeDB(file);
    },
    saveUrlToRealTimeDB: function(file){
        const auth= getAuth();
        const id=auth.photo.id.uid;
        const db = getDatabase();
        set(ref(db, 'images/' +id), {
        image: file.url,
        title: file.name,
        }); 
     
    },
    updateLocation: function(latitude,longitude){
        console.log("POGODIO");
         axios.get("https://nominatim.openstreetmap.org/reverse", {
      params: {
        lat: longitude,
        lon: latitude,
        format: "json",
     },
     })
    .then((response) => {
            const { address } = response.data;
            var flag = false;
            var road
            var street
            var number
            var town
            var zipCode
            if (address) {
    
                if (address.road) {
                    road = address.road;
      
                    flag = true;
                } else if (address.street) {
                    street = address.street;
                    flag = true;
                }
                if (flag && address["house-number"]) {
                    number = address["house-number"];
                }
                else if (flag && address["house_number"]) {
                    number = address["house_number"];
                }
                if (flag && address.town) {
                    town = address.town;
                }
                else if (flag && address.city) {
                    town = address.city;
                }
                if (flag && address.postCode) {
                    zipCode = address.postCode;
                }
                else if (flag && address.postcode) {
                    zipCode = address.postcode;
                }
                
            }
            
                    this.cela = street  + " " + number + ", " + town + road + zipCode
                
              console.log("adresa "+ this.cela)

        })
    },
    onFileSelected: function(event){
              console.log("lepo"+event)
              this.mojEvent=event
              

    },
    preuzmi: function(){
       
         for( var i = 0; i <  this.mojEvent.target.files.length; i++ ){
                 let formData = new FormData();
                    let file =  this.mojEvent.target.files[i];
                    formData.append('file', file);
                     axios.post("http://localhost:8081/firebase/profile/pic",formData, {
                       headers: {
                        'Content-Type': 'multipart/form-data'
                    },
                    })
                    .then(response => {
                      
                      return response;
                    })


          }


    },
    
    
    

    









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

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
