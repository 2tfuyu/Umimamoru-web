<template>
  <div>
    <BeachMenu />
    <Title />
    <Occurring />
    <GmapMap :center="center" :zoom="zoom" style="width: 100%; height: 100%;" />
    <WaveState @open="openModal" />
    <Modal @close="closeModal"  v-if="modal">
      <GmapMap
        :center="{lat:10, lng:10}"
        :zoom="7"
        map-type-id="terrain"
        style="width: 500px; height: 300px"
      >
      </GmapMap>
    </Modal>
    
  </div>
</template>

<script>

  import BeachMenu from "@/components/BeachMenu";
  import Title from "@/components/Title";
  import Occurring from "@/components/Occurring";
  import WaveState from "@/components/WaveState";
  import Modal from '@/components/Modal'
  import * as VueGoogleMaps from 'vue2-google-maps'
  import Vue from 'vue'

  Vue.use(VueGoogleMaps, {
    load: {
      key: 'AIzaSyB_3Nt_qhA1P5uttxA4K5LCy62dcEuJ0OY'
    }
  });

  export default {
    name: 'app',
    components: {
      BeachMenu,
      Title,
      Occurring,
      WaveState,
      Modal
    },
    data() {
      return {
        modal: false
      }
    },
    methods: {
      openModal() {
        this.modal = true
      },
      closeModal() {
        this.modal = false
      }
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
