<template>
  <ClientNavbar />
  <ul class="nav justify-content-center" style="background-color: #0f5591">
    <li class="nav-item">
      <a style="color: white" class="nav-link" @click="changeEntityDisplay('cabins')">
        CABIN RESERVATIONS</a
      >
    </li>

    <li class="nav-item">
      <a style="color: white" class="nav-link" @click="changeEntityDisplay('boats')">
        BOAT RESERVATIONS</a
      >
    </li>

    <li class="nav-item">
      <a
        style="color: white"
        class="nav-link"
        @click="changeEntityDisplay('adventures')"
      >
        ADVENTURE RESERVATIONS</a
      >
    </li>
  </ul>

  <ul class="nav justify-content-center" style="background-color: #0f5591">
    <li class="nav-item">
      <a
        style="color: white"
        class="nav-link"
        @click="changeReservationDisplay('upcoming')"
      >
        UPCOMING</a
      >
    </li>

    <li class="nav-item">
      <a
        style="color: white"
        class="nav-link"
        @click="changeReservationDisplay('history')"
      >
        HISTORY</a
      >
    </li>
  </ul>

  <template v-if="upcomingReservationsShown">
    <template v-if="selectedEntity=='cabins'">
      <ClientCabinReservationsList :upcomingReservations="true" />
    </template>
    <template v-if="selectedEntity=='boats'">
      <ClientBoatReservationsList :upcomingReservations="true" />
    </template>
  </template>
  <template v-if="!upcomingReservationsShown">
    <template v-if="selectedEntity=='cabins'">
      <ClientCabinReservationsList :upcomingReservations="false" />
    </template>
    <template v-if="selectedEntity=='boats'">
      <ClientBoatReservationsList :upcomingReservations="false" />
    </template>
  </template>
</template>

<script>
import ClientNavbar from "./ClientNavbar";
import ClientCabinReservationsList from "./ClientCabinReservationsList";
import ClientBoatReservationsList from "./ClientBoatReservationsList";

export default {
  components: {
    ClientNavbar,
    ClientCabinReservationsList,
    ClientBoatReservationsList,
  },
  data() {
    return {
      email: "",
      selectedEntity: 'cabins',
      upcomingReservationsShown: true,
    };
  },
  mounted() {
    this.email = this.$route.params.email;
  },
  methods: {
    changeEntityDisplay: function (toDisplay) {
      this.selectedEntity = toDisplay;
      this.upcomingReservationsShown = true;
    },
    changeReservationDisplay: function (toDisplay) {
      if (toDisplay === "upcoming") {
        this.upcomingReservationsShown = true;
      } else {
        this.upcomingReservationsShown = false;
      }
    },
  },
  computed: {},
};
</script> 

<style>
</style>
