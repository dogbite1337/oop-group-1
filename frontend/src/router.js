import { createRouter, createWebHistory } from 'vue-router';

// import views
import HomePage from './views/HomePage.vue';
import LoginPage from './views/LoginPage.vue';

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: HomePage,
    },
    {
      path: '/Login',
      component: LoginPage
    }
  ],
});
