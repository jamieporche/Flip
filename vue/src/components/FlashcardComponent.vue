<template>
  <div class="scene">
    <div class="flashcard" v-on:click.stop="flip($event)">
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
    flip(event) {
      event.target.parentElement.classList.toggle("is-flipped");
    },
  },
};
</script>

<style scoped>
.scene {
  width: 100%;
  height: 100%;
  perspective: 600px;
}
.flashcard {
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
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.card__face--back {
  background: white;
  border: solid 1px black;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  transform: rotateY(180deg);
}
.is-flipped {
  transform: rotateY(180deg);
}
</style>