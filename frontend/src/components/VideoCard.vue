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
  max-width: 190px;
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
  max-width: 190px;
  max-height: 160px;
  background-color: black;
  border: 0.7px solid #000000;
  color: black;
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  margin-left: 6px;
  margin-right: 6px;
  padding-bottom: 12px;
}

.lowerDiv {
  width: 180px;
}
.ImageBox {
  height: 80px;
  width: 100%;
  max-width: 180px;
}

@media screen and (max-width: 418px) {
  .ImageBox {
    height: 160px;
    width: 100vw;
    max-width: 100vw;
  }
  .lowerDiv {
    width: 100vw;
  }
  .LightCardBox, .DarkCardBox {
    max-width: 100vw;
    width: 100vw;
    background-color: white;
    border: 0.7px solid #000000;
    color: black;
    box-sizing: border-box;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    padding-bottom: 12px;
    display: grid;
  }
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


</style>
