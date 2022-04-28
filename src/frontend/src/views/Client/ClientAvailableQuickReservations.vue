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
    <template v-if="selectedEntity == 'cabins'">
      <ClientCabinReservationsList :upcomingReservations="false" :availableQuickReservations="true" />
    </template>
    <template v-if="selectedEntity == 'boats'">
      <ClientBoatReservationsList :upcomingReservations="false" :availableQuickReservations="true"/>
    </template>
    <template v-if="selectedEntity == 'adventures'">
      <ClientAdventureReservationsList :upcomingReservations="false" :availableQuickReservations="true"/>
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
    };
  },
  mounted() {
    this.email = this.$route.params.email;
  },
  methods: {
    changeEntityDisplay: function (toDisplay) {
      this.selectedEntity = toDisplay;
    },
  },
  computed: {},
};
</script> 

<style>
</style>
