<template>
	<el-row class="asd">
		<div style="height: 300px;border: 0px solid green;">
			<el-button @click="startTest">开始测试</el-button>
			<el-button @click="picture">拍照</el-button>
			<el-button @click="pointCorrect">坐标标定</el-button>
			<el-button @click="cameraMove">相机移动</el-button>
			<el-button @click="finished">放行</el-button>
			<el-button @click="lightOpen">光源打开</el-button>
			<el-button @click="lightClose">光源关闭</el-button>
			<el-button @click="editImg">框图</el-button>
		</div>
	</el-row>
	
</template>
<script>
export default {
  name: 'Test',
  data () {
    return {
      img:""
    }
  },
  methods: {
  	startTest(){
  		this.$api.post('/startTest', null, r => {
  			r.data;
		});
  	},
  	picture(){
  		this.$api.post('/takePhono', null, r => {
  			this.img = r.data;
		});
  	},
  	pointCorrect(){
  		this.$api.post('/pointCorrect', null, r => {
		});
  	},
  	cameraMove(){
  		this.$api.post('/cameraMove', null, r => {
		});
  	},
  	finished(){
  		this.$api.post('/finished', null, r => {
		});
  	},
  	lightOpen(){
  		this.$api.post('/lightOpen', null, r => {
		});
  	},
  	lightClose(){
  		this.$api.post('/lightClose', null, r => {
		});
  	},
  	editImg(){
		this.$api.post('/editImg', null, r => {
  			r.data;
		});
	}
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.asd {
		margin-top: 10px;
		overflow: hidden;
	}
</style>
