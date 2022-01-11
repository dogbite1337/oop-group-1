<template>
  <main class="app-main">
    <div class="BackDrop">
      <router-view />
    </div>
  </main>
</template>
<script>
import Header from './components/Header.vue';
import store from './store';

export default {
  name: 'App',
  components: {
    Header,
  },
  data() {
    return {};
  },
  created() {},
};
</script>

<style scoped></style>
<style>
* {
  margin: 0px;
  padding: 0px;
}
.app-main {
  background-color: #131313;
}
.BackDrop {
  height: 100vh; /* temp */
  background-color: #131313;
}
</style>
