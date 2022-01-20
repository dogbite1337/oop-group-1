<template>
  <div class="backgroundDiv" @click="clickedMe">
    <router-link class="HomeLink" to="/">
      <div class="backHomeDiv">
        <div class="SpaceDiv" />
        <img
          v-if="darkTheme"
          class="ArrowIcon"
          src="../projectImages/white_arrow.png"
        />
        <img
          v-if="!darkTheme"
          class="LightArrowIcon"
          src="../projectImages/light_Arrow.png"
        />
        <div class="SpaceDiv" />
        <p v-if="darkTheme" class="HomeText">Home</p>
        <p v-if="!darkTheme" class="LightHomeText">Home</p>
        <div class="SpaceDiv" />
        <img
          v-if="darkTheme"
          class="catIcon"
          src="../projectImages/whiteCatBlackBaground.png"
        />
        <img
          v-if="!darkTheme"
          class="catIcon"
          src="../projectImages/blackCatTransparentBackground.png"
        />
        <div class="SpaceDiv" />
      </div>
    </router-link>
    <div class="girlsGrid">
      <div class="SpaceDiv" />
      <div v-if="!hideEyes" class="girlsOpenEyesDiv" />
      <div class="SpaceDiv" />
    </div>
    <div v-if="hideEyes" class="girlsClosedEyesDiv" />
    <div class="inputGrid">
      <div class="emailGrid">
        <div class="SpaceDiv" />
        <div class="usernameText">Username</div>
        <div class="SpaceDiv" />
        <input
          v-model="wantedUserName"
          type="email"
          placeholder="Please enter your email"
          class="emailInput"
        />
        <div class="SpaceDiv" />
      </div>
      <div class="LineDiv" />
      <div class="passwordGrid">
        <div class="SpaceDiv" />
        <div class="passwordText">Password</div>
        <div class="SpaceDiv" />
        <input
          v-model="wantedPassword"
          type="password"
          class="passwordInput"
          placeholder="Please enter your password"
        />
        <div class="SpaceDiv" />
      </div>
    </div>
    <div class="ButtonsDiv">
      <div class="SpaceDiv" />
      <router-link class="HomeLink" to="/Register">
        <button class="registerButton" value="Register">Register</button>
      </router-link>
      <div class="SpaceDiv" />
      <button
        class="DarkLoginButton"
        @click="tryToLogIn"
        value="Login"
        v-if="canLogIn && darkTheme"
      >
        Login
      </button>
      <button
        class="LightLoginButton"
        @click="tryToLogIn"
        value="Login"
        v-if="canLogIn && !darkTheme"
      >
        Login
      </button>
      <button
        class="LightDisabledLoginButton"
        disabled
        value="Login"
        v-if="!canLogIn"
      >
        Login
      </button>
      <button
        class="DarkDisabledLoginButton"
        disabled
        value="Login"
        v-if="!canLogIn && darkTheme"
      >
        Login
      </button>
      <div class="SpaceDiv" />
    </div>
    <div class="forgotPasswordDiv">Forgot your password?</div>
  </div>
  <Footer />
</template>
<script>
import User from '../jsClasses/general/User';
import Footer from '../components/Footer.vue';
import store from '../store';

export default {
  name: 'LoginPage',
  components: {
    Footer,
  },
  data() {
    return {
      canLogIn: false,
      hideEyes: '',
      wantedUserName: '',
      wantedPassword: '',
      darkTheme: this.$store.getters.getIsDarkTheme,
    };
  },
  mounted() {
    if (!this.darkTheme) {
      document.getElementsByClassName('backgroundDiv')[0].style =
        'background-color: #F0F0F0;';
      document.getElementsByClassName('registerButton')[0].style =
        'border: solid 1px #B56464; color: #B56464; background-color: #F0F0F0;';
      document.getElementsByClassName('backHomeDiv')[0].style =
        'background-color: white;';
    }
  },
  watch: {
    wantedUserName() {
      if (this.wantedUserName.length > 0 && this.wantedPassword.length > 0) {
        this.canLogIn = true;
      } else {
        this.canLogIn = false;
      }
    },
    wantedPassword() {
      if (this.wantedUserName.length > 0 && this.wantedPassword.length > 0) {
        this.canLogIn = true;
      } else {
        this.canLogIn = false;
      }
    },
  },
  methods: {
    async tryToLogIn() {
      let user = {
        providedUserName: this.wantedUserName,
        providedPassword: this.wantedPassword,
      };
      let res = await fetch('/api/login', {
        method: 'POST',
        body: JSON.stringify(user),
      });

      let response = await res.json();
      if (response == null) {
        //Failed to log in
        alert('Error - Failed to login, bad credentials!');
      } else {
        let currentUser = new User();
        user = Object.assign(currentUser, response);
        this.$store.dispatch('login', user);
        document.getElementsByClassName('HomeLink')[0].click();
        this.$router.push('/');
      }
    },
    clickedMe(e) {
      if (e.target.className == 'passwordInput') {
        this.hideEyes = true;
      } else {
        this.hideEyes = false;
        if (e.target.className == 'Xsymbol') {
          this.wantedPassword = '';
        }
      }
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto:wght@300;400&display=swap');

* {
  outline: none;
  border: none;
}
.HomeLink {
  text-decoration: none;
}

.ButtonsDiv {
  display: grid;
  grid-template-columns: auto max-content auto max-content auto;
  width: 399px;
  margin-left: auto;
  margin-right: auto;
}

.LineDiv {
  height: 1px;
  background-color: black;
  margin-left: -30px;
}
.forgotPasswordDiv {
  text-align: center;
  font-family: 'Roboto', sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  line-height: 21px;
  text-align: center;
  color: #eb6a6a;
  margin-top: 23px;
}
.registerButton {
  width: 154px;
  height: 40px;
  outline: solid 0.7px white;
  background: #2d2c2c;
  color: white;
  font-family: 'Roboto', sans-serif;
  font-size: 18px;
  line-height: 21px;
}
.LightLoginButton {
  width: 154px;
  height: 40px;
  outline: solid 0.7px white;
  background: #b56464;
  color: white;
  font-family: 'Roboto', sans-serif;
  font-size: 18px;
  line-height: 21px;
}
.DarlLoginButton {
  width: 154px;
  height: 40px;
  outline: solid 0.7px white;
  background: #2d2c2c;
  color: white;
  font-family: 'Roboto', sans-serif;
  font-size: 18px;
  line-height: 21px;
}
.DarkDisabledLoginButton {
  width: 154px;
  height: 40px;
  background: rgba(45, 44, 44, 0.5);
  border: 0.7px solid rgba(255, 255, 255, 0.45);
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  color: rgba(255, 255, 255, 0.45);
  font-family: 'Roboto', sans-serif;
  font-size: 18px;
  line-height: 21px;
}
.LightDisabledLoginButton {
  width: 154px;
  height: 40px;
  background: rgba(181, 100, 100, 0.5);
  border: 0.7px solid rgba(255, 255, 255, 0.45);
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  color: rgba(255, 255, 255, 0.45);
  font-family: 'Roboto', sans-serif;
  font-size: 18px;
  line-height: 21px;
}
.backgroundDiv {
  background-color: black;
  height: 100vh;
}

.girlsGrid {
  display: grid;
  grid-template-columns: auto max-content auto;
}

.emailGrid {
  display: grid;
  grid-template-columns: 24px max-content 20px max-content auto;
  margin-top: 10px;
  padding-top: 20px;
  padding-bottom: 20px;
}

.passwordGrid {
  display: grid;
  grid-template-columns: 28px max-content 20px max-content auto;
  padding-top: 20px;
  padding-bottom: 20px;
  margin-bottom: 12px;
}

.girlsOpenEyesDiv {
  margin-top: 20vh;
  height: 140px;
  text-align: center;
  width: 420px;
  background-image: url('../projectImages/open eyes.PNG');
  background-size: 100% 140px;
  background-repeat: no-repeat;
}

.backgroundDiv {
  background-color: #131313;
}
.girlsClosedEyesDiv {
  margin-top: 20vh;
  height: 140px;
  text-align: center;
  width: 420px;
  margin-left: auto;
  margin-right: auto;
  background-image: url('../projectImages/cover eyes.PNG');
  background-size: 100% 140px;
  background-repeat: no-repeat;
}
.backHomeDiv {
  display: grid; /* space, Arrow, space, Home, space, Cat */
  grid-template-columns: 17px 32px 5px 60px 12px 20px auto;
  background-color: black;
}

.inputGrid {
  grid-template-rows: 60px 1px 60px;
  background-color: white;
  padding-left: 24px;
  width: 300px;
  margin-left: auto;
  margin-right: auto;
}
.ArrowIcon {
  transform: rotate(180deg);
  margin-top: 20px;
  margin-bottom: 12px;
  height: 25px;
  width: 28px;
}
.LightHomeText {
  font-size: 24px;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  line-height: 28.13px;
  margin-top: 18px;
  color: black;
  margin-bottom: 15px;
}
.HomeText {
  font-size: 24px;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  line-height: 28.13px;
  margin-top: 18px;
  color: white;
  margin-bottom: 15px;
}
.catIcon {
  width: 28px;
  height: 31px;
  margin-top: 15px;
  margin-bottom: 12px;
}

.LightArrowIcon {
  margin-top: 15px;
}

@media screen and (min-width: 1000px) {
  .girlsOpenEyesDiv,
  .girlsClosedEyesDiv {
    transform: scale(1.5);
    position: relative;
    top: -50px;
  }

  .registerButton,
  .LightLoginButton,
  .DarkLoginButton,
  .LightDisabledLoginButton,
  .DarkDisabledLoginButton {
    transform: scale(1.1);
  }

  .inputGrid {
    width: 260px;
  }

  .inputGrid {
    width: 500px;
  }
  .usernameText,
  .passwordText {
    padding-left: 0px;
    margin-left: 0px;
  }
  .ButtonsDiv {
    width: 500px;
  }
}

@media screen and (max-width: 500px) {
  .girlsOpenEyesDiv {
    width: 400px;
  }
}

@media screen and (max-width: 450px) {
  .girlsOpenEyesDiv,
  .girlsClosedEyesDiv {
    width: 350px;
  }

  .ButtonsDiv {
    width: max-content;
  }
  .registerButton,
  .LightLoginButton,
  .DarkLoginButton,
  .LightDisabledLoginButton,
  .DarkDisabledLoginButton {
    transform: scale(0.9);
  }
}

@media screen and (max-width: 400px) {
  .girlsOpenEyesDiv,
  .girlsClosedEyesDiv {
    width: 300px;
  }

  .ButtonsDiv {
    width: max-content;
  }
  .registerButton,
  .LightLoginButton,
  .DarkLoginButton,
  .LightDisabledLoginButton,
  .DarkDisabledLoginButton {
    transform: scale(0.8);
  }
  .inputGrid {
    width: 260px;
  }

  .usernameText,
  .passwordText {
    padding-left: 0px;
    margin-left: -40px;
  }
}

@media screen and (max-width: 325px) {
  .girlsOpenEyesDiv,
  .girlsClosedEyesDiv {
    width: 250px;
  }

  .ButtonsDiv {
    width: max-content;
  }
  .registerButton,
  .LightLoginButton,
  .DarkLoginButton,
  .LightDisabledLoginButton,
  .DarkDisabledLoginButton {
    transform: scale(0.7);
  }
  .inputGrid {
    width: 220px;
  }

  .usernameText,
  .passwordText {
    padding-left: 0px;
    margin-left: -40px;
  }

  input {
    width: 140px;
  }

  .ButtonsDiv {
    width: 250px;
    padding: 0px;
    margin: 0px;
  }
}

@media screen and (max-width: 325px) {
  .girlsOpenEyesDiv,
  .girlsClosedEyesDiv {
    width: 250px;
  }

  .ButtonsDiv {
    width: max-content;
  }
  .registerButton,
  .LightLoginButton,
  .DarkLoginButton,
  .LightDisabledLoginButton,
  .DarkDisabledLoginButton {
    transform: scale(0.6);
  }
  .inputGrid {
    width: 220px;
  }

  .usernameText,
  .passwordText {
    padding-left: 0px;
    margin-left: -40px;
  }

  input {
    width: 140px;
  }

  .ButtonsDiv {
    grid-template-columns: auto 50px 70px 50px auto;
    width: 180px;
    padding: 0px;
    margin: 0px;
  }
}
</style>
