<template>
  <MatchedUsers
    v-if="matchedUserList.length > 0"
    :matchedUserList="matchedUserList"
  />
  <MatchedVideos
    v-if="matchedVideoList.length > 0"
    :matchedVideoList="matchedVideoList"
  />
</template>
<script>
import MatchedUsers from './MatchedUsers.vue';
import MatchedVideos from './MatchedVideos.vue';

export default {
  props: ['matchedVideoList', 'matchedUserList'],

  components: {
    MatchedUsers,
    MatchedVideos,
  },

  mounted() {},

  data() {
    return {};
  },
};
</script>

<style scoped></style>
