<template>
  <div v-if="currentUser && !darkTheme" class="LightBackgroundDiv">
    <router-link class="HomeLink" to="/">
      <div class="LightBackHomeDiv">
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
        <p v-if="!darkTheme" class="lightHomeText">Home</p>
        <div class="SpaceDiv" />
        <img
          v-if="darkTheme"
          class="catIcon"
          src="../projectImages/whiteCatBlackBaground.png"
        />
        <img
          v-if="!darkTheme"
          class="catIcon"
          src="../projectImages/light_Cat.png"
        />
        <div class="SpaceDiv" />
      </div>
    </router-link>
    <div v-if="currentUser" class="userNameDiv">{{ currentUser.username }}</div>
    <div v-if="currentUser" class="imageDiv">
      <img class="profileImage" :src="currentUser.profileURL" />
    </div>
    <div class="themeDiv">
      <div v-if="darkTheme" class="themeText">Current Theme</div>
      <div v-if="!darkTheme" class="lightThemeText">Current Theme</div>
      <div class="themeGrid">
        <div class="SpaceBlock" />
        <div
          @click="chooseWhiteTheme"
          v-if="darkTheme"
          class="whiteTheme theme"
        >
          Light
        </div>
        <div v-if="!darkTheme" class="chosenWhiteTheme theme">Light</div>
        <div class="SpaceBlock" />
        <div
          @click="chooseBlackTheme"
          v-if="!darkTheme"
          class="blackTheme theme"
        >
          Dark
        </div>
        <div v-if="darkTheme" class="chosenBlackTheme theme">Dark</div>
        <div class="SpaceBlock" />
      </div>
    </div>
    <div class="SectionGrid">
      <div class="SpaceBlock" />
      <div class="ProfileTextDiv">
        <div
          @click="switchToMyProfile"
          v-if="!chosenProfileInfo && darkTheme"
          class="notChosenProfileInfo"
        >
          Profile Info
        </div>
        <div
          @click="switchToMyProfile"
          v-if="!chosenProfileInfo && !darkTheme"
          class="lightNotChosenProfileInfo"
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
          v-if="!chosenMyVideos && darkTheme"
          class="notChosenMyVideos"
        >
          My Videos
        </div>
        <div
          @click="switchToMyVideos"
          v-if="!chosenMyVideos && !darkTheme"
          class="lightNotChosenMyVideos"
        >
          My Videos
        </div>
        <div v-if="chosenMyVideos" class="ChosenMyVideos">My Videos</div>
      </div>
      <div class="SpaceBlock" />
      <div class="mySubscribersDiv">
        <div
          @click="switchToMySubscribers"
          v-if="!chosenSubscribers && darkTheme"
          class="notChosenSubscriptions"
        >
          Subscriptions
        </div>
        <div
          @click="switchToMySubscribers"
          v-if="!chosenSubscribers && !darkTheme"
          class="lightNotChosenSubscriptions"
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
      <div v-if="currentUser && darkTheme" class="SubscribersGrid">
        <div class="SpaceBlock" />
        <div class="SubscribersText">Subscribers:</div>
        <div class="SpaceBlock" />
        <div class="SubscribersNumber">{{ currentSubs }}</div>
        <div class="SpaceBlock" />
        <div class="FillerBlock" />
      </div>
      <div v-if="currentUser && !darkTheme" class="LightSubscribersGrid">
        <div class="SpaceBlock" />
        <div class="SubscribersText">Subscribers:</div>
        <div class="SpaceBlock" />
        <div class="SubscribersNumber">{{ currentSubs }}</div>
        <div class="SpaceBlock" />
        <div class="FillerBlock" />
      </div>
      <div v-if="currentUser && darkTheme" class="VideosGrid">
        <div class="SpaceBlock" />
        <div class="VideosText">Videos:</div>
        <div class="SpaceBlock" />
        <div class="VideosNumber">{{ currentUser.videosPosted }}</div>
        <div class="SpaceBlock" />
        <div class="FillerBlock" />
      </div>
      <div v-if="currentUser && !darkTheme" class="LightVideosGrid">
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
            v-if="!wantedImage && darkTheme"
            class="previewImage"
            src="../projectImages/Dark_User.png"
          />
          <img
            v-if="!wantedImage && !darkTheme"
            class="previewImage"
            src="../projectImages/Light_User.png"
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
      <VideoInProfilePage
        v-for="(videoItem, index) of myVideos"
        :key="index"
        :video="videoItem"
        @deletedVideo="showDeleteDialog"
        @editVideo="showEditDialog"
        class="videoBox"
      />
      <div v-if="deleteVideoDialog" class="deleteDialog">
        Are you sure that you want to delete this video?
        <button @click="deleteAndUpdate" class="deleteVideoButton" value="Yes">
          Yes
        </button>
        <button
          @click="cancelDelete"
          class="cancelDeleteVideoButton"
          value="No"
        >
          No
        </button>
      </div>
      <div class="editDialogDiv">
        <div v-if="editDialog" class="editDialog">
          <div class="titleInputGrid">
            <div class="SpaceBlock" />
            <div class="titleLabel">Title:</div>
            <div class="SpaceBlock" />
            <input
              class="newTitleInput"
              v-model="newTitle"
              placeholder="New Title"
              type="text"
            />
            <div class="SpaceBlock" />
            <div @click="closeEditDialog" class="XButton">X</div>
            <div class="SpaceBlock" />
          </div>
          <div class="descriptionInputGrid">
            <div class="SpaceBlock" />
            <div class="DescriptionLabel">Description:</div>
            <div class="SpaceBlock" />
            <textarea
              class="newDescriptionInput"
              rows="7"
              cols="33"
              placeholder="New Text"
              v-model="newDescription"
            />
            <div class="SpaceBlock" />
          </div>
          <button
            @click="editVideo"
            class="saveNewDescriptionButton"
            value="Save"
          >
            Save
          </button>
        </div>
      </div>
    </div>
    <div v-if="chosenSubscribers" class="mySubscribersSection">
      <SubscriptionToUser
        v-for="(subbedTo, index) of peopleSubscribedTo"
        :subbedTo="subbedTo"
        :key="index"
        class="videoBox"
        @unsubscribe="unsubscribe"
      />
    </div>
    <Footer class="footerDiv" />
  </div>
  <div v-if="currentUser && darkTheme" class="DarkBackgroundDiv">
    <router-link class="HomeLink" to="/">
      <div class="DarkBackHomeDiv">
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
        <p v-if="!darkTheme" class="lightHomeText">Home</p>
        <div class="SpaceDiv" />
        <img
          v-if="darkTheme"
          class="catIcon"
          src="../projectImages/whiteCatBlackBaground.png"
        />
        <img
          v-if="!darkTheme"
          class="catIcon"
          src="../projectImages/light_Cat.png"
        />
        <div class="SpaceDiv" />
      </div>
    </router-link>
    <div v-if="currentUser" class="userNameDiv">{{ currentUser.username }}</div>
    <div v-if="currentUser" class="imageDiv">
      <img class="profileImage" :src="currentUser.profileURL" />
    </div>
    <div class="themeDiv">
      <div v-if="darkTheme" class="themeText">Current Theme</div>
      <div v-if="!darkTheme" class="lightThemeText">Current Theme</div>
      <div class="themeGrid">
        <div class="SpaceBlock" />
        <div
          @click="chooseWhiteTheme"
          v-if="darkTheme"
          class="whiteTheme theme"
        >
          Light
        </div>
        <div v-if="!darkTheme" class="chosenWhiteTheme theme">Light</div>
        <div class="SpaceBlock" />
        <div
          @click="chooseBlackTheme"
          v-if="!darkTheme"
          class="blackTheme theme"
        >
          Dark
        </div>
        <div v-if="darkTheme" class="chosenBlackTheme theme">Dark</div>
        <div class="SpaceBlock" />
      </div>
    </div>
    <div class="SectionGrid">
      <div class="SpaceBlock" />
      <div class="ProfileTextDiv">
        <div
          @click="switchToMyProfile"
          v-if="!chosenProfileInfo && darkTheme"
          class="notChosenProfileInfo"
        >
          Profile Info
        </div>
        <div
          @click="switchToMyProfile"
          v-if="!chosenProfileInfo && !darkTheme"
          class="lightNotChosenProfileInfo"
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
          v-if="!chosenMyVideos && darkTheme"
          class="notChosenMyVideos"
        >
          My Videos
        </div>
        <div
          @click="switchToMyVideos"
          v-if="!chosenMyVideos && !darkTheme"
          class="lightNotChosenMyVideos"
        >
          My Videos
        </div>
        <div v-if="chosenMyVideos" class="ChosenMyVideos">My Videos</div>
      </div>
      <div class="SpaceBlock" />
      <div class="mySubscribersDiv">
        <div
          @click="switchToMySubscribers"
          v-if="!chosenSubscribers && darkTheme"
          class="notChosenSubscriptions"
        >
          Subscriptions
        </div>
        <div
          @click="switchToMySubscribers"
          v-if="!chosenSubscribers && !darkTheme"
          class="lightNotChosenSubscriptions"
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
      <div v-if="currentUser && darkTheme" class="SubscribersGrid">
        <div class="SpaceBlock" />
        <div class="SubscribersText">Subscribers:</div>
        <div class="SpaceBlock" />
        <div class="SubscribersNumber">{{ currentSubs }}</div>
        <div class="SpaceBlock" />
        <div class="FillerBlock" />
      </div>
      <div v-if="currentUser && !darkTheme" class="LightSubscribersGrid">
        <div class="SpaceBlock" />
        <div class="SubscribersText">Subscribers:</div>
        <div class="SpaceBlock" />
        <div class="SubscribersNumber">{{ currentSubs }}</div>
        <div class="SpaceBlock" />
        <div class="FillerBlock" />
      </div>
      <div v-if="currentUser && darkTheme" class="VideosGrid">
        <div class="SpaceBlock" />
        <div class="VideosText">Videos:</div>
        <div class="SpaceBlock" />
        <div class="VideosNumber">{{ currentUser.videosPosted }}</div>
        <div class="SpaceBlock" />
        <div class="FillerBlock" />
      </div>
      <div v-if="currentUser && !darkTheme" class="LightVideosGrid">
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
            v-if="!wantedImage && darkTheme"
            class="previewImage"
            src="../projectImages/Dark_User.png"
          />
          <img
            v-if="!wantedImage && !darkTheme"
            class="previewImage"
            src="../projectImages/Light_User.png"
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
      <VideoInProfilePage
        v-for="(videoItem, index) of myVideos"
        :key="index"
        :video="videoItem"
        @deletedVideo="showDeleteDialog"
        @editVideo="showEditDialog"
        class="videoBox"
      />
      <div v-if="deleteVideoDialog" class="deleteDialog">
        Are you sure that you want to delete this video?
        <button @click="deleteAndUpdate" class="deleteVideoButton" value="Yes">
          Yes
        </button>
        <button
          @click="cancelDelete"
          class="cancelDeleteVideoButton"
          value="No"
        >
          No
        </button>
      </div>
      <div class="editDialogDiv">
        <div v-if="editDialog" class="editDialog">
          <div class="titleInputGrid">
            <div class="SpaceBlock" />
            <div class="titleLabel">Title:</div>
            <div class="SpaceBlock" />
            <input
              class="newTitleInput"
              v-model="newTitle"
              placeholder="New Title"
              type="text"
            />
            <div class="SpaceBlock" />
            <div @click="closeEditDialog" class="XButton">X</div>
            <div class="SpaceBlock" />
          </div>
          <div class="descriptionInputGrid">
            <div class="SpaceBlock" />
            <div class="DescriptionLabel">Description:</div>
            <div class="SpaceBlock" />
            <textarea
              class="newDescriptionInput"
              rows="7"
              cols="33"
              placeholder="New Text"
              v-model="newDescription"
            />
            <div class="SpaceBlock" />
          </div>
          <button
            @click="editVideo"
            class="saveNewDescriptionButton"
            value="Save"
          >
            Save
          </button>
        </div>
      </div>
    </div>
    <div v-if="chosenSubscribers" class="mySubscribersSection">
      <SubscriptionToUser
        v-for="(subbedTo, index) of peopleSubscribedTo"
        :subbedTo="subbedTo"
        :key="index"
        class="videoBox"
        @unsubscribe="unsubscribe"
      />
    </div>
    <Footer class="footerDiv" />
  </div>
</template>
<script>
import User from '../jsClasses/general/User';
import Footer from '../components/Footer.vue';
import VideoInProfilePage from '../components/VideoInProfilePage.vue';
import Video from '../jsClasses/general/Video';
import SubscriptionToUser from '../components/SubscriptionToUser.vue';

export default {
  emits: ['unsubscribe'],
  name: 'ProfilePage',
  components: {
    Footer,
    SubscriptionToUser,
    VideoInProfilePage,
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
      peopleSubscribedTo: [],
      deleteVideoDialog: false,
      idToDelete: 0,
      idToUpdate: 0,
      editDialog: false,
      newTitle: '',
      newDescription: '',
      newURL: '',
      oldTitle: '',
      oldDescription: '',
      darkTheme: false,
    };
  },
  computed: {
    cssVars() {
      return {
        '--bg-color': 'white',
      };
    },
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

    this.darkTheme = this.$store.getters.getIsDarkTheme;

    let subsRes = await fetch(
      '/rest/getSubscribersForId?' +
        new URLSearchParams({
          userId: this.currentUser.userId,
        })
    );
    let subscribersResponse = await subsRes.json();
    for (let i = 0; i < subscribersResponse.length; i++) {
      let subbedUser = new User(0, '', '', '', 0, 0);
      subbedUser = Object.assign(subbedUser, subscribersResponse[i]);
      this.peopleSubscribedTo.push(subbedUser);
    }
  },
  watch: {
    wantedImage() {
      if (this.wantedImage) {
        this.editMode = true;
      }
    },
  },
  methods: {
    closeEditDialog() {
      this.editDialog = false;
      this.idToUpdate = 0;
    },
    showEditDialog(videoToEdit) {
      this.editDialog = true;
      this.idToUpdate = videoToEdit.videoId;
      this.newURL = videoToEdit.videoURL;
      this.oldTitle = videoToEdit.title;
      this.oldDescription = videoToEdit.description;
    },
    cancelDelete() {
      this.deleteVideoDialog = false;
      this.idToDelete = 0;
    },
    /* Deletes a Video and all related likes, dislikes, replies/comments */
    /* Updates a Videos URL, title, description */
    showDeleteDialog(idToDelete) {
      this.deleteVideoDialog = true;
      this.idToDelete = idToDelete;
    },
    async deleteAndUpdate() {
      let deleteVideoRes = await fetch(
        '/api/deleteVideo?' +
          new URLSearchParams({
            videoId: this.idToDelete,
          }),
        {
          method: 'DELETE',
        }
      );
      this.switchToMyVideos();
      this.deleteVideoDialog = false;
      this.idToDelete = 0;
    },
    async editVideo() {
      let video = {
        videoId: this.idToUpdate,
        videoURL: this.newURL,
        title: this.newTitle == '' ? this.oldTitle : this.newTitle,
        description:
          this.newDescription == '' ? this.oldDescription : this.newDescription,
      };
      let res = await fetch('/api/updateVideo', {
        method: 'POST',
        body: JSON.stringify(video),
      });
      this.newTitle = '';
      this.newDescription = '';
      this.newURL = '';
      this.idToUpdate = 0;
      this.editDialog = false;
      this.switchToMyVideos();
    },
    async unsubscribe(e) {
      let newList = [];
      let userToRemove;
      for (let i = 0; i < this.peopleSubscribedTo.length; i++) {
        if (this.peopleSubscribedTo[i].userId != e.userId) {
          newList.push(this.peopleSubscribedTo[i]);
        } else {
          userToRemove = this.peopleSubscribedTo[i];
        }
      }
      this.peopleSubscribedTo = newList;
      let unsubscribeRes = await fetch(
        '/api/unsubscribe?' +
          new URLSearchParams({
            targetId: userToRemove.userId,
            userId: this.currentUser.userId,
          }),
        {
          method: 'DELETE',
        }
      );
    },
    chooseBlackTheme() {
      this.darkTheme = true;
      this.$store.dispatch('setDarkTheme', true);
    },
    chooseWhiteTheme() {
      this.darkTheme = false;
      this.$store.dispatch('setDarkTheme', false);
    },
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

.XButton {
  color: white;
  font-size: 25px;
  margin-top: 5px;
}
.saveNewDescriptionButton {
  display: block;
  width: 138px;
  height: 26px;
  color: white;
  background-color: rgba(255, 0, 0, 0.5);
  margin-left: auto;
  margin-right: auto;
  margin-top: 10px;
}
.lightNotChosenSubscriptions {
  color: black;
}
.newTitleInput {
  background-color: #2d2c2c;
  color: white;
  margin-left: 78px;
  margin-top: 20px;
  height: 18px;
  font-size: 18px;
  width: 170px;
}
.themeText {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
}
.DescriptionLabel {
  color: white;
  font-size: 18px;
}
.newDescriptionInput {
  background-color: #2d2c2c;
  color: white;
  font-size: 18px;
  width: 170px;
  padding-left: 5px;
}
.editDialog {
  width: 344px;
  height: 269px;
  background-color: #2d2c2c;
  z-index: 10;
  display: block;
  position: absolute;
  top: 290px;
}
.descriptionInputGrid {
  display: grid;
  grid-template-columns: 24px max-content 17px max-content auto;
  margin-top: 14px;
}
.titleLabel {
  color: white;
  margin-top: 18px;
  font-size: 18px;
}
.themeDiv {
  color: white;
}
* {
  overflow-x: hidden;
  max-width: 100vw;
}
.EditGrid {
  display: grid;
}
.titleInputGrid {
  display: grid;
  grid-template-columns: 23px max-content 2px auto 5px max-content 8px;
  padding-bottom: 14px;
  border-bottom: 1px solid white;
}
.deleteDialog {
  display: block;
  width: 280px;
  padding-left: 38px;
  padding-right: 38px;
  padding-top: 30px;
  height: 125px;
  background-color: #2d2c2c;
  margin-left: auto;
  margin-right: auto;
  color: white;
  margin-top: -125px;
  position: relative;
  z-index: 10;
}
.TrashCanIcon {
  height: 30px;
  width: 30px;
}
.editDialogDiv {
  width: 344px;
  display: block;
  margin-left: auto;
  margin-right: auto;
}
.lightNotChosenMyVideos,
.lightNotChosenProfileInfo {
  color: black;
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
.trashGrid {
  display: grid;
  grid-template-rows: 100px;
  justify-content: right;
  width: 400px;
  background-color: transparent;
  z-index: 5;
  position: relative;
  top: -135px;
}
.cancelDeleteVideoButton {
  width: 138px;
  height: 26px;
  color: white;
  background-color: #2d2c2c;
  border: solid 1px white;
  position: relative;
  left: 160px;
  top: 19px;
}

.deleteVideoButton {
  width: 138px;
  height: 26px;
  color: white;
  background-color: rgba(255, 0, 0, 0.5);
  position: relative;
  left: -80px;
  top: 45px;
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
  border: solid 1px black;
}
.themeGrid {
  display: grid;
  grid-template-columns: auto max-content 10px max-content auto;
  padding-bottom: 5px;
}
.theme {
  height: 58px;
  width: 100px;
  border-radius: 100px;
  background-color: white;
  margin-top: 5px;
  margin-bottom: 2px;
}
.whiteTheme {
  color: black;
  text-align: center;
  padding-top: 40px;
  outline: solid 3px #2d2c2c;
}
.chosenWhiteTheme {
  color: black;
  text-align: center;
  padding-top: 40px;
  outline: solid 3px green;
}
.blackTheme {
  background-color: black;
  padding-top: 40px;
  text-align: center;
  color: white;
  outline: solid 3px #2d2c2c;
}
.chosenBlackTheme {
  background-color: black;
  padding-top: 40px;
  text-align: center;
  color: white;
  outline: solid 3px green;
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

.LightBackgroundDiv {
  background-color: white;
  width: calc(100vw + 19px);
  height: 100vh;
}
.DarkBackgroundDiv {
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
.SectionGrid,
.lightSectionGrid {
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
.LightBackHomeDiv {
  display: grid; /* space, Arrow, space, Home, space, Cat */
  grid-template-columns: 17px 32px 5px 60px 12px 20px auto;
  background-color: white;
  margin-top: 20px;
}
.DarkBackHomeDiv {
  display: grid; /* space, Arrow, space, Home, space, Cat */
  grid-template-columns: 17px 32px 5px 60px 12px 20px auto;
  background-color: black;
  margin-top: 20px;
}
.lightBackHomeDiv {
  display: grid; /* space, Arrow, space, Home, space, Cat */
  grid-template-columns: 17px 32px 5px 60px 12px 20px auto;
  background-color: white;
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
.LightVideosGrid {
  color: black;
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
.LightSubscribersGrid {
  display: grid;
  grid-template-columns: 42px max-content 42px max-content auto max-content auto;
  color: black;
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

.LightArrowIcon {
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
.lightHomeText {
  font-size: 24px;
  font-weight: 500;
  font-family: 'Roboto', sans-serif;
  line-height: 28.13px;
  margin-top: 18px;
  color: black;
  margin-bottom: 15px;
  width: 300px;
}

.lightSectionGrid {
  border-bottom: solid 1px black;
}
.lightThemeText {
  width: max-content;
  margin-right: auto;
  margin-left: auto;
}
.lightHomeText,
.lightThemeText,
.lightSectionGrid,
.lightSubscribersGrid,
.lightVideosGrid {
  color: black;
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
