<template>
  <div class="historyDivUnexpanded">
    <div class="EmptyDiv"/>
    <div v-if="values.length > 0" class="searchHistoryItem">{{values[0].length > 14 ? values[0].substring(0,14) + '...' : values[0]}}</div>
    <div v-if="values.length <= 0" class="EmptysearchHistoryItem" />
    <div class="EmptyDiv"/>
    <div v-if="values.length > 1" class="searchHistoryItem">{{values[1].length > 14 ? values[1].substring(0,14) + '...' : values[1]}}</div>
    <div v-if="values.length <= 1" class="NosearchHistoryItemYet">No Searches Yet</div>
    <div class="EmptyDiv"/>
    <div v-if="values.length > 2" class="searchHistoryItem">{{values[2].length > 14 ? values[2].substring(0,14) + '...' : values[2]}}</div>
    <div v-if="values.length <= 2" class="EmptysearchHistoryItem" />
    <div class="EmptyDiv"/>
  </div>
  <div v-if="expandedSearchHistory" class="historyDivUnexpanded">
    <div class="EmptyDiv"/>
    <div v-if="values.length > 3" class="searchHistoryItem">{{values[3].length > 14 ? values[3].substring(0,14) + '...' : values[3]}}</div>
    <div v-if="values.length <= 3" class="EmptysearchHistoryItem" />
    <div class="EmptyDiv"/>
    <div v-if="values.length > 4" class="searchHistoryItem">{{values[4].length > 14 ? values[4].substring(0,14) + '...' : values[4]}}</div>
    <div v-if="values.length <= 4" class="EmptysearchHistoryItem" />
    <div class="EmptyDiv"/>
    <div v-if="values.length > 5" class="searchHistoryItem">{{values[5].length > 14 ? values[5].substring(0,14) + '...' : values[5]}}</div>
    <div v-if="values.length <= 5" class="EmptysearchHistoryItem" />
    <div class="EmptyDiv"/>
  </div>
</template>
<script>

export default {
  props: ['expandedSearchHistory', 'values'],
  name: 'ExpandableSearchHistory',
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

.historyDivUnexpanded{
  height: 46px;
  background-color: white;
  margin-left: 19px;
  margin-right: 19px;
  display: grid;
  grid-template-columns: 13px 108px 13px 108px 13px 108px 13px;
  grid-template-rows: 100px;
  width: 372px;
  margin-left: auto;
  margin-right: auto;
}
.NosearchHistoryItemYet{
  color: black;
  background-color: white;

  height: 20.5px;
  text-align: center;
  padding-top: 6.5px;
  border-radius: 3px;
  margin-top: 12px;
  font-size: 13px;
}
.searchHistoryItem{
  color: white;
  background-color: rgba(0, 0, 0, 0.7);

  height: 20.5px;
  text-align: center;
  padding-top: 6.5px;
  border-radius: 3px;
  margin-top: 12px;
  font-size: 13px;
}
.EmptysearchHistoryItem{
  color: white;
  background-color: white;
  width: 108px;
  height: 20.5px;
  text-align: center;
  padding-top: 6.5px;
  border-radius: 3px;
  margin-top: 12px;
  font-size: 13px;
}
</style>