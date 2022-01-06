import { createStore } from 'vuex';
import User from './jsClasses/general/User';

export default createStore({
  state: {
    currentUser: null,
    searchResults: null,
    lastSearchQuery: null,
    showSearchPage: null,
    shouldResetToStartPage: null,
    eightFirstVideos: null,
    relatedVideoId: null,
    mySearchHistoryList: [],
    keyWord: "",
  },
  mutations: {
    setUser(state, user) {
      if (user !== null) {
        user = new User(user.userId, user.username, user.description, user.profileURL, user.subscribers, user.videosPosted)
      }
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
    },
    setMySearchHistoryList(state, searchHistory){
      state.mySearchHistoryList = searchHistory
    },
    setKeyWord(state, keyWord){
      state.keyWord = keyWord
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
    },
    getKeyWord:(state) => {
      return state.keyWord
    }
  },
  actions: {
    async login(store, currentUser) {
      store.commit('setUser', currentUser);
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
    },
    async whoAmI(store) {
      let res = await fetch('/api/whoami')
      let currentUser = await res.json();
      store.commit('setUser', currentUser);
    },
    async logout(store) {
      await fetch('/api/logout')
      store.commit('setUser', null);
    },
    async getSearchHistories(store, id){
      let res = await fetch('/rest/getSearchHistories/' + id);
      return res.json();
    },
    async cacheSearchHistory(store, searchHistory){
      store.commit('setMySearchHistoryList', searchHistory);
    },
    async setKeyWord(store, keyWord){
      store.commit('setKeyWord', keyWord)
    }

  },
});
