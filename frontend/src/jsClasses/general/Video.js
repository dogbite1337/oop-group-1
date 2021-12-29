export default class Video {
  constructor(videoId, userId, link, title, description, views, postedByUsername) {
    this.videoId = videoId;
    this.userId = userId;
    this.link = link;
    this.title = title;
    this.description = description;
    this.views = views;
    this.postedByUsername = postedByUsername;
  }

  getPostedByUsername() {
    return this.postedByUsername
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

  getLink() {
    return this.link;
  }

  setLink(link) {
    this.link = link;
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
