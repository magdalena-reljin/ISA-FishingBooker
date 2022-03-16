<template>
  <div>
    <nav
      class="navbar navbar-fixed-top navbar-expand"
      style="background-color: #1d7ac9; list-style: none"
    >
      <div class="container-fluid" style="background-color: #1d7ac9">
        <a class="navbar-brand" href="http://localhost:8080/">
          <img src="../../assets/logoF1.png" alt="" width="194" height="80" />
        </a>
      </div>
    </nav>

    <ul class="nav justify-content-center" style="background-color: #0b477b">
      <li class="nav-item">
        <a style="color: white" class="nav-link active" href="#"
          ><svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-house"
            viewBox="0 0 16 16"
          >
            <path
              fill-rule="evenodd"
              d="M2 13.5V7h1v6.5a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7h1v6.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5zm11-11V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"
            />
            <path
              fill-rule="evenodd"
              d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z"
            />
          </svg>
          MY CABINS</a
        >
      </li>
      <li @click="addNewCabin()" class="nav-item">
        <a style="color: white" class="nav-link active" href="#"
          ><svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-plus-square"
            viewBox="0 0 16 16"
          >
            <path
              d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"
            />
            <path
              d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"
            />
          </svg>
          ADD NEW CABIN</a
        >
      </li>

      <li @click="myProfile()" class="nav-item">
        <a style="color: white" class="nav-link active" href="#"
          ><svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-person"
            viewBox="0 0 16 16"
          >
            <path
              d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"
            />
          </svg>
          MY ACCOUNT</a
        >
      </li>
     

      <li class="nav-item">
        <a style="color: white" @click="requests()" class="nav-link active" href="#"
          ><svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-bar-chart-line"
            viewBox="0 0 16 16"
          >
            <path
              d="M11 2a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v12h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1v-3a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3h1V7a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v7h1V2zm1 12h2V2h-2v12zm-3 0V7H7v7h2zm-5 0v-3H2v3h2z"
            />
          </svg>
          STATISTCS</a
        >
      </li>
    </ul>

   <br>
   <!-- search-->
   
    <div class="header" >
      <form>
        <h1 style="text-align: left; color: #0b477b;  padding-left: 7.2%;">Search cabins</h1>
        <br>
        <div style="padding-left: 7.2%; padding-right: 7.2%; width: 100%;" class="row" >
          <div class="col">
          <input
            class="form-control rounded-pill"
            type="text"
            style="height: 90%; width:110%; padding-left: 5%;"
            id="search-field"
            placeholder="NAME"
            :value="searchName"
            @input="searchName = $event.target.value.toUpperCase()"
          />
          </div>

          <div class="col">
          <input
            class="form-control rounded-pill"
            type="text"
            style="height: 90%; width:110%; padding-left: 5%;"
            placeholder="STREET AND NUM"
            :value="searchAddress"
            @input="searchAddress = $event.target.value.toUpperCase()"
          />
          </div>

          <div class="col">
          <input
            class="form-control rounded-pill"
            style="height: 90%; width:110%; padding-left: 5%;"
            type="text"
            placeholder="CITY"
            :value="searchCity"
            @input="searchCity = $event.target.value.toUpperCase()"
          />
          </div>

          <div class="col">
          <input
            class="form-control rounded-pill"
            type="text"
            style="height: 90%; width:110%; padding-left: 5%;"
            placeholder="COUNTRY"
            :value="searchCountry"
            @input="searchCountry = $event.target.value.toUpperCase()"
          />
          </div>

          <div class="col">
          <input
            class="form-control rounded-pill"
            type="text"
            style="height: 90%; width:110%; padding-left: 5%;"
            placeholder="PRICE"
            :value="searchPrice"
            @input="searchPrice = $event.target.value"
          />
          </div>

          <div class="col">
          <select
             style="height: 90%; color: #5f7280;"
            v-model="searchRating"
            class="form-select rounded-pill"
            aria-label="Default select example"
            placeholder="Rating"
          >
            <option  disabled value="">RATING</option>
            <option v-bind:value="5">FIVE STARS</option>
            <option v-bind:value="4">FOUR STARS</option>
            <option v-bind:value="3">THREE STARS</option>
            <option v-bind:value="2">TWO STARS</option>
            <option v-bind:value="1">ONE STAR</option>
          </select>
          </div>

          <div class="col">
          <button
            @click="resetSearch()"
            style="height: 90%; background-color: #0b477b; color: white;"
            type="button"
            class="btn  rounded-pill"
          >
            RESET SEARCH
          </button>
          </div>
          
        </div>
      </form>
    </div>

    <!--search-->


   <hr/>


    <!-- Carousel wrapper -->
    <div
      v-if="cabinsLoaded == true"
      id="carouselMultiItemExample"
      class="carousel slide carousel-dark text-center"
      data-mdb-ride="carousel"
    >
      <!-- Inner -->
      <div class="carousel-inner py-4">
        <!-- Single item -->
        <div class="carousel-item active">
          <div class="container">
            <div class="row">
              <div
                v-for="(cabinDto, index) in filteredCabins"
                :key="index"
                class="col-lg-4"
              >
                <div style="width: 100%; height: 95%" class="card">
                  <img
                    style="width: 100%; height: 100%"
                    :src="require('@/assets/' + getImageUrl(index))"
                  />

                  <div class="card-body">
                    <div class="row">
                      <div class="col-10">
                        <h2 style="text-align: left" class="card-title">
                          {{ cabinDto.name.toUpperCase() }}
                        </h2>
                      </div>
                      <div style="vertical-align: bottom" class="col">
                        <span class="badge bg-warning text-light"
                          ><svg
                            xmlns="http://www.w3.org/2000/svg"
                            width="16"
                            height="16"
                            fill="currentColor"
                            class="bi bi-star-fill"
                            viewBox="0 0 16 16"
                          >
                            <path
                              d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"
                            />
                          </svg>
                          {{ cabinDto.rating }}</span
                        >
                      </div>
                    </div>
                    <hr />
                    <h6 style="text-align: left; color: gray">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="16"
                        height="16"
                        fill="currentColor"
                        class="bi bi-geo-alt"
                        viewBox="0 0 16 16"
                      >
                        <path
                          d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A31.493 31.493 0 0 1 8 14.58a31.481 31.481 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94zM8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10z"
                        />
                        <path
                          d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4zm0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"
                        />
                      </svg>
                      {{ getFullAddress(index) }}
                    </h6>

                    <h6 style="text-align: left">{{ cabinDto.description }}</h6>
                    <h6 style="text-align: left; color: green">
                      {{ cabinDto.price }} $ per night
                    </h6>
                    <div class="row">
                      <div class="col" style="text-align: right">
                        <button
                          @click="seeProfile(cabinDto.name)"
                          type="button"
                          class="btn btn-outline-dark rounded-pill"
                        >
                          SEE PROFILE
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
                <hr style="color: white" />
                <hr style="color: white" />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Inner -->

    
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      email: "",
      cabinDtos: [
        {
          id: null,
          name: "",
          description: "",
          numOfRooms: 1,
          bedsPerRoom: 1,
          rules: "",
          price: 1.0,
          addressDto: {
            longitude: 0.0,
            latitude: 0.0,
            country: "",
            city: "",
            streetAndNum: "",
          },
          additionalServices: [
            {
              id: null,
              name: "",
              price: 0.0,
            },
          ],
          rating: 0.0,
          images: [
            {
              id: null,
              url: "",
              cabin: "",
            },
          ],
          ownerUsername: "",
        },
      ],
      user: {
        username: "",
      },
      cabinsLoaded: false,
      searchName: "",
      searchRating: "",
      searchAddress: "",
      searchPrice: "",
      searchCity: "",
      searchCountry: "",
      

    };
  },
  mounted() {
    this.email = this.$route.params.email;
    this.getOwnersCabins();
  },
  methods: {
    myProfile: function () {
      this.$router.push("/editProfile/" + "cabinOwner/" + this.email);
    },
    addNewCabin: function () {
      this.$router.push("/addNewCabin/" + this.email);
    },
    getOwnersCabins: function () {
      this.user.username = this.email;
      axios
        .post(
          "http://localhost:8081/cabins/findCabinsByOwnersUsername",
          this.user,
          {
            headers: {
              "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
              Authorization: "Bearer " + localStorage.jwt,
            },
          }
        )
        .then((response) => {
          this.cabinDtos = response.data;
          this.cabinsLoaded = true;
        });
    },
    getImageUrl: function (index) {
      if (this.cabinsLoaded == true) {
        return this.filteredCabins[index].images[0].url;
      }
      return "logoF1.png";
    },
    getFullAddress: function (index) {
      if (this.cabinsLoaded == true)
        return (
          this.filteredCabins[index].addressDto.streetAndNum +
          ", " +
          this.filteredCabins[index].addressDto.city +
          ", " +
          this.filteredCabins[index].addressDto.country
        );
      return "logoF1.png";
    },
    seeProfile: function (cabinName) {
      this.$router.push("/cabinProfile/" + this.email + "/" + cabinName);
    },
    resetSearch: function(){
      this.searchName= ""
      this.searchRating= ""
      this.searchAddress= ""
      this.searchCity= ""
      this.searchCountry=""
      this.searchPrice= ""
    }
  },
  computed: {
    filteredCabins: function () {
      var temp = this.cabinDtos.filter((cabin) => {
        return cabin.name.toUpperCase().match(this.searchName) && 
               cabin.addressDto.streetAndNum.toUpperCase().match(this.searchAddress) &&
               cabin.addressDto.city.toUpperCase().match(this.searchCity) &&
               cabin.addressDto.country.toUpperCase().match(this.searchCountry) && 
               cabin.rating.toString().match(this.searchRating) && 
               cabin.price.toString().startsWith(this.searchPrice);
      });

      return temp;
    },
  },
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
#logincard {
  width: 47%;
  background-image: url("../../assets/IMG_3872.jpeg");
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
