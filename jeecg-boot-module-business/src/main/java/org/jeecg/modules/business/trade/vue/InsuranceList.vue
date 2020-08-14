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
            <a-form-item label="订单号">
              <a-input placeholder="请输入订单号" v-model="queryParam.orderno"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="保单编号">
              <a-input placeholder="请输入保单编号" v-model="queryParam.policyno"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="申请人姓名(投保人) 敏感加密">
              <a-input placeholder="请输入申请人姓名(投保人) 敏感加密" v-model="queryParam.policyholdername"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="申请人手机号码(投保人) ">
              <a-input placeholder="请输入申请人手机号码(投保人) " v-model="queryParam.policyholdermobile"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('疗效保险')">导出</a-button>
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
    <insurance-modal ref="modalForm" @ok="modalFormOk"></insurance-modal>
  </a-card>
</template>

<script>
  import InsuranceModal from './modules/InsuranceModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "InsuranceList",
    mixins:[JeecgListMixin],
    components: {
      InsuranceModal
    },
    data () {
      return {
        description: '疗效保险管理页面',
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
            title: '订单号',
            align:"center",
            dataIndex: 'orderno'
           },
		   {
            title: '保单编号',
            align:"center",
            dataIndex: 'policyno'
           },
		   {
            title: '申请人姓名(投保人) 敏感加密',
            align:"center",
            dataIndex: 'policyholdername'
           },
		   {
            title: '申请人手机号码(投保人) ',
            align:"center",
            dataIndex: 'policyholdermobile'
           },
		   {
            title: '申请人身份证号码(投保人)',
            align:"center",
            dataIndex: 'policyholderidcardno'
           },
		   {
            title: '份数',
            align:"center",
            dataIndex: 'copies'
           },
		   {
            title: '区域',
            align:"center",
            dataIndex: 'areaname'
           },
		   {
            title: '省份',
            align:"center",
            dataIndex: 'provincename'
           },
		   {
            title: '城市',
            align:"center",
            dataIndex: 'cityname'
           },
		   {
            title: '区县',
            align:"center",
            dataIndex: 'countyname'
           },
		   {
            title: '状态',
            align:"center",
            dataIndex: 'status'
           },
		   {
            title: '申请日期',
            align:"center",
            dataIndex: 'applicationdate'
           },
		   {
            title: '审核日期',
            align:"center",
            dataIndex: 'auditdate'
           },
		   {
            title: '被保险人姓名',
            align:"center",
            dataIndex: 'insuredname'
           },
		   {
            title: '被保险人手机号码',
            align:"center",
            dataIndex: 'insuredmobile'
           },
		   {
            title: '被保险人身份证号码',
            align:"center",
            dataIndex: 'insuredidcardno'
           },
		   {
            title: '受益人姓名',
            align:"center",
            dataIndex: 'beneficiaryname'
           },
		   {
            title: '受益人手机号码',
            align:"center",
            dataIndex: 'beneficiarymobile'
           },
		   {
            title: '受益人身份证号码',
            align:"center",
            dataIndex: 'beneficiaryidcardno'
           },
		   {
            title: '是否金域扫码',
            align:"center",
            dataIndex: 'scanflag'
           },
		   {
            title: '备注1',
            align:"center",
            dataIndex: 'remark1'
           },
		   {
            title: '备注2',
            align:"center",
            dataIndex: 'remark2'
           },
		   {
            title: '最后更新时间',
            align:"center",
            dataIndex: 'datelastupdate'
           },
		   {
            title: '创建日期',
            align:"center",
            dataIndex: 'createdate'
           },
		   {
            title: '审核成功时间',
            align:"center",
            dataIndex: 'auditfaildate'
           },
		   {
            title: '审核失败时间',
            align:"center",
            dataIndex: 'auditsuccessdate'
           },
		   {
            title: '保单生成日期',
            align:"center",
            dataIndex: 'policydate'
           },
		   {
            title: '购药日期',
            align:"center",
            dataIndex: 'purchasedate'
           },
		   {
            title: '药房名称',
            align:"center",
            dataIndex: 'drugstorename'
           },
		   {
            title: '医院名称',
            align:"center",
            dataIndex: 'hospitalname'
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
          list: "/trade/insurance/list",
          delete: "/trade/insurance/delete",
          deleteBatch: "/trade/insurance/deleteBatch",
          exportXlsUrl: "trade/insurance/exportXls",
          importExcelUrl: "trade/insurance/importExcel",
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