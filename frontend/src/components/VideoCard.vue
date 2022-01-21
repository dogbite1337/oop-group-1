<template>
  <router-link
    :to="{ path: '/VideoPage/' + video.videoId, query: video.videoId }"
  >
    <div v-if="!darkTheme" class="LightCardBox">
      <img
        class="ImageBox"
        :src="
          'https://img.youtube.com/vi/' +
          video.videoURL.substring(32, 43) +
          '/default.jpg'
        "
      />
      <div class="lowerDiv">
        <div class="titleDiv">
          <p class="LightTitleText">
            {{
              video.title.length > 24
                ? video.title.substring(0, 24) + '...'
                : video.title
            }}
          </p>
        </div>
        <div class="LightUpBoxAndUsernameDiv">
          <div v-if="!darkTheme" class="LightUpDiv">UP</div>
          <div v-if="!darkTheme" class="LightUserNameDiv">
            {{ video.postedByUsername }}
          </div>
        </div>
      </div>
    </div>
    <div v-if="darkTheme" class="DarkCardBox">
      <img
        class="ImageBox"
        :src="
          'https://img.youtube.com/vi/' +
          video.videoURL.substring(32, 43) +
          '/default.jpg'
        "
      />
      <div class="lowerDiv">
        <div class="titleDiv">
          <p class="DarkTitleText">
            {{
              video.title.length > 24
                ? video.title.substring(0, 24) + '...'
                : video.title
            }}
          </p>
        </div>
        <div v-if="darkTheme" class="DarkUpBoxAndUsernameDiv">
          <div v-if="darkTheme" class="DarkUpDiv">UP</div>
          <div v-if="darkTheme" class="DarkUserNameDiv">
            {{ video.postedByUsername }}
          </div>
        </div>
      </div>
    </div>
  </router-link>
</template>
<script>
export default {
  props: ['video'],
  name: 'VideoCard',
  mounted() {
    this.$store.subscribe(async (mutation, state) => {
      if (mutation.type == 'setDarkTheme') {
        if (mutation.payload) {
          this.darkTheme = true;
        } else {
          this.darkTheme = false;
        }
      }
    });
  },
  data() {
    return {
      thumbNail: '',
      darkTheme: this.$store.getters.getIsDarkTheme,
    };
  },

  methods: {},
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

* {
  text-decoration: none;
}
.LightUpBoxAndUsernameDiv {
  display: grid;
  grid-template-columns: auto auto auto;
  margin-top: 7.16px;
  background-color: white;
  width: 150px;
  margin-left: auto;
  margin-right: auto;
}
.DarkUpBoxAndUsernameDiv {
  display: grid;
  grid-template-columns: auto auto auto;
  margin-top: 7.16px;
  background-color: #131313;
  width: 150px;
  margin-left: auto;
  margin-right: auto;
}

.LightCardBox {
  height: max-content;
  max-width: 192px;
  max-height: 160px;
  background-color: white;
  border: 0.7px solid #000000;
  color: black;
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  margin-left: 6px;
  margin-right: 6px;
  padding-bottom: 12px;
}

.DarkCardBox {
  height: max-content;
  max-width: 192px;
  max-height: 160px;
  background-color: #000000;
  border: 0.7px solid #000000;
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  margin-left: 6px;
  margin-right: 6px;
  padding-bottom: 12px;
}

.ImageBox {
  height: 80px;
  width: 100%;
  max-width: 190px;
}

.LightTitleText {
  color: black;
  text-align: center;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  line-height: 14.06px;
  font-size: 12px;
  margin-top: 8.15px;
}

.DarkTitleText {
  color: white;
  text-align: center;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  line-height: 14.06px;
  font-size: 12px;
  margin-top: 8.15px;
}

.LightUpDiv {
  color: black;
  text-align: center;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  line-height: 14.06px;
  font-size: 12px;
  border: solid 1px black;
  width: max-content;
  padding-left: 5px;
  padding-right: 5px;
  padding-top: 1.7px;
  padding-bottom: 1px;
  position: relative;
  left: 8px;
}
.DarkUpDiv {
  color: white;
  text-align: center;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  line-height: 14.06px;
  font-size: 12px;
  border: solid 1px white;
  width: max-content;
  padding-left: 5px;
  padding-right: 5px;
  padding-top: 1.7px;
  padding-bottom: 1px;
}
.LightUserNameDiv {
  color: black;
  text-align: center;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  font-size: 10px;
  line-height: 12px;
  margin-top: 2px;
  padding-top: 1.5px;
}

.DarkUserNameDiv {
  color: white;
  text-align: center;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  font-size: 10px;
  line-height: 12px;
  margin-top: 2px;
  padding-top: 1.5px;
}
@media screen and (max-width: 400px) {
  .LightCardBox, .DarkCardBox {
    max-width: 100vw;
    max-height: 260px;
    margin-top: 1px;
    margin-bottom: 1px;
  }
  .ImageBox {
    height: 105px;
  }
}

@media screen and (min-width: 400px) {
  .LightCardBox, .DarkCardBox {
    max-width: 48vw;
    max-height: 400px;
    margin-left: auto;
    margin-right: auto;
    margin-top: 1px;
    margin-bottom: 5px;
    margin-left: 3px;
    margin-right: 3px;
  }
  .ImageBox {
    height: 100px;
    width: 190px;
  }
}

@media screen and (min-width: 440px) and (max-width: 560px) {
  .LightCardBox, .DarkCardBox {
    max-width: 48vw;
    max-height: 200px;
    margin-left: auto;
    margin-right: auto;
    margin-top: 1px;
    margin-bottom: 6px;
    margin-left: 5px;
    margin-right: 5px;
  }
  .ImageBox {
    height: 120px;
    width: 220px;
  }
}

@media screen and (min-width: 560px) and (max-width: 600px) {
  .LightCardBox, .DarkCardBox {
    max-width: 56vw;
    max-height: max-content;
    margin-left: auto;
    margin-right: auto;
    margin-top: 1px;
    margin-bottom: 6px;
    margin-left: 5px;
    margin-right: 5px;
  }
  .ImageBox {
    height: 100px;
    width: 270px;
  }
}
@media screen and (min-width: 601px) {
  .LightCardBox, .DarkCardBox {
    max-width: 42vw;
    max-height: max-content;
    margin-left: auto;
    margin-right: auto;
    margin-top: 1px;
    margin-bottom: 6px;
    margin-left: 2px;
    margin-right: 2px;
  }
  .ImageBox {
    height: 130px;
    width: 196px;
  }
}
</style>
