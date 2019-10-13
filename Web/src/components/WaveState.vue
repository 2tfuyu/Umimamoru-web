/* eslint-disable */

<template>
    <div>
        <div class="state">
          <p> 海流の様子</p>
        </div>
        <div class="wave_speed" id="fast">速い</div>
        <div class="wave_speed" id="usually">普通</div>
        <div class="wave_speed" id="slow">穏やか</div>
        <ul>
            <li class="module" v-for="(mod, index) in flows" :key="index"
			:style="{'--danger-color': mod.flow.flow >= 1.6 &&  (mod.flow.angle > Math.PI * 3 / 4 && mod.flow.angle < Math.PI * 5 / 4) ?'#ef8468' : mod.flow.flow >= 0.8 && (mod.flow.angle > Math.PI * 3 / 4 && mod.flow.angle < Math.PI * 5 / 4) ? '#92d050' : '#4cbbb4'}"
			@click="$emit('open', mod.flow.loc)">
                <p class="pole">{{mod.flow.loc}}番モジュール</p>
                <div class="info">
                    <p>流速 ： {{mod.flow.flow}} m/s</p>
                    <p>角度 ： {{mod.flow.angle * 180 / Math.PI}} °</p>
                    <p>今月の離岸流発生回数 : {{mod.count}} 回</p>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
	data(){
		return{
		};
	},
	props: {flows:Array}
};
</script>

<style scoped>
	ul {
		list-style: none;
	}
    .info{
        margin-left: 5%;
        font-size: 20px;
    }
    .pole{
        clear: left;
        margin-left: 5%;
        margin-bottom: 20px;
        list-style-type: none;
        position: relative;
        padding-left: 0.6em;
        font-size: 25px;
        color: black;
    }

    .state{
        float: left;
        text-align: center;
        margin-left: 35%;
        margin-top: 25px;
        margin-bottom: 25px;
        font-weight: normal;
        font-size: 17px;
        color: #4CBBB4;/*文字色*/
        background: #FDFCF1;
        border: solid 2px #4CBBB4;/*線*/
        border-radius: 4px;/*角の丸み*/
        width: 25%;
        box-shadow: 2px 2px 4px gray;
    }

    .module{
        float: left;
        text-align: left;
        margin-left: 35%;
        margin-top: 25px;
        margin-bottom: 25px;
        font-weight: normal;
        font-size: 17px;
        color: black;/*文字色*/
        background: #FDFCF1;
        border: solid 2px #4CBBB4;/*線*/
        border-radius: 4px;/*角の丸み*/
        width: 70%;
        box-shadow: 2px 2px 4px gray;
    }

    .wave_speed{
        position: relative;
        float: left;
        margin-left: 35px;
        margin-top: 60px;
        font-size: 18px;
    }

    .wave_speed::before{
        border-radius: 50%;
        width: 18px;
        height: 18px;
        content: "";
        display: block;
        position: absolute;
        left: -20px;
        top: 3.7px;
    }

    #fast.wave_speed::before{
        background-color: #ef8468;
    }

    #usually.wave_speed::before{
        background-color: #92d050;
    }

    #slow.wave_speed::before{
        background-color: #4cbbb4;
    }

    .pole::before{
        border-radius: 50%; /*丸くする*/
        width: 30px; /*点の幅*/
        height: 30px; /*点の高さ*/
        display: block;
        position: absolute; /*絶対配置*/
        left: -30px; /*点の位置*/
        top: 4px; /*点の位置*/
        content: "";
        background: var(--danger-color); /*点の色*/
    }
</style>