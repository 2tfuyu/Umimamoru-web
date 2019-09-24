<template>
    <div class="occurring">
        <ul>
            <li class="box_small" id="left">
                <p>離岸流の発生</p>
            </li>
            <li class="box_small" id="right">
                <p>離岸流の発生場所</p>
            </li>
        </ul>

        <div class="line" style=background-color:gray;></div>

    </div>
</template>

<script>
    export default {
        name: "Occurring"
    }
</script>

<style scoped>
    .occurring{
        position: relative;
        height: 250px;
        padding: 0.5em 1em;
        margin-top: 50px;
        margin-left: 35%;
        font-weight: bold;
        color: black;/*文字色*/
        background: #FDFCF1;
        border: solid 2px gray;/*線*/
        border-radius: 10px;/*角の丸み*/
    }

    .box_small{
        list-style-type: none;
        position: absolute;
        padding: 0;
        text-align: center;
        top: -25px;
        text-align: center;
        font-weight: normal;
        font-size: 17px;
        color: #4CBBB4;
        background: #FDFCF1;
        border: solid 2px #4CBBB4;
        border-radius: 4px;
        width: 35%;
        height: 50px;
        box-shadow: 2px 2px 4px gray;
    }

    #left{
        left: 7.5%;
    }

    #right{
        right: 7.5%;
    }

    .line{
        position: absolute;
        left: 50%;
        top: 8px;
        width:2px;
        height: 250px;
    }
</style>