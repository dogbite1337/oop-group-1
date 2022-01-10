<template>
    <Header />
    <HasResult v-if="matchedVideoList.length > 0" :matchedVideoList = matchedVideoList />
    
    <Footer />
</template>

<script>
import Header from "../components/Header.vue"
import Footer from "../components/Footer.vue"
import HasResult from "../components/SearchResultPageComponents/HasResult.vue"

export default {

    components:{
        Header,
        Footer,
        HasResult
    },

    data(){
        return{
            matchedVideoList: [],
            matchedUserList: [],
        }
    },

    async mounted(){
        let keyword = this.$store.getters.getKeyWord;
        this.matchedVideoList = await this.$store.dispatch('getMatchedVideoList', keyword);
        // this.matchedUserList = this.$store.dispatch('getMatchedUserList', keyword);
    }
    
}
</script>

<style scoped>

</style>