<template>
  <div class="MainDiv">
    <Header @update="register" />
    <div class="searchPage">
      <TrendLink @addTrendingSearch="addTrendingSearch($event)"/>
      <ExpandableSearchHistory />
      <div class="searchPageButtonsContainer">
        <button @click="register" type="button">Search</button>
        <button @click="showConfirmModal" type="button">Clear History</button>
      </div>
    </div>
    <div class="confirmModalBackGround" v-if="showConfirmWindow">
      <div class="confirmModalContainer">
        <p>Are you sure you want to reset search history? This action can not be un done</p>
        <div class="confirmBtnContainer">
          <button class="confirmBtn" type="button" @click="clearHistory">Yes</button>
          <button class="confirmBtn" type="button" @click="showConfirmWindow=false">No</button>
        </div>
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
  emits: ['update','addTrendingSearch'],
  data() {
    return {
      searchHistory: [],
      currentUser: null,
      showConfirmWindow: false,
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
          this.searchHistory = this.$store.getters.getMySearchHistoryList;
        }
        boolean = true;
      }
    });
  },

  beforeUnmount() {},

  methods: {
    async addTrendingSearch(keyword){
      await this.$store.dispatch('setKeyWord', keyword)
      this.register()
    },

    async register() {
      let searchParam = await this.$store.getters.getKeyWord;
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
      }else{
        if(this.$store.getters.getMySearchHistoryList != null){
          this.searchHistory = this.$store.getters.getMySearchHistoryList;
        }
        else if(localStorage.searchHistorList){
          this.searchHistory = await JSON.parse(localStorage.searchHistoryList)
        }
      }
      let boolean = await this.checkIfListContainsKey(
        this.searchHistory,
        searchParam
      );
      if (this.currentUser && !boolean && searchParam) {
        let obj = {
          userId: this.currentUser.userId,
          keyWord: searchParam,
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
        this.$router.push('/SearchResult');
        
      } else if (!this.currentUser && boolean && searchParam) {
        this.$router.push('/SearchResult');
        
      } else if (!this.currentUser && !boolean && searchParam) {
        if(this.$store.getters.getMySearchHistoryList != null){
          this.searchHistory = this.$store.getters.getMySearchHistoryList;
        }else if(localStorage.searchHistoryList){
          this.searchHistory = await JSON.parse(localStorage.searchHistoryList)
        }

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

    showConfirmModal(){
      this.showConfirmWindow = true;
    },

    async clearHistory() {
      
      this.searchHistory = [];
      await this.$store.dispatch('cacheSearchHistory', this.searchHistory);
      if (this.currentUser) {
        await this.$store.dispatch('clearHistory', this.currentUser.userId);
      }
      this.showConfirmWindow = false;
    },

    async checkIfListContainsKey(list, keyword) {
      let boolean = false;
      if (list == null) {
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

.confirmModalBackGround{
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 98;
  background-color: rgba(0, 0, 0, 0.7);
}

.confirmModalContainer{
    position: relative;
    margin: 0 auto;
    top: 40%;
    width: 100%;
    max-width: 80vw;
    background-color: #595959;
    border-radius: 10px;
    text-align: center;
    padding: 2rem;
    z-index: 99;
}

.confirmModalContainer p{
  color: white;
  font-size: larger;
}

.confirmBtnContainer{
  display: flex;
  place-content: space-evenly;
  margin-top: 2rem;
}

.confirmBtn{
  width: 6rem;
  padding: 0.5rem;
  font-size: large;
  background-color: #595959;
  border: solid 1px white;
  color: white;
}


</style>
