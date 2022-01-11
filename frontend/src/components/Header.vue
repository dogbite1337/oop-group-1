<template>
  <div class="HeaderDiv">
    <div class="SpaceDiv" />
    <p @click="resetToStartPage" class="KittyText">KittyKitty</p>
    <div class="SpaceDiv" />
    <p class="SeasonText">Winter</p>
    <div class="SpaceDiv" />
  </div>
  <div class="SearchAndLoginDiv">
    <div class="SpaceDiv" />
    <img class="CatInHeader" src="../projectImages/Cat no background.png" />
    <div class="SpaceDiv2" />
    <div class="searchDiv">
      <router-link class="SearchLink" :to="{ path: '/Search' }">
        <img
          class="iconInSearchField"
          src="../projectImages/magnifying_glass.png"
        />
        <input
          v-on:keyup.enter="search"
          @change="setKeyWord"
          v-on:click="searchParam = ''"
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
    <div></div>
    <div></div>
    <div></div>
    <div v-if="profileDropdown" class="profile-dropdown">
      <ul>
        <li @click="uploadNavigation">Upload Video</li>
        <li @click="logout">Logout</li>
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
  async mounted() {
    await this.$store.dispatch('whoAmI');
    if (this.$store.getters.getCurrentUser) {
      this.profilePic = this.$store.getters.getCurrentUser.getProfileURL();
    }
  },
  data() {
    return {
      isLoggedIn: false,
      searchParam: '',
      profilePic: this.$store.getters.getCurrentUser
        ? this.$store.getters.getCurrentUser.getProfileURL()
        : '',
      profileDropdown: false,
    };
  },
  methods: {
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
}

.SeasonText {
  -webkit-text-stroke-width: 0.007px;
  -webkit-text-stroke-color: #c9c9c9;
  font-family: 'Revalia', cursive;
  font-style: normal;
  font-weight: 700;
  font-size: 15px;
  line-height: 19px;
  color: #ffffff;
  text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25), 0px 4px 4px rgba(0, 0, 0, 0.25);
  margin-top: 33px;
}

.HeaderDiv {
  display: grid;
  grid-template-columns: 16px 100px auto 80px;
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
.SearchAndLoginDiv {
  display: grid; /* Margin, Cat, Margin, search icon, margin, Search Field, margin, Login Button, Margin */
  grid-template-columns: auto 43px auto max-content auto auto;
  background-color: #131313;
  padding-top: 16px;
  border-bottom: solid 1px #bfbfbf;
  max-width: max-content;
  margin-left: auto;
  margin-right: auto;
  padding-bottom: 20px;
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
li {
  text-align: center;
  color: white;
  font-family: 'Roboto', sans-serif;
  margin-top: 10px;
}
</style>
