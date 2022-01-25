<template>
  <router-link
    :to="{
      path: '/VideoPage/' + (video ? video.videoId : 0),
      query: video ? video.videoId : 0,
    }"
  >
    <div v-if="!darkTheme" class="BoxDiv">
      <div @click="clickedRelatedVideo" class="LightRelatedVideoBox">
        <div class="SpaceBlock" />
        <div class="linkBox">
          <img
            class="videoImage"
            :src="
              'https://img.youtube.com/vi/' +
              video.videoURL.substring(32, 43) +
              '/default.jpg'
            "
          />
        </div>
        <div class="SpaceBlock" />
        <div class="textDiv">
          <div class="SpaceBlock" />
          <div class="LightTitleDiv">
            {{ video.title }}
          </div>
          <div class="SpaceBlock" />
          <div class="UploaderDiv">
            <img class="lightUp" src="../projectImages/Up_Light.png" />
            <div class="SpaceBlock" />
            <div class="UploaderTextDiv">{{ video.postedByUsername }}</div>
          </div>
          <div class="SpaceBlock" />
          <div class="ViewsDiv">
            <div class="PlayerDiv">
              <img class="PlayerImage" src="../projectImages/Light_Play.png" />
            </div>
            <div class="SpaceDiv" />
            <div class="ViewsNumberDiv">{{ spacedViews }} views</div>
          </div>
          <div class="SpaceBlock" />
        </div>
        <div class="SpaceBlock" />
      </div>
    </div>
    <div v-if="darkTheme" class="BoxDiv">
      <div @click="clickedRelatedVideo" class="DarkRelatedVideoBox">
        <div class="SpaceBlock" />
        <div class="linkBox">
          <img
            class="videoImage"
            :src="
              'https://img.youtube.com/vi/' +
              video.videoURL.substring(32, 43) +
              '/default.jpg'
            "
          />
        </div>
        <div class="SpaceBlock" />
        <div class="textDiv">
          <div class="SpaceBlock" />
          <div class="DarkTitleDiv">
            {{ video.title }}
          </div>
          <div class="SpaceBlock" />
          <div class="UploaderDiv">
            <div class="UpDiv">UP</div>
            <div class="SpaceBlock" />
            <div class="UploaderTextDiv">{{ video.postedByUsername }}</div>
          </div>
          <div class="SpaceBlock" />
          <div class="ViewsDiv">
            <div class="PlayerDiv">
              <img
                class="PlayerImage"
                src="../projectImages/video-player.png"
              />
            </div>
            <div class="SpaceDiv" />
            <div class="ViewsNumberDiv">{{ spacedViews }} views</div>
          </div>
          <div class="SpaceBlock" />
        </div>
        <div class="SpaceBlock" />
      </div>
    </div>
  </router-link>
</template>
<script>
import store from '../store';
export default {
  props: ['video'],
  name: 'RelatedVideo',
  mounted() {
    this.spacedViews = this.renderSpacedNumbers(this.video.views.toString());
  },
  data() {
    return {
      linksTo: this.$store.getters.getFirstEightVideos
        ? this.$store.getters.getFirstEightVideos
        : null,
      spacedViews: 0,
      darkTheme: this.$store.getters.getIsDarkTheme,
    };
  },
  methods: {
    clickedRelatedVideo() {
      this.$store.dispatch('updateRelatedVideoId', this.video.videoId);
    },
    renderSpacedNumbers(stringToPad) {
      let base = '';
      let startFrom = stringToPad % 1000;
      let spacedViews = '';
      startFrom = startFrom.toString();

      for (let i = 0; i < stringToPad.length; i++) {
        if (i != 0 && (i - (stringToPad.length % 3)) % 3 == 0) {
          base += ' ' + stringToPad[i];
        } else {
          base += stringToPad[i];
        }
        spacedViews = base;
      }
      return spacedViews;
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

* {
  text-decoration: none;
}

.UpDiv {
  height: 7px;
  width: 8px;
  background-color: black;
  color: grey;
  border: 1.55px solid #939393;
  padding-left: 1px;
  padding-right: 1px;
  font-size: 7px;
}

.LightRelatedVideoBox {
  background-color: white;
  height: max-content;
  display: grid;
  grid-template-columns: auto max-content 22px max-content auto;
  max-width: 1000px;
  border-bottom: solid 0.5px #bfbfbf;
  width: 100vw;
  z-index: 3;
  position: relative;
  margin-left: auto;
  margin-right: auto;
}

.DarkRelatedVideoBox {
  background-color: black;
  height: max-content;
  display: grid;
  grid-template-columns: auto max-content 22px max-content auto;
  max-width: 1000px;
  border-bottom: solid 0.5px #bfbfbf;
  width: 100vw;
  z-index: 3;
  position: relative;
  margin-left: auto;
  margin-right: auto;
}

.PlayerDiv {
  background-color: grey;
  width: 13px;
  height: 12.2px;
}

.PlayerImage {
  width: 11px;
  height: 13px;
  margin-left: 1px;
  margin-top: -0.5px;
}

.linkBox,
.videoImage {
  width: 191px;
  height: 95px;
}

.DarkRelatedVideoBox {
  border-top: solid 1px #939393;
}
.DarkTitleDiv {
  color: #ffffff;
  margin-top: 5px;
  font-size: 12px;
  width: 160px;
}
.LightTitleDiv {
  color: black;
  margin-top: 5px;
  font-size: 12px;
  width: 160px;
}

.UploaderDiv,
.ViewsDiv {
  font-size: 10px;
  color: #939393;
}
.BoxDiv {
  border-top: solid 1px grey;
}
.UploaderDiv {
  margin-bottom: 5px;
  display: grid;
  grid-template-columns: max-content 8px auto;
}

.ViewsDiv {
  display: grid;
  grid-template-columns: max-content 8px auto;
}

.lightUp {
  height: 15px;
  width: 15px;
  position: relative;
  left: -1px;
}
.ViewsNumberDiv {
  margin-top: 0.5px;
}

.textDiv {
  display: grid;
  grid-template-rows: 1px max-content auto max-content 1px max-content 3px;
}

@media screen and (max-width: 320px) {
  
  .textDiv{   
margin-left:2vh;   
 grid-column: 1  / span 2;   
 
  } 
  .DarkTitleDiv{
    width:110px;
    margin-bottom:5px;
  }
  
.linkBox,
.videoImage {
  width: 230px;
  height: 100px;

}


}

</style>