<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :span="6">
            <a-form-item label="药房id">
              <a-input placeholder="请输入药房id" v-model="queryParam.pharmacyid"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="6">
            <a-form-item label="药房code">
              <a-input placeholder="请输入药房code" v-model="queryParam.pharmacycode"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('药房pos机pad协议管理')">导出</a-button>
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
    <pharmacyequipment-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import PharmacyequipmentModal from './modules/PharmacyequipmentModal'

  export default {
    name: "PharmacyequipmentList",
    mixins: [JeecgListMixin],
    components: {
      PharmacyequipmentModal
    },
    data () {
      return {
        description: '药房pos机pad协议管理管理页面',
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
            title: '药房id',
            align:"center",
            dataIndex: 'pharmacyid'
          },
          {
            title: '药房code',
            align:"center",
            dataIndex: 'pharmacycode'
          },
          {
            title: '药房名称',
            align:"center",
            dataIndex: 'pharmacyname'
          },
          {
            title: '药企对应的药房名称',
            align:"center",
            dataIndex: 'pharmacyname4partner'
          },
          {
            title: 'pos协议状态',
            align:"center",
            dataIndex: 'posagreementstatus'
          },
          {
            title: 'pos状态',
            align:"center",
            dataIndex: 'posstatus'
          },
          {
            title: 'pad状态',
            align:"center",
            dataIndex: 'padstatus'
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
            title: '删除标志 Y 删除 N未删除',
            align:"center",
            dataIndex: 'deleteflag'
          },
          {
            title: 'pos协议快递单号',
            align:"center",
            dataIndex: 'posagreementexpressno'
          },
          {
            title: 'pos协议邮寄日期',
            align:"center",
            dataIndex: 'posagreementpostdate'
          },
          {
            title: 'pos协议收货人',
            align:"center",
            dataIndex: 'posagreementconsignee'
          },
          {
            title: 'pos协议收货人电话',
            align:"center",
            dataIndex: 'posagreementtelphone'
          },
          {
            title: 'pos协议项目名称',
            align:"center",
            dataIndex: 'posagreementproject'
          },
          {
            title: 'pos协议经办人',
            align:"center",
            dataIndex: 'posagreementoperator'
          },
          {
            title: 'pos协议备注',
            align:"center",
            dataIndex: 'posagreementremark'
          },
          {
            title: 'pos快递单号',
            align:"center",
            dataIndex: 'posexpressno'
          },
          {
            title: 'pos邮寄日期',
            align:"center",
            dataIndex: 'pospostdate'
          },
          {
            title: 'pos收货人',
            align:"center",
            dataIndex: 'posconsignee'
          },
          {
            title: 'pos收货人电话',
            align:"center",
            dataIndex: 'postelphone'
          },
          {
            title: 'pos项目名称',
            align:"center",
            dataIndex: 'posproject'
          },
          {
            title: 'pos经办人',
            align:"center",
            dataIndex: 'posoperator'
          },
          {
            title: 'pos备注',
            align:"center",
            dataIndex: 'posremark'
          },
          {
            title: 'pad协议快递单号',
            align:"center",
            dataIndex: 'padexpressno'
          },
          {
            title: 'pad协议邮寄日期',
            align:"center",
            dataIndex: 'padpostdate'
          },
          {
            title: 'pad协议收货人',
            align:"center",
            dataIndex: 'padconsignee'
          },
          {
            title: 'pad协议收货人电话',
            align:"center",
            dataIndex: 'padtelphone'
          },
          {
            title: 'pad协议项目名称',
            align:"center",
            dataIndex: 'padproject'
          },
          {
            title: 'pad协议经办人',
            align:"center",
            dataIndex: 'padoperator'
          },
          {
            title: 'pad协议备注',
            align:"center",
            dataIndex: 'padremark'
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
        // 请求参数
    	url: {
              list: "/mdm/pharmacyequipment/list",
              delete: "/mdm/pharmacyequipment/delete",
              deleteBatch: "/mdm/pharmacyequipment/deleteBatch",
              exportXlsUrl: "mdm/pharmacyequipment/exportXls",
              importExcelUrl: "mdm/pharmacyequipment/importExcel",
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