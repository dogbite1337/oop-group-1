<template>
  <div>
    <div v-if="!isAReply && darkTheme" class="DarkCommentGrid">
      <div class="mainGrid">
        <div class="SpaceDiv" />
        <div class="DarkUserGrid">
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
      <div class="DarkCommentDiv">
        <div class="commentGrid">
          {{ comment.content }}
          <div v-if="activeUser" class="trashIconDiv">
            <img
              v-if="
                activeUser.username == comment.postedByUsername && darkTheme
              "
              @click="removeComment"
              class="trashCanIcon"
              src="../projectImages/Trashcan.png"
            />
            <img
              v-if="
                activeUser.username == comment.postedByUsername && !darkTheme
              "
              @click="removeComment"
              class="trashCanIcon"
              src="../projectImages/Light_Trash.png"
            />
          </div>
        </div>
      </div>
    </div>
    <div v-if="!isAReply && !darkTheme" class="LightCommentGrid">
      <div class="mainGrid">
        <div class="SpaceDiv" />
        <div class="LightUserGrid">
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
      <div class="LightCommentDiv">
        <div class="commentGrid">
          {{ comment.content }}
          <div v-if="activeUser" class="trashIconDiv">
            <img
              v-if="
                activeUser.username == comment.postedByUsername && darkTheme
              "
              @click="removeComment"
              class="trashCanIcon"
              src="../projectImages/Trashcan.png"
            />
            <img
              v-if="
                activeUser.username == comment.postedByUsername && !darkTheme
              "
              @click="removeComment"
              class="trashCanIcon"
              src="../projectImages/Light_Trash.png"
            />
          </div>
        </div>
      </div>
    </div>
    <div v-if="comment && !isAReply && !darkTheme" class="LightStatsDiv">
      <div class="SpaceDiv" />
      <div class="LightLikesDiv">
        <img
          v-if="!likedCommentAlready && darkTheme"
          @click="like"
          class="commentLike"
          src="../projectImages/like_black_background.png"
        />
        <img
          v-if="!likedCommentAlready && !darkTheme"
          @click="like"
          class="commentLike"
          src="../projectImages/like_white_background.png"
        />
        <img
          v-if="likedCommentAlready && darkTheme"
          class="commentLike"
          src="../projectImages/blue_like.png"
        />
        <img
          v-if="likedCommentAlready && !darkTheme"
          class="commentLike"
          src="../projectImages/blue_like_whiteBackground.png"
        />
        <div class="SpaceDiv" />
        {{ comment.likes }}
      </div>
      <div class="SpaceDiv" />
      <div class="LightDislikesDiv">
        <img
          @click="dislike"
          class="commentDislike"
          src="../projectImages/dislike_black_background.png"
          v-if="!dislikedCommentAlready && darkTheme"
        />
        <img
          @click="dislike"
          class="commentDislike"
          src="../projectImages/dislike_white_background.png"
          v-if="!dislikedCommentAlready && !darkTheme"
        />
        <img
          class="commentDislike"
          src="../projectImages/blue_dislike.png"
          v-if="dislikedCommentAlready && darkTheme"
        />
        <img
          class="commentDislike"
          src="../projectImages/blue_dislike_whiteBackground.png"
          v-if="dislikedCommentAlready && !darkTheme"
        />
        <div class="SpaceDiv" />
        {{ comment.dislikes }}
      </div>
      <div class="SpaceDiv" />
      <div v-if="!expandedReply" class="ReplyDiv">
        <div
          v-if="darkTheme"
          @click="toggleReplyInput"
          class="DarkReplyTextDiv"
        >
          Reply
        </div>
        <div
          v-if="!darkTheme"
          @click="toggleReplyInput"
          class="LightReplyTextDiv"
        >
          Reply
        </div>
        <div class="SpaceBlock" />
        <div
          v-if="!showComments && darkTheme"
          @click="showReplies"
          class="DarkShowRepliesDiv"
        >
          Show Replies
        </div>
        <div
          v-if="!showComments && !darkTheme"
          @click="showReplies"
          class="LightShowRepliesDiv"
        >
          Show Replies
        </div>
        <div
          v-if="showComments"
          @click="hideReplies"
          class="DarkHideRepliesDiv"
        >
          Hide Replies
        </div>
        <div
          v-if="showComments"
          @click="hideReplies"
          class="LightHideRepliesDiv"
        >
          Hide Replies
        </div>
      </div>
      <div v-if="expandedReply" class="PostReplyDiv">
        <button class="cancelButton" @click="toggleReplyInput">Cancel</button>
        <div class="SpaceBlock" />
        <button
          @click="postReply"
          v-if="wantedReply.length > 0"
          class="postButton"
        >
          Reply To
        </button>
        <button
          disabled
          v-if="wantedReply.length == 0"
          class="disabledPostButton"
        >
          Reply To
        </button>
      </div>
      <div class="SpaceBlock" />
      <div class="timestampOfComments"></div>
      <div class="SpaceBlock" />
    </div>
    <div v-if="comment && !isAReply && darkTheme" class="DarkStatsDiv">
      <div class="SpaceDiv" />
      <div class="DarkLikesDiv">
        <img
          v-if="!likedCommentAlready"
          @click="like"
          class="commentLike"
          src="../projectImages/like_black_background.png"
        />
        <img
          v-if="likedCommentAlready"
          class="commentLike"
          src="../projectImages/blue_like.png"
        />
        <div class="SpaceDiv" />
        {{ comment.likes }}
      </div>
      <div class="SpaceDiv" />
      <div class="DarkDislikesDiv">
        <img
          @click="dislike"
          class="commentDislike"
          src="../projectImages/dislike_black_background.png"
          v-if="!dislikedCommentAlready"
        />
        <img
          class="commentDislike"
          src="../projectImages/blue_dislike.png"
          v-if="dislikedCommentAlready"
        />
        <div class="SpaceDiv" />
        {{ comment.dislikes }}
      </div>
      <div class="SpaceDiv" />
      <div v-if="!expandedReply" class="ReplyDiv">
        <div @click="toggleReplyInput" class="ReplyTextDiv">Reply</div>
        <div class="SpaceBlock" />
        <div v-if="!showComments" @click="showReplies" class="showRepliesDiv">
          Show Replies
        </div>
        <div v-if="showComments" @click="hideReplies" class="showRepliesDiv">
          Hide Replies
        </div>
      </div>
      <div v-if="expandedReply" class="PostReplyDiv">
        <button class="cancelButton" @click="toggleReplyInput">Cancel</button>
        <div class="SpaceBlock" />
        <button
          @click="postReply"
          v-if="wantedReply.length > 0"
          class="postButton"
        >
          Reply To
        </button>
        <button
          disabled
          v-if="wantedReply.length == 0"
          class="disabledPostButton"
        >
          Reply To
        </button>
      </div>
      <div class="SpaceBlock" />
      <div class="timestampOfComments"></div>
      <div class="SpaceBlock" />
    </div>
    <div v-if="expandedReply && !darkTheme" class="LightReplyOpenDiv">
      <div class="SpaceBlock" />
      <div v-if="activeUser" class="replyOpenGrid">
        <div class="SpaceBlock" />
        <img class="imgInReply" :src="activeUser.profileURL" />
        <div class="SpaceBlock" />
        <div class="replyInputDiv">
          <div class="SpaceBlock" />
          <div class="replyHolderDiv">
            <div class="yourReplyDiv">Your reply</div>
            <textarea
              name="yourReply"
              class="replyInput"
              rows="5"
              cols="33"
              placeholder="Write here.."
              v-model="wantedReply"
            >
            </textarea>
          </div>
          <div class="SpaceBlock" />
        </div>
        <div class="SpaceBlock" />
      </div>
      <div class="SpaceBlock" />
    </div>
    <div v-if="expandedReply && darkTheme" class="DarkReplyOpenDiv">
      <div class="SpaceBlock" />
      <div v-if="activeUser" class="replyOpenGrid">
        <div class="SpaceBlock" />
        <img class="imgInReply" :src="activeUser.profileURL" />
        <div class="SpaceBlock" />
        <div class="replyInputDiv">
          <div class="SpaceBlock" />
          <div class="replyHolderDiv">
            <div class="yourReplyDiv">Your reply</div>
            <textarea
              name="yourReply"
              class="replyInput"
              rows="5"
              cols="33"
              placeholder="Write here.."
              v-model="wantedReply"
            >
            </textarea>
          </div>
          <div class="SpaceBlock" />
        </div>
        <div class="SpaceBlock" />
      </div>
      <div class="SpaceBlock" />
    </div>
    <div v-if="showComments" class="commentersBox">
      <CommentReply
        v-for="(replyItem, index) of replies"
        :key="index"
        :commenter="commenters[index]"
        :reply="replyItem"
      />
    </div>
  </div>
</template>
<script>
import User from '../jsClasses/general/User';
import Comment from '../jsClasses/general/Comment';
import CommentReply from '../components/CommentReply.vue';
import store from '../store';

export default {
  components: {
    CommentReply,
  },
  emits: ["updateReplies", "removedAReply", "postedAReply"],
  props: ["activeId", "comment", "replies", "commenters"],
  name: "PostedComment",

  created() {},
  data() {
    return {
      User: null,
      expandedReply: false,
      wantedReply: '',
      timestampOfReplies: [],
      isAReply: this.comment.responseToCommentId == -1 ? false : true,
      isANumberInput: isNaN(this.comment.timeOfPosting),
      showComments: false,
      isActive: this.activeId == this.comment.commentId,
      activeUser: this.$store.getters.getCurrentUser,
      dislikedCommentAlready: false,
      likedCommentAlready: false,
      darkTheme: this.$store.getters.getIsDarkTheme,
    };
  },
  mounted() {
    this.darkTheme = this.$store.getters.getIsDarkTheme;
  },
  methods: {
    async showReplies() {
      if (this.isActive || this.activeId == 0) {
        this.$emit('updateReplies', this.comment.commentId);
        this.showComments = true;
      }
    },
    hideReplies() {
      this.$emit('updateReplies', 0);
      this.showComments = false;
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
    async removeComment() {
      let res = await fetch(
        '/api/removeComment?' +
          new URLSearchParams({
            commentId: this.comment.commentId,
            videoId: this.comment.relatesToVideoId,
          }),
        {
          method: 'DELETE',
        }
      );

      let response = await res.json();
      this.$emit('removedAReply', response);
    },
    async postReply() {
      let currentUser = new User();
      currentUser = Object.assign(
        currentUser,
        this.$store.getters.getCurrentUser
      );
      let myReply = new Comment(
        0,
        this.comment.relatesToVideoId,
        currentUser.username,
        this.wantedReply,
        0,
        0,
        this.comment.commentId,
        Date.now()
      );
      let res = await fetch('/api/postComment', {
        method: 'POST',
        body: JSON.stringify(myReply),
      });

      let response = await res.json();
      let newResponse = new Comment();
      newResponse = Object.assign(newResponse, response);
      this.commenters.push(currentUser);
      this.replies.push(newResponse);
      let minsAgo = (Date.now() - new Date(newResponse.timeOfPosting)) / 60000;
      if (minsAgo < 1.0) {
        this.timestampOfReplies.push('Less than a minute ago');
      } else if (minsAgo >= 1.0 && minsAgo <= 59) {
        this.timestampOfReplies.push(
          'About ' +
            Math.floor(minsAgo) +
            ' minute' +
            (minsAgo >= 2.0 ? 's ' : ' ') +
            'ago'
        );
      } else if (minsAgo >= 60 && minsAgo <= 1440) {
        this.timestampOfReplies.push(
          'About ' +
            Math.floor(minsAgo / 60) +
            ' hour' +
            (minsAgo / 60 >= 2.0 ? 's ' : ' ') +
            'ago'
        );
      } else if (minsAgo >= 1441 && minsAgo <= 10080) {
        this.timestampOfReplies.push(
          'About ' +
            Math.floor(minsAgo / 60 / 24) +
            ' day' +
            (Math.floor(minsAgo / 60 / 24) >= 2.0 ? 's ' : ' ') +
            'ago'
        );
      } else if (minsAgo >= 10081 && minsAgo <= 40324) {
        this.timestampOfReplies.push(
          Math.floor(minsAgo / 60 / 24 / 7) +
            ' week' +
            (Math.floor(minsAgo / 60 / 24 / 7) >= 2.0 ? 's ' : ' ') +
            'ago'
        );
      } else if (minsAgo >= 40325 && minsAgo <= 80648) {
        this.timestampOfReplies.push(
          'About ' +
            Math.floor(minsAgo / 60 / 24 / 7 / 30) +
            ' month' +
            (Math.floor(minsAgo / 60 / 24 / 7 / 30) >= 2.0 ? 's ' : ' ') +
            'ago'
        );
      } else {
        if (Math.floor(minsAgo / 60 / 24 / 7 / 30) > 12) {
          this.timestampOfReplies.push(
            'About ' +
              Math.floor(minsAgo / 60 / 24 / 7 / 30 / 12) +
              ' years ago'
          );
        } else {
          this.timestampOfReplies.push(
            'About ' + Math.floor(minsAgo / 60 / 24 / 7 / 30) + ' months ago'
          );
        }
      }
      this.expandedReply = false;
      this.$emit('postedAReply', this.replies);
    },
    toggleReplyInput() {
      if (this.expandedReply) {
        this.expandedReply = false;
      } else {
        this.expandedReply = true;
      }
    },
    async like() {
      if (this.$store.getters.getCurrentUser) {
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

        let registerlikedCommentRes = await fetch(
          '/api/registerLikeOnComment?' +
            new URLSearchParams({
              relatesToVideoId: this.$route.params.id,
              userId: this.$store.getters.getCurrentUser.userId,
              videoId: 0,
              commentId: this.comment.commentId,
            }),
          {
            method: 'POST',
            body: JSON.stringify(this.comment),
          }
        );

        this.likedCommentAlready = true;
      } else {
        alert('You have to log in to like Comments!');
      }
    },
    async dislike() {
      if (this.$store.getters.getCurrentUser) {
        let dislikedCommentRes = await fetch(
          '/api/dislikeComment?' +
            new URLSearchParams({
              relatesToVideoId: this.$route.params.id,
              commentId: this.comment.commentId,
              userId: this.$store.getters.getCurrentUser.userId,
            }),
          {
            method: 'POST',
            body: JSON.stringify(this.comment),
          }
        );
        let dislikedCommentResponse = await dislikedCommentRes.json();
        this.comment.dislikes = dislikedCommentResponse.dislikes;
        this.dislikedCommentAlready = true;
      } else {
        alert('You have to log in to dislike Comments!');
      }
    },
  },
  async mounted() {
    this.User = this.$store.getters.getCurrentUser;
    let commenterRes = await fetch(
      '/rest/getUserByUsername?' +
        new URLSearchParams({
          providedUsername: this.comment.postedByUsername,
        })
    );
    let commenterResponse = await commenterRes.json();
    let emptyUser = new User(0, '', '', '', 0, 0);
    this.User = Object.assign(emptyUser, commenterResponse);
    if (this.comment.responseToCommentId == -1) {
      this.isNotAReply = true;
    } else {
      this.isNotAReply = false;
    }
    let dislikesRes = await fetch(
      '/rest/getDislikesForComment?' +
        new URLSearchParams({
          commentId: this.comment.commentId,
        })
    );
    let dislikesResponse = await dislikesRes.json();
    for (let i = 0; i < dislikesResponse.length; i++) {
      if (this.$store.getters.getCurrentUser) {
        if (
          dislikesResponse[i].dislikedByUserId ==
          this.$store.getters.getCurrentUser.userId
        ) {
          this.dislikedCommentAlready = true;
        }
      }
    }

    let likesRes = await fetch(
      '/rest/getLikesForComment?' +
        new URLSearchParams({
          commentId: this.comment.commentId,
        })
    );
    let likesResponse = await likesRes.json();
    for (let i = 0; i < likesResponse.length; i++) {
      if (this.$store.getters.getCurrentUser) {
        if (
          likesResponse[i].likedByUserId ==
          this.$store.getters.getCurrentUser.userId
        ) {
          this.likedCommentAlready = true;
        }
      }
    }
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

.misc {
  width: max-content;
  height: max-content;
  background-color: black;
  margin-left: auto;
  margin-right: auto;
}

.DarkReplyTextDiv {
  color: white;
}
.LightReplyTextDiv {
  color: black;
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
  max-width: 600px;
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

.LightCommentGrid {
  background-color: white;
  color: white;
  border-top: solid 1px black;
}

.trashCanIcon {
  height: 30px;
  width: 30px;
  position: relative;
  top: -15px;
  right: 40px;
}
.DarkShowRepliesDiv {
  color: white;
}
.LightShowRepliesDiv {
  color: black;
}
.LightHideRepliesDiv {
  color: black;
}
.DarkHideRepliesDiv {
  color: white;
}
.imgInReply {
  height: 40px;
  width: 40px;
  border-radius: 30px;
  position: relative;
  left: 20px;
}
.replyOpenGrid {
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

.LightLikesDiv,
.LightDislikesDiv {
  display: grid;
  grid-template-columns: max-content 10px max-content;
  width: 65px;
  background-color: white;
  color: black;
  padding-top: 3px;
  padding-left: 3px;
  padding-right: 2px;
}

.DarkLikesDiv,
.DarkDislikesDiv {
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
.LightReplyOpenDiv {
  display: grid;
  width: 100vw;
  grid-template-columns: auto max-content auto;
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
  background-color: white;
  padding-bottom: 20px;
}
.DarkReplyOpenDiv {
  display: grid;
  width: 100vw;
  grid-template-columns: auto max-content auto;
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
  background-color: black;
  padding-bottom: 20px;
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
.DarkCommentGrid {
  height: max-content;
  background-color: black;
  color: white;
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
  padding-bottom: 10px;
}
.commentGrid {
  display: grid;
  grid-template-columns: auto 50px;
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

.LightStatsDiv {
  display: grid;
  grid-template-columns: 30px max-content 10px max-content 15px auto auto max-content 1px;
  background-color: white;
  width: 100vw;
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
  padding-top: 4px;
  padding-bottom: 10px;
}

.DarkStatsDiv {
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
.ReplyContentDiv {
  width: 100vw;
  background-color: black;
  max-width: 365px;
  margin-left: auto;
  margin-right: auto;
  padding-left: 35px;
  padding-top: 10px;
}
.LightCommentDiv {
  width: 100vw;
  background-color: white;
  color: black;
  max-width: 565px;
  margin-left: auto;
  margin-right: auto;
  padding-left: 35px;
  padding-top: 10px;
}
.DarkCommentDiv {
  width: 100vw;
  background-color: black;
  max-width: 565px;
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
.DarkUserGrid {
  display: grid;
  grid-template-columns: 25px max-content 15px max-content auto max-content 25px;
  width: 100vw;
  max-width: 600px;
  background-color: black;
  padding-top: 5px;
}
.LightUserGrid {
  display: grid;
  grid-template-columns: 25px max-content 15px max-content auto max-content 25px;
  width: 100vw;
  max-width: 600px;
  background-color: white;
  color: black;
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
  .replyOpenGrid {
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
