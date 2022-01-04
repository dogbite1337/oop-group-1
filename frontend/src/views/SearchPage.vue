<template>
  <div class="MainDiv">
    <Header/>
    <div class="searchPage">
        <TrendLink :trends="topTenTrend"/>
      <div class="SearchHistoryDiv">
        <div/>
        <p class="searchHistoryText">Search History</p>
        <div/>
        <button v-if="!expandedSearchHistory" @click="expandSearchHistory" class="expandButton">expand</button>
        <button v-if="expandedSearchHistory" @click="closeSearchHistory" class="closeButton">close</button>
        <div/>
      </div>
      <ExpandableSearchHistory :expandedSearchHistory="expandedSearchHistory" 
      :values="mySearchHistory" />
    </div>
    <div v-if="showSearchPage && !showResultsPage" class="clearHistoryDiv">
      <button class="clearHistoryButton">Clear history</button>
    </div>
    <div v-if="showSearchPage && !showResultsPage" class="clearHistoryDiv">
      <button @click="showResultsOfSearch" class="Search">Search</button>
    </div>
    </div>

</template>
<script>
import Header from '../components/Header.vue'
import TrendLink from '../components/TrendLink.vue'

export default {
  data() {
    return {
        topTenTrend: ['Cats', 'More Cats', 'All cats', 'Cats?!', 'Cats.', 'Cats!', 'Why are there so many cats', 'John', 'Stop This', 'Madness'],
    };
  },

  components:{
    Header,
    TrendLink,
  },

  created(){
  },

  beforeUnmount(){
  },

  methods: {
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

.clearHistoryButton{
  width: 97px;
  height: 20px;
  background-color: white;
  color: black;
  border-radius: 3px;
}
.trendsDiv{
  height: 193px;
  outline: 1px solid white;
  margin-left: 19px;
  margin-right: 19px;
  background-color: black;
  width: 372px;
  margin-left: auto;
  margin-right: auto;
  padding-bottom: 9px;
}
.searchHistoryText{
  font-family: 'Roboto', sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  line-height: 21px;
}
.SorryKitty{
  display:block;
  width: 200px;
  height: 224px;
  margin-left:auto;
  margin-right: auto;
}
.SearchHistoryDiv{
  background-color: #131313;
  display: grid;
  grid-template-columns: 1px auto auto 72px 1px;
  margin-right: 19px;
  margin-left: 19px;
  margin-top: 39px;
  margin-bottom: 10px;
  width: 376px;
  margin-left: auto;
  margin-right: auto;
}
.topTrendingDiv{
  margin-bottom: 4px;
  width: 372px;
  margin-left: auto;
  margin-right: auto;
}

.expandButton, .closeButton{
  width: 72.57px;
  height: 20px;
  width: 72.57px;
  height: 20px;
  background-color: white;
  border-radius: 3px;
}
.trendBox{
  width: 100px;
  height: 100px;
  background-color: white;
}

</style>