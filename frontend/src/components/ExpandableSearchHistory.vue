<template>
  <div class="searchHistoryContainer"> 
    <div class="title">
      <h1>Search History</h1>
    </div>
    <div class="buttonContainer">
      <button @click="btnClicked ? btnClicked = false : btnClicked = true">{{btnClicked ? "close" : "expand"}}</button>
    </div>
    <div class="historyItemContainer" v-if="!btnClicked">
      <div class="historyItem" v-for="item of mySearchHistoryList.slice().reverse().slice(0,3)" :key="item">
        <p>{{item.length > 12 ? item.substring(0, 9) + '...' : item}}</p>
      </div>
    </div>

    <div class="historyItemContainer" v-if="btnClicked">
      <div class="historyItem" v-for="item of mySearchHistoryList.slice().reverse()" :key="item">
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

.searchHistoryContainer{
  margin: 5vh 5% 0 5%;
  display: grid;
  grid-template-columns: 50% 50%;
}

h1{
  font-size: 20px;
}

.title{
  margin: 10px 5% 10px 5%;
}

.buttonContainer{
  align-self: center;
  text-align: right;
}

button{
  width: 15vw;
}

.historyItemContainer{
  grid-column-start: span 2;
  background: white;
  border-radius: 5px;
  display: grid;
  grid-template-columns: repeat(3, 33%);
}

.historyItem{
  background: #595959;
  margin: 5px;
  border-radius: 5px;
  height: 3vh;
  text-align: center;
}

</style>