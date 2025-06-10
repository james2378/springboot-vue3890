<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','hotel_name') || $check_field('add','hotel_name') || $check_field('set','hotel_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="酒店名称" prop="hotel_name">
					<el-input id="hotel_name" v-model="form['hotel_name']" placeholder="请输入酒店名称"
							  v-if="user_group === '管理员' || (form['hotel_information_id'] && $check_field('set','hotel_name')) || (!form['hotel_information_id'] && $check_field('add','hotel_name'))" :disabled="disabledObj['hotel_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','hotel_name')">{{form['hotel_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','hotel_telephone') || $check_field('add','hotel_telephone') || $check_field('set','hotel_telephone')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="酒店电话" prop="hotel_telephone">
					<el-input id="hotel_telephone" v-model="form['hotel_telephone']" placeholder="请输入酒店电话"
							  v-if="user_group === '管理员' || (form['hotel_information_id'] && $check_field('set','hotel_telephone')) || (!form['hotel_information_id'] && $check_field('add','hotel_telephone'))" :disabled="disabledObj['hotel_telephone_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','hotel_telephone')">{{form['hotel_telephone']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cover_photo_') || $check_field('add','cover_photo_') || $check_field('set','cover_photo_')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="封面图片" prop="cover_photo_">
					<el-upload :disabled="disabledObj['cover_photo__isDisabled']" id="cover_photo_" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover_photo_"
						:show-file-list="false" v-if="user_group === '管理员' || (form['hotel_information_id'] && $check_field('set','cover_photo_')) || (!form['hotel_information_id'] && $check_field('add','cover_photo_'))">
						<img v-if="form['cover_photo_']" :src="$fullUrl(form['cover_photo_'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover_photo_')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover_photo_'])" :preview-src-list="[$fullUrl(form['cover_photo_'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','nearby_scenic_spots') || $check_field('add','nearby_scenic_spots') || $check_field('set','nearby_scenic_spots')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="附近景点" prop="nearby_scenic_spots">
					<el-input id="nearby_scenic_spots" v-model="form['nearby_scenic_spots']" placeholder="请输入附近景点"
							  v-if="user_group === '管理员' || (form['hotel_information_id'] && $check_field('set','nearby_scenic_spots')) || (!form['hotel_information_id'] && $check_field('add','nearby_scenic_spots'))" :disabled="disabledObj['nearby_scenic_spots_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','nearby_scenic_spots')">{{form['nearby_scenic_spots']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','hotel_address') || $check_field('add','hotel_address') || $check_field('set','hotel_address')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="酒店地址" prop="hotel_address">
					<el-input id="hotel_address" v-model="form['hotel_address']" placeholder="请输入酒店地址"
							  v-if="user_group === '管理员' || (form['hotel_information_id'] && $check_field('set','hotel_address')) || (!form['hotel_information_id'] && $check_field('add','hotel_address'))" :disabled="disabledObj['hotel_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','hotel_address')">{{form['hotel_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','hotel_profile') || $check_field('add','hotel_profile') || $check_field('set','hotel_profile')" :xs="24" :sm="24" :lg="24">
				<el-form-item label="酒店简介" prop="hotel_profile">
					<quill-editor v-model.number="form['hotel_profile']"
						v-if="user_group === '管理员' || (form['hotel_information_id'] && $check_field('set','hotel_profile')) || (!form['hotel_information_id'] && $check_field('add','hotel_profile')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','hotel_profile')" v-html="form['hotel_profile']"></div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "hotel_information_id",
				url_add: "~/api/hotel_information/add?",
				url_set: "~/api/hotel_information/set?",
				url_get_obj: "~/api/hotel_information/get_obj?",
				url_upload: "~/api/hotel_information/upload?",

				query: {
					"hotel_information_id": 0,
				},

				form: {
					"hotel_name":'', // 酒店名称
					"hotel_telephone":'', // 酒店电话
					"cover_photo_":'', // 封面图片
					"nearby_scenic_spots":'', // 附近景点
					"hotel_address":'', // 酒店地址
					"hotel_profile":'', // 酒店简介
					"hotel_information_id": 0, // ID

				},
				disabledObj:{
					"hotel_name_isDisabled": false,
					"hotel_telephone_isDisabled": false,
					"cover_photo__isDisabled": false,
					"nearby_scenic_spots_isDisabled": false,
					"hotel_address_isDisabled": false,
					"hotel_profile_isDisabled": false,
				},

			}
		},
		methods: {
			/**
			 * 上传封面图片
			 * @param {Object} param图片参数
			 */
			upload_cover_photo_(param){
				this.uploadFile(param.file, "cover_photo_");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/hotel_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/hotel_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/hotel_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/hotel_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/hotel_information/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
