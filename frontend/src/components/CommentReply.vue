<template>
  <div class="CommentGrid">
    <div class="mainGrid">
      <div class="SpaceDiv" />
      <div class="userGrid">
        <div class="SpaceDiv" />
        <img
          v-if="commenter"
          :src="commenter.profileURL"
          class="commentImage"
        />
        <div class="SpaceDiv" />
        <div v-if="commenter" class="usernameDiv">{{ commenter.username }}</div>
        <div class="SpaceDiv" />
        <div v-if="reply" class="timeOfPostingDiv">
          Posted at
          {{ convertDateObjectToString(new Date(reply.timeOfPosting)) }}
        </div>
        <div class="SpaceDiv" />
      </div>
      <div class="SpaceDiv" />
    </div>
    <div class="CommentDiv">{{ reply.content }}</div>
  </div>
  <div class="StatsDiv">
    <div class="SpaceDiv" />
    <div class="LikesDiv">
      <img
        v-if="!likedReplyAlready"
        @click="like"
        class="commentLike"
        src="../projectImages/like_black_background.png"
      />
      <img
        v-if="likedReplyAlready"
        class="commentLike"
        src="../projectImages/blue_like.png"
      />
      <div class="SpaceDiv" />
      {{ reply.likes }}
    </div>
    <div class="SpaceDiv" />
    <div class="DislikesDiv">
      <img
        v-if="!dislikedReplyAlready"
        @click="dislike"
        class="commentDislike"
        src="../projectImages/dislike_black_background.png"
      />
      <img
        v-if="dislikedReplyAlready"
        class="commentDislike"
        src="../projectImages/blue_dislike.png"
      />
      <div class="SpaceDiv" />
      {{ reply.dislikes }}
    </div>
    <div class="SpaceDiv" />

    <div class="SpaceBlock" />
    <div class="timestampOfComments"></div>
    <div class="SpaceBlock" />
  </div>
</template>
<script>
export default {
  props: ['commenter', 'reply'],
  name: 'CommentReply',
  data() {
    return {
      likedReplyAlready: false,
      dislikedReplyAlready: false,
    };
  },
  async mounted() {
    let likesRes = await fetch(
      '/rest/getLikesForComment?' +
        new URLSearchParams({
          commentId: this.reply.commentId,
        })
    );
    let likesResponse = await likesRes.json();
    for (let i = 0; i < likesResponse.length; i++) {
      if (this.$store.getters.getCurrentUser) {
        if (
          likesResponse[i].likedByUserId ==
          this.$store.getters.getCurrentUser.userId
        ) {
          this.likedReplyAlready = true;
        }
      }
    }

    let dislikesRes = await fetch(
      '/rest/getDislikesForComment?' +
        new URLSearchParams({
          commentId: this.reply.commentId,
        })
    );
    let dislikesResponse = await dislikesRes.json();
    for (let e = 0; e < dislikesResponse.length; e++) {
      if (this.$store.getters.getCurrentUser) {
        if (
          dislikesResponse[e].dislikedByUserId ==
          this.$store.getters.getCurrentUser.userId
        ) {
          this.dislikedReplyAlready = true;
        }
      }
    }
  },
  methods: {
    async like() {
      if (this.$store.getters.getCurrentUser) {
        let likedCommentRes = await fetch(
          '/api/likeComment?' +
            new URLSearchParams({
              commentId: this.reply.commentId,
            }),
          {
            method: 'POST',
            body: JSON.stringify(this.reply),
          }
        );
        let likedCommentResponse = await likedCommentRes.json();
        this.reply.likes = likedCommentResponse.likes;

        let registerlikedCommentRes = await fetch(
          '/api/registerLikeOnComment?' +
            new URLSearchParams({
              relatesToVideoId: this.$route.params.id,
              userId: this.$store.getters.getCurrentUser.userId,
              videoId: 0,
              commentId: this.reply.commentId,
            }),
          {
            method: 'POST',
            body: JSON.stringify(this.reply),
          }
        );
        this.likedReplyAlready = true;
      } else {
        alert('You have to be logged in to like a Reply!');
      }
    },
    async dislike() {
      if (this.$store.getters.getCurrentUser) {
        let dislikedCommentRes = await fetch(
          '/api/dislikeComment?' +
            new URLSearchParams({
              relatesToVideoId: this.$route.params.id,
              commentId: this.reply.commentId,
              userId: this.$store.getters.getCurrentUser.userId,
            }),
          {
            method: 'POST',
            body: JSON.stringify(this.reply),
          }
        );
        let dislikedCommentResponse = await dislikedCommentRes.json();
        this.reply.dislikes = dislikedCommentResponse.dislikes;

        this.dislikedReplyAlready = true;
      } else {
        alert('You have to be logged in to dislike a reply!');
      }
    },
    convertDateObjectToString(dateObject) {
      let newDate =
        (dateObject.getUTCDate() < 10
          ? '0' + dateObject.getUTCDate()
          : dateObject.getUTCDate()) +
        ' - ' +
        (dateObject.getUTCMonth() + 1 < 10
          ? '0' + (dateObject.getUTCMonth() + 1)
          : dateObject.getUTCMonth() + 1) +
        ' - ' +
        dateObject.getUTCFullYear();
      newDate = newDate + ' ' + dateObject.getHours() + ':';

      if (dateObject.getMinutes() < 1) {
        newDate += '00';
      } else if (dateObject.getMinutes() < 10) {
        newDate += '0' + dateObject.getMinutes();
      } else {
        newDate += dateObject.getMinutes();
      }
      return newDate;
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

.misc {
  width: max-content;
  height: max-content;
  background-color: white;
  margin-left: auto;
  margin-right: auto;
}

.commentersBox {
  background-color: white;
}
.timestampOfComments {
  color: white;
  width: 100vw;
  background-color: transparent;
  display: grid;
  grid-template-columns: 1px auto 25px;
  position: absolute;
  margin-top: -46px;
  align-content: right;
  justify-items: right;
  max-width: 400px;
}

.timestampOfReplies {
  color: white;
  width: 100vw;
  background-color: transparent;
  display: grid;
  grid-template-columns: 1px auto 25px;
  position: absolute;
  margin-top: -46px;
  align-content: right;
  justify-items: right;
  max-width: 400px;
}
.CommentReplyDiv {
  width: 100vw;
  max-width: 400px;
}
.StatsInReplyDiv {
  display: grid;
  grid-template-columns: 30px max-content 10px max-content 15px auto auto max-content 1px;
  background-color: black;
  width: 100vw;
  max-width: 400px;
  margin-left: auto;
  margin-right: auto;
  padding-top: 4px;
  padding-bottom: 10px;
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
.replyInputDiv {
  display: grid;
  grid-template-rows: max-content max-content;
  color: white;
}
.ReplyOpenDiv {
  display: grid;
  width: 100vw;
  grid-template-columns: auto max-content auto;
  background-color: yellow;
  max-width: 400px;
  margin-left: auto;
  margin-right: auto;
  background-color: black;
}
.EndBlock {
  width: 30px;
}
.replyHolderDiv {
  display: grid;
  grid-template-columns: auto max-content auto max-content auto;
}
.replyInput {
  padding-left: 10px;
  outline: none;
  padding-top: 3px;
  color: black;
}
.ReplyGrid {
  height: max-content;
  background-color: black;
  color: white;
  max-width: 400px;
  margin-left: auto;
  margin-right: auto;
  padding-bottom: 10px;
}
.ReplyCommentGrid {
  height: max-content;
  background-color: black;
  color: white;
  max-width: 400px;
  width: 100vw;
  padding-bottom: 10px;
}
.CommentGrid {
  height: max-content;
  background-color: black;
  color: white;
  max-width: 450px;
  margin-left: auto;
  margin-right: auto;
  padding-bottom: 10px;
}
.yourReplyDiv {
  position: relative;
  left: 80px;
  top: -20px;
}
.ReplyDiv {
  color: white;
  padding-top: 2px;
  display: grid;
  grid-template-columns: max-content 10px max-content;
}
.commentImage {
  height: 40px;
  width: 40px;
  border-radius: 30px;
  padding-top: 4px;
  padding-bottom: 4px;
}
.ReplyStatsDiv {
  display: grid;
  grid-template-columns: 30px max-content 10px max-content 15px auto auto max-content 1px;
  background-color: black;
  width: 100vw;
  max-width: 400px;

  padding-top: 4px;
  padding-bottom: 10px;
}
.StatsDiv {
  display: grid;
  grid-template-columns: 30px max-content 10px max-content 15px auto auto max-content 1px;
  background-color: black;
  width: 100vw;
  max-width: 450px;
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
.ReplyContentDiv {
  width: 100vw;
  background-color: black;
  max-width: 365px;
  margin-left: auto;
  margin-right: auto;
  padding-left: 35px;
  padding-top: 10px;
}
.CommentDiv {
  width: 100vw;
  background-color: black;
  max-width: 400px;
  margin-left: auto;
  margin-right: auto;
  padding-left: 35px;
  padding-top: 10px;
}
.replyToCommentDiv {
  width: 100vw;
  background-color: black;
  max-width: 365px;
  margin-left: auto;
  margin-right: auto;
  padding-left: 35px;
  padding-top: 10px;
}
.userInReplyGrid {
  display: grid;
  grid-template-columns: 25px max-content 15px max-content auto max-content 25px;
  width: 100vw;
  max-width: 400px;
  background-color: black;
  padding-top: 5px;
}
.CommenterGrid {
  display: grid;
  grid-template-columns: 25px max-content 15px max-content auto max-content 25px;
  width: 100vw;
  max-width: 400px;
  background-color: black;
  padding-top: 5px;
}
.userGrid {
  display: grid;
  grid-template-columns: 25px max-content 15px max-content auto max-content 25px;
  width: 100vw;
  max-width: 450px;
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

@media screen and (max-width: 450px) {
  .Test {
    width: max-content;
    padding-right: 20px;
    margin-left: auto;
    margin-right: auto;
  }
  .replyInput {
    width: 180px;
  }
}
</style>
