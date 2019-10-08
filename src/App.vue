<template>
  <div>
    <BeachMenu @selectBeach="select" :city="network.city" :ward="network.ward"/>
    <Title :beach="network.beach" :city="network.city" />
    <Occurring :danger="danger" :poles="poles"/>
    <WaveState @open="openModal" :flows="flows" />
    <Modal @close="closeModal"  v-if="modal">
        <MapG
			:marker_items="marker_items"
			:center="center"
			:zoom="zoom"/>
    </Modal>
  </div>
</template>

<script>

import BeachMenu from "@/components/BeachMenu";
import Title from "@/components/Title";
import Occurring from "@/components/Occurring";
import WaveState from "@/components/WaveState";
import Modal from "@/components/Modal";
import MapG from "@/components/MapG";
import axios from "axios";
import { clearInterval } from "timers";
  
export default {
	name: "app",
	components: {
		BeachMenu,
		Title,
		Occurring,
		WaveState,
		Modal,
		MapG
	},
	data() {
		return {
			modal: false,
			loading: false,
			network: {},
			interval: undefined,
			flows: [],
			zoom: 18,
			modules:[],
			marker_items: [],
			danger: false,
			poles:[],
			center: {}
		};
	},
	methods: {
		openModal(loc) {
			for(var i = 0; i < this.modules.length; i++){
				if(this.modules[i].loc == loc){
					this.center = {lat: this.modules[i].latitude, lng: this.modules[i].longitude};
					break;
				}
			}
			this.modal = true;

		},
		closeModal() {
			this.modal = false;
		},
		getModule(){
			axios.get("http://35.247.121.242:8080/Umimamoru/umimamoru/net/module?net=" + this.network.net)
				.then(response => {
					if(response.status == 200) {
						this.modules = response.data;
					}
				}
				);
		},
		getZoom(v, h){
			v *= 2;
			h *= 2;
			var array = [2, 5, 1];
			var ten = 10000;
			var zoom = 1;
			for(var i = 2; ten > 0.0001; i++, zoom++){
				if(i >= 3){
					i = 0;
					ten /= 10;
				}
				if(array[i] * ten <= Math.max(v, h)){
					return Math.max(zoom - 1, 19);
				}
			}
			return 19;
		},
		getRange(){
			axios.get("http://35.247.121.242:8080/Umimamoru/umimamoru/net/range?net=" + this.network.net)
				.then(response => {
					if(response.status == 200) {
						var v = response.data.vertical;
						var h = response.data.horizontal;
						this.zoom = this.getZoom(v/1000, h/1000)-1;
						console.log(this.zoom);
					}
				}
				);
		},
		getFlow(){
			if (! this.interval) clearInterval(this.interval);
			var net = this.network.net;
			console.log(net);
			var func = function(vue){
				axios.get("http://35.247.121.242:8080/Umimamoru/umimamoru/net/flow?net=" + net)
					.then(response => {
						if(response.status == 200) {
							vue.flows = response.data;
							vue.marker_items = [];
							vue.poles = [];
							vue.danger = false;
							for(var i = 0; i < vue.flows.length; i++){
								var flow = vue.flows[i];
								var mod = {};
								for(var j = 0; j < vue.modules.length; j++){
									if(vue.modules[j].loc == flow.flow.loc) mod = vue.modules[j];
								}
								var color;
								if(flow.flow.tobank >= 1.6){
									vue.danger = true;
									vue.poles.push(mod.pole);
									color = "EF8468";
								}else if (flow.flow.tobank >= 0.8){
									color="92D050";
								}else{
									color = "4CBBB4";
								}
								vue.marker_items.push({
									position: {lat: mod.latitude, lng: mod.longitude},
									title: "モジュール" + mod.loc,
									icon: {
										url: "https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + color
									}
								});
							}
							vue.poles = vue.poles.filter(function (x, i, self) {
								return self.indexOf(x) === i;
							});
						}
					});
			};
			func(this);
			this.interval = setInterval(func, 5000, this);
		},
		select(beach){
			if(!this.loading){
				this.loading = true;
				axios.get("http://35.247.121.242:8080/Umimamoru/umimamoru/net/beach?beach=" + beach)
					.then(response => {
						if(response.status == 200) {
							this.network = response.data[0];
							this.center = {lat:this.network.latitude, lng:this.network.longitude};
							this.getModule();
							this.getRange();
							this.getFlow();
						}
					}
					);
			}
		}
	},
	mounted () {
		
	}
};

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
