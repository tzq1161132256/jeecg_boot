<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="drugstorename">
              <a-input placeholder="请输入drugstorename" v-model="queryParam.drugstorename"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="drugstoreprovincename">
              <a-input placeholder="请输入drugstoreprovincename" v-model="queryParam.drugstoreprovincename"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="drugstorecityname">
              <a-input placeholder="请输入drugstorecityname" v-model="queryParam.drugstorecityname"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="药房">
              <a-input placeholder="请输入药房" v-model="queryParam.drugstoreid"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="姓名">
              <a-input placeholder="请输入姓名" v-model="queryParam.buyername"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('慢病订单')">导出</a-button>
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
    <orderChronicdisease-modal ref="modalForm" @ok="modalFormOk"></orderChronicdisease-modal>
  </a-card>
</template>

<script>
  import OrderChronicdiseaseModal from './modules/OrderChronicdiseaseModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "OrderChronicdiseaseList",
    mixins:[JeecgListMixin],
    components: {
      OrderChronicdiseaseModal
    },
    data () {
      return {
        description: '慢病订单管理页面',
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
            title: 'drugstorename',
            align:"center",
            dataIndex: 'drugstorename'
           },
		   {
            title: 'drugstoreprovincename',
            align:"center",
            dataIndex: 'drugstoreprovincename'
           },
		   {
            title: 'drugstorecityname',
            align:"center",
            dataIndex: 'drugstorecityname'
           },
		   {
            title: '药房',
            align:"center",
            dataIndex: 'drugstoreid'
           },
		   {
            title: '姓名',
            align:"center",
            dataIndex: 'buyername'
           },
		   {
            title: '手机号',
            align:"center",
            dataIndex: 'buyermobile'
           },
		   {
            title: '身份证号码',
            align:"center",
            dataIndex: 'buyeridcard'
           },
		   {
            title: '订单编码',
            align:"center",
            dataIndex: 'ordercode'
           },
		   {
            title: '订单金额',
            align:"center",
            dataIndex: 'amount'
           },
		   {
            title: '支付时间',
            align:"center",
            dataIndex: 'settlementdate'
           },
		   {
            title: '折后金额',
            align:"center",
            dataIndex: 'afterdiscountamount'
           },
		   {
            title: '折扣',
            align:"center",
            dataIndex: 'discount'
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
            title: '状态',
            align:"center",
            dataIndex: 'status'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/trade/orderChronicdisease/list",
          delete: "/trade/orderChronicdisease/delete",
          deleteBatch: "/trade/orderChronicdisease/deleteBatch",
          exportXlsUrl: "trade/orderChronicdisease/exportXls",
          importExcelUrl: "trade/orderChronicdisease/importExcel",
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