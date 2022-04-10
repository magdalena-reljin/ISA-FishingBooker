<template>

  <ClientNavbar v-if="role=='CLIENT'" />
  <AdminNavbar v-else :username=email />
 
  <BookAdventure :bookingProcess="false" />
</template>

<script>
import ClientNavbar from "./ClientNavbar";
import BookAdventure from "./BookAdventure";
import AdminNavbar from "../Admin/AdminNav.vue"

export default {
  components: {
    ClientNavbar,
    BookAdventure,
    AdminNavbar
  },
  data() {
    return {
      role:localStorage.role,
      email: '',
    };
  },
  mounted() {
    this.email = this.$route.params.email;
  },
  methods: {},
  computed: {},
};
</script> 

<style>
</style>
