<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','name_of_scenic_spot') || $check_field('add','name_of_scenic_spot') || $check_field('set','name_of_scenic_spot')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="景点名称" prop="name_of_scenic_spot">
					<el-input id="name_of_scenic_spot" v-model="form['name_of_scenic_spot']" placeholder="请输入景点名称"
							  v-if="user_group === '管理员' || (form['scenic_spot_information_id'] && $check_field('set','name_of_scenic_spot')) || (!form['scenic_spot_information_id'] && $check_field('add','name_of_scenic_spot'))" :disabled="disabledObj['name_of_scenic_spot_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_scenic_spot')">{{form['name_of_scenic_spot']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','scenic_spot_type') || $check_field('add','scenic_spot_type') || $check_field('set','scenic_spot_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="景点类型" prop="scenic_spot_type">
					<el-select id="scenic_spot_type" v-model="form['scenic_spot_type']"
						v-if="user_group === '管理员' || (form['scenic_spot_information_id'] && $check_field('set','scenic_spot_type')) || (!form['scenic_spot_information_id'] && $check_field('add','scenic_spot_type'))">
						<el-option v-for="o in list_scenic_spot_type" :key="o['scenic_spot_type']" :label="o['scenic_spot_type']"
							:value="o['scenic_spot_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','scenic_spot_type')">{{form['scenic_spot_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','opening_hours') || $check_field('add','opening_hours') || $check_field('set','opening_hours')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="开放时间" prop="opening_hours">
					<el-input id="opening_hours" v-model="form['opening_hours']" placeholder="请输入开放时间"
							  v-if="user_group === '管理员' || (form['scenic_spot_information_id'] && $check_field('set','opening_hours')) || (!form['scenic_spot_information_id'] && $check_field('add','opening_hours'))" :disabled="disabledObj['opening_hours_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','opening_hours')">{{form['opening_hours']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','scenic_spot_telephone') || $check_field('add','scenic_spot_telephone') || $check_field('set','scenic_spot_telephone')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="景点电话" prop="scenic_spot_telephone">
					<el-input id="scenic_spot_telephone" v-model="form['scenic_spot_telephone']" placeholder="请输入景点电话"
							  v-if="user_group === '管理员' || (form['scenic_spot_information_id'] && $check_field('set','scenic_spot_telephone')) || (!form['scenic_spot_information_id'] && $check_field('add','scenic_spot_telephone'))" :disabled="disabledObj['scenic_spot_telephone_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','scenic_spot_telephone')">{{form['scenic_spot_telephone']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','scenic_spot_address') || $check_field('add','scenic_spot_address') || $check_field('set','scenic_spot_address')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="景点地址" prop="scenic_spot_address">
					<el-input id="scenic_spot_address" v-model="form['scenic_spot_address']" placeholder="请输入景点地址"
							  v-if="user_group === '管理员' || (form['scenic_spot_information_id'] && $check_field('set','scenic_spot_address')) || (!form['scenic_spot_information_id'] && $check_field('add','scenic_spot_address'))" :disabled="disabledObj['scenic_spot_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','scenic_spot_address')">{{form['scenic_spot_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cover_photo_') || $check_field('add','cover_photo_') || $check_field('set','cover_photo_')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="封面图片" prop="cover_photo_">
					<el-upload :disabled="disabledObj['cover_photo__isDisabled']" id="cover_photo_" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover_photo_"
						:show-file-list="false" v-if="user_group === '管理员' || (form['scenic_spot_information_id'] && $check_field('set','cover_photo_')) || (!form['scenic_spot_information_id'] && $check_field('add','cover_photo_'))">
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
			<el-col v-if="user_group === '管理员' || $check_field('get','recommended_hotel') || $check_field('add','recommended_hotel') || $check_field('set','recommended_hotel')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="推荐酒店" prop="recommended_hotel">
					<el-input id="recommended_hotel" v-model="form['recommended_hotel']" placeholder="请输入推荐酒店"
							  v-if="user_group === '管理员' || (form['scenic_spot_information_id'] && $check_field('set','recommended_hotel')) || (!form['scenic_spot_information_id'] && $check_field('add','recommended_hotel'))" :disabled="disabledObj['recommended_hotel_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','recommended_hotel')">{{form['recommended_hotel']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','route_recommendation') || $check_field('add','route_recommendation') || $check_field('set','route_recommendation')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="路线推荐" prop="route_recommendation">
					<el-input type="textarea" id="route_recommendation" v-model="form['route_recommendation']" placeholder="请输入路线推荐"
						v-if="user_group === '管理员' || (form['scenic_spot_information_id'] && $check_field('set','route_recommendation')) || (!form['scenic_spot_information_id'] && $check_field('add','route_recommendation'))" :disabled="disabledObj['route_recommendation_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','route_recommendation')">{{form['route_recommendation']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','introduction_to_scenic_spots') || $check_field('add','introduction_to_scenic_spots') || $check_field('set','introduction_to_scenic_spots')" :xs="24" :sm="24" :lg="24">
				<el-form-item label="景点简介" prop="introduction_to_scenic_spots">
					<quill-editor v-model.number="form['introduction_to_scenic_spots']"
						v-if="user_group === '管理员' || (form['scenic_spot_information_id'] && $check_field('set','introduction_to_scenic_spots')) || (!form['scenic_spot_information_id'] && $check_field('add','introduction_to_scenic_spots')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','introduction_to_scenic_spots')" v-html="form['introduction_to_scenic_spots']"></div>
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
				field: "scenic_spot_information_id",
				url_add: "~/api/scenic_spot_information/add?",
				url_set: "~/api/scenic_spot_information/set?",
				url_get_obj: "~/api/scenic_spot_information/get_obj?",
				url_upload: "~/api/scenic_spot_information/upload?",

				query: {
					"scenic_spot_information_id": 0,
				},

				form: {
					"name_of_scenic_spot":'', // 景点名称
					"scenic_spot_type":'', // 景点类型
					"opening_hours":'', // 开放时间
					"scenic_spot_telephone":'', // 景点电话
					"scenic_spot_address":'', // 景点地址
					"cover_photo_":'', // 封面图片
					"recommended_hotel":'', // 推荐酒店
					"route_recommendation":'', // 路线推荐
					"introduction_to_scenic_spots":'', // 景点简介
					"scenic_spot_information_id": 0, // ID

				},
				disabledObj:{
					"name_of_scenic_spot_isDisabled": false,
					"scenic_spot_type_isDisabled": false,
					"opening_hours_isDisabled": false,
					"scenic_spot_telephone_isDisabled": false,
					"scenic_spot_address_isDisabled": false,
					"cover_photo__isDisabled": false,
					"recommended_hotel_isDisabled": false,
					"route_recommendation_isDisabled": false,
					"introduction_to_scenic_spots_isDisabled": false,
				},
				//景点类型选项列表
				list_scenic_spot_type: [],

			}
		},
		methods: {
			/**
			 * 获取景点类型列表
			 */
			async get_list_scenic_spot_type() {
				var json = await this.$get("~/api/type_management/get_list?");
				if(json.result && json.result.list){
					this.list_scenic_spot_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
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
					bl = this.$check_action('/scenic_spot_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/scenic_spot_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/scenic_spot_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/scenic_spot_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/scenic_spot_information/view','get');
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
			this.get_list_scenic_spot_type();
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
