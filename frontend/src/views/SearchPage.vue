<template>
  <div class="MainDiv">
    <Header @update="register" />
    <div class="searchPage">
      <TrendLink/>
      <ExpandableSearchHistory />
      <div class="searchPageButtonsContainer">
        <button @click="register">Search</button>
        <button @click="clearHistory">Clear History</button>
      </div>
    </div>
  </div>
</template>
<script>
import Header from '../components/Header.vue';
import TrendLink from '../components/TrendLink.vue';
import ExpandableSearchHistory from '../components/ExpandableSearchHistory.vue';

export default {
  emits: ['update'],
  data() {
    return {
        topTenTrend: ['Cats', 'More Cats', 'All cats', 'Cats?!', 'Cats.', 'Cats!', 'Why are there so many cats', 'John', 'Stop This', 'Madness'],
        searchHistory: [],
        currentUser: null,
        trendingSearch: [],
    };
  },

  components: {
    Header,
    TrendLink,
    ExpandableSearchHistory,
  },

  created() {},

  async mounted(){
    let detailedSearchList;
    let boolean = false;
    this.$store.subscribe(async (mutation, state) => {
      if (mutation.type == 'setUser' && mutation.payload && !boolean) {
        this.$store.dispatch('cacheSearchHistory', []);
        this.currentUser = mutation.payload;
        if (this.currentUser) {
          detailedSearchList = [];
          this.searchHistory = [];
          detailedSearchList = await this.$store.dispatch(
            'getSearchHistories',
            this.currentUser.userId
          );
          if (detailedSearchList.length > 0) {
            detailedSearchList.forEach((element) => {
              this.searchHistory.push(element.keyWord);
            });
          }
        } else {
          console.log('user is not logged in');
        }
        // this.searchHistory = this.$store.getters.getMySearchHistoryList;
        boolean = true;
      }
    });
  },

  beforeUnmount() {},

  methods: {
    async register() {
      let searchParam = this.$store.getters.getKeyWord;
      if (this.currentUser && !this.searchHistory.includes(searchParam)) {
        console.log("we r in ")
        let obj = {
          userId: this.currentUser.userId,
          keyWord: this.$store.getters.getKeyWord,
        };

        let res = await fetch('/api/registerHistory', {
          method: 'POST',
          body: JSON.stringify(obj),
        });

        // update from DB
        let detailedSearchList = await this.$store.dispatch(
          'getSearchHistories',
          this.currentUser.userId
        );
        this.searchHistory = [];

        if (detailedSearchList.length > 0) {
          detailedSearchList.forEach((element) => {
            this.searchHistory.push(element.keyWord);
          });
        }
        await this.$store.dispatch('cacheSearchHistory', this.searchHistory);
        this.$router.push('/SearchResult');

      } else if (this.currentUser && this.searchHistory.includes(searchParam)) {
        console.log('loggedin but already made this search before');
      } else if (
        !this.currentUser &&
        this.searchHistory.includes(searchParam)
      ) {
        console.log("didn't log in but already made this search before");
      } else {
        if (this.searchHistory.length > 5) {
          this.searchHistory.splice(this.searchHistory.length - 1, 1);
        }

        let obj = {
          keyWord: this.$store.getters.getKeyWord,
        };

        this.searchHistory.unshift(obj);

        await this.$store.dispatch('cacheSearchHistory', this.searchHistory);
        this.$router.push('/SearchResult');
      }
    },
    async clearHistory() {
      this.searchHistory = [];
      await this.$store.dispatch('cacheSearchHistory', []);
      if (this.currentUser) {
        await this.$store.dispatch('clearHistory', this.currentUser.userId);
      }
    },
  },
};
</script>

<style scoped>
.MainDiv {
  background-color: #131313;
}

.searchPage {
  color: white;
  height: 100vh;
}

.searchPageButtonsContainer {
  text-align: center;
}

.searchPageButtonsContainer button {
  color: black;
  background-color: white;
  width: 25vw;
  border-radius: 10px;
  margin: 20px;
  height: 5vh;
}
</style>
