
import { createStore } from 'vuex'
import axios from 'axios'
// Create a new store instance.
const store = createStore({
    state: {
        token: null,
        expiredIn : null
    },
    getters:{
        authenticated(state ){
            return state.token
        }
    },
    mutations: {
        SET_TOKEN (state,token){
            state.token = token
        },
        REFRESH_TOKEN (state,token){
            state.token = token
        }
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
                

               dispatch('attempt',response.data.accessToken)
               return  response
           } catch (error) {

            
                console.log("AAAAAAAAAA"+error)
           }
        

        },
        async attempt ({commit},token){
            try {
                commit('SET_TOKEN',token)
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



