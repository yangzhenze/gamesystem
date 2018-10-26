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
    <table-model ref="table" :pageFun="getPage" :sortKey="'logtime'" :isLoad="false" :columns="columns">
      <template slot="Button" slot-scope="button">
          <el-button type="primary" size="small"  @click="detailLog(button.row)" >{{buttons.detail}}</el-button>
      </template>
    </table-model>

    <el-dialog title="日志详情" :visible.sync="dialogFormVisible">
      <el-form v-for="logs in logData" :inline="true"  class="demo-form-inline">
        <el-form-item v-for="log in logs" :label="log.key">
        </el-form-item>
      </el-form>
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
            text: '角色ID',
            value: 'roleid'
          },
          {
            text: '角色名称',
            value: 'rolename'
          },
          {
            text: '用户ID',
            value: 'userid'
          },
          {
            text: '帐号',
            value: 'account'
          }
        ]
      }
    },
    methods: {
      getPage(curPage, pageSize) {
        const params = {}
        if (this.params.date !== null) {
          params.startDate = formatDate(this.params.date[0], 'yyyy-MM-dd hh:mm:ss')
          params.endDate = formatDate(this.params.date[1], 'yyyy-MM-dd hh:mm:ss')
        }
        params.service = this.params.service
        params.pageSize = pageSize
        params.roleId = this.params.roleId
        params.roleName = this.params.roleName
        params.userId = this.params.userId
        params.account = this.params.account
        return new Promise((resolve, reject) => {
          getPersonList(curPage, params).then(response => {
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
        this.logData = this.logDetail(this.tableComponent, data)
        this.dialogFormVisible = true
      },
      logDetail(logname, data) {
        let tempData = {}
        switch (logname) {
          case 'rolelogin':
            debugger
            tempData = {
              '记录时间': data.logtime,
              '角色ID': data.roleid,
              '角色名称': data.rolename,
              '用户ID': data.userid,
              '帐号': data.account,
              '等级': data.params6,
              '平台': data.params5,
              '操作系统': data.params1,
              'ip地址': data.params2,
              '总金额': data.params3,
              '服务器': data.serverid
            }
            break
          case 'addcash':
            break
          case 'addyuanbao':
            break
          case 'chardata':
            break
          case 'chatlog':
            break
          case 'costyuanbao':
            break
          case 'levelup':
            break
          case 'endtarena':
            break
          case 'endtask':
            break
          case 'exchange':
            break
          case 'gainitem':
            break
          case 'jiazurank':
            break
          case 'welfare':
            break
          case 'loseitem':
            break
          case 'rank':
            break
          case 'createrole':
            break
          case 'shoptrade':
            break
          case 'startarena':
            break
          case 'starttask':
            break
          case 'rolelogout':
            break
          case 'recruit':
            break
          case 'startinstance':
            break
          case 'endinstance':
            break
        }
        const dataArray = []
        const tempKey = Object.keys(tempData)
        for (let i = 0; i < tempKey.length.length; i++) {
          const tempArray = []
          const t = {}
          t.key = tempKey[i]
          t.value = tempData[tempKey[i]]
          tempArray.push(t)
          if (i < tempKey.length) {
            const s = {}
            ++i
            s.key = tempKey[i]
            s.value = tempData[tempKey[i]]
            tempArray.push(s)
          }
          dataArray.push(tempArray)
        }

        return dataArray
      }
    }

  }
</script>

<style scoped>

</style>
