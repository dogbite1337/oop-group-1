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
              <p class="time">{{ 'Videos: ' + user.userVideos.length }}</p>
            </div>
            <button
              :class="'subBtn ' + user.userId"
              v-if="
                loggedInUser &&
                loggedInUser.userId != user.userId &&
                !subscribedAlready
              "
              @click="subscribe"
            >
              + Subscribe
            </button>
            <button
              :class="'unsubButton ' + user.userId"
              v-if="
                loggedInUser &&
                loggedInUser.userId != user.userId &&
                subscribedAlready
              "
              @click="unsubscribe"
            >
              - Unsubscribe
            </button>
          </div>
        </div>
      </div>

      <div class="videosContianer" v-if="user.userVideos.length > 0">
        <div
          class="videoCard"
          :class="
            isDarkTheme == true ? 'videoCardDarkTheme' : 'videoCardLightTheme'
          "
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
          <p
            class="title"
            :class="isDarkTheme == true ? 'titleDarkTheme' : 'titleLightTheme'"
          >
            {{ video.title }}
          </p>
          <p class="time">{{ uploadTime(video.uploadDate) }}</p>
        </div>
        <div class="textContainer" v-if="user.userVideos.length > 2">
          <p>{{ 'check all ' + user.userVideos.length + ' videos >' }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import User from '../../jsClasses/general/User';
export default {
  props: ['matchedUserList'],

  data() {
    return {
      loggedInUser: this.$store.getters.getCurrentUser,
      isDarkTheme: true,
      subscribedAlready: false,
    };
  },

  async created() {
    this.isDarkTheme = await this.$store.getters.getIsDarkTheme;
    this.$store.watch(
      (state) => state.darkTheme,
      (newVal) => {
        this.isDarkTheme = newVal;
      }
    );
    if (this.loggedInUser) {
      let subsRes = await fetch(
        '/rest/getSubscribersForId?' +
          new URLSearchParams({
            userId: this.loggedInUser.userId,
          })
      );
      let subscribersResponse = await subsRes.json();
      for (let e = 0; e < this.matchedUserList.length; e++) {
        for (let i = 0; i < subscribersResponse.length; i++) {
          if (subscribersResponse[i].userId == this.matchedUserList[e].userId) {
            this.subscribedAlready = true;
          }
        }
      }
    }
  },

  methods: {
    async unsubscribe(e) {
      let targetUserId = e.target.className.split(' ')[1];

      let unsubscribeRes = await fetch(
        '/api/unsubscribe?' +
          new URLSearchParams({
            targetId: targetUserId,
            userId: this.loggedInUser.userId,
          }),
        {
          method: 'DELETE',
        }
      );
      let subscribersResponse = await unsubscribeRes.json();

      for (let i = 0; i < this.matchedUserList.length; i++) {
        if ((this.matchedUserList[i].userId = targetUserId)) {
          this.matchedUserList[i].subscribers = subscribersResponse;
          break;
        }
      }

      this.subscribedAlready = false;
    },
    async subscribe(e) {
      let targetUserId = e.target.className.split(' ')[1];
      let res = await fetch(
        '/api/subscribe?' +
          new URLSearchParams({
            targetId: targetUserId,
            userId: this.loggedInUser.userId,
          }),
        {
          method: 'POST',
        }
      );
      let subscribersResponse = await res.json();

      for (let i = 0; i < this.matchedUserList.length; i++) {
        if ((this.matchedUserList[i].userId = targetUserId)) {
          this.matchedUserList[i].subscribers = subscribersResponse;
          break;
        }
      }

      this.subscribedAlready = true;
    },
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

.unsubButton {
  background: #e75858;
  color: white;
  padding: 3px;
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
  margin: 0 1rem;
}

.videoCard {
  text-align: center;
  margin: 0 0.2rem;
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

.titleLightTheme {
  color: black;
}

.videoCardLightTheme {
  border: black 1px solid;
}
</style>
