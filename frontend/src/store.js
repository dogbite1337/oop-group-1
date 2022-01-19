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
    keyWord: '',
    matchedVideoList: [],
    darkTheme: false,
  },
  mutations: {
    setUser(state, user) {
      if (user !== null) {
        user = new User(
          user.userId,
          user.username,
          user.description,
          user.profileURL,
          user.subscribers,
          user.videosPosted
        );
      }
      state.currentUser = user;
    },
    setDarkTheme(state, darkTheme) {
      state.darkTheme = darkTheme;
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
    setMySearchHistoryList(state, searchHistory) {
      state.mySearchHistoryList = searchHistory;
    },
    setKeyWord(state, keyWord) {
      state.keyWord = keyWord;
    },
    setMatchedVideoList(state, list) {
      state.matchedVideoList = list;
    },
  },
  getters: {
    getIsDarkTheme: (state) => {
      return state.darkTheme;
    },
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
      return state.mySearchHistoryList;
    },
    getKeyWord: (state) => {
      return state.keyWord;
    },
    getMatchedVideoList: (state) => {
      return state.matchedVideoList;
    },
  },
  actions: {
    async setDarkTheme(store, darkTheme) {
      store.commit('setDarkTheme', darkTheme);
    },
    async login(store, currentUser) {
      store.commit('setUser', currentUser);
      // store.commit('setMySearchHistoryList', [])
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
      let res = await fetch('/api/whoami');
      let currentUser = await res.json();
      store.commit('setUser', currentUser);
      // store.commit('setMySearchHistoryList', [])
    },
    async logout(store) {
      await fetch('/api/logout');
      store.commit('setUser', null);
      // store.commit('setMySearchHistoryList', [])
    },
    async getSearchHistories(store, id) {
      let res = await fetch('/rest/getSearchHistories/' + id);
      return res.json();
    },
    async cacheSearchHistory(store, searchHistory) {
      store.commit('setMySearchHistoryList', searchHistory);
    },
    async setKeyWord(store, keyWord) {
      store.commit('setKeyWord', keyWord);
    },
    async clearHistory(store, userId) {
      await fetch('/api/clearHistories/' + userId, {
        method: 'DELETE',
      });
    },
    async getTrendingSearch(store) {
      let res = await fetch('/api/getTrendingSearch', {
        method: 'GET',
      });

      return res.json();
    },
    async getMatchedVideoList(store, keyword) {
      let res = await fetch('/rest/getMatchedVideoList/' + keyword);
      return res.json();
    },
    async getMatchedUserList(store, keyword) {
      let res = await fetch(
        '/rest/getMatchedUserList?' +
          new URLSearchParams({
            keyword: keyword,
          })
      );
      return res.json();
    },
    async fetchEightMoreVideos(store, lengthOfCurrentVideoList) {
      let res = await fetch(
        '/rest/getEightNewVideos?' +
          new URLSearchParams({
            lengthOfCurrentVideoList: lengthOfCurrentVideoList,
          })
      );
      return res.json();
    },

    async setMatchedVideoList(store, list) {
      store.commit('setMatchedVideoList', list);
    },
  },
});
