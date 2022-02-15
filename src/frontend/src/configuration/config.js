export default {
    apiStart: "http://" + location.hostname + ":8081",
    requestHeader: {
      headers: {
        Authorization: localStorage.jwtToken ? "Bearer " + localStorage.jwtToken:'',
      },
    },
  };