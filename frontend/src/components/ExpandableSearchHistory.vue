<template>
  <div class="searchHistoryContainer" v-if="mySearchHistoryList.length > 0"> 
    <div class="title">
      <h1>Search History</h1>
    </div>
    <div class="buttonContainer">
      <button @click="btnClicked ? btnClicked = false : btnClicked = true">{{btnClicked ? "close" : "expand"}}</button>
    </div>
    <div class="historyItemContainer">
      <div class="historyItem" v-for="item of mySearchHistoryList" :key="item">
        <p>{{item.length > 12 ? item.substring(0, 9) + '...' : item}}</p>
      </div>
    </div>
  </div>
</template>
<script>

export default {
  name: 'ExpandableSearchHistory',
  data() {
    return {
      btnClicked: false,
      // getting from state atm, change to fetch from DB when search page shown
      mySearchHistoryList: this.$store.getters.getMySearchHistoryList
    };
  },
  created(){
    
  },
  methods: {

  }
};
</script>

<style scoped>

h1{
  font-size: 20px;
}

button{
  width: 15vw;
}

</style>