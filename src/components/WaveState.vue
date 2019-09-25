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
            <div class="module" @click="$emit('open')">
                <li class="pole">1番モジュール</li>
                <div class="info">
                    <p>流速 ： 0.6m/s</p>
                    <p>今月の離岸流発生回数 : 2回</p>
                </div>
            </div>

            <div class="module">
                <li class="pole">2番モジュール</li>
                <div class="info">
                    <p>流速 ： 1.3m/s</p>
                    <p>今月の離岸流発生回数 : 2回</p>
                </div>
            </div>
        </ul>
    </div>
</template>

<style scoped>

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
        color: #5b9a92;
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
        color: #4CBBB4;/*文字色*/
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
        background: #4cbbb4; /*点の色*/
    }
</style>