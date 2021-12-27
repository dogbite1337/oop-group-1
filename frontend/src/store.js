import { createStore } from 'vuex';

export default createStore({
  state: {
    loggedInUser: null,
    searchResults: null,
    lastSearchQuery: null,
    showSearchPage: null
  },
  mutations: {
    setUser(state, user) {
      state.currentUser = user;
    },
    setSearchResults(state, searchResults) {
      state.searchResults = searchResults;
    },
    setLastSearchQuery(state, searchQuery) {
      state.lastSearchQuery = searchQuery;
    },
    setShowSearchPage(state, showSearchPage) {
      state.showSearchPage = showSearchPage;
    }
  },
  getters: {
    getCurrentUser: (state) => {
      return state.currentUser;
    },
    getSearchResults: (state) => {
      return state.searchResults;
    },
    getLastSearchQuery: (state) => {
      return state.lastSearchQuery;
    },
    getShowSearchPage: (state) => {
      return state.showSearchPage;
    }
  },
  actions: {
    async login(store, loggedInUser) {
      store.commit('setUser', loggedInUser);
    },
    async updateSearchResult(store, searchResults) {
      store.commit('setSearchResults', searchResults);
    },
    async updateLastSearchQuery(store, searchQuery) {
      store.commit('setLastSearchQuery', searchQuery);
    },
    async updateShowSearchPage(store, showSearchPage) {
      store.commit('setShowSearchPage', showSearchPage);
    }
  }});
