<template>
  <div class="title">
    <h1 :class="isDarkTheme == true ? 'darkTheme' : 'lightTheme'">Top 10 Trending Searches</h1>
  </div>
  <div class="trendGrid" :class="isDarkTheme == true ? 'trendGridDarkTheme' : 'trendGridLightTheme'">
    <div class="itemsContainer" >
      <div v-for="(trend, index) of trendingSearch" :key="trend" class="item" :class="isDarkTheme == true ? 'itemDarkTheme' : 'itemLightTheme'">
        <p @click="makeSearch(trend)">
          {{
            index +
            1 +
            '. ' +
            (trend.length > 15 ? trend.substring(0, 14) + '...' : trend)
          }}
        </p>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'TrendLink',
  emits: ['addTrendingSearch'],
  data() {
    return {
      trendingSearch: [],
      isDarkTheme: true,
    };
  },

  async created() {
    this.isDarkTheme = await this.$store.getters.getIsDarkTheme
    this.trendingSearch = await this.$store.dispatch('getTrendingSearch');
    this.$store.watch((state) => state.darkTheme, (newVal) => {
      this.isDarkTheme = newVal
    })
  },

  methods: {
    async makeSearch(keyWord) {
      await this.$store.dispatch('setKeyWord', keyWord);

      let obj = {
        keyWord: keyWord,
      };

      let mySearchHistoryList = [];

      //if u are not logged in
      if (!this.$store.getters.getCurrentUser) {
        if (this.$store.getters.getMySearchHistoryList != null) {
          mySearchHistoryList = await this.$store.getters
            .getMySearchHistoryList;
        } else if (localStorage.searchHistoryList) {
          mySearchHistoryList = JSON.parse(localStorage.searchHistoryList);
        }

        if (
          mySearchHistoryList.length > 5 &&
          mySearchHistoryList.some((data) => data.keyWord != obj.keyWord)
        ) {
          mySearchHistoryList.splice(mySearchHistoryList.length - 1, 1);
          mySearchHistoryList.unshift(obj);
        } else if (
          mySearchHistoryList.length == 0 ||
          mySearchHistoryList.some((data) => data.keyWord != obj.keyWord)
        ) {
          mySearchHistoryList.unshift(obj);
        }

        await this.$store.dispatch('cacheSearchHistory', mySearchHistoryList);
        localStorage.setItem(
          'searchHistoryList',
          JSON.stringify(mySearchHistoryList)
        );
      } else {
        //if u r user register in db
        this.$emit('addTrendingSearch', keyWord);
      }

      this.$router.push('/SearchResult');
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

p {
  color: white;
}

h1 {
  font-size: 20px;
}

.title {
  margin: 10px 5% 10px 5%;
}

.item {
  background-color: white;
  margin: 5px;
  border-radius: 5px;
  height: 3vh;
  padding-top: 5px;
}

.item p {
  margin-left: 10px;
  color: black;
}

.trendGrid {
  border: 1px solid white;
  margin: 10px 5% 10px 5%;
  border-radius: 5px;
}

.itemsContainer {
  margin: 10px 5px 10px 5px;
  display: grid;
  grid-template-columns: repeat(2, 50%);
  grid-template-rows: repeat(5, 20%);
  grid-gap: 2px;
  justify-content: center;
  height: 25vh;
}

.lightTheme{
  color: #bfbfbf;
}

.trendGridLightTheme{
  border: 1px solid black;
}

.itemLightTheme{
  background-color: #939393;
}

.itemLightTheme p{
  color: white;
}

@media screen and (max-width: 450px) {
  .trendGrid{
    display: grid;
    grid-template-columns: 6% 1fr 6%;
  }
  .itemsContainer {
    grid-column-start: 2;
    grid-template-columns: 1fr;
    grid-template-rows: repeat(10,auto);
    height: unset;
  }
}
</style>
