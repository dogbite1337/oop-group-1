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
          <router-link :to="{ path: '/Search'}">
          <img class="iconInSearchField" src="../projectImages/magnifying_glass.png" />
      <input v-model="searchParam" class="SearchField" type="text" placeholder="Search.."/>
    </router-link>
      <div class="SpaceDiv" />
    </div>
    <div class="SpaceDiv" />
    <router-link v-if="!$store.getters.getCurrentUser" :to="{ path: '/Login'}">
      <input class="LoginButton" type="button" value="Login">
    </router-link>
    <router-link v-if="$store.getters.getCurrentUser" :to="{ path: '/Upload'}">
      <img class="profilePic" :src="profilePic" />
    </router-link>
    <div class="SpaceDiv" />
  </div>
</template>
<script>
import store from '../store'
export default {
  name: 'Header',
  data() {
    return {
      isLoggedIn: false,
      searchParam : '',
      profilePic: (this.$store.getters.getCurrentUser ? this.$store.getters.getCurrentUser.getProfileURL() : '')
    };
  },
  methods: {
    resetToStartPage(){
      this.$store.dispatch('resetToStartPage', true)
      this.searchParam = ''
    },
  //   showSearchMenu() {
  //     this.$store.dispatch('updateShowSearchPage', true);
  //   },
  //   async searchForVideos() {
  //     this.$store.dispatch('updateLastSearchQuery', this.searchParam);
  //     let res = await fetch('/rest/getAllVideosByTitle?' + new URLSearchParams({
  //       providedTitle: this.searchParam
  //     }));

  //     let response = await res.json();
  //     this.$store.dispatch('updateSearchResult', response);
  //   }
  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

*{
  overflow-x: hidden;
}

.searchDiv{
  align-content: center;
  align-items: center;
  text-align: center;
  margin-left: -30px;
  width: 302px;
  margin-top: -7px;
  margin-bottom: 15px;
}

.searchIcon{
  height: 20px;
  width: 20px;
  z-index: 3;
  color:white;
  background-color:green;
  display:inline;
}

.iconInSearchField{
  position: relative;
  top: 9px;
  left: 35px;
  display: inline;
  height: 25px;
  width:25px;
  z-index: 3;
  margin-left: 20px;
  margin-top: 5px;
  opacity: 60%;
}

.SearchField{
  height: 30px;
  border-radius: 30px;
  max-width: 400px;
  min-width: 100px;
  padding-left: 37px;
  display: inline;
  outline:none;
}

.CatInHeader{
  width: 43px;
  height: 50px;
  margin-top: -7px;
}

.KittyText{
  -webkit-text-stroke-width: .007px;
  -webkit-text-stroke-color: #c9c9c9;
  font-family: 'Revalia', cursive;
  font-style: normal;
  font-weight: 700;
  font-size: 15px;
  line-height: 19px;
  color: #FFFFFF;
  text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25), 0px 4px 4px rgba(0, 0, 0, 0.25);
  margin-top: 16px;
}

.SeasonText{
  -webkit-text-stroke-width: .007px;
  -webkit-text-stroke-color: #c9c9c9;
  font-family: 'Revalia', cursive;
  font-style: normal;
  font-weight: 700;
  font-size: 15px;
  line-height: 19px;
  color: #FFFFFF;
  text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25), 0px 4px 4px rgba(0, 0, 0, 0.25);
  margin-top: 33px;
}

.HeaderDiv{
  display: grid;
  grid-template-columns: 16px 100px auto minmax(100px, 30vw);
  height: 60px;
  text-align: center;
  background-image:url('../projectImages/ghosts.gif');
  background-size: 100% 60px;
  background-repeat: no-repeat;
}

.SearchAndLoginDiv{
  display: grid;    /* Margin, Cat, Margin, search icon, margin, Search Field, margin, Login Button, Margin */
  grid-template-columns: 16px 43px auto max-content auto 70px; 
  background-color: #131313;
  padding-top: 16px;
  /* border-bottom: solid 1px #BFBFBF; */
}

.LoginButton{
  border-radius: 30px;
  width: 40px;
  height: 40px;
  background-color:#F0F0F0;
  border: 0.7px solid #000000;
  color: black;
  margin-bottom: 15px;
  margin-right: 17px;
  text-align: center;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  margin-top: 4px;
}

.LoginText{
  margin-top: 12.5px;
  font-size: 12px;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  line-height: 14.06px;
}

.profilePic{
  height: 40px;
  width: 40px;
  border-radius: 30px; 
}
</style>