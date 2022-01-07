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



   
  </div>

</template>



<script>

      
      import { getStorage,ref1, uploadBytesResumable } from "firebase/storage";
      import { getDatabase,ref, set } from "firebase/database";
      import {getAuth} from "firebase/auth";
export default {

  data() {
    return {
      photo:{
          id: 0,
          url: '',
          title: '',
      },
      selectedFile: null,
       
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

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
