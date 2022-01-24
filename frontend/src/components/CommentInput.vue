<template>
  <div>
    <div v-if="currentUser && !darkTheme" class="LightCommentGrid">
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
    <div v-if="currentUser && darkTheme" class="DarkCommentGrid">
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
  </div>
</template>
<script>
import Comment from "../jsClasses/general/Comment";
import store from "../store";
export default {
  props: ["videoId"],
  name: "CommentInput",
  mounted() {
    this.$store.subscribe(async (mutation, state) => {
      if (mutation.type == "setDarkTheme") {
        if (mutation.payload) {
          this.darkTheme = true;
        } else {
          this.darkTheme = false;
        }
      }
    });
  },
  data() {
    return {
      darkTheme: this.$store.getters.getIsDarkTheme,
      currentUser: this.$store.getters.getCurrentUser
        ? this.$store.getters.getCurrentUser
        : null,
      wantedComment: "",
      myImageUrl: this.$store.getters.getCurrentUser
        ? this.$store.getters.getCurrentUser.profileURL
        : "",
    };
  },
  methods: {
    async postComment() {
      let comment = new Comment(
        0,
        this.videoId,
        this.$store.getters.getCurrentUser.username,
        this.wantedComment,
        0,
        0,
        -1,
        Date.now()
      );
      let res = await fetch("/api/postComment", {
        method: "POST",
        body: JSON.stringify(comment),
      });

      let response = await res.json();
      this.$emit("postedComment", response);
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap");

.disabledPostButton {
  width: 38px;
  height: 26px;
  background: rgba(45, 44, 44, 0.5);
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  color: rgba(255, 255, 255, 0.45);
  font-family: "Roboto", sans-serif;
  font-size: 14px;
  line-height: 16px;
  margin-top: 7px;
}

.postButton {
  width: 45px;
  height: 35px;
  background: #2d2c2c;
  color: white;
  font-family: "Roboto", sans-serif;
  font-size: 14px;
  line-height: 16px;
  margin-top: 2px;
}

.LightCommentGrid {
  display: grid;
  grid-template-columns: auto max-content auto max-content auto max-content auto;
  padding-top: 30px;
  background-color: white;
  margin-top: 10px;
  padding-bottom: 30px;
  max-width: 800px;
  margin-left: auto;
  margin-right: auto;
}

.DarkCommentGrid {
  display: grid;
  grid-template-columns: auto max-content auto max-content auto max-content auto;
  padding-top: 30px;
  background-color: black;
  margin-top: 10px;
  padding-bottom: 30px;
  max-width: 800px;
  margin-left: auto;
  margin-right: auto;
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
