<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','name_of_scenic_spot') || $check_field('add','name_of_scenic_spot') || $check_field('set','name_of_scenic_spot')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="景点名称" prop="name_of_scenic_spot">
					<el-input id="name_of_scenic_spot" v-model="form['name_of_scenic_spot']" placeholder="请输入景点名称"
							  v-if="user_group === '管理员' || (form['route_application_id'] && $check_field('set','name_of_scenic_spot')) || (!form['route_application_id'] && $check_field('add','name_of_scenic_spot'))" :disabled="disabledObj['name_of_scenic_spot_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_scenic_spot')">{{form['name_of_scenic_spot']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','applicant') || $check_field('add','applicant') || $check_field('set','applicant')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="申请人" prop="applicant">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_applicant(form['applicant']) }}
							<!--<el-input id="business_name" v-model="form['applicant']" placeholder="请输入申请人"-->
							<!--v-if="user_group === '管理员' || (form['route_application_id'] && $check_field('set','applicant')) || (!form['route_application_id'] && $check_field('add','applicant'))" :disabled="disabledObj['applicant_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','applicant')">{{form['applicant']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['route_application_id'] && $check_field('set','applicant')) || (!form['route_application_id'] && $check_field('add','applicant'))" id="applicant" v-model="form['applicant']" :disabled="disabledObj['applicant_isDisabled']">
								<el-option v-for="o in list_user_applicant" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','applicant')" id="applicant" v-model="form['applicant']" :disabled="true">
								<el-option v-for="o in list_user_applicant" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="applicant" v-model="form['applicant']" :disabled="disabledObj['applicant_isDisabled']">
							<el-option v-for="o in list_user_applicant" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['route_application_id'] && $check_field('set','full_name')) || (!form['route_application_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_number') || $check_field('add','contact_number') || $check_field('set','contact_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系电话" prop="contact_number">
					<el-input id="contact_number" v-model="form['contact_number']" placeholder="请输入联系电话" type="tel"
						v-if="user_group === '管理员' || (form['route_application_id'] && $check_field('set','contact_number')) || (!form['route_application_id'] && $check_field('add','contact_number'))">
					</el-input>
					<div v-else-if="$check_field('get','contact_number')">{{form['contact_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','approval_status') || $check_field('add','approval_status') || $check_field('set','approval_status')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="审批状态" prop="approval_status">
					<el-select id="approval_status" v-model="form['approval_status']"
						v-if="user_group === '管理员' || (form['route_application_id'] && $check_field('set','approval_status')) || (!form['route_application_id'] && $check_field('add','approval_status'))">
						<el-option v-for="o in list_approval_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','approval_status')">{{form['approval_status']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','route_information') || $check_field('add','route_information') || $check_field('set','route_information')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="路线信息" prop="route_information">
					<el-input type="textarea" id="route_information" v-model="form['route_information']" placeholder="请输入路线信息"
						v-if="user_group === '管理员' || (form['route_application_id'] && $check_field('set','route_information')) || (!form['route_application_id'] && $check_field('add','route_information'))" :disabled="disabledObj['route_information_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','route_information')">{{form['route_information']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','reply_message') || $check_field('add','reply_message') || $check_field('set','reply_message')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="回复信息" prop="reply_message">
					<el-input type="textarea" id="reply_message" v-model="form['reply_message']" placeholder="请输入回复信息"
						v-if="user_group === '管理员' || (form['route_application_id'] && $check_field('set','reply_message')) || (!form['route_application_id'] && $check_field('add','reply_message'))" :disabled="disabledObj['reply_message_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','reply_message')">{{form['reply_message']}}</div>
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
				field: "route_application_id",
				url_add: "~/api/route_application/add?",
				url_set: "~/api/route_application/set?",
				url_get_obj: "~/api/route_application/get_obj?",
				url_upload: "~/api/route_application/upload?",

				query: {
					"route_application_id": 0,
				},

				form: {
					"name_of_scenic_spot":'', // 景点名称
					"applicant": 0, // 申请人
					"full_name":'', // 姓名
					"contact_number":'', // 联系电话
					"approval_status":'', // 审批状态
					"route_information":'', // 路线信息
					"reply_message":'', // 回复信息
					"route_application_id": 0, // ID

				},
				disabledObj:{
					"name_of_scenic_spot_isDisabled": false,
					"applicant_isDisabled": false,
					"full_name_isDisabled": false,
					"contact_number_isDisabled": false,
					"approval_status_isDisabled": false,
					"route_information_isDisabled": false,
					"reply_message_isDisabled": false,
				},
				// 用户列表
				list_user_applicant: [],
				// 用户组
				group_user_applicant: "",
				//审批状态选项列表
				list_approval_status: ['未通过','已通过'],

			}
		},
		methods: {
			/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_applicant() {
                // if(this.user_group !== "管理员" && this.form["applicant"] === 0) {
                //     this.form["applicant"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=普通用户");
                if(json.result && json.result.list){
                    this.list_user_applicant = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取普通用户用户组
			 */
			async get_group_user_applicant() {
				this.form["applicant"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					this.group_user_applicant = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_applicant(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_applicant.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="applicant") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_applicant(id){
				var obj = this.list_user_applicant.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "申请人":
							if(param["applicant"] > 0){
								param["applicant"] = this.user.user_id;
							}
							break;
					}
				}
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
					bl = this.$check_action('/route_application/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/route_application/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/route_application/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/route_application/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/route_application/view','get');
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
			this.get_list_user_applicant();
			this.get_group_user_applicant();
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
