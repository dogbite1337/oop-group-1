<template>
  <img
    class="sliderBackground"
    :src="`src\\projectImages\\${
      decodeURI(sliderImageURls[currentSliderImageIndex]) +
      (gifs.includes(decodeURI(sliderImageURls[currentSliderImageIndex]))
        ? '.gif'
        : '.png')
    }`"
  />

  <div class="SlideShowDiv">
    <div class="SpaceBlock" />
    <div class="titleDiv">
      <p class="titleText">{{ sliderTitles[currentSliderImageIndex] }}</p>
    </div>
    <div class="SpaceBlock" />
    <img
      v-if="currentSliderImageIndex == 0"
      class="pacManIcon"
      src="../../projectImages/cleaned_pacman.png"
    />
    <div
      v-if="currentSliderImageIndex > 0"
      @click="currentSliderImageIndex = 0"
      class="FirstWhiteCircle"
    />
    <div class="SpaceBlock" />
    <img
      v-if="currentSliderImageIndex == 1"
      class="pacManIcon"
      src="../../projectImages/cleaned_pacman.png"
    />
    <div
      v-if="currentSliderImageIndex != 1"
      @click="currentSliderImageIndex = 1"
      class="WhiteCircle"
    />
    <div class="SpaceBlock" />
    <img
      v-if="currentSliderImageIndex == 2"
      class="lastPacManIcon"
      src="../../projectImages/cleaned_pacman.png"
    />
    <div
      v-if="currentSliderImageIndex != 2"
      @click="currentSliderImageIndex = 2"
      class="WhiteCircle"
    />
  </div>
</template>
<script>
export default {
  data() {
    return {
      sliderInterval: '',
      currentSliderImageIndex: 0,
      sliderImageURls: ['happyCats', 'smile', 'samuelCatJackson'],
      sliderTitles: [
        'Kitty Kissaten in Kyoto',
        'Top 10 feel Good Animes',
        'What did you call me? Meowdafaka',
      ],
      gifs: [
        'ghosts',
        'sorryKitty',
        'sumo-run',
        'shalsha-aizawa-falfa-aizawa',
        'sleepyCat',
        'WinterCold',
        'samuelCatJackson',
        'WinterWarm',
        'smiling-cat-creepy-cat',
        'cat-shooting',
      ],
    };
  },

  created() {
    this.setSliderInterval();
  },

  beforeUnmount() {
    clearInterval(this.sliderInterval);
  },

  methods: {
    setSliderInterval() {
      this.sliderInterval = setInterval(this.sliderIndexLoop, 5000);
    },

    sliderIndexLoop() {
      if (this.currentSliderImageIndex + 1 >= this.sliderImageURls.length) {
        this.currentSliderImageIndex = 0;
      } else {
        this.currentSliderImageIndex += 1;
      }
    },
  },
};
</script>

<style scoped>
.sliderBackground {
  display: block;
  width: calc(100% - 16px);
  margin-left: auto;
  margin-right: auto;
  height: 164px;
  max-width: 575px;
}

.SlideShowDiv {
  padding-top: 113px;
  height: 30px;
  background-color: black;
  display: grid; /* Margin, Text, Margin, Pacman, margin, dot, margin, dot, margin */
  grid-template-columns: 23px auto auto 20px 6px 20px 3px 20px 18px;
  padding-bottom: 21px;
  background-repeat: no-repeat;
  width: calc(100% - 16px);
  margin-left: auto;
  margin-right: auto;
  margin-top: -164px;
  max-width: 575px;
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
</style>
