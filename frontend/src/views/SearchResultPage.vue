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
      matchedVideoList: this.$store.dispatch(
        'getMatchedVideoList',
        this.$store.getters.getKeyWord
      ),
      matchedUserList: this.$store.dispatch(
        'getMatchedUserList',
        this.$store.getters.getKeyWord
      ),
    };
  },

  async created() {
    let keyword = this.$store.getters.getKeyWord;
    this.matchedVideoList = await this.$store.dispatch(
      'getMatchedVideoList',
      keyword
    );
    this.matchedUserList = await this.$store.dispatch(
      'getMatchedUserList',
      keyword
    );
  },
};
</script>

<style scoped>
.mainContainer {
  height: 80vh;
  overflow: scroll;
}
</style>
