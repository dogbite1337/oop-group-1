<template>
  <div class="MainDiv">
    <Header @update="register" />
    <div class="searchPage">
      <TrendLink />
      <ExpandableSearchHistory />
      <div class="searchPageButtonsContainer">
        <button @click="register" type="button">Search</button>
        <button @click="clearHistory" type="button">Clear History</button>
      </div>
    </div>
    <Footer />
  </div>
</template>
<script>
import Header from '../components/Header.vue';
import TrendLink from '../components/TrendLink.vue';
import ExpandableSearchHistory from '../components/ExpandableSearchHistory.vue';
import Footer from '../components/Footer.vue';

export default {
  emits: ['update'],
  data() {
    return {
      searchHistory: [],
      currentUser: null,
    };
  },

  components: {
    Header,
    Footer,
    TrendLink,
    ExpandableSearchHistory,
  },

  created() {},

  async mounted() {
    let detailedSearchList;
    let boolean = false;
    this.$store.subscribe(async (mutation, state) => {
      if (mutation.type == 'setUser' && mutation.payload && !boolean) {
        // this.$store.dispatch('cacheSearchHistory', []);
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
          this.searchHistory = this.$store.getters.getMySearchHistoryList;
        }
        boolean = true;
      }
    });
  },

  beforeUnmount() {},

  methods: {
    async register() {
      this.searchHistory = this.$store.getters.getMySearchHistoryList;
      if (this.currentUser) {
        let detailedSearchList = await this.$store.dispatch(
          'getSearchHistories',
          this.currentUser.userId
        );
        this.searchHistory = [];

        if (detailedSearchList.length > 0) {
          detailedSearchList.forEach((element) => {
            this.searchHistory.push(element);
          });
        }
      }

      let searchParam = this.$store.getters.getKeyWord;
      let boolean = await this.checkIfListContainsKey(
        this.searchHistory,
        searchParam
      );
      if (this.currentUser && !boolean && searchParam) {
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
        this.$router.push('/SearchResult');
      } else if (this.currentUser && boolean && searchParam) {
        console.log('loggedin but already made this search before');
        this.$router.push('/SearchResult');
      } else if (!this.currentUser && boolean && searchParam) {
        console.log("didn't log in but already made this search before");
        this.$router.push('/SearchResult');
      } else if (!this.currentUser && !boolean && searchParam) {
        this.searchHistory = this.$store.getters.getMySearchHistoryList;

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
      await this.$store.dispatch('cacheSearchHistory', this.searchHistory);
      if (this.currentUser) {
        await this.$store.dispatch('clearHistory', this.currentUser.userId);
      }
    },

    async checkIfListContainsKey(list, keyword) {
      let boolean = false;
      if (list.length == 0) {
        console.log('returned');
        return boolean;
      }

      list.forEach((element) => {
        if (element.keyWord.toLowerCase() == keyword.toLowerCase()) {
          boolean = true;
          return boolean;
        }
      });

      return boolean;
    },
  },
};
</script>

<style scoped>
.MainDiv {
  background-color: #131313;
}

.IconDiv{
  position: absolute;
  width: -webkit-fill-available;
}

.searchPage {
  color: white;
  height: 75.3vh;
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
