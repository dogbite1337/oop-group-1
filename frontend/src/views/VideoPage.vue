<template>
  <div class="backgroundDiv" @click="clickedMe">
    <router-link class="HomeLink" to="/">
      <div class="backHomeDiv">
        <div class="SpaceDiv" />
        <img class="ArrowIcon" src="../projectImages/big_arrow.png" />
        <div class="SpaceDiv" />
      </div>
    </router-link>
    <img class="videoArea" src="../projectImages/theJuice.png" />
    <div class="descriptionAndCommentsDiv">
      <div class="SpaceDiv"/>
      <div class="middleDiv">
        <div v-if="!showDescriptionTab" class="notChosenDescriptionDiv">
          Description
        </div>
        <div v-if="showDescriptionTab" class="ChosenDescriptionDiv">
          Description
          <div class="LineDiv" />
        </div>
        <div class="SpaceDiv"/>
        <div class="commentsDiv">Comments({{amountOfComments}})</div>
      </div>     
      <div class="SpaceDiv"/>
    </div>
    <div class="UploaderDiv">
      <div class="SpaceDiv"/>
      <div class="square1 square">
        <img class="uploaderProfileDiv" src="../projectImages/xQcBanned.png"/>
      </div>
      <div class="SpaceDiv"/>
      <div class="square2 square">
        <div class="usernameDiv">
          {{User.username}}
        </div>
        <div class="subsDiv">
          Subscribers: {{spacedSubs}}
        </div>
      </div>
      <div class="SpaceDiv"/>
      <div class="square3 square">
        <div class="videosDiv">
          Videos: {{spacedVideos}}
        </div>
      </div>
      <div class="SpaceDiv"/>
      <div class="square4 square">
        <div class="subButtonDiv">
          <button class="subButton" value="Subscribe">+ Subscribe</button>
        </div>
      </div>
      <div class="SpaceDiv"/>
    </div>
    <div class="videoTitleDiv">
      {{video.title}}
    </div> 
    <div class="viewsAndDateDiv">
      <div class="SpaceDiv" />
      <div class="square playButtonDiv">
        <img class="playButton" src="../projectImages/small_grey_trans.png" />
      </div>
      <div class="SpaceDiv"/>
      <div class="viewsDiv square">
        {{spacedViews}} views
      </div>
      <div class="SpaceDiv" />
      <div class="uploadDateDiv square">
        11-04
      </div>
      <div class="SpaceDiv"/>
    </div>
    <div class="likeAndDislikeIconDiv">
      <div class="SpaceDiv"/>
      <div class="likeDiv">
        <img src="../projectImages/like_black_background.png" />
      </div>
      <div class="SpaceDiv"/>
      <div class="dislikeDiv">
        <img src="../projectImages/dislike_black_background.png" />
      </div>
      <div class="SpaceDiv"/>
      <div class="starDiv">
        <img src="../projectImages/grey_star_trans.png" />
      </div>
      <div class="SpaceDiv"/>
    </div>
    <div class="likesAndDislikesNumberDiv">
      <div class="SpaceDiv"/>
      <div class="likesNumberDiv">
        {{spacedLikes}}
      </div>
      <div class="SpaceDiv"/>
      <div class="dislikesNumberDiv">
        {{spacedDislikes}}
      </div>
      <div class="SpaceDiv"/>
      <div class="starNumberDiv">
        {{spacedStars}}
      </div>
      <div class="SpaceDiv"/>
    </div>
  </div>
  <div class="videosBody">
    <div v-if="showWatchNowInstead" class="test">
      Show Now Instead
    </div>
    <RelatedVideo
        v-for="(videoItem, index) of relatedVideos"
        :key="index"
        :video="videoItem"
        class="videoBox"
      />
  </div>
</template>
<script>
import User from '../jsClasses/general/User'
import Video from '../jsClasses/general/Video'
import Footer from '../components/Footer.vue'
import RelatedVideo from '../components/RelatedVideo.vue'
import store from '../store'

export default {
  name: 'VideoPage',
  components: {
    RelatedVideo,
    Footer
  },
  data() {
    return {
      showCommentsSection: false,
      showDescriptionTab: true,
      amountOfComments: 52,
      relatedVideos: (this.$store.getters.getEightFirstVideos ? this.$store.getters.getEightFirstVideos : undefined),
      video: '',
      spacedViews: 0,
      spacedLikes: 0,
      spacedDislikes: 0,
      spacedStars: 0,
      spacedSubs: 0,
      spacedVideos: 0,
      User: '',
      isOnVideosPage: false,
      showWatchNowInstead: false
    };
  },
  async created() {
    this.$store.subscribe(async (mutation, state) => {
      console.log(mutation);
      if(mutation.type == 'setRelatedVideoId'){
        this.loadRelevantInformation(mutation.payload)
      }
    });
  },
  async mounted() {
    this.loadRelevantInformation();
    this.isOnVideosPage = true;
    document.addEventListener('scroll', () => {
      if(window.scrollY >= 268){
        this.showWatchNowInstead = true;
      }
      else{
        this.showWatchNowInstead = false;
      }
    })
  },
  watch: {
  },
  methods: {
    async loadRelevantInformation(wantedUserId) {
      let videoRes = await fetch('/rest/getVideoById?' + new URLSearchParams({
            videoId: (wantedUserId === undefined ? this.$route.params.id : wantedUserId)
          }));
      let videoResponse = await videoRes.json();

      let emptyVideo = new Video(0, 0, '', '', '', 0, '', 0, 0, 0)
      this.video = Object.assign(emptyVideo, videoResponse);
      this.spacedViews = this.renderSpacedNumbers(this.video.views.toString())
      this.spacedLikes = this.renderSpacedNumbers(this.video.likes.toString())
      this.spacedDislikes = this.renderSpacedNumbers(this.video.dislikes.toString())
      this.spacedStars = this.renderSpacedNumbers(this.video.stars.toString())

      let uploaderRes = await fetch('/rest/getUserByUsername?' + new URLSearchParams({
            providedUsername: videoResponse.postedByUsername
          }));
      let uploaderResponse = await uploaderRes.json();
      let emptyUser = new User(0,'','','',0,0);
      this.User = Object.assign(emptyUser, uploaderResponse);
      this.spacedSubs = this.renderSpacedNumbers(this.User.subscribers.toString())
      this.spacedVideos = this.renderSpacedNumbers(this.User.videosPosted.toString())
    },
    renderSpacedNumbers(stringToPad) {
      let base = ''
      let startFrom = stringToPad % 1000;
      let spacedString = '';
      startFrom = startFrom.toString();

      for(let i = 0; i < stringToPad.length; i++){
        if(i != 0 && (i - stringToPad.length % 3) % 3 == 0){
          base += ' ' + stringToPad[i]
        }
        else{
          base += stringToPad[i]
        }
        spacedString = base;
      }
      return spacedString;  
    },
    clickedMe(e) {
      if (e.target.className == "commentsTab") {
        this.showCommentsSection = true;
        this.showDescriptionTab = false;
      }
      if (e.target.className == "descriptionTab"){
        this.showDescriptionTab = true;
        this.showCommentsSection = false;
      }
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

.watchNowGrid {
  height: max-content;
  background-color: red;
  grid-template-columns: 100px 100px 100px 100px;
}
.test{
  width: 100vw;
  height: 10px;
  background-color: red;
  position: sticky;
  top: 0px;
  display: inline-block;
  z-index: 10;
}
.videosBody{
  height: 423px;
  background-color: black;
  color: white;
}
.likeTextDiv, .dislikeTextDiv, .starTextDiv{
  color: #939393;
  font-size: 10px;
}

.likeDiv, .dislikeDiv, .starDiv{
  display: grid;
  grid-template-rows: auto 100px;
}
.uploaderProfileDiv{
  height: 40px;
  width: 40px;
  border-radius: 30px;
  margin-top: 9.5px;
}
.UploaderDiv{
  display: grid;
  grid-template-columns: 17px 40px 19px 82px 19px 20px auto max-content 19px;
  background-color: black;
  height: max-content;
  padding-bottom: 16px;
}
.videosDiv{
  font-size: 9px;
  margin-top: 29.5px;
  color: #939393;
}
.likeAndDislikeIconDiv{
  display: grid;
  grid-template-columns: 57px max-content auto max-content auto max-content 57px;
  background-color: black;
  padding-top: 24px;
  height: 15px;
  padding-bottom: 7px;
}
.square{
  margin-top: 1px;
  width: max-content;
  background-color: black;
  height: max-content;
  outline: solid black 1px;
  color: white;
}
.viewsDiv{
  margin-top: 1px;
  width: max-content;
  background-color: black;
  height: max-content;
  outline: solid black 1px;
  color: white;
  font-size: 10px;
  color: #939393;
}
.uploadDateDiv{
  margin-top: 2px;
  width: max-content;
  background-color: black;
  height: max-content;
  outline: solid black 1px;
  color: #939393;
  font-size: 10px;
}
.descriptionAndCommentsDiv{
  display: grid;
  grid-template-columns: auto max-content auto;
  font-family: 'Roboto', sans-serif;
  background-color: black;
  color: white;
  padding-top: 14px;
  margin-top: -5px;
  padding-bottom: 3px;
  border-bottom: 0.5px solid #BFBFBF;
}
.likesAndDislikesNumberDiv{
  display: grid;
  grid-template-columns: 0px 130px 12px 130px 12.5px 130px 1px;
  background-color: black;
  border-bottom: #BFBFBF solid 0.5px;
}
.likesNumberDiv, .dislikesNumberDiv, .starNumberDiv {
  text-align: center;
  color: #939393;
  background-color: black;
  font-size: 10px;
  padding-bottom: 10px;
}

.playButtonDiv{
  width: 13px;
  height: 9px;
  background-color: black;
  margin-top: 2.1px;
  outline: solid 1.5px #939393;
}
.middleDiv{
  display: grid;
  grid-template-columns: max-content 92px max-content;
}
.ArrowIcon {
  transform: rotate(00deg);
  transform: scale(4, 3.5);
  margin-top: 16px;
  margin-bottom: 12px;
  height: 25px;
  width: 28px;
  margin-left: 15px;
}
.viewsAndDateDiv{
  display: grid;
  grid-template-columns: 21px max-content 12px max-content 28px max-content;
  background-color: black;
  color: #939393;
  padding-bottom: 2px;
}
.usernameDiv{
  margin-top: 10px;
  font-size: 12px;
  margin-top: 14.5px;
  color: #E75858;
}
.playButton{
  margin-left: -10px;
  margin-top: -11.45px;
  transform: scale(0.6, 0.4)
}
.subButton{
  background-color:#E75858;
  height: 16px;
  color: white;
  font-size: 9px;
  outline: 1px solid black;
  padding: 2px;
  padding-left: 7px;
  padding-right: 6px;
  margin-top: 26px;
}
.subsDiv{
  font-size: 9px;
  margin-top: 1px;
  color: #939393;
}
.videoArea{
  margin-top: -57px;
  height: 268px;
  width: 100vw;
}
.videoTitleDiv{
  color: white;
  background-color: black;
  padding-left: 17px;
  padding-bottom: 9px;
}
.ChosenDescriptionDiv{
  color: #E75858;
}
.LineDiv{
  margin-top: 6px;
  height: 2px;
  background-color: #E75858;
}
</style>
