<template>
  <div class="MainDiv">
    <Header />
    <div class="NoLineDiv" />
    <BannerSlider />
    <div class="CardsContainer">
      <VideoCard
        v-for="(videoItem, index) of relevantVideos"
        :key="index"
        :video="videoItem"
        class="videoBox"
      />
    </div>
  </div>
  <Footer class="footerDiv" />
</template>
<script>
import Header from '../components/Header.vue';
import VideoCard from '../components/VideoCard.vue';
import Footer from '../components/Footer.vue';
import Video from '../jsClasses/general/Video';
import TrendLink from '../components/TrendLink.vue';
import VideoResultCard from '../components/VideoResultCard.vue';
import User from '../jsClasses/general/User';
import BannerSlider from '../components/FrontPageComponents/bannerSlider.vue';
import store from '../store';

export default {
  name: 'HomePage',
  components: {
    Header,
    VideoCard,
    VideoResultCard,
    TrendLink,
    Footer,
    BannerSlider,
  },
  async created() {
    let allVideos = await this.getVideosForCurrentPage();

    // Yang new code
    allVideos = await this.getAllVideos();
    if(allVideos.length > 8){
      allVideos = allVideos.slice(allVideos.length-8 ,allVideos.length)
    }

    this.$store.dispatch('cacheFirstEightVideos', allVideos);
    this.relevantVideos = [];
    for (let i = allVideos.length; i > 0; i--) {
      let video = new Video();
      let newVideo = Object.assign(video, allVideos[i - 1]);
      this.relevantVideos.push(newVideo);
    }
    this.$store.subscribe(async (mutation, state) => {
      if (mutation.type == 'setShowSearchPage') {
        this.showSearchPage = true;
      }
      if (mutation.type == 'setShouldResetToStartPage') {
        this.searchedYet = false;
        this.showResultsPage = false;
        this.showSearchPage = false;
        this.relevantUsers = [];
        this.relevantVideos = [];
        let cachedVideos = this.$store.getters.getEightFirstVideos;
        for (let i = 0; i < cachedVideos.length; i++) {
          let oldVideo = new Video();
          oldVideo = Object.assign(oldVideo, cachedVideos[i]);
          this.relevantVideos.push(oldVideo);
        }
      }
      if (mutation.type == 'setSearchResults') {
        this.relevantVideos = [];
        this.lastSearchQuery = this.$store.getters.getLastSearchQuery;
        for (let i = mutation.payload.length; i > 0; i--) {
          let video = new Video();
          let newVideo = Object.assign(video, mutation.payload[i - 1]);
          if (newVideo.getVideoId() !== 0) {
            this.searchResults.push(newVideo);
          }
        }
        this.searchedYet = true;
        let res = await fetch(
          '/rest/getUserByUsername?' +
            new URLSearchParams({
              providedUsername: this.lastSearchQuery,
            })
        );

        let response = await res.json();
        let foundUser = new User(0, '', '', '', 0, 0);
        foundUser = Object.assign(foundUser, response);
        if (foundUser.getUserId() !== 0) {
          this.relevantUsers.push(foundUser);
        }
      }
      let lastSearch = {
        lastSearchQuery: '',
      };
      let newStuff = Object.assign(lastSearch, state);
      if (newStuff.lastSearchQuery == '') {
        this.searchedYet = false;
      }
    });
  },
  beforeMount() {
    this.relevantVideos = [];
    this.searchedYet = false;
    this.showSearchPage = false;
    this.showResultsPage = false;
    let cachedVideos = this.$store.getters.getEightFirstVideos;
    if (cachedVideos) {
      for (let i = 0; i < cachedVideos.length; i++) {
        let oldVideo = new Video();
        oldVideo = Object.assign(oldVideo, cachedVideos[i]);
        this.relevantVideos.push(oldVideo);
      }
    }
  },
  data() {
    return {
      showPage: 'home',
      relevantVideos: this.$store.getters.getSearchResults
        ? this.$store.getters.getSearchResults
        : [],
    };
  },
  mounted() {
    document.getElementsByClassName('CardsContainer')[0].style =
      'grid-template-columns: ' + this.getGridDimensions() + ';';

    window.addEventListener('resize', this.recalculateGrid);
  },
  unmounted() {
    window.removeEventListener('resize', this.recalculateGrid);
  },
  methods: {
    getGridDimensions() {
      let base = '';
      for (let i = 0; i < Math.floor(window.screen.width / 200); i++) {
        base += 'auto ';
        if (i >= 8) {
          base = 'auto auto auto auto auto auto auto auto';
          break;
        }
      }
      if (window.screen.width <= 340) {
        base = 'auto auto';
      }
      return base;
    },
    recalculateGrid() {
      document.getElementsByClassName('CardsContainer')[0].style =
        'grid-template-columns: ' + this.getGridDimensions() + ';';
    },
    showResultsOfSearch() {
      this.showResultsPage = true;
      this.showSearchPage = false;
    },
    // expandSearchHistory() {
    //   this.expandedSearchHistory = true;
    // },
    // closeSearchHistory() {
    //   this.expandedSearchHistory = false;
    // },
    async getVideosForCurrentPage() {
      if (this.currentPage == 1 && this.$store.getters.getEightFirstVideos) {
        return this.$store.getters.getEightFirstVideos;
      }
      if (!this.currentPage) {
        this.currentPage = 1;
      }
      let res = await fetch(
        '/rest/getVideosForCurrentPage?' +
          new URLSearchParams({
            currentPage: this.currentPage,
          })
      );

      let response = await res.json();
      return response;
    },
    async getAllVideos() {
      let res = await fetch('/rest/getAllVideos');

      let response = await res.json();
      return response;
    },
    getImageUrl() {
      return this.imgPreUrl;
    },
    setSliderIndexToZero() {
      this.currentSliderImageIndex = 0;
    },
    setSliderIndexToOne() {
      this.currentSliderImageIndex = 1;
    },
    setSliderIndexToTwo() {
      this.currentSliderImageIndex = 2;
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto:wght@300;400&display=swap');

.MainDiv {
  background-color: #131313;
}
.test {
  width: 200px;
  height: 200px;
}
.videoBox {
  margin-bottom: 10px;
}
.coveringDiv {
  background-color: #131313;
  width: 45px;
  height: 50px;
  z-index: 3;
  margin-left: -45px;
  margin-top: -40px;
  position: relative;
}
.searchPage {
  color: white;
}

.linkVideoPic {
  width: 115px;
  height: 59px;
}
.roundingDiv {
  background-color: transparent;
  outline: 10px solid #131313;
  border-radius: 30px;
  width: 50px;
  height: 50px;
  z-index: 3;
  margin-top: -50px;
  position: relative;
}
.userResultsDiv {
  display: grid;
  grid-template-columns: 17px 39px 19px 165px 1px auto;
}
.usernameP {
  color: #e75858;
}
.subscribersP {
  color: #939393;
}
.NoLineDiv {
  height: 2px;
  background-color: #131313;
  margin-top: -1px;
}
.profileInResultsPage {
  width: 80px;
  height: 50px;
  object-fit: cover;
  border-radius: 30px;
  transform: scaleX(0.5);
  transform: scaleY(1);
  margin-left: -28px;
  z-index: -1;
}
.checkAllVideosLink {
  color: #e75858;
  text-align: center;
  padding-bottom: 9.5px;
  border-bottom: 1px solid #bfbfbf;
  font-size: 12px;
  font-family: 'Roboto', sans-serif;
}
.videosP {
  color: #939393;
  margin-top: 18.5px;
}
.SubscribeDiv {
  background-color: none;
  text-align: right;
  margin-right: 16px;
  margin-top: -57.5px;
}
.EmptyDiv {
  width: 100vw;
}
.clearHistoryDiv {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  margin-top: 10px;
}
.topVideosGrid {
  display: grid;
  grid-template-columns: auto 115px 16px 115px 16px 115px auto;
  background-color: #131313;
  margin-top: 15px;
  padding-bottom: 20px;
}
.firstVideo,
.secondVideo,
.thirdVideo {
  width: 115px;
  height: 93px;
}
.videoTitleP {
  background-color: black;
  font-size: 9.5px;
  font-family: 'Roboto', sans-serif;
  text-align: center;
  color: white;
  padding-top: 3px;
}
.VideoTitleDiv {
  margin-top: -4px;
  height: 36px;
  background-color: black;
}
.uploadedP {
  font-size: 11px;
  line-height: 8.2px;
  text-align: center;
  color: #939393;
  margin-top: 5px;
  font-family: 'Roboto', sans-serif;
}
.SubscribeButton {
  height: 16px;
  width: 63px;
  font-size: 9px;
  line-height: 11px;
  font-family: 'Roboto', sans-serif;
  font-style: normal;
  font-weight: normal;
  color: white;
  background-color: #e75858;
  margin-bottom: 21px;
  position: relative;
  top: -1.5px;
}
.footerDiv {
  display: grid;
  grid-template-rows: 5vh auto;
  background-color: black;
  position: sticky;
  top: 100vh;
  width: 100vw;
}

/* .clearHistoryButton{
  width: 97px;
  height: 20px;
  background-color: white;
  color: black;
  border-radius: 3px;
}
.trendsDiv {
  height: 193px;
  outline: 1px solid white;
  margin-left: 19px;
  margin-right: 19px;
  background-color: black;
  width: 372px;
  margin-left: auto;
  margin-right: auto;
  padding-bottom: 9px;
}
.searchHistoryText {
  font-family: 'Roboto', sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  line-height: 21px;
}
.SorryKitty {
  display: block;
  width: 200px;
  height: 224px;
  margin-left: auto;
  margin-right: auto;
}
.SearchHistoryDiv {
  background-color: #131313;
  display: grid;
  grid-template-columns: 1px auto auto 72px 1px;
  margin-right: 19px;
  margin-left: 19px;
  margin-top: 39px;
  margin-bottom: 10px;
  width: 376px;
  margin-left: auto;
  margin-right: auto;
}
.topTrendingDiv {
  display: grid;
  grid-template-columns: 19px 168px auto;
  margin-bottom: 4px;
  width: 372px;
  margin-left: auto;
  margin-right: auto;
} */

.expandButton,
.closeButton {
  width: 72.57px;
  height: 20px;
  width: 72.57px;
  height: 20px;
  background-color: white;
  border-radius: 3px;
}
.trendBox {
  width: 100px;
  height: 100px;
  background-color: white;
}

.profileDivResultPage {
  height: 40px;
  width: 40px;
  z-index: -1;
}

.userResultsTopDiv {
  background-color: #131313;
  grid-template-rows: max-content;
  padding-bottom: 15px;
  padding-left: 8px;
  padding-right: 7px;
  width: max-content;
  padding-top: 16px;
  margin-bottom: 10px;
}

.CardsContainer {
  background-color: #131313;
  display: grid;
  height: auto;
  grid-template-rows: auto auto auto auto auto;
  padding-top: 16px;
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  padding-bottom: 20px;
}

.SorryText {
  color: white;
  margin-top: 12.5px;
  font-size: 18px;
  font-weight: 400;
  font-family: 'Roboto', sans-serif;
  line-height: 21.09px;
  padding-left: 20px;
}
.SorryDiv {
  display: grid;
  grid-template-columns: auto max-content auto;
  height: calc(100vh - 200px); /* 100vh - */
}

.FirstWhiteCircle {
  width: 8px;
  height: 8px;
  border-radius: 30px;
  background-color: white;
  border: 0.7px solid black;
  margin-top: 28px;
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  margin-left: 12px;
}

.WhiteCircle {
  width: 8px;
  height: 8px;
  border-radius: 30px;
  background-color: white;
  border: 0.7px solid black;
  margin-top: 28px;
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  margin-left: 8px;
  margin-right: 8px;
}
.pacManIcon {
  width: 20px;
  display: inline;
  margin-top: 22px;
  margin-left: 4px;
}
.lastPacManIcon {
  width: 20px;
  display: inline;
  margin-top: 22px;
}
.sliderBackground {
  display: block;
  max-width: 575px;
  margin-left: auto;
  margin-right: auto;
  height: 164px;
}

.titleText {
  -webkit-text-stroke-width: 0.007px;
  -webkit-text-stroke-color: #c9c9c9;
  font-family: 'Revalia', cursive;
  font-style: normal;
  font-weight: 400;
  font-size: 15px;
  line-height: 18.6px;
  color: #ffffff;
  text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25), 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.SlideShowDiv {
  padding-top: 113px;
  height: 30px;
  background-color: #131313;
  max-width: 575px;
  display: grid; /* Margin, Text, Margin, Pacman, margin, dot, margin, dot, margin */
  grid-template-columns: 23px auto auto 20px 6px 20px 3px 20px 18px;
  padding-bottom: 21px;
  background-repeat: no-repeat;
  width: calc(100% - 16px);
  margin-left: auto;
  margin-right: auto;
  margin-top: -164px;
}

@media screen and (min-width: 550px) {
  .sliderBackground {
    transform: scaleX(0.95);
    transform: scaleY(1.05);

    height: 164px;
    z-index: -1;
  }
  .lastPacManIcon {
    width: 20px;
    display: inline;
    margin-top: 22px;
    z-index: 3;
  }

  .FirstWhiteCircle {
    width: 8px;
    height: 8px;
    border-radius: 30px;
    background-color: white;
    border: 0.7px solid black;
    margin-top: 28px;
    box-sizing: border-box;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    margin-left: 12px;
    z-index: 3;
  }

  .WhiteCircle {
    width: 8px;
    height: 8px;
    border-radius: 30px;
    background-color: white;
    border: 0.7px solid black;
    margin-top: 28px;
    box-sizing: border-box;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    margin-left: 8px;
    margin-right: 8px;
    z-index: 3;
  }
  .pacManIcon {
    width: 20px;
    display: inline;
    margin-top: 22px;
    margin-left: 4px;
    z-index: 3;
  }

  .titleText {
    -webkit-text-stroke-width: 0.007px;
    -webkit-text-stroke-color: #c9c9c9;
    font-family: 'Revalia', cursive;
    font-style: normal;
    font-weight: 400;
    font-size: 15px;
    line-height: 18.6px;
    color: #ffffff;
    text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25),
      0px 4px 4px rgba(0, 0, 0, 0.25);
    z-index: 3;
    position: absolute;
  }
}

@media screen and (min-width: 700px) {
  .CardsContainer {
    margin-top: 20px;
  }
  .sliderBackground {
    transform: scaleX(0.9);
    transform: scaleY(1.1);
    height: 164px;
    z-index: -1;
  }
  .lastPacManIcon {
    width: 20px;
    display: inline;
    margin-top: 22px;
    z-index: 3;
  }

  .FirstWhiteCircle {
    width: 8px;
    height: 8px;
    border-radius: 30px;
    background-color: white;
    border: 0.7px solid black;
    margin-top: 28px;
    box-sizing: border-box;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    margin-left: 12px;
    z-index: 3;
  }

  .WhiteCircle {
    width: 8px;
    height: 8px;
    border-radius: 30px;
    background-color: white;
    border: 0.7px solid black;
    margin-top: 28px;
    box-sizing: border-box;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    margin-left: 8px;
    margin-right: 8px;
    z-index: 3;
  }
  .pacManIcon {
    width: 20px;
    display: inline;
    margin-top: 22px;
    margin-left: 4px;
    z-index: 3;
  }

  .titleText {
    -webkit-text-stroke-width: 0.007px;
    -webkit-text-stroke-color: #c9c9c9;
    font-family: 'Revalia', cursive;
    font-style: normal;
    font-weight: 400;
    font-size: 15px;
    line-height: 18.6px;
    color: #ffffff;
    text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25),
      0px 4px 4px rgba(0, 0, 0, 0.25);
    z-index: 3;
    position: absolute;
  }
}

@media screen and (min-width: 850px) {
  .sliderBackground {
    width: 90%;
    transform: scaleY(1.5);

    height: 164px;
    margin-top: 30px;
    margin-bottom: 30px;
    z-index: -1;
  }
  .lastPacManIcon {
    width: 20px;
    display: inline;
    margin-top: 22px;
    z-index: 3;
  }

  .FirstWhiteCircle {
    width: 8px;
    height: 8px;
    border-radius: 30px;
    background-color: white;
    border: 0.7px solid black;
    margin-top: 28px;
    box-sizing: border-box;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    margin-left: 12px;
    z-index: 3;
  }

  .WhiteCircle {
    width: 8px;
    height: 8px;
    border-radius: 30px;
    background-color: white;
    border: 0.7px solid black;
    margin-top: 28px;
    box-sizing: border-box;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    margin-left: 8px;
    margin-right: 8px;
    z-index: 3;
  }
  .pacManIcon {
    width: 20px;
    display: inline;
    margin-top: 22px;
    margin-left: 4px;
    z-index: 3;
  }

  .titleText {
    -webkit-text-stroke-width: 0.007px;
    -webkit-text-stroke-color: #c9c9c9;
    font-family: 'Revalia', cursive;
    font-style: normal;
    font-weight: 400;
    font-size: 15px;
    line-height: 18.6px;
    padding-left: 30px;
    color: #ffffff;
    text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25),
      0px 4px 4px rgba(0, 0, 0, 0.25);
    z-index: 3;
    position: absolute;
  }
}
@media screen and (max-width: 400px) {
  .videoBox {
    width: 100vw;
  }
}
</style>
