<template>
  <div class="searchHistoryContainer" v-if="mySearchHistoryList.length > 0">
    <div class="title">
      <h1 :class="isDarkTheme == true ? 'darkTheme' : 'lightTheme'">Search History</h1>
    </div>
    <div class="buttonContainer">
      <button @click="btnClicked ? (btnClicked = false) : (btnClicked = true)" :class="isDarkTheme == true ? 'btnDarkTheme' : 'btnLightTheme'">
        {{ btnClicked ? 'close' : 'expand' }}
      </button>
    </div>
    <div class="historyItemContainer" v-if="!btnClicked && mySearchHistoryList" :class="isDarkTheme == true ? 'historyItemContainerDarkTheme' : 'historyItemContainerLightTheme'">
      <div
        class="historyItem"
        :class="isDarkTheme == true ? 'historyItemDarkTheme' : 'historyItemLightTheme'"
        v-for="item of mySearchHistoryList.slice(0, 3)"
        :key="item"
      >
        <p v-if="item" @click="makeSearch(item.keyWord)">
          {{
            item.keyWord.length > 12
              ? item.keyWord.substring(0, 9) + '...'
              : item.keyWord
          }}
        </p>
      </div>
    </div>

    <div class="historyItemContainer" v-if="btnClicked && mySearchHistoryList">
      <div class="historyItem" v-for="item of mySearchHistoryList" :key="item">
        <p v-if="item" @click="makeSearch(item.keyWord)">
          {{
            item.keyWord.length > 12
              ? item.keyWord.substring(0, 9) + '...'
              : item.keyWord
          }}
        </p>
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
      mySearchHistoryList: [],
      isDarkTheme: true,
    };
  },
  async beforeCreate(){
    this.isDarkTheme = await this.$store.getters.getIsDarkTheme
    if(this.$store.getters.getMySearchHistoryList == null && localStorage.searchHistoryList){
      this.mySearchHistoryList = await JSON.parse(localStorage.searchHistoryList)
    }
  },

  async mounted() {
    let boolean = false;
    this.$store.subscribe(async (mutation, state) => {
      if (
        (mutation.type == 'setMySearchHistoryList' ||
          mutation.type == 'setUser') &&
        !this.$store.getters.getCurrentUser
      ) {
        if(this.$store.getters.getMySearchHistoryList!=null){
          this.mySearchHistoryList = await this.$store.getters
          .getMySearchHistoryList;

          localStorage.setItem('searchHistoryList', JSON.stringify(this.mySearchHistoryList))
        }
        return;
      }

      // if (
      //   mutation.type == 'setMySearchHistoryList' &&
      //   mutation.payload.length == 0
      // ) {
      //   this.mySearchHistoryList = [];
      //   return;
      // }

      if (
        mutation.type == 'setUser' &&
        !boolean &&
        this.$store.getters.getCurrentUser
      ) {
        this.mySearchHistoryList = await this.$store.dispatch(
          'getSearchHistories',
          this.$store.getters.getCurrentUser.userId
        );
        boolean = true;
        return;
      }
      
      if (
        mutation.type == 'setMySearchHistoryList' &&
        this.$store.getters.getCurrentUser
      ) {

        this.mySearchHistoryList = await this.$store.dispatch(
          'getSearchHistories',
          this.$store.getters.getCurrentUser.userId
        );
        return;
      }
    });
  },

  methods: {
    async makeSearch(keyWord) {
      await this.$store.dispatch('setKeyWord', keyWord);
      this.$router.push('/SearchResult');
    },
  },
};
</script>

<style scoped>
.searchHistoryContainer {
  margin: 5vh 5% 5vh 5%;
  display: grid;
  grid-template-columns: 50% 50%;
}

h1 {
  font-size: 20px;
}

.title {
  margin: 10px 0 10px 0;
}

.buttonContainer {
  align-self: center;
  text-align: right;
}

button {
  width: 15vw;
}

.historyItemContainer {
  grid-column-start: span 2;
  background: white;
  border-radius: 5px;
  display: grid;
  grid-template-columns: repeat(3, 33%);
}

.historyItem {
  background: #595959;
  margin: 5px;
  border-radius: 5px;
  height: 3vh;
  text-align: center;
}

.btnLightTheme{
  background-color: black;
  color: white;
}

.historyItemContainerLightTheme{
  background-color: #939393;
}

.historyItemLightTheme{
  background-color: white;
}

.historyItemLightTheme p{
  color: #939393;
}


</style>
