<template>
    <div>
        <header>
            <div>
                <p>
                    <img src="../assets/oyogu_people.png" class="oyoguhito" align="middle">
                    ビーチ一覧
                </p>
                <hr>
                <h3>検索する</h3>
                <span>都道府県:</span>
                <label>
                <select name="pref" v-model="selectedPref" v-on:change="changePref">
                    <option v-for="(pref, id) in prefs" v-bind:key="id">
                        {{ pref.name }}
                    </option>
                </select>
                </label>
                <br>
                <span>ビーチ名:</span>
                <label>
                    <select name="beach" v-model="selectedBeach" v-on:change="changeBeach">
                    <option v-for="(beach, id) in beaches" v-bind:key="id">
                        {{ beach.beach }}
                    </option>
                </select>
                </label>
                <hr>
                <h3>検索結果</h3>
                <p>
                    <img src="../assets/oyogu_people.png" class="oyoguhito_small" align="bottom">
                    {{selectedBeach}}
                    <span class="title">{{selectedPref}}{{city}}{{ward}}</span>
                </p>
            </div>
        </header>
    </div>
</template>

<script>
import axios from "axios";
import AssetsImage from "@/assets/oyogu_people.png";
export default {
	name: "BeachMenu",
	props:{
		ward: String,
		city: String
	},
	data() {
		return {
			assetsImage: AssetsImage,
			assetsImage_NG: "../assets/oyogu_people.png",
			staticImage: "/oyogu_people.png",
			selectedPref: "",
			selectedBeach: "",
			prefs: [
				{name:"沖縄県"}, {name:"宮崎県"}
			],
			beaches: [
                    
			],
			onConnect: false
		};
	},
	methods: {
		changePref (){
			axios.get("http://35.247.121.242:8080/Umimamoru/umimamoru/net/pref?pref=" + this.selectedPref)
				.then(response => {
					console.log(response);
					// eslint-disable-next-line no-empty
					//if(response.status != 200) {
					//}else{
					this.beaches = response.data;
					//}
				}
				);
		},
		changeBeach (){
			console.log(this.selectedBeach);
			this.$emit("selectBeach", this.selectedBeach);
		}
	}
};
</script>

<style scoped>
    header{
        height: 100%;
        width: 25%;
        left: 0;
        color: #FDFCF1;
        background-color: #4CBBB4;
        position: fixed;
    }

    header p{
        font-size: 25px;
    }

    .oyoguhito{
        height: 30%;
        width: 50%;
    }

    .oyoguhito_small{
        height: 15%;
        width: 25%;
        margin-left: 10px;
    }

    .span{
        margin-left: 30px;
    }

    span{
        margin-left: 30px;
    }

    h3{
        margin-left: 30px;
    }

    .title{
        margin-left: 100px;
    }
</style>