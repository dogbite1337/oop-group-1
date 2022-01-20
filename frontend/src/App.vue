<template>
  <main class="app-main" :class="isDarkTheme == true ? 'app-mainDarkTheme' : 'app-mainLightTheme'">
    <div class="BackDrop" :class="isDarkTheme == true ? 'BackDropDarkTheme' : 'BackDropLightTheme'" >
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
    return {
      isDarkTheme: true,
    };
  },
  async created() {
    // if we refreshed and there is a save, load saved theme
    if(await this.$store.getters.getIsDarkTheme == null && localStorage.isDarkTheme){
      console.log(1)
      this.isDarkTheme = await JSON.parse(localStorage.isDarkTheme);
      await this.$store.dispatch('setDarkTheme', this.isDarkTheme)
    }
    // if state has a saved theme and there is no save in localstoreage
    else if(((await this.$store.getters.getIsDarkTheme == true || await this.$store.getters.getIsDarkTheme == false) && !localStorage.isDarkTheme)){
      console.log(2)
      this.isDarkTheme = await this.$store.getters.getIsDarkTheme
      localStorage.setItem('isDarkTheme', JSON.stringify(this.isDarkTheme))
    }
    // if we enter the page for the first time
    else{
      console.log(3)
      this.isDarkTheme = true
    }
    
    // this.isDarkTheme = await this.$store.getters.getIsDarkTheme
    // this.isDarkTheme = false;
    // await this.$store.dispatch('setDarkTheme', this.isDarkTheme)
    // console.log(this.isDarkTheme)
  },
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
  height: 100vh;
}
.BackDrop {
  background-color: #131313;
  /* overflow-y: scroll; */
}
.lightTheme{
  color: #bfbfbf;
}
.BackDropLightTheme,
.app-mainLightTheme,
.MainDivLightTheme{
  background-color: white !important
}
</style>
