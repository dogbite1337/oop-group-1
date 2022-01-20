<template>
  <div class="IconDiv">
    <div class="SpaceDiv" />
    <router-link :to="{ path: '/' }">
      <div v-if="darkTheme" class="DarkHomeDiv">
        <img class="HomeIcon" src="../projectImages/Dark_House.png" />
        <router-link :to="{ path: '/' }">
          <div class="HomeText">Home</div>
        </router-link>
      </div>
      <div v-if="!darkTheme" class="LightHomeDiv">
        <img class="HomeIcon" src="../projectImages/Light_House.png" />
        <router-link :to="{ path: '/' }">
          <div class="LightHomeText">Home</div>
        </router-link>
      </div>
    </router-link>
    <div class="SpaceDiv" />
    <div class="PantherDiv">
      <img
        v-if="darkTheme"
        class="PantherImage"
        src="../projectImages/panther.png"
      />
      <img
        v-if="!darkTheme"
        class="SmileImage"
        src="../projectImages/smile.png"
      />
    </div>
    <div class="SpaceDiv" />
    <div v-if="activeUser" class="loggedInRoute">
      <router-link :to="{ path: '/Profile/' + activeUser }">
        <div @click="seeIfLoggedIn" v-if="darkTheme" class="HomeDiv">
          <img class="ProfileIcon" src="../projectImages/Dark_User.png" />
          <div class="ProfileText">Profile</div>
        </div>
        <div @click="seeIfLoggedIn" v-if="!darkTheme" class="LightProfileDiv">
          <img class="ProfileIcon" src="../projectImages/Light_User.png" />
          <div class="LightProfileText">Profile</div>
        </div>
      </router-link>
    </div>
    <div v-if="!activeUser" class="LightProfileDiv">
      <img
        v-if="darkTheme"
        class="ProfileIcon"
        src="../projectImages/Dark_User.png"
      />
      <img
        v-if="!darkTheme"
        class="ProfileIcon"
        src="../projectImages/Light_User.png"
      />
      <div v-if="darkTheme" class="DarkProfileText">Profile</div>
      <div v-if="!darkTheme" class="LightProfileText">Profile</div>
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
      darkTheme: true,
    };
  },
  async mounted() {
    this.$store.subscribe(async (mutation, state) => {
      if (mutation.type == 'setDarkTheme') {
        if (mutation.payload) {
          this.darkTheme = true;
        } else {
          this.darkTheme = false;
        }
      }
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
  /* top: 100vh;
  width: 100vw; */
  position: sticky;
  bottom: 0;
}

.PantherDiv {
  height: 50px;
}
.PantherImage {
  height: 50px;
  transform: scaleX(2);
  position: relative;
  left: -7px;
}

.HomeIcon,
.ProfileIcon {
  height: 36px;
  width: 35px;
  background: transparent;
}

.ProfileIcon {
  border-radius: 100px;
}
.TextDiv {
  display: grid;
  color: white;
  grid-template-columns: auto auto auto auto auto;
  background-color: none;
  margin-top: -20px;
}

.SmileImage {
  width: 100vw;
  margin-top: -14px;
  padding-top: 7px;
  height: 240px;
  top: -100px;
  position: relative;
  overflow-y: clip;
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
.DarkProfileText {
  width: max-content;
  background-color: none;
  display: inline-block;
  color: white;
  font-weight: 400;
  font-family: 'Roboto', sans-serif;
  line-height: 14.06px;
  font-size: 12px;
  position: relative;
}

.LightHomeText {
  width: max-content;
  background-color: transparent;
  display: inline-block;
  color: black;
  font-weight: 400;
  font-family: 'Roboto', sans-serif;
  line-height: 14.06px;
  font-size: 12px;
  position: relative;
  left: 0px;
  top: -8px;
}
.HomeDiv,
.LightHomeDiv {
  margin-left: 35px;
}

.DarkHomeDiv {
  margin-left: 0px;
}

.LightHomeDiv {
  width: 35px;
  height: 60px;
  background-color: transparent;
  position: absolute;
  z-index: 5;
}
.LightProfileDiv {
  width: 35px;
  height: 60px;
  background-color: transparent;
  color: black;
  position: absolute;
  z-index: 5;
  right: 25px;
}

.LightProfileText {
  color: black;
  font-weight: 400;
  font-family: 'Roboto', sans-serif;
  line-height: 14.06px;
  font-size: 12px;
}
.IconDiv {
  max-height: max-content;
  overflow-y: clip;
}
</style>
