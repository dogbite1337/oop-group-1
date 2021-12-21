import { createRouter, createWebHistory } from 'vue-router';

// import views
import HomePage from './views/HomePage.vue';

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: HomePage,
    },
  ],
});
