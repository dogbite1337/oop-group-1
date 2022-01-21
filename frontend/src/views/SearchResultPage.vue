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
    if (this.$store.getters.getKeyWord) {
      let keyword = this.$store.getters.getKeyWord;
      this.matchedVideoList = await this.$store.dispatch(
        'getMatchedVideoList',
        keyword
      );
      localStorage.setItem(
        'orginalVideosList',
        JSON.stringify(this.matchedVideoList)
      );
      localStorage.setItem('searchKey', keyword);

      this.matchedUserList = await this.$store.dispatch(
      'getMatchedUserList',
      keyword
    );

      localStorage.setItem('userList', JSON.stringify(this.matchedUserList));

    }
    else{
      let list;
      list = await JSON.parse(localStorage.orginalVideosList)
      this.matchedVideoList = list

      this.matchedUserList = await JSON.parse(localStorage.userList)
    }

    await this.storeMatchedVideoList(this.matchedVideoList);
    await this.storeMatchedUserList(this.matchedUserList);

    this.$store.dispatch('setKeyWord', '');
  },

  methods: {
    async storeMatchedVideoList(matchedVideoList) {
      await this.$store.dispatch('setMatchedVideoList', matchedVideoList);
    },

    async storeMatchedUserList(list){
      await this.$store.dispatch('setMatchedUserList', list)
    }
  },
};
</script>

<style scoped>
.mainContainer {
  height: inherit;
  overflow: scroll;
  max-width:100vw;

}

.BackDrop {
  height: 0;

}



</style>
