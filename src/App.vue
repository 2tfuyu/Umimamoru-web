<template>
  <div>
    <BeachMenu />
    <Title />
    <Occurring />
    <WaveState />
  </div>
</template>

<script>

  import BeachMenu from "@/components/BeachMenu";
  import Title from "@/components/Title";
  import Occurring from "@/components/Occurring";
  import WaveState from "@/components/WaveState";
  export default {
    name: 'app',
    components: {
      BeachMenu,
      Title,
      Occurring,
      WaveState
    }
  }
</script>

<style>
  body {
    position: initial;
    padding: 0;
    margin: 0;
    width: 83%;
    height: 300px;
    background: #FDFCF1;
  }
</style>
