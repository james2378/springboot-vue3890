<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="景点资讯"
				source_table="article"
			  ></list_result_search>
				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="在线留言"
				source_table="forum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/ordinary_users/list', 'get')"
				:list="result_ordinary_users_full_name"
				title="普通用户姓名"
				source_table="ordinary_users"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/ordinary_users/list', 'get')"
				:list="result_ordinary_users_gender"
				title="普通用户性别"
				source_table="ordinary_users"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/scenic_spot_information/list', 'get')"
				:list="result_scenic_spot_information_name_of_scenic_spot"
				title="景点信息景点名称"
				source_table="scenic_spot_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/scenic_spot_information/list', 'get')"
				:list="result_scenic_spot_information_scenic_spot_type"
				title="景点信息景点类型"
				source_table="scenic_spot_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/type_management/list', 'get')"
				:list="result_type_management_scenic_spot_type"
				title="类型管理景点类型"
				source_table="type_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/hotel_information/list', 'get')"
				:list="result_hotel_information_hotel_name"
				title="酒店信息酒店名称"
				source_table="hotel_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/route_application/list', 'get')"
				:list="result_route_application_name_of_scenic_spot"
				title="路线申请景点名称"
				source_table="route_application"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/route_application/list', 'get')"
				:list="result_route_application_approval_status"
				title="路线申请审批状态"
				source_table="route_application"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
			"result_ordinary_users_full_name":[],
			"result_ordinary_users_gender":[],
			"result_scenic_spot_information_name_of_scenic_spot":[],
			"result_scenic_spot_information_scenic_spot_type":[],
			"result_type_management_scenic_spot_type":[],
			"result_hotel_information_hotel_name":[],
			"result_route_application_name_of_scenic_spot":[],
			"result_route_application_approval_status":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取在线留言
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},
	/**
	 * 获取full_name
	 */
	get_ordinary_users_full_name(){
		this.$get("~/api/ordinary_users/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_users_full_name = json.result.list;
			result_ordinary_users_full_name.map(o => o.title = o['full_name'])
	  			this.result_ordinary_users_full_name = result_ordinary_users_full_name
		 	}
		});
	},
	/**
	 * 获取gender
	 */
	get_ordinary_users_gender(){
		this.$get("~/api/ordinary_users/get_list?like=0", { page: 1, size: 10, "gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_users_gender = json.result.list;
			result_ordinary_users_gender.map(o => o.title = o['gender'])
	  			this.result_ordinary_users_gender = result_ordinary_users_gender
		 	}
		});
	},
	/**
	 * 获取name_of_scenic_spot
	 */
	get_scenic_spot_information_name_of_scenic_spot(){
		this.$get("~/api/scenic_spot_information/get_list?like=0", { page: 1, size: 10, "name_of_scenic_spot": this.query.word }, (json) => {
		  if (json.result) {
			var result_scenic_spot_information_name_of_scenic_spot = json.result.list;
			result_scenic_spot_information_name_of_scenic_spot.map(o => o.title = o['name_of_scenic_spot'])
	  			this.result_scenic_spot_information_name_of_scenic_spot = result_scenic_spot_information_name_of_scenic_spot
		 	}
		});
	},
	/**
	 * 获取scenic_spot_type
	 */
	get_scenic_spot_information_scenic_spot_type(){
		this.$get("~/api/scenic_spot_information/get_list?like=0", { page: 1, size: 10, "scenic_spot_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_scenic_spot_information_scenic_spot_type = json.result.list;
			result_scenic_spot_information_scenic_spot_type.map(o => o.title = o['scenic_spot_type'])
	  			this.result_scenic_spot_information_scenic_spot_type = result_scenic_spot_information_scenic_spot_type
		 	}
		});
	},
	/**
	 * 获取scenic_spot_type
	 */
	get_type_management_scenic_spot_type(){
		this.$get("~/api/type_management/get_list?like=0", { page: 1, size: 10, "scenic_spot_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_type_management_scenic_spot_type = json.result.list;
			result_type_management_scenic_spot_type.map(o => o.title = o['scenic_spot_type'])
	  			this.result_type_management_scenic_spot_type = result_type_management_scenic_spot_type
		 	}
		});
	},
	/**
	 * 获取hotel_name
	 */
	get_hotel_information_hotel_name(){
		this.$get("~/api/hotel_information/get_list?like=0", { page: 1, size: 10, "hotel_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_hotel_information_hotel_name = json.result.list;
			result_hotel_information_hotel_name.map(o => o.title = o['hotel_name'])
	  			this.result_hotel_information_hotel_name = result_hotel_information_hotel_name
		 	}
		});
	},
	/**
	 * 获取name_of_scenic_spot
	 */
	get_route_application_name_of_scenic_spot(){
		this.$get("~/api/route_application/get_list?like=0", { page: 1, size: 10, "name_of_scenic_spot": this.query.word }, (json) => {
		  if (json.result) {
			var result_route_application_name_of_scenic_spot = json.result.list;
			result_route_application_name_of_scenic_spot.map(o => o.title = o['name_of_scenic_spot'])
	  			this.result_route_application_name_of_scenic_spot = result_route_application_name_of_scenic_spot
		 	}
		});
	},
	/**
	 * 获取approval_status
	 */
	get_route_application_approval_status(){
		this.$get("~/api/route_application/get_list?like=0", { page: 1, size: 10, "approval_status": this.query.word }, (json) => {
		  if (json.result) {
			var result_route_application_approval_status = json.result.list;
			result_route_application_approval_status.map(o => o.title = o['approval_status'])
	  			this.result_route_application_approval_status = result_route_application_approval_status
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
		this.get_ordinary_users_full_name();
		this.get_ordinary_users_gender();
		this.get_scenic_spot_information_name_of_scenic_spot();
		this.get_scenic_spot_information_scenic_spot_type();
		this.get_type_management_scenic_spot_type();
		this.get_hotel_information_hotel_name();
		this.get_route_application_name_of_scenic_spot();
		this.get_route_application_approval_status();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
	  this.get_ordinary_users_full_name();
	  this.get_ordinary_users_gender();
	  this.get_scenic_spot_information_name_of_scenic_spot();
	  this.get_scenic_spot_information_scenic_spot_type();
	  this.get_type_management_scenic_spot_type();
	  this.get_hotel_information_hotel_name();
	  this.get_route_application_name_of_scenic_spot();
	  this.get_route_application_approval_status();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
