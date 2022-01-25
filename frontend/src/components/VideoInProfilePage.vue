<template>
  <div class="BoxDiv">
    <router-link
      :to="{
        path: '/VideoPage/' + (video ? video.videoId : 0),
        query: video ? video.videoId : 0,
      }"
    >
      <div
        v-if="darkTheme"
        @click="clickedRelatedVideo"
        class="relatedVideoBox"
      >
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
          <div v-if="darkTheme" class="DarkTitleDiv">
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
      <div
        v-if="!darkTheme"
        @click="clickedRelatedVideo"
        class="LightRelatedVideoBox"
      >
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
          <div v-if="!darkTheme" class="LightTitleDiv">
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
    </router-link>
    <div class="trashGrid">
      <div class="SpaceBlock" />
      <img
        v-if="darkTheme"
        @click="editVideo"
        class="Pen"
        src="../projectImages/black_pen.png"
      />
      <img
        v-if="!darkTheme"
        @click="editVideo"
        class="Pen"
        src="../projectImages/light_pen.png"
      />
      <div class="SpaceBlock" />
      <img
        @click="deleteVideo"
        class="DarkTrash"
        v-if="darkTheme"
        src="../projectImages/Trashcan.png"
      />
      <img
        @click="deleteVideo"
        class="LightTrash"
        v-if="!darkTheme"
        src="../projectImages/Light_Trash.png"
      />
      <div class="SpaceBlock" />
    </div>
  </div>
</template>
<script>
import store from '../store';
export default {
  emits: ['deletedVideo', 'editVideo'],
  props: ['video'],
  name: 'VideoInProfilePage',
  mounted() {
    this.spacedViews = this.renderSpacedNumbers(this.video.views.toString());
    this.$store.subscribe(async (mutation, state) => {
      if (mutation.type == 'setDarkTheme') {
        if (mutation.payload) {
          this.darkTheme = true;
        } else {
          this.darkTheme = false;
        }
      }
    });
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
    mounted() {
      this.$store.subscribe(async (mutation, state) => {
        if (mutation.type == 'setDarkTheme') {
          if (mutation.payload) {
            this.darkTheme = true;
          } else {
            this.darkTheme = false;
          }
        }
        this.darkTheme = this.$store.getters.isDarkTheme;
      });
    },
    async deleteVideo() {
      this.$emit('deletedVideo', this.video.videoId);
    },
    async editVideo() {
      this.$emit('editVideo', this.video);
    },
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

.trashGrid {
  display: grid;
  grid-template-columns: auto max-content 3px max-content 10px;
  z-index: 5;
  position: relative;
  top: -35px;
  width: 400px;
  background-color: transparent;
}
.LightTrash {
  height: 30px;
  width: 30px;
  position: absolute;
  left: calc(100vw - 40px);
  top: 1px;
}
.DarkTrash {
  height: 30px;
  width: 30px;
  position: absolute;
  left: calc(100vw - 40px);
  top: 1px;
}
.Pen {
  height: 23px;
  width: 23px;
  position: absolute;
  left: calc(100vw - 70px);
  top: 5px;
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

.relatedVideoBox {
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

.LightRelatedVideoBox {
  background-color: white;
  height: max-content;
  display: grid;
  grid-template-columns: auto max-content 22px max-content auto;
  max-width: 1000px;
  border-bottom: solid 0.5px black;
  width: 100vw;
  z-index: 3;
  position: relative;
  margin-left: auto;
  margin-right: auto;
  border-top: solid 1px black;
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

.relatedVideoBox {
  border-top: solid 1px #939393;
}
.LightTitleDiv {
  color: black;
  margin-top: 5px;
  font-size: 12px;
  width: 160px;
}
.DarkTitleDiv {
  color: #ffffff;
  margin-top: 5px;
  font-size: 12px;
  width: 160px;
}

.UploaderDiv,
.ViewsDiv {
  font-size: 10px;
  color: #939393;
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

.ViewsNumberDiv {
  margin-top: 0.5px;
}

.textDiv {
  display: grid;
  grid-template-rows: 1px max-content auto max-content 1px max-content 3px;
}

@media screen and (max-width: 420px) {
  .SpaceBlock{
    display: none;
  }

  .relatedVideoBox{
    grid-template-columns: 10% auto 10%;
    border-bottom: none;
  }

  .linkBox,
  .textDiv{
    grid-column: 2;
  }

  .videoImage,
  .linkBox{
    width: -webkit-fill-available;
    height: unset;
  }

  .textDiv{
    display: flex;
    flex-direction: column;
  }

  .UploaderDiv{
    margin-top: 30px;
  }

  .ViewsDiv{
    margin-bottom: 10px;
  }

  .UploaderDiv,
  .ViewsDiv{
    display: flex;
  }

  .PlayerDiv{
    height: 21.09px;
    width: 21.09px;
  }

  .UpDiv{
    height: 17px;
    width: 17px;
  }

  .UploaderTextDiv,
  .ViewsNumberDiv{
    margin-left: 1rem;
    align-self: center;
  }

  .PlayerImage{
    width: 21.09px;
    height: 21.09px;
    margin: unset;
  }

  .trashGrid{
    position: unset;
    /* grid-column: 2; */
    display: grid;
    grid-template-columns: auto 10% 10% 10%;
    align-items: center;
    width: unset;
    justify-items: flex-end;
    margin-bottom: 10px;
  }
 
  .trashGrid img{
    position: unset;
  }
  
  .Pen{
    grid-column: 2;
    height: 1.5rem;
    width: 1.5rem;
  }

  .DarkTrash{
    grid-column: 3;
    height: 2rem;
    width: 2rem;
  }

  .BoxDiv{
    border-bottom: 1px solid #939393;
  }
}
</style>
