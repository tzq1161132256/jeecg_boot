<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="项目名称">
              <a-input placeholder="请输入项目名称" v-model="queryParam.projectname"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="申请人">
              <a-input placeholder="请输入申请人" v-model="queryParam.applicantname"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="申请人手机号">
              <a-input placeholder="请输入申请人手机号" v-model="queryParam.applicantmobile"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="申请人身份证号">
              <a-input placeholder="请输入申请人身份证号" v-model="queryParam.applicantidcardno"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="患者姓名">
              <a-input placeholder="请输入患者姓名" v-model="queryParam.patientname"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('金融分期')">导出</a-button>
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
    <instalment-modal ref="modalForm" @ok="modalFormOk"></instalment-modal>
  </a-card>
</template>

<script>
  import InstalmentModal from './modules/InstalmentModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "InstalmentList",
    mixins:[JeecgListMixin],
    components: {
      InstalmentModal
    },
    data () {
      return {
        description: '金融分期管理页面',
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
            title: '项目名称',
            align:"center",
            dataIndex: 'projectname'
           },
		   {
            title: '申请人',
            align:"center",
            dataIndex: 'applicantname'
           },
		   {
            title: '申请人手机号',
            align:"center",
            dataIndex: 'applicantmobile'
           },
		   {
            title: '申请人身份证号',
            align:"center",
            dataIndex: 'applicantidcardno'
           },
		   {
            title: '患者姓名',
            align:"center",
            dataIndex: 'patientname'
           },
		   {
            title: '患者身份证号',
            align:"center",
            dataIndex: 'patientidcard'
           },
		   {
            title: '申请额度',
            align:"center",
            dataIndex: 'applyauota'
           },
		   {
            title: '批复额度',
            align:"center",
            dataIndex: 'approveauota'
           },
		   {
            title: '申请盒数',
            align:"center",
            dataIndex: 'applyqty'
           },
		   {
            title: '批复盒数',
            align:"center",
            dataIndex: 'approveqty'
           },
		   {
            title: '期数',
            align:"center",
            dataIndex: 'periods'
           },
		   {
            title: '状态',
            align:"center",
            dataIndex: 'status'
           },
		   {
            title: '申请时间',
            align:"center",
            dataIndex: 'applydate'
           },
		   {
            title: '终审时间',
            align:"center",
            dataIndex: 'approvedate'
           },
		   {
            title: '分期单ID',
            align:"center",
            dataIndex: 'applyid'
           },
		   {
            title: '更新时间',
            align:"center",
            dataIndex: 'createdate'
           },
		   {
            title: '最后更新时间',
            align:"center",
            dataIndex: 'datelastupdate'
           },
		   {
            title: '审核失败时间',
            align:"center",
            dataIndex: 'auditfaildate'
           },
		   {
            title: '审核成功时间',
            align:"center",
            dataIndex: 'auditsuccessdate'
           },
		   {
            title: '药房id',
            align:"center",
            dataIndex: 'drugstoreid'
           },
		   {
            title: '资金渠道',
            align:"center",
            dataIndex: 'channel'
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
          list: "/trade/instalment/list",
          delete: "/trade/instalment/delete",
          deleteBatch: "/trade/instalment/deleteBatch",
          exportXlsUrl: "trade/instalment/exportXls",
          importExcelUrl: "trade/instalment/importExcel",
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