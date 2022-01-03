export default class User {
  constructor(userId, username, userDescription, profileURL, subscribers, videosPosted) {
    this.userId = userId;
    this.username = username;
    this.description = userDescription;
    this.profileURL = profileURL;
    this.subscribers = subscribers;
    this.videosPosted = videosPosted;
  }

  getSubscribers() {
    let countedIndexes = 0;
    let subscribersString = ''
    for (let i = 1; i < this.subscribers.toString().length+1; i++){
      
      if ((this.subscribers.toString().length - countedIndexes) % 3 == 0 && countedIndexes != 0) {
        subscribersString += ' '
      }
      countedIndexes += 1;
      subscribersString += this.subscribers.toString()[i-1]
    }
    return subscribersString;
  }

  setSubsscribers(subscribers) {
    this.subscribers = subscribers;
  }

  getVideosPosted() {
    return this.videosPosted;
  }

  setVideosPosted(videosPosted) {
    this.videosPosted = videosPosted;
  }
  getProfileURL() {
    return this.profileURL;
  }
  setProfileURL(profileURL) {
    this.profileURL = profileURL;
  }
  getUserId() {
    return this.userId;
  }
  setUserId( newUserId ) {
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