<template>
    <MatchedVideos v-if="matchedVideoList.length > 0" :matchedVideoList = matchedVideoList />
</template>
<script>
import MatchedUsers from "./MatchedUsers.vue"
import MatchedVideos from "./MatchedVideos.vue"

export default {
    props:['matchedVideoList'],
    
    components:{
        MatchedUsers,
        MatchedVideos
    },

    mounted(){
        console.log("in Hasresult")
        console.log(this.$props.matchedVideoList.length)
    },

    data(){
        return{

        }
    }
}
</script>

<style scoped>

</style>