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
            <a-form-item label="Pos机序列号">
              <a-input placeholder="请输入Pos机序列号" v-model="queryParam.posid"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="Pos机型号">
              <a-input placeholder="请输入Pos机型号" v-model="queryParam.postype"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('pos机领用记录')">导出</a-button>
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
    <posapply-modal ref="modalForm" @ok="modalFormOk"></posapply-modal>
  </a-card>
</template>

<script>
  import PosapplyModal from './modules/PosapplyModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "PosapplyList",
    mixins:[JeecgListMixin],
    components: {
      PosapplyModal
    },
    data () {
      return {
        description: 'pos机领用记录管理页面',
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
            title: 'Pos机序列号',
            align:"center",
            dataIndex: 'posid'
           },
		   {
            title: 'Pos机型号',
            align:"center",
            dataIndex: 'postype'
           },
		   {
            title: '商户名称',
            align:"center",
            dataIndex: 'merchantname'
           },
		   {
            title: '商户号',
            align:"center",
            dataIndex: 'merchantcode'
           },
		   {
            title: '终端号',
            align:"center",
            dataIndex: 'terminalcode'
           },
		   {
            title: '资金归属',
            align:"center",
            dataIndex: 'funds'
           },
		   {
            title: '药房id',
            align:"center",
            dataIndex: 'drugstoreid'
           },
		   {
            title: '备用字段1',
            align:"center",
            dataIndex: 'remark1'
           },
		   {
            title: '备用字段2',
            align:"center",
            dataIndex: 'remark2'
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
          list: "/mdm/posapply/list",
          delete: "/mdm/posapply/delete",
          deleteBatch: "/mdm/posapply/deleteBatch",
          exportXlsUrl: "mdm/posapply/exportXls",
          importExcelUrl: "mdm/posapply/importExcel",
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