<template>
  <div class="IconDiv">
    <div class="SpaceDiv" />
    <router-link :to="{ path: '/'}">
      <div class="HomeDiv">
        <img class="HomeIcon" src="../projectImages/Dark_House.png" />
        <router-link :to="{ path: '/'}">
          <div class="HomeText">Home</div>
        </router-link>
      </div>
    </router-link>
    <div class="SpaceDiv"/>
    <div class="PantherDiv">
      <img class="PantherImage" src="../projectImages/panther.png">
    </div>
    <div class="SpaceDiv"/>
    <router-link :to="{ path: '/MyProfile'}">
      <div class="HomeDiv">
        <img class="ProfileIcon" src="../projectImages/Dark_User.png" />
        <router-link :to="{ path: '/'}">
          <div class="ProfileText">Profile</div>
        </router-link>
      </div>
    </router-link>
    <div class="SpaceDiv"/>
  </div>

</template>
<script>

export default {
  name: 'Footer',
  data() {
    return {
      
    };
  },
  methods: {

  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

.IconDiv{
  display: grid;
  grid-template-columns: auto max-content 50px auto 60px max-content auto;
  height: 58px;
  text-align: center;
  background-color: black;
  padding-top: 7px;
}

.PantherDiv{
  height: 50px;
}
.PantherImage{
  height: 50px;
  transform: scaleX(2);
  position: relative; 
  left: 10px;
}

.HomeIcon, .ProfileIcon{
  height: 36px;
  width: 35px;
}

.TextDiv{
  display: grid;
  color: white;
  grid-template-columns: auto auto auto auto auto;
  background-color: none;
  margin-top: -20px;
}

.HomeText, .ProfileText{
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
.HomeDiv{
  margin-left: 35px;
}
</style>