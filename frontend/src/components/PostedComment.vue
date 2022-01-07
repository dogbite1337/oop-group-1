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
    <div v-if="!expandedReply" @click="toggleReplyInput" class="ReplyDiv">
      Reply
    </div>
    <div v-if="expandedReply" class="PostReplyDiv">
      <button class="cancelButton" @click="toggleReplyInput">Cancel</button>
      <div class="SpaceBlock" />
      <button v-if="wantedReply.length > 0" class="postButton">Reply To</button>
      <button disabled v-if="wantedReply.length == 0" class="disabledPostButton">
        Reply To
      </button>
    </div>
    <div class="SpaceBlock" />
    <div class="timestampOfComments">
      <div class="SpaceBlock" />
      {{ timestampOfComments }}
      <div class="SpaceBlock" />
    </div>
    <div class="SpaceBlock" />
  </div>
  <div v-if="expandedReply" class="ReplyOpenDiv">
    <div class="SpaceBlock" />
    <div class="Test">
      <div class="SpaceBlock" />
      <img class="imgInReply" :src="User.profileURL" />
      <div class="SpaceBlock" />
      <div class="replyInputDiv">
        <label class="YourReplyLabel" for="yourReply">Your reply</label>
        <textarea
          name="yourReply"
          class="replyInput"
          rows="5"
          cols="33"
          placeholder="Write here.."
        >
        </textarea>
      </div>
      <div class="SpaceBlock" />
    </div>
    <div class="SpaceBlock" />
  </div>
</template>
<script>
import User from '../jsClasses/general/User';

export default {
  props: ['comment', 'timestampOfComments'],
  name: 'PostedComment',
  mounted() {},
  data() {
    return {
      User: null,
      expandedReply: false,
      wantedReply: '',
    };
  },
  methods: {
    toggleReplyInput() {
      if (this.expandedReply) {
        this.expandedReply = false;
      } else {
        this.expandedReply = true;
      }
    },
    async like() {
      let likedCommentRes = await fetch(
        '/api/likeComment?' +
          new URLSearchParams({
            commentId: this.comment.commentId,
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
            commentId: this.comment.commentId,
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

.timestampOfComments {
  color: white;
  width: max-content;
  display: grid;
  grid-template-columns: auto max-content 30px;
  margin-top: -40px;
}
.postButton {
  background: #2d2c2c;
  color: white;
  padding-left: 3px;
  padding-right: 3px;
}
.disabledPostButton {
  background: rgba(45, 44, 44, 0.5);
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  color: rgba(255, 255, 255, 0.45);
  padding-left: 3px;
  padding-right: 3px;
}

.imgInReply {
  height: 40px;
  width: 40px;
  border-radius: 30px;
  position: relative;
  left: 20px;
}
.Test {
  display: grid;
  margin-left: -40px;
  grid-template-columns: 0px 0px 1px max-content auto;
  height: max-content;
  background-color: #131313;
  padding-top: 25px;
  padding-bottom: 20px;
  width: 370px;
}

.cancelButton {
  background-color: #2d2c2c;
  color: white;
  padding: 3px;
}

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
.ReplyOpenDiv {
  display: grid;
  width: 100vw;
  grid-template-columns: auto max-content auto;
  background-color: yellow;
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
  background-color: black;
}
.YourReplyLabel {
  color: white;
  position: relative;
  top: -85px;
  left: 72px;
}
.replyInput {
  padding-left: 10px;
  outline: none;
  padding-top: 3px;
  color: black;
}
.CommentGrid {
  height: max-content;
  background-color: black;
  color: white;
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
  padding-bottom: 10px;
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
  grid-template-columns: 30px max-content 10px max-content 15px auto auto max-content 1px;
  background-color: black;
  width: 100vw;
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
  padding-top: 4px;
  padding-bottom: 10px;
}
.PostReplyDiv {
  display: grid;
  grid-template-columns: max-content 10px max-content;
  color: white;
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

@media screen and (max-width: 450px){
  .Test{
    width: max-content;
    padding-right: 20px;
    margin-left: auto;
    margin-right: auto;
  }
  .replyInput{
    width: 180px;
  }
}
</style>
