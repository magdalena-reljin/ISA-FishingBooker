<template>

  <ClientNavbar v-if="role=='CLIENT' && email" />
  <AdminNavbar v-if="role=='ADMIN' && email"  :username=email />
  <Nav v-if="!email" />
 
  <BookCabin :bookingProcess="false" />
</template>

<script>
import ClientNavbar from "./ClientNavbar";
import BookCabin from "./BookCabin";
import AdminNavbar from "../Admin/AdminNav.vue"
import Nav from "../Nav.vue"

export default {
  components: {
    ClientNavbar,
    BookCabin,
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
