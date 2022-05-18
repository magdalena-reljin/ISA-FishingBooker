<template>

  <ClientNavbar v-if="role=='CLIENT' && this.email" />
  <AdminNavbar v-if="role=='ADMIN' && this.email"  :username=email />
  <Nav v-if="!email" />
 
  <BookAdventure :bookingProcess="false" />
</template>

<script>
import ClientNavbar from "./ClientNavbar";
import BookAdventure from "./BookAdventure";
import AdminNavbar from "../Admin/AdminNav.vue"
import Nav from "../Nav.vue"

export default {
  components: {
    ClientNavbar,
    BookAdventure,
    AdminNavbar,
    Nav,
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
