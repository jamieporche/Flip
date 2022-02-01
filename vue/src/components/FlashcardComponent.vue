<template>
  <div class="scene">
    <div id="flashcard" v-on:click.stop="flip">
      <div class="card__face card__face--front">Question: {{ this.front }}</div>
      <div class="card__face card__face--back">Answer: {{ this.back }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "flashcard-component",
  props: ["front", "back"],
  methods: {
    flip() {
      const flashcard = document.getElementById("flashcard");
      flashcard.classList.toggle("is-flipped");
    },
  },
};
</script>

<style scoped>
.scene {
  width: 200px;
  height: 260px;
  perspective: 600px;
}
#flashcard {
  width: 100%;
  height: 100%;
  position: relative;
  transition: transform 1s;
  transform-style: preserve-3d;
}
.card__face {
  position: absolute;
  height: 100%;
  width: 100%;
  backface-visibility: hidden;
}
.card__face--front {
  background: white;
  border: solid 1px black;
}
.card__face--back {
  background: white;
  border: solid 1px black;
  transform: rotateY(180deg);
}
.is-flipped {
  transform: rotateY(180deg);
}
</style>