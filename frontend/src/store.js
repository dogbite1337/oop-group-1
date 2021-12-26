import { createStore } from 'vuex';

export default createStore({
  state: {
    loggedInUser: null,
    searchResults: null
  },
  mutations: {
    setUser(state, user) {
      state.currentUser = user;
    },
    setSearchResults(state, searchResults) {
      state.searchResults = searchResults;
    }
  },
  getters: {
    getCurrentUser: (state) => {
      return state.currentUser;
    },
    getSearchResults: (state) => {
      return state.searchResults;
    }
  },
  actions: {
    async login(store, loggedInUser) {
      store.commit('setUser', loggedInUser);
    },
    async updateSearchResult(store, searchResults) {
      store.commit('setSearchResults', searchResults);
    }
  }});
