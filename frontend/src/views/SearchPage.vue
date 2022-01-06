<template>
  <div class="MainDiv">
    <Header/>
    <div class="searchPage">
        <TrendLink :trends="topTenTrend"/>
        <ExpandableSearchHistory v-if="searchHistory.length>0"/>
        <div class="searchPageButtonsContainer">
            <button @click="register">Search</button>
            <button >Clear History</button>
    </div>
    </div>
    </div>

</template>
<script>
import Header from '../components/Header.vue'
import TrendLink from '../components/TrendLink.vue'
import ExpandableSearchHistory from '../components/ExpandableSearchHistory.vue'

export default {
  data() {
    return {
        topTenTrend: ['Cats', 'More Cats', 'All cats', 'Cats?!', 'Cats.', 'Cats!', 'Why are there so many cats', 'John', 'Stop This', 'Madness'],
        searchHistory: [],
        // currentUser: this.$store.getters.getCurrentUser,
        currentUser: null,
    };
  },

  components:{
    Header,
    TrendLink,
    ExpandableSearchHistory,
  },

  async created(){
    this.$store.subscribe(async (mutation, state) => {
      let detailedSearchList
      if(mutation.type == "setUser"){
        this.currentUser = mutation.payload
        if(this.currentUser){
          console.log("user is logged in")
          detailedSearchList = await this.$store.dispatch("getSearchHistories", 1);
            if(detailedSearchList.length>0){
               detailedSearchList.forEach(element => {
               this.searchHistory.push(element.keyWord)
               console.log("has searched: " + element.keyWord)
              });
              await this.$store.dispatch("cacheSearchHistory", this.searchHistory)
            }
        }
        else{
          console.log("user is not logged in")
          this.searchHistory = this.$store.getters.getMySearchHistoryList
          if(this.searchHistory.length>0){
            console.log("you have searched for:")
            this.searchHistory.forEach(element => {
              console.log(element)
            });
          }
          console.log("user has not made any search yet")
        }
    }})
  },

  beforeUnmount(){
  },

  methods: {
    async register(){
      if(this.currentUser){
        let obj = {
            userId: this.currentUser.userId,
            keyWord: this.$store.getters.getKeyword,
        }

          let res = await fetch('/api/registerHistory', {
          method: 'POST',
          body: JSON.stringify(obj),
          });
          console.log("stored search in BD")
        }

        else{
          if(this.searchHistory.length>5){
            this.searchHistory.splice(0,1);
          }

          this.searchHistory.push(this.$store.getters.getKeyWord)
          console.log("keyword: " + this.$store.getters.getKeyWord + " has been added to list")
        }
      }
    }
  }
</script>

<style scoped>
.MainDiv{
  background-color: #131313;
}

.searchPage{
  color: white;
  height: 100vh;
}

.searchPageButtonsContainer{
  text-align: center;
}

.searchPageButtonsContainer button{
  color: black;
  background-color: white;
  width: 25vw;
  border-radius: 10px;
  margin: 20px;
  height: 5vh;
}

</style>