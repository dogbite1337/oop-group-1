import { createStore } from 'vuex';

export default createStore({
  state: {
    loggedInUser: null
  },
  mutations: {
    setUser(state, user) {
      state.currentUser = user;
    },
  },
  getters: {
    getCurrentUser: (state) => {
      return state.currentUser;
    },
  },
  actions: {
    async login(store, loggedInUser) {
      store.commit('setUser', loggedInUser);
    },
  }});
