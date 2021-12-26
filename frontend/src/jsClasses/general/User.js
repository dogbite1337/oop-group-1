export default class User {
  constructor(username, userDescription, userVideos) {
    this.userId = 0;
    this.username = username;
    this.description = userDescription;
    this.userVideos = userVideos;
  }
  getuserId() {
    return this.userId;
  }
  setuserId( newUserId ) {
    this.userId = newUserId;
  }
  getUsername() {
    return this.username;
  }
  setUsername(newUsername) {
    this.username = newUsername;
  }

  getDescription() {
    return this.description;
  }

  setDescription(newUserDescripttion) {
    this.description = newUserDescripttion;
  }

  getUserVideos() {
    return this.userVideos;
  }

  addUserVideo(newVideo) {
    this.userVideos.push(newVideo);
  }

  removeUserVideo(videoId) {
    // TO DO
  }

  changeUserVideoDescription(newDescription, videoId) {
    // TO DO
  }
  
}