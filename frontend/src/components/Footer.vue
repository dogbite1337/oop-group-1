<template>
  <div class="IconDiv">
    <div class="SpaceDiv" />
    <router-link :to="{ path: '/' }">
      <div class="HomeDiv">
        <img class="HomeIcon" src="../projectImages/Dark_House.png" />
        <router-link :to="{ path: '/' }">
          <div class="HomeText">Home</div>
        </router-link>
      </div>
    </router-link>
    <div class="SpaceDiv" />
    <div class="PantherDiv">
      <img class="PantherImage" src="../projectImages/panther.png" />
    </div>
    <div class="SpaceDiv" />
    <div v-if="activeUser" class="loggedInRoute">
      <router-link :to="{ path: '/Profile/' + activeUser }">
        <div @click="seeIfLoggedIn" class="HomeDiv">
          <img class="ProfileIcon" src="../projectImages/Dark_User.png" />
          <div class="ProfileText">Profile</div>
        </div>
      </router-link>
    </div>
    <div v-if="!activeUser" class="HomeDiv">
      <img class="ProfileIcon" src="../projectImages/Dark_User.png" />
      <div class="ProfileText">Profile</div>
    </div>
    <div class="SpaceDiv" />
  </div>
</template>
<script>
import store from '../store';

export default {
  name: 'Footer',
  data() {
    return {
      activeUser: '',
    };
  },
  async mounted() {
    this.$store.subscribe(async (mutation, state) => {
      if (mutation.type == 'setUser') {
        if (mutation.payload) {
          this.activeUser = mutation.payload.username;
        } else {
          this.activeUser = null;
        }
      }
    });
  },
  methods: {
    /* Deletes a Video and all related likes, dislikes, replies/comments */
    async deleteVideo() {
      let deleteVideoRes = await fetch(
        '/api/deleteVideo?' +
          new URLSearchParams({
            videoId: 18,
          }),
        {
          method: 'DELETE',
        }
      );
    },
    /* Updates a Videos URL, title, description */
    async updateVideo() {
      let video = {
        videoId: 1,
        videoURL: 'https://www.youtube.com/watch?v=axsnM1kH99c',
        title: 'hello',
        description: 'No u',
      };
      let res = await fetch('/api/updateVideo', {
        method: 'POST',
        body: JSON.stringify(video),
      });
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

.IconDiv {
  display: grid;
  grid-template-columns: auto max-content auto auto auto max-content auto;
  height: 58px;
  text-align: center;
  background-color: black;
  padding-top: 7px;
  top: 100vh;
  width: 100vw;
  position: sticky;
}

.PantherDiv {
  height: 50px;
}
.PantherImage {
  height: 50px;
  transform: scaleX(2);
  position: relative;
  left: 10px;
}

.HomeIcon,
.ProfileIcon {
  height: 36px;
  width: 35px;
}

.TextDiv {
  display: grid;
  color: white;
  grid-template-columns: auto auto auto auto auto;
  background-color: none;
  margin-top: -20px;
}

.HomeText,
.ProfileText {
  width: max-content;
  background-color: none;
  display: inline-block;
  color: white;
  font-weight: 400;
  font-family: 'Roboto', sans-serif;
  line-height: 14.06px;
  font-size: 12px;
  position: relative;
  left: -35px;
  top: 13px;
}
.HomeDiv {
  margin-left: 35px;
}
</style>
