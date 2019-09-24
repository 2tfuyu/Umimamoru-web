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
                <label>
                    <select v-model="selected" class="span">
                        <option value="">沖縄県</option>
                        <option>A</option>
                        <option>B</option>
                        <option>C</option>
                    </select>
                </label>
                <span>都道府県: {{ selected }}</span>
                <label>
                    <select v-model="selected" class="span">
                        <option value="">シーグラスビーチ</option>
                        <option>A</option>
                        <option>B</option>
                        <option>C</option>
                    </select>
                </label>
                <span>ビーチ名: {{ selected }}</span>
                <hr>
                <h3>検索結果</h3>

                <p>
                    <img src="../assets/oyogu_people.png" class="oyoguhito_small" align="bottom">
                    シーグラスビーチ
                    <span class="title">沖縄県名護市豊原</span>
                </p>
            </div>
        </header>
    </div>
</template>

<script>
    import AssetsImage from "@/assets/oyogu_people.png";
    export default {
        name: "BeachMenu",
        data() {
            return {
                assetsImage: AssetsImage,
                assetsImage_NG: "../assets/oyogu_people.png",
                staticImage: "/oyogu_people.png"
            };
        }
    }
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