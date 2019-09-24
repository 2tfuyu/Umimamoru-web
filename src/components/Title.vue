<template>
    <div>

        <div class="set">
            <img src="../assets/umimamoru.png" class="logo" align="middle">
            -離岸流事故防止システム-
        </div>

        <p class="text">名護市</p>
        <p class="text">シーサイドビーチ<span class="span">の様子</span></p>
    </div>
</template>

<script>
    export default {
        name: "Title"
    }
</script>

<style scoped>
    .span {
        margin-left: 30px;
    }

    .logo{
        float: top;
        margin-left: 350px;
        size: 30px;
    }

    .set{
        font-size: 25px;
    }

    .text{
        clear: left;
        margin: 0;
        margin-left: 35%;
        font-size: 150%
    }
</style>