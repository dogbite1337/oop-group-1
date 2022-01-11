<template>
  <div class="CardBox">
    <div class="SpaceBlock" />
    <img
      class="ImageBox"
      :src="
        'https://img.youtube.com/vi/' +
        video.videoURL.substring(32, 43) +
        '/default.jpg'
      "
    />
    <div class="SpaceeBlock" />
    <div class="titleDiv">
      <div class="matchGrid">
        <p class="matchedWord">{{ matchedString }}</p>
        <pre v-if="nonMatchedString" class="titleText">{{
          nonMatchedString
        }}</pre>
        <div />
      </div>
      <div class="uploaderSubDiv">
        <div class="uploaderDiv">
          <div class="upDiv">UP</div>
          <div />
          <div class="userNameDiv">
            {{ video ? video.postedByUsername : '' }}
          </div>
          <div />
        </div>
        <div class="viewsDiv">
          <div class="arrowDiv">
            <img
              class="playArrow"
              src="../projectImages/dark-play-button.png"
            />
            <div class="innerViewsDiv">{{ spacedViews }} views</div>
          </div>
        </div>
      </div>
    </div>
    <div class="SpaceBlock" />
  </div>
</template>
<script>
export default {
  props: ['video', 'searchQuery'],
  name: 'VideoResultCard',
  data() {
    return {
      videoAbove10: this.video ? this.video.views >= 10 : false,
      videoAbove100: this.video ? this.video.views >= 100 : false,
      videoAbove1k: this.video ? this.video.views >= 1000 : false,
      videoAbove10k: this.video ? this.video.views >= 10000 : false,
      videoAbove100k: this.video ? this.video.views >= 100000 : false,
      videoAbove1Mil: this.video ? this.video.views >= 1000000 : false,
      videoAbove10Mil: this.video ? this.video.views >= 10000000 : false,
      videoAbove100Mil: this.video ? this.video.views >= 100000000 : false,
      spacedViews: this.video ? this.renderSpacedViews() : 0,
      matchedWord: this.searchQuery ? this.searchQuery : '',
      matchedString: this.video ? this.renderMatchedString() : '',
      nonMatchedString: this.video ? this.renderTitle() : '',
      checkedWords: false,
      empty: '',
      fullstring: '',
      providedSearchQuery: this.searchQuery ? this.getSearchQuery() : '',
      resultIsIdentical: false,
    };
  },
  methods: {
    renderMatchedString() {
      let originalString = this.video.title;
      let matchedString = '';

      if (!this.checkedWords) {
        for (let i = 0; i < this.video.title.length; i++) {
          if (
            originalString
              .substring(i, this.searchQuery.length)
              .toLowerCase() === this.searchQuery.toLowerCase()
          ) {
            matchedString += originalString.substring(
              i,
              this.searchQuery.length
            );
          } else {
            matchedString += ' ';
          }
        }
      }

      return matchedString;
    },
    renderTitle() {
      let empty = '';
      for (let i = 0; i < this.searchQuery.length; i++) {
        empty += ' ';
      }
      this.empty = empty;

      let nonMatchedString = this.video.title.replaceAll(
        this.searchQuery,
        empty
      );

      this.nonMatchedString = nonMatchedString;

      if (this.searchQuery.toLowerCase() === nonMatchedString.toLowerCase()) {
        this.resultIsIdentical = true;
        nonMatchedString = '';
      }

      this.checkedWords = true;
      return nonMatchedString;
    },
    getSearchQuery() {},
    renderSpacedViews() {
      let viewsString = this.video.views.toString();
      let base = '';
      let startFrom = this.video.views % 1000;
      let spacedViews = '';
      startFrom = startFrom.toString();

      for (let i = 0; i < viewsString.length; i++) {
        if (i != 0 && (i + startFrom - 1) % 3 == 0) {
          base += ' ' + viewsString[i];
        } else {
          base += viewsString[i];
        }
        spacedViews = base;
      }
      return spacedViews;
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');
.matchedWord {
  color: #e75858;
  font-size: 12px;
  width: 160px;
  position: absolute;
  margin-top: 8px;
}

.playArrow {
  height: 6px;
  width: 10px;
  transform: scale(0.7);
  background-color: none;
  margin-left: 0.5px;
  padding-top: 1.5px;
}

.userNameDiv {
  padding-left: 2px;
}

.viewsP {
  color: white;
  width: 30px;
}

.arrowDiv {
  background-color: white;
  height: 8px;
  width: 11px;
  outline: solid 1px #939393;
  display: grid;
  grid-template-columns: 21px 9px auto;
  margin-left: 1px;
}

.matchGrid {
  display: grid;
}
.upDiv {
  width: 12px;
  height: 8px;
  background-color: white;
  margin-bottom: 7px;
  border: solid 1px #939393;
  font-size: 7px;
  text-align: center;
  padding-top: 1.5px;
  font-family: 'Roboto', sans-serif;
}

.CardBox {
  border-bottom: solid 1px grey;
  display: grid;
  grid-template-columns: 13px 191px 22px 160px auto;
}

.ImageBox {
  width: 191px;
  height: 95px;
  margin-top: 7px;
  margin-bottom: 7px;
}

.titleText {
  color: white;
  font-size: 12px;
  margin-top: 7px;
  overflow-x: auto;
  white-space: pre-wrap;
  white-space: -moz-pre-wrap;
  white-space: -pre-wrap;
  white-space: -o-pre-wrap;
}

.uploaderDiv,
.viewsDiv {
  color: #939393;
  font-size: 10px;
}

.viewsDiv {
  padding-left: 1px;
}

.uploaderDiv {
  display: grid;
  grid-template-columns: max-content 8px auto;
  padding-left: 1px;
}

.innerViewsDiv {
  width: max-content;
  margin-top: -1.6px;
}

.uploaderSubDiv {
  position: relative;
  top: 41px;
}

.arrowDiv {
  padding-left: 1px;
}
</style>
