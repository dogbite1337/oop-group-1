<template>
  <Header />
  <div v-if="!darkTheme" class="LightNoLineDiv" />
  <div v-if="darkTheme" class="DarkNoLineDiv" />
  <BannerSlider />
  <div v-if="darkTheme" class="DarkCardsContainer CardsContainer">
    <VideoCard
      v-for="(videoItem, index) of relevantVideos"
      :key="index"
      :video="videoItem"
      class="videoBox"
      ref="videoBox"
    />
  </div>
  <div v-if="!darkTheme" class="LightCardsContainer CardsContainer">
    <VideoCard
      v-for="(videoItem, index) of relevantVideos"
      :key="index"
      :video="videoItem"
      class="videoBox"
      ref="videoBox"
    />
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
    this.relevantVideos = [];
    await this.loadMoreVideos();
    let allVideos = this.relevantVideos;

    // this.$store.dispatch('cacheFirstEightVideos', allVideos);
    localStorage.setItem('relatedVideos', JSON.stringify(allVideos));
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
      lastVideoObserver: null,
      darkTheme: false,
      showPadder: window.outerWidth > 418 ? false : true,
    };
  },

  async mounted() {
    this.$store.subscribe(async (mutation, state) => {
      if (mutation.type == 'setDarkTheme') {
        if (mutation.payload) {
          this.darkTheme = true;
        } else {
          this.darkTheme = false;
        }
      }
    });
    document.getElementsByClassName('CardsContainer')[0].style =
      'grid-template-columns: ' + this.getGridDimensions() + ';';
    window.addEventListener('resize', this.recalculateGrid);
  },
  updated() {
    // here i am trying to only observer the last element of that class
    this.lastVideoObserver = new IntersectionObserver(
      (entries) => {
        let lastVideo = entries[0];
        if (!lastVideo.isIntersecting) {
          // this.loadMoreVideos()
          return;
        }

        this.loadMoreVideos();
        this.lastVideoObserver.unobserve(lastVideo.target);
        this.lastVideoObserver.observe(
          document.querySelector('.videoBox:last-child')
        );
      },
      { rootMargin: '100px' }
    );
    this.lastVideoObserver.observe(
      document.querySelector('.videoBox:last-child')
    );
  },
  unmounted() {
    this.lastVideoObserver.disconnect();
    window.removeEventListener('resize', this.recalculateGrid);
  },

  methods: {
    async loadMoreVideos() {
      let newlyLoadedVideos;
      let numberOfCurrentShownVideos = this.relevantVideos.length;
      newlyLoadedVideos = await this.fetchEightMoreVideosFromDB(
        numberOfCurrentShownVideos
      );
      this.$nextTick(function () {
        if (newlyLoadedVideos.length != 0) {
          newlyLoadedVideos.forEach((newVideo) => {
            if (
              !this.relevantVideos.some(
                (data) => data.videoId === newVideo.videoId
              )
            ) {
              this.relevantVideos.push(newVideo);
            }
          });
        }
      });
    },

    async fetchEightMoreVideosFromDB(numberOfCurrentShownVideos) {
      return await this.$store.dispatch(
        'fetchEightMoreVideos',
        numberOfCurrentShownVideos
      );
    },

    getGridDimensions() {
      let base = '';
      for (let i = 0; i < Math.floor(window.screen.width / 210); i++) {
        base += '192px ';
        if (i >= 8) {
          base = '192px 192px 192px 192px 192px 192px 192px 192px';
          break;
        }
      }
      if (window.screen.width <= 340) {
        base = '192px 192px';
      }
      return base;
    },
    recalculateGrid() {
      if (this.darkTheme) {
        document.getElementsByClassName('CardsContainer')[0].style =
          'grid-template-columns: ' + this.getGridDimensions() + ';';
      } else {
        document.getElementsByClassName('CardsContainer')[0].style =
          'grid-template-columns: ' + this.getGridDimensions() + ';';
      }
    },
    showResultsOfSearch() {
      this.showResultsPage = true;
      this.showSearchPage = false;
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

.LightMainDiv {
  background-color: white;
  height: inherit;
  overflow-y: scroll;
}

.DarkMainDiv {
  background-color: #131313;
  height: inherit;
  overflow-y: scroll;
}
.test {
  width: 200px;
  height: 200px;
}
.videoBox {
  margin-bottom: 10px;
  max-width: max-content;
  display: inline;
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
.DarkNoLineDiv {
  height: 32px;
  background-color: black;
  margin-top: -1px;
}
.LightNoLineDiv {
  height: 32px;
  background-color: white;
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
.Padder {
  height: 10px;
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
/* .footerDiv {
  display: grid;
  grid-template-rows: 5vh auto;
  background-color: black;
  position: sticky;
} */

.footerDiv {
  position: fixed;
  top: calc(100vh - 65px);
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

.LightCardsContainer {
  background-color: white;
  display: grid;
  grid-template-columns: 200px 200px;
  grid-template-rows: max-content;
  margin-left: auto;
  margin-right: auto;
  max-width: max-content;
  height: auto;
  padding-top: 16px;
  padding-bottom: 65px;
  position: relative;
  left: 0px;
}

.DarkCardsContainer {
  background-color: #131313;
  display: grid;
  grid-template-columns: 200px 200px;
  grid-template-rows: max-content;
  margin-left: auto;
  margin-right: auto;
  max-width: max-content;
  height: auto;
  padding-top: 16px;
  padding-bottom: 65px;
  position: relative;
  left: 0px;
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
@media screen and (max-width: 418px) {
  .LightCardsContainer,
  .DarkCardsContainer {
    position: relative;
    width: 100vw;
    max-width: 100vw;
    display: block;
  }
  .videoBox {
    height: 200px;
    margin-top: 20px;
    margin-bottom: 20px;
    position: relative;
    width: 100vw;
    max-width: 100vw;
    left: -10px;
    display: block;
  }
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
</style>
