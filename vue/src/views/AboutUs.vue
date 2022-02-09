<template>
  <div class="view">
    <div id="main">
      <article>
        <div id="card-container"></div>
      </article>
    </div>
    <div class="footer">
      <footer-component />
    </div>
  </div>
</template>

<script>
import FooterComponent from "../components/FooterComponent.vue";

export default {
  components: {
    FooterComponent,
  },
  name: "home",
  data() {
    return {
      teammates: this.$store.state.teammates,
    };
  },
  computed: {},
};
</script>

<style scoped>
.view {
  min-height: 100vh;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas:
    "body"
    "footer";
}
#card-container {
  height: 75vh;
  background-image: url("../assets/lighter-blue-green-background.png");
  padding: 4vh 4vh 4vh 4vh;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  align-content: space-between;
  gap: 7vh 3vh;
  overflow: auto;
}
#main {
  grid-area: body;
  min-height: 75vh;
  margin-top: 11vh;
}
.flashcard-component {
  height: 24vh;
  width: 40vh;
}
.footer {
  z-index: 3;
  grid-area: footer;
}
</style>
