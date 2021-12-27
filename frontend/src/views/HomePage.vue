<template>
  <div class="MainDiv">
    <Header />
    <img v-if="!searchedYet && !showSearchPage" class="sliderBackground" :src="`src\\projectImages\\${
      decodeURI(sliderImageURls[currentSliderImageIndex]) 
      + 
      (gifs.includes(decodeURI(sliderImageURls[currentSliderImageIndex])) ? '.gif' : '.png')}`">
    <img class="SorryKitty" v-if="searchedYet && relevantVideos.length == 0 && !showSearchPage" src="src\\projectImages\\sorryKitty.gif" />
    <div v-if="showSearchPage" class="searchPage">
      <div class="topTrendingDiv">
        <div/>
        <p class="topTrendingSearches">Top 10 Trending Searches</p>
        <div/>
      </div>
      <div class="trendsDiv">
        <TrendLink :trends="topTenTrend"/>
      </div>
      <div class="SearchHistoryDiv">
        <div/>
        <p class="searchHistoryText">Search History</p>
        <div/>
        <button v-if="!expandedSearchHistory" @click="expandSearchHistory" class="expandButton">expand</button>
        <button v-if="expandedSearchHistory" @click="closeSearchHistory" class="closeButton">close</button>
        <div/>
      </div>
      <ExpandableSearchHistory :expandedSearchHistory="expandedSearchHistory" 
      :values="mySearchHistory" />
      
    </div>
    <div v-if="showSearchPage" class="clearHistoryDiv">
      <button class="clearHistoryButton">Clear history</button>
    </div>
    <div v-if="!searchedYet && !showSearchPage" class="SlideShowDiv">
      <div class="SpaceBlock" />
      <div class="titleDiv">
        <p class="titleText">{{sliderTitles[currentSliderImageIndex]}}</p>
      </div>
      <div class="SpaceBlock" />
      <img v-if="currentSliderImageIndex == 0" class="pacManIcon" src="../projectImages/cleaned_pacman.png" />
      <div v-if="currentSliderImageIndex > 0" @click="setSliderIndexToZero" class="FirstWhiteCircle" />
      <div class="SpaceBlock" />
      <img v-if="currentSliderImageIndex == 1" class="pacManIcon" src="../projectImages/cleaned_pacman.png" />
      <div v-if="currentSliderImageIndex != 1" @click="setSliderIndexToOne" class="WhiteCircle" />
      <div class="SpaceBlock" />
      <img v-if="currentSliderImageIndex == 2" class="lastPacManIcon" src="../projectImages/cleaned_pacman.png" />
      <div v-if="currentSliderImageIndex != 2" @click="setSliderIndexToTwo" class="WhiteCircle" />
    </div>
    <div v-if="searchedYet && relevantVideos.length > 0 && !showSearchPage" class="CardsContainer">
      <VideoCard
        v-for="(videoItem, index) of relevantVideos"
        :key="index"
        :video="videoItem"
        class="videoBox"
      />
    </div>
    <div v-if="!searchedYet && !showSearchPage" class="CardsContainer">
      <VideoCard
        v-for="(videoItem, index) of relevantVideos"
        :key="index"
        :video="videoItem"
        class="videoBox"
      />
    </div>
    <div v-if="relevantVideos.length == 0 && searchedYet && !showSearchPage" class="SorryDiv">
      <div />
      <div class="SorryText">
        No result was found
      </div>
      <div />
    </div>
  </div>
  <div class="footerDiv">
    <div/>
    <Footer />
  </div>
</template>
<script>
import Header from '../components/Header.vue'
import VideoCard from '../components/VideoCard.vue'
import Footer from '../components/Footer.vue'
import ExpandableSearchHistory from '../components/ExpandableSearchHistory.vue'
import Video from '../jsClasses/general/Video'
import TrendLink from '../components/TrendLink.vue'
import store from '../store'


export default {
  name: 'HomePage',
  components: {
    Header,
    VideoCard,
    ExpandableSearchHistory,
    TrendLink,
    Footer
  },
  async created() {
    let allVideos = await this.getAllVideos();
    this.relevantVideos = []
    for(let i = allVideos.length; i > 0; i--){
      let video = new Video(0, 0, '', '', '')
      let newVideo = Object.assign(video, allVideos[i-1])
      this.relevantVideos.push(newVideo);
    }
    this.$store.subscribe((mutation, state) => {
      
      if(mutation.type == "setShowSearchPage"){
        this.showSearchPage = true;
      }
      if(mutation.type == "setSearchResults"){
        this.relevantVideos = []
        for(let i = mutation.payload.length; i > 0; i--){
          let video = new Video(0, 0, '', '', '')
          let newVideo = Object.assign(video, mutation.payload[i-1])
          this.relevantVideos.push(newVideo);
        }
        this.searchedYet = true;
      }
      let lastSearch = {
        lastSearchQuery: ''
      }
      let newStuff = Object.assign(lastSearch, state);
      if(newStuff.lastSearchQuery == ''){
        this.searchedYet = false;
      }
    })
  },
  data() {
    return {
      currentSliderImageIndex: 0,
      sliderImageURls: ['happyCats', 'smile', 'samuelCatJackson'],
      sliderTitles: ['Kitty Kissaten in Kyoto', 'Top 10 feel Good Animes', 'What did you call me? Meowdafaka'],
      gifs: ['ghosts', 'sorryKitty', 'sumo-run', 'shalsha-aizawa-falfa-aizawa', 'sleepyCat',
      'WinterCold', 'samuelCatJackson', 'WinterWarm', 'smiling-cat-creepy-cat', 'cat-shooting'],
      relevantVideos: this.$store.getters.getSearchResults ? this.$store.getters.getSearchResults : [],
      relevantUsers: [],
      searchedYet: this.$store.getters.getSearchResults === null ? false : true,
      showSearchPage: false,
      expandedSearchHistory: false,
      mySearchHistory: ['John..', 'Why', 'Is the', 'Entire website', 'About', 'Cats..'],
      topTenTrend: ['Cats', 'More Cats', 'All cats', 'Cats?!', 'Cats.', 'Cats!', 'Why are there so many cats', 'John', 'Stop This', 'Madness']
    };
  },
  watch: {
    relevantVideos() {
      console.log("I searched for some videos");
    }
  },
  methods: {
    expandSearchHistory() {
      this.expandedSearchHistory = true;
    },
    closeSearchHistory() {
      this.expandedSearchHistory = false;
    },
    async getAllVideos() {
      let res = await fetch('/rest/getAllVideos', {
        method: 'GET'
      });

      let response = await res.json();
      console.log(response);
      return response;
    },
    htmlDecode(input) {
      var doc = new DOMParser().parseFromString(input, "text/html");
      return doc.documentElement.textContent;
    },
    getImageUrl(){
      return this.imgPreUrl
    },
    setSliderIndexToZero(){
      this.currentSliderImageIndex = 0;
      console.log(this.$store.getters.getSearchResults);
    },
    setSliderIndexToOne(){
      this.currentSliderImageIndex = 1;
    },
    setSliderIndexToTwo(){
      this.currentSliderImageIndex = 2;      
    }
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto:wght@300;400&display=swap');

.MainDiv{
  background-color: #131313;
}
.searchPage{
  color: white;
}
.clearHistoryDiv{
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  margin-top: 10px;
}
.footerDiv{
  display: grid;
  grid-template-rows: 40vh auto;
  background-color: #131313;
}
.clearHistoryButton{
  width: 97px;
  height: 20px;
  background-color: white;
  color: black;
  border-radius: 3px;
}
.trendsDiv{
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
.searchHistoryText{
  font-family: 'Roboto', sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  line-height: 21px;
}
.SorryKitty{
  display:block;
  width: 200px;
  height: 224px;
  margin-left:auto;
  margin-right: auto;
}
.SearchHistoryDiv{
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
.topTrendingDiv{
  display: grid;
  grid-template-columns: 19px 168px auto;
  margin-bottom: 4px;
  width: 372px;
  margin-left: auto;
  margin-right: auto;
}
.expandButton, .closeButton{
  width: 72.57px;
  height: 20px;
  width: 72.57px;
  height: 20px;
  background-color: white;
  border-radius: 3px;
}
.trendBox{
  width: 100px;
  height: 100px;
  background-color: white;
}
.CardsContainer{
  background-color: #131313;
  display: grid;
  grid-template-columns: auto auto;
  grid-template-rows: 159px 159px 159px;
  padding-left: 8px;
  padding-right: 7px;
  width:max-content;
  padding-top: 16px;
}

.SorryText{
  color: white;
  margin-top: 12.5px;
  font-size: 18px;
  font-weight: 400;
  font-family: 'Roboto', sans-serif;
  line-height: 21.09px;
  padding-left: 20px;
}
.SorryDiv{
  display: grid;
  grid-template-columns: auto max-content auto;
  height: calc(100vh - 200px); /* 100vh - */
}

.FirstWhiteCircle{
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

.WhiteCircle{
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
.pacManIcon{
  width: 20px;
  display:inline;
  margin-top: 22px;
  margin-left: 4px;
}
.lastPacManIcon{
  width: 20px;
  display:inline;
  margin-top: 22px;

}
.sliderBackground{
  display: inline;
  width: calc(100% - 16px);
  margin-left: 8px;
  margin-right: 8px;
  height: 164px;
}
.titleText{
  -webkit-text-stroke-width: .007px;
  -webkit-text-stroke-color: #c9c9c9;
  font-family: 'Revalia', cursive;
  font-style: normal;
  font-weight: 400;
  font-size: 15px;
  line-height: 18.6px;
  color: #FFFFFF;
  text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25), 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.SlideShowDiv{
  padding-top: 113px;
  height: 30px;
  background-color: black;
  display: grid;          /* Margin, Text, Margin, Pacman, margin, dot, margin, dot, margin */
  grid-template-columns: 23px auto auto 20px 6px 20px 3px 20px 18px;
  padding-bottom: 21px;
  background-repeat: no-repeat;
  width: calc(100% - 16px);
  margin-left: auto;
  margin-right: auto;
  margin-top: -164px;
}
</style>
