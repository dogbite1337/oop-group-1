<template>
  <div class="UserBox">
    <img class="SubscribedToImage" :src="image" key="" />
    <div class="UsernameDiv">{{ subbedTo.username }}</div>
    <div @click="Unsubscribe" class="UnsubscribeDiv">Unsubscribe</div>
  </div>
</template>
<script>
export default {
  emits: ['unsubscribe'],
  props: ['subbedTo'],
  name: 'SubscriptionToUser',
  data() {
    return {
      image: this.subbedTo.profileURL,
    };
  },
  methods: {
    async Unsubscribe() {
      this.$emit('unsubscribe', this.subbedTo);
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap');

.UserBox {
  text-align: center;
  width: 100px;
  height: max-content;
  background-color: transparent;
  color: black;
  padding-left: 20px;
  padding-right: 20px;
  display: inline-block;
  overflow-y: hidden;
  margin-top: 30px;
}
.SubscribedToImage {
  width: 60px;
  height: 60px;
  border-radius: 100px;
}
.UsernameDiv {
  background-color: #565454;
  color: white;
  border-bottom: 1px solid white;
}
.UnsubscribeDiv {
  background-color: #565454;
  color: #e75858;
  padding-top: 3px;
  padding-bottom: 3px;
}
</style>
