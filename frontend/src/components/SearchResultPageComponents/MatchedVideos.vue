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
      <div
        class="titleText"
        :class="
          isDarkTheme == true ? 'titleTextDarkTheme' : 'titleTextLightTheme'
        "
      >
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
  <div class="emptyDiv"></div>
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

  async created() {
    this.isDarkTheme = await this.$store.getters.getIsDarkTheme;

    this.$store.watch(
      (state) => state.darkTheme,
      (newVal) => {
        this.isDarkTheme = newVal;
      }
    );
  },

  async mounted() {
    if (!this.$store.getters.getKeyWord) {
      this.keyword = await localStorage.searchKey;
    }

    if ((await this.$store.getters.getMatchedVideoList.length) > 6) {
      this.matchedVideos = await this.$store.getters.getMatchedVideoList.slice(
        0,
        6
      );
    } else {
      this.matchedVideos = await this.$store.getters.getMatchedVideoList;
    }

    this.$nextTick(function () {
      this.lastVideoObserverSearchResult = new IntersectionObserver(
        (entries) => {
          let lastVideo = entries[0];
          if (!lastVideo.isIntersecting) {
            return;
          }
          this.loadMoreVideos();
          this.lastVideoObserverSearchResult.unobserve(lastVideo.target);
          if (!this.stopObserver)
            this.lastVideoObserverSearchResult.observe(
              document.querySelector('.emptyDiv')
            );
        },
        { rootMargin: '50px' }
      );
      this.lastVideoObserverSearchResult.observe(
        document.querySelector('.emptyDiv')
      );
    });
  },

  unmounted() {
    this.stopObserver = true;
    // this.lastVideoObserverSearchResult.disconnect();
  },

  methods: {
    async loadMoreVideos() {
      let lengthOfCurrentShowedVideos = this.matchedVideos.length;
      let fullMatchedList = await this.$store.getters.getMatchedVideoList;

      if (lengthOfCurrentShowedVideos + 6 > fullMatchedList.length) {
        this.matchedVideos = fullMatchedList;
      } else {
        this.matchedVideos = fullMatchedList.slice(
          0,
          lengthOfCurrentShowedVideos + 6
        );
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
  font-size: small;
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
  /* height: 2.5vh; */
}

@media screen and (max-width: 355px) {
  .titleText {
    margin-top: 1px;
    font-size: medium;
    max-width: 140px;
    overflow: hidden;
  }
  .uploaderName,
  .viewCount {
    margin-left: 3vw;
    align-self: center;
    height: 2vh;
    font-size: small;
  }
  .uploader,
  .view {
    display: grid;
    grid-template-columns: 14% auto;
  }

  .textInfo {
    height: 15vh;
    margin-left: 1vw;
    place-content: space-between;
    width: 160px;
  }
}

@media screen and (max-width: 520px) and (min-width: 356px) {
  .titleText {
    margin-top: 1px;
    font-size: medium;
    max-width: 120px;
    overflow: hidden;
  }
  .uploaderName,
  .viewCount {
    margin-left: 3vw;
    align-self: center;
    height: 2vh;
    font-size: small;
  }

  @media screen and (max-width: 400px) {
    .videoCard {
      grid-template-rows: 1fr auto;
    }

    .thumbnail {
      grid-row: 1;
      grid-column: 1 / span 2;
      margin: 0 0 1vh 0;
    }

    .textInfo {
      grid-column: 1 / span 2;
      height: unset;
      margin: 0 0 0.2vh 0;
    }

    .otherInfo {
      margin: 1vh 0 0.2vh 0;
    }

    .uploaderName,
    .viewCount {
      height: unset;
    }
  }
  /*.uploader,
.view {
  display: grid;
  grid-template-columns: 14% auto; 
}

.textInfo {
  height: 16vh;
  margin-left: 6vw;
  place-content: space-between;
  width:160px;
 
}

.thumbnail {
  height: 16vh;
  width:46vw;
  
}

}

@media screen and (max-width:620px) and (min-width: 520px) {

.titleText {
margin-top:1px;
font-size: large;
max-width: 240px;
overflow: hidden;
  
}
.uploaderName,
.viewCount {
  margin-left: 3vw;
  align-self: center;
  height: 2.5vh;
  font-size: medium;
}
.uploader,
.view {
  display: grid;
  grid-template-columns: 14% auto; 

}

.textInfo {
  height: 16vh;
  margin-left: 8vw;
  place-content: space-between;
  width:220px;
 
}
.thumbnail {
  height: 16vh;
  width:48vw;
}


}

@media screen and (max-width:820px) and (min-width: 620px) {

.titleText {
margin-top:1px;
font-size: large;
max-width: 240px;
overflow: hidden;
  
}
.uploaderName,
.viewCount {
  margin-left: 3vw;
  align-self: center;
  height: 2vh;
  font-size: large;
}
.uploader,
.view {
  display: grid;
  grid-template-columns: 14% auto; 

}

.textInfo {
  height: 18vh;
  margin-left: 10vw;
  place-content: space-between;
  width:220px;
 
}

.thumbnail {
  height: 18vh;
  width:50vw;
}
}

@media screen and (min-width:820px) {

.titleText {
margin-top:1px;
max-width: 260px;
font-size: x-large;
overflow: hidden;
  
}
.uploaderName,
.viewCount {
  margin-left: 3vw;
  align-self: center;
  height: 2.5vh;
  font-size: x-large;
}
.uploader,
.view {
  display: grid;
  grid-template-columns: 15% auto; 

}

.textInfo {
  height: 20vh;
  margin-left: 12vw;
  place-content: space-between;
  width:280px;
 
}

.thumbnail {
  height: 20vh;
  width:53vw;
}*/
}
</style>
