<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :span="6">
            <a-form-item label="业务系统中的订单ID">
              <a-input placeholder="请输入业务系统中的订单ID" v-model="queryParam.orderid"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="6">
            <a-form-item label="订单日期">
              <a-input placeholder="请输入订单日期" v-model="queryParam.orderdate"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('订单')">导出</a-button>
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
        <i class="anticon anticon-info-circle ant-alert-icon"></i>
        <span>已选择</span>
        <a style="font-weight: 600">
          {{ selectedRowKeys.length }}
        </a>
        <span>项</span>
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
    <order-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import OrderModal from './modules/OrderModal'

  export default {
    name: "OrderList",
    mixins: [JeecgListMixin],
    components: {
      OrderModal
    },
    data () {
      return {
        description: '订单管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key: 'rowIndex',
            width: 60,
            align: "center",
            customRender:function (t, r, index) {
              return parseInt(index)+1;
            }
          },
          {
            title: '业务系统中的订单ID',
            align:"center",
            dataIndex: 'orderid'
          },
          {
            title: '订单日期',
            align:"center",
            dataIndex: 'orderdate'
          },
          {
            title: '订单类型，送药到家/送药到家',
            align:"center",
            dataIndex: 'ordertype'
          },
          {
            title: '订单编码',
            align:"center",
            dataIndex: 'ordercode'
          },
          {
            title: '医院名称',
            align:"center",
            dataIndex: 'hospitalname'
          },
          {
            title: '医生名称',
            align:"center",
            dataIndex: 'doctorname'
          },
          {
            title: '是否项目医生',
            align:"center",
            dataIndex: 'doctorflag'
          },
          {
            title: '药店省份',
            align:"center",
            dataIndex: 'drugstoreprovincename'
          },
          {
            title: '药店城市',
            align:"center",
            dataIndex: 'drugstorecityname'
          },
          {
            title: '药店名称',
            align:"center",
            dataIndex: 'drugstorename'
          },
          {
            title: '患者名称',
            align:"center",
            dataIndex: 'patientname'
          },
          {
            title: '患者电话',
            align:"center",
            dataIndex: 'patientmobile'
          },
          {
            title: '购买人名称',
            align:"center",
            dataIndex: 'buyername'
          },
          {
            title: '购买人电话',
            align:"center",
            dataIndex: 'buyermobile'
          },
          {
            title: '收货人名称',
            align:"center",
            dataIndex: 'consigneename'
          },
          {
            title: '收货人电话',
            align:"center",
            dataIndex: 'consigneemobile'
          },
          {
            title: '收货地址',
            align:"center",
            dataIndex: 'consigneeaddress'
          },
          {
            title: '记录生成时间',
            align:"center",
            dataIndex: 'createdate'
          },
          {
            title: '记录修改时间',
            align:"center",
            dataIndex: 'datelastupdate'
          },
          {
            title: '订单状态',
            align:"center",
            dataIndex: 'status'
          },
          {
            title: '订单配送时间',
            align:"center",
            dataIndex: 'deliverytime'
          },
          {
            title: '适应症',
            align:"center",
            dataIndex: 'indication'
          },
          {
            title: '适应症分类',
            align:"center",
            dataIndex: 'indicationtype'
          },
          {
            title: '最后更新人',
            align:"center",
            dataIndex: 'updateuser'
          },
          {
            title: '项目名称',
            align:"center",
            dataIndex: 'projectname'
          },
          {
            title: '患者身份证号码',
            align:"center",
            dataIndex: 'patientidcard'
          },
          {
            title: '购买人身份证号码',
            align:"center",
            dataIndex: 'buyeridcard'
          },
          {
            title: '药房结算金额',
            align:"center",
            dataIndex: 'amount'
          },
          {
            title: '折扣',
            align:"center",
            dataIndex: 'discount'
          },
          {
            title: '折后总金额',
            align:"center",
            dataIndex: 'afterdiscountamount'
          },
          {
            title: '扣款通道',
            align:"center",
            dataIndex: 'paychannel'
          },
          {
            title: '手续费',
            align:"center",
            dataIndex: 'servicecharge'
          },
          {
            title: '补贴金额',
            align:"center",
            dataIndex: 'subsidyamount'
          },
          {
            title: '到账金额',
            align:"center",
            dataIndex: 'amountaccount'
          },
          {
            title: '备注',
            align:"center",
            dataIndex: 'remark'
          },
          {
            title: '结算情况',
            align:"center",
            dataIndex: 'settlementstatus'
          },
          {
            title: '打款日',
            align:"center",
            dataIndex: 'settlementdate'
          },
          {
            title: '是否清洗主数据',
            align:"center",
            dataIndex: 'clearflag'
          },
          {
            title: '患者id',
            align:"center",
            dataIndex: 'patientid'
          },
          {
            title: '药房id',
            align:"center",
            dataIndex: 'drugstoreid'
          },
          {
            title: '是否已有物流信息',
            align:"center",
            dataIndex: 'logisticsflag'
          },
          {
            title: '预计下次购药日期',
            align:"center",
            dataIndex: 'nextpurchasetime'
          },
          {
            title: '订单来源（默沙东精准肝愈）WECHAT/PAD',
            align:"center",
            dataIndex: 'ordersource'
          },
          {
            title: '是否会员订单(BPM项目)',
            align:"center",
            dataIndex: 'memberorderflag'
          },
          {
            title: '用户类型(关爱卡/健康卡/康付卡) (BPM项目)',
            align:"center",
            dataIndex: 'usertype'
          },
          {
            title: '药房渠道（康德乐/众协）(BPM项目)',
            align:"center",
            dataIndex: 'drugstorechannel'
          },
          {
            title: '药店返点收入(BPM项目)',
            align:"center",
            dataIndex: 'rebate'
          },
          {
            title: '净补贴(BPM项目)',
            align:"center",
            dataIndex: 'netsubsidy'
          },
          {
            title: '业务区域名称',
            align:"center",
            dataIndex: 'areaname'
          },
          {
            title: '标识线下导入的数据',
            align:"center",
            dataIndex: 'remark2'
          },
          {
            title: '用券数量',
            align:"center",
            dataIndex: 'ticketcount'
          },
          {
            title: '不通过原因',
            align:"center",
            dataIndex: 'reason'
          },
          {
            title: '期望配送日期',
            align:"center",
            dataIndex: 'expecteddeliverydate'
          },
          {
            title: '医院省份',
            align:"center",
            dataIndex: 'hospitalprovincename'
          },
          {
            title: '医院城市',
            align:"center",
            dataIndex: 'hospitalcityname'
          },
          {
            title: '处方量',
            align:"center",
            dataIndex: 'prescriptionvolume'
          },
          {
            title: '是否退款',
            align:"center",
            dataIndex: 'refundflag'
          },
          {
            title: '退款原因',
            align:"center",
            dataIndex: 'refundreason'
          },
          {
            title: '项目来源名称',
            align:"center",
            dataIndex: 'remark3'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
        // 请求参数
    	url: {
              list: "/trade/order/list",
              delete: "/trade/order/delete",
              deleteBatch: "/trade/order/deleteBatch",
              exportXlsUrl: "trade/order/exportXls",
              importExcelUrl: "trade/order/importExcel",
           },
        }
      },
      computed: {
        importExcelUrl: function(){
          return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
        }
      },


    methods: {

      initDictConfig() {
      }

    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>