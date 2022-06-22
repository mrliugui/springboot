import {createStore} from 'vuex'

declare let SessionStorage: any;
const KEY = "USER";
// const getUser = () => {
//     try {
//         return JSON.parse(sessionStorage.USER)
//     } catch (e) {
//         return {}
//     }
// }
const store =  createStore({
  state: {
    user:SessionStorage.get(KEY) || {}
  },
  getters: {
    getUser:(state) => {
     return state.user;
}
  },
  mutations: {
    setUser(state,user){
      state.user=user || {};
      SessionStorage.set(KEY,user);
      //   const newUser = JSON.parse(JSON.stringify(user))
      //   sessionStorage.USER = JSON.stringify(newUser);
        // state.user = sessionStorage.USER;
        // SessionStorage.set(KEY,newUser);
    }
  },
  actions: {
  },
  modules: {
  }
})
export default store;
