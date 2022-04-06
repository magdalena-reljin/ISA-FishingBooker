<template>

  <ClientNavbar v-if="role='CLIENT'" />
 
  <BookCabin :bookingProcess="false" />
</template>

<script>
import ClientNavbar from "./ClientNavbar";
import BookCabin from "./BookCabin";

export default {
  components: {
    ClientNavbar,
    BookCabin,
    
  },
  data() {
    return {};
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
