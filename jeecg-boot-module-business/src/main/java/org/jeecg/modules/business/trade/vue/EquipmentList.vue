<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="药房名称">
              <a-input placeholder="请输入药房名称" v-model="queryParam.drugstorename"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="药房所在省份">
              <a-input placeholder="请输入药房所在省份" v-model="queryParam.provincename"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="药房所在城市">
              <a-input placeholder="请输入药房所在城市" v-model="queryParam.cityname"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="设备类型（ipad、pos、printer）">
              <a-input placeholder="请输入设备类型（ipad、pos、printer）" v-model="queryParam.equipmenttype"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="设备编码">
              <a-input placeholder="请输入设备编码" v-model="queryParam.equipmentcode"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('ipad登录信息')">导出</a-button>
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
    <equipment-modal ref="modalForm" @ok="modalFormOk"></equipment-modal>
  </a-card>
</template>

<script>
  import EquipmentModal from './modules/EquipmentModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "EquipmentList",
    mixins:[JeecgListMixin],
    components: {
      EquipmentModal
    },
    data () {
      return {
        description: 'ipad登录信息管理页面',
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
            title: '药房名称',
            align:"center",
            dataIndex: 'drugstorename'
           },
		   {
            title: '药房所在省份',
            align:"center",
            dataIndex: 'provincename'
           },
		   {
            title: '药房所在城市',
            align:"center",
            dataIndex: 'cityname'
           },
		   {
            title: '设备类型（ipad、pos、printer）',
            align:"center",
            dataIndex: 'equipmenttype'
           },
		   {
            title: '设备编码',
            align:"center",
            dataIndex: 'equipmentcode'
           },
		   {
            title: '设备名称',
            align:"center",
            dataIndex: 'equipmentname'
           },
		   {
            title: '设备唯一ID',
            align:"center",
            dataIndex: 'equipmentid'
           },
		   {
            title: 'App版本号',
            align:"center",
            dataIndex: 'appversion'
           },
		   {
            title: '最后使用时间（最后交易时间）',
            align:"center",
            dataIndex: 'lastusetime'
           },
		   {
            title: 'remark1',
            align:"center",
            dataIndex: 'remark1'
           },
		   {
            title: 'remark2',
            align:"center",
            dataIndex: 'remark2'
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
            title: '药房id',
            align:"center",
            dataIndex: 'drugstoreid'
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
          list: "/trade/equipment/list",
          delete: "/trade/equipment/delete",
          deleteBatch: "/trade/equipment/deleteBatch",
          exportXlsUrl: "trade/equipment/exportXls",
          importExcelUrl: "trade/equipment/importExcel",
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