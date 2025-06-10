<template>
	<div class="diy_edit page_route_application" id="route_application_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
					<div v-if="$check_field('set','name_of_scenic_spot') || $check_field('add','name_of_scenic_spot') || $check_field('get','name_of_scenic_spot')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>景点名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_name_of_scenic_spot" v-model="form['name_of_scenic_spot']" placeholder="请输入景点名称" v-if="(form['name_of_scenic_spot'] && $check_field('set','name_of_scenic_spot')) || (!form['name_of_scenic_spot'] && $check_field('add','name_of_scenic_spot'))"  :disabled="disabledObj['name_of_scenic_spot_isDisabled']"/>
							<span v-else-if="$check_field('get','name_of_scenic_spot')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','applicant') || $check_field('add','applicant') || $check_field('get','applicant')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>申请人:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_applicant" :disabled="disabledObj['applicant_isDisabled']" v-model="form['applicant']" v-if="(form['applicant'] && $check_field('set','applicant')) || (!form['applicant'] && $check_field('add','applicant'))" >
								<option v-for="o in list_user_applicant" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','applicant')">{{ form['applicant'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','full_name') || $check_field('add','full_name') || $check_field('get','full_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_full_name" v-model="form['full_name']" placeholder="请输入姓名" v-if="(form['full_name'] && $check_field('set','full_name')) || (!form['full_name'] && $check_field('add','full_name'))"  :disabled="disabledObj['full_name_isDisabled']"/>
							<span v-else-if="$check_field('get','full_name')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','contact_number') || $check_field('add','contact_number') || $check_field('get','contact_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>联系电话:
							</span>
						</div>
						<!-- 手机 -->
						<div class="diy_field diy_phone">
							<input type="tel" id="form_contact_number" v-model="form['contact_number']" placeholder="请输入联系电话" v-if="(form['contact_number'] && $check_field('set','contact_number')) || (!form['contact_number'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']" />
							<span v-else-if="$check_field('get','contact_number')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','approval_status') || $check_field('add','approval_status') || $check_field('get','approval_status')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>审批状态:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_approval_status" v-model="form['approval_status']" v-if="(form['approval_status'] && $check_field('set','approval_status')) || (!form['approval_status'] && $check_field('add','approval_status'))" >
								<option v-for="o in list_approval_status" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','approval_status')">{{ form['approval_status'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','route_information') || $check_field('add','route_information') || $check_field('get','route_information')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>路线信息:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_route_information" v-model="form['route_information']" v-if="(form['route_information'] && $check_field('set','route_information')) || (!form['route_information'] && $check_field('add','route_information'))" :disabled="disabledObj['route_information_isDisabled']" />
							<span v-else-if="$check_field('get','route_information')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','reply_message') || $check_field('add','reply_message') || $check_field('get','reply_message')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>回复信息:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_reply_message" v-model="form['reply_message']" v-if="(form['reply_message'] && $check_field('set','reply_message')) || (!form['reply_message'] && $check_field('add','reply_message'))" :disabled="disabledObj['reply_message_isDisabled']" />
							<span v-else-if="$check_field('get','reply_message')">{{ form['full_name'] }}</span>
						</div>
					</div>
				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/route_application/get_obj?",
				url_add: "~/api/route_application/add?",
				url_set: "~/api/route_application/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"name_of_scenic_spot": "",
					"applicant": 0,
					"full_name": "",
					"contact_number": "",
					"approval_status": "",
					"route_information": "",
					"reply_message": "",
					"route_application_id": 0,
				},

				obj: {
					"name_of_scenic_spot":'', // 景点名称
					"applicant": 0, // 申请人
					"full_name":'', // 姓名
					"contact_number":'', // 联系电话
					"approval_status":'', // 审批状态
					"route_information":'', // 路线信息
					"reply_message":'', // 回复信息
					"route_application_id": 0,
				},

				// 表单字段
				form: {
					"name_of_scenic_spot":'', // 景点名称
					"applicant": 0, // 申请人
					"full_name":'', // 姓名
					"contact_number":'', // 联系电话
					"approval_status":'', // 审批状态
					"route_information":'', // 路线信息
					"reply_message":'', // 回复信息
					"route_application_id": 0,

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
				//审批状态选项列表
				list_approval_status: ['未通过','已通过'],

				// ID字段
				field: "route_application_id",
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
			async get_user_session_applicant(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["applicant"] = user_id
								_this.disabledObj['applicant' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="applicant") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/route_application/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_applicant();
			this.get_list_user_applicant();
		}
	}
</script>

<style>
</style>
