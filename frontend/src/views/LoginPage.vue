<template>
  <div class="backgroundDiv" @click="clickedMe">
    <router-link class="HomeLink" to="/">
      <div class="backHomeDiv">
        <div class="SpaceDiv" />
        <img class="ArrowIcon" src="../projectImages/white_arrow.png" />
        <div class="SpaceDiv" />
        <p class="HomeText">Home</p>
        <div class="SpaceDiv" />
        <img
          class="catIcon"
          src="../projectImages/whiteCatBlackBaground.png"
        />
        <div class="SpaceDiv" />
      </div>
    </router-link>
    <div v-if="!hideEyes" class="girlsOpenEyesDiv" />
    <div v-if="hideEyes" class="girlsClosedEyesDiv" />
    <div class="emailDiv">
      <p class="UsernameText">Username</p>
      <input
        v-model="wantedUserName"
        class="emailInput"
        type="text"
        placeholder="Please enter your email"
      />
    </div>
    <div class="lineDiv" />
    <div class="passwordDiv">
      <p class="PasswordText">Password</p>
      <input
        v-model="wantedPassword"
        class="passwordInput"
        type="password"
        placeholder="Please enter your password"
      />
      <div v-if="wantedPassword != ''" class="xDiv">
        <p class="Xsymbol">X</p>
      </div>
    </div>

    <div class="ButtonsDiv">
      <div class="SpaceDiv" />
      <button class="registerButton" value="Register">Register</button>
      <div class="SpaceDiv" />
      <button class="loginButton" @click="tryToLogIn" value="Login" v-if="canLogIn">Login</button>
      <button
        class="disabledLoginButton"
        disabled
        value="Login"
        v-if="!canLogIn"
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
import User from '../jsClasses/general/User'
import Footer from '../components/Footer.vue'

export default {
  name: 'LoginPage',
  components: {
    Footer
  },
  data() {
    return {
      canLogIn: false,
      hideEyes: '',
      wantedUserName: '',
      wantedPassword: '',
    };
  },
  watch: {
    wantedUserName() {
      if(this.wantedUserName.length > 0 && this.wantedPassword.length > 0){
        this.canLogIn = true;
      }
      else{
        this.canLogIn = false;
      }
    },
    wantedPassword() {
      if(this.wantedUserName.length > 0 && this.wantedPassword.length > 0){
        this.canLogIn = true;
      }
      else{
        this.canLogIn = false;
      }
    },
  },
  methods: {
    async tryToLogIn() {
      let user = {
        providedUserName: this.wantedUserName,
        providedPassword: this.wantedPassword
      }
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
.HomeLink{
  text-decoration: none;
}
.xDiv {
  color: white;
  width: 25px;
  height: 22px;
  z-index: 500;
  text-align: center;
  padding-top: 3px;
  position: relative;
  margin-left: 117%;
  margin-top: -23px;
  border-radius: 50px;
  background-color: #c4c4c4;
}
.ButtonsDiv {
  display: grid;
  grid-template-columns: 29px 154px 37px 154px 18px;
  width: 399px;
  margin-left: auto;
  margin-right: auto;
}
.UsernameText,
.PasswordText {
  display: inline;
}
.forgotPasswordDiv {
  text-align: center;
  font-family: 'Roboto', sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  line-height: 21px;
  /* identical to box height */

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
.loginButton {
  width: 154px;
  height: 40px;
  outline: solid 0.7px white;
  background: #2d2c2c;
  color: white;
  font-family: 'Roboto', sans-serif;
  font-size: 18px;
  line-height: 21px;
}
.disabledLoginButton {
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
.backgroundDiv {
  background-color: black;
  height: 100vh;
}
.lineDiv {
  height: 1px;
  background-color: black;
  width: 350px;
  position: relative;
  z-index: 10;
  margin-left: auto;
  margin-right: auto;
}
.emailInput {
  display: inline;
  position: absolute;
  width: 214.5px;
  margin-left: -115.5px;
  padding-left: 129px;
  background-color: white;
  height: 46px;
  margin-top: -16px;
  padding-top: 5px;
} /* 170.425 */
.passwordInput {
  display: inline;
  position: absolute;
  width: 170px;
  margin-left: -145px;
  padding-left: 129px;
  padding-right: 44px;
  background-color: white;
  height: 50px;
  margin-top: -16px;
  padding-top: 3px;
  outline: none;
}
.UsernameText {
  z-index: 3;
  position: relative;
  margin-top: 12.5px;
  font-size: 18px;
  font-weight: 400;
  font-family: 'Roboto', sans-serif;
  line-height: 21.06px;
}
.PasswordText {
  padding-right: 32px;
  z-index: 3;
  position: relative;
  font-size: 18px;
  font-weight: 400;
  font-family: 'Roboto', sans-serif;
  line-height: 21.06px;
}
.emailDiv {
  margin-top: 10px;
}
.passwordDiv {
  margin-bottom: 12px;
}
.emailDiv,
.passwordDiv {
  width: 232px;
  margin-left: auto;
  margin-right: auto;
  outline: 1px solid black;
  padding-top: 15px;
  padding-bottom: 15px;
  padding-left: 6px;
}

.passwordDiv {
  padding-right: 47px;
}
.emailDiv {
  padding-right: 47px;
}
.girlsOpenEyesDiv {
  height: 140px;
  text-align: center;
  background-image: url('../projectImages/open eyes.PNG');
  background-size: 97% 140px;
  background-repeat: no-repeat;
  margin-left: 15px;
}
.girlsClosedEyesDiv {
  height: 140px;
  text-align: center;
  background-image: url('../projectImages/cover eyes.PNG');
  background-size: 97% 140px;
  background-repeat: no-repeat;
  margin-left: 15px;
}
.backHomeDiv {
  display: grid; /* space, Arrow, space, Home, space, Cat */
  grid-template-columns: 17px 32px 5px 60px 12px 20px auto;
  background-color: black;
}
.ArrowIcon {
  transform: rotate(180deg);
  margin-top: 20px;
  margin-bottom: 12px;
  height: 25px;
  width: 28px;
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
</style>
