<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="开始日期">
              <a-input placeholder="请输入开始日期" v-model="queryParam.begindate"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="结束日期">
              <a-input placeholder="请输入结束日期" v-model="queryParam.enddate"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="累计用户数">
              <a-input placeholder="请输入累计用户数" v-model="queryParam.cumulateuser"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="数据的日期">
              <a-input placeholder="请输入数据的日期" v-model="queryParam.refdate"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="记录生成时间">
              <a-input placeholder="请输入记录生成时间" v-model="queryParam.createdate"></a-input>
            </a-form-item>
          </a-col>
        </template>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('微信公众号关注人数')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <usercumulate-modal ref="modalForm" @ok="modalFormOk"></usercumulate-modal>
  </a-card>
</template>

<script>
  import UsercumulateModal from './modules/UsercumulateModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "UsercumulateList",
    mixins:[JeecgListMixin],
    components: {
      UsercumulateModal
    },
    data () {
      return {
        description: '微信公众号关注人数管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
           },
		   {
            title: '开始日期',
            align:"center",
            dataIndex: 'begindate'
           },
		   {
            title: '结束日期',
            align:"center",
            dataIndex: 'enddate'
           },
		   {
            title: '累计用户数',
            align:"center",
            dataIndex: 'cumulateuser'
           },
		   {
            title: '数据的日期',
            align:"center",
            dataIndex: 'refdate'
           },
		   {
            title: '记录生成时间',
            align:"center",
            dataIndex: 'createdate'
           },
		   {
            title: '记录更新时间',
            align:"center",
            dataIndex: 'datelastupdate'
           },
		   {
            title: '公众号名称',
            align:"center",
            dataIndex: 'gzhname'
           },
		   {
            title: 'projectname',
            align:"center",
            dataIndex: 'projectname'
           },
		   {
            title: '删除标识0-正常,1-已删除',
            align:"center",
            dataIndex: 'delFlag'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/trade/usercumulate/list",
          delete: "/trade/usercumulate/delete",
          deleteBatch: "/trade/usercumulate/deleteBatch",
          exportXlsUrl: "trade/usercumulate/exportXls",
          importExcelUrl: "trade/usercumulate/importExcel",
       },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {
     
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>