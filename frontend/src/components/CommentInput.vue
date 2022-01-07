<template>
  <div v-if="currentUser" class="CommentGrid">
    <div class="SpaceDiv" />
    <img class="profileImage" v-if="myImageUrl" :src="myImageUrl" />
    <div class="SpaceDiv" />
    <input
      v-model="wantedComment"
      class="commentInput"
      type="textarea"
      placeholder="Write your comment here.."
    />
    <div class="SpaceDiv" />
    <button
      @click="postComment"
      v-if="wantedComment.length > 0"
      class="postButton"
      type="button"
      value="post"
    >
      Post
    </button>
    <button
      v-if="wantedComment.length == 0"
      class="disabledPostButton"
      type="button"
      value="post"
    >
      Post
    </button>
    <div class="SpaceDiv" />
  </div>
</template>
<script>
import Comment from '../jsClasses/general/Comment';
import store from '../store';
export default {
  props: [],
  name: 'CommentInput',
  mounted() {},
  data() {
    return {
      currentUser: this.$store.getters.getCurrentUser
        ? this.$store.getters.getCurrentUser
        : null,
      wantedComment: '',
      myImageUrl: this.$store.getters.getCurrentUser
        ? this.$store.getters.getCurrentUser.profileURL
        : '',
    };
  },
  methods: {
    async postComment() {
      let comment = new Comment(
        0,
        14,
        this.$store.getters.getCurrentUser.username,
        this.wantedComment,
        0,
        0,
        -1,
        0
      );
      let res = await fetch('/api/postComment', {
        method: 'POST',
        body: JSON.stringify(comment),
      });

      let response = await res.json();
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

.disabledPostButton {
  width: 38px;
  height: 26px;
  background: rgba(45, 44, 44, 0.5);
  border: 0.7px solid rgba(255, 255, 255, 0.45);
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  color: rgba(255, 255, 255, 0.45);
  font-family: 'Roboto', sans-serif;
  font-size: 14px;
  line-height: 16px;
  margin-top: 7px;
}

.postButton {
  width: 45px;
  height: 35px;
  outline: solid 0.7px white;
  background: #2d2c2c;
  color: white;
  font-family: 'Roboto', sans-serif;
  font-size: 14px;
  line-height: 16px;
  margin-top: 2px;
}
.CommentGrid {
  display: grid;
  grid-template-columns: auto max-content auto max-content auto max-content auto;
  padding-top: 30px;
  background-color: black;
  margin-top: 10px;
  padding-bottom: 30px;
}

.profileImage {
  width: 40px;
  height: 40px;
  border-radius: 30px;
}

.commentInput {
  padding-bottom: 3px;
  padding-left: 5px;
  padding-top: 3px;
  width: 295px;
  outline: none;
}
.postButton {
  width: max-content;
  height: max-content;
  padding: 2px;
  margin-top: 7px;
}
</style>
