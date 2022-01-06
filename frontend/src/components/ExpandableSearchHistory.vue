<template>
  <div class="searchHistoryContainer" v-if="mySearchHistoryList.length > 0"> 
    <div class="title">
      <h1>Search History</h1>
    </div>
    <div class="buttonContainer">
      <button @click="btnClicked ? btnClicked = false : btnClicked = true">{{btnClicked ? "close" : "expand"}}</button>
    </div>
    <div class="historyItemContainer" v-if="!btnClicked">
      <div class="historyItem" v-for="item of mySearchHistoryList.slice(0,3)" :key="item">
        <p>{{item.keyWord.length > 12 ? item.keyWord.substring(0, 9) + '...' : item.keyWord}}</p>
      </div>
    </div>

    <div class="historyItemContainer" v-if="btnClicked">
      <div class="historyItem" v-for="item of mySearchHistoryList" :key="item">
        <p>{{item.keyWord.length > 12 ? item.keyWord.substring(0, 9) + '...' : item.keyWord}}</p>
      </div>
    </div>
  </div>
</template>
<script>

export default {
  name: 'ExpandableSearchHistory',
  props:["searchHistory"],
  data() {
    return {
      btnClicked: false,
      // getting from state atm, change to fetch from DB when search page shown
      mySearchHistoryList: [],
    };
  },
  async mounted(){
    let boolean = false;
    this.$store.subscribe(async (mutation, state) => {

      if(mutation.type == 'setMySearchHistoryList' && mutation.payload.length == 0){
        this.mySearchHistoryList = []
        return;
      }

      console.log(mutation);

      if(mutation.type == 'setUser' && !boolean && this.$store.getters.getCurrentUser){
        this.mySearchHistoryList = await this.$store.dispatch("getSearchHistories", this.$store.getters.getCurrentUser.userId);
        boolean = true;
      }
      if(mutation.type == 'setMySearchHistoryList' && this.$store.getters.getCurrentUser){
        this.mySearchHistoryList = await this.$store.dispatch("getSearchHistories", this.$store.getters.getCurrentUser.userId);
      }
      if(mutation.type == 'setMySearchHistoryList' && !this.$store.getters.getCurrentUser){
        this.mySearchHistoryList = await this.$store.getters.getMySearchHistoryList;
      }
    })
  },

  methods: {

  },
};
</script>

<style scoped>

.searchHistoryContainer{
  margin: 5vh 5% 5vh 5%;
  display: grid;
  grid-template-columns: 50% 50%;
}

h1{
  font-size: 20px;
}

.title{
  margin: 10px 0 10px 0;
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