<template>
    <div>
        <GmapMap
            :center="center"
            :zoom="zoom"
            map-type-id="terrain"
            style="width: 500px; height: 300px" >
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
    </div>
</template>

<script>
import Vue from "vue";
import * as VueGoogleMaps from "vue2-google-maps";  
Vue.use(VueGoogleMaps, {
	load: {
		key: "AIzaSyCfNJEuxsE-YjCjk-iDw_ZNacrb1iHkXQ4"
	}
});
  
export default {
	name: "mapg",
	props:{
		marker_items: Array,
		center: Object,
		zoom: Number
	},
	data() {
		return {
			
		};
	}
};

</script>

<style scoped>
</style>