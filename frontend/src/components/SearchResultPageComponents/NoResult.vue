<template>
    <div class="NoResultText">
      <p>No result was found</p>
    </div>
    <div class="kittyImageDiv">
      <img src="src/projectImages/sorryKitty.gif" alt="">
    </div>
</template>

<script>
export default {
    
}
</script>

<style scoped>
.NoResultText{
  width:max-content;
  margin-left: auto;
  margin-right: auto;
}
p{
    color: white;
    text-align-last: center;
    margin-top: 10vh;
}

.kittyImageDiv{
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  position: relative;
  left: -20px;
}
</style>