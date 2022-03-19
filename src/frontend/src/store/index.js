import { createStore } from 'vuex'
import axios from 'axios'
// Create a new store instance.
const store = createStore({
    state: {
        token: null,
        role: null
    },
    getters:{
        getToken : (state) => state.token,
        getRole : (state) => state.role,
    },
    mutations: {
        SET_TOKEN (state,token){
            state.token = token.accessToken
        },
        SET_ROLE (state,token){
            state.role = token.userType
        },
    },
    actions: {

       async logIn ({dispatch},credentials){
           try {
            let response =  await axios.post("http://localhost:8081/auth/login",credentials)
                localStorage.clear();
                localStorage.setItem('token',response.data.accessToken)
                localStorage.setItem('role',response.data.userType)
                axios.defaults.headers['Authorization']="Bearer "+ localStorage.token
                

               dispatch('attempt',response.data)

               return  response
           } catch (error) {

            
                console.log("AAAAAAAAAA"+error)
           }
        

        },
        async attempt ({commit},token){
            try {
                commit('SET_TOKEN',token)
                commit('SET_ROLE',token)
            } catch (error) {
                commit('SET_TOKEN',null)
            }
         
        },
        async logOut (){
            localStorage.setItem('token','empty')
            localStorage.removeItem('role');
        },
       
      
    },
    modules: {
         
    }
  
})
export default store;
