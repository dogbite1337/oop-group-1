<template>
  <div class="MainDiv">
    <Header/>
    <div class="searchPage">
        <TrendLink :trends="topTenTrend"/>
        <ExpandableSearchHistory/>
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
    };
  },

  components:{
    Header,
    TrendLink,
    ExpandableSearchHistory,
  },

  async created(){
    // works upon visiting search page the first time
    // when refresh, currentUser become null?
    if(this.$store.getters.getCurrentUser){
      console.log("logged in")
      // this.$store.getters.getCurrentUser.userId
      // 1
      let detailedSearchList = await this.$store.dispatch("getSearchHistories", this.$store.getters.getCurrentUser.userId);
      if(detailedSearchList.length>0){
        detailedSearchList.forEach(element => {
        this.searchHistory.push(element.keyWord)
      });
      }
    }
    else{
      console.log("not Logged In")
    }


    if(this.searchHistory.length>0){
      console.log("has searched")
      this.searchHistory.forEach(element => {
        console.log(element)
      });
    }
    else{
      console.log("user has no search history")
    }

  },

  beforeUnmount(){
  },

  methods: {
    async register(){
        let obj = {
            userId: 1,
            keyWord: "hoho",
        }
    
       let res = await fetch('/api/registerHistory', {
        method: 'POST',
        body: JSON.stringify(obj),
      });
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