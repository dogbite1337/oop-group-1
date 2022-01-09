<template>
    <Header />
    <HasResult v-if="matchedVideoList.length > 0"/>
    
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
        this.matchedVideoList = this.$store.dispatch('getMatchedVideoList', keyword);
        console.log(this.matchedVideoList)
        // this.matchedUserList = this.$store.dispatch('getMatchedUserList', keyword);
    }
    
}
</script>

<style scoped>

</style>