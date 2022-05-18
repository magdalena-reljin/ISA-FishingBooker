<template>
  <div>
    <nav
      class="navbar navbar-fixed-top navbar-expand"
      style="background-color: #1d7ac9; list-style: none"
    >
      <div class="container-fluid" style="background-color: #1d7ac9">
        <a class="navbar-brand">
          <img src="../assets/logoF1.png" alt="" width="194" height="80" />
        </a>

        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a
              style="color: white"
              class="nav-link active"
              aria-current="page"
              @click="login()"
              href="#"
              >LOGIN</a
            >
          </li>
          <li class="nav-item">
            <a
              style="color: white"
              class="nav-link active"
              @click="signup()"
              href="#"
              >SIGN UP</a
            >
          </li>
        </ul>
      </div>
    </nav>
  </div>

  <ul class="nav justify-content-center" style="background-color: #0f5591">
    <li class="nav-item">
      <a
        style="color: white"
        class="nav-link"
        href="#"
        @click="changeDisplay('cabins')"
      >
        CABINS</a
      >
    </li>

    <li class="nav-item">
      <a
        style="color: white"
        class="nav-link"
        href="#"
        @click="changeDisplay('boats')"
      >
        BOATS</a
      >
    </li>

    <li class="nav-item">
      <a
        style="color: white"
        class="nav-link"
        href="#"
        @click="changeDisplay('adventures')"
      >
        ADVENTURES</a
      >
    </li>
  </ul>

  <template v-if="cabinsShown">
    <ClientCabins :reservationProcess="false" :availableCabins="[]" :unidentifiedUser="true"/>
  </template>
  <template v-if="boatsShown">
    <ClientBoats :reservationProcess="false" :availableBoats="[]" :unidentifiedUser="true"/>
  </template>
  <template v-if="adventuresShown">
    <ClientAdventures :reservationProcess="false" :availableAdventures="[]" :unidentifiedUser="true"/>
  </template>
</template>



<script>
import ClientCabins from "./Client/ClientCabins";
import ClientBoats from "./Client/ClientBoats";
import ClientAdventures from "./Client/ClientAdventures";

export default {
  components: {
    ClientCabins,
    ClientBoats,
    ClientAdventures,
  },
  data() {
    return {
      cabinsShown: true,
      boatsShown: false,
      adventuresShown: false,
    };
  },
  mounted() {},
  methods: {
    changeDisplay: function (toDisplay) {
      if (toDisplay === "cabins") {
        this.cabinsShown = true;
        this.boatsShown = false;
        this.adventuresShown = false;
      } else if (toDisplay === "boats") {
        this.cabinsShown = false;
        this.boatsShown = true;
        this.adventuresShown = false;
      } else {
        this.cabinsShown = false;
        this.boatsShown = false;
        this.adventuresShown = true;
      }
    },
    login: function () {
      this.$router.push("/login");
    },
    signup: function () {
      this.$router.push("/signup");
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

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
