<template>
  <transition name="modal" appear>
    <div class="modal modal-overlay" @click.self="$emit('close')">
      <div class="modal-window">
        <div class="modal-content">
          <slot />
        </div>
        <footer class="modal-footer">
          <slot name="footer">
            <button @click="$emit('close')">Close</button>
          </slot>
        </footer>
      </div>
    </div>
  </transition>
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
	name: "modal",
	data() {
		return {
			center: {lat: 26.5120833, lng: 128.0282313},
			zoom: 14,
			marker_items: [
				{
					position: {lat: 26.511621, lng: 128.028338},
					title: "モジュール1\n流速: 0.8m/s 発生回数: 4",
					icon: {
						url: "https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|FF0000"
					}
				},
				{
					position: {lat: 26.511614, lng: 128.028475},
					title: "モジュール2",
					icon: {
						url: "https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|00FF00"
					}
				},
				{
					position: {lat: 26.511605, lng: 128.028586},
					title: "モジュール3",
					icon: {
						url: "https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|0000FF"
					}
				},
				{
					position: {lat: 26.511601, lng: 128.028697},
					title: "モジュール4",
					icon: {
						url: "https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|0000FF"
					}
				}
			]
		};
	}
};

</script>

<style lang="stylus" scoped>
.modal {
  &.modal-overlay {
    display: flex;
    align-items: center;
    justify-content: center;
    position: fixed;
    z-index: 30;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
  }

  &-window {
    background: #fff;
    border-radius: 4px;
    overflow: hidden;
  }

  &-content {
    padding: 10px 20px;
  }

  &-footer {
    background: #ccc;
    padding: 10px;
    text-align: right;
  }
}

.modal-enter-active, .modal-leave-active {
  transition: opacity 0.4s;

  .modal-window {
    transition: opacity 0.4s, transform 0.4s;
  }
}

.modal-leave-active {
  transition: opacity 0.6s ease 0.4s;
}

.modal-enter, .modal-leave-to {
  opacity: 0;

  .modal-window {
    opacity: 0;
    transform: translateY(-20px);
  }
}
</style>