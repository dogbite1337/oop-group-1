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
          src="../projectImages/light_Arrow_With_Background.png"
        />
        <div class="SpaceDiv" />
        <p v-if="darkTheme" class="DarkHomeText">Home</p>
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
    <div v-if="!hideEyes" class="girlsOpenEyesDiv" />
    <div v-if="hideEyes" class="girlsClosedEyesDiv" />
    <div class="emailDiv">
      <p class="UsernameText">Username</p>
      <input
        v-if="darkTheme"
        v-model="wantedUserName"
        class="DarkEmailInput"
        type="text"
        placeholder="Please enter your email"
      />
      <input
        v-if="!darkTheme"
        v-model="wantedUserName"
        class="LightEmailInput"
        type="text"
        placeholder="Please enter your email"
      />
    </div>
    <div v-if="darkTheme" class="DarkLineDiv" />
    <div v-if="!darkTheme" class="LightLineDiv" />
    <div class="passwordDiv">
      <p class="PasswordText">Password</p>
      <input
        v-if="!darkTheme"
        v-model="wantedPassword"
        class="LightPasswordInput"
        type="password"
        placeholder="Please enter your password"
      />
      <input
        v-if="darkTheme"
        v-model="wantedPassword"
        class="DarkPasswordInput"
        type="password"
        placeholder="Please enter your password"
      />
      <div v-if="wantedPassword != ''" class="xDiv">
        <p class="Xsymbol">X</p>
      </div>
    </div>
    <div v-if="wantedPassword == ''" class="sevenPixelsLineDiv" />
    <div v-if="wantedPassword != ''" class="fivePixelsLineDiv" />
    <div class="DescriptionDiv">
      <p v-if="darkTheme" class="DarkDescriptionText">Description</p>
      <p v-if="!darkTheme" class="LightDescriptionText">Description</p>
      <input
        v-if="darkTheme"
        v-model="wantedDescription"
        class="DarkDescriptionInput"
        type="textarea"
        placeholder="Tell us about yourself (Optional)"
      />
      <input
        v-if="!darkTheme"
        v-model="wantedDescription"
        class="LightDescriptionInput"
        type="textarea"
        placeholder="Tell us about yourself (Optional)"
      />
    </div>
    <div class="RegisterContent">
      <div class="RegisterDiv">
        <div />
        <div class="PreviewImageDiv">
          <p v-if="darkTheme" class="DarkPreviewText">Preview</p>
          <p v-if="!darkTheme" class="LightPreviewText">Preview</p>
          <img
            class="PreviewImage"
            :src="`${currentImageURL == '' ? baseImage : currentImageURL}`"
          />
        </div>
        <div />
        <div class="ImageURLInputDiv">
          <input
            v-if="darkTheme"
            v-model="wantedImageURL"
            class="DarkImageURLInput"
            type="text"
            placeholder="Image url goes here.."
          />
          <input
            v-if="!darkTheme"
            v-model="wantedImageURL"
            class="LightImageURLInput"
            type="text"
            placeholder="Image url goes here.."
          />
          <input
            @click="uploadPreviewPicture"
            v-if="canUpload && darkTheme"
            class="DarkUploadImageButton"
            type="button"
            value="Upload Profile Image"
          />
          <input
            @click="uploadPreviewPicture"
            v-if="canUpload && !darkTheme"
            class="LightUploadImageButton"
            type="button"
            value="Upload Profile Image"
          />
          <input
            v-if="!canUpload"
            class="disabledUploadImageButton"
            type="button"
            value="Upload Profile Image"
          />
          <input
            v-if="!canRegister && darkTheme"
            class="DarkDisabledRegisterButton"
            type="button"
            value="Register"
          />
          <input
            v-if="!canRegister && !darkTheme"
            class="LightDisabledRegisterButton"
            type="button"
            value="Register"
          />
          <input
            @click="registerUser"
            v-if="canRegister"
            class="RegisterButton"
            type="button"
            value="Register"
          />
        </div>
        <div />
      </div>
    </div>
  </div>
  <Footer />
</template>
<script>
import User from '../jsClasses/general/User';
import Footer from '../components/Footer.vue';
import store from '../store';

export default {
  name: 'RegisterPage',
  components: {
    Footer,
  },
  data() {
    return {
      canRegister: false,
      hideEyes: '',
      wantedUserName: '',
      wantedPassword: '',
      wantedImageURL: '',
      currentImageURL: '',
      wantedDescription: '',
      baseImage: this.darkTheme
        ? 'src\\projectImages\\Dark_User.png'
        : 'src\\projectImages\\Light_User.png',
      canUpload: false,
      darkTheme: this.$store.getters.getIsDarkTheme,
    };
  },
  mounted() {
    if (!this.darkTheme) {
      document.getElementsByClassName('backgroundDiv')[0].style =
        'background-color: #F0F0F0;';
      document.getElementsByClassName('backHomeDiv')[0].style =
        'background-color: white;';

      document.getElementsByClassName('UsernameText')[0].style =
        'position: relative; left: -20px; color: black;';
      document.getElementsByClassName('PasswordText')[0].style =
        'position: relative; left: -20px; color: black;';
    }
  },
  watch: {
    currentImageURL() {
      if (this.wantedUserName.length > 0 && this.wantedPassword.length > 0) {
        this.canRegister = true;
      }
    },
    wantedImageURL() {
      if (this.wantedImageURL.length > 0) {
        this.canUpload = true;
      } else {
        this.canUpload = false;
      }
    },
    wantedUserName() {
      if (
        this.wantedUserName.length > 0 &&
        this.wantedPassword.length > 0 &&
        this.currentImageURL.length > 0
      ) {
        this.canRegister = true;
      } else {
        this.canRegister = false;
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
    uploadPreviewPicture() {
      this.currentImageURL = this.wantedImageURL;
    },
    async registerUser() {
      let user = {
        providedUserName: this.wantedUserName,
        providedPassword: this.wantedPassword,
        providedDescription: this.wantedDescription,
        providedProfileURL: this.currentImageURL,
      };
      let res = await fetch('/api/register', {
        method: 'POST',
        body: JSON.stringify(user),
      });

      let response = await res.json();
      if (response == null) {
        //Failed to Register
        alert('Error - Failed to Register, Username already taken!');
      } else {
        let currentUser = new User();
        user = Object.assign(currentUser, response);
        this.$store.dispatch('login', user);
        document.getElementsByClassName('HomeLink')[0].click();
      }
    },
    clickedMe(e) {
      if (
        e.target.className == 'LightPasswordInput' ||
        e.target.className == 'DarkPasswordInput'
      ) {
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
.imageURLInput,
.DarkUploadImageButton,
.LightUploadImageButton,
.registerButton {
  display: block;
}
.RegisterContent {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
}
.DarkUploadImageButton,
.LightUploadImageButton,
.registerButton {
  margin-top: 9px;
}
.LightImageURLInput {
  width: 243px;
  padding-left: 5px;
  border: 0.7px solid black;
  padding-top: 1px;
  height: 21px;
  margin-top: 3px;
  margin-right: 19px;
}
.DarkImageURLInput {
  width: 243px;
  padding-left: 5px;
  border: 0.7px solid #ffffff;
  padding-top: 1px;
  height: 21px;
  margin-top: 3px;
  margin-right: 19px;
}
.LightUploadImageButton {
  width: 192px;
  color: black;
  font-size: 16px;
  font-family: 'Roboto', sans-serif;
  background-color: #c4c4c4;
  border: 0.7px solid black;
}
.DarkUploadImageButton {
  width: 192px;
  color: white;
  font-size: 16px;
  font-family: 'Roboto', sans-serif;
  background-color: #2d2c2c;
  border: 0.7px solid #ffffff;
}
.disabledUploadImageButton {
  display: block;
  margin-top: 9px;
  width: 192px;
  color: rgba(255, 255, 255, 0.45);
  font-size: 16px;
  font-family: 'Roboto', sans-serif;
  background: rgba(45, 44, 44, 0.5);
  border: 0.7px solid rgba(255, 255, 255, 0.45);
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.RegisterButton {
  width: 142px;
  color: white;
  font-size: 18px;
  line-height: 21px;
  font-family: 'Roboto', sans-serif;
  margin-top: 10px;
  height: 40px;
  background: #2d2c2c;
  border: 0.7px solid #ffffff;
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.LightDisabledRegisterButton {
  width: 142px;
  font-size: 18px;
  line-height: 21px;
  font-family: 'Roboto', sans-serif;
  color: white;
  margin-top: 10px;
  height: 40px;
  background: rgba(181, 100, 100, 0.5);
  border: 1px solid rgba(181, 100, 100, 0.5);
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.DarkDisabledRegisterButton {
  width: 142px;
  font-size: 18px;
  line-height: 21px;
  font-family: 'Roboto', sans-serif;
  color: rgba(255, 255, 255, 0.45);
  margin-top: 10px;
  height: 40px;
  background: rgba(45, 44, 44, 0.5);
  border: 0.7px solid rgba(255, 255, 255, 0.45);
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.DarkPreviewText {
  color: white;
  margin-bottom: 3px;
  text-align: center;
}
.LightPreviewText {
  color: black;
  margin-bottom: 3px;
  text-align: center;
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

.sevenPixelsLineDiv {
  height: 7px;
}
.fivePixelsLineDiv {
  height: 5px;
}
.PreviewImage {
  width: 80px;
  height: 80px;
  border-radius: 60px;
}
.PreviewImageDiv {
  width: max-content;
  padding-right: 10px;
}
.RegisterDiv {
  display: grid; /* space, preview, space, input, space */
  grid-template-columns: 33px auto 0px auto 8px;
}
.UsernameText,
.PasswordText {
  display: inline;
  color: white;
  font-family: 'Roboto', sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  line-height: 21px;
}
.DarkDescriptionText {
  display: inline;
  color: white;
  font-family: 'Roboto', sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  line-height: 21px;
}
.LightDescriptionText {
  position: relative;
  left: -11px;
  z-index: 10;
  display: inline;
  color: black;
  font-family: 'Roboto', sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  line-height: 21px;
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

.backgroundDiv {
  background-color: black;
  height: 100vh;
}

.DarkLineDiv {
  height: 1px;
  background-color: black;
  width: 350px;
  position: relative;
  z-index: 10;
  margin-left: auto;
  margin-right: auto;
}
.LightDescriptionInput {
  display: inline;
  position: absolute;
  width: 214.5px;
  margin-left: -115.5px;
  padding-left: 129px;
  background-color: white;
  height: 46px;
  padding-bottom: 64px;
  margin-top: -14px;
  padding-top: 3px;
  color: black;
}
.DarkDescriptionInput {
  display: inline;
  position: absolute;
  width: 214.5px;
  margin-left: -115.5px;
  padding-left: 129px;
  background-color: #2d2c2c;
  height: 46px;
  padding-bottom: 64px;
  margin-top: -14px;
  padding-top: 3px;
  color: white;
} /* 170.425 */
.LightEmailInput {
  display: inline;
  position: absolute;
  width: 214.5px;
  margin-left: -115.5px;
  padding-left: 129px;
  background-color: white;
  border-bottom: 3px solid black;
  height: 46px;
  margin-top: -16px;
  padding-top: 5px;
  color: black;
}
.DarkEmailInput {
  display: inline;
  position: absolute;
  width: 214.5px;
  margin-left: -115.5px;
  padding-left: 129px;
  background-color: #2d2c2c;
  height: 46px;
  margin-top: -16px;
  padding-top: 5px;
  color: white;
} /* 170.425 */
.LightPasswordInput {
  display: inline;
  position: absolute;
  color: black;
  width: 170px;
  margin-left: -145px;
  padding-left: 129px;
  padding-right: 44px;
  background-color: white;
  border-bottom: solid 3px black;
  height: 50px;
  margin-top: -16px;
  padding-top: 3px;
  outline: none;
}
.DarkPasswordInput {
  display: inline;
  position: absolute;
  color: white;
  width: 170px;
  margin-left: -145px;
  padding-left: 129px;
  padding-right: 44px;
  background-color: #2d2c2c;
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
.DescriptionDiv {
  display: block;
  width: 292px;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 100px;
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
  margin-top: 3px;
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
  margin-bottom: 10px;
}
.LightArrowIcon {
  margin-top: 20px;
  margin-bottom: 12px;
  height: 25px;
  width: 28px;
}

.ArrowIcon {
  transform: rotate(180deg);
  margin-top: 20px;
  margin-bottom: 12px;
  height: 25px;
  width: 28px;
}
.DarkHomeText {
  font-size: 24px;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  line-height: 28.13px;
  margin-top: 18px;
  color: white;
  margin-bottom: 15px;
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
.catIcon {
  width: 28px;
  height: 31px;
  margin-top: 15px;
  margin-bottom: 12px;
}
</style>
