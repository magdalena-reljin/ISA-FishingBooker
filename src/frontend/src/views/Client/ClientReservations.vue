<template>
  <ClientNavbar />
  <ul class="nav justify-content-center" style="background-color: #0f5591">
    <li class="nav-item">
      <a
        style="color: white"
        class="nav-link"
        href="#"
        @click="changeEntityDisplay('cabins')"
      >
        CABIN RESERVATIONS</a
      >
    </li>

    <li class="nav-item">
      <a
        style="color: white"
        class="nav-link"
        href="#"
        @click="changeEntityDisplay('boats')"
      >
        BOAT RESERVATIONS</a
      >
    </li>

    <li class="nav-item">
      <a
        style="color: white"
        class="nav-link"
        href="#"
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
        href="#"
        @click="changeReservationDisplay('upcoming')"
      >
        UPCOMING</a
      >
    </li>

    <li class="nav-item">
      <a
        style="color: white"
        class="nav-link"
        href="#"
        @click="changeReservationDisplay('history')"
      >
        HISTORY</a
      >
    </li>
  </ul>

  <template v-if="upcomingReservationsShown">
    <template v-if="selectedEntity == 'cabins'">
      <ClientCabinReservationsList :upcomingReservations="true" :availableQuickReservations="false"/>
    </template>
    <template v-if="selectedEntity == 'boats'">
      <ClientBoatReservationsList :upcomingReservations="true" :availableQuickReservations="false"/>
    </template>
    <template v-if="selectedEntity == 'adventures'">
      <ClientAdventureReservationsList :upcomingReservations="true" :availableQuickReservations="false"/>
    </template>
  </template>
  <template v-if="!upcomingReservationsShown">
    <template v-if="selectedEntity == 'cabins'">
      <ClientCabinReservationsList :upcomingReservations="false" :availableQuickReservations="false"/>
    </template>
    <template v-if="selectedEntity == 'boats'">
      <ClientBoatReservationsList :upcomingReservations="false" :availableQuickReservations="false"/>
    </template>
    <template v-if="selectedEntity == 'adventures'">
      <ClientAdventureReservationsList :upcomingReservations="false" :availableQuickReservations="false"/>
    </template>
  </template>
</template>

<script>
import ClientNavbar from "./ClientNavbar";
import ClientCabinReservationsList from "./ClientCabinReservationsList";
import ClientBoatReservationsList from "./ClientBoatReservationsList";
import ClientAdventureReservationsList from "./ClientAdventureReservationsList";

export default {
  components: {
    ClientNavbar,
    ClientCabinReservationsList,
    ClientBoatReservationsList,
    ClientAdventureReservationsList,
  },
  data() {
    return {
      email: "",
      selectedEntity: "cabins",
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
