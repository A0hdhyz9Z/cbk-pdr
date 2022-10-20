<template>
	<div class="block text-center">
		<el-carousel loop>
			<el-carousel-item v-for="item in imageList" :key="item.name">
				<img :src="getImageUrl(item.name)" style="height: 100%;" />
			</el-carousel-item>
		</el-carousel>
	</div>
</template>

<script>
export default {
	data() {
		return {
			imageList: [{ name: "welcome.jpg" }, { name: "welcome2.jpg" }],
		};
	},
	methods: {
		getImageUrl(name) {
			return new URL(`../../assets/images/${name}`, import.meta.url).href;
		},
	},
};
</script>

<style scoped>
.el-carousel__item h3 {
	color: #475669;
	opacity: 0.75;
	line-height: 150px;
	margin: 0;
	text-align: center;
}

.el-carousel__item:nth-child(2n) {
	background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
	background-color: #99a9bf;
}
</style>
