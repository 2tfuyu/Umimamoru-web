<template>
    <div class="occurring">
        <ul>
            <li class="box_small" id="left">
                <p>離岸流の発生</p>
                <div class="image">
                    <h3>離岸流が発生しています</h3>
                    発生地点で警告音が鳴っています<br>
                    十分に警戒してください
                </div>
            </li>
            <li class="box_small" id="right">
                <p>離岸流の発生場所</p>
                <div class="occur_place">
                    <ul type="disc" class="ul">
                        <li><span>4番ポール</span></li>
                        <li><span>5番ポール</span></li>
                    </ul>
                </div>
            </li>
        </ul>

        <div class="line" style=background-color:gray;></div>

    </div>
</template>

<script>
    import AssetsImage from "@/assets/warning.png";
    export default {
        name: "Occurring",
        data() {
            return {
                assetsImage: AssetsImage,
                assetsImage_NG: "../assets/warning.png",
                staticImage: "/warning.png"
            };
        }
    }
</script>

<style scoped>

    .ul{
        color: #4cbbb4;
    }

    span{
        color: gray;
    }

    h3{
        color: #ef8468;
    }
    .image{
        text-align: left;
        color: darkgray;
    }

    .occur_place{
        font-size: 30px;
        margin-top: 50px;
        text-align: left;
        color: gray;
    }
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