
import { createStore } from 'vuex'
import axios from 'axios'
// Create a new store instance.
const store = createStore({
    state: {
        token: null,
        expiredIn : null,
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
               localStorage.setItem('token',null)
                localStorage.setItem('token',response.data.accessToken)
                this.state.expiredIn=response.data.expiresIn;
                console.log("AAAAAAAAAAAAAA"+response.data.expiresIn)
                const date = new Date()
               //date.setMilliseconds(date.getMilliseconds+response.data.expiresIn)
                console.log("saddddddddddddddddddd"+date.getMilliseconds)
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
        async logOut ({commit}){
            commit('SET_TOKEN',null)
            localStorage.setItem('token','empty')
        }
       
      
    },
    modules: {
         
    }
  
})
export default store;



