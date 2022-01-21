<template>
  <div :class="isDarkTheme == true ? 'darkBackgroundDiv' : 'lightBackgroundDiv'" @click="clickedMe">
    <router-link class="HomeLink" to="/">
      <div :class="isDarkTheme == true ? 'darkBackHomeDiv' : 'lightBackHomeDiv'">
        <div class="SpaceDiv" />
        <img class="ArrowIcon" src="../projectImages/white_arrow.png" />
        <div class="SpaceDiv" />
        <p class="homeText" :class="isDarkTheme == true ? 'whiteText' : 'blackText'">Home</p>
        <div class="SpaceDiv" />
        <img class="catIcon" src="../projectImages/whiteCatBlackBaground.png" />
        <div class="SpaceDiv" />
      </div>
    </router-link>
    <div :class="isDarkTheme == true ? 'darkVideoPreviewDiv' : 'lightVideoPreviewDiv'">
      <p :class="isDarkTheme == true ? 'whiteText' : 'blackText'">Video Preview</p>
      <div :class="isDarkTheme == true ? 'darkVideoLinkBox' : 'lightVideoLinkBox'">
        <img
          class="previewImage"
          :src="
            videoURL.length > 0
              ? 'https://img.youtube.com/vi/' +
                videoURL.substring(32, 43) +
                '/default.jpg'
              : 'https://cdn.vox-cdn.com/thumbor/HIluJzxPz3qH66lFxxHKVl10UzQ=/0x0:2040x1360/1200x800/filters:focal(857x517:1183x843)/cdn.vox-cdn.com/uploads/chorus_image/image/60211577/acastro_180403_1777_youtube_0001.0.jpg'
          "
        />
        <div class="TitleDivInVideoPreview">
          {{ titleOfVideo == '' ? 'Title' : titleOfVideo }}
        </div>
        <div class="uploaderDiv" :class="isDarkTheme == true ? 'blackBackground whiteText' : 'whiteBackground blackText'">
          <div class="SpaceDiv" />
          <div class="upDiv">UP</div>
          <div class="SpaceDiv" />
          <div class="usernameDiv">
            {{ currentUser != '' ? currentUser.getUsername() : 'None' }}
          </div>
          <div class="SpaceDiv" />
        </div>
      </div>
      <div class="inputsDiv" :class="isDarkTheme == true ? 'blackBackground whiteText' : 'whiteBackground blackText'">
        <p class="VideoUrlP">Video Url</p>
        <input
          v-model="videoURL"
          :class="isDarkTheme == true ? 'whiteBorder' : 'blackBorder'"
          type="text"
          placeholder="Video url goes here.."
        />
        <p class="VideoTitleP">Video Title</p>
        <input
          v-model="titleOfVideo"
          :class="isDarkTheme == true ? 'whiteBorder' : 'blackBorder'"
          type="text"
          placeholder="What is the title of the video?"
        />
        <p class="DescriptionP">Description</p>
        <textarea
          v-model="descriptionOfVideo"
          class="DescriptionInput"
          :class="isDarkTheme == true ? 'whiteBorder' : 'blackBorder'"
          placeholder="What is the video about? (Optional)"
        >
        </textarea>
      </div>
      <button
        @click="uploadVideo"
        v-if="canUpload"
        class="uploadButton"
        value="Upload Video"
      >
        Upload Video
      </button>
      <button
        v-if="!canUpload"
        class="DisabledUploadButton"
        value="Upload Video"
      >
        Upload Video
      </button>
    </div>
  </div>
  <Footer />
</template>
<script>
import User from '../jsClasses/general/User';
import Footer from '../components/Footer.vue';
import store from '../store';

export default {
  name: 'UploadVideoPage',
  components: {
    Footer,
  },
  data() {
    return {
      videoURL: '',
      titleOfVideo: '',
      descriptionOfVideo: '',
      currentUser: this.$store.getters.getCurrentUser
        ? this.$store.getters.getCurrentUser
        : '',
      canUpload: false,
      isDarkTheme: false,
    };
  },
  beforeCreate () {
    //this.isDarkTheme = this.$store.getters.isDarkTheme;
  },
  watch: {
    videoURL() {
      if (
        this.videoURL.length > 0 &&
        this.titleOfVideo.length > 0 &&
        this.$store.getters.getCurrentUser
      ) {
        this.canUpload = true;
      }
    },
    titleOfVideo() {
      if (
        this.videoURL.length > 0 &&
        this.titleOfVideo.length > 0 &&
        this.$store.getters.getCurrentUser
      ) {
        this.canUpload = true;
      }
    },
  },
  methods: {
    async uploadVideo() {
      let video = {
        uploaderId: this.currentUser.getUserId(),
        videoURL: this.videoURL.substring(0, 43),
        uploadDate: Date.now(),
        title: this.titleOfVideo,
        description: this.descriptionOfVideo,
        views: 0,
        postedByUsername: this.currentUser.getUsername(),
        likes: 0,
        dislikes: 0,
        stars: 0,
      };
      let res = await fetch('/api/uploadVideo', {
        method: 'POST',
        body: JSON.stringify(video),
      });
      document.getElementsByClassName('HomeLink')[0].click();
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto:wght@300;400&display=swap');

* {
  outline: none;
  border: none;
  font-family: 'Roboto', sans-serif;
}
.HomeLink {
  text-decoration: none;
}
p,
div {
  text-align: center;
}

.whiteText {
  color: white;
}

.blackText {
  color: black;
}

.whiteBackground {
  background-color: white;
}

.blackBackground {
  background-color: #131313;
}

.whiteBorder {
  border: 0.7px solid white;
}

.blackBorder {
  border: 0.7px solid black;
}

.uploadButton {
  margin-top: 10px;
  margin-bottom: 10px;
  width: max-content;
  padding-left: 5px;
  padding-right: 5px;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  border: solid 0.7px white;
  border-radius: 5px;
  background-color: rgba(45, 44, 44, 0.5);
  color: white;
  padding-top: 2px;
  padding-bottom: 2px;
}
.DisabledUploadButton {
  margin-top: 10px;
  margin-bottom: 10px;
  width: max-content;
  padding-left: 5px;
  padding-right: 5px;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  border: solid 0.7px white;
  border-radius: 5px;
  background-color: rgba(45, 44, 44, 0.5);
  color: white;
  padding-top: 2px;
  padding-bottom: 2px;
  font-size: 10px;
  color: rgba(255, 255, 255, 0.45);
  background-color: rgba(45, 44, 44, 0.5);
  border: 0.7px solid rgba(255, 255, 255, 0.45);
}
.upDiv {
  width: 12px;
  height: 8px;
  background-color: white;
  margin-bottom: 7px;
  border: solid 1px #939393;
  font-size: 7px;
  text-align: center;
  padding-top: 1.5px;
  font-family: 'Roboto', sans-serif;
}

.darkBackgroundDiv {
  background-color: black;
  height: 100vh;
  display: grid;
  grid-template-rows: max-content auto;
  padding-bottom: 10px;
}

.lightBackgroundDiv {
  background-color: #E5E5E5;
  height: 100vh;
  display: grid;
  grid-template-rows: max-content auto;
  padding-bottom: 10px;
}

input {
  width: 362px;
  padding-left: 10px;
  /*background-color: #131313;
  color: white;
  border: 0.7px solid #ffffff;*/
  box-sizing: border-box;
  padding-top: 2.5px;
  padding-bottom: 2.5px;
}

.usernameDiv {
  padding-top: 0.75px;
  height: max-content;
}
.darkVideoLinkBox {
  border: solid 1px white;
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  padding: 16px;
  margin-bottom: 8px;
  background-color: black;
}

.lightVideoLinkBox {
  border: solid 1px black;
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  padding: 16px;
  margin-bottom: 8px;
  background-color: white;
}

.previewImage {
  height: 100px;
  width: 120px;
}

.darkBackHomeDiv {
  display: grid; /* space, Arrow, space, Home, space, Cat */
  grid-template-columns: 17px 32px 5px 60px 12px 20px auto;
  background-color: black;
}

.lightBackHomeDiv {
  display: grid; /* space, Arrow, space, Home, space, Cat */
  grid-template-columns: 17px 32px 5px 60px 12px 20px auto;
  background-color: #E5E5E5;
}

.DescriptionInput {
  height: 70px;
  width: 343px;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 5px;
}
.VideoUrlP,
.DescriptionP,
.VideoTitleP {
  margin-bottom: 2px;
  margin-top: 7px;
}
.ArrowIcon {
  transform: rotate(180deg);
  margin-top: 20px;
  margin-bottom: 12px;
  height: 25px;
  width: 28px;
}
.TitleDivInVideoPreview {
  font-size: 13px;
  margin-bottom: 2px;
}

.homeText {
  font-size: 24px;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  line-height: 28.13px;
  margin-top: 18px;
  margin-bottom: 15px;
}

.uploaderDiv {
  display: grid;
  grid-template-columns: auto 14px 9px 100px auto;
  font-size: 10px;
  height: 14px;
  padding: 0px;
  margin: 0px;
}
.upDiv {
  margin-top: 1.25px;
  margin-left: 2.5px;
  color: white;
  background-color: #131313;
  height: max-content;
}
.catIcon {
  width: 28px;
  height: 31px;
  margin-top: 15px;
  margin-bottom: 12px;
}
.darkVideoPreviewDiv {
  height: max-content;
  width: 388px;
  margin-left: auto;
  margin-right: auto;
  background-color: #131313;
  margin-bottom: 19px;
  border: 0.7px solid #ffffff;
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  padding-top: 3px;
  padding-bottom: 2px;
}

.lightVideoPreviewDiv {
  height: max-content;
  width: 388px;
  margin-left: auto;
  margin-right: auto;
  background-color: white;
  margin-bottom: 19px;
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  padding-top: 3px;
  padding-bottom: 2px;
}
</style>
