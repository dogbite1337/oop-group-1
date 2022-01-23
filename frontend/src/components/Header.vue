<template>
  <div
    class="HeaderDiv"
    :style="{
      'background-image':
        isDarkTheme == true
          ? 'url(' + '/src/projectImages/ghosts.gif' + ')'
          : 'url(' + 'src/projectImages/WinterWarm.gif' + ')',
      'background-size': isDarkTheme == true ? '100% 120px' : 'cover',
    }"
  >
    <div class="SpaceDiv" />
    <p @click="resetToStartPage" class="KittyText">KittyKitty</p>
    <div class="SpaceDiv" />
    <div class="switchContainer">
      <input
        type="checkbox"
        id="checkbox"
        class="themeSwitch"
        @change="changedTheme"
      />
      <label
        for="checkbox"
        class="switch"
        :class="isDarkTheme == true ? 'switchDarkTheme' : 'switchLightTheme'"
      >
        <img src="src/projectImages/sun.png" alt="" class="sun" />
        <img src="src/projectImages/moon.png" alt="" class="moon" />
        <div
          class="switchItem"
          :class="
            isDarkTheme == true ? 'switchItemDarkTheme' : 'switchItemLightTheme'
          "
        ></div>
      </label>
    </div>
    <p class="SeasonText">Winter</p>
    <div class="SpaceDiv" />
  </div>
  <div v-if="!isDarkTheme" class="LightSearchAndLoginDiv">
    <div class="SpaceDiv" />
    <img
      class="CatInHeader"
      src="../projectImages/Cat no background.png"
      @click="playMe"
    />
    <audio ref="meow" src="src/projectImages/meowSound.mp3"></audio>
    <div class="SpaceDiv2 SpaceDiv" />
    <div class="searchDiv">
      <router-link class="SearchLink" :to="{ path: '/Search' }">
        <img
          class="iconInSearchField"
          src="../projectImages/magnifying_glass.png"
        />
        <input
          v-on:keyup.enter="search"
          @change="setKeyWord"
          @click="resetKey"
          v-model="searchParam"
          class="SearchField"
          type="text"
          placeholder="Search.."
        />
      </router-link>
      <div class="SpaceDiv" />
    </div>
    <div class="SpaceDiv" />
    <router-link v-if="!$store.getters.getCurrentUser" :to="{ path: '/Login' }">
      <input class="LoginButton" type="button" value="Login" />
    </router-link>
    <img
      v-else
      class="profilePic"
      @click="toggleProfileDropdown"
      :src="profilePic"
    />
    <div class="SpaceDiv" />
    <div class="SpaceDiv" />
    <div class="SpaceDiv" />
    <div v-if="profileDropdown && !isDarkTheme" class="profile-dropdown">
      <ul v-if="$store.getters.getCurrentUser" class="LightUlMenu">
        <router-link
          :to="{ path: '/Profile/' + $store.getters.getCurrentUser.username }"
        >
          <li class="LightProfileLink" v-if="!isDarkTheme">My Profile</li>
        </router-link>
        <li v-if="!isDarkTheme" @click="uploadNavigation">Upload Video</li>
        <li v-if="!isDarkTheme" @click="logout">Logout</li>
      </ul>
    </div>
    <div class="SpaceDiv" />
  </div>
  <div v-if="isDarkTheme" class="DarkSearchAndLoginDiv">
    <div class="SpaceDiv" />
    <img
      class="CatInHeader"
      src="../projectImages/Cat no background.png"
      @click="playMe"
    />
    <audio ref="meow" src="src/projectImages/meowSound.mp3"></audio>
    <div class="SpaceDiv2 SpaceDiv" />
    <div class="searchDiv">
      <router-link class="SearchLink" :to="{ path: '/Search' }">
        <img
          class="iconInSearchField"
          src="../projectImages/magnifying_glass.png"
        />
        <input
          v-on:keyup.enter="search"
          @change="setKeyWord"
          @click="resetKey"
          v-model="searchParam"
          class="SearchField"
          type="text"
          placeholder="Search.."
        />
      </router-link>
      <div class="SpaceDiv" />
    </div>
    <div class="SpaceDiv" />
    <router-link v-if="!$store.getters.getCurrentUser" :to="{ path: '/Login' }">
      <input class="LoginButton" type="button" value="Login" />
    </router-link>
    <img
      v-else
      class="profilePic"
      @click="toggleProfileDropdown"
      :src="profilePic"
    />
    <div class="SpaceDiv" />
    <div class="SpaceDiv" />
    <div class="SpaceDiv" />
    <div v-if="profileDropdown" class="profile-dropdown">
      <ul v-if="$store.getters.getCurrentUser" class="DarkUlMenu">
        <router-link
          :to="{ path: '/Profile/' + $store.getters.getCurrentUser.username }"
        >
          <li class="DarkProfileLink" v-if="isDarkTheme">My Profile</li>
        </router-link>
        <li v-if="isDarkTheme" @click="uploadNavigation">Upload Video</li>
        <li v-if="isDarkTheme" @click="logout">Logout</li>
      </ul>
    </div>
    <div class="SpaceDiv" />
  </div>
</template>
<script>
import store from '../store';
export default {
  name: 'Header',
  emits: ['update'],
  data() {
    return {
      isLoggedIn: false,
      searchParam: '',
      profilePic: this.$store.getters.getCurrentUser
        ? this.$store.getters.getCurrentUser.getProfileURL()
        : '',
      profileDropdown: false,
      isDarkTheme: true,
    };
  },
  created() {
    this.isDarkTheme = this.$store.getters.getIsDarkTheme;
    if (this.isDarkTheme == null) {
      this.isDarkTheme = true;
    }
    let route = this.$router.currentRoute.value.fullPath;
    if (route == '/SearchResult') {
      this.searchParam = this.$store.getters.getKeyWord;
    }

    this.$store.watch(
      (state) => state.darkTheme,
      (newVal) => {
        this.isDarkTheme = newVal;
      }
    );
  },

  async mounted() {
    await this.$store.dispatch('whoAmI');
    if (this.$store.getters.getCurrentUser) {
      this.profilePic = this.$store.getters.getCurrentUser.getProfileURL();
    }
    this.$store.subscribe(async (mutation, state) => {
      console.log(mutation);
      if (mutation.payload) {
        this.isDarkTheme = true;
      } else {
        this.isDarkTheme = false;
      }
    });
  },

  methods: {
    async changedTheme() {
      if (this.isDarkTheme) {
        this.isDarkTheme = false;
        await this.$store.dispatch('setDarkTheme', this.isDarkTheme);
        localStorage.setItem('isDarkTheme', JSON.stringify(this.isDarkTheme));
      } else {
        this.isDarkTheme = true;
        await this.$store.dispatch('setDarkTheme', this.isDarkTheme);
        localStorage.setItem('isDarkTheme', JSON.stringify(this.isDarkTheme));
      }
      console.log(this.isDarkTheme);
    },

    playMe() {
      this.$refs.meow.play();
    },

    fetchKeyWord() {
      this.searchParam = '';
    },

    resetToStartPage() {
      this.$store.dispatch('resetToStartPage', true);
      this.searchParam = '';
      this.$router.push('/');
    },

    toggleProfileDropdown() {
      if (!this.profileDropdown) {
        this.profileDropdown = true;
      } else {
        this.profileDropdown = false;
      }
    },
    async logout() {
      await this.$store.dispatch('logout');
      this.toggleProfileDropdown();
      this.$router.push('/');
    },
    uploadNavigation() {
      this.$router.push('Upload');
    },
    search() {
      this.setKeyWord;
      this.$emit('update');
    },
    setKeyWord() {
      this.$store.dispatch('setKeyWord', this.searchParam);
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

* {
  overflow-x: hidden;
  text-decoration: none;
}

.sun {
  margin-left: -13px;
}
.searchDiv {
  display: grid;
  grid-template-columns: max-content max-content 10px;
  margin-left: 16px;
  margin-right: 30px;
}

@media screen and (max-width: 380px) {
  .searchDiv {
    margin-left: 5px;
    margin-right: 10px;
  }
}
.searchIcon {
  height: 20px;
  width: 20px;
  z-index: 3;
  color: white;
  background-color: green;
  display: inline;
}

.iconInSearchField {
  position: relative;
  top: 5px;
  left: 15px;
  display: inline;
  height: 20px;
  width: 20px;
  z-index: 3;
  margin-left: 20px;
  margin-top: 5px;
  opacity: 60%;
}

.SearchField {
  height: 30px;
  border-radius: 30px;
  width: 40vw;
  max-width: 290px;
  min-width: 100px;
  padding-left: 37px;
  display: inline;
  outline: none;
  margin-left: -20px;
}

.CatInHeader {
  width: 43px;
  height: 50px;
  margin-top: -7px;
}

.KittyText {
  -webkit-text-stroke-width: 0.007px;
  -webkit-text-stroke-color: #c9c9c9;
  font-family: 'Revalia', cursive;
  font-style: normal;
  font-weight: 700;
  font-size: 15px;
  line-height: 19px;
  color: #ffffff;
  text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25), 0px 4px 4px rgba(0, 0, 0, 0.25);
  margin-top: 16px;
  grid-row: span 2;
}

.SeasonText {
  grid-row: 2;
  grid-column: 4;
  -webkit-text-stroke-width: 0.007px;
  -webkit-text-stroke-color: #c9c9c9;
  font-family: 'Revalia', cursive;
  font-style: normal;
  font-weight: 700;
  font-size: 15px;
  line-height: 19px;
  color: #ffffff;
  text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25), 0px 4px 4px rgba(0, 0, 0, 0.25);
  /* margin-top: 33px; */
}

.HeaderDiv {
  display: grid;
  grid-template-columns: 16px 100px auto 20%;
  grid-template-rows: repeat(2, 50%);
  height: 60px;
  text-align: center;
  background-image: url('../projectImages/ghosts.gif');
  background-size: 100% 120px;
  background-repeat: no-repeat;
  max-width: 725px;
  margin-left: auto;
  margin-right: auto;
  overflow-y: hidden;
}

.SeasonText {
  overflow-y: hidden;
}
.moon {
  margin-left: 3px;
}
.LightSearchAndLoginDiv {
  display: grid; /* Margin, Cat, Margin, search icon, margin, Search Field, margin, Login Button, Margin */
  grid-template-columns: auto 43px auto max-content auto auto;
  background-color: white;
  padding-top: 16px;
  border-bottom: solid 1px #bfbfbf;
  max-width: 100vw;
  margin-left: auto;
  margin-right: auto;
  padding-bottom: 3px;
}
.DarkSearchAndLoginDiv {
  display: grid; /* Margin, Cat, Margin, search icon, margin, Search Field, margin, Login Button, Margin */
  grid-template-columns: auto 43px auto max-content auto auto;
  background-color: black;
  padding-top: 16px;
  border-bottom: solid 1px #bfbfbf;
  max-width: 100vw;
  margin-left: auto;
  margin-right: auto;
  padding-bottom: 3px;
}
.DarkUlMenu {
  height: max-content;
  width: 100vw;
  z-index: 5;
  color: white;
  position: absolute;
  background-color: transparent;
  text-align: center;
  padding-top: 10px;
  left: 0px;
  text-decoration: none;
}
.LightUlMenu {
  height: max-content;
  width: 100vw;
  z-index: 5;
  color: black;
  position: absolute;
  background-color: transparent;
  text-align: center;
  padding-top: 10px;
  left: 0px;
  text-decoration: none;
}
.DarkProfileLink {
  color: white;
}
.LightProfileLink,
.LightProfileLink:visited {
  color: black;
}
.LoginButton {
  border-radius: 30px;
  width: 40px;
  height: 40px;
  background-color: #f0f0f0;
  border: 0.7px solid #000000;
  color: black;
  margin-bottom: 15px;
  margin-right: 17px;
  text-align: center;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  margin-top: 4px;
}

.SearchLink {
  text-decoration: none;
}
.LoginText {
  margin-top: 12.5px;
  font-size: 12px;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  line-height: 14.06px;
}

.profilePic {
  height: 40px;
  width: 40px;
  border-radius: 30px;
}
.SpaceDiv {
  height: 10px;
  width: 10px;
}
.switchLightTheme,
.switchDarkTheme {
  max-width: 48px;
}
.switchItemLightTheme,
.switchItemDarkTheme {
  max-width: 19px;
}
.DarkLi {
  text-align: center;
  color: white;
  font-family: 'Roboto', sans-serif;
  margin-top: 10px;
}
.LightLi {
  text-align: center;
  color: black;
  font-family: 'Roboto', sans-serif;
  margin-top: 10px;
}
.profile-dropdown {
  height: max-content;
  padding-bottom: 60px;
}

.switchContainer {
  grid-column: 4;
  grid-row: 1;
  text-align: -webkit-center;
  align-self: center;
}

.themeSwitch {
  opacity: 0;
  position: absolute;
}

/* .themeSwitch:checked + .switch .switchItem, */
.switchItemLightTheme {
  transform: translateX(6.5vw);
}

.switchItemDarkTheme {
  transform: none;
}

.switchItem {
  height: 2.4vh;
  width: 22px;
  background-color: #242222;
  border-radius: 50%;
  position: absolute;
  top: 0;
  left: -2px;
  transition: transform 0.2s linear;
}

.switch {
  position: relative;
  display: grid;
  grid-template-columns: repeat(2, 50%);
  max-width: 48px;
  background-color: #9c9c9c;
  height: 2.5vh;
  width: 48px;
  border-radius: 50px;
  padding: 5px;
  place-items: center;
  box-sizing: border-box;
}

@media screen and (max-width: 300px) {
  .SearchAndLoginDiv {
    grid-template-columns: 5px 15px 10px 199px 1px 50px 1px 1px 1px 1px 10px;
  }
}
@media screen and (min-width: 301px) {
  .SearchAndLoginDiv {
    grid-template-columns: 5px 15px 10px 210px auto 50px 1px 1px 1px 1px 10px;
  }
}
@media screen and (min-width: 335px) {
  .SearchAndLoginDiv {
    grid-template-columns: 5px 15px 10px 230px auto 50px 1px 1px 1px 1px 10px;
  }
}
@media screen and (min-width: 370px) {
  .SearchAndLoginDiv {
    grid-template-columns: 5px 15px 10px max-content auto 50px 1px 1px 1px 1px 10px;
  }
}
</style>
