<template>
  <main class="app-main">
    <router-view />
  </main>
</template>
<script>
import Header from './components/Header.vue';

export default {
  name: 'App',
  components: {
    Header
  },
  data() {
    return {
    };
  },
  methods: {
  },
};
</script>

<style scoped>
</style>
<style>
* {
  margin: 0px;
  padding: 0px;
}
</style>