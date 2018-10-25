<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input placeholder="角色ID" v-model="params.roleId" style="width: 200px;" class="filter-item"/>
      <el-input placeholder="角色名称" v-model="params.roleName" style="width: 200px;" class="filter-item"/>
      <el-input placeholder="用户ID" v-model="params.userId" style="width: 200px;" class="filter-item"/>
      <el-input placeholder="帐号" v-model="params.account" style="width: 200px;" class="filter-item"/>
      <el-select v-model="params.service" placeholder="服务端" clearable style="width: 130px" class="filter-item">
        <el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item"/>
      </el-select>
      <el-date-picker v-model="params.date" style="vertical-align: middle;margin-bottom: 10px;" type="datetimerange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期">
      </el-date-picker>
      <el-button  class="filter-item" type="primary" icon="el-icon-search" @click="handleSearch">{{buttons.serach}}</el-button>
    </div>
    <table-model ref="table" :pageFun="getPage" :paging="false" :sortKey="'logtime'" :controlColumn="true" :isLoad="false" :columns="columns">
      <template slot="Button" slot-scope="button">
          <el-button type="primary" size="small"  @click="detailLog(button.row)" >{{buttons.detail}}</el-button>
      </template>
    </table-model>

    <el-dialog fullscreen="true" title="日志详情" :visible.sync="dialogFormVisible">
      <component :is="tableComponent" :search="false" :isLoad="true" :params="params"></component>
    </el-dialog>
  </div>
</template>

<style>
  .form .form-left {
    width:50%; padding-left:1rem; padding-right:1rem; padding-top:1rem;
  }
  .form .form-right {
    width:50%; padding-left:1rem; padding-right:1rem; padding-top:1rem;
  }
</style>
<script>
  import tableModel from '@/components/Table'
  import { getPersonList } from '@/api/log'
  import { isEmpty, formatDate } from '@/utils'
  import rolelogin from '@/views/loginlog'
  import addcash from '@/views/addcash'
  import addyuanbao from '@/views/addyuanbao'
  import chardata from '@/views/chardata'
  import chatlog from '@/views/chatlog'
  import costyuanbao from '@/views/costyuanbao'
  import levelup from '@/views/levelup'
  import endtarena from '@/views/endtarena'
  import endtask from '@/views/endtask'
  import exchange from '@/views/exchange'
  import gainitem from '@/views/gainitem'
  import jiazurank from '@/views/jiazurank'
  import welfare from '@/views/welfare'
  import loseitem from '@/views/loseitem'
  import rank from '@/views/rank'
  import createrole from '@/views/rolelog'
  import shoptrade from '@/views/shoptrade'
  import startarena from '@/views/startarena'
  import starttask from '@/views/starttask'
  import rolelogout from '@/views/loginout'
  import recruit from '@/views/recruit'
  import startinstance from '@/views/startinstance'
  import endinstance from '@/views/startinstance'

  export default {
    components: {
      tableModel,
      rolelogin,
      addcash,
      addyuanbao,
      chardata,
      chatlog,
      costyuanbao,
      levelup,
      endtarena,
      endtask,
      exchange,
      gainitem,
      jiazurank,
      welfare,
      loseitem,
      rank,
      createrole,
      shoptrade,
      startarena,
      starttask,
      rolelogout,
      recruit,
      startinstance,
      endinstance
    },
    data() {
      return {
        dialogFormVisible: false,
        logData: null,
        tableComponent: '',
        buttons: {
          detail: '详情',
          serach: '搜索'
        },
        importanceOptions: [1, 2, 3],
        params: {
          service: '',
          date: null,
          roleId: '',
          roleName: '',
          userId: '',
          account: ''
        },
        columns: [
          {
            text: '日志类型',
            value: 'logname'
          },
          {
            text: '日志总数',
            value: 'logcount'
          }
        ]
      }
    },
    methods: {
      getPage() {
        const params = {}
        if (this.params.date !== null) {
          params.startDate = formatDate(this.params.date[0], 'yyyy-MM-dd hh:mm:ss')
          params.endDate = formatDate(this.params.date[1], 'yyyy-MM-dd hh:mm:ss')
        }
        params.service = this.params.service
        params.roleId = this.params.roleId
        params.roleName = this.params.roleName
        params.userId = this.params.userId
        params.account = this.params.account
        return new Promise((resolve, reject) => {
          getPersonList(params).then(response => {
            resolve(response)
          }).catch(error => {
            reject(error)
          })
        })
      },
      handleSearch() {
        if (isEmpty(this.params.date)) {
          this.$message.warning('请选择时间范围')
          return false
        }
        if (isEmpty(this.params.service) && isEmpty(this.params.roleId) && isEmpty(this.params.roleName) && isEmpty(this.params.userId) && isEmpty(this.params.account)) {
          this.$message.warning('请选择搜索条件')
          return false
        }
        this.$refs.table.getPage(1, 10)
      },
      detailLog(data) {
        this.tableComponent = data.logname
        this.logData = data
        this.dialogFormVisible = true
        console.log(data)
      }
    }

  }
</script>

<style scoped>

</style>
