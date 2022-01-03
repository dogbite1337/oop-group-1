<template>
  <router-link v-if="!$store.getters.getCurrentUser" :to="{ path: '/VideoPage/' + video.videoId, query: video.videoId}">
    <div class="CardBox">
      <img class="ImageBox" :src="'https://img.youtube.com/vi/' + video.videoURL.substring(32,43) + '/default.jpg'" />
      <div class="titleDiv">
        <p class="titleText">{{video.title.length > 24 ? video.title.substring(0,24) + '...' : video.title}}</p>
      </div>
      <div class="upBoxAndUsernameDiv">
        <div class="upDiv">
          UP
        </div>
        <div class="userNameDiv">
          {{video.postedByUsername}}
        </div>
      </div>
    </div>
  </router-link>
</template>
<script>

export default {
  props: ['video'],
  name: 'VideoCard',
  data() {
    return {
      thumbNail: "",
    };
  },

  methods: {

  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

.upBoxAndUsernameDiv{
  display: grid;
  grid-template-columns: auto auto auto;
  margin-top: 7.16px;
  background-color: #131313;
  width: 150px;
  margin-left: auto;
  margin-right: auto;
}

.CardBox{
  height: max-content;
  max-width: 192px;
  max-height: 160px;
  background-color: #000000;
  border: 0.7px solid #000000;
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  margin-left: 6px;
  margin-right: 6px;
  padding-bottom: 12px;
}

.ImageBox{
  height: 80px;
  width: 100%;
}

.titleText{
  color: white;
  text-align: center;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  line-height: 14.06px;
  font-size: 12px;
  margin-top: 8.15px;
}

.upDiv{
  color: white;
  text-align: center;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  line-height: 14.06px;
  font-size: 12px;
  border: solid 1px white;
  width: max-content;
  padding-left: 5px;
  padding-right: 5px;
  padding-top: 1.7px;
  padding-bottom: 1px;
}

.userNameDiv {
  color: white;
  text-align: center;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  font-size: 10px;
  line-height: 12px;
  margin-top: 2px;
  padding-top: 1.5px;
}
</style>