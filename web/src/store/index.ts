import {createStore} from 'vuex'

declare let SessionStorage: any;
const KEY = "USER";
export default createStore({
  state: {
    user:SessionStorage.get(KEY)||{}
  },
  getters: {
//     getUser:(state) => {
//       return state.user.filter(user: string => user.name);
// }
  },
  mutations: {
    setUser(state,user){
      SessionStorage.set(KEY,user);
    }
  },
  actions: {
  },
  modules: {
  }
})
