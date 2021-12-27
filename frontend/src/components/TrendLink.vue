<template>
  <div class="trendGrid">
      <div v-if="trends.length > 0" class="trendDiv">
        <p class="numberP">#1 ~ </p><p class="trendP">{{ trends[0] }}</p>
      </div>
      <div/>
      <div v-if="trends.length > 1" class="trendDiv">
        <p class="numberP">#2 ~ </p><p class="trendP">{{ trends[1] }}</p>
      </div>

      <div v-if="trends.length > 2" class="trendDiv">
        <p class="numberP">#3 ~ </p><p class="trendP">{{ trends[2] }}</p>
      </div>
      <div/>
      <div v-if="trends.length > 3" class="trendDiv">
        <p class="numberP">#4 ~ </p><p class="trendP">{{ trends[3] }}</p>
      </div>

      <div v-if="trends.length > 4" class="trendDiv">
        <p class="numberP">#5 ~ </p><p class="trendP">{{ trends[4] }}</p>
      </div>
      <div/>
      <div v-if="trends.length > 5" class="trendDiv">
        <p class="numberP">#6 ~ </p><p class="trendP">{{ trends[5] }}</p>
      </div>

      <div v-if="trends.length > 6" class="trendDiv">
        <p class="numberP">#7 ~ </p><p class="trendP">{{ trends[6] }}</p>
      </div>
      <div/>
      <div v-if="trends.length > 7" class="trendDiv">
        <p class="numberP">#8 ~ </p><p class="trendP">{{ trends[7] }}</p>
      </div>

      <div v-if="trends.length > 8" class="trendDiv">
        <p class="numberP">#9 ~ </p><p class="trendP">{{ trends[8] }}</p></div>
      <div/>
      <div v-if="trends.length > 9" class="LasttrendDiv">
        <p class="numberP">#10 ~ </p><p class="trendP">{{ trends[9]}}</p>
      </div>
  </div>
</template>
<script>

export default {
  props: ['trends'],
  name: 'TrendLink',
  data() {
    return {
    };
  },
  methods: {

  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

.trendGrid{
  display: grid;
  grid-template-columns: 173px 10px 173px;
}
.trendDiv{
  background-color: white;
  margin-left: 17px;
  margin-top: 12px;
  color: black;
  border-radius: 3px;
  display: grid;
  grid-template-columns: auto auto;
  padding-top: 1px;
  padding-bottom: 1px;
  padding-right: 5px;
}
.LasttrendDiv{
  background-color: white;
  margin-left: 17px;
  margin-top: 12px;
  color: black;
  border-radius: 3px;
  display: grid;
  grid-template-columns: auto auto;
  padding-top: 1px;
  padding-bottom: 1px;
  padding-right: 5px;
}
.numberP{
  display:inline;
  padding-left: 8px;
  padding-top: 2px;
}

</style>