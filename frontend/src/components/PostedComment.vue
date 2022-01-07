<template>
  <div class="CommentGrid">
    <div class="mainGrid">
      <div class="SpaceDiv" />
      <div class="userGrid">
        <div class="SpaceDiv" />
        <img v-if="User" :src="User.profileURL" class="commentImage" />
        <div class="SpaceDiv" />
        <div v-if="User" class="usernameDiv">{{ User.username }}</div>
        <div class="SpaceDiv" />
        <div v-if="comment" class="timeOfPostingDiv">
          Posted at {{ comment.timeOfPosting }}
        </div>
        <div class="SpaceDiv" />
      </div>
      <div class="SpaceDiv" />
    </div>
    <div class="CommentDiv">{{ comment.content }}</div>
  </div>
  <div v-if="comment" class="StatsDiv">
    <div class="SpaceDiv" />
    <div class="LikesDiv">
      <img
        @click="like"
        class="commentLike"
        src="../projectImages/like_black_background.png"
      />
      <div class="SpaceDiv" />
      {{ comment.likes }}
    </div>
    <div class="SpaceDiv" />
    <div class="DislikesDiv">
      <img
        @click="dislike"
        class="commentDislike"
        src="../projectImages/dislike_black_background.png"
      />
      <div class="SpaceDiv" />
      {{ comment.dislikes }}
    </div>
    <div class="SpaceDiv" />
    <div class="ReplyDiv">Reply</div>
  </div>
</template>
<script>
import User from '../jsClasses/general/User';
export default {
  props: ['comment'],
  name: 'PostedComment',
  mounted() {
    console.log(this.comment);
  },
  data() {
    return {
      User: null,
    };
  },
  methods: {
    async like() {
      let likedCommentRes = await fetch(
        '/api/likeComment?' +
          new URLSearchParams({
            commentId: this.comment.commentId
          }),
        {
          method: 'POST',
          body: JSON.stringify(this.comment),
        }
      );
      let likedCommentResponse = await likedCommentRes.json();
      this.comment.likes = likedCommentResponse.likes;
   
    },
    async dislike() {
      let dislikedCommentRes = await fetch(
        '/api/dislikeComment?' +
          new URLSearchParams({
            commentId: this.comment.commentId
          }),
        {
          method: 'POST',
          body: JSON.stringify(this.comment),
        }
      );
      let dislikedCommentResponse = await dislikedCommentRes.json();
      this.comment.dislikes = dislikedCommentResponse.dislikes;
    },
  },
  async mounted() {
    let commenterRes = await fetch(
      '/rest/getUserByUsername?' +
        new URLSearchParams({
          providedUsername: this.comment.postedByUsername,
        })
    );
    let commenterResponse = await commenterRes.json();
    let emptyUser = new User(0, '', '', '', 0, 0);
    this.User = Object.assign(emptyUser, commenterResponse);
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

.LikesDiv,
.DislikesDiv {
  display: grid;
  grid-template-columns: max-content 10px max-content;
  width: 65px;
  background-color: black;
  color: white;
  padding-top: 3px;
  padding-left: 3px;
  padding-right: 2px;
}
.CommentGrid {
  height: max-content;
  background-color: black;
  color: white;
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
  padding-bottom: 3px;
}
.ReplyDiv {
  color: white;
  padding-top: 2px;
}
.commentImage {
  height: 40px;
  width: 40px;
  border-radius: 30px;
  padding-top: 4px;
  padding-bottom: 4px;
}
.StatsDiv {
  display: grid;
  grid-template-columns: 30px max-content 10px max-content 15px auto;
  background-color: black;
  width: 100vw;
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
  padding-top: 4px;
  padding-bottom: 4px;
}

.usernameDiv {
  margin-top: 16px;
}
.CommentDiv {
  width: 100vw;
  background-color: black;
  max-width: 565px;
  margin-left: auto;
  margin-right: auto;
  padding-left: 35px;
  padding-top: 10px;
}
.userGrid {
  display: grid;
  grid-template-columns: 25px max-content 15px max-content auto max-content 25px;
  width: 100vw;
  max-width: 600px;
  background-color: black;
  padding-top: 5px;
}
.mainGrid {
  display: grid;
  grid-template-columns: auto max-content auto;
}
.timeOfPostingDiv {
  margin-top: 16px;
}
</style>
