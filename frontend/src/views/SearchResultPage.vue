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

  watch:{
    keyword(newList) {
      console.log(newList)
      console.log("saved")
      localStorage.setItem('orginalVideosList', JSON.stringify(newList));
    }
  },
  
  async created() {

    if(this.$store.getters.getKeyWord){
       let keyword = this.$store.getters.getKeyWord;
      this.matchedVideoList = await this.$store.dispatch(
      'getMatchedVideoList',
      keyword
    );
    console.log(this.matchedVideoList)
    }
    else{
      let list;
      list = await JSON.parse(localStorage.orginalVideosList)
      this.matchedVideoList = list
      console.log("loaded from storeage")
      console.log(this.matchedVideoList)
    }

    // if(this.matchedVideoList){
    //   if(localStorage.orginalVideosList){
    //   let list;
    //   list = await JSON.parse(localStorage.orginalVideosList)
    //   // list = JSON.parse(localStorage.orginalVideoList)

    //   this.matchedVideoList = list
    //   console.log("loaded from storeage")
    //   console.log(this.matchedVideoList)
    // }
    // }

    

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
