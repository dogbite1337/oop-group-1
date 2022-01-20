<template>
  <div v-for="video of matchedVideos" :key="video" class="videoCard">
    <div class="thumbnail">
      <img
        @click="goToVideoPage(video)"
        :src="
          'https://img.youtube.com/vi/' +
          video.videoURL.substring(32, 43) +
          '/default.jpg'
        "
      />
    </div>
    <div class="textInfo">
      <div class="titleText" :class="isDarkTheme == true ? 'titleTextDarkTheme' : 'titleTextLightTheme'">
        {{ displayTitleBeforeKey(video.title) }}
        <p class="keyword">{{ displayKeyWord(video.title) }}</p>
        {{ displayTitleAfterKey(video.title) }}
      </div>
      <div class="otherInfo">
        <div class="uploader">
          <p class="icon">UP</p>
          <p class="uploaderName">{{ video.postedByUsername }}</p>
        </div>
        <div class="view">
          <img class="icon" src="src\projectImages\dark-play-button.png" />
          <p class="viewCount">{{ video.views }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      keyword: this.$store.getters.getKeyWord,
      lastVideoObserverSearchResult: null,
      matchedVideos: [],
      stopObserver: false,
      isDarkTheme: true,
    };
  },

  async created(){
    this.isDarkTheme = await this.$store.getters.getIsDarkTheme
  },

  async mounted() {
    if(!this.$store.getters.getKeyWord){
      this.keyword = await localStorage.searchKey
    }

    if(await this.$store.getters.getMatchedVideoList.length > 6){
      this.matchedVideos = await this.$store.getters.getMatchedVideoList.slice(0,6)
    }
    else{
      this.matchedVideos = await this.$store.getters.getMatchedVideoList
    }
  },

  updated(){
      this.lastVideoObserverSearchResult = new IntersectionObserver(entries =>{
        let lastVideo = entries[0]
        if(!lastVideo.isIntersecting) {
          return;}
        this.loadMoreVideos()
        this.lastVideoObserverSearchResult.unobserve(lastVideo.target);
        if(!this.stopObserver) 
        this.lastVideoObserverSearchResult.observe(document.querySelector(".videoCard:last-child"))
      },{rootMargin: "50px"}
      )
      this.lastVideoObserverSearchResult.observe(document.querySelector(".videoCard:last-child"))
  },

  unmounted() {
    this.stopObserver = true;
    // this.lastVideoObserverSearchResult.disconnect();
  },

  methods: {
    async loadMoreVideos() {
      let lengthOfCurrentShowedVideos = this.matchedVideos.length;
      let fullMatchedList = await this.$store.getters.getMatchedVideoList;

      if(lengthOfCurrentShowedVideos + 6 > fullMatchedList.length){
        this.matchedVideos = fullMatchedList
      }
      else{
        this.matchedVideos = fullMatchedList.slice(0, lengthOfCurrentShowedVideos+6)
      }
    },

    goToVideoPage(video) {
      this.$router.push('/VideoPage/' + video.videoId);
    },

    displayTitleBeforeKey(orginalTitle) {
      let index = orginalTitle
        .toLowerCase()
        .indexOf(this.keyword.toLowerCase());

      return orginalTitle.substring(0, index);
    },
    displayTitleAfterKey(orginalTitle) {
      let index = orginalTitle
        .toLowerCase()
        .indexOf(this.keyword.toLowerCase());
      return orginalTitle.substring(
        index + this.keyword.length,
        orginalTitle.length
      );
    },
    displayKeyWord(orginalTitle) {
      let index = orginalTitle
        .toLowerCase()
        .indexOf(this.keyword.toLowerCase());
      return orginalTitle.substring(index, index + this.keyword.length);
    },
  },
};
</script>

<style scoped>
p {
  color: grey;
  font-size: small;
}

.titleText .keyword {
  color: rgb(255, 99, 99);
  font-size: inherit;
  display: inline;
  margin: 0 -5px;
}

.titleText {
  display: flex;
  color: white;
  display: inline;
}

/* .title p {
  color: white;
  font-size: medium;
} */

.videoCard {
  display: grid;
  grid-template-columns: 50% 50%;
  padding: 2% 5% 2% 5%;
  border-bottom: solid 1px #ababab75;
}

.thumbnail {
  height: 15vh;
  margin-right: 3vw;
}

img {
  width: -webkit-fill-available;
  max-block-size: -webkit-fill-available;
  height: -webkit-fill-available;
  border: solid 1px grey;
}

.textInfo {
  height: 15vh;
  margin-left: 3vw;
  place-content: space-between;
}

.textInfo,
.otherInfo {
  display: flex;
  flex-direction: column;
}

.uploader {
  margin-bottom: 0.5vh;
}

.uploader,
.view {
  display: grid;
  grid-template-columns: 15% auto;
}

.icon {
  border: solid 2px grey;
  background: white;
  height: 1.5vh;
  width: 3.5vw;
  text-align: center;
  align-self: center;
}

.uploader .icon {
  font-family: cursive;
  font-size: xx-small;
}

.uploaderName,
.viewCount {
  margin-left: 3vw;
  align-self: center;
  height: 2.5vh;
}

.titleTextLightTheme{
  color: black;
}
</style>
