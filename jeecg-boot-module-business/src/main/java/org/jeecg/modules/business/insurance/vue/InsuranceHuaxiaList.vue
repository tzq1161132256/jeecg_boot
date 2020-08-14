<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="保单号">
              <a-input placeholder="请输入保单号" v-model="queryParam.policyno"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="生效时间">
              <a-input placeholder="请输入生效时间" v-model="queryParam.effectivetime"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="有效性 ">
              <a-input placeholder="请输入有效性 " v-model="queryParam.policytype"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="生日">
              <a-input placeholder="请输入生日" v-model="queryParam.birth"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="ext1">
              <a-input placeholder="请输入ext1" v-model="queryParam.ext1"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('华夏保险')">导出</a-button>
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
    <insuranceHuaxia-modal ref="modalForm" @ok="modalFormOk"></insuranceHuaxia-modal>
  </a-card>
</template>

<script>
  import InsuranceHuaxiaModal from './modules/InsuranceHuaxiaModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "InsuranceHuaxiaList",
    mixins:[JeecgListMixin],
    components: {
      InsuranceHuaxiaModal
    },
    data () {
      return {
        description: '华夏保险管理页面',
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
            title: '保单号',
            align:"center",
            dataIndex: 'policyno'
           },
		   {
            title: '生效时间',
            align:"center",
            dataIndex: 'effectivetime'
           },
		   {
            title: '有效性 ',
            align:"center",
            dataIndex: 'policytype'
           },
		   {
            title: '生日',
            align:"center",
            dataIndex: 'birth'
           },
		   {
            title: 'ext1',
            align:"center",
            dataIndex: 'ext1'
           },
		   {
            title: 'ext2',
            align:"center",
            dataIndex: 'ext2'
           },
		   {
            title: 'ext3',
            align:"center",
            dataIndex: 'ext3'
           },
		   {
            title: 'ext4',
            align:"center",
            dataIndex: 'ext4'
           },
		   {
            title: 'ext5',
            align:"center",
            dataIndex: 'ext5'
           },
		   {
            title: 'ext6',
            align:"center",
            dataIndex: 'ext6'
           },
		   {
            title: 'ext7',
            align:"center",
            dataIndex: 'ext7'
           },
		   {
            title: 'ext8',
            align:"center",
            dataIndex: 'ext8'
           },
		   {
            title: '创建时间',
            align:"center",
            dataIndex: 'createdate'
           },
		   {
            title: '最后更新时间',
            align:"center",
            dataIndex: 'datelastupdate'
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
          list: "/insurance/insuranceHuaxia/list",
          delete: "/insurance/insuranceHuaxia/delete",
          deleteBatch: "/insurance/insuranceHuaxia/deleteBatch",
          exportXlsUrl: "insurance/insuranceHuaxia/exportXls",
          importExcelUrl: "insurance/insuranceHuaxia/importExcel",
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