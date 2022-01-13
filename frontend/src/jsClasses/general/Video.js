export default class Video {
  constructor(
    videoId,
    userId,
    uploadDate,
    videoURL,
    title,
    description,
    views,
    postedByUsername,
    likes,
    dislikes,
    stars
  ) {
    this.videoId = videoId;
    this.userId = userId;
    this.uploadDate = uploadDate;
    this.videoURL = videoURL;
    this.title = title;
    this.description = description;
    this.views = views;
    this.postedByUsername = postedByUsername;
    this.likes = likes;
    this.dislikes = dislikes;
    this.stars = stars;
  }

  getUploadDate() {
    return this.uploadDate;
  }

  setUploadDate(uploadDate) {
    this.uploadDate = uploadDate;
  }

  getStars() {
    return this.stars;
  }

  setStars(stars) {
    this.stars = stars;
  }

  getDislikes() {
    return this.dislikes;
  }

  setDislikes(dislikes) {
    this.dislikes = dislikes;
  }

  getLikes() {
    return this.likes;
  }

  setLikes(likes) {
    this.likes = likes;
  }

  getPostedByUsername() {
    return this.postedByUsername;
  }

  setPostedByUsername(username) {
    this.postedByUsername = username;
  }

  getVideoId() {
    return this.videoId;
  }

  setVideoId(videoId) {
    this.videoId = videoId;
  }

  getuserId() {
    return this.userId;
  }
  setuserId(userId) {
    this.userId = userId;
  }

  getVideoURL() {
    return this.videoURL;
  }

  setVideoUrl(videoURL) {
    this.videoURL = videoURL;
  }

  getTitle() {
    return this.title;
  }

  setTitle(title) {
    this.title = title;
  }

  getDescription() {
    return this.description;
  }

  setDescription(description) {
    this.description = description;
  }

  getViews() {
    return this.views;
  }

  setViews(views) {
    this.views = views;
  }
}
