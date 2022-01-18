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
      <div class="titleText">
          {{ displayTitleBeforeKey(video.title) }}
          <p class="keyword">{{ displayKeyWord(video.title) }}</p>
          {{ displayTitleAfterKey(video.title) }}
        <!-- <a class="titleText" :href="'/VideoPage/' + video.videoId">
          {{ video.title }}
        </a> -->
        <!-- <p>{{video.title}}</p> -->
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
    };
  },

  async mounted() {
    if(this.$store.getters.getMatchedVideoList.length > 6){
      this.matchedVideos = await this.$store.getters.getMatchedVideoList.slice(0,6)
    }
    else{
      this.matchedVideos = await this.$store.getters.getMatchedVideoList
    }
  },

  updated(){

    // let htmlElement = document.querySelectorAll(".titleText");
    // htmlElement.forEach(element => {
    //   console.log(element.innerHTML)
    //   element.innerHTML = element.innerHTML.replace(this.keyword, '<span style="color: red;">' + this.keyword + '</span>')
    // });

    // upon start, show 6 videos, and then when the last video DIV is showing, loadMoreVideos
    // however, unobserve should have stopped this from observing the earlier "last div" and observe on the new last div
    // Problem: unobserve had no effect, Observer instead listens to both div
      this.lastVideoObserverSearchResult = new IntersectionObserver(entries =>{
        let lastVideo = entries[0]
        if(!lastVideo.isIntersecting) {
          // console.log(lastVideo.target)
          return;}
        this.loadMoreVideos()
        this.lastVideoObserverSearchResult.unobserve(lastVideo.target);
        // Since neither of these worked, i set a switch for temp fix
        // this.lastVideoObserverSearchResult.disconnect();
        // IntersectionObserver.disconnect();
        if(!this.stopObserver) 
        this.lastVideoObserverSearchResult.observe(document.querySelector(".videoCard:last-child"))
      },{rootMargin: "50px"}
      )

      this.lastVideoObserverSearchResult.observe(document.querySelector(".videoCard:last-child"))
  },

  unmounted(){
    this.stopObserver = true;
  },

  methods: {
    async loadMoreVideos(){
      let lengthOfCurrentShowedVideos = this.matchedVideos.length
      let fullMatchedList = await this.$store.getters.getMatchedVideoList;

      if(lengthOfCurrentShowedVideos + 6 > fullMatchedList.length){
        this.matchedVideos = fullMatchedList
      }
      else{
        console.log(fullMatchedList.slice(0, lengthOfCurrentShowedVideos+6))
        this.matchedVideos = fullMatchedList.slice(0, lengthOfCurrentShowedVideos+6)
      }

      // console.log("loaded more videos")
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
}

.titleText {
  display: flex;
  color: white;
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
</style>
