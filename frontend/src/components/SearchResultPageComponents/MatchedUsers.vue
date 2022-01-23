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
      <div class="videosContainer" v-if="user.userVideos.length > 0">
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
    this.isDarkTheme = await this.$store.getters.getIsDarkTheme
    this.$store.watch((state) => state.darkTheme, (newVal) => {
    this.isDarkTheme = newVal
    })

    // if not logged in or refreshed and there is a save
    if(this.loggedInUser == null && localStorage.loggedInUser){
      this.loggedInUser = await JSON.parse(localStorage.loggedInUser)
    }
    // if not logged in or refreshed and there is no save
    else if(this.loggedInUser == null && !localStorage.loggedInUser){
      return;
    }
    
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
  height: 5rem;
  border-radius: 45px;
  width: 5rem;
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
  color: white;
}

.subContainer p,
.videoCountContainer p,
.textContainer p,
.userNameTEXT
 {
  font-size: smaller;
  font: menu;
  color: white;
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

.videosContainer {
  margin: 2% 0 2% 0;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  justify-items: center;
  margin: 0 1rem;
}

.videoCard {
  text-align: center;
  margin: 0.2rem;
  width: -webkit-fill-available;
}

.title {
  font-size: smaller;
   color: white;
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

@media screen and (max-width: 350px) {
  .profilePic {
  height: 8vh;
  border-radius: 45px;
  width: 14vw;
  margin-left:2vh;
}
.videosContainer {
  margin: 1 1rem;
}
.videoCard img {
  height: 10vh;
  width:11vh;
}
  .textContainer {
  grid-column: span 3;
  margin: 3%;
}
.subContainer p,
.videoCountContainer p,
.time {
  font-size: smaller;
}
.subContainer p,
.videoCountContainer p,
.textContainer p,
.userNameTEXT
 {
  font-size: smaller;
 }
  .title {
  font-size: smaller;
}

}
@media screen and (max-width:480px) and (min-width: 350px) {
   .profilePic {
  height: 6vh;
  border-radius: 45px;
  width: 12vw;
  margin-left:2vh;
}

.videosContainer {
  margin: 1 1rem;
}
.videoCard img {
  height: 12vh;
  width:12vh;
}
.subContainer p,
.videoCountContainer p,
.time {
  font-size: small;
}
.subContainer p,
.videoCountContainer p,
.textContainer p,
.userNameTEXT
 {
  font-size: small;
 }
  .title {
  font-size: small;
}

}

@media screen and (max-width:620px) and (min-width: 480px) {
     .profilePic {
  height: 8vh;
  border-radius: 45px;
  width: 14vw;
  margin-left:3vh;
}

.videoCard img {
  height: 16vh;
  width:18vh;
}
.subContainer p,
.videoCountContainer p,
.time {
  font-size: medium;
}
.subContainer p,
.videoCountContainer p,
.textContainer p,
.userNameTEXT
 {
  font-size: medium;
 }
  .title {
  font-size: medium;
}
}

@media screen and  (max-width:820px) and (min-width: 620px) {
   .profilePic {
  height: 10vh;
  border-radius: 45px;
  width: 12vw;
  margin-left:4vh;
}
.videoCard img {
  height: 16vh;
  width:18vh;
}
.subContainer p,
.videoCountContainer p,
.time {
  font-size: large;
}
.subContainer p,
.videoCountContainer p,
.textContainer p,
.userNameTEXT
 {
  font-size: large;
 }
  .title {
  font-size: large;
}



}

@media screen and (min-width:820px) {
    .profilePic {
  height: 9vh;
  border-radius: 55px;
  width: 12vw;
  margin-left:4vh;
}

.videoCard img {
  height: 18vh;
  width:20vh;
}
.subContainer p,
.videoCountContainer p,
.time {
  font-size: larger;
}
.subContainer p,
.videoCountContainer p,
.textContainer p,
.userNameTEXT
 {
  font-size: larger;
 }
  .title {
  font-size: larger;
}

@media screen and (min-width:1024px) {
       .profilePic {
  height: 12vh;
  border-radius: 55px;
  width: 10vw;
  margin-left:9vh;
}

.videoCard img {
  height: 22vh;
  width:30vh;
}
.subContainer p,
.videoCountContainer p,
.time {
  font-size: x-large;
}
.subContainer p,
.videoCountContainer p,
.textContainer p,
.userNameTEXT
 {
  font-size: x-large;
 }
  .title {
  font-size: x-large;
}

}
}

</style>





