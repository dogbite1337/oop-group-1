<template>
    <div v-for="video of matchedVideoList" :key="video" class="videoCard">
         <div class="thumbnail">
             <img :src="'https://img.youtube.com/vi/' +
                video.videoURL.substring(32, 43) +
                '/default.jpg'">
         </div>
         <div class="textInfo">
             <div class="title">
                 <a class="titleText" :href="'/VideoPage/' + video.videoId">
                     {{displayTitleBeforeKey(video.title)}}
                     <p class="keyword">{{displayKeyWord(video.title)}}</p>
                     {{displayTitleAfterKey(video.title)}}
                 </a>
                 <!-- <p>{{video.title}}</p> -->
             </div>
             <div class="otherInfo">
                 <div class="uploader">
                     <p class="icon">UP</p>
                     <p class="uploaderName">{{video.postedByUsername}}</p>
                 </div>
                 <div class="view">
                     <img class="icon" src="src\projectImages\dark-play-button.png">
                     <p class="viewCount">{{video.views}}</p>
                 </div>
             </div>
         </div>
    </div>
</template>

<script>
export default {
    props:["matchedVideoList"],

    data(){
        return{
            keyword: this.$store.getters.getKeyWord,
        }
    },

    created(){
    },

    methods:{
        displayTitleBeforeKey(orginalTitle){
            let index = orginalTitle.toLowerCase().indexOf(this.keyword.toLowerCase())
            
            return orginalTitle.substring(0,index)
        },
        displayTitleAfterKey(orginalTitle){
            let index = orginalTitle.toLowerCase().indexOf(this.keyword.toLowerCase())
            return orginalTitle.substring((index+this.keyword.length),orginalTitle.length)
        },
        displayKeyWord(orginalTitle){
            let index = orginalTitle.toLowerCase().indexOf(this.keyword.toLowerCase())
            return orginalTitle.substring(index, index + this.keyword.length)

        }
    }
}
</script>

<style scoped>
p{
    color: grey;
    font-size: small;
}

.titleText .keyword{
    color: rgb(255, 99, 99);
}

.titleText{
    display: flex;
    color: white;
}

.title p{
    color: white;
    font-size: medium;
}

.videoCard{
    display: grid;
    grid-template-columns: 50% 50%;
    padding: 2% 5% 2% 5%;
    border-bottom: solid 1px #ababab75;
}

.thumbnail{
    height: 15vh;
    margin-right: 3vw;
}

img{
    width: -webkit-fill-available;
    max-block-size: -webkit-fill-available;
    height: -webkit-fill-available;
    border: solid 1px grey;
}

.textInfo{
    height: 15vh;
    margin-left: 3vw;
    place-content: space-between;
}

.textInfo,
.otherInfo{
    display: flex;
    flex-direction: column;
}

.uploader{
    margin-bottom: 0.5vh;
}

.uploader,
.view{
    display: grid;
    grid-template-columns: 15% auto;
}

.icon{
    border: solid 2px grey;
    background: white;
    height: 1.5vh;
    width: 3.5vw;
    text-align: center;
    align-self: center;
}

.uploader .icon{
    font-family: cursive;
    font-size: xx-small;
}

.uploaderName,
.viewCount{
    margin-left: 3vw;
    align-self: center;
    height: 2.5vh;
}

</style>