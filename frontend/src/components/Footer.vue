<template>
  <div
    class="IconDiv"
    :style="{
      'background-image':
        darkTheme == true
          ? 'url(' + '/src/projectImages/panther.png' + ')'
          : 'url(' + 'src/projectImages/smile.png' + ')',
      'background-position-x': darkTheme == true ? '1rem' : 'unset',
      'background-size': darkTheme == true ? '100vw 30rem' : '100vw 15rem',
    }"
  >
    <div class="firstBox">
      <router-link :to="{ path: '/' }">
        <div v-if="darkTheme" class="DarkHomeDiv" @click="goToHome">
          <img class="HomeIcon" src="../projectImages/Dark_House.png" />
          <div class="HomeText">Home</div>
        </div>
        <div v-if="!darkTheme" class="LightHomeDiv" @click="goToHome">
          <img class="HomeIcon" src="../projectImages/Light_House.png" />
          <div class="LightHomeText">Home</div>
        </div>
      </router-link>
    </div>
    <div class="SecondBox">
      <div @click="goToProfilePage" v-if="darkTheme" class="DarkProfileDiv">
        <img class="HomeIcon" src="../projectImages/Dark_User.png" />
        <div class="DarkHomeText">Profile</div>
      </div>
      <div @click="goToProfilePage" v-if="!darkTheme" class="LightProfileDiv">
        <img class="HomeIcon" src="../projectImages/Light_User.png" />
        <div class="LightHomeText">Profile</div>
      </div>
    </div>
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
    goToHome() {
      this.$router.push('/');
    },

    goToProfilePage() {
      if (this.$store.getters.getCurrentUser) {
        this.$router.push(
          '/Profile/' + this.$store.getters.getCurrentUser.username
        );
      } else {
        this.$router.push('/Login');
      }
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

.IconDiv {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  height: 60px;
  text-align: center;
  background-color: black;

  position: sticky;
  bottom: 0;
  padding-top: 2px;
  padding-bottom: 7px;
}

.firstBox {

  grid-column: 1;
}

.SecondBox {
  grid-column: 5;
}
.PantherDiv {
  height: 50px;
  position: relative;
  max-width: 360px;
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
  border-radius: 30px;
  align-self: center;
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
  left: -200px;
  /* position: relative; */
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
  /* position: relative; */
  left: -35px;
  top: 13px;
}
.DarkHomeText {
  width: max-content;
  background-color: transparent;
  /* display: inline-block; */
  color: white;
  font-weight: 400;
  font-family: 'Roboto', sans-serif;
  line-height: 14.06px;
  font-size: 12px;
  /* position: relative; */
  left: 0px;
  top: -8px;
}

.LightHomeText {
  width: max-content;
  background-color: transparent;
  /* display: inline-block; */
  color: black;
  font-weight: 400;
  font-family: 'Roboto', sans-serif;
  line-height: 14.06px;
  font-size: 12px;
  /* position: relative; */
  /* left: 0px;
  top: -8px; */
  align-self: center;
}
.DarkHomeDiv,
.LightHomeDiv {
  padding-top: 7px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.DarkProfileDiv,
.LightProfileDiv {
  padding-top: 7px;
  border-radius: 30px;
  text-align: -webkit-center;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.LightProfileDiv,
.DarkProfileDiv {
  background-color: transparent;
  color: black;
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
  background-image: url(/src/projectImages/panther.png);
  background-repeat: no-repeat;
  background-position: center;
  background-size: 100vw 30rem;
  background-position-x: 1rem;
}
</style>
