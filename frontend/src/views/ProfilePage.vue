<template>
  <div v-if="currentUser" class="BackgroundDiv">
    <router-link class="HomeLink" to="/">
      <div class="backHomeDiv">
        <div class="SpaceDiv" />
        <img class="ArrowIcon" src="../projectImages/white_arrow.png" />
        <div class="SpaceDiv" />
        <p class="HomeText">Home</p>
        <div class="SpaceDiv" />
        <img class="catIcon" src="../projectImages/whiteCatBlackBaground.png" />
        <div class="SpaceDiv" />
      </div>
    </router-link>
    <div v-if="currentUser" class="userNameDiv">{{ currentUser.username }}</div>
    <div v-if="currentUser" class="imageDiv">
      <img class="profileImage" :src="currentUser.profileURL" />
    </div>
    <div class="SectionGrid">
      <div class="SpaceBlock" />
      <div class="ProfileTextDiv">
        <div
          @click="switchToMyProfile"
          v-if="!chosenProfileInfo"
          class="notChosenProfileInfo"
        >
          Profile Info
        </div>
        <div v-if="chosenProfileInfo" class="ChosenProfileInfo">
          Profile Info
        </div>
      </div>
      <div class="SpaceBlock" />
      <div class="myVideosTextDiv">
        <div
          @click="switchToMyVideos"
          v-if="!chosenMyVideos"
          class="notChosenMyVideos"
        >
          My Videos
        </div>
        <div v-if="chosenMyVideos" class="ChosenMyVideos">My Videos</div>
      </div>
      <div class="SpaceBlock" />
      <div class="mySubscribersDiv">
        <div
          @click="switchToMySubscribers"
          v-if="!chosenSubscribers"
          class="notChosenSubscriptions"
        >
          Subscriptions
        </div>
        <div v-if="chosenSubscribers" class="ChosenSubscribers">
          Subscriptions
        </div>
      </div>
      <div class="SpaceBlock" />
    </div>
    <div v-if="chosenProfileInfo && currentUser" class="ProfileInfoSection">
      <div class="SubscribersGrid">
        <div class="SpaceBlock" />
        <div class="SubscribersText">Subscribers:</div>
        <div class="SpaceBlock" />
        <div class="SubscribersNumber">{{ currentSubs }}</div>
        <div class="SpaceBlock" />
        <div class="FillerBlock" />
      </div>
      <div v-if="currentUser" class="VideosGrid">
        <div class="SpaceBlock" />
        <div class="VideosText">Videos:</div>
        <div class="SpaceBlock" />
        <div class="VideosNumber">{{ currentUser.videosPosted }}</div>
        <div class="SpaceBlock" />
        <div class="FillerBlock" />
      </div>
      <div v-if="currentUser" class="InfoGrid">
        <div class="UsernameGrid">
          <div class="SpaceBlock" />
          <div v-if="!editMode" class="UsernameText">Username:</div>
          <div v-if="editMode" class="UsernameText">New Username:</div>
          <div class="SpaceBlock" />
          <div v-if="!editMode" class="UsernameValue">
            {{ currentUser.username }}
          </div>
          <input
            v-model="wantedUsername"
            v-if="editMode"
            type="text"
            :placeholder="currentUser.username"
            class="UsernameInput"
          />
          <div class="SpaceBlock" />
          <div class="FillerBlock" />
        </div>
        <div class="PasswordGrid">
          <div class="SpaceBlock" />
          <div v-if="!editMode" class="PasswordText">Password:</div>
          <div v-if="editMode" class="NewPasswordText">New Password:</div>
          <div class="SpaceBlock" />
          <div v-if="!editMode" class="PasswordValue">***********</div>
          <input
            v-model="wantedPassword"
            v-if="editMode"
            placeholder="New Password"
            class="NewPasswordInput"
            type="password"
          />
          <div class="SpaceBlock" />
          <div class="FillerBlock" />
        </div>
        <div class="DescriptionGrid">
          <div class="SpaceBlock" />
          <div v-if="!editMode" class="DescriptionText">Description:</div>
          <div v-if="editMode" class="DescriptionText">New Description:</div>
          <div class="SpaceBlock" />
          <div v-if="!editMode" class="DescriptionValue">
            {{ currentUser.description }}
          </div>
          <textarea
            v-if="editMode"
            name="yourReply"
            class="descriptionInput"
            rows="5"
            cols="33"
            :placeholder="currentUser.description"
            v-model="wantedDescription"
          />
          <div class="SpaceBlock" />
          <div class="FillerBlock" />
        </div>
      </div>
      <div class="UploadGrid">
        <div class="SpaceBlock" />
        <div class="PreviewGrid">
          <div class="previewText">Preview</div>
          <img
            v-if="!wantedImage"
            class="previewImage"
            src="../projectImages/Dark_User.png"
          />
          <img v-if="wantedImage" class="previewImage" :src="wantedImage" />
        </div>
        <div class="SpaceBlock" />
        <div class="EditGrid">
          <input
            class="imageInput"
            type="text"
            placeholder="Image URL goes here"
            v-model="wantedImage"
          />
          <button
            v-if="!editMode"
            @click="enableEditMode"
            class="editInfoButton"
          >
            Edit Info
          </button>
          <button
            v-if="editMode"
            @click="cancelEditMode"
            class="cancelInfoButton"
          >
            Cancel
          </button>
          <button v-if="editMode" @click="saveChanges" class="saveInfoButton">
            Save
          </button>
        </div>
        <div class="SpaceBlock" />
      </div>
    </div>
    <div v-if="chosenMyVideos" class="myVideosSection">
      <RelatedVideo
        v-for="(videoItem, index) of myVideos"
        :key="index"
        :video="videoItem"
        class="videoBox"
      />
    </div>
    <div v-if="chosenSubscribers" class="mySubscribersSection">
      <SubscriptionToUser
        v-for="(videoItem, index) of myVideos"
        :key="index"
        class="videoBox"
      />
    </div>
    <Footer class="footerDiv" />
  </div>
</template>
<script>
import User from '../jsClasses/general/User';
import Footer from '../components/Footer.vue';
import RelatedVideo from '../components/RelatedVideo.vue';
import Video from '../jsClasses/general/Video';
import SubscriptionToUser from '../components/SubscriptionToUser.vue';

export default {
  name: 'ProfilePage',
  components: {
    Footer,
    RelatedVideo,
    SubscriptionToUser,
  },
  data() {
    return {
      chosenProfileInfo: true,
      chosenMyVideos: false,
      chosenSubscribers: false,
      myVideos: [],
      currentUser: null,
      currentSubs: null,
      editMode: false,
      wantedImage: null,
      wantedUsername: '',
      wantedPassword: '',
      wantedDescription: '',
    };
  },
  async mounted() {
    let userRes = await fetch(
      '/rest/getUserByUsername?' +
        new URLSearchParams({
          providedUsername: this.$route.params.username,
        })
    );
    let userResponse = await userRes.json();
    let foundUser = new User();
    foundUser = Object.assign(foundUser, userResponse);
    this.currentUser = foundUser;
    this.currentSubs = this.renderSpacedNumbers(
      foundUser.subscribers.toString()
    );
  },
  watch: {
    wantedImage() {
      if (this.wantedImage) {
        this.editMode = true;
      }
    },
  },
  methods: {
    cancelEditMode() {
      this.editMode = false;
    },
    enableEditMode() {
      this.editMode = true;
    },
    async saveChanges() {
      this.editMode = false;
      let newInfo = {
        userId: this.currentUser.userId,
        username:
          this.wantedUsername.length > 0
            ? this.wantedUsername
            : this.currentUser.username,
        password: this.wantedPassword,
        description:
          this.wantedDescription.length > 0
            ? this.wantedDescription
            : this.currentUser.description,
        profileURL: this.wantedImage
          ? this.wantedImage
          : this.currentUser.profileURL,
      };

      let res = await fetch('/api/updateUser', {
        method: 'POST',
        body: JSON.stringify(newInfo),
      });

      this.currentUser.username =
        this.wantedUsername.length > 0
          ? this.wantedUsername
          : this.currentUser.username;
      this.currentUser.description =
        this.wantedDescription.length > 0
          ? this.wantedDescription
          : this.currentUser.description;
      this.currentUser.profileURL = this.wantedImage
        ? this.wantedImage
        : this.currentUser.profileURL;

      this.wantedUsername = '';
      this.wantedDescription = '';
      this.wantedImage = null;
    },
    renderSpacedNumbers(stringToPad) {
      let base = '';
      let startFrom = stringToPad % 1000;
      let spacedString = '';
      startFrom = startFrom.toString();
      if (stringToPad.length <= 3) {
        return parseInt(stringToPad);
      }

      for (let i = 0; i < stringToPad.length; i++) {
        if (i != 0 && (i - (stringToPad.length % 3)) % 3 == 0) {
          base += ' ' + stringToPad[i];
        } else {
          base += stringToPad[i];
        }
        spacedString = base;
      }
      return spacedString;
    },
    uploadImage() {
      this.editMode = true;
    },
    switchToMyProfile() {
      this.chosenProfileInfo = true;
      this.chosenMyVideos = false;
      this.chosenSubscribers = false;
    },
    async switchToMyVideos() {
      this.chosenProfileInfo = false;
      this.chosenMyVideos = true;
      this.chosenSubscribers = false;
      let videoRes = await fetch(
        '/rest/getAllVideosByUsername?' +
          new URLSearchParams({
            providedUsername: 'xQc',
          })
      );
      this.myVideos = [];
      let videoResponse = await videoRes.json();
      for (let i = 0; i < videoResponse.length; i++) {
        let newVideo = new Video();
        let assignedVideo = Object.assign(newVideo, videoResponse[i]);
        this.myVideos.push(assignedVideo);
      }
    },
    switchToMySubscribers() {
      this.chosenProfileInfo = false;
      this.chosenMyVideos = false;
      this.chosenSubscribers = true;
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto:wght@300;400&display=swap');

* {
  overflow-x: hidden;
  max-width: 100vw;
}
.EditGrid {
  display: grid;
}
.BigGrid {
  display: grid;
  background-color: black;
  min-width: 430px;
  width: 100vw;
  max-width: 100vw;
  overflow-x: hidden;
  max-height: max-content;
}
.UploadGrid {
  display: grid;
  grid-template-columns: auto max-content auto max-content auto;
  margin-top: 20px;
  padding-bottom: 40px;
  width: max-content;
  margin-left: auto;
  margin-right: auto;
}
.uploadButton {
  height: 20px;
  width: 185px;
  background-color: #2d2c2c;
  border: 1px solid white;
  color: white;
  margin-top: 10px;
}
.saveInfoButton {
  background-color: rgba(255, 0, 0, 0.5);
  border: 0.7px solid rgba(0, 0, 0, 0.45);
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  color: white;
  width: 138px;
  height: 26px;
  margin-top: 17px;
  position: relative;
  left: 50px;
}
.editInfoButton {
  background-color: rgba(255, 0, 0, 0.5);
  border: 0.7px solid rgba(0, 0, 0, 0.45);
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  color: white;
  width: 138px;
  height: 26px;
  margin-top: 17px;
}
.imageInput {
  width: 232px;
  padding-left: 12px;
  height: 18px;
  position: relative;
  top: 30px;
}
.descriptionInput {
  width: 170px;
  margin-left: -35px;
}
.previewText {
  color: white;
  width: 85px;
  text-align: center;
  margin-bottom: 4px;
}
.previewImage {
  width: 85px;
  height: 85px;
  margin-right: 8px;
  border-radius: 100px;
}
.HomeLink {
  text-decoration: none;
}
.footerDiv {
  display: grid;
  grid-template-rows: 5vh auto;
  background-color: black;
  position: sticky;
  padding-bottom: 10px;
  width: 100%;
}
.rest {
  height: 50px;
  background-color: red;
}
.DescriptionGrid {
  color: white;
  display: grid;
  grid-template-columns: 10px max-content 45px max-content auto max-content auto;
  padding-top: 18px;
  padding-bottom: 16px;
}

.PasswordGrid {
  color: white;
  display: grid;
  grid-template-columns: 10px max-content 58px max-content auto max-content auto;
  padding-top: 18px;
  border-bottom: solid 1px white;
  padding-bottom: 16px;
}
.cancelInfoButton {
  margin-top: 46px;
  width: 136px;
  position: relative;
  left: -3px;
  margin-left: auto;
  margin-right: auto;
}
.UsernameGrid {
  color: white;
  display: grid;
  grid-template-columns: 10px max-content 58px max-content auto max-content auto;
  border-bottom: solid 1px white;
  padding-bottom: 16px;
}
.BackgroundDiv {
  background-color: black;
  width: calc(100vw + 19px);
  height: 100vh;
}
.BackgroundDiv > * {
  overflow-y: hidden;
}
.UsernameInput {
  padding-left: 5px;
  margin-top: 8px;
  margin-left: -40px;
}
.NewPasswordInput {
  padding-left: 5px;
  margin-left: -39px;
}
.VideosText {
  width: 100px;
  position: relative;
  left: -11px;
  margin-top: 18px;
}
.VideosNumber {
  width: max-content;
  position: relative;
  left: -24px;
  margin-top: 18px;
}
.SectionGrid {
  color: white;
  font-size: 18px;
  line-height: 21px;
  font-family: 'Roboto', sans-serif;
  display: grid;
  grid-template-columns: auto max-content auto max-content auto max-content auto;
  margin-top: 34px;
  border-bottom: solid 1px white;
  padding-bottom: 39px;
}
.backHomeDiv {
  display: grid; /* space, Arrow, space, Home, space, Cat */
  grid-template-columns: 17px 32px 5px 60px 12px 20px auto;
  background-color: black;
  margin-top: 20px;
}
.mySubscribersSection {
  display: grid;
  grid-template-columns: auto auto auto;
  height: max-content;
  padding-bottom: 40px;
}
.VideosGrid {
  color: white;
  display: grid;
  grid-template-columns: 54px max-content 54px max-content auto max-content auto;
}
.FillerBlock {
  width: 110px;
  height: 10px;
}
.SubscribersGrid {
  display: grid;
  grid-template-columns: 42px max-content 42px max-content auto max-content auto;
  color: white;
  font-size: 18px;
  line-height: 21px;
  margin-top: 18px;
}
.descriptionInput {
  padding-left: 5px;
}
.imageDiv {
  width: 140px;
  height: 140px;
  margin-left: auto;
  margin-right: auto;
  max-width: 140px;
  overflow-x: hidden;
  overflow-y: hidden;
  border-radius: 100px;
}

.profileImage {
  width: 190px;
  height: 130px;
  border-radius: 100px;
  position: relative;
  left: -40px;
}
.UsernameText {
  margin-top: 10px;
}
.ChosenProfileInfo,
.ChosenMyVideos,
.ChosenSubscribers {
  color: #e75858;
}
.userNameDiv {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  color: #e75858;
  margin-top: -5px;
  margin-bottom: 10px;
}
.UsernameValue {
  margin-top: 10px;
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
  width: 300px;
}
.InfoGrid {
  min-height: 188px;
  height: max-content;
  background-color: #2d2c2c;
  width: 80vw;
  max-width: 900px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 20px;
}
.catIcon {
  width: 28px;
  height: 31px;
  margin-top: 15px;
  margin-bottom: 12px;
}

* {
  outline: none;
  border: none;
  font-family: 'Roboto', sans-serif;
}
</style>
