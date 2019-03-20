<template>
	<el-row class="asd">
		<table style="width: 99%;height: 100%;border: 0px solid red;" cellspacing="0" cellpadding="0">
			<!--<tr>
				<td style="text-align: left;"><el-button @click="editImg">框图</el-button></td>
				<td></td>
			</tr>-->
			<tr>
				<td style="height: 600px;width: 50%;border: 0px solid gold;">
					<table>
						<tr>
							<td style="height: 600px;">
								<img style="width: 600px;" :src="img">

								</img>
							</td>
						</tr>
						<tr>
							<td style="text-align: left;"><el-button @click="execl">报表</el-button></td>
						</tr>
					</table>
					
				</td>
				<td style="height: 500px;width:49%;border: 0px solid gray;">
					<div style="height: 350px;border: 0px solid green;">
						<el-table :data="tableData" class="el-table-class" :height="tabelHeight" style="width: 100%;">
							<el-table-column prop="deviceName" label="元件名称" align="center">
							</el-table-column>
							<el-table-column prop="deviceNo" label="元件编号">
							</el-table-column>
							<el-table-column prop="failType" label="故障类型">
							</el-table-column>
							<el-table-column prop="time" label="校验时间">
							</el-table-column>
							<el-table-column prop="result" label="校验结果">
							</el-table-column>
							<el-table-column  label="正确图片">
								 <template slot-scope="scope">
				                    <img :src="scope.row.nomarlUrl" width="100" class="head_pic"/>
				                </template>
							</el-table-column>
							<el-table-column  label="错误图片">
								 <template slot-scope="scope">
				                    <img :src="scope.row.errorUrl" width="100" class="head_pic"/>
				                </template>
							</el-table-column>
						</el-table>
					</div>
					<div id="console_div" style="height: 300px;">
						
						<table>
							<tr>
								<td>
									<img style="height: 300px;" :src="img1">

					    </img>
								</td>
								<td>
									<img style="height: 300px;" :src="img2">

					    </img>
								</td>
							</tr>
						</table>
					</div>
					<div id="mousewindow" style="display: none;position: absolute; height: 70px;width: 60px;">
						<ul id="consoleul">
							<li class="el-icon-document" onclick="copytext()">&nbsp;复制</li>
							<li class="el-icon-delete" onclick="cleartext()">&nbsp;清空</li>
						</ul>
					</div>
				</td>
			</tr>
		</table>

	</el-row>

</template>
<script>
	export default {
		name: 'HelloWorld',
		data() {
			return {
				img: "",
				img1: "",
				img2: "",
				tableData: [],
				tabelHeight: 400
			}
		},
		created() {
//			this.getData();
//			this.getImage();
			this.$api.initWebsocket(r => {
				this.gotoLinkManager(r);
			});
		},
		methods: {
			gotoLinkManager(data) {
				if(data.msgType == 'IMG_MSG'){
					this.img2 = this.img1;
					this.img1 = this.img;
					this.img = data.data;
				}else if(data.msgType == 'RESULT_MSG'){
					this.getData();
				}
			},
			getData() {
				this.$api.get('/getData', null, r => {
					this.tableData = r.data;
				});
			},
			getImage(){
				this.$api.get('/takePhono',null,r => {
					this.img = r.data;
					console.log(r.data);
				});
			},
			execl(){
				this.$api.downloadTemplate({
					"a":"a"
				},'/exportData');
			}
		}
	}
	window.onload = function() { //用window的onload事件，窗体加载完毕的时候
		$("#console_div").unbind("mousedown").bind("mousedown", function(event) {
			console.log(event);
			if(event.which == 3) { //控制台鼠标右键处理方法
				mousedownClick(event);
			} else {
				$("#mousewindow").css("display", "none");
			}
		});
	}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	.asd {
		margin-top: 10px;
		overflow: hidden;
	}
	
	#consoleul {
		border: 1px solid grey;
		list-style-type: none;
		-webkit-padding-start: 0px;
		border-radius: 2px;
		background-color: white;
	}
	
	#consoleul li {
		padding: 6px 8px;
		font-size: 12px;
	}
	
	#consoleul li:hover {
		background: rgb(244, 248, 249);
		cursor: pointer;
	}
</style>