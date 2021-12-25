<template>
  <div class="IconDiv">
    <div />
    <router-link :to="{ path: '/'}">
      <img class="HomeIcon" src="../projectImages/Dark_House.png" />
    </router-link>
    <div />
    <router-link :to="{ path: '/MyProfile'}">
      <img class="HomeIcon" src="../projectImages/Dark_User.png" />
    </router-link>
    <div />
  </div>
  <div class="TextDiv">
    <div />
    <router-link :to="{ path: '/'}">
      <div class="HomeText">Home</div>
    </router-link>
    <div />
    <router-link :to="{ path: '/MyProfile'}">
      <div class="ProfileText">Profile</div>
    </router-link>
    <div />
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
  grid-template-columns: 30px 30px auto 36px 30px;
  height: 58px;
  text-align: center;
  background-image:url('../projectImages/panther.png');
  background-size: 100% 65px;
  background-repeat: no-repeat;
  padding-top: 7px;
}
.HomeIcon{
  height: 36px;
  width: 35px;
}
.TextDiv{
  display: grid;
  color: white;
  grid-template-columns: 31px 30px auto 35.5px 30px;
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
}
</style>