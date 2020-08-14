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
            <a-form-item label="手机号">
              <a-input placeholder="请输入手机号" v-model="queryParam.mobile"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="用药人姓名">
              <a-input placeholder="请输入用药人姓名" v-model="queryParam.name"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="用药人身份证号码">
              <a-input placeholder="请输入用药人身份证号码" v-model="queryParam.idcard"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="预约用药省份">
              <a-input placeholder="请输入预约用药省份" v-model="queryParam.provincename"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('早鸟券申请信息')">导出</a-button>
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
    <ticket-modal ref="modalForm" @ok="modalFormOk"></ticket-modal>
  </a-card>
</template>

<script>
  import TicketModal from './modules/TicketModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "TicketList",
    mixins:[JeecgListMixin],
    components: {
      TicketModal
    },
    data () {
      return {
        description: '早鸟券申请信息管理页面',
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
            title: '手机号',
            align:"center",
            dataIndex: 'mobile'
           },
		   {
            title: '用药人姓名',
            align:"center",
            dataIndex: 'name'
           },
		   {
            title: '用药人身份证号码',
            align:"center",
            dataIndex: 'idcard'
           },
		   {
            title: '预约用药省份',
            align:"center",
            dataIndex: 'provincename'
           },
		   {
            title: '预约用药城市',
            align:"center",
            dataIndex: 'cityname'
           },
		   {
            title: '用户申请状态',
            align:"center",
            dataIndex: 'status'
           },
		   {
            title: '申请时间',
            align:"center",
            dataIndex: 'applytime'
           },
		   {
            title: '审核时间',
            align:"center",
            dataIndex: 'audittime'
           },
		   {
            title: '支付时间',
            align:"center",
            dataIndex: 'paytime'
           },
		   {
            title: '是否激活',
            align:"center",
            dataIndex: 'activationflag'
           },
		   {
            title: '最后更新时间,业务系统中的更新时间',
            align:"center",
            dataIndex: 'datelastupdate'
           },
		   {
            title: '项目名称',
            align:"center",
            dataIndex: 'projectname'
           },
		   {
            title: '记录生成时间',
            align:"center",
            dataIndex: 'createdate'
           },
		   {
            title: '最后更新时间',
            align:"center",
            dataIndex: 'modifydate'
           },
		   {
            title: '交易金额',
            align:"center",
            dataIndex: 'amount'
           },
		   {
            title: '已使用优惠券数量',
            align:"center",
            dataIndex: 'activeticketscount'
           },
		   {
            title: 'usercode',
            align:"center",
            dataIndex: 'usercode'
           },
		   {
            title: 'sequence',
            align:"center",
            dataIndex: 'sequence'
           },
		   {
            title: '手机号加密',
            align:"center",
            dataIndex: 'mobileEncryption'
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
          list: "/trade/ticket/list",
          delete: "/trade/ticket/delete",
          deleteBatch: "/trade/ticket/deleteBatch",
          exportXlsUrl: "trade/ticket/exportXls",
          importExcelUrl: "trade/ticket/importExcel",
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