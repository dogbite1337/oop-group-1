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
    };
  },

  components:{
    Header,
    TrendLink,
    ExpandableSearchHistory,
  },

  created(){
  },

  beforeUnmount(){
  },

  methods: {
    async register(){
        let obj = {
            userId: 1,
            keyword: "haha"
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