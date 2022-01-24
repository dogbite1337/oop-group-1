<template>
  <div class="backgroundDiv" @click="clickedMe">
    <router-link class="HomeLink" to="/">
      <div class="backHomeDiv">
        <div class="SpaceDiv" />
        <img
          v-if="darkTheme"
          class="ArrowIcon"
          src="../projectImages/big_arrow.png"
        />
        <img
          v-if="!darkTheme"
          class="LightArrowIcon"
          src="../projectImages/right-arrow.png"
        />
        <div class="SpaceDiv" />
      </div>
    </router-link>
    <div class="SpaceBlock" />
    <div class="FrameGrid">
      <div class="iFrameDiv">
        <iframe
          id="existing-iframe-example"
          class="PlayerDiv"
          type="text/html"
          width="100%"
          :height="height"
          :src="video.videoURL"
          frameborder="0"
        ></iframe>
      </div>
    </div>
    <div class="someOther" />
    <div class="UpperSection">
      <div
        v-if="!showWatchNowInstead && darkTheme"
        class="DarkDescriptionAndCommentsDiv"
      >
        <div class="SpaceDiv" />
        <div class="middleDiv">
          <div v-if="!showDescriptionSection" class="notChosenDescriptionDiv">
            Description
          </div>
          <div v-if="showDescriptionSection" class="ChosenDescriptionDiv">
            Description
            <div class="LineDiv" />
          </div>
          <div class="SpaceDiv" />
          <div v-if="showDescriptionSection" class="notChosenCommentsDiv">
            Comments({{ amountOfComments }})
          </div>
          <div v-if="!showDescriptionSection" class="ChosenCommentsDiv">
            Comments({{ amountOfComments }})
            <div class="LineDiv" />
          </div>
        </div>
        <div class="SpaceDiv" />
      </div>
      <div
        v-if="!showWatchNowInstead && !darkTheme"
        class="LightDescriptionAndCommentsDiv"
      >
        <div class="SpaceDiv" />
        <div class="middleDiv">
          <div v-if="!showDescriptionSection" class="notChosenDescriptionDiv">
            Description
          </div>
          <div v-if="showDescriptionSection" class="ChosenDescriptionDiv">
            Description
            <div class="LineDiv" />
          </div>
          <div class="SpaceDiv" />
          <div v-if="showDescriptionSection" class="notChosenCommentsDiv">
            Comments({{ amountOfComments }})
          </div>
          <div v-if="!showDescriptionSection" class="ChosenCommentsDiv">
            Comments({{ amountOfComments }})
            <div class="LineDiv" />
          </div>
        </div>
        <div class="SpaceDiv" />
      </div>
      <div v-if="!showWatchNowInstead && darkTheme" class="DarkUploaderDiv">
        <div class="SpaceDiv" />
        <div v-if="darkTheme" class="square1 DarkSquare">
          <img class="uploaderProfileDiv" :src="Uploader.profileURL" />
        </div>
        <div class="SpaceDiv" />
        <div v-if="darkTheme" class="square2 DarkSquare">
          <div class="darkUsernameDiv">
            {{ Uploader.username }}
          </div>
          <div class="subsDiv">Subscribers: {{ spacedSubs }}</div>
        </div>
        <div class="SpaceDiv" />
        <div v-if="darkTheme" class="square3 DarkSquare">
          <div class="videosDiv">Videos: {{ spacedVideos }}</div>
        </div>
        <div class="SpaceDiv" />
        <div v-if="activeUserId != 0 && darkTheme" class="square4 DarkSquare">
          <div v-if="activeUserId != video.userId" class="subButtonDiv">
            <button
              v-if="!subscribedAlready"
              @click="subscribe"
              class="subButton"
              value="Subscribe"
            >
              + Subscribe
            </button>
            <button
              v-if="subscribedAlready"
              @click="unsubscribe"
              class="subButton"
              value="Unsubscribe"
            >
              - Unsubscribe
            </button>
          </div>
        </div>
        <div class="SpaceDiv" />
      </div>
      <div v-if="!showWatchNowInstead && !darkTheme" class="LightUploaderDiv">
        <div class="SpaceDiv" />
        <div v-if="!darkTheme" class="square1 LightSquare">
          <img class="LightUploaderProfileDiv" :src="Uploader.profileURL" />
        </div>
        <div class="SpaceDiv" />
        <div v-if="!darkTheme" class="square2 LightSquare">
          <div class="lightUsernameDiv">
            {{ Uploader.username }}
          </div>
          <div class="subsDiv">Subscribers: {{ spacedSubs }}</div>
        </div>
        <div class="SpaceDiv" />
        <div v-if="darkTheme" class="square3 DarkSquare">
          <div class="videosDiv">Videos: {{ spacedVideos }}</div>
        </div>
        <div v-if="!darkTheme" class="square3 LightSquare">
          <div class="videosDiv">Videos: {{ spacedVideos }}</div>
        </div>
        <div class="SpaceDiv" />
        <div v-if="activeUserId != 0 && darkTheme" class="square4 DarkSquare">
          <div v-if="activeUserId != video.userId" class="subButtonDiv">
            <button
              v-if="!subscribedAlready"
              @click="subscribe"
              class="subButton"
              value="Subscribe"
            >
              + Subscribe
            </button>
            <button
              v-if="subscribedAlready"
              @click="unsubscribe"
              class="subButton"
              value="Unsubscribe"
            >
              - Unsubscribe
            </button>
          </div>
        </div>
        <div v-if="activeUserId != 0 && !darkTheme" class="square4 LightSquare">
          <div v-if="activeUserId != video.userId" class="subButtonDiv">
            <button
              v-if="!subscribedAlready"
              @click="subscribe"
              class="subButton"
              value="Subscribe"
            >
              + Subscribe
            </button>
            <button
              v-if="subscribedAlready"
              @click="unsubscribe"
              class="subButton"
              value="Unsubscribe"
            >
              - Unsubscribe
            </button>
          </div>
        </div>
        <div class="SpaceDiv" />
      </div>

      <div v-if="!showWatchNowInstead && darkTheme" class="DarkVideoTitleDiv">
        <div class="titleDiv">{{ video.title }}</div>
        <div class="descriptionDiv">{{ video.description }}</div>
      </div>
      <div v-if="!showWatchNowInstead && !darkTheme" class="LightVideoTitleDiv">
        <div class="titleDiv">{{ video.title }}</div>
        <div class="descriptionDiv">{{ video.description }}</div>
      </div>
      <div v-if="!showWatchNowInstead && darkTheme" class="DarkViewsAndDateDiv">
        <div class="SpaceDiv" />
        <div class="DarkSquare playButtonDiv">
          <img class="playButton" src="../projectImages/small_grey_trans.png" />
        </div>
        <div class="SpaceDiv" />
        <div class="DarkViewsDiv DarkSquare">{{ spacedViews }} views</div>
        <div class="SpaceDiv" />
        <div class="DarkUploadDateDiv DarkSquare">
          {{
            new Date(video.uploadDate).toLocaleDateString().replaceAll('/', '-')
          }}
        </div>
        <div class="SpaceDiv" />
      </div>
      <div
        v-if="!showWatchNowInstead && !darkTheme"
        class="LightViewsAndDateDiv"
      >
        <div class="SpaceDiv" />
        <div class="LightSquare LightPlayButtonDiv">
          <img class="LightPlayButton" src="../projectImages/Light_Play.png" />
        </div>
        <div class="SpaceDiv" />
        <div class="LightViewsDiv LightSquare">{{ spacedViews }} views</div>
        <div class="SpaceDiv" />
        <div class="LightUploadDateDiv LightSquare">
          {{
            new Date(video.uploadDate).toLocaleDateString().replaceAll('/', '-')
          }}
        </div>
        <div class="SpaceDiv" />
      </div>
      <div
        v-if="!showWatchNowInstead && !darkTheme"
        class="LightLikesAndDislikesNumberDiv"
      >
        <div class="SpaceDiv" />
        <div class="LightLikesNumberDiv">
          <img
            v-if="!likedVideoAlready"
            @click="likeVideo"
            src="../projectImages/like_white_background.png"
          />
          <img
            v-if="likedVideoAlready"
            src="../projectImages/blue_white_like.png"
          />
          <div class="likesDiv">{{ spacedLikes }}</div>
        </div>
        <div class="SpaceDiv" />
        <div class="LightDislikesNumberDiv">
          <img
            v-if="!dislikedVideoAlready"
            @click="dislikeVideo"
            src="../projectImages/dislike_white_background.png"
          />
          <img
            v-if="dislikedVideoAlready"
            src="../projectImages/blue_white_dislike.png"
          />
          <div class="thumbsDownDiv">{{ spacedDislikes }}</div>
        </div>
        <div class="SpaceDiv" />
        <div class="LightStarNumberDiv">
          <img src="../projectImages/grey_star_trans.png" />
          <div class="starsDiv">{{ spacedStars }}</div>
        </div>
        <div class="SpaceDiv" />
      </div>
      <div
        v-if="!showWatchNowInstead && darkTheme"
        class="DarkLikesAndDislikesNumberDiv"
      >
        <div class="SpaceDiv" />
        <div class="DarkLikesNumberDiv">
          <img
            v-if="!likedVideoAlready"
            @click="likeVideo"
            src="../projectImages/like_black_background.png"
          />
          <img v-if="likedVideoAlready" src="../projectImages/blue_like.png" />
          <div class="likesDiv">{{ spacedLikes }}</div>
        </div>
        <div class="SpaceDiv" />
        <div class="DarkDislikesNumberDiv">
          <img
            v-if="!dislikedVideoAlready"
            @click="dislikeVideo"
            src="../projectImages/dislike_black_background.png"
          />
          <img
            v-if="dislikedVideoAlready"
            src="../projectImages/blue_dislike.png"
          />
          <div class="thumbsDownDiv">{{ spacedDislikes }}</div>
        </div>
        <div class="SpaceDiv" />
        <div class="DarkStarNumberDiv">
          <img src="../projectImages/grey_star_trans.png" />
          <div class="starsDiv">{{ spacedStars }}</div>
        </div>
        <div class="SpaceDiv" />
      </div>
    </div>
  </div>
  <div v-if="!darkTheme" class="LightVideosBody">
    <div v-if="showWatchNowInstead" class="watchNowScrollGrid">
      <div class="watchNowTextDiv">
        <div class="SpaceBlock" />
        <img class="ArrowIcon" src="../projectImages/big_arrow.png" />
        <div class="SpaceBlock" />
        <img class="watchNowPlay" src="../projectImages/whitePlay.png" />
        <div class="SpaceBlock" />
        <div class="watchNowText">Watch Now</div>
        <div class="SpaceBlock" />
      </div>
      <div v-if="showWatchNowInstead" class="descriptionAndCommentsDivInScroll">
        <div class="SpaceDiv" />
        <div class="middleDiv">
          <div v-if="!showDescriptionTab" class="notChosenDescriptionDiv">
            Description
          </div>
          <div v-if="showDescriptionTab" class="ChosenDescriptionDiv">
            Description
            <div class="LineDiv" />
          </div>
          <div class="SpaceDiv" />
          <div class="commentsDiv">Comments({{ amountOfComments }})</div>
        </div>
        <div class="SpaceDiv" />
      </div>
    </div>
    <div v-if="showCommentsSection">
      <CommentInput
        class="postingCommentDiv"
        @postedComment="updateComments"
        :videoId="video.videoId"
      />
    </div>
    <div
      class="LightPostedCommentsDiv"
      v-if="showCommentsSection && !darkTheme"
    >
      <PostedComment
        v-for="(commentItem, index) of relevantComments"
        :key="index"
        :comment="commentItem"
        :replies="currentReplies"
        :commenters="currentCommenters"
        :activeId="activeId"
        class="commentBox"
        @removedAReply="updateBasedOnDelete"
        @postedAReply="updateCommentSection"
        @updateReplies="updateReplies"
      />
    </div>
    <div class="DarkPostedCommentsDiv" v-if="showCommentsSection && darkTheme">
      <PostedComment
        v-for="(commentItem, index) of relevantComments"
        :key="index"
        :comment="commentItem"
        :replies="currentReplies"
        :commenters="currentCommenters"
        :activeId="activeId"
        class="commentBox"
        @removedAReply="updateBasedOnDelete"
        @postedAReply="updateCommentSection"
        @updateReplies="updateReplies"
      />
    </div>
    <!-- <div class="relatedVideosContainer" v-if="startRenderingtrue"> -->
    <RelatedVideo
      v-for="(videoItem, index) of relatedVideos"
      :key="index"
      :video="videoItem"
      class="videoBox"
    />
    <!-- </div> -->
  </div>
  <div v-if="darkTheme" class="DarkVideosBody">
    <div v-if="showWatchNowInstead" class="watchNowScrollGrid">
      <div class="watchNowTextDiv">
        <div class="SpaceBlock" />
        <img class="ArrowIcon" src="../projectImages/big_arrow.png" />
        <div class="SpaceBlock" />
        <img class="watchNowPlay" src="../projectImages/whitePlay.png" />
        <div class="SpaceBlock" />
        <div class="watchNowText">Watch Now</div>
        <div class="SpaceBlock" />
      </div>
      <div v-if="showWatchNowInstead" class="descriptionAndCommentsDivInScroll">
        <div class="SpaceDiv" />
        <div class="middleDiv">
          <div v-if="!showDescriptionTab" class="notChosenDescriptionDiv">
            Description
          </div>
          <div v-if="showDescriptionTab" class="ChosenDescriptionDiv">
            Description
            <div class="LineDiv" />
          </div>
          <div class="SpaceDiv" />
          <div class="commentsDiv">Comments({{ amountOfComments }})</div>
        </div>
        <div class="SpaceDiv" />
      </div>
    </div>
    <div v-if="showCommentsSection">
      <CommentInput
        class="postingCommentDiv"
        @postedComment="updateComments"
        :videoId="video.videoId"
      />
    </div>
    <div
      class="LightPostedCommentsDiv"
      v-if="showCommentsSection && !darkTheme"
    >
      <PostedComment
        v-for="(commentItem, index) of relevantComments"
        :key="index"
        :comment="commentItem"
        :replies="currentReplies"
        :commenters="currentCommenters"
        :activeId="activeId"
        class="commentBox"
        @removedAReply="updateBasedOnDelete"
        @postedAReply="updateCommentSection"
        @updateReplies="updateReplies"
      />
    </div>
    <div class="DarkPostedCommentsDiv" v-if="showCommentsSection && darkTheme">
      <PostedComment
        v-for="(commentItem, index) of relevantComments"
        :key="index"
        :comment="commentItem"
        :replies="currentReplies"
        :commenters="currentCommenters"
        :activeId="activeId"
        class="commentBox"
        @removedAReply="updateBasedOnDelete"
        @postedAReply="updateCommentSection"
        @updateReplies="updateReplies"
      />
    </div>
    <!-- <div class="relatedVideosContainer" v-if="startRenderingtrue"> -->
    <RelatedVideo
      v-for="(videoItem, index) of relatedVideos"
      :key="index"
      :video="videoItem"
      class="videoBox"
    />
    <!-- </div> -->
  </div>
</template>
<script>
import User from '../jsClasses/general/User';
import Video from '../jsClasses/general/Video';
import Comment from '../jsClasses/general/Comment';
import Footer from '../components/Footer.vue';
import RelatedVideo from '../components/RelatedVideo.vue';
import CommentInput from '../components/CommentInput.vue';
import PostedComment from '../components/PostedComment.vue';
import store from '../store';

// 770/430 width - 1:1
export default {
  name: 'VideoPage',
  emits: ['updateReplies', 'postedAReply', 'postedComment'],
  components: {
    RelatedVideo,
    CommentInput,
    PostedComment,
    Footer,
  },
  data() {
    return {
      amountOfComments: 0,
      relatedVideos: [],
      activeUserId: 0,
      loggedInUser: '',
      isLoggedIn: false,
      video: '',
      activeId: 0,
      spacedViews: 0,
      spacedLikes: 0,
      spacedDislikes: 0,
      spacedStars: 0,
      spacedSubs: 0,
      spacedVideos: 0,
      Uploader: '',
      isOnVideosPage: false,
      showWatchNowInstead: false,
      height: window.screen.height / 2,
      showCommentsSection: false,
      showDescriptionSection: true,
      relevantComments: [],
      timestampOfComments: [],
      currentReplies: [],
      currentCommenters: [],
      likedVideoAlready: false,
      dislikedVideoAlready: false,
      subscribedAlready: false,
      darkTheme: localStorage.isDarkTheme == 'true' ? true : false,
    };
  },

  async created() {
    this.$store.subscribe(async (mutation, state) => {
      if (mutation.type == 'setRelatedVideoId') {
        this.loadRelevantInformation(mutation.payload);
      }
    });
  },

  async mounted() {
    this.loadRelevantInformation();
    if (localStorage.loggedInUser) {
      this.loggedInUser = JSON.parse(localStorage.loggedInUser);
      this.activeUserId = this.loggedInUser.userId;
      this.isLoggedIn = true;
    }

    this.actOnResize();
    this.isOnVideosPage = true;
    window.scrollTo(0, 0);
    if (!this.darkTheme) {
      document.getElementsByClassName('backgroundDiv')[0].style =
        'background-color: white;';
    }

    this.relatedVideos = [];
    // this.relatedVideos = await this.$store.dispatch('fetchEightMoreVideos', 0);
    this.relatedVideos = await JSON.parse(localStorage.relatedVideos);

    let fixedList = [];
    if (this.relatedVideos) {
      for (let i = 0; i < this.relatedVideos.length; i++) {
        if (this.relatedVideos[i].videoId != this.$route.params.id) {
          fixedList.push(this.relatedVideos[i]);
        }
      }
      this.relatedVideos = fixedList;
    }

    let likesRes = await fetch(
      '/rest/getLikesForVideo?' +
        new URLSearchParams({
          videoId: this.$route.params.id,
        })
    );
    let likesResponse = await likesRes.json();

    if (localStorage.loggedInUser) {
      let relevantId = JSON.parse(localStorage.loggedInUser).userId;

      for (let i = 0; i < likesResponse.length; i++) {
        if (likesResponse[i].likedByUserId == relevantId) {
          this.likedVideoAlready = true;
        }
      }
    }

    let dislikesRes = await fetch(
      '/rest/getDislikesForVideo?' +
        new URLSearchParams({
          videoId: this.$route.params.id,
        })
    );
    let dislikesResponse = await dislikesRes.json();
    if (localStorage.loggedInUser) {
      let relevantId = JSON.parse(localStorage.loggedInUser).userId;
      for (let i = 0; i < dislikesResponse.length; i++) {
        if (dislikesResponse[i].dislikedByUserId == relevantId) {
          this.dislikedVideoAlready = true;
        }
      }
    }

    let commentsRes = await fetch(
      '/rest/getCommentsForVideoId?' +
        new URLSearchParams({
          videoId: this.$route.params.id,
        })
    );

    if (localStorage.loggedInUser) {
      let subsRes = await fetch(
        '/rest/getSubscribersForId?' +
          new URLSearchParams({
            userId: JSON.parse(localStorage.loggedInUser).userId,
          })
      );
      let subsResponse = await subsRes.json();

      for (let i = 0; i < subsResponse.length; i++) {
        if (subsResponse[i].userId == this.video.userId) {
          this.subscribedAlready = true;
        }
      }
    }

    // full width on mobile view on cards
    let commentsResponse = await commentsRes.json();
    this.amountOfComments = commentsResponse.length;
    for (let i = 0; i < commentsResponse.length; i++) {
      let newComment = new Comment(0, 0, '', '', 0, 0, 0, 0);
      newComment = Object.assign(newComment, commentsResponse[i]);
      newComment.timeOfPosting = this.convertDateObjectToString(
        new Date(commentsResponse[i].timeOfPosting)
      );
      this.relevantComments.push(newComment);
      let minsAgo =
        (Date.now() - new Date(commentsResponse[i].timeOfPosting)) / 60000;
      if (minsAgo < 1.0) {
        this.timestampOfComments.push('Less than a minute ago');
      } else if (minsAgo >= 1.0 && minsAgo <= 59) {
        this.timestampOfComments.push(
          'About ' +
            Math.floor(minsAgo) +
            ' minute' +
            (minsAgo >= 2.0 ? 's ' : ' ') +
            'ago'
        );
      } else if (minsAgo >= 60 && minsAgo <= 1440) {
        this.timestampOfComments.push(
          'About ' +
            Math.floor(minsAgo / 60) +
            ' hour' +
            (minsAgo / 60 >= 2.0 ? 's ' : ' ') +
            'ago'
        );
      } else if (minsAgo >= 1441 && minsAgo <= 10080) {
        this.timestampOfComments.push(
          'About ' +
            Math.floor(minsAgo / 60 / 24) +
            ' day' +
            (Math.floor(minsAgo / 60 / 24) >= 2.0 ? 's ' : ' ') +
            'ago'
        );
      } else if (minsAgo >= 10081 && minsAgo <= 40324) {
        this.timestampOfComments.push(
          Math.floor(minsAgo / 60 / 24 / 7) +
            ' week' +
            (Math.floor(minsAgo / 60 / 24 / 7) >= 2.0 ? 's ' : ' ') +
            'ago'
        );
      } else if (minsAgo >= 40325 && minsAgo <= 80648) {
        this.timestampOfComments.push(
          'About ' +
            Math.floor(minsAgo / 60 / 24 / 7 / 30) +
            ' month' +
            (Math.floor(minsAgo / 60 / 24 / 7 / 30) >= 2.0 ? 's ' : ' ') +
            'ago'
        );
      } else {
        if (Math.floor(minsAgo / 60 / 24 / 7 / 30) > 12) {
          this.timestampOfComments.push(
            'About ' +
              Math.floor(minsAgo / 60 / 24 / 7 / 30 / 12) +
              ' years ago'
          );
        } else {
          this.timestampOfComments.push(
            'About ' + Math.floor(minsAgo / 60 / 24 / 7 / 30) + ' months ago'
          );
        }
      }
    }

    document.addEventListener('scroll', () => {
      if (window.scrollY >= 368) {
        this.showWatchNowInstead = false;
      } else {
        this.showWatchNowInstead = false;
      }
    });
    window.addEventListener('resize', this.actOnResize);

    // See comment on method
    this.incrementViewCount(this.$route.path);

    this.$nextTick(function () {
      // Code that will run only after the
      // entire view has been re-rendered
      this.lastVideoObserverSearchResult = new IntersectionObserver(
        (entries) => {
          let lastVideo = entries[0];
          if (!lastVideo.isIntersecting) {
            return;
          }
          this.loadMoreVideos();
          this.lastVideoObserverSearchResult.unobserve(lastVideo.target);
          if (!this.stopObserver)
            this.lastVideoObserverSearchResult.observe(
              document.querySelector('.videoBox:last-child')
            );
        },
        { rootMargin: '25px' }
      );

      this.lastVideoObserverSearchResult.observe(
        document.querySelector('.videoBox:last-child')
      );
    });
  },

  beforeUnmount() {
    this.stopObserver = true;
    this.$store.dispatch('cacheFirstEightVideos', []);
  },

  watch: {},
  methods: {
    async loadMoreVideos() {
      let newlyLoadedVideos;
      let numberOfCurrentShownVideos = this.relatedVideos.length;
      newlyLoadedVideos = await this.fetchEightMoreVideosFromDB(
        numberOfCurrentShownVideos
      );
      this.$nextTick(function () {
        if (newlyLoadedVideos.length != 0) {
          newlyLoadedVideos.forEach((newVideo) => {
            if (
              !this.relatedVideos.some(
                (data) => data.videoId === newVideo.videoId
              ) &&
              this.video.videoId != newVideo.videoId
            ) {
              //don't exists
              this.relatedVideos.push(newVideo);
            }
          });
        }
      });
    },

    async fetchEightMoreVideosFromDB(numberOfCurrentShownVideos) {
      return await this.$store.dispatch(
        'fetchEightMoreVideos',
        numberOfCurrentShownVideos
      );
    },

    async updateBasedOnDelete(commentsAfterRemoval) {
      if (commentsAfterRemoval.length == 0) {
        this.relevantComments = [];
      } else {
        this.relevantComments = [];
        for (let i = 0; i < commentsAfterRemoval.length; i++) {
          if (commentsAfterRemoval[i].responseToCommentId == -1) {
            let newComment = new Comment(0, 0, '', '', 0, 0, 0, 0);
            newComment = Object.assign(newComment, commentsAfterRemoval[i]);
            newComment.timeOfPosting = this.convertDateObjectToString(
              new Date(commentsAfterRemoval[i].timeOfPosting)
            );
            this.relevantComments.push(newComment);
          }
        }
      }
      this.amountOfComments = this.relevantComments.length;
      this.activeId = 0;
    },
    async updateReplies(commentId) {
      this.activeId = commentId;
      this.currentReplies = [];
      this.currentCommenters = [];
      let res = await fetch(
        '/rest/getRepliesToComment?' +
          new URLSearchParams({
            commentId: commentId,
          }),
        {
          method: 'GET',
        }
      );
      let response = await res.json();

      for (let i = 0; i < response.length; i++) {
        this.currentReplies.push(
          new Comment(
            response[i].commentId,
            response[i].relatesToVideoId,
            response[i].postedByUsername,
            response[i].content,
            response[i].likes,
            response[i].dislikes,
            response[i].responseToCommentId,
            response[i].timeOfPosting
          )
        );
        let uploaderRes = await fetch(
          '/rest/getUserByUsername?' +
            new URLSearchParams({
              providedUsername: response[i].postedByUsername,
            })
        );
        let userResponse = await uploaderRes.json();
        let myUser = new User(
          userResponse.userId,
          userResponse.username,
          userResponse.description,
          userResponse.profileURL,
          userResponse.subscribers,
          userResponse.videosPosted
        );
        this.currentCommenters.push(myUser);
      }
      this.amountOfComments =
        this.currentReplies.length + this.relevantComments.length;
    },
    async updateCommentSection() {},
    updateComments(postedComment) {
      let newComment = new Comment();
      newComment = Object.assign(newComment, postedComment);
      newComment.timeOfPosting = this.convertDateObjectToString(
        new Date(newComment.timeOfPosting)
      );
      this.relevantComments.push(newComment);
      this.amountOfComments = this.relevantComments.length;
    },
    convertDateObjectToString(dateObject) {
      let newDate =
        (dateObject.getUTCDate() < 10
          ? '0' + dateObject.getUTCDate()
          : dateObject.getUTCDate()) +
        ' - ' +
        (dateObject.getUTCMonth() + 1 < 10
          ? '0' + (dateObject.getUTCMonth() + 1)
          : dateObject.getUTCMonth() + 1) +
        ' - ' +
        dateObject.getUTCFullYear();
      newDate = newDate + ' ' + dateObject.getHours() + ':';

      if (dateObject.getMinutes() < 1) {
        newDate += '00';
      } else if (dateObject.getMinutes() < 10) {
        newDate += '0' + dateObject.getMinutes();
      } else {
        newDate += dateObject.getMinutes();
      }
      return newDate;
    },
    async unsubscribe() {
      let uploader = this.video.userId;

      let activeUserId = JSON.parse(localStorage.activeUser).userId;

      if (this.activeUserId != 0) {
        let unsubscribeRes = await fetch(
          '/api/unsubscribe?' +
            new URLSearchParams({
              targetId: uploader,
              userId: activeUserId,
            }),
          {
            method: 'DELETE',
          }
        );
        let newAmountOfSubResponse = await unsubscribeRes.json();

        this.spacedSubs = newAmountOfSubResponse;
        this.subscribedAlready = false;
      }
    },
    async subscribe() {
      let uploader = this.Uploader;

      if (uploader) {
        let currentUser = JSON.parse(localStorage.activeUser);
        let res = await fetch(
          '/api/subscribe?' +
            new URLSearchParams({
              targetId: this.Uploader.userId,
              userId: currentUser.userId,
            }),
          {
            method: 'POST',
          }
        );
        let newAmountOfSubResponse = await res.json();

        this.spacedSubs = newAmountOfSubResponse;
        this.subscribedAlready = true;
      }
    },
    async likeVideo() {
      if (
        this.loggedInUser &&
        !this.likedVideoAlready &&
        !this.dislikedVideoAlready
      ) {
        let relevantInfo = {
          userId: JSON.parse(localStorage.activeUser).userId,
          videoId: this.video.videoId,
          likes: this.video.likes,
        };
        let likedVideoRes = await fetch('/api/likeVideo', {
          method: 'POST',
          body: JSON.stringify(relevantInfo),
        });
        let likedVideoResponse = await likedVideoRes.json();

        let likeObject = {
          relatesToVideoId: this.$route.params.id,
          userId: JSON.parse(localStorage.activeUser).userId,
          videoId: this.video.videoId,
          commentId: -1,
        };
        let registeredLikeRes = await fetch('/api/registerLikeOnVideo', {
          method: 'POST',
          body: JSON.stringify(likeObject),
        });

        this.video.likes = likedVideoResponse;
        this.spacedLikes = this.video.likes;
        this.likedVideoAlready = true;
      } else if (this.dislikedVideoAlready) {
        alert('You already disliked this video.');
      } else {
        alert('You have to log in to like Videos!');
      }
    },
    async dislikeVideo() {
      if (
        this.$store.getters.getCurrentUser &&
        !this.likedVideoAlready &&
        !this.dislikedVideoAlready
      ) {
        let relevantInfo = {
          relatesToVideoId: this.$route.params.id,
          userId: this.$store.getters.getCurrentUser.userId,
          videoId: this.video.videoId,
          dislikes: this.video.dislikes,
        };
        let dislikedVideoRes = await fetch('/api/dislikeVideo', {
          method: 'POST',
          body: JSON.stringify(relevantInfo),
        });
        let dislikedVideoResponse = await dislikedVideoRes.json();

        let dislikeObject = {
          relatesToVideoId: this.$route.params.id,
          userId: this.$store.getters.getCurrentUser.userId,
          videoId: this.video.videoId,
          commentId: -1,
        };
        let registeredDislikeRes = await fetch('/api/registerDislikeOnVideo', {
          method: 'POST',
          body: JSON.stringify(dislikeObject),
        });

        this.video.dislikes = dislikedVideoResponse;
        this.spacedDislikes = this.video.dislikes;
        this.dislikedVideoAlready = true;
      } else if (this.likedVideoAlready) {
        alert('You already liked this video.');
      } else {
        alert('You have to log in to dislike Videos!');
      }
    },
    async actOnResize() {
      this.width = 280;
      this.height = 500;
      if (window.innerWidth < 500) {
        this.height = 400;
      }
      if (window.innerWidth < 400) {
        this.height = 300;
      }
    },
    // This is a rudimentary view count method. I spent a lot of time trying to get the
    // YouTube API to work in order to make this more robust, but didn't have any luck.
    // Using this for the time being
    incrementViewCount(urlPath) {
      setTimeout(
        async function () {
          // the if statement verifies (partially) that the user has been on the same page for 15 seconds
          if (this.$route.path === urlPath) {
            await fetch('/api/incrementViewCount', {
              method: 'PUT',
              body: JSON.stringify(this.video),
            });
          }
        }.bind(this),
        15000
      );
    },
    async loadRelevantInformation(wantedUserId) {
      let videoRes = await fetch(
        '/rest/getVideoById?' +
          new URLSearchParams({
            videoId:
              wantedUserId === undefined ? this.$route.params.id : wantedUserId,
          })
      );
      let videoResponse = await videoRes.json();

      let emptyVideo = new Video();

      this.video = Object.assign(emptyVideo, videoResponse);

      this.video.videoURL = this.video.videoURL
        .replace('watch?v=', 'embed/')
        .concat('?enablejsapi=1&origin=http://example.com');

      this.spacedViews = this.renderSpacedNumbers(this.video.views.toString());
      this.spacedLikes = this.renderSpacedNumbers(this.video.likes.toString());
      this.spacedDislikes = this.renderSpacedNumbers(
        this.video.dislikes.toString()
      );
      this.spacedStars = this.renderSpacedNumbers(this.video.stars.toString());

      let uploaderRes = await fetch(
        '/rest/getUserByUsername?' +
          new URLSearchParams({
            providedUsername: videoResponse.postedByUsername,
          })
      );
      let uploaderResponse = await uploaderRes.json();

      let emptyUser = new User(0, '', '', '', 0, 0);
      this.Uploader = Object.assign(emptyUser, uploaderResponse);
      this.spacedSubs = this.renderSpacedNumbers(
        this.Uploader.subscribers.toString()
      );
      this.spacedVideos = this.renderSpacedNumbers(
        this.Uploader.videosPosted.toString()
      );
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

      return parseInt(spacedString);
    },
    clickedMe(e) {
      if (e.target.className == 'notChosenCommentsDiv') {
        this.showCommentsSection = true;
        this.showDescriptionSection = false;
      }
      if (e.target.className == 'notChosenDescriptionDiv') {
        this.showDescriptionSection = true;
        this.showCommentsSection = false;
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
  font-family: 'Roboto', sans-serif;
  overflow-x: hidden;
}
.square3 {
  position: relative;
  top: 13px;
}
.postingCommentDiv {
  margin-bottom: 20px;
}
.backHomeDiv {
  position: absolute;
  margin-top: 40px;
  overflow-y: hidden;
}
.LightPostedCommentsDiv {
  background-color: white;
  border-top: solid 10px white;
  border-bottom: solid 10px white;
  max-width: 800px;
  margin-left: auto;
  margin-right: auto;
}
.DarkPostedCommentsDiv {
  margin-top: 10px;
  margin-bottom: 10px;
}
.watchNowPlay {
  height: 45px;
  width: 45px;
  margin-top: 4px;
}
.LightVideosBody {
  background-color: white;
}

.DarkVideosBody {
  background-color: #131313;
}
.watchNowScrollGrid {
  width: 100vw;
  height: 10px;
  padding-bottom: 46px;
  background-color: #434343;
  position: sticky;
  top: 0px;
  display: inline-block;
  z-index: 10;
}
.UpperSection {
  max-width: 1000px;
  width: 100vw;
  margin-left: auto;
  margin-right: auto;
  border-radius: 3px;
}
.watchNowText {
  font-size: 18px;
  margin-top: 16px;
}
.watchNowTextDiv {
  display: grid;
  grid-template-columns: 5px max-content auto max-content 8px max-content auto;
}

.starDiv {
  width: 97px;
  padding-left: 40px;
  background-color: black;
  display: inline-block;
  position: relative;
  top: -20px;
  left: 270px;
}

.likeTextDiv,
.dislikeTextDiv,
.starTextDiv {
  color: #939393;
  font-size: 10px;
}
.FillerDiv {
  height: 20px;
  width: 100vw;
}

.uploaderProfileDiv {
  height: 40px;
  width: 40px;
  border-radius: 30px;
  margin-top: 9.5px;
  overflow: hidden;
}

.LightUploaderProfileDiv {
  height: 40px;
  width: 40px;
  border-radius: 30px;
  margin-top: 9.5px;
  overflow: hidden;
  border: solid 1px black;
}

.LightUploaderDiv {
  display: grid;
  grid-template-columns: 17px 40px 19px 82px 19px 20px auto max-content 19px;
  background-color: white;
  height: max-content;
  padding-bottom: 16px;
}

.DarkUploaderDiv {
  display: grid;
  grid-template-columns: 17px 40px 19px 82px 19px 20px auto max-content 19px;
  background-color: black;
  height: max-content;
  padding-bottom: 16px;
}
.videosDiv {
  font-size: 9px;
  margin-top: 22px;
  color: #939393;
}
.likeAndDislikeIconDiv {
  background-color: black;
  padding-top: 24px;
  height: 15px;
  padding-bottom: 7px;
}
.likeDiv {
  width: 42px;
  padding-left: 95px;
  background-color: black;
  display: inline-block;
}
.dislikeDiv {
  width: 69px;
  padding-left: 74px;
  background-color: black;

  display: inline-block;
  position: relative;
  top: 2px;
  left: -3px;
}
.PlayerDiv {
  width: 100vw;
  max-width: 820px;
}

.LightPlayButton {
  height: 10px;
  position: relative;
  top: -4px;
}
.LightSquare {
  margin-top: 1px;
  width: max-content;
  background-color: white;
  height: max-content;
  outline: none;
  color: #e75858;
}

.DarkSquare {
  margin-top: 1px;
  width: max-content;
  background-color: black;
  height: max-content;
  outline: solid black 1px;
  color: white;
}
.LightViewsDiv {
  margin-top: 1px;
  width: max-content;
  background-color: white;
  height: max-content;
  color: white;
  font-size: 10px;
  color: #939393;
}
.DarkViewsDiv {
  margin-top: 1px;
  width: max-content;
  background-color: black;
  height: max-content;
  outline: solid black 1px;
  color: white;
  font-size: 10px;
  color: #939393;
}
.LightUploadDateDiv {
  margin-top: 2px;
  width: max-content;
  background-color: white;
  height: max-content;
  color: #939393;
  font-size: 10px;
}
.DarkUploadDateDiv {
  margin-top: 2px;
  width: max-content;
  background-color: black;
  height: max-content;
  outline: solid black 1px;
  color: #939393;
  font-size: 10px;
}
.descriptionAndCommentsDivInScroll {
  display: grid;
  grid-template-columns: auto max-content auto;
  font-family: 'Roboto', sans-serif;
  background-color: black;
  color: white;
  padding-top: 14px;
  margin-top: 0px;
  padding-bottom: 3px;
  border-bottom: 0.5px solid #bfbfbf;
}
.LightDescriptionAndCommentsDiv {
  display: grid;
  grid-template-columns: auto max-content auto;
  font-family: 'Roboto', sans-serif;
  background-color: white;
  color: black;
  padding-top: 14px;
  margin-top: -5px;
  padding-bottom: 3px;
  border-bottom: 0.5px solid black;
}
.DarkDescriptionAndCommentsDiv {
  display: grid;
  grid-template-columns: auto max-content auto;
  font-family: 'Roboto', sans-serif;
  background-color: black;
  color: white;
  padding-top: 14px;
  margin-top: -5px;
  padding-bottom: 3px;
  border-bottom: 0.5px solid #bfbfbf;
}
.LightLikesAndDislikesNumberDiv {
  display: grid;
  grid-template-columns: auto max-content auto max-content auto max-content auto;
  background-color: white;
  border-bottom: #bfbfbf solid 0.5px;
  padding-top: 10px;
}
.DarkLikesAndDislikesNumberDiv {
  display: grid;
  grid-template-columns: auto max-content auto max-content auto max-content auto;
  background-color: black;
  border-bottom: #bfbfbf solid 0.5px;
  padding-top: 10px;
}
.LightLikesNumberDiv,
.LightDislikesNumberDiv,
.LightStarNumberDiv {
  text-align: center;
  color: #939393;
  background-color: white;
  font-size: 10px;
  padding-bottom: 10px;
}
.DarkLikesNumberDiv,
.DarkDislikesNumberDiv,
.DarkStarNumberDiv {
  text-align: center;
  color: #939393;
  background-color: black;
  font-size: 10px;
  padding-bottom: 10px;
}
.FrameGrid {
  display: grid;
  grid-template-columns: 0px auto 0px;
  max-width: 820px;
  margin-left: auto;
  margin-right: auto;
}
.iFrameDiv {
  display: block;
  min-width: 100vw;
  width: 100vw;
}

.playButtonDiv {
  width: 13px;
  height: 9px;
  background-color: black;
  margin-top: 2.1px;
  outline: solid 1.5px #939393;
}
.middleDiv {
  display: grid;
  grid-template-columns: max-content 92px max-content;
}
.LightArrowIcon {
  transform: rotate(180deg);
  margin-left: 30px;
}
.ArrowIcon {
  transform: rotate(00deg);
  transform: scale(4, 3.5);
  margin-top: 16px;
  margin-bottom: 12px;
  height: 25px;
  width: 28px;
  margin-left: 15px;
}
.LightViewsAndDateDiv {
  display: grid;
  grid-template-columns: 21px max-content 12px max-content 28px max-content;
  background-color: white;
  color: #939393;
  padding-bottom: 2px;
}
.DarkViewsAndDateDiv {
  display: grid;
  grid-template-columns: 21px max-content 12px max-content 28px max-content;
  background-color: black;
  color: #939393;
  padding-bottom: 2px;
}
.lightUsernameDiv {
  margin-top: 10px;
  font-size: 12px;
  margin-top: 14.5px;
  color: #e75858;
  height: 20px;
}
.darkUsernameDiv {
  margin-top: 10px;
  font-size: 12px;
  margin-top: 14.5px;
  color: #e75858;
  height: 20px;
}
.playButton {
  margin-left: -10px;
  margin-top: -11.45px;
  transform: scale(0.6, 0.4);
}
.subButton {
  background-color: #e75858;
  height: 16px;
  color: white;
  font-size: 9px;
  outline: 1px solid black;
  padding: 2px;
  padding-left: 7px;
  padding-right: 6px;
  margin-top: 26px;
}
.subsDiv {
  font-size: 9px;
  margin-top: 1px;
  color: #939393;
}
.videoArea {
  margin-top: -57px;
  height: 268px;
  width: 100vw;
}
.LightVideoTitleDiv {
  color: black;
  background-color: white;
  padding-left: 17px;
  padding-bottom: 9px;
}

.DarkVideoTitleDiv {
  color: white;
  background-color: black;
  padding-left: 17px;
  padding-bottom: 9px;
}
.titleDiv {
  color: #939393;
}
.descriptionDiv {
  padding-top: 2px;
  padding-left: 5px;
  padding-bottom: 2px;
}

.ChosenDescriptionDiv,
.ChosenCommentsDiv {
  color: #e75858;
}
.LineDiv {
  margin-top: 6px;
  height: 2px;
  background-color: #e75858;
}

@media screen and (max-width: 305px) {
  .DarkUploaderDiv {
    display: grid;
    grid-template-columns: auto 40px auto max-content auto auto 3px auto auto;
    background-color: black;
    height: max-content;
    padding-bottom: 16px;
  }

  .middleDiv {
    display: grid;
    grid-template-columns: max-content 45px max-content;
  }

  .notChosenDescriptionDiv,
  .ChosenDescriptionDiv {
    margin-left: 13px;
  }

  .descriptionAndCommentsDiv {
    display: grid;
    grid-template-columns: 10px max-content auto max-content 10px;
    font-family: 'Roboto', sans-serif;
    background-color: black;
    color: white;
    padding-top: 14px;
    margin-top: -5px;
    padding-bottom: 3px;
    border-bottom: 0.5px solid #bfbfbf;
  }
}
</style>
