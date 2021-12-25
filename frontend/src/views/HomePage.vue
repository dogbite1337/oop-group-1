<template>
  <div class="MainDiv">
    <Header />
    <img class="sliderBackground" :src="`src\\projectImages\\${
      decodeURI(sliderImageURls[currentSliderImageIndex]) 
      + 
      (gifs.includes(decodeURI(sliderImageURls[currentSliderImageIndex])) ? '.gif' : '.png')}`">
    <div class="SlideShowDiv">
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
    <div class="CardsContainer">

      

      <VideoCard class="Box1"/>
      <VideoCard class="Box1"/>
      <VideoCard class="Box1"/>
      <VideoCard class="Box1"/>
      <VideoCard class="Box1"/>
      <VideoCard class="Box1"/>
      
      
      

    </div>
    <Footer />
  </div>
</template>
<script>
import Header from '../components/Header.vue'
import VideoCard from '../components/VideoCard.vue'
import Footer from '../components/Footer.vue'


export default {
  name: 'HomePage',
  components: {
    Header,
    VideoCard,
    Footer
  },
  data() {
    return {
      currentSliderImageIndex: 0,
      sliderImageURls: ['happyCats', 'smile', 'samuelCatJackson'],
      sliderTitles: ['Kitty Kissaten in Kyoto', 'Top 10 feel Good Animes', 'What did you call me? Meowdafaka'],
      gifs: ['ghosts', 'sorryKitty', 'sumo-run', 'shalsha-aizawa-falfa-aizawa', 'sleepyCat',
      'WinterCold', 'samuelCatJackson', 'WinterWarm', 'smiling-cat-creepy-cat', 'cat-shooting']
    };
  },
  methods: {
    htmlDecode(input) {
      var doc = new DOMParser().parseFromString(input, "text/html");
      return doc.documentElement.textContent;
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
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto:wght@300;400&display=swap');

.MainDiv{
  background-color: #131313;
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
