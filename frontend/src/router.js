import { createRouter, createWebHistory } from 'vue-router';

// import views
import HomePage from './views/HomePage.vue';
import LoginPage from './views/LoginPage.vue';
import RegisterPage from './views/RegisterPage.vue';
import UploadVideoPage from './views/UploadVideoPage.vue';
import VideoPage from './views/VideoPage.vue';

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: HomePage,
    },
    {
      path: '/Login',
      component: LoginPage,
    },
    {
      path: '/Register',
      component: RegisterPage,
    },
    {
      path: '/Upload',
      component: UploadVideoPage,
    },
    {
      path: '/VideoPage/:id',
      component: VideoPage,
    },
  ],
});
