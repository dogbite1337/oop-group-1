<template>
  <Header />
  <div class="mainContainer">
    <HasResult
      v-if="matchedVideoList.length > 0 || matchedUserList.length > 0"
      :matchedVideoList="matchedVideoList"
      :matchedUserList="matchedUserList"
    />
    <NoResult
      v-if="matchedVideoList.length <= 0 && matchedUserList.length <= 0"
    />
  </div>
  <Footer />
</template>

<script>
import Header from '../components/Header.vue';
import Footer from '../components/Footer.vue';
import HasResult from '../components/SearchResultPageComponents/HasResult.vue';
import NoResult from '../components/SearchResultPageComponents/NoResult.vue';

export default {
  components: {
    Header,
    Footer,
    HasResult,
    NoResult,
  },

  data() {
    return {
      matchedVideoList: [],
      matchedUserList: [],
    };
  },
  
  async created() {

    if(this.$store.getters.getKeyWord){
      let keyword = this.$store.getters.getKeyWord;
      this.matchedVideoList = await this.$store.dispatch(
      'getMatchedVideoList',
      keyword
    );
      localStorage.setItem('orginalVideosList', JSON.stringify(this.matchedVideoList));
      localStorage.setItem('searchKey', keyword);
      console.log("saved storeage")
    }
    else{
      let list;
      list = await JSON.parse(localStorage.orginalVideosList)
      this.matchedVideoList = list
      console.log("loaded from storeage")
    }

    await this.storeMatchedVideoList(this.matchedVideoList);

    this.$store.dispatch("setKeyWord", "");
  },

  methods:{
    async storeMatchedVideoList(matchedVideoList){
      await this.$store.dispatch("setMatchedVideoList", matchedVideoList)
    }
  }
};
</script>

<style scoped>
.mainContainer {
  height: inherit;
  overflow: scroll;
}

.BackDrop {
  height: 0;
}
</style>
