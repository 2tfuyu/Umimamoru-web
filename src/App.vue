<template>
  <div>
    <BeachMenu />
    <Title />
    <Occurring />
    <GmapMap :center="center" :zoom="zoom" style="width: 100%; height: 100%;" />
    <WaveState @open="openModal" />
    <Modal @close="closeModal"  v-if="modal">
      <GmapMap
        :center="center"
        :zoom="zoom"
        map-type-id="terrain"
        style="width: 500px; height: 300px"
      >
        <GmapMarker v-for="(m,id) in marker_items"
          :position="m.position"
          :title="m.title"
          :clickable="true" 
          :draggable="false" 
          :key="id"
          :icon="m.icon"
          >
        </GmapMarker>
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
      key: 'AIzaSyCfNJEuxsE-YjCjk-iDw_ZNacrb1iHkXQ4'
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
        modal: false,
        center: {lat: 26.5120833, lng: 128.0282313},
        zoom: 14,
        marker_items: [
          {
            position: {lat: 26.511621, lng: 128.028338},
            title: 'モジュール1\n流速: 0.8m/s 発生回数: 4',
            icon: {
              url: 'https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|FF0000'
            }
          },
          {
            position: {lat: 26.511614, lng: 128.028475},
            title: 'モジュール2',
            icon: {
              url: 'https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|00FF00'
            }
          },
          {
            position: {lat: 26.511605, lng: 128.028586},
            title: 'モジュール3',
            icon: {
              url: 'https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|0000FF'
            }
          },
          {
            position: {lat: 26.511601, lng: 128.028697},
            title: 'モジュール4',
            icon: {
              url: 'https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|0000FF'
            }
          }
        ]
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
