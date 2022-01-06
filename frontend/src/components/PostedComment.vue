<template>
  <div class="CommentGrid">
    <div class="upperSection">
      <img v-if="User" :src="User.profileURL" class="commentImage" />
      <div class="usernameDiv">{{User.username}}</div>
      <div>Posted at {{comment.timeOfPosting}}</div>
    </div>
    <div>{{comment.content}}</div>
    
  </div>
  <div v-if="comment" class="StatsDiv">
    <div class="LikesDiv">
      {{comment.likes}}
    </div>
    <div class="DislikesDiv">
      {{comment.dislikes}}
    </div>
  </div>
</template>
<script>

import User from '../jsClasses/general/User'
export default {
  props: ['comment'],
  name: 'PostedComment',
  mounted() {},
  data() {
    return {
      User: null
    };
  },
  methods: {

  },
  async mounted() {
    console.log(this.comment.likes);
    let commenterRes = await fetch(
      '/rest/getUserByUsername?' +
        new URLSearchParams({
          providedUsername: this.comment.postedByUsername,
        })
    );
    let commenterResponse = await commenterRes.json();
    let emptyUser = new User(0, '', '', '', 0, 0);
    this.User = Object.assign(emptyUser, commenterResponse);
    console.log(this.User);
  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

.CommentGrid {
  height: max-content;
  background-color: red;
  color: white;
  max-width: 1000px;
  margin-left: auto;
  margin-right: auto;
}
.commentImage{
  height: 40px;
  width: 40px;
  border-radius: 30px;
}
.StatsDiv{
  background-color: green;
  max-width: 1000px;
  margin-left: auto;
  margin-right: auto;
}
</style>
