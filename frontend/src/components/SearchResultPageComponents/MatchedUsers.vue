<template>
  <div class="matchedUserListContainer">
    <div class="userCard" v-for="user in matchedUserList" :key="user.username">
      <div class="infoContainer">
        <div class="profilePicContainer">
          <img class="profilePic" :src="user.profileURL" alt="" />
        </div>

        <div class="otherInfoContainer">
          <div class="username">
            <p class="userNameTEXT">{{ user.username }}</p>
          </div>

          <div class="subAndVideo">
            <div class="subContainer">
              <p class="time">{{ 'Subscribers: ' + user.subscribers }}</p>
            </div>
            <div class="videoCountContainer">
              <p class="time">{{ 'Videos: ' + user.videosPosted }}</p>
            </div>
            <button
              class="subBtn"
              v-if="loggedInUser && loggedInUser.userId != user.userId"
            >
              + Subscribe
            </button>
          </div>
        </div>
      </div>

      <div class="videosContianer" v-if="user.userVideos.length > 0">
        <div
          class="videoCard"
          v-for="video in userVideos(user)"
          :key="video.title"
          @click="goToVideoPage(video)"
        >
          <img
            class="thumbnail"
            :src="
              'https://img.youtube.com/vi/' +
              video.videoURL.substring(32, 43) +
              '/default.jpg'
            "
            alt=""
          />
          <p class="title">{{ video.title }}</p>
          <p class="time">{{ uploadTime(video.uploadDate) }}</p>
        </div>
        <div class="textContainer" v-if="user.userVideos.length > 2">
          <p>{{ 'check all ' + user.videosPosted + ' videos >' }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['matchedUserList'],

  created() {},

  data() {
    return {
      loggedInUser: this.$store.getters.getCurrentUser,
    };
  },

  methods: {
    goToVideoPage(video) {
      this.$router.push('/VideoPage/' + video.videoId);
    },
    userVideos(user) {
      let orderedList = user.userVideos.sort(
        (a, b) => new Date(b.uploadDate) - new Date(a.uploadDate)
      );

      if (orderedList.length > 3) {
        return orderedList.slice(0, 3);
      } else {
        return orderedList;
      }
      // user.userVideos.length > 3 ? user.userVideos.slice(0,3) : user.userVideos
    },

    uploadTime(timeInMilSec) {
      let unixNow = Math.round(+new Date() / 1000);
      let unix24Hours = 24 * 60 * 60;
      let result = unixNow - timeInMilSec / 1000;

      if (result < unix24Hours) {
        let hour = Math.floor(result / 24 / 60 / 60);
        return (hour == 0 ? 1 : hour) + ' hours ago';
      } else {
        let t = new Date(timeInMilSec);
        if (t.getFullYear() != new Date().getFullYear()) {
          let time =
            t.getFullYear() +
            '-' +
            (t.getMonth() + 1 < 10
              ? '0' + (t.getMonth() + 1).toString()
              : t.getMonth() + 1) +
            '-' +
            (t.getDate() < 10 ? '0' + t.getDate() : t.getDate());
          return time;
        } else {
          let time =
            (t.getMonth() + 1 < 10
              ? '0' + (t.getMonth() + 1).toString()
              : t.getMonth() + 1) +
            '-' +
            (t.getDate() < 10 ? '0' + t.getDate() : t.getDate());
          return time;
        }
      }
    },
  },
};
</script>

<style scoped>
p {
  color: white;
}

.infoContainer {
  display: grid;
  grid-template-columns: 16vw auto;
  margin: 5%;
}

.profilePic {
  height: 7vh;
  border-radius: 45px;
  width: 16vw;
}

.otherInfoContainer {
  margin-left: 3vw;
  display: flex;
  flex-direction: column;
  align-self: center;
}

.subAndVideo {
  display: grid;
  align-items: center;
  grid-template-columns: repeat(3, 33%);
}

/* .username{
    margin-bottom: 5vh;
} */

.subContainer p,
.videoCountContainer p,
.time {
  font-size: smaller;
  font: menu;
  color: grey;
}

.subBtn {
  background: #e75858;
  color: white;
  padding: 3px;
}

.videoCard img {
  height: 8vh;
}

.videosContianer {
  margin: 2% 0 2% 0;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  justify-items: center;
}

.videoCard {
  text-align: center;
}

.title {
  font-size: smaller;
}

.textContainer {
  grid-column: span 3;
  margin: 3%;
}

.textContainer p,
.userNameTEXT {
  color: #e75858;
}

.userCard {
  border-bottom: solid 1px #bfbfbf;
}
</style>
