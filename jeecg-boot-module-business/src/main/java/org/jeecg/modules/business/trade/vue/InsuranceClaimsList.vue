<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="订单号">
              <a-input placeholder="请输入订单号" v-model="queryParam.orderno"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="手机">
              <a-input placeholder="请输入手机" v-model="queryParam.mobile"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="姓名">
              <a-input placeholder="请输入姓名" v-model="queryParam.name"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="申请数量">
              <a-input placeholder="请输入申请数量" v-model="queryParam.applyqty"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="状态">
              <a-input placeholder="请输入状态" v-model="queryParam.status"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('保险报销')">导出</a-button>
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
    <insuranceClaims-modal ref="modalForm" @ok="modalFormOk"></insuranceClaims-modal>
  </a-card>
</template>

<script>
  import InsuranceClaimsModal from './modules/InsuranceClaimsModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "InsuranceClaimsList",
    mixins:[JeecgListMixin],
    components: {
      InsuranceClaimsModal
    },
    data () {
      return {
        description: '保险报销管理页面',
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
            title: '订单号',
            align:"center",
            dataIndex: 'orderno'
           },
		   {
            title: '手机',
            align:"center",
            dataIndex: 'mobile'
           },
		   {
            title: '姓名',
            align:"center",
            dataIndex: 'name'
           },
		   {
            title: '申请数量',
            align:"center",
            dataIndex: 'applyqty'
           },
		   {
            title: '状态',
            align:"center",
            dataIndex: 'status'
           },
		   {
            title: '报销申请日期',
            align:"center",
            dataIndex: 'applydate'
           },
		   {
            title: '个人凭证号',
            align:"center",
            dataIndex: 'idcard'
           },
		   {
            title: '赔案号',
            align:"center",
            dataIndex: 'claimno'
           },
		   {
            title: '业务系统中更新时间',
            align:"center",
            dataIndex: 'modifydate'
           },
		   {
            title: '项目名称',
            align:"center",
            dataIndex: 'projectname'
           },
		   {
            title: '理赔金额',
            align:"center",
            dataIndex: 'amount'
           },
		   {
            title: '处方医院',
            align:"center",
            dataIndex: 'attr1'
           },
		   {
            title: '处方医院省份',
            align:"center",
            dataIndex: 'attr2'
           },
		   {
            title: '处方医院城市',
            align:"center",
            dataIndex: 'attr3'
           },
		   {
            title: '理赔日期',
            align:"center",
            dataIndex: 'attr4'
           },
		   {
            title: '发票日期',
            align:"center",
            dataIndex: 'attr5'
           },
		   {
            title: 'attr6',
            align:"center",
            dataIndex: 'attr6'
           },
		   {
            title: 'createdate',
            align:"center",
            dataIndex: 'createdate'
           },
		   {
            title: 'datelastupdate',
            align:"center",
            dataIndex: 'datelastupdate'
           },
		   {
            title: '药房名称',
            align:"center",
            dataIndex: 'drugstorename'
           },
		   {
            title: '药房省份',
            align:"center",
            dataIndex: 'drugstoreprovincename'
           },
		   {
            title: '药房城市',
            align:"center",
            dataIndex: 'drugstorecityname'
           },
		   {
            title: '实际购药数量',
            align:"center",
            dataIndex: 'purchaseqty'
           },
		   {
            title: '处方量',
            align:"center",
            dataIndex: 'prescriptionvolume'
           },
		   {
            title: '身份证号码',
            align:"center",
            dataIndex: 'idnumber'
           },
		   {
            title: '性别',
            align:"center",
            dataIndex: 'sex'
           },
		   {
            title: '用户编码',
            align:"center",
            dataIndex: 'usercode'
           },
		   {
            title: '保险申请日期',
            align:"center",
            dataIndex: 'applydateInsurance'
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
          list: "/trade/insuranceClaims/list",
          delete: "/trade/insuranceClaims/delete",
          deleteBatch: "/trade/insuranceClaims/deleteBatch",
          exportXlsUrl: "trade/insuranceClaims/exportXls",
          importExcelUrl: "trade/insuranceClaims/importExcel",
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