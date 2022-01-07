<template>
  <div class="title">
    <h1>Top 10 Trending Searches</h1>
  </div>
  <div class="trendGrid">
    <div class="itemsContainer">
      <div v-for="(trend, index) of trends" :key="trend" class="item">
        <p>
          {{
            index +
            1 +
            '. ' +
            (trend.length > 15 ? trend.substring(0, 14) + '...' : trend)
          }}
        </p>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: ['trends'],
  name: 'TrendLink',
  data() {
    return {};
  },
  methods: {},
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

p {
  color: white;
}

h1 {
  font-size: 20px;
}

.title {
  margin: 10px 5% 10px 5%;
}

.item {
  background-color: white;
  margin: 5px;
  border-radius: 5px;
  height: 3vh;
  padding-top: 5px;
}

.item p {
  margin-left: 10px;
  color: black;
}

.trendGrid {
  border: 1px solid white;
  margin: 10px 5% 10px 5%;
  border-radius: 5px;
}

.itemsContainer {
  margin: 10px 5px 10px 5px;
  display: grid;
  grid-template-columns: repeat(2, 50%);
  grid-template-rows: repeat(5, 20%);
}
</style>
