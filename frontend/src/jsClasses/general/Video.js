export default class Video {
  constructor(videoId, userId, link, title, description) {
    this.videoId = videoId;
    this.userId = userId;
    this.link = link;
    this.title = title;
    this.description = description;
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
}
