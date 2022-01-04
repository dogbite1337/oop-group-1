import { createStore } from 'vuex';

export default createStore({
  state: {
    loggedInUser: null,
    searchResults: null,
    lastSearchQuery: null,
    showSearchPage: null,
    shouldResetToStartPage: null,
    eightFirstVideos: null,
    relatedVideoId: null,
    mySearchHistoryList: [],
  },
  mutations: {
    setUser(state, user) {
      state.currentUser = user;
    },
    setShouldResetToStartPage(state, shouldReset) {
      state.shouldResetToStartPage = shouldReset;
    },
    setSearchResults(state, searchResults) {
      state.searchResults = searchResults;
    },
    setLastSearchQuery(state, searchQuery) {
      state.lastSearchQuery = searchQuery;
    },
    setShowSearchPage(state, showSearchPage) {
      state.showSearchPage = showSearchPage;
    },
    setEightFirstVideos(state, eightFirstVideos) {
      state.eightFirstVideos = eightFirstVideos;
    },
    setRelatedVideoId(state, relatedVideoId) {
      state.relatedVideoId = relatedVideoId;
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
    },
    getEightFirstVideos: (state) => {
      return state.eightFirstVideos;
    },
    getRelatedVideoId: (state) => {
      return state.relevantVideoId;
    },
    getMySearchHistoryList: (state) => {
      return state.mySearchHistoryList
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
    },
    async resetToStartPage(store, shouldReset) {
      store.commit('setShouldResetToStartPage', shouldReset);
    },
    async cacheFirstEightVideos(store, eightFirstVideos) {
      store.commit('setEightFirstVideos', eightFirstVideos);
    },
    async updateRelatedVideoId(store, relatedVideoId) {
      store.commit('setRelatedVideoId', relatedVideoId);
    }
  },
});
