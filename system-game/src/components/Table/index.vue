<template>
  <div>
  <el-table ref="table" :data="list" v-loading.body="listLoading" element-loading-text="加载" @row-click="handleRowChange" @selection-change="selsChange" border fit highlight-current-row >
    <el-table-column type="selection">
    </el-table-column>
    <el-table-column v-for="(column, index) in columns" :key="column.value" :label="column.text" :width="column.width">
      <template slot-scope="scope">
        {{scope.row[column.value]}}
      </template>
    </el-table-column>
    <el-table-column v-if="controlColumn" label="操作" align="center">
      <template slot-scope="scope">
        <el-button v-if="editFun" type="primary" size="small" icon="el-icon-edit" @click="editData(scope.row.id)" >编缉</el-button>
        <el-button v-if="delFun" type="danger" size="small" icon="el-icon-delete" @click="delData(scope.row.id)" >删除</el-button>
        <slot name="Button" :row="scope.row" slot-scope="button"></slot>
      </template>
    </el-table-column>
  </el-table>
  <div class="pagination-container">
    <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pagination.curPage" :page-sizes="[10,20,30,50]" :page-size="pagination.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="pagination.totalRow">
    </el-pagination>
  </div>
  </div>
</template>

<script>
    export default {
      name: 'tableModel',
      props: {
        pageFun: { // 获取分页的方法
          type: Function,
          default: null
        },
        delFun: { // 删除方法
          type: Function,
          default: null
        },
        editFun: { // 编辑方法
          type: Function,
          default: null
        },
        columns: { // 分行
          type: Array,
          default: () => []
        },
        controlColumn: { // 操作列
          type: Boolean,
          default: false
        }
      },
      data() {
        return {
          pagination: {
            curPage: 1,
            pageSize: 10,
            totalRow: 10
          },
          list: [],
          sels: [],
          listLoading: true
        }
      },
      created() {
        this.getPage(this.pagination.curPage, this.pagination.pageSize)
      },
      methods: {
        getPage() {
          this.listLoading = true
          this.pageFun(this.pagination.curPage, { 'pageSize': this.pagination.pageSize }).then(response => {
            this.list = response.data.list
            this.pagination.curPage = response.data.pageNumber
            this.pagination.pageSize = response.data.pageSize
            this.pagination.totalRow = response.data.totalRow
            this.listLoading = false
          })
        },
        handleSizeChange(val) {
          this.pagination.pageSize = val
          this.getPage()
        },
        handleCurrentChange(val) {
          this.pagination.curPage = val
          this.getPage()
        },
        handleRowChange(row, event, column) {
          this.$refs.table.toggleRowSelection(row)
        },
        selsChange(sels) {
          this.sels = sels
        },
        delData(id) {
          const other = this
          this.$confirm('确认删除该数据？', { type: 'warning', confirmButtonText: '删除', cancelButtonText: '取消' })
            .then(_ => {
              this.delFun(id).then(response => {
                if (response.code === 0) {
                  other.$message.success(response.msg)
                  other.getPage()
                } else {
                  other.$message.info(response.msg)
                }
              })
            })
            .catch(_ => {})
        },
        editData(id) {
          this.editFun(id)
        }
      }
    }
</script>
