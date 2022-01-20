<template>
  <div class="IconDiv">
    <div class="SpaceDiv" />
    <div class="firstBox">
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
    </div>
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
    <div class="SecondBox">
      <div @click="goToProfilePage" v-if="darkTheme" class="DarkProfileDiv">
        <img class="HomeIcon" src="../projectImages/Dark_User.png" />

        <div class="HomeText">Profile</div>
      </div>
      <div @click="goToProfilePage" v-if="!darkTheme" class="LightProfileDiv">
        <img class="HomeIcon" src="../projectImages/Light_User.png" />
        <div class="LightHomeText">Profile</div>
      </div>
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
      activeUser: this.$store.getters.getCurrentUser,
      darkTheme: this.$store.getters.getIsDarkTheme,
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
    this.darkTheme = this.$store.getters.getIsDarkTheme;
  },
  methods: {
    goToProfilePage() {
      if (this.$store.getters.getCurrentUser) {
        this.$router.push(
          '/Profile/' + this.$store.getters.getCurrentUser.username
        );
      }
      else{
        this.$router.push(
          '/Login'
        )
      }
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

.IconDiv {
  display: grid;
  grid-template-columns: 5px 165px 10px 100px 25px 165px 5px;
  height: 60px;
  text-align: center;
  background-color: black;
  /* top: 100vh;
  width: 100vw; */
  position: sticky;
  bottom: 0;
  padding-top: 2px;
  padding-bottom: 7px;
}

.firstBox {
  width: 120px;
}

.SecondBox {
  width: 120px;
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
  left: -180px;
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
.DarkHomeDiv,
.LightHomeDiv {
  padding-top: 7px;
}
.DarkProfileDiv,
.LightProfileDiv {
  padding-top: 10px;
  margin-left: 40px;
}
.LightHomeDiv {
  width: 35px;
  height: 60px;
  background-color: transparent;
  position: absolute;
  z-index: 5;
  left: 20px;
}
.LightProfileDiv {
  width: 35px;
  height: 60px;
  background-color: transparent;
  color: black;
  position: absolute;
  z-index: 5;
  right: 25px;
  padding-top: 7px;
  padding-right: 10px;
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
