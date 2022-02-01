<template>
  <div class="home">
    <nav>
      <router-link :to="{ name: 'new-card' }" class="nav-button"
        >Create New Card</router-link
      >
      <router-link :to="{ name: 'my-decks' }" class="nav-button">
        View Your Decks</router-link
      >
    </nav>
    <div id="main">
      <input type="text" name="search" placeholder="Search cards by tag" />
      <article>
        <div id="card-container"></div>
      </article>
    </div>
  </div>
</template>

<script>
export default {
  name: "home",
};
</script>

<style scoped>
nav {
  height: 80%;
  width: 20%;
  position: fixed;
  left: 0;
  top: 16.3vh;
  padding-top: 20px;
  padding-bottom: 20px;
  overflow-x: hidden;
  background-color: #e4e0dd;
  border-right: solid #b4b0ad 1px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
}
.nav-button {
  background-color: #a66f5b;
  color: #f7fafc;
  text-decoration: none;
  border-radius: 10px;
  padding: 30px;
  font-size: 18px;
  font-weight: bold;
  margin: 10px;
  text-align: center;
  cursor: pointer;
  width: 60%;
  justify-self: flex-end;
}
#card-container {
  width: 77%;
  position: absolute;
  right: 0;
  border-radius: 20px;
  background-color: #e4e0dd;
  border: solid #b4b0ad 1px;
  margin: 3vh;
  margin-top: 9vh;
  height: 100%;
}
#main {
  top: 16.3vh;
  position: relative;
  height: 100vh;
  z-index: -1;
}
input[type="text"] {
  width: 30%;
  position: absolute;
  right: 0;
  box-sizing: border-box;
  border: 2px solid #ccc;
  border-radius: 20px;
  font-size: 16px;
  background-color: white;
  background-image: url("../assets/icons8-search-32.png");
  background-repeat: no-repeat;
  padding: 12px 20px 12px 40px;
  margin: 1vh 3vh 2vh 0vh;
}
</style>
