<template>
  <div class="MainDiv">
    <Header />
    <div class="NoLineDiv" />
    <BannerSlider v-if="showPage=='home'"/>

    <!-- search page here -->
    <!-- <div v-if="showSearchPage && !showResultsPage" class="searchPage">
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
    <div v-if="showSearchPage && !showResultsPage" class="clearHistoryDiv">
      <button class="clearHistoryButton">Clear history</button>
    </div>
    <div v-if="showSearchPage && !showResultsPage" class="clearHistoryDiv">
      <button @click="showResultsOfSearch" class="Search">Search</button>
    </div> -->




    <!-- search result page user -->
    <div v-if="relevantUsers.length > 0 && searchedYet && showResultsPage" class="userResultsTopDiv">
      <div class="userResultsDiv">
        <div />
        <div class="profileImageDiv">
          <div class="profileDivResultPage"><img class="profileInResultsPage" src="../projectImages/xqcBanned.png"></div>
          <div class="coveringDiv" />
          <div class="roundingDiv" />
        </div>
        <div/>
        <div class="SubsDiv">
          <p class="usernameP">{{relevantUsers[0].getUsername()}}</p>
          <p class="subscribersP">Subscribers: {{relevantUsers[0].getSubscribers()}}</p>
        </div>
        <div/>
        <p class="videosP">Videos: {{relevantUsers[0].getVideosPosted()}}</p>
        <div/>
      </div>
    </div>
    <div v-if="searchedYet && relevantUsers.length > 0 && showResultsPage" class="SubscribeDiv">
      <button class="SubscribeButton" value="Subscribe">+ Subscribe</button>
    </div>
    <div v-if="searchedYet && relevantUsers.length > 0 && showResultsPage" class="topVideosGrid">
      <div class="SpaceBlock" />
      <div class="firstVideo">
        <img class="linkVideoPic" src="../projectImages/birthdayPepe.png" />
        <div class="VideoTitleDiv">
          <p class="videoTitleP">Birthday presents</p>
          <p class="uploadedP">6 hours ago</p>
        </div>
      </div>
      <div class="SpaceBlock" />
      <div class="secondVideo">
        <img class="linkVideoPic" src="../projectImages/reallyXQC.png" />
        <div class="VideoTitleDiv">
          <p class="videoTitleP">I made the day for this kid</p>
          <p class="uploadedP">12:17</p>
        </div>
      </div>
      <div class="SpaceBlock" />
      <div class="thirdVideo">
        <img class="linkVideoPic" src="../projectImages/pepeBike.png" />
        <div class="VideoTitleDiv">
          <p class="videoTitleP">xQcOW</p>
          <p class="uploadedP">11:15</p>
        </div>
      </div>
      <div class="SpaceBlock" />
    </div>


    <!-- <p v-if="searchedYet && relevantUsers.length > 0 && showResultsPage" class="checkAllVideosLink">check all 10 videos ></p> -->
    <div v-if="searchResults.length > 0 && showResultsPage">
      <VideoResultCard :video="searchResults[0]" :searchQuery="lastSearchQuery" v-if="searchResults[0]" />
    </div>
    <div v-if="!searchedYet && !showSearchPage && !showResultsPage" class="CardsContainer">
      <VideoCard
        v-for="(videoItem, index) of relevantVideos"
        :key="index"
        :video="videoItem"
        class="videoBox"
      />
    </div>
    <div v-if="searchResults.length == 0 && searchedYet && showResultsPage" class="SorryDiv">
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
import VideoResultCard from '../components/VideoResultCard.vue'
import User from '../jsClasses/general/User'
import BannerSlider from '../components/FrontPageComponents/bannerSlider.vue'
import store from '../store'


export default {
  name: 'HomePage',
  components: {
    Header,
    VideoCard,
    ExpandableSearchHistory,
    VideoResultCard,
    TrendLink,
    Footer,
    BannerSlider
  },
  async created() {
    let allVideos = await this.getVideosForCurrentPage();
    this.$store.dispatch('cacheFirstEightVideos', allVideos);
    this.relevantVideos = []
    for(let i = allVideos.length; i > 0; i--){
      let video = new Video(0, 0, '', '', '')
      let newVideo = Object.assign(video, allVideos[i-1])
      this.relevantVideos.push(newVideo);
    }
    this.$store.subscribe(async (mutation, state) => {
      if(mutation.type == "setShowSearchPage"){
        this.showSearchPage = true;
      }
      if(mutation.type == "setShouldResetToStartPage"){
        this.searchedYet = false;
        this.showResultsPage = false;
        this.showSearchPage = false;
        this.relevantUsers = []
        this.relevantVideos = []
        let cachedVideos = this.$store.getters.getEightFirstVideos;
        for(let i = 0; i < cachedVideos.length; i++){
          let oldVideo = new Video(0,0,'','','',0,'');
          oldVideo = Object.assign(oldVideo, cachedVideos[i])
          this.relevantVideos.push(oldVideo)
        }
      }
      if(mutation.type == "setSearchResults"){
        this.relevantVideos = []
        this.lastSearchQuery = this.$store.getters.getLastSearchQuery;
        for(let i = mutation.payload.length; i > 0; i--){
          let video = new Video(0, 0, '', '', '', 0, '')
          let newVideo = Object.assign(video, mutation.payload[i-1])
          if(newVideo.getVideoId() !== 0){
            this.searchResults.push(newVideo);
          }
        }
        this.searchedYet = true;
        let res = await fetch('/rest/getUserByUsername?' + new URLSearchParams({
          providedUsername: this.lastSearchQuery
        }));

        let response = await res.json();
        let foundUser = new User(0, "", "", "", 0, 0)
        foundUser = Object.assign(foundUser, response);
        if(foundUser.getUserId() !== 0){
          this.relevantUsers.push(foundUser);
        }   
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
  beforeMount() {
    this.relevantVideos = [];
    this.searchedYet = false;
    this.showSearchPage = false;
    this.showResultsPage = false;
    let cachedVideos = this.$store.getters.getEightFirstVideos;
    if(cachedVideos){
      for(let i = 0; i < cachedVideos.length; i++){
        let oldVideo = new Video(0,0,'','','',0,'');
        oldVideo = Object.assign(oldVideo, cachedVideos[i])
        this.relevantVideos.push(oldVideo)
      }
    }
  },
  data() {
    return {
      showPage: 'home',
      // currentSliderImageIndex: 0,
      // sliderImageURls: ['happyCats', 'smile', 'samuelCatJackson'],
      // sliderTitles: ['Kitty Kissaten in Kyoto', 'Top 10 feel Good Animes', 'What did you call me? Meowdafaka'],
      // gifs: ['ghosts', 'sorryKitty', 'sumo-run', 'shalsha-aizawa-falfa-aizawa', 'sleepyCat',
      // 'WinterCold', 'samuelCatJackson', 'WinterWarm', 'smiling-cat-creepy-cat', 'cat-shooting'],
      relevantVideos: this.$store.getters.getSearchResults ? this.$store.getters.getSearchResults : [
      ],
      relevantUsers: [],
      searchedYet: this.$store.getters.getSearchResults === null ? false : true,
      showSearchPage: false,
      expandedSearchHistory: false,
      mySearchHistory: ['John..', 'Why', 'Is the', 'Entire website', 'About', 'Cats..'],
      topTenTrend: ['Cats', 'More Cats', 'All cats', 'Cats?!', 'Cats.', 'Cats!', 'Why are there so many cats', 'John', 'Stop This', 'Madness'],
      showResultsPage: false,
      tempVideo: new Video(7, 8, "test", 'xQc talks about the meaning of "juice"', "ha", 5655123, "xQc"),
      searchResults: [],
      lastSearchQuery: (this.$store.getters.getLastSearchQuery ? this.$store.getters.getLastSearchQuery : ''),
      currentPage: 1
    };
  },
  methods: {
    showResultsOfSearch() {
      this.showResultsPage = true;
      this.showSearchPage = false;
    },
    expandSearchHistory() {
      this.expandedSearchHistory = true;
    },
    closeSearchHistory() {
      this.expandedSearchHistory = false;
    },
    async getVideosForCurrentPage() {
      if(this.currentPage == 1 && this.$store.getters.getEightFirstVideos){
        return this.$store.getters.getEightFirstVideos;
      }
      if(!this.currentPage){
        this.currentPage = 1;
      }
      let res = await fetch('/rest/getVideosForCurrentPage?' + new URLSearchParams({
        currentPage: this.currentPage
      }));

      let response = await res.json();
      return response;
    },
    async getAllVideos() {
      let res = await fetch('/rest/getAllVideos');

      let response = await res.json();
      return response;
    },
    getImageUrl(){
      return this.imgPreUrl
    },
    setSliderIndexToZero(){
      this.currentSliderImageIndex = 0;
    },
    setSliderIndexToOne(){
      this.currentSliderImageIndex = 1;
    },
    setSliderIndexToTwo(){
      this.currentSliderImageIndex = 2;      
    }
  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto:wght@300;400&display=swap');

.MainDiv{
  background-color: #131313;
}
.test {
  width: 200px;
  height: 200px;
}
.coveringDiv{
  background-color: #131313;
  width: 45px;
  height: 50px;
  z-index: 3;
  margin-left: -45px;
  margin-top: -40px;
  position: relative;
}
.searchPage{
  color: white;
}
.linkVideoPic{
  width: 115px;
  height: 59px;
}
.roundingDiv{
  background-color: transparent;
  outline: 10px solid #131313;
  border-radius: 30px;
  width: 50px;
  height: 50px;
  z-index: 3;
  margin-top: -50px;
  position: relative;
}
.userResultsDiv{
  display: grid;
  grid-template-columns: 17px 39px 19px 165px 1px auto;
}
.usernameP{
  color: #E75858;
}
.subscribersP{
  color: #939393;
}
.NoLineDiv{
  height: 2px;
  background-color: black;
  margin-top: -1px;
}
.profileInResultsPage{
  width: 80px;
  height: 50px;
  object-fit:cover;
  border-radius: 30px;
  transform: scaleX(0.5);
  transform: scaleY(1);
  margin-left: -28px;
  z-index: -1;
}
.checkAllVideosLink{
  color: #E75858;
  text-align: center;
  padding-bottom: 9.5px;
  border-bottom: 1px solid #BFBFBF;
  font-size: 12px;
  font-family: 'Roboto', sans-serif;
}
.videosP{
  color: #939393;
  margin-top: 18.5px;
}
.SubscribeDiv{
  background-color: none;
  text-align: right;
  margin-right: 16px;
  margin-top: -57.5px;
}
.clearHistoryDiv{
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  margin-top: 10px;
}
.topVideosGrid{
  display: grid;
  grid-template-columns: auto 115px 16px 115px 16px 115px auto;
  background-color: #131313;
  margin-top: 15px;
  padding-bottom: 20px;
}
.firstVideo, .secondVideo, .thirdVideo{
  width: 115px;
  height: 93px;
  background-color: red;
}
.videoTitleP{
  background-color: black;
  font-size: 9.5px;
  font-family: 'Roboto', sans-serif;
  text-align:center;
  color: white;
  padding-top: 3px;
}
.VideoTitleDiv{
  margin-top: -4px;
  height: 36px;
  background-color: black;
}
.uploadedP{
  font-size: 11px;
  line-height: 8.2px;
  text-align: center;
  color: #939393;
  margin-top: 5px;
  font-family: 'Roboto', sans-serif;
}
.SubscribeButton{
  height: 16px;
  width: 63px;
  font-size: 9px;
  line-height: 11px;
  font-family: 'Roboto', sans-serif;
  font-style: normal;
  font-weight: normal;
  color: white;
  background-color: #E75858;
  margin-bottom: 21px;
  position: relative;
  top: -1.5px;
}
.footerDiv{
  display: grid;
  grid-template-rows: 5vh auto;
  background-color: #131313;
}

/* .clearHistoryButton{
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
} */

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

.profileDivResultPage{
  height: 40px;
  width: 40px;
  z-index: -1;
}

.userResultsTopDiv{
  background-color: #131313;
  grid-template-rows: max-content;
  padding-bottom: 15px;
  padding-left: 8px;
  padding-right: 7px;
  width:max-content;
  padding-top: 16px;
  margin-bottom: 10px;
}
.CardsContainer{
  background-color: #131313;
  display: grid;
  grid-template-columns: auto auto;
  grid-template-rows: 159px 159px 159px;
  padding-left: 8px;
  padding-right: 7px;
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

</style>
